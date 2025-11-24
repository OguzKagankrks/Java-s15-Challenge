package com.librarymanagement.book;
import com.librarymanagement.person.Author;



public class Book {

    private Long id;
    private String title;
    private Author author;
    private Category category;
    private boolean isAvailable;

    public Book(Long id, String title, Author author, Category category, boolean isAvailable) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;

    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor(){
        return author;
    }

    public Category getCategory(){
        return category;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void updateStatus(boolean status) {
        this.isAvailable = status;
    }

     // @Override hashCode , toString , Equals

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Book book = (Book) obj;

        return id != null && id.equals(book.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0 ;

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author.getName() + '\'' +
                ", category=" + category +
                ", available=" + isAvailable +
                '}';
    }

}
