package com.librarymanagement.app;
import com.librarymanagement.book.Book;
import com.librarymanagement.book.Category;
import com.librarymanagement.management.Library;
import com.librarymanagement.person.Author;
import com.librarymanagement.person.Customer;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    private Library library;
    private Scanner scanner;

    public LibraryApp() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            running = handleChoice(choice);
        }

        System.out.println("Exiting system. Goodbye.");
    }

    private void printMenu() {
        System.out.println("----- Library Menu -----");
        System.out.println("1 - Add new book");
        System.out.println("2 - Update book");
        System.out.println("3 - Delete book");
        System.out.println("4 - Find books by title");
        System.out.println("5 - Find books by author");
        System.out.println("6 - List books by category");
        System.out.println("7 - Borrow book");
        System.out.println("8 - Return book");
        System.out.println("0 - Exit");
    }


    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                addBook();
                return true;
            case 2:
                updateBook();
                return true;
            case 3:
                deleteBook();
                return true;
            case 4:
                findBooksByTitle();
                return true;
            case 5:
                findBooksByAuthor();
                return true;
            case 6:
                listBooksByCategory();
                return true;
            case 7:
                borrowBook();
                return true;
            case 8:
                returnBook();
                return true;
            case 0:

                return false;
            default:
                System.out.println("Invalid option. Try again.");
                return true;
        }
    }

//-------------------------------------------------------------------------------
        private void addBook() {
            System.out.println("=== Add New Book ===");


            System.out.print("Enter book id (number): ");
            long id = scanner.nextLong();
            scanner.nextLine();


            System.out.print("Enter book title: ");
            String title = scanner.nextLine();


            System.out.print("Enter author name: ");
            String authorName = scanner.nextLine();
            Author author = new Author(authorName, "password");


            System.out.print("Enter category (SCIENCE, HISTORY, FICTION, TECHNOLOGY, BIOGRAPHY, EDUCATION, HORROR, FANTASY, ROMANCE): ");
            String categoryText = scanner.nextLine().trim().toUpperCase();

            Category category;
            try {
                category = Category.valueOf(categoryText);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category: " + categoryText);
                return;
            }

            Book book = new Book(id, title, author, category, true);
            library.addBook(book);
        }
    //-------------------------------------------------------------------------------
    private void updateBook() {
        System.out.println("=== Update Book ===");
        System.out.print("Enter book id to update: ");

        long id = scanner.nextLong();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("Invalid ID. Please enter a positive number.");
            return;
        }

        Book book = library.findBookById(id);
        if (book == null) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }


        System.out.print("Enter new title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isBlank()) {
            book.setTitle(newTitle);
        }


        System.out.print("Enter new author name (leave blank to keep current): ");
        String newAuthorName = scanner.nextLine();
        if (!newAuthorName.isBlank()) {
            Author newAuthor = new Author(newAuthorName, "password");
            book.setAuthor(newAuthor);
        }


        System.out.print("Enter new category (SCIENCE, HISTORY, FICTION, TECHNOLOGY, BIOGRAPHY, EDUCATION, HORROR, FANTASY, ROMANCE) " +
                "(leave blank to keep current): ");
        String categoryText = scanner.nextLine();
        if (!categoryText.isBlank()) {
            Category newCategory = Category.valueOf(categoryText.toUpperCase());
            book.setCategory(newCategory);
        }

        System.out.println("Book with ID " + id + " updated successfully.");

    }
    //-------------------------------------------------------------------------------
    private void deleteBook() {
        System.out.println("=== Delete Book ===");
        System.out.print("Enter book id to delete: ");

        long id = scanner.nextLong();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("Invalid ID. Please enter a positive number.");
            return;
        }

        Book book = library.findBookById(id);

        if(book == null){
            System.out.println("Book with ID " + id + " not found.");
            return;
        }
        library.deleteBook(id);
        System.out.println("Book with ID " + id + " deleted successfully.");
    }

    //-------------------------------------------------------------------------------
   private void findBooksByTitle() {
       System.out.println("=== Find Books by Title ===");

       System.out.print("Enter the Title of the book you want to search: ");
       String title = scanner.nextLine();

       List<Book> results = library.findBooksByTitle(title);

       if(title == null){
           System.out.println("Book title cannot be null.");
       }
       if(!results.contains(title)){
           System.out.print("The book you were looking for was not found!");
       }

           System.out.println("The book you were looking for was found!");
           for(Book book : results){
               System.out.println(book);
           }
       }

    //-------------------------------------------------------------------------------
    private void findBooksByAuthor() {
    System.out.println("=== Find Books by Author ===");

    System.out.print("Enter the author name: ");
    String authorName = scanner.nextLine();

    List<Book> results = library.findBooksByAuthorName(authorName);

    if (results.isEmpty()) {
        System.out.println("No books found for author: " + authorName);
        return;
    }

    System.out.println("Books found:");
    for (Book book : results) {
        System.out.println(book);
     }
   }
    //-------------------------------------------------------------------------------
    private void listBooksByCategory() {
        System.out.println("=== List Books by Category ===");
        System.out.print("Enter category (SCIENCE, HISTORY, FICTION, TECHNOLOGY, BIOGRAPHY, EDUCATION, HORROR, FANTASY, ROMANCE): ");
        String categoryText = scanner.nextLine().toUpperCase();

        Category category;
        try {
            category = Category.valueOf(categoryText);
        } catch (IllegalArgumentException error) {
            System.out.println("Invalid category: " + categoryText);
            return;
        }

        List<Book> results = library.listBooksByCategory(category);

        if (results.isEmpty()) {
            System.out.println("No books found for category: " + category);
            return;
        }

        System.out.println("Books in category " + category + ":");
        for (Book book : results) {
            System.out.println(book);
        }

    }
    //-------------------------------------------------------------------------------
    private void borrowBook() {
        System.out.println("=== Borrow Book ===");
        System.out.print("Enter book id to borrow: ");

        long id = scanner.nextLong();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("Invalid ID. Please enter a positive number.");
            return;
        }

        Book book = library.findBookById(id);
        if (book == null) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();


        Customer customer = library.findOrCreateCustomer(customerName);


        library.borrowBook(id, customer);
    }
    //-------------------------------------------------------------------------------
    private void returnBook() {
        System.out.println("=== Return Book ===");
        System.out.print("Enter book id to return: ");

        long id = scanner.nextLong();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("Invalid ID. Please enter a positive number.");
            return;
        }

        Book book = library.findBookById(id);
        if (book == null) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();


        Customer customer = library.findOrCreateCustomer(customerName);

        boolean success = library.returnBook(id, customer);

        if (success) {
            System.out.println("Book with ID " + id + " returned successfully by customer: " + customerName + ".");
        } else {
            System.out.println("Book with ID " + id + " could not be returned.");
        }
    }
  }
