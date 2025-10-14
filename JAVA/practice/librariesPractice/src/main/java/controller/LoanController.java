package controller;

import domain.Loan;
import exception.ServiceException;
import service.interfaces.LoanService;
import java.util.List;

public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    public void createLoan(int bookId, int memberId, int userId) throws ServiceException {
        loanService.createLoan(bookId, memberId, userId);
    }

    public void returnLoan(int loanId) throws ServiceException {
        loanService.returnLoan(loanId);
    }

    public List<Loan> findAllLoans() throws ServiceException {
        return loanService.findAllLoans();
    }

    public List<Loan> findOverdueLoans() throws ServiceException {
        return loanService.findOverdueLoans();
    }
}