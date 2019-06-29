package com.example.farmer_assist;

import java.util.Date;

public class Order {
    private int order_id;
    private int user_id;
    private Date orderedDate;
    private String location;
    private  String status;
    private ProductItem productItem;

    public Order() {
    }

    public Order(int order_id, int user_id, Date orderedDate, String location, String status, ProductItem productItem) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.orderedDate = orderedDate;
        this.location = location;
        this.status = status;
        this.productItem = productItem;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }
}
