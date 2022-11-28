package com.example.grocery;

import java.io.Serializable;

public class DHModel implements Serializable {
    String name;
    String imag_url;
    int price;
    String weight;

    public DHModel() {
    }

    public DHModel(String name, String imag_url, int price, String weight) {
        this.name = name;
        this.imag_url = imag_url;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
