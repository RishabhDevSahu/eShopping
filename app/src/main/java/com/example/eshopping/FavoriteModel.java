package com.example.eshopping;

public class FavoriteModel {
    private String FavoriteID;
    private String ProductID;
    private String FavoriteName;
    private String FavoriteDiscountPrice;
    private String FavoriteOriginalPrice;
    private String FavoriteDiscountPercent;
    private String FavoriteImage;

    public FavoriteModel(){

    }


    public String getFavoriteID() {
        return FavoriteID;
    }

    public void setFavoriteID(String favoriteID) {
        FavoriteID = favoriteID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getFavoriteName() {
        return FavoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        FavoriteName = favoriteName;
    }

    public String getFavoriteDiscountPrice() {
        return FavoriteDiscountPrice;
    }

    public void setFavoriteDiscountPrice(String favoriteDiscountPrice) {
        FavoriteDiscountPrice = favoriteDiscountPrice;
    }

    public String getFavoriteOriginalPrice() {
        return FavoriteOriginalPrice;
    }

    public void setFavoriteOriginalPrice(String favoriteOriginalPrice) {
        FavoriteOriginalPrice = favoriteOriginalPrice;
    }

    public String getFavoriteDiscountPercent() {
        return FavoriteDiscountPercent;
    }

    public void setFavoriteDiscountPercent(String favoriteDiscountPercent) {
        FavoriteDiscountPercent = favoriteDiscountPercent;
    }

    public String getFavoriteImage() {
        return FavoriteImage;
    }

    public void setFavoriteImage(String favoriteImage) {
        FavoriteImage = favoriteImage;
    }
}
