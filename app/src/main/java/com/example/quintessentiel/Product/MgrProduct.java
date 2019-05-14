package com.example.quintessentiel.Product;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.quintessentiel.Constants;
import com.example.quintessentiel.HttpPostRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Fichier: MgrProduct.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Class accessing the table product in the database
 * and using the class Product to store the table's data
 * Date : 8-5-2019
 *
 * Vérification :
 * Date               Nom     Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 * 10-5-2019          CV      Accès à la base de données centrale
*/
public class MgrProduct {
    private ArrayList<Product> products; // List of products
    private HttpPostRequest httpRequest;

    /**
     * Constructor
     */
    public MgrProduct() {
        this.products = new ArrayList<>();
        this.httpRequest = new HttpPostRequest();
    }

    /**
     * Gets all the sellable products for the catalog
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getAllProducts() {
        String query = "SELECT * FROM product WHERE is_sellable = 1";
        Map<String, Object> parameters = new HashMap<>();

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);


        ArrayList<ArrayList<String>> productsList = new ArrayList<>();
        JSONArray jsonProductsArray = null;
        try {
            jsonProductsArray = new JSONArray(result);
            if (jsonProductsArray != null) {
                for (int i = 0; i < jsonProductsArray.length(); i++) {

                    JSONArray jsonProduct = new JSONArray(jsonProductsArray.get(i).toString());
                    ArrayList<String> productProperties = new ArrayList<>();

                    if (jsonProduct != null) {
                        for (int j = 0; j < jsonProduct.length(); j++) {
                            productProperties.add(jsonProduct.get(j).toString());
                        }
                    }

                    productsList.add(productProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        createProducts(productsList);
    }

    /**
     * Gets the information of the product with its id
     * by calling the HttpPostRequest class.
     *
     * @param productId The id of the product
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getProductById(Integer productId) {

        String query = "SELECT * FROM product WHERE id_product = :id_product";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id_product", productId);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);

        ArrayList<ArrayList<String>> productsList = new ArrayList<>();
        JSONArray jsonProductsArray = null;
        try {
            jsonProductsArray = new JSONArray(result);
            if (jsonProductsArray != null) {
                for (int i = 0; i < jsonProductsArray.length(); i++) {

                    JSONArray jsonProduct = new JSONArray(jsonProductsArray.get(i).toString());
                    ArrayList<String> productProperties = new ArrayList<>();

                    if (jsonProduct != null) {
                        for (int j = 0; j < jsonProduct.length(); j++) {
                            productProperties.add(jsonProduct.get(j).toString());
                        }
                    }

                    productsList.add(productProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        createProducts(productsList);
    }

    /**
     * Creates a new object Product with the properties from the database
     * and adds it to the array of products.
     *
     * @param productsList The list of the products properties in string
     */
    public void createProducts(ArrayList<ArrayList<String>> productsList) {
        for (int i = 0; i < productsList.size(); i++) {
            Product product = new Product(
                    productsList.get(i).get(1), // name
                    productsList.get(i).get(3), // description
                    Double.parseDouble(productsList.get(i).get(4)), // price
                    Integer.parseInt(productsList.get(i).get(5)), // quantity
                    productsList.get(i).get(6) // image_path
            );
            product.setId(Integer.parseInt(productsList.get(i).get(0)));
            products.add(product);
        }
    }

    /**
     * Gets the list of products
     *
     * @return The list of products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products
     *
     * @param products The list of products
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertProduct() {
        String query = "INSERT INTO product(name, description, price, quantity, is_sellable) VALUES(:name, :description, :price, :quantity, 1)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":name", "ANDROID");
        parameters.put(":description", "15h DE CANCER");
        parameters.put(":price", 999.99);
        parameters.put(":quantity", 1);

        JSONObject jsonParameters = new JSONObject(parameters);

        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_UPDATE);
        this.httpRequest.executeQuery(query, jsonParameters);
    }

    /**
     * Insert a product from an order
     * @param p Product
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertOrderProduct(Product p, Integer qty, int id) {
        String query = "INSERT INTO `ta_order_product` (`id_order`, `id_product`, `quantity`) VALUES (:id_order, :id_product, :quantity)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id_order", id);
        parameters.put(":id_product", p.getId());
        parameters.put(":quantity", qty);;

        JSONObject jsonParameters = new JSONObject(parameters);

        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_UPDATE);
        this.httpRequest.executeQuery(query, jsonParameters);
    }
}
