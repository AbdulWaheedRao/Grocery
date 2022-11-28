package com.example.grocery;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String productname;
    String productprice;
    String currentDate;
    String currentTime;
    int totalPrice;
    String totalquantity;
    String doucmentId;

    public MyCartModel() {
    }

    public MyCartModel(String productname, String productprice, String currentDate, String currentTime, int totalPrice, String totalquantity, String doucmentId) {

        this.productname = productname;
        this.productprice = productprice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalPrice = totalPrice;
        this.totalquantity = totalquantity;
        this.doucmentId = doucmentId;
    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(String totalquantity) {
        this.totalquantity = totalquantity;
    }

    public String getDoucmentId() {
        return doucmentId;
    }

    public void setDoucmentId(String doucmentId) {
        this.doucmentId = doucmentId;
    }

    @Override
    public String toString() {
        return "MyCartModel{" +
                ", productname='" + productname + '\'' +
                ", productprice='" + productprice + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalquantity='" + totalquantity + '\'' +
                ", doucmentId='" + doucmentId + '\'' +
                '}';
    }
}
