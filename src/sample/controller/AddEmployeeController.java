package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.model.Employee;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmpField;

    @FXML
    private Button AddButton;

    @FXML
    private TextField EducationField;

    @FXML
    void initialize() {

        AddButton.setOnAction(event -> {

            if (EmpField.getText().isEmpty()
                    || EducationField.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Заполните поле!");
                alert.showAndWait();
                return;
            }

            DataBase db = new DataBase();

            String emp = EmpField.getText().trim();
            String education = EducationField.getText().trim();
            Employee employee = new Employee(0, emp, education);

            try {
                db.addEmployee(employee);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            AddButton.getScene().getWindow().hide();

        });
    }
}
