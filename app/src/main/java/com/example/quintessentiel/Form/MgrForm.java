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
 * <p>
 * Vérification :
 * Date               Nom     Approuvé
 * <p>
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
    public void insertForm(HashMap<String, String> formData) {

        Form newForm = createObjectForm(formData);

        String query = "INSERT INTO form_answers(id_user, age, skintype, product_type, work_environment, desired_effect, quantity, fragrance, more_infos) " +
                "VALUES(:id_user, :age, :skintype, :product_type, :work_environment, :desired_effect, :quantity, :fragrance, :more_infos)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":id_user", newForm.getIdUser());
        parameters.put(":age", newForm.getAge());
        parameters.put(":skintype", newForm.getSkintype());
        parameters.put(":product_type", newForm.getProductType());
        parameters.put(":work_environment", newForm.getWorkEnvironment());
        parameters.put(":desired_effect", newForm.getEffect());
        parameters.put(":quantity", newForm.getQuantity());
        parameters.put(":fragrance", newForm.getFragrance());
        parameters.put(":more_infos", newForm.getMoreInfos());

        JSONObject jsonParameters = new JSONObject(parameters);

        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_UPDATE);
        this.httpRequest.executeQuery(query, jsonParameters);
    }

    /**
     * Creates an object Form with the right parameters.
     * @param formData The data of the new form to create
     * @return the new object Form
     */
    public Form createObjectForm(HashMap<String, String> formData) {

        Integer idUser = Integer.parseInt(formData.get("idClient"));
        Integer age = Integer.parseInt(formData.get("age"));
        Integer quantity = Integer.parseInt(formData.get("quantity"));
        String skintype = formData.get("skintype");
        String effect = formData.get("effect");
        String productType = formData.get("productType");
        String fragrance = formData.get("fragrance");
        String workEnvironment = formData.get("workEnvironment");
        String moreInfos = formData.get("moreInfos");

        Form newForm = new Form(idUser, age, skintype, effect, productType, workEnvironment, quantity, fragrance, moreInfos);
        return newForm;
    }
}
