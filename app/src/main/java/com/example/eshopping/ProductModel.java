package com.example.eshopping;

public class ProductModel {

    private String CategoryID;
    private String ProductID;
    private String ProductName;
    private String ProductDiscountPrice;
    private String ProductOriginalPrice;
    private String ProductDiscountPercent;
    private String ProductStock;
    private String ProductStockStatus;
    private String ProductShortDescription;
    private String ProductImage;

    public ProductModel(){

    }

    public ProductModel(String categoryID, String productID, String productName, String productDiscountPrice, String productOriginalPrice, String productDiscountPercent, String productStock, String productStockStatus, String productShortDescription, String productImage) {
        CategoryID = categoryID;
        ProductID = productID;
        ProductName = productName;
        ProductDiscountPrice = productDiscountPrice;
        ProductOriginalPrice = productOriginalPrice;
        ProductDiscountPercent = productDiscountPercent;
        ProductStock = productStock;
        ProductStockStatus = productStockStatus;
        ProductShortDescription = productShortDescription;
        ProductImage = productImage;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDiscountPrice() {
        return ProductDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        ProductDiscountPrice = productDiscountPrice;
    }

    public String getProductOriginalPrice() {
        return ProductOriginalPrice;
    }

    public void setProductOriginalPrice(String productOriginalPrice) {
        ProductOriginalPrice = productOriginalPrice;
    }

    public String getProductDiscountPercent() {
        return ProductDiscountPercent;
    }

    public void setProductDiscountPercent(String productDiscountPercent) {
        ProductDiscountPercent = productDiscountPercent;
    }

    public String getProductStock() {
        return ProductStock;
    }

    public void setProductStock(String productStock) {
        ProductStock = productStock;
    }

    public String getProductStockStatus() {
        return ProductStockStatus;
    }

    public void setProductStockStatus(String productStockStatus) {
        ProductStockStatus = productStockStatus;
    }

    public String getProductShortDescription() {
        return ProductShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        ProductShortDescription = productShortDescription;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }
}
