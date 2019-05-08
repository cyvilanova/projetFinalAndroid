/****************************************
 Fichier : MgrUser.java
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

package com.example.quintessentiel;

public class MgrUser {
    private User user;

    public MgrUser(){
        this.user = new User();
    }

    /**
     * Adds a user into the database
     * @param user
     */
    public void addUser(User user){
        //queryEngine
        //query
        //parameters
        //execute
        //return status code
    }

    /**
     * Takes a username an checks if it exists in the database
     * @param name
     * @return a bool telling if it exists or not
     */
    private boolean checkIfExists(String name){
        //queryEngine
        //query
        //parameters
        //execute
        //return true or false
        return true;
    }

    /**
     * Checks if the given password and name match
     * a user in the db
     * @param name
     * @param password
     * @return the user's id in the db or 0 if not found
     */
    public Integer checkCredentials(String name,String password){
        //query engine
        //query
        //parameters
        //execute
        //Sets the user's id with the loaded one
        //return bool telling if the user exists or not
        return 0;
    }
}
