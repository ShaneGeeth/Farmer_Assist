package com.example.farmer_assist;

public class ProductItem {

    private Product product;
    private int qty;

    public ProductItem() {
    }

    public ProductItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public boolean makeProductItem(Product newProduct, int qty){
        return false;
    }

    public boolean updateProductItem(Product newProduct, int qty){
        return false;
    }

    public boolean removeProductItem(Product newProduct, int qty){
        return false;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
