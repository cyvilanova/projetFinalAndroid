package com.example.quintessentiel.Product;

import java.io.Serializable;

/**
 * Fichier: Product.java
 * Auteur: Cynthia Vilanova & Philippe Audit-Allaire
 * Fonctionnalité: Object containing the information of a product
 * Date : 8-5-2019
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 * 10-5-2019          CV      Ajout ImagePath
 */
public class Product implements Serializable {
    private Integer id; // The id of the product
    private String name; // The name of the product
    private String description; // The description of the product
    private Integer quantity; // The quantity in stock of the product
    private Double price; // The price of the product
    private String imagePath; // The image path of the product

    /**
     * Constructor of a product with parameters
     *
     * @param name        The name of the product
     * @param description The description of the product
     * @param quantity    The quantity in stock of the product
     * @param price       The price of the product
     * @param imagePath   The image path of the product
     */
    public Product(String name, String description, Double price, Integer quantity, String imagePath) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.imagePath = imagePath;
    }

    /**
     * Returns the id of the product
     *
     * @return The id of the product
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the product
     *
     * @param id The id of the product
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the product
     *
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product
     *
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product
     *
     * @return The description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product
     *
     * @param description The description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the quantity in stock of the product
     *
     * @return The quantity in stock
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity in stock of the product
     *
     * @param quantity The new quantity in stock
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of the product
     *
     * @return The price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product
     *
     * @param price The price of the product
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the image path of the product
     *
     * @return The image path of the product
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the image path of the product
     *
     * @param imagePath The image path of the product
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
