package com.example.quintessentiel.Form;
/**
 * Fichier: Form.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Object containing the information of a form
 * Date : 15-5-2019
 *
 * Vérification :
 * Date               Nom                   Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 15-5-2019          CV      Création
 */
public class Form {
    private Integer idUser; // The id of the user connected
    private Integer age; // The age of the user connected
    private String skintype; // The skintype of the user connected
    private String effect; // The effect of the product desired
    private String productType; // The type of the product desired
    private String workEnvironment; // The work environment of the user
    private Integer quantity; // The quantity of the product
    private String fragrance; // The fragrance of the product desired
    private String moreInfos; // More informations on the desired product

    /**
     * Constructor
     * @param idUser
     * @param age
     * @param skintype
     * @param effect
     * @param productType
     * @param workEnvironment
     * @param quantity
     * @param fragrance
     * @param moreInfos
     */
    public Form(Integer idUser, Integer age, String skintype, String effect, String productType, String workEnvironment, Integer quantity, String fragrance, String moreInfos) {
        this.idUser = idUser;
        this.age = age;
        this.skintype = skintype;
        this.effect = effect;
        this.productType = productType;
        this.workEnvironment = workEnvironment;
        this.quantity = quantity;
        this.fragrance = fragrance;
        this.moreInfos = moreInfos;
    }

    /**
     * @return the id of the user
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @return the age of the user
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @return the skintype of the user
     */
    public String getSkintype() {
        return skintype;
    }

    /**
     * @return the effect desired
     */
    public String getEffect() {
        return effect;
    }

    /**
     * @return the type of product desired
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @return  the work environment of the user
     */
    public String getWorkEnvironment() {
        return workEnvironment;
    }

    /**
     * @return the quantity of product desired
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @return the fragrance of the product desired
     */
    public String getFragrance() {
        return fragrance;
    }

    /**
     * @return more informations on the product desired
     */
    public String getMoreInfos() {
        return moreInfos;
    }
}
