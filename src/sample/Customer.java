package sample;

import javafx.beans.property.SimpleStringProperty;

public class Customer {

        private int id=-1;
        private final SimpleStringProperty customer;

        public Customer(int id, String customer) {
            this.id = id;
            this.customer = new SimpleStringProperty(customer);
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

    @Override
    public String toString() {
        return customer.getValue();
    }
}

