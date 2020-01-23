package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PriceField;

    @FXML
    private Button AddButton;

    @FXML
    private TextField AuthorField;

    @FXML
    private TextField GenreField;

    @FXML
    private TextField BookField;

    @FXML
    void initialize() {
        AddButton.setOnAction(event -> {

        if (    PriceField.getText().isEmpty()
                || BookField.getText().isEmpty()
                || GenreField.getText().isEmpty()
                || AuthorField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");
            alert.showAndWait();
            return;
        }

        DataBase db = new DataBase();

        String book = BookField.getText().trim();
        String genre = GenreField.getText().trim();
        String author = AuthorField.getText().trim();
        Integer price = Integer.valueOf(PriceField.getText().trim());

        Book bookr = new Book(0, book, genre, author, price);
        try {
            db.addBook(bookr);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        AddButton.getScene().getWindow().hide();
        });
    }
}
