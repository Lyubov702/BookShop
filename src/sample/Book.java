package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private int id=-1;
    private final SimpleStringProperty book;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty author;
    private final SimpleIntegerProperty price;


    public Book(int id, String book, String genre, String author, Integer price) {
        this.id = id;
        this.book = new SimpleStringProperty(book);
        this.genre = new SimpleStringProperty(genre);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleIntegerProperty(price);
    }

    public SimpleStringProperty bookProperty() {
        return book;
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public SimpleStringProperty authorProperty() { return author; }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book.get();
    }

    public String getAuthor() { return author.get(); }

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

    public void setPrice(Integer price) {
        this.price.set(price);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    @Override
    public String toString() {
        return book.getValue();
    }
}
