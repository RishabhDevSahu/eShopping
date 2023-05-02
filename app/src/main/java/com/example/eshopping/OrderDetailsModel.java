package com.example.eshopping;

public class OrderDetailsModel {
    private String ProductID;
    private String ProductImage;
    private String ProductName;
    private String ProductPrice;
    private String ProductQuantity;
    private String ProductTotalPrice;

    public OrderDetailsModel(String productID, String productImage, String productName, String productPrice, String productQuantity, String productTotalPrice) {
        ProductID = productID;
        ProductImage = productImage;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductQuantity = productQuantity;
        ProductTotalPrice = productTotalPrice;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductTotalPrice() {
        return ProductTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        ProductTotalPrice = productTotalPrice;
    }
}
