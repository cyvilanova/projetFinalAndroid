package com.example.quintessentiel.Order;

import java.util.ArrayList;

public class CtrlOrder {
    private MgrOrder mgrOrder;
    //private MgrProduct mgrProduct;

    /**
     * Returns all the orders in the database
     * @return ArrayList of all orders
     */
    public ArrayList<Order> getAllOrders() {
        return new ArrayList<Order>();
    }

    /**
     * Add an order to the database
     * @param order
     */
    public void addOrder(Order order) {

    }

    public void editOrder(Order order) {

    }

    public void deleteOrder(Order order) {

    }

    /* Getteurs setteurs */
    public MgrOrder getMgrOrder() {
        return mgrOrder;
    }

    public void setMgrOrder(MgrOrder mgrOrder) {
        this.mgrOrder = mgrOrder;
    }
/*
    public MgrProduct getMgrProduct() {
        return mgrProduct;
    }

    public void setMgrProduct(MgrProduct mgrProduct) {
        this.mgrProduct = mgrProduct;
    }
    */
}
