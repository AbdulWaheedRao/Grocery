package com.example.grocery;

public class RecommendedModel {
    String name;
    String type;
    String imag_url;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String type, String imag_url) {
        this.name = name;
        this.type = type;
        this.imag_url = imag_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }
}
