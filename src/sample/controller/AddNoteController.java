package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.model.Book;
import sample.model.Customer;
import sample.model.Employee;
import sample.model.Order;

public class AddNoteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dateOfBuyField;

    @FXML
    private ChoiceBox<Customer> Customer;

    @FXML
    private ChoiceBox<Book> Book;

    @FXML
    private ChoiceBox<Employee> Employee;

    @FXML
    private Button AddButton;

    DataBase db = new DataBase();

    @FXML
    void initialize() {

        Book.setItems( db.getBook());
        Customer.setItems(db.getCustomer());
        Employee.setItems(db.getEmployee());

        AddButton.setOnAction(event -> AddNewOrder());
    }

    private void AddNewOrder() {

        Book book = Book.getSelectionModel().getSelectedItem();
        Customer customer = Customer.getValue();
        Employee employee= Employee.getValue();

        if (dateOfBuyField.getText().isEmpty()
        ||book==null
        ||customer==null
        || employee==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");

            alert.showAndWait();
            return;
        }

       String  date = dateOfBuyField.getText().trim();

        Order order = new Order(0, book.getId(), customer.getId(),date, employee.getId());
        try {
                 db.addOrder(order);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            AddButton.getScene().getWindow().hide();
    }
}
