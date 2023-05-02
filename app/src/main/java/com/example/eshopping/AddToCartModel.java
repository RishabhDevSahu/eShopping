package com.example.eshopping;

public class AddToCartModel {
    private int cartId;
    private String ID;
    private String cartImage;
    private String cartName;
    private String cartPrice;
    private String qty;
    private String cartTotalPrice;

    public AddToCartModel(int cartId, String ID, String cartImage, String cartName, String cartPrice, String qty, String cartTotalPrice) {
        this.cartId = cartId;
        this.ID = ID;
        this.cartImage = cartImage;
        this.cartName = cartName;
        this.cartPrice = cartPrice;
        this.qty = qty;
        this.cartTotalPrice = cartTotalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCartImage() {
        return cartImage;
    }

    public void setCartImage(String cartImage) {
        this.cartImage = cartImage;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(String cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
}
