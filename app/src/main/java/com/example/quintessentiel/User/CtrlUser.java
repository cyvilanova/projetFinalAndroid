/****************************************
 Fichier : CtrlUser.java
 Auteure : David Gaulin
 Fonctionnalité : A6 - Login
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

import java.util.ArrayList;

public class CtrlUser {

    private MgrUser mgrUser;

    public CtrlUser(Context ctx){
        this.setMgrUser(new MgrUser(ctx));
    }

    /**
     * Creates a user with the given infos and calls the manager to add it to the db
     * @param name
     * @param email
     * @param password
     * @param secretResponse
     * @param idSecretQuestion
     */
    public boolean createUser(String name, String email,String password,String secretResponse,Integer idSecretQuestion){

        User newUser = new User(name,email,password,secretResponse,idSecretQuestion);

        return this.mgrUser.addUser(newUser);
    }

    /**
     * Checks if the user is already connected
     * in the db
     */
    public boolean checkIfConnected(){
        return this.mgrUser.checkIfConnected();
    }



    /**
     * Checks if the given credentials match a user
     * in the db
     * @param name
     * @param password
     */
    public boolean checkCredentials(String name, String password){
        return this.getMgrUser().checkCredentials(name,password);
    }

    /**
     * Gets the security questions from the db
     * @return the security questions in a list
     */
    public ArrayList<Question> getSecurityQuestions(){
        return this.getMgrUser().getSecurityQuestions();
    }


    /**
     * Gets the MgrUser
     * @return MgrUser
     */
    public MgrUser getMgrUser() {
        return mgrUser;
    }

    /**
     * Sets the MgrUser
     * @param mgrUser
     */
    public void setMgrUser(MgrUser mgrUser) {
        this.mgrUser = mgrUser;
    }
}
