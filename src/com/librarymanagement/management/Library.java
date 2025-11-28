package com.librarymanagement.management;
import com.librarymanagement.book.Book;
import com.librarymanagement.book.Category;
import com.librarymanagement.person.Author;
import com.librarymanagement.person.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private Map<Long, Book> bookMap = new HashMap<>();
    private List<BorrowTransaction> transactions = new ArrayList<>();
    private List<Invoice> invoices = new ArrayList<>();
    //------------------------------------------------------------------------------
    public void addBook(Book book) {
        if (bookMap.containsKey(book.getId())) {
            System.out.println("Book with ID " + book.getId() + " already exists.");
            return;
        }

        bookMap.put(book.getId(), book);
        books.add(book);
    }
    //------------------------------------------------------------------------------
    public Book findBookById(Long id) {
        if (id == null) {
            return null;
        }
        return bookMap.get(id);
    }
    //------------------------------------------------------------------------------
    public List<Book> findBooksByTitle(String title) {
        List<Book> resultByTitle = new ArrayList<>();
        if (title == null) {
            return resultByTitle;
        }

        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
                resultByTitle.add(book);
            }
        }
        return resultByTitle;
    }
    //------------------------------------------------------------------------------
    public List<Book> findBooksByAuthorName(String authorName) {
        List<Book> resultByAuthorName = new ArrayList<>();
        if (authorName == null) {
            return resultByAuthorName;
        }

        for (Book book : books) {
            if (book.getAuthor().getName().toLowerCase().equals(authorName.toLowerCase())) {
                resultByAuthorName.add(book);
            }
        }
        return resultByAuthorName;
    }
    //------------------------------------------------------------------------------
    public boolean deleteBook(Long id) {
        if (id == null) {
            System.out.println("Id cannot be null.");
            return false;
        }

        Book removed = bookMap.remove(id);

        if (removed == null) {
            System.out.println("Book with ID " + id + " not found.");
            return false;
        }

        books.remove(removed);
        return true;
    }
    //------------------------------------------------------------------------------
    public List<Book> listBooksByCategory(Category category) {
        List<Book> resultByCategory = new ArrayList<>();
        if (category == null) {
            return resultByCategory;
        }

        for (Book book : books) {
            if (book.getCategory() == category) {
                resultByCategory.add(book);
            }
        }
        return resultByCategory;
    }

    //------------------------------------------------------------------------------
    public BorrowTransaction borrowBook(Long bookId, Customer customer) {

        if (bookId == null || customer == null) {
            System.out.println("Book id and customer cannot be null.");
            return null;
        }

        if (customer.getBorrowedBooks().size() >= 5) {
            System.out.println("Customer already has 5 books. Cannot borrow more.");
            return null;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return null;
        }

        if (!book.isAvailable()) {
            System.out.println("Book with ID " + bookId + " is not available.");
            return null;
        }

        boolean added = customer.borrowBook(book);
        if (!added) {
            System.out.println("Customer already has this book.");
            return null;
        }
        book.setAvailable(false);

        BorrowTransaction transaction = new BorrowTransaction(book, customer);
        transactions.add(transaction);

        Invoice invoice = new Invoice(transaction, 10.0);
        invoices.add(invoice);

        System.out.println("Book borrowed successfully. Invoice amount: " + invoice.getAmount());

        return transaction;
    }

//------------------------------------------------------------------------------
    public boolean returnBook(Long bookId, Customer customer) {
    if (bookId == null || customer == null) {
        System.out.println("Book id and customer cannot be null.");
        return false;
    }

    Book book = findBookById(bookId);
    if (book == null) {
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }


    if (!customer.getBorrowedBooks().contains(book)) {
        System.out.println("Customer does not have this book.");
        return false;
    }


    BorrowTransaction activeTransaction = null;
    for (BorrowTransaction transaction : transactions) {
        if (transaction.getBook().equals(book)
                && transaction.getCustomer().equals(customer)
                && !transaction.isReturned()) {
            activeTransaction = transaction;
            break;
        }
    }

    if (activeTransaction == null) {
        System.out.println("No active borrow transaction found for this book and customer.");
        return false;
    }


        activeTransaction.markReturned();
        customer.returnBook(book);
        book.setAvailable(true);


    for (Invoice invoice : invoices) {
        if (invoice.getTransaction() == activeTransaction) {
            invoice.refund();
            break;
        }
    }

    System.out.println("Book returned successfully.");
    return true;
    }

    public boolean updateBook(Long id, String newTitle, Author newAuthor, Category newCategory) {
        if (id == null) {
            System.out.println("Id cannot be null.");
            return false;
        }
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book with ID " + id + " not found.");
            return false;
        }

        if(newTitle != null){
            book.setTitle(newTitle);
        }

        if(newAuthor != null){
            book.setAuthor(newAuthor);
        }

        if(newCategory != null){
            book.setCategory(newCategory);
        }



        System.out.println("Book with ID " + id + " updated successfully.");
        return true;
    }
    
    public Customer findOrCreateCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        Customer newCustomer = new Customer(customerName, "defaultPassword");
        customers.add(newCustomer);
        return newCustomer;
    }
}
