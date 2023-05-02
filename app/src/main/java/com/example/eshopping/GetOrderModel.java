package com.example.eshopping;

public class GetOrderModel {

    private int orderID;
    private int personID;
    private String dateTime;
    private String paymentMethod;
    private String total;

    public GetOrderModel(int orderID, int personID, String dateTime, String paymentMethod, String total) {
        this.orderID = orderID;
        this.personID = personID;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
