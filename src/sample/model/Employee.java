package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Employee {

    private int id=-1;
    private SimpleStringProperty employee;
    private SimpleStringProperty education;


    public Employee(int id, String employee, String education) {
        this.id = id;
        this.employee = new SimpleStringProperty(employee);
        this.education = new SimpleStringProperty(education);
    }

    public Employee() {
    }

    public SimpleStringProperty employeeProperty() {
        return employee;
    }
    public SimpleStringProperty educationProperty() { return education; }

    public int getId() {
        return id;
    }

    public String getEmployee() {
        return employee.get();
    }

    public void setEmployee(String employee) {
        this.employee.set(employee);
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String  getEducation() { return education.get(); }

    @Override
    public String toString() {
        return employee.getValue();
    }


}
