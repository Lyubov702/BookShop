package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.model.*;

public class EditNoteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EditButton;

    @FXML
    private TextField dateOfBuyField;

    @FXML
    private ChoiceBox<Customer> customerChoiceBox;

    @FXML
    private ChoiceBox<Book> bookChoiceBox;

    @FXML
    private ChoiceBox<Employee> employeeChoiceBox;

    public OrderWithData selectedOrder;

    private DataBase db = new DataBase();

    @FXML
    void initialize() {
        EditButton.setOnAction(event -> EditOrder());
        bookChoiceBox.setItems(db.getBook());
        customerChoiceBox.setItems(db.getCustomer());
        employeeChoiceBox.setItems(db.getEmployee());

    }

    public void setSelectedOrder(OrderWithData selectedOrder) {
        this.selectedOrder = selectedOrder;
        customerChoiceBox.setValue(findCustomerById(selectedOrder.getCustomerId()));
        bookChoiceBox.setValue(findBookById(selectedOrder.getBookId()));
        dateOfBuyField.setText(selectedOrder.getDateOfBuy());
        employeeChoiceBox.setValue(findEmployeeById(selectedOrder.getEmployeeId()));

        // employeeField.setText(selectedOrder.getEmployee());
    }

    private Customer findCustomerById(int customerId) {
        for (Customer customer : customerChoiceBox.getItems()) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }


    private Book findBookById(int customerId) {
        for (Book book : bookChoiceBox.getItems()) {
            if (book.getId() == customerId) {
                return book;
            }
        }
        return null;
    }

    private Employee findEmployeeById(int employeeId) {
        for (Employee employee : employeeChoiceBox.getItems()) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }


    private void EditOrder()  {
        DataBase db = new DataBase();

        selectedOrder.setDateOfBuy(dateOfBuyField.getText());
        selectedOrder.setEmployeeId(employeeChoiceBox.getValue().getId());

        selectedOrder.setCustomerId(customerChoiceBox.getValue().getId());
        selectedOrder.setBookId(bookChoiceBox.getValue().getId());


        db.editOrder(new Order(selectedOrder.getId(), selectedOrder.getBookId(),
                selectedOrder.getCustomerId(), selectedOrder.getDateOfBuy(), selectedOrder.getEmployeeId()));

        EditButton.getScene().getWindow().hide();
    }
}