/****************************************
 Fichier: MgrOrder.java
 Auteur: Catherine Bronsard
 Fonctionnalité: Manager des commandes
 Date : 8-4-2019

 Vérification :
 Date               Nom                   Approuvé
 =========================================================


 Historique de modifications :
 Date               Nom     Description
 =========================================================
 8-4-2019           CB      Création
 11-05-2019         CB      Database connection
 11-05-2019         CB      Modification
 ****************************************/
package com.example.quintessentiel.Order;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.quintessentiel.Constants;
import com.example.quintessentiel.HttpPostRequest;
import com.example.quintessentiel.Product.MgrProduct;
import com.example.quintessentiel.Product.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MgrOrder {
    private MgrProduct mgrProduct;
    private HttpPostRequest httpRequest;

    public MgrOrder() {
        this.mgrProduct = new MgrProduct();
        this.httpRequest = new HttpPostRequest();
    }

    /**
     * Insert a new order
     * @param order Order containing the products, client, shipping method, etc
     */
    public void insertOrder(Order order) {
        String query = "";
    }

    /**
     *
     * @param id_client
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Order> getAllOrderClient(int id_client) {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT id_order FROM `order` WHERE id_client = :client";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":client", id_client);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);

        ArrayList<ArrayList<String>> ordersList = new ArrayList<>();
        JSONArray jsonProductsArray = null;
        try {
            Log.d("FML", "getAllOrderClient: " + result);
            jsonProductsArray = new JSONArray(result);
            if (jsonProductsArray != null) {
                for (int i = 0; i < jsonProductsArray.length(); i++) {

                    JSONArray jsonProduct = new JSONArray(jsonProductsArray.get(i).toString());
                    ArrayList<String> orderProperties = new ArrayList<>();

                    if (jsonProduct != null) {
                        for (int j = 0; j < jsonProduct.length(); j++) {
                            orderProperties.add(jsonProduct.get(j).toString());
                        }
                    }

                    ordersList.add(orderProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ordersList.size(); i++) {
            orders.add(getInfoOrder(Integer.parseInt(ordersList.get(i).get(0))));
        }
        return orders;
    }

    /**
     *  Returns an order when send an id
     * @param id Order id
     * @return order
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Order getInfoOrder(int id) {
        String query = "SELECT * FROM `order` WHERE id_order = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id", id);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);

        ArrayList<ArrayList<String>> ordersList = new ArrayList<>();
        JSONArray jsonProductsArray = null;
        try {
            jsonProductsArray = new JSONArray(result);
            if (jsonProductsArray != null) {
                for (int i = 0; i < jsonProductsArray.length(); i++) {

                    JSONArray jsonProduct = new JSONArray(jsonProductsArray.get(i).toString());
                    ArrayList<String> orderProperties = new ArrayList<>();

                    if (jsonProduct != null) {
                        for (int j = 0; j < jsonProduct.length(); j++) {
                            orderProperties.add(jsonProduct.get(j).toString());
                        }
                    }
                    Log.d("FML", "orderProperties: " + orderProperties);
                    ordersList.add(orderProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("FML", "getAllOrderClient: " + ordersList);
        ArrayList<Order> order = createOrders(ordersList);
        Log.d("FML", "getAllOrderClient: " + order.size());
        return order.get(0);
    }

    /**
     * Mofify an order
     * @param order Order containing the products, client, shipping method, etc to be modified
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void modifyOrder(Order order) {
        String query = "UPDATE `order` SET `tps` = :tps, `tvq` = :tvq, `total` = :total WHERE `order`.`id_order` = :id_order";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":tps", order.getTps());
        parameters.put(":tvq", order.getTvq());
        parameters.put(":total", order.getTotal());
        parameters.put(":id_order", order.getId());

        JSONObject jsonParameters = new JSONObject(parameters);

        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_UPDATE);
        this.httpRequest.executeQuery(query, jsonParameters);
    }

    /**
     * Puts results from a select and transforms it into an ArrayList of orders
     * @param ordersList Result from a select
     * @return ArrayList of Order
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Order> createOrders(ArrayList<ArrayList<String>> ordersList) {
        ArrayList<Order> orders = new ArrayList<>();

        for (int i = 0; i < ordersList.size(); i++) {
            ArrayList<Product> products = getProductsFromOrder(Integer.parseInt(ordersList.get(i).get(0)));
            Order order = new Order(
                    Integer.parseInt(ordersList.get(i).get(0)), // id
                    Integer.parseInt(ordersList.get(i).get(3)), // state id
                    products, // products
                    Integer.parseInt(ordersList.get(i).get(2)), // id user
                    Integer.parseInt(ordersList.get(i).get(4)), // id shipping method
                    Double.parseDouble(ordersList.get(i).get(5)), //  tps
                    Double.parseDouble(ordersList.get(i).get(6)), //  tvq
                    ordersList.get(i).get(8),
                    getQuantitiesFromOrder(Integer.parseInt(ordersList.get(i).get(0)))
            );
            orders.add(order);
            Log.d("FML", "getAllOrderClient: " + orders.size());
        }

        return orders;
    }

    /**
     * Get all the quantities for an order
     * @param id_order id of the order
     * @return ArrayList of the quantities
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public  ArrayList<Integer> getQuantitiesFromOrder(int id_order) {
        ArrayList<Integer> quantities = new ArrayList<>();

        String query = "SELECT * FROM ta_order_product WHERE id_order = :id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id", id_order);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);


        ArrayList<ArrayList<String>> productList = new ArrayList<>();
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

                    productList.add(productProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < productList.size(); i++) {
            quantities.add(Integer.parseInt(productList.get(i).get(2)));
        }

        return quantities;
    }

    /**
     * Get all products from an order
     * @param id_order Id of the order
     * @return ArrayList of Product
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Product> getProductsFromOrder(int id_order) {
        ArrayList<Product> products = new ArrayList<>();

        String query = "SELECT * FROM ta_order_product WHERE id_order = :id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id", id_order);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
        String result = this.httpRequest.executeQuery(query, jsonParameters);


        ArrayList<ArrayList<String>> productList = new ArrayList<>();
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

                    productList.add(productProperties);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < productList.size(); i++) {
            mgrProduct.getProductById(Integer.parseInt(productList.get(i).get(1)));
            products.add((mgrProduct.getProducts().get(0)));
            mgrProduct.setProducts(new ArrayList<>());
        }

        return products;
    }

    public MgrProduct getMgrProduct() {
        return mgrProduct;
    }

    public void setMgrProduct(MgrProduct mgrProduct) {
        this.mgrProduct = mgrProduct;
    }
}
