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
 ****************************************/
package com.example.quintessentiel.Order;

import com.example.quintessentiel.Product.MgrProduct;
import com.example.quintessentiel.Product.Product;

import java.util.ArrayList;

public class MgrOrder {
    private MgrProduct mgrProduct;

    /**
     * Insert a new order
     * @param order Order containing the products, client, shipping method, etc
     */
    public void insertOrder(Order order) {

    }


    /**
     *  Returns an order when send an id
     * @param id Order id
     * @return order
     */
    public Order getInfoOrder(int id) {
        Order order = new Order(1, new ArrayList<Product>(), 1, 1, 1.00, 1.00);

        return order;

    }

    /**
     * Mofify an order
     * @param order Order containing the products, client, shipping method, etc to be modified
     */
    public void modifyOrder(Order order) {

    }




    public MgrProduct getMgrProduct() {
        return mgrProduct;
    }

    public void setMgrProduct(MgrProduct mgrProduct) {
        this.mgrProduct = mgrProduct;
    }

}
