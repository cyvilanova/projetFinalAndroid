package com.example.quintessentiel.Product;

import java.util.ArrayList;

/**
 * Class accessing the table product in the database
 * and using the class Product to store the table's data
 */
public class MgrProduct {
    private ArrayList<Product> products; // List of products

    /**
     * Constructor
     */
    public MgrProduct() {
        this.products = new ArrayList<>();
    }

    /**
     * Gets the list of products
     * @return The list of products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products
     * @param products The list of products
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
