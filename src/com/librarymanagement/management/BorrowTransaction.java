package com.librarymanagement.management;
import com.librarymanagement.book.Book;
import com.librarymanagement.person.Customer;
import java.time.LocalDate;


public class BorrowTransaction {
    private Book book;
    private Customer customer;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public BorrowTransaction(Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
        this.isReturned = false;
    }

    public void markReturned() {
        this.returnDate = LocalDate.now();
        this.isReturned = true;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public String toString() {
        return "BorrowTransaction{" + "book=" + book + ", customer=" + customer + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + ", isReturned=" + isReturned + '}';
    }
}
