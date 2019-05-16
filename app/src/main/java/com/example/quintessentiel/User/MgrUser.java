/****************************************
 Fichier : MgrUser.java
 Auteure : David Gaulin
 Fonctionnalité : M1 - Authentification && M2-Enregistrement
 Date : 2019-05-08
 Vérification :
 Date Nom Approuvé
 =========================================================
 Historique de modifications :
 Date Nom Description
 =========================================================
 ****************************************/

package com.example.quintessentiel.User;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quintessentiel.Constants;
import com.example.quintessentiel.HttpPostRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MgrUser {
    private User user;
    private HttpPostRequest httpRequest;
    private Context ctx;

    public MgrUser(Context ctx){
        this.user = new User();
        this.httpRequest = new HttpPostRequest();
        this.ctx = ctx;
    }

    /**
     * Adds a user into the database
     * @param user object to add
     */
    public boolean addUser(User user){

        if(!userExists(user.getName())){

            String query = "INSERT INTO User VALUES (DEFAULT,:id_question,:username,:password,:email,:secret_answer)";

            Map<String, Object> parameters = new HashMap<>();

            parameters.put(":id_question", user.getIdSecretQuestion());
            parameters.put(":username", user.getName());
            parameters.put(":password", user.getPassword());
            parameters.put(":email", user.getEmail());
            parameters.put(":secret_answer", user.getSecretResponse());

            JSONObject jsonParameters = new JSONObject(parameters);

            this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);
            this.httpRequest.executeQuery(query,jsonParameters);

            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Takes a username an checks if it exists in the database
     * @param name name of the user to check if exists
     * @return a bool telling if it exists or not
     */
    private boolean userExists(String name){
        String query = "SELECT id_user FROM User WHERE username = :name";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":name", name);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);

        String result = this.httpRequest.executeQuery(query,jsonParameters);


        try{
            JSONArray jsonUserArray = new JSONArray(result);


            if(jsonUserArray.length() > 0){ //User exists
                return true;
            }
            else{   //User doesn't exist
                return false;
            }

        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Checks if the given password and name match
     * a user in the db
     * @param name  of the user to check for
     * @param password of the user to check for
     * @return the user's id in the db or 0 if not found
     */
    public boolean checkCredentials(String name,String password){
        String query = "SELECT id_user,username FROM User WHERE username = :username AND password = :password";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(":username", name);
        parameters.put(":password", password);

        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);

        String result = this.httpRequest.executeQuery(query,jsonParameters);

        try{
            JSONArray jsonUserArray = new JSONArray(result);


            if(jsonUserArray.length() > 0){ //User exists

                JSONArray jsonUser = new JSONArray(jsonUserArray.get(0).toString());
                Integer userId = jsonUser.getInt(0);
                String userName = jsonUser.getString(1);

                //sets the user's infos
                this.user.setIdUser(userId);
                this.user.setName(userName);


                SharedPreferences prefs = this.ctx.getSharedPreferences("UserPref", 0);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("username",userName);
                editor.putInt("userId",userId);

                editor.commit();


                return true;
            }
            else{
                return false;
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Loads all the security questions from the DB
     * @return an ArrayList of questions (Id,Question)
     */
    public ArrayList<Question> getSecurityQuestions(){

        String query = "SELECT * FROM secret_question";
        Map<String, Object> parameters = new HashMap<>();
        JSONObject jsonParameters = new JSONObject(parameters);
        this.httpRequest.setHttpUrlConnection(Constants.HTTP_URL_CONNECTION_SELECT);

        String result = this.httpRequest.executeQuery(query,jsonParameters);
        ArrayList<Question> questionList = new ArrayList<>();

        try{
            JSONArray jsonQuestionArray = new JSONArray(result);

            for(int i = 0;i < jsonQuestionArray.length();i++)
            {
                JSONArray jsonUser = new JSONArray(jsonQuestionArray.get(i).toString());

                Integer idQuestion = jsonUser.getInt(0);
                String questionStr = jsonUser.getString(1);

                Question question = new Question(idQuestion,questionStr);
                questionList.add(question);
            }

        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return questionList;
    }

    /**
     * Checks if the user is currently connected
     * @return a boolean telling whether or not the user is connected
     */
    public boolean checkIfConnected(){
        SharedPreferences prefs;
        prefs = this.ctx.getSharedPreferences("UserPref", 0); // 0 - for private mode

        if(prefs.contains("username") && prefs.contains("userId")){
            return true;
        }

        return false;
    }

}
