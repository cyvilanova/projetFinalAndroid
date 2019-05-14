package com.example.quintessentiel.Form;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.quintessentiel.Constants;
import com.example.quintessentiel.HttpPostRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Fichier: MgrForm.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Class accessing the table form in the database
 * Date : 14-5-2019
 *
 * Vérification :
 * Date               Nom     Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 14-5-2019           CV      Création
 */
public class MgrForm {
    private HttpPostRequest httpRequest;

    /**
     * Constructor
     */
    public MgrForm() {
        this.httpRequest = new HttpPostRequest();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertForm() {
        String query = "INSERT INTO form(name, description, price, quantity, is_sellable) VALUES(:name, :description, :price, :quantity, 1)";
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
