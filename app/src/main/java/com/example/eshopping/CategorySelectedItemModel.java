package com.example.eshopping;

public class CategorySelectedItemModel {
    private String categorySelectedItemID;
    private String categorySelectedItemName;
    private String categorySelectedItemDiscountPrice;
    private String categorySelectedItemOriginalPrice;
    private String categorySelectedItemDiscountPercent;
    private String categorySelectedItemStockStatus;
    private String categorySelectedItemShortDescription;
    private String categorySelectedItemImage;

    public CategorySelectedItemModel(String categorySelectedItemID, String categorySelectedItemName, String categorySelectedItemDiscountPrice, String categorySelectedItemOriginalPrice, String categorySelectedItemDiscountPercent, String categorySelectedItemStockStatus, String categorySelectedItemShortDescription, String categorySelectedItemImage) {
        this.categorySelectedItemID = categorySelectedItemID;
        this.categorySelectedItemName = categorySelectedItemName;
        this.categorySelectedItemDiscountPrice = categorySelectedItemDiscountPrice;
        this.categorySelectedItemOriginalPrice = categorySelectedItemOriginalPrice;
        this.categorySelectedItemDiscountPercent = categorySelectedItemDiscountPercent;
        this.categorySelectedItemStockStatus = categorySelectedItemStockStatus;
        this.categorySelectedItemShortDescription = categorySelectedItemShortDescription;
        this.categorySelectedItemImage = categorySelectedItemImage;
    }

    public String getCategorySelectedItemID() {
        return categorySelectedItemID;
    }

    public void setCategorySelectedItemID(String categorySelectedItemID) {
        this.categorySelectedItemID = categorySelectedItemID;
    }

    public String getCategorySelectedItemName() {
        return categorySelectedItemName;
    }

    public void setCategorySelectedItemName(String categorySelectedItemName) {
        this.categorySelectedItemName = categorySelectedItemName;
    }

    public String getCategorySelectedItemDiscountPrice() {
        return categorySelectedItemDiscountPrice;
    }

    public void setCategorySelectedItemDiscountPrice(String categorySelectedItemDiscountPrice) {
        this.categorySelectedItemDiscountPrice = categorySelectedItemDiscountPrice;
    }

    public String getCategorySelectedItemOriginalPrice() {
        return categorySelectedItemOriginalPrice;
    }

    public void setCategorySelectedItemOriginalPrice(String categorySelectedItemOriginalPrice) {
        this.categorySelectedItemOriginalPrice = categorySelectedItemOriginalPrice;
    }

    public String getCategorySelectedItemDiscountPercent() {
        return categorySelectedItemDiscountPercent;
    }

    public void setCategorySelectedItemDiscountPercent(String categorySelectedItemDiscountPercent) {
        this.categorySelectedItemDiscountPercent = categorySelectedItemDiscountPercent;
    }

    public String getCategorySelectedItemStockStatus() {
        return categorySelectedItemStockStatus;
    }

    public void setCategorySelectedItemStockStatus(String categorySelectedItemStockStatus) {
        this.categorySelectedItemStockStatus = categorySelectedItemStockStatus;
    }

    public String getCategorySelectedItemShortDescription() {
        return categorySelectedItemShortDescription;
    }

    public void setCategorySelectedItemShortDescription(String categorySelectedItemShortDescription) {
        this.categorySelectedItemShortDescription = categorySelectedItemShortDescription;
    }

    public String getCategorySelectedItemImage() {
        return categorySelectedItemImage;
    }

    public void setCategorySelectedItemImage(String categorySelectedItemImage) {
        this.categorySelectedItemImage = categorySelectedItemImage;
    }
}
