package com.librarymanagement.person;

import com.librarymanagement.book.Book;

public class Librarian extends Person  {


    public Librarian(String name, String password){
        super(name, password);

    }

    @Override
    public String whoYouAre(){
        return "Librarian";
    }

    public void searchBook() {


    }

    public void issueBook(Customer customer, Book book) {


    }

    public void takeBackBook() {


    }


}
