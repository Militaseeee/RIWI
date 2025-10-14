package service.interfaces;

import domain.Loan;
import exception.ServiceException;
import java.util.List;


public interface LoanService {

    void createLoan(int bookId, int memberId, int userId) throws ServiceException;

    void returnLoan(int loanId) throws ServiceException;

    Loan findLoanById(int id) throws ServiceException;

    List<Loan> findAllLoans() throws ServiceException;

    // Find all loans that are overdue and have not been repaid
    List<Loan> findOverdueLoans() throws ServiceException;
}
