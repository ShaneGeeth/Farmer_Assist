package com.example.farmer_assist;

public class Product {
    private int product_no;
    private String name;
    private double price;
    private String description;

    public Product() {
    }

    public Product(int product_no, String name, double price, String description) {
        this.product_no = product_no;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_no=" + product_no +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
