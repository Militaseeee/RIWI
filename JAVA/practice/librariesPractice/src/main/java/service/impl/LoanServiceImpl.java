package service.impl;

import config.DbConfig;
import dao.interfaces.*;
import domain.*;
import exception.DataAccessException;
import exception.NotFoundException;
import exception.ServiceException;
import service.interfaces.LoanService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final LoanDao loanDao;
    private final BookDao bookDao;
    private final MemberDao memberDao;
    private final UserDao userDao;
    private final StatusDao statusDao;

    public LoanServiceImpl(LoanDao loanDao, BookDao bookDao, MemberDao memberDao, UserDao userDao, StatusDao statusDao) {
        this.loanDao = loanDao;
        this.bookDao = bookDao;
        this.memberDao = memberDao;
        this.userDao = userDao;
        this.statusDao = statusDao;
    }

    @Override
    public void createLoan(int bookId, int memberId, int userId) throws ServiceException {

        try {
            // Validate that the entities exist
            Book book = bookDao.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));
            Member member = memberDao.findById(memberId).orElseThrow(() -> new NotFoundException("Member not found"));
            User user = userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            Status onLoanStatus = statusDao.findById(1).orElseThrow(() -> new NotFoundException("Loan status 'On Loan' not found")); // Asumiendo que 1 = 'On Loan'

            if (book.getStock() <= 0) {
                throw new ServiceException("Book is out of stock");
            }
            if (!member.isActive()) {
                throw new ServiceException("Member is not active");
            }

            Loan loan = new Loan();
            loan.setBook(book);
            loan.setMember(member);
            loan.setUser(user);
            loan.setStatus(onLoanStatus);
            loan.setLoanDate(LocalDate.now());
            loan.setReturnDate(LocalDate.now().plusDays(15));

            loanDao.createLoan(loan);

        } catch (DataAccessException | NotFoundException | ServiceException e) {
            throw new ServiceException("Error creating loan: " + e.getMessage(), e);
        }
    }

    @Override
    public void returnLoan(int loanId) throws ServiceException {
        Connection connection = null;
        try {
            connection = DbConfig.getConnection();
            connection.setAutoCommit(false);

            Loan loan = loanDao.findById(loanId)
                    .orElseThrow(() -> new NotFoundException("Loan with ID " + loanId + " not found"));
            Status returnedStatus = statusDao.findById(2)
                    .orElseThrow(() -> new NotFoundException("Loan status 'Returned' not found"));

            if (loan.getStatus().getIdStatus() == returnedStatus.getIdStatus()){
                throw new ServiceException("This loan has already been returned");
            }

            // Calculate fine if there is a delay
            LocalDate today = LocalDate.now();
            BigDecimal fine = BigDecimal.ZERO;
            if (today.isAfter(loan.getReturnDate())) {
                long daysOverdue = ChronoUnit.DAYS.between(loan.getReturnDate(), today);
                fine = new BigDecimal(daysOverdue).multiply(new BigDecimal("5000"));
            }

            // Update loan
            loan.setActualReturn(today);
            loan.setStatus(returnedStatus);
            loan.setFine(fine);
            loanDao.update(loan, connection);

            int bookId = loan.getBook().getIdBook();
            // Busca el objeto Book completo en la base de datos
            Book book = bookDao.findById(bookId)
                    .orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " not found to update stock."));
            // Ahora sí, modifica el stock y actualiza
            book.setStock(book.getStock() + 1);
            bookDao.update(book, connection);

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new ServiceException("Error during rollback: " + ex.getMessage(), ex);
                }
            }
            throw new ServiceException("Error returning loan: " + e.getMessage(), e);

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Loan findLoanById(int id) throws ServiceException {
        try {
            return loanDao.findById(id)
                    .orElseThrow(() -> new NotFoundException("Loan with ID " + id + " not found"));
        } catch (DataAccessException | NotFoundException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Loan> findAllLoans() throws ServiceException {
        try {
            return loanDao.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error retrieving all loans.", e);
        }
    }

    @Override
    public List<Loan> findOverdueLoans() throws ServiceException {
        try {
            return loanDao.findOverdueLoans();
        } catch (DataAccessException e) {
            throw new ServiceException("Error finding overdue loans.", e);
        }
    }
}
