package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.Configs;
import sample.util.Const;
import sample.model.*;

import java.sql.*;

import static sample.util.Configs.*;

public class DataBase   {

    Connection connection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {


        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }

    public boolean addOrder(Order order) throws SQLException, ClassNotFoundException {

        String insert = String.format("INSERT INTO %1$s (%2$s,%3$s,%4$s,%5$s)" +
                        " VALUES(?,?,?,?)",
                Const.ORDER_TABLE,
                Const.BOOK_ID,
                Const.CUSTOMER_ID,
                Const.ORDER_DATEOFBUY,
                Const.EMPLOYEE_ID);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setInt(1, order.getBookId());
            prSt.setInt(2, order.getCustomerId());
            prSt.setString(3, order.getDate());
            prSt.setInt(4, order.getEmployeeId());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    //ДОБАВЛЕНИЕ КНИГИ В БД
    public boolean addBook(Book book) throws SQLException, ClassNotFoundException {


        String insert = String.format("INSERT INTO %1$s (%2$s,%3$s,%4$s,%5$s)" +
                        " VALUES(?,?,?,?)",
                Const.BOOKS_TABLE,
                Const.BOOKS_BOOK,
                Const.BOOKS_GENRE,
                Const.BOOKS_AUTHOR,
                Const.BOOKS_PRICE);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setString(1, book.getBook());
            prSt.setString(2, book.getGenre());
            prSt.setString(3, book.getAuthor());
            prSt.setInt(4, book.getPrice());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    //ДОБАВЛЕНИЕ КЛИЕНТА В БД
    public boolean addCustomer (Customer cust) throws ClassNotFoundException {


        String insert = String.format("INSERT INTO %1$s (%2$s,%3$s)" +
                        " VALUES(?,?)",
                Const.CUSTOMER_TABLE,
                Const.NAME,
                Const.CUSTOMER_PHONE);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setString(1, cust.getCustomer());
            prSt.setInt(2, cust.getPhoneNumber());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    public ObservableList<OrderWithData> getFullOrders() {

        ResultSet resSet = null;

        String select = "SELECT * FROM  " + Const.ORDER_TABLE
                + " INNER JOIN " +
                Const.BOOKS_TABLE + " ON custom.bookId = books.id "
                + " INNER JOIN " +
                Const.CUSTOMER_TABLE + " ON custom.customerId = customers.id"
                + " INNER JOIN " +
                Const.EMPLOYEE_TABLE + " ON custom.employeeId = employee.id";

        /*
        custom. id -1
        bookId - 2
        customerId -3
        date - 4
        emplId -5
        book.id -6
        book -7
        genre - 8
        author -9
        price - 10
        customer.id -11
        name -12
        phoneNumber - 13
        empl.id - 14
        empl.name - 15
        education - 16

         */

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet= prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<OrderWithData> result = FXCollections.observableArrayList();

        try {
            while(resSet.next()) {
                OrderWithData order = new OrderWithData(
                        resSet.getInt(1),
                        resSet.getString(12),
                        resSet.getString(7),
                        resSet.getString(8),
                        resSet.getString(9),
                        resSet.getInt(10),
                        resSet.getString(4),
                        resSet.getString(15),
                        resSet.getInt(5),
                        resSet.getInt(2),
                        resSet.getInt(3)
                        );
                result.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    //ПОЛУЧЕНИЕ КНИГИ ИЗ БД
    public ObservableList<Book> getBook() {

        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.BOOKS_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet= prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Book> result = FXCollections.observableArrayList();

        try {
            while(resSet.next()) {
                Book book = new Book(
                        resSet.getInt(1),
                        resSet.getString(2),
                        resSet.getString(3),
                        resSet.getString(4),
                        resSet.getInt(5)
                );

               result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    //ПОЛУЧЕНИЕ РАБОТНИКА ИЗ БД
    public ObservableList<Employee> getEmployee() {

        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.EMPLOYEE_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet= prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Employee> result = FXCollections.observableArrayList();

        try {
            while(resSet.next()) {
                Employee employee = new Employee(
                        resSet.getInt(1), //id
                        resSet.getString(2), //name
                        resSet.getString(3) //education
                );
                result.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //ПОЛУЧЕНИЕ КЛИЕНТА ИЗ БД
    public ObservableList<Customer> getCustomer() {

        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet= prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Customer> result = FXCollections.observableArrayList();

        try {
            while(resSet.next()) {
                Customer customer = new Customer(
                        resSet.getInt(1),
                        resSet.getString(2),
                        resSet.getInt(3));

                result.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean editOrder(Order newOrder) {

        String edit = "UPDATE " + Const.ORDER_TABLE + " SET " +
                Const.BOOK_ID + "=?, "
                + Const.CUSTOMER_ID + "=?, "
                + Const.ORDER_DATEOFBUY + "=?, "
                + Const.EMPLOYEE_ID + "=? " +
                " WHERE "+ Const.ORDER_ID + "=? ";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(edit);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            prSt.setInt(1, newOrder.getBookId());
            prSt.setInt(2, newOrder.getCustomerId());
            prSt.setString(3, newOrder.getDate());
            prSt.setInt(4, newOrder.getEmployeeId());
            prSt.setInt(5, newOrder.getId());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteOrder(OrderWithData delOrder) {

        String edit = "DELETE FROM " + Const.ORDER_TABLE + " WHERE " +
                Const.ORDER_ID + "=?";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(edit);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setInt(1, delOrder.getId());
            prSt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException{
        String insert = String.format("INSERT INTO %1$s (%2$s,%3$s)" +
                        " VALUES(?,?)",
                Const.EMPLOYEE_TABLE,
                Const.NAME,
                Const.EMPLOYEE_EDUCATION);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setString(1, employee.getEmployee());
            prSt.setString(2, employee.getEducation());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }




}
