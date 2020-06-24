package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {

        private int id=-1;
        private final SimpleStringProperty customer;
        private final SimpleIntegerProperty phoneNumber;


        public Customer(int id, String customer, int phoneNumber) {
            this.id = id;
            this.customer = new SimpleStringProperty(customer);
            this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
        }

        public SimpleStringProperty customerProperty() {
            return customer;
        }

        public int getId() {
            return id;
        }

        public String getCustomer() {
            return customer.get();
        }

        public void setCustomer(String customer) {
            this.customer.set(customer);
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber.set(phoneNumber);
        }

    @Override
    public String toString() {
        return customer.getValue();
    }

    public int getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleIntegerProperty phoneNumberProperty() {
        return phoneNumber;
    }
}

