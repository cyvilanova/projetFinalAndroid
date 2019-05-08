package com.example.quintessentiel.Product;

/**
 * Object containing the information of a product
 */
public class Product {
    private Integer id; // The id of the product
    private String name; // The name of the product
    private String description; // The description of the product
    private Integer quantity; // The quantity in stock of the product
    private Double price; // The price of the product

    /**
     * Constructor of a product with parameters
     * @param name
     * @param description
     * @param quantity
     * @param price
     */
    public Product(String name, String description, Integer quantity, Double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the id of the product
     * @return The id of the product
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the product
     * @param id The id of the product
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the product
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product
     * @return The description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product
     * @param description The description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the quantity in stock of the product
     * @return The quantity in stock
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity in stock of the product
     * @param quantity The new quantity in stock
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of the product
     * @return The price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product
     * @param price The price of the product
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}
