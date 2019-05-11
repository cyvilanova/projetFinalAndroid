/****************************************
 Fichier : User.java
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

public class User {
    private Integer IdUser;
    private String name;
    private Integer phoneNumber;
    private String email;
    private String password;
    private String secretResponse;
    private Integer idSecretQuestion;


    public User(String name,Integer phoneNumber, String email,String password,String secretResponse,Integer idSecretQuestion){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.secretResponse = secretResponse;
        this.idSecretQuestion = idSecretQuestion;
    }

    public User(){}
    /**
     * Gets the id of the user
     * @return Id of the user
     */
    public Integer getIdUser() {
        return IdUser;
    }

    /**
     * Sets the id of the user
     * @param idUser
     */
    public void setIdUser(Integer idUser) {
        IdUser = idUser;
    }

    /**
     * Gets the user's name
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's phone number
     * @return the user's phone number
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user's email address
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the users password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's secret Response
     * @return the user's secret Response
     */
    public String getSecretResponse() {
        return secretResponse;
    }

    /**
     * Sets the user's secret Response
     * @param secretResponse
     */
    public void setSecretResponse(String secretResponse) {
        this.secretResponse = secretResponse;
    }

    /**
     * Gets the Id of the user's secret question
     * @return the Id of the user's secret question
     */
    public Integer getIdSecretQuestion() {
        return idSecretQuestion;
    }

    /**
     * Sets the id of the user's secret question
     * @param idSecretQuestion
     */
    public void setIdSecretQuestion(Integer idSecretQuestion) {
        this.idSecretQuestion = idSecretQuestion;
    }
}
