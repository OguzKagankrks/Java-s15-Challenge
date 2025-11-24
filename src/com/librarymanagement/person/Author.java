package com.librarymanagement.person;
import com.librarymanagement.book.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author extends Person {

    public Author(String name, String password){
        super(name, password);

    }

    @Override
    public String whoYouAre(){
        return "Author";
    }

    private List<Book> writtenBooks = new ArrayList<>();

    public List<Book> getWrittenBooks(){
        return Collections.unmodifiableList(writtenBooks);

    }

    public void addWrittenBooks(Book book){
        writtenBooks.add(book);
    }

    // @Override hashCode , toString , Equals


}
