package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, String> Customer11;
    @FXML
    private Button AddCustomer;

    @FXML
    private TableView<Book> tableBook;
    @FXML
    private TableColumn<Book, Integer> Price1;
    @FXML
    private TableColumn<Book, String> Book1;
    @FXML
    private TableColumn<Book, String> Genre1;
    @FXML
    private TableColumn<Book, String> Author1;
    @FXML
    private Button addBook;

    @FXML
    private TableView<OrderWithData> tableOrders;
    @FXML
    private TableColumn<OrderWithData, String> Employee;
    @FXML
    private TableColumn<OrderWithData, String> Customer;
    @FXML
    private TableColumn<OrderWithData, Integer> Price;
    @FXML
    private TableColumn<OrderWithData, String> DateOfBuy;
    @FXML
    private TableColumn<OrderWithData, String> Book;
    @FXML
    private TableColumn<OrderWithData, String> Author;
    @FXML
    private Button Edit;
    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private TableColumn<OrderWithData, String> Genre;


    private ObservableList<OrderWithData> orders = FXCollections.observableArrayList();
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        Edit.setDisable(true);
        Delete.setDisable(true);
        tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Edit.setDisable(newValue == null);
            Delete.setDisable(newValue == null);
        });
        tableOrders.setEditable(false);
        Customer.setCellValueFactory(cellData->cellData.getValue().customerProperty());
        Book.setCellValueFactory(cellData->cellData.getValue().bookProperty());
        Genre.setCellValueFactory(cellData->cellData.getValue().genreProperty());
        Author.setCellValueFactory(cellData->cellData.getValue().authorProperty());
        Price.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());
        DateOfBuy.setCellValueFactory(cellData->cellData.getValue().dateOfBuyProperty());
        Employee.setCellValueFactory(cellData->cellData.getValue().employeeProperty());

        updateData();
        tableOrders.getSelectionModel().clearSelection();

        tableBook.setEditable(false);
        Book1.setCellValueFactory(cellData->cellData.getValue().bookProperty());
        Genre1.setCellValueFactory(cellData->cellData.getValue().genreProperty());
        Author1.setCellValueFactory(cellData->cellData.getValue().authorProperty());
        Price1.setCellValueFactory(cellData->cellData.getValue().priceProperty().asObject());

        books.clear();
        books.addAll(new DataBase().getBook());
        tableBook.setItems(books);
        tableBook.getSelectionModel().clearSelection();

        tableCustomers.setEditable(false);
        Customer11.setCellValueFactory(cellData->cellData.getValue().customerProperty());

        customers.clear();
        customers.addAll(new DataBase().getCustomer());
        tableCustomers.setItems(customers);

        tableCustomers.getSelectionModel().clearSelection();


        Add.setOnAction(event -> {
            openAdd();
        });

        Edit.setOnAction(event -> {
            openEdit();
        });

        Delete.setOnAction(event -> {
            delete();
        });

       AddCustomer.setOnAction(event -> {
           addCustomer();
       });
       addBook.setOnAction(event -> {
           addBook();
       });
    }

    private void addCustomer() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AddCustomer.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        customers.clear();
        customers.addAll(new DataBase().getCustomer());
        tableCustomers.setItems(customers);
    }

    private void addBook() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AddBook.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        books.clear();
        books.addAll(new DataBase().getBook());
        tableBook.setItems(books);
    }

    private void delete() {

        db.deleteOrder(tableOrders.getSelectionModel().getSelectedItem());
      updateData();
    }

    DataBase db = new DataBase();

    private void updateData() {
        orders.clear();
        orders.addAll(db.getFullOrders());
        tableOrders.setItems(orders);
    }

    private void openEdit() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/EditNote.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((EditNoteController) loader.getController()).setSelectedOrder(tableOrders.getSelectionModel().getSelectedItem());
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        updateData();
    }

    private void openAdd() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AddNote.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        updateData();
    }
}
