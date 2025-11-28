package com.librarymanagement.person;
import com.librarymanagement.book.Book;
import java.util.HashSet;
import java.util.Set;

public class Customer extends Person {

    private Set<Book> borrowedBooks = new HashSet<>();

    public boolean borrowBook(Book book) {
        if (book == null) {
            return false;
        }
        return borrowedBooks.add(book);
    }


        public boolean returnBook(Book book) {
            if (book == null) {
                return false;
            }
            return borrowedBooks.remove(book);
        }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Customer(String name, String password) {
        super(name, password);
    }


    @Override
    public String whoYouAre() {
        return "Customer";
    }







    // @Override hashCode , toString , Equals


}
