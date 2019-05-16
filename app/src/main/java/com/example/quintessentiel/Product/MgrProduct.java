package com.example.quintessentiel.Product;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.quintessentiel.Constants;
import com.example.quintessentiel.HttpPostRequest;
import com.example.quintessentiel.LocalDBManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fichier: MgrProduct.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Class accessing the table product in the database
 * and using the class Product to store the table's data
 * Date : 8-5-2019
 * <p>
 * Vérification :
 * Date               Nom     Approuvé
 * <p>
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
 * 10-5-2019          CV      Accès à la base de données centrale
 */
public class MgrProduct {
    private ArrayList<Product> products; // List of products
    private HttpPostRequest httpRequest;
    private LocalDBManager dbManager; // Connection to local database
    private Context context;

    /**
     * Constructor
     */
    public MgrProduct(Context context) {
        this.products = new ArrayList<>();
        this.httpRequest = new HttpPostRequest();
        this.context = context;
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
     * Gets all the sellable products for the catalog
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getAllLocalProducts() {

        dbManager = new LocalDBManager(this.context);
        dbManager.open();

        Cursor result = dbManager.fetch();

        ArrayList<ArrayList<String>> productsList = new ArrayList<>();

        try {
            while (result.moveToNext()) {
                ArrayList<String> productProperties = new ArrayList<>();
                productProperties.add(result.getString(result.getColumnIndex("name")));
                productProperties.add(result.getString(result.getColumnIndex("description")));
                productProperties.add(result.getString(result.getColumnIndex("price")));
                productProperties.add(result.getString(result.getColumnIndex("quantity")));
                productProperties.add(result.getString(result.getColumnIndex("image_path")));
                productsList.add(productProperties);
            }
        } finally {
            result.close();
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

        dbManager = new LocalDBManager(this.context);
        dbManager.open();

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
            dbManager.insert(product);
        }
    }

    /**
     * Checks if the local database exists
     * @param context
     * @return If the local database exists
     */
    private static boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath("QUIN_PRODUCTS.DB");
        return dbFile.exists();
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


}
