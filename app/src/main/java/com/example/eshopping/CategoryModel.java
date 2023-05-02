package com.example.eshopping;

public class CategoryModel {

    private String CategoryID;
    private String CategoryName;
    private String CategoryImage;

//    public CategoryModel(String categoryID, String categoryName, String categoryImage) {
//        this.categoryID = categoryID;
//        this.categoryName = categoryName;
//        this.categoryImage = categoryImage;
//    }

    public CategoryModel(){

    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }
}
