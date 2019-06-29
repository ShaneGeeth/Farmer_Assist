package com.example.farmer_assist;

public class OrderHandler {
    private Order order;

    public OrderHandler() {
    }

    public boolean makeOrder(Order newOrder){
        //create order on DB
        return false;
    }

    public boolean updateOrder(Order oldOrder){
        //update order in DB
        return false;
    }

    public boolean removeOrder(Order oldOrder){
        //remove order in DB
        return false;
    }

    public Order[] getAllOders(Order oldOrder){
        //get all orders from DB
        return null;
    }

    public Order[] getAllOdersByUserId(int userID){
        //get all orders that user ordered from db
        return null;
    }
}
