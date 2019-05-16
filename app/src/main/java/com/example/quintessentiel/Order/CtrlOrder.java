/****************************************
 Fichier: CtrlOrder.java
 Auteur: Catherine Bronsard
 Fonctionnalité: Contrôleur des commandes
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

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.quintessentiel.Product.*;

import java.util.ArrayList;

public class CtrlOrder {
    private MgrOrder mgrOrder;
    private MgrProduct mgrProduct;


    public CtrlOrder(Context context) {
        mgrOrder = new MgrOrder(context);
        mgrProduct = new MgrProduct(context);
    }

    /**
     * Returns all the orders in the database
     * @return ArrayList of all orders
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Order> getAllOrdersClient(int id_client) {
        return mgrOrder.getAllOrderClient(id_client);
    }

    /**
     * Add an order to the database
     * @param order
     */
    public void addOrder(Order order) {
        mgrOrder.insertOrder(order);
    }

    /**
     *
     * @param order
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void editOrder(Order order) {
        mgrOrder.modifyOrder(order);
    }

    /* Getteurs setteurs */
    public MgrOrder getMgrOrder() {
        return mgrOrder;
    }

    public void setMgrOrder(MgrOrder mgrOrder) {
        this.mgrOrder = mgrOrder;
    }

    public MgrProduct getMgrProduct() {
        return mgrProduct;
    }

    public void setMgrProduct(MgrProduct mgrProduct) {
        this.mgrProduct = mgrProduct;
    }
}
