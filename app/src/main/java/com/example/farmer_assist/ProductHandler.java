package com.example.farmer_assist;

public class ProductHandler {
    private  Product product;


    public ProductHandler() {
    }

    public boolean createProduct(Product newProduct){
        //create new product in db
        return false;
    }

    public boolean removeProduct(Product currentProduct){
        //remove current product from db
        return false;
    }

    public boolean updateProduct(Product newProduct){
        //update product in db
        return false;
    }

    public Product[] getAllProducts(Product newProduct){
        //return all products
        return null;
    }

    public Product[] findProductById(String productName){
        //return all products that contain productName
        return null;
    }
}
