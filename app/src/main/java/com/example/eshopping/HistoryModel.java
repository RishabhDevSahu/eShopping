package com.example.eshopping;

public class HistoryModel {
    private String OrderRandomID;
    private String OrderID;
    private String OrderDate;
    private String PersonName;
    private String OrderTotalAmount;
    private String OrderStatus;
    private String CardNumber;

    public HistoryModel(){

    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getOrderRandomID() {
        return OrderRandomID;
    }

    public void setOrderRandomID(String orderRandomID) {
        OrderRandomID = orderRandomID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getOrderTotalAmount() {
        return OrderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        OrderTotalAmount = orderTotalAmount;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
