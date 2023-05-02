package com.example.eshopping;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="eshopping.db";
    public static final String TABLE_NAME ="WishList";
    public static final String TABLE2_NAME ="AddToCart";
    public static final String TABLE3_NAME ="Address";
    public static final String TABLE4_NAME ="Orders";
    public static final String TABLE5_NAME ="OrderDetails";
    public static final String TABLE6_NAME ="OrderHistory";
    private long Sum=0;

    public DBHelper(@Nullable Context context) {
        super(context, context.getPackageName(),null ,21);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @SuppressLint("NewApi")
    public DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table WishList"+"(SNo integer primary key," +
                " ID text, " +
                " UserID text, " +
                " Image text, " +
                "Name text, " +
                "Price text, " +
                "OrigPrice text, " +
                "DiscountPer text, " +
                "StockStatus text, " +
                "ShortDescription text)");

        sqLiteDatabase.execSQL("create table AddToCart"+"(SNo integer primary key, " +
                " ID text, " +
                " UserID text, " +
                "Image text, " +
                "Name text, " +
                "Price text, " +
                "OrigPrice text, " +
                "DiscountPer text, " +
                "Quantity text," +
                "TotalPrice text)");

        sqLiteDatabase.execSQL("create table Address"+"(SNo integer primary key, " +
                " UserID text, " +
                "FullName text, " +
                "MobileNumber text, " +
                "PinCode text, " +
                "AddressLine text, " +
                "Landmark text, " +
                "City text," +
                "State text," +
                "Country text)");

        sqLiteDatabase.execSQL("create table Orders"+"(SNo integer primary key, " +
                " UserID text, " +
                "AddressID int," +
                "Total text, " +
                "DateandTime text, " +
                "PaymentMethod text, " +
                "Status text)");

        sqLiteDatabase.execSQL("create table OrderDetails"+"(SNo integer primary key, " +
                " OrderID int, " +
                "ProductID text)");

        sqLiteDatabase.execSQL("create table OrderHistory"+"(SNo integer primary key, " +
                " OrderID int, " +
                " OrderDateTime text, " +
                "OrderNewDate text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS WishList");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AddToCart");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Address");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderDetails");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderHistory");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrderHistoryData(int OrderID ,String OrderDateTime,String OrderNewDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderID",OrderID);
        contentValues.put("OrderDateTime",OrderDateTime);
        contentValues.put("OrderNewDate",OrderNewDate);
        db.insert(TABLE6_NAME, null, contentValues);
        return true;
    }

    public boolean insertOrderDetailsData(int OrderID ,String ProductID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderID",OrderID);
        contentValues.put("ProductID",ProductID);
        db.insert(TABLE5_NAME, null, contentValues);
        return true;
    }

    public boolean insertOrdersData(String UserID,int AddressID,String Total,String DateandTime,String PaymentMethod, String Status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID",UserID);
        contentValues.put("AddressID",AddressID);
        contentValues.put("Total",Total);
        contentValues.put("DateandTime",DateandTime);
        contentValues.put("PaymentMethod",PaymentMethod);
        contentValues.put("Status",Status);
        db.insert(TABLE4_NAME, null, contentValues);
        return true;
    }

    public boolean insertAddressData(String UserID,String FullName,String MobileNumber, String PinCode , String AddressLine , String Landmark,String City, String State,String Country)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID",UserID);
        contentValues.put("FullName",FullName);
        contentValues.put("MobileNumber",MobileNumber);
        contentValues.put("PinCode",PinCode);
        contentValues.put("AddressLine", AddressLine);
        contentValues.put("Landmark", Landmark);
        contentValues.put("City", City);
        contentValues.put("State",State);
        contentValues.put("Country",Country);
        db.insert(TABLE3_NAME, null, contentValues);
        return true;
    }

    public boolean updateAddressData(int sNo,String FullName,String MobileNumber, String PinCode , String AddressLine , String Landmark,String City, String State,String Country)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FullName",FullName);
        values.put("MobileNumber",MobileNumber);
        values.put("PinCode",PinCode);
        values.put("AddressLine", AddressLine);
        values.put("Landmark", Landmark);
        values.put("City", City);
        values.put("State",State);
        values.put("Country",Country);
        return  db.update(TABLE3_NAME, values, "SNo=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public boolean insertWishListData(String id,String UID, String image, String name , String price , String origPrice, String discountPer, String stockStatus, String shortDesc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("UserID",UID);
        contentValues.put("Image",image);
        contentValues.put("Name",name);
        contentValues.put("Price", price);
        contentValues.put("OrigPrice",origPrice);
        contentValues.put("DiscountPer",discountPer);
        contentValues.put("StockStatus",stockStatus);
        contentValues.put("ShortDescription",shortDesc);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertCartData(String id,String UID,String image, String name , String price , String origPrice,String discountPer, String quantity,String totalPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("UserID",UID);
        contentValues.put("Image",image);
        contentValues.put("Name",name);
        contentValues.put("Price", price);
        contentValues.put("OrigPrice", origPrice);
        contentValues.put("DiscountPer", discountPer);
        contentValues.put("Quantity",quantity);
        contentValues.put("TotalPrice",totalPrice);
        db.insert(TABLE2_NAME, null, contentValues);
        return true;
    }

    public boolean deleteWishListData(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID" + "=?", new String[]{ID}) > 0;
    }

    public boolean deleteOrderDetails(String OrderID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE5_NAME, "OrderID" + "=?", new String[]{OrderID}) > 0;
    }

    public boolean deleteOrderData(String userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE4_NAME, "UserID" + "=?", new String[]{userID}) > 0;
    }

    public boolean deleteAddToCartData(String userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE2_NAME, "UserID" + "=?", new String[]{userID}) > 0;
    }

    public boolean deleteCartData(int sNo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE2_NAME, "SNo" + "=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public boolean deleteAddressData(int sNo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE3_NAME, "SNo" + "=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public boolean increaseCart(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int qty2 = 0;
        Double totalPrice;
        Cursor c = db.rawQuery("SELECT * FROM AddToCart", null);
        ContentValues values = new ContentValues();
        if (c.moveToFirst()) {
            do {
                qty2 = 1;
                int qty = Integer.parseInt(c.getString(8));
                qty2 = qty2 + qty;
                Double price = Double.parseDouble( c.getString(5));
                totalPrice = qty2*price;
                values.put("Quantity", qty2);
                values.put("TotalPrice",totalPrice);
            } while (c.moveToNext());
        }
        return db.update(TABLE2_NAME, values, "ID=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean increseCartData(int sNo,int qty,String totalPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Quantity", qty);
        values.put("TotalPrice",totalPrice);
        return  db.update(TABLE2_NAME, values, "SNo=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public boolean updateCartTotalPrice(int sNo,String totalPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TotalPrice",totalPrice);
        return  db.update(TABLE2_NAME, values, "SNo=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public boolean decrementCartData(int sNo,int qty,String totalPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Quantity", qty);
        values.put("TotalPrice",totalPrice);

        return  db.update(TABLE2_NAME, values, "SNo=?", new String[]{String.valueOf(sNo)}) > 0;
    }

    public Boolean checkWishListID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("Select * from WishList where ID = ?", new String[]{id});
        if (rs.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkAddToCartID(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("Select * from AddToCart where ID = ?", new String[]{id});
        if (rs.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean getQuantity() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("Select Quantity from AddToCart", null);

        if (rs.getCount() > 0)
            return true;
        else
            return false;
    }
    public long getQuantityValue() {
        SQLiteDatabase db = this.getReadableDatabase();
        int qty2 = 0;
        Cursor c = db.rawQuery("SELECT * FROM AddToCart", null);
        if (c.moveToFirst()) {
            do {
                int qty = Integer.parseInt(c.getString(8));
                qty2 = qty2 + qty;
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return qty2;
    }

    public int getOrderID(String UserID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT sNo FROM Orders where UserID = ?", new String[]{UserID});
        if (c.moveToFirst()) {
            do {
                int sNo = Integer.parseInt(c.getString(0));
                return sNo;
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return 0;
    }

    public String getPersonName(int SNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM Address where SNo = ?", new String[]{String.valueOf(SNo)});
        if(rs!=null && rs.getCount()>0){
            if (rs.moveToFirst()) {
                do {
                    int sNo = rs.getInt(0);
                    String personName;
                    if (sNo == SNo)
                    {
                        personName = rs.getString(2);
                        return personName;
                    }
                } while (rs.moveToNext());
            }
        }
        rs.close();
        db.close();
        return null;
    }

    public String getDateTime(int SNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM Orders where SNo = ?", new String[]{String.valueOf(SNo)});
        if(rs!=null && rs.getCount()>0){
            if (rs.moveToFirst()) {
                do {
                    int sNo = rs.getInt(0);
                    String personName;
                    if (sNo == SNo)
                    {
                        personName = rs.getString(4);
                        return personName;
                    }
                } while (rs.moveToNext());
            }
        }
        rs.close();
        db.close();
        return null;
    }

//    public static GetPersonNameModel getPersonName(int SNo, SQLiteDatabase sqLiteDatabase)
//    {
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT FullName FROM Address where SNo = ?", new String[]{String.valueOf(SNo)});
//        String name = null;
//        if (cursor != null)
//        {
//            if (cursor.moveToFirst())
//            {
//                do
//                {
//                    int sNo = cursor.getInt(0);
//                    String personName;
//                    if (sNo == SNo)
//                    {
//                        personName = cursor.getString(2);
//                        name = personName;
//                        return new GetPersonNameModel(personName);
//                    }
//                }
//                while (cursor.moveToNext());
//            }
//        }
//        return null;
//    }

//    public int getOrderID(String UserID){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int orderID;
//        Cursor c = db.rawQuery("SELECT * FROM Orders where UserID = ?", new String[]{UserID});
//        int count = c.getCount();
//        if(count == 1){
//            c.moveToFirst();
//            orderID = c.getInt(0);
//        }
//        c.close();
//        db.close();
//        return orderID;
//    }

    public ArrayList<WishListModel> getWishListModel() {
        ArrayList<WishListModel> getWishListRecords = new ArrayList<WishListModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from WishList",null);
        if (rs!= null && rs.moveToFirst()) {
            String id,img,name,price,origPrice,discountPer,stockStatus,shortDesc;
            int sNo;
            do {
                sNo = rs.getInt(0);
                id = rs.getString(1);
                img = rs.getString(3);
                name = rs.getString(4);
                price = rs.getString(5);
                origPrice = rs.getString(6);
                discountPer = rs.getString(7);
                stockStatus = rs.getString(8);
                shortDesc = rs.getString(9);
                getWishListRecords.add(new WishListModel(sNo,id,img,name,price,origPrice,discountPer,stockStatus,shortDesc));
                Log.d("Data",id+img+name+price+origPrice+discountPer);

            } while (rs.moveToNext());
        }
        rs.close();
        return getWishListRecords;
    }

    public ArrayList<AddToCartModel> getCartData() {
        ArrayList<AddToCartModel> getCartRecords = new ArrayList<AddToCartModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from AddToCart", null);
        if (rs!= null && rs.moveToFirst()) {
            String id,name,img,price,qty,totalPrice;
            int sNo;
            do {
                sNo = rs.getInt(0);
                id = rs.getString(1);
                img = rs.getString(3);
                name = rs.getString(4);
                price = rs.getString(5);
                qty = rs.getString(8);
                totalPrice = rs.getString(9);
                getCartRecords.add(new AddToCartModel(sNo,id,img,name,price,qty,totalPrice));

            } while (rs.moveToNext());
        }
        rs.close();
        return getCartRecords;
    }

//    public ArrayList<HistoryModel> getOrderHistory() {
//        ArrayList<HistoryModel> getCartRecords = new ArrayList<HistoryModel>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor rs = db.rawQuery("select * from OrderHistory", null);
//        if (rs!= null && rs.moveToFirst()) {
//            String dateTime,newDate;
//            int id,sNo;
//            do {
//                sNo = rs.getInt(0);
//                id = rs.getInt(1);
//                dateTime = rs.getString(2);
//                newDate = rs.getString(3);
//                getCartRecords.add(new HistoryModel(sNo,id,dateTime,newDate));
//
//            } while (rs.moveToNext());
//        }
//        rs.close();
//        return getCartRecords;
//    }

    public ArrayList<OrderDetailsModel> getProductOrderDetails() {
        ArrayList<OrderDetailsModel> getOrderDetails = new ArrayList<OrderDetailsModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from AddToCart", null);
        if (rs!= null && rs.moveToFirst()) {
            String id,name,img,price,qty,totalPrice;
            do {
                id = rs.getString(1);
                img = rs.getString(3);
                name = rs.getString(4);
                price = rs.getString(5);
                qty = rs.getString(8);
                totalPrice = rs.getString(9);
                getOrderDetails.add(new OrderDetailsModel(id,img,name,price,qty,totalPrice));

            } while (rs.moveToNext());
        }
        rs.close();
        return getOrderDetails;
    }

    public static GetAllProductsModel getProductDetails(String UserID, SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM AddToCart where UserID = ?", new String[]{UserID});
        String allProducts = null;
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    String userID = cursor.getString(2);
                    String id,name,img,price,qty,totalPrice;
                    if (userID.equals(UserID))
                    {
                        id = cursor.getString(1);
                        allProducts = id;
                        return new GetAllProductsModel(id);
                    }
                }
                while (cursor.moveToNext());
            }
        }
        return null;
    }

    public static GetOrderModel getOrderModel(String UserID, SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Orders where UserID = ?", new String[]{UserID});
        String orders = null;
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    String userID = cursor.getString(1);
                    String dateTime,paymentMethod,total;
                    int id,personID;
                    if (userID.equals(UserID))
                    {
                        id = cursor.getInt(0);
                        personID = cursor.getInt(2);
                        dateTime = cursor.getString(4);
                        paymentMethod = cursor.getString(5);
                        total = cursor.getString(3);
                        orders = id + personID + dateTime + paymentMethod + total;
                        return new GetOrderModel(id,personID,dateTime,paymentMethod,total);
                    }
                }
                while (cursor.moveToNext());
            }
        }
        return null;
    }

    public ArrayList<AddAddressModel> getPersonAddress(String uID) {
        ArrayList<AddAddressModel> getAddress = new ArrayList<AddAddressModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from Address where UserID = ?",new String[]{String.valueOf(uID)});
        if (rs!= null && rs.moveToFirst()) {
            String userID,personName,personAddress,personLandMark,personCity,personState,personPinCode,personCountry,personMobileNumber;
            int sNo;
            do {
                sNo = rs.getInt(0);
                userID = rs.getString(1);
                personName = rs.getString(2);
                personMobileNumber = rs.getString(3);
                personPinCode = rs.getString(4);
                personAddress = rs.getString(5);
                personLandMark = rs.getString(6);
                personCity = rs.getString(7);
                personState = rs.getString(8);
                personCountry = rs.getString(9);
                getAddress.add(new AddAddressModel(sNo,userID,personName,personAddress,personLandMark,personCity,personState,personPinCode,personCountry,personMobileNumber));

            } while (rs.moveToNext());
        }
        rs.close();
        return getAddress;
    }

    public ArrayList<AddAddressModel> getPersonAdd(int sno) {
        ArrayList<AddAddressModel> getAddress = new ArrayList<AddAddressModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from Address where SNo = ?",new String[]{String.valueOf(sno)});
        if (rs!= null && rs.moveToFirst()) {
            String userID,personName,personAddress,personLandMark,personCity,personState,personPinCode,personCountry,personMobileNumber;
            int sNo;
            do {
                sNo = rs.getInt(0);
                userID = rs.getString(1);
                personName = rs.getString(2);
                personMobileNumber = rs.getString(3);
                personPinCode = rs.getString(4);
                personAddress = rs.getString(5);
                personLandMark = rs.getString(6);
                personCity = rs.getString(7);
                personState = rs.getString(8);
                personCountry = rs.getString(9);
                getAddress.add(new AddAddressModel(sNo,userID,personName,personAddress,personLandMark,personCity,personState,personPinCode,personCountry,personMobileNumber));

            } while (rs.moveToNext());
        }
        rs.close();
        return getAddress;
    }

//    public static GetSumOfTotalPriceModel getSumOfTotalPrice(String UserID, SQLiteDatabase sqLiteDatabase)
//    {
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM AddToCart where UserID = ?", new String[]{UserID});
//        double PriceSum = 0;
//        if (cursor != null)
//        {
//            if (cursor.moveToFirst())
//            {
//                do
//                {
//                    String userID = cursor.getString(2);
//                    if (userID.equals(UserID))
//                    {
//                        Double qty = Double.parseDouble( cursor.getString(8));
//                        Double price = Double.parseDouble( cursor.getString(5));
//                        PriceSum += qty*price;
//                        return new GetSumOfTotalPriceModel(PriceSum);
//                    }
//                }
//                while (cursor.moveToNext());
//            }
//        }
//        return null;
//    }

    @SuppressLint("Range")
    public double sumOfTotalPrice() {
        double PriceSum = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM AddToCart", null);
        if (c.moveToFirst()) {
            do {
                Double qty = Double.parseDouble( c.getString(8));
                Double price = Double.parseDouble( c.getString(5));
                PriceSum += qty*price;
            } while (c.moveToNext());
        }
        c.close();
        db.close();



//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery(" select * from  " + "AddToCart" , null);
//        ArrayList<AddToCartListData> list = new ArrayList<AddToCartListData>();
//        try {
//            res.moveToFirst();
//            for (int i = 0; i < res.getCount(); i++) {
//
//                AddToCartListData addToCartListData = new AddToCartListData();
//                try {
//                    int total = Integer.parseInt(res.getString(res.getColumnIndex("TotalPrice")));
//
//                    Sum = Sum + total;
//                    Log.d("Hello",String.valueOf(Sum));
//                    return Sum;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                list.add(addToCartListData);
//                res.moveToNext();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        Log.d("hello",String.valueOf(PriceSum));
        return PriceSum;
    }

    static String numDifferentiation(double val) {
        String result = "";
        if(val<100000){
            return String.valueOf(val);
        }
        else if (val >= 10000000) {
            double a = val/10000000;
            result = String.valueOf((a)) + " Cr";
        } else if (val >= 100000) {
            double a = val/100000;
            result = String.valueOf(a) + " Lac";
        }
        return result;
    }
}
