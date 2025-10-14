package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {

    private int idLoan;
    private User user;
    private Book book;
    private Member member;
    private Status status;

    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate actualReturn;
    private BigDecimal fine; // multa

    public Loan() {
    }

    public Loan(int idLoan, User user, Book book, Member member, Status status, LocalDate loanDate, LocalDate returnDate, LocalDate actualReturn, BigDecimal fine) {
        this.idLoan = idLoan;
        this.user = user;
        this.book = book;
        this.member = member;
        this.status = status;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.actualReturn = actualReturn;
        this.fine = fine;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getActualReturn() {
        return actualReturn;
    }

    public void setActualReturn(LocalDate actualReturn) {
        this.actualReturn = actualReturn;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "idLoan=" + idLoan +
                ", user=" + user +
                ", book=" + book +
                ", member=" + member +
                ", status=" + status +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", actualReturn=" + actualReturn +
                ", fine=" + fine +
                '}';
    }
}
