package com.librarymanagement.person;
import com.librarymanagement.book.Book;
import java.util.HashSet;
import java.util.Set;

public class Customer extends Person {


    public void borrowBook(Book book){

    }

    public void returnBook(Book book){

    }

    public Customer(String name, String password) {
        super(name, password);
    }


    @Override
    public String whoYouAre() {
        return "Customer";
    }

    private Set<Book> borrowedBooks = new HashSet(); // set kullanılmasının sebebi aynı kitabın tekrar eklenmesini engellemek





    // @Override hashCode , toString , Equals


}
