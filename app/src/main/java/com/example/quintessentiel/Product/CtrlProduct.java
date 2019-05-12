package com.example.quintessentiel.Product;

/**
 * Fichier: CtrlProduct.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Class formatting the data of the product table received from the manager
 * and displays or gets the informations on/of the UI.
 * Date : 8-5-2019
 *
 * Vérification :
 * Date              Nom     Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 8-5-2019           CV      Création
*/
public class CtrlProduct {
    private MgrProduct mgrProduct; // The manager of the product

    /**
     * Constructor
     */
    public CtrlProduct() {
        this.mgrProduct = new MgrProduct();
    }
}
