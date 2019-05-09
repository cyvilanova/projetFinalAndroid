/****************************************
 Fichier: Order.java
 Auteur: Catherine Bronsard
 Fonctionnalité: Commande
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

import com.example.quintessentiel.Product.*;

import java.util.ArrayList;

public class Order{
    private int id;
    private int idState;
    private ArrayList<Product> products;
    private int idUser;
    private int idMethod;
    private double tps;
    private double tvq;

    public Order(int id, int idState, ArrayList<Product> products, int idUser, int idMethod, double tps, double tvq) {
        this.id = id;
        this.idState = idState;
        this.products = products;
        this.idUser = idUser;
        this.idMethod = idMethod;
        this.tps = tps;
        this.tvq = tvq;
    }

    public Order(int idState, ArrayList<Product> products, int idUser, int idMethod, double tps, double tvq) {
        this.idState = idState;
        this.products = products;
        this.idUser = idUser;
        this.idMethod = idMethod;
        this.tps = tps;
        this.tvq = tvq;
    }

    /* Getteurs/Setteurs */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(int idMethod) {
        this.idMethod = idMethod;
    }

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public double getTvq() {
        return tvq;
    }

    public void setTvq(double tvq) {
        this.tvq = tvq;
    }
}
