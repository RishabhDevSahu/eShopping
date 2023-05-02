package com.example.eshopping;

public class WishListModel {

    private int id;
    private String wishListID;
    private String wishListImage;
    private String wishListName;
    private String wishListDiscountPrice;
    private String wishListOriginalPrice;
    private String wishListDiscountPercent;
    private String wishListStockStatus;
    private String wishListShortDescription;

    public WishListModel(int id, String wishListID, String wishListImage, String wishListName, String wishListDiscountPrice, String wishListOriginalPrice, String wishListDiscountPercent, String wishListStockStatus, String wishListShortDescription) {
        this.id = id;
        this.wishListID = wishListID;
        this.wishListImage = wishListImage;
        this.wishListName = wishListName;
        this.wishListDiscountPrice = wishListDiscountPrice;
        this.wishListOriginalPrice = wishListOriginalPrice;
        this.wishListDiscountPercent = wishListDiscountPercent;
        this.wishListStockStatus = wishListStockStatus;
        this.wishListShortDescription = wishListShortDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishListID() {
        return wishListID;
    }

    public void setWishListID(String wishListID) {
        this.wishListID = wishListID;
    }

    public String getWishListImage() {
        return wishListImage;
    }

    public void setWishListImage(String wishListImage) {
        this.wishListImage = wishListImage;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public String getWishListDiscountPrice() {
        return wishListDiscountPrice;
    }

    public void setWishListDiscountPrice(String wishListDiscountPrice) {
        this.wishListDiscountPrice = wishListDiscountPrice;
    }

    public String getWishListOriginalPrice() {
        return wishListOriginalPrice;
    }

    public void setWishListOriginalPrice(String wishListOriginalPrice) {
        this.wishListOriginalPrice = wishListOriginalPrice;
    }

    public String getWishListDiscountPercent() {
        return wishListDiscountPercent;
    }

    public void setWishListDiscountPercent(String wishListDiscountPercent) {
        this.wishListDiscountPercent = wishListDiscountPercent;
    }

    public String getWishListStockStatus() {
        return wishListStockStatus;
    }

    public void setWishListStockStatus(String wishListStockStatus) {
        this.wishListStockStatus = wishListStockStatus;
    }

    public String getWishListShortDescription() {
        return wishListShortDescription;
    }

    public void setWishListShortDescription(String wishListShortDescription) {
        this.wishListShortDescription = wishListShortDescription;
    }
}
