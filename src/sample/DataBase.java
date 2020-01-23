package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DataBase extends Configs {

    Connection dbConection;

    public Connection getDbConection()
            throws ClassNotFoundException, SQLException {


        String conectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConection = DriverManager.getConnection(conectionString, dbUser, dbPass);

        return dbConection;
    }

    public boolean addOrder(Order order) throws SQLException, ClassNotFoundException {


        String insert = String.format("INSERT INTO %1$s (%2$s,%3$s,%4$s,%5$s)" +
                        " VALUES(?,?,?,?)",
                Const.ORDER_TABLE,
                Const.BOOK_ID,
                Const.CUSTOMER_ID,
                Const.ORDER_DATEOFBUY,
                Const.ORDER_EMPLOYEE);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setInt(1, order.getBookId());
            prSt.setInt(2, order.getCustomerId());
            prSt.setString(3, order.getDate());
            prSt.setString(4, order.getEmployee());

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
            prSt = getDbConection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setString(1, book.getBook());
            prSt.setString(2, book.getGenre());
            prSt.setString(3, book.getAuthor());
            prSt.setInt(4, (int) book.getPrice());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    //ДОБАВЛЕНИЕ КЛИЕНТА В БД
    public boolean addCustomer (Customer cust) throws SQLException, ClassNotFoundException {


        String insert = String.format("INSERT INTO %1$s (%2$s)" +
                        " VALUES(?)",
                Const.CUSTOMER_TABLE,
                Const.NAME);

        PreparedStatement prSt = null;
        try {
            prSt = getDbConection().prepareStatement(insert);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prSt.setString(1, cust.getCustomer());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    public ObservableList<OrderWithData> getFullOrders() {

        ResultSet resSet = null;

        String select = "SELECT * FROM  " + Const.ORDER_TABLE + " INNER JOIN " +
                Const.BOOKS_TABLE + " ON custom.bookId = books.id "
                + " INNER JOIN " +
                Const.CUSTOMER_TABLE + " ON custom.customerId = customers.id  ";

        /*
        custom. id -1
        bookId - 2
        customerId -3
        date - 4
        empl -5
        book.id -6
        book -7
        genre - 8
        author -9
        price - 10
        customer.id -11
        name -12
         */

        try {
            PreparedStatement prSt = getDbConection().prepareStatement(select);
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
                        resSet.getString(5),
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
            PreparedStatement prSt = getDbConection().prepareStatement(select);
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

    //ПОЛУЧЕНИЕ КЛИЕНТА ИЗ БД
    public ObservableList<Customer> getCustomer() {

        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE;

        try {
            PreparedStatement prSt = getDbConection().prepareStatement(select);
            resSet= prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Customer> result = FXCollections.observableArrayList();

        try {
            while(resSet.next()) {
                Customer customer = new Customer(
                        resSet.getInt(1),
                        resSet.getString(2)
                );

                result.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean editOrder(Order newOrder) throws SQLException, ClassNotFoundException {

        String edit = "UPDATE " + Const.ORDER_TABLE + " SET " +
                Const.BOOK_ID + "=?, "
                + Const.CUSTOMER_ID + "=?, "
                + Const.ORDER_DATEOFBUY + "=?, "
                + Const.ORDER_EMPLOYEE + "=? " +
                " WHERE "+ Const.ORDER_ID + "=? ";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConection().prepareStatement(edit);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            prSt.setInt(1, newOrder.getBookId());
            prSt.setInt(2, newOrder.getCustomerId());
            prSt.setString(3, newOrder.getDate());
            prSt.setString(4, newOrder.getEmployee());
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
            prSt = getDbConection().prepareStatement(edit);
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
}
