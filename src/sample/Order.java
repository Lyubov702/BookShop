package sample;

public class Order {

    private int id = -1;
    private int bookId;
    private int customerId;
    private String date;
    private String employee;

    public Order(int id, int bookId, int customerId, String date,String employee) {
        this.id = id;
        this.bookId = bookId;
        this.customerId = customerId;
        this.date = date;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
