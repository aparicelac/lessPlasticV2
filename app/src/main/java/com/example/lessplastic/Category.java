package com.example.lessplastic;

public class Category {

    public String color;
    public String title;
    public String status;
    public String weight;
    public String quantity;

    public Category(String color, String title, String status, String weight, String quantity) {
        this.color = color;
        this.title = title;
        this.status = status;
        this.weight = weight;
        this.quantity = quantity;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
