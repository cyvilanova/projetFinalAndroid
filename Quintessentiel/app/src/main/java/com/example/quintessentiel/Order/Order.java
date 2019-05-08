package com.example.quintessentiel.Order;

public class Order{
    private int id;
    private int idState;
    //private ArrayList<Product> products;
    private int idUser;
    private int idMethod;
    private double tps;
    private double tvq;

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
/*
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
*/
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
