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

package com.example.quintessentiel;

public class CtrlUser {

    private MgrUser mgrUser;

    public CtrlUser(){
        this.setMgrUser(new MgrUser());
    }

    /**
     * Creates a user with the given infos and calls the manager to add it to the db
     * @param name
     * @param phoneNumber
     * @param email
     * @param password
     * @param secretResponse
     * @param idSecretQuestion
     */
    public void createUser(String name,Integer phoneNumber, String email,String password,String secretResponse,Integer idSecretQuestion){
        User newUser = new User(name,phoneNumber,email,password,secretResponse,idSecretQuestion);
        this.getMgrUser().addUser(newUser);
    }

    /**
     * Checks if the given credentials match a user
     * in the db
     * @param name
     * @param password
     */
    public void checkCredentials(String name,String password){
        Integer userId = 0;

        userId = this.getMgrUser().checkCredentials(name,password);

        if(userId.equals(0)){
            //Invalid user credentials
        }
        else{
            //User is valid
        }
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
