package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CustomerField;

    @FXML
    private Button AddButton;

    @FXML
    void initialize() {
        AddButton.setOnAction(event -> {

            if (CustomerField.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Заполните поле!");
                alert.showAndWait();
                return;
            }

            DataBase db = new DataBase();

            String cust = CustomerField.getText().trim();

            Customer customer = new Customer(0, cust);
            try {
                db.addCustomer(customer);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            AddButton.getScene().getWindow().hide();
        });

    }
}
