package dao.interfaces;

import domain.Book;
import domain.Loan;
import exception.DataAccessException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface LoanDao {
    Loan createLoan(Loan loan) throws DataAccessException;
    Optional<Loan> findById(int id) throws DataAccessException;
    void update(Loan loan) throws DataAccessException;
    List<Loan> findAll() throws DataAccessException;

    // Required for the CSV report
    List<Loan> findOverdueLoans() throws DataAccessException;

    void update(Loan loan, Connection connection) throws DataAccessException;
}
