package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddNoteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dateOfBuyField;

    @FXML
    private TextField employeeField;

    @FXML
    private ChoiceBox<Customer> Customer;

    @FXML
    private ChoiceBox<Book> Book;

    @FXML
    private Button AddButton;
    DataBase db = new DataBase();

    @FXML
    void initialize() {

        Book.setItems( db.getBook());
        Customer.setItems(db.getCustomer());

        AddButton.setOnAction(event -> AddNewOrder());
    }

    private void AddNewOrder() {

        Book book = Book.getSelectionModel().getSelectedItem();
        Customer customer = Customer.getValue();

        if (employeeField.getText().isEmpty()
        ||book==null
        ||customer==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");

            alert.showAndWait();
            return;
        }

        String employee = employeeField.getText().trim();

        Order order = new Order(0, book.getId(), customer.getId(),"ffgd", employee);
        try {
                 db.addOrder(order);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            AddButton.getScene().getWindow().hide();
    }
}
