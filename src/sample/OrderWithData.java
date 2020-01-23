package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderWithData {

    private int id;
    private final SimpleStringProperty customer;
    private final SimpleStringProperty book;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty author;
    private final SimpleIntegerProperty price;
    private final SimpleStringProperty dateOfBuy;
    private final SimpleStringProperty employee;
    private  int bookId;
    private  int customerId;

    public OrderWithData(int id, String customer, String book, String genre,
                         String author, Integer price, String dateOfBuy,
                         String employee, int bookId,
                         int customerId) {
        this.id = id;

        this.customer = new SimpleStringProperty(customer);
        this.book = new SimpleStringProperty(book);
        this.genre = new SimpleStringProperty(genre);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleIntegerProperty(price);
        this.dateOfBuy = new SimpleStringProperty(dateOfBuy);
        this.employee = new SimpleStringProperty(employee);
        this.bookId = bookId;
        this.customerId = customerId;
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public SimpleStringProperty bookProperty() {
        return book;
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public SimpleStringProperty dateOfBuyProperty() {
        return dateOfBuy;
    }

    public SimpleStringProperty employeeProperty() {
        return employee;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer.get();
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getBook() {
        return book.get();
    }

    public void setBook(String book) {
        this.book.set(book);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getDateOfBuy() {
        return dateOfBuy.get();
    }

    public void setDateOfBuy(String dateOfBuy) {
        this.dateOfBuy.set(dateOfBuy);
    }

    public String getEmployee() {
        return employee.get();
    }

    public void setEmployee(String employee) {
        this.employee.set(employee);
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
