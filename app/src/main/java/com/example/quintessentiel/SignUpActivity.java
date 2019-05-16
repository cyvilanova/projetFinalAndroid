/****************************************
 Fichier : SignUpActivity.java
 Auteure : David Gaulin
 Fonctionnalité : M2 - Enregistrement
 Date : 2019-05-08
 Vérification :
 Date Nom Approuvé
 =========================================================
 Historique de modifications :
 Date Nom Description
 =========================================================
 ****************************************/
package com.example.quintessentiel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.quintessentiel.User.CtrlUser;
import com.example.quintessentiel.User.Question;
import java.util.ArrayList;

public class SignUpActivity extends BaseActivity {

    private CtrlUser ctrlUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        super.onCreateDrawer(true);


        //Hide the side bar menu
        FrameLayout btnSideMenu = findViewById(R.id.drawer_button);
        btnSideMenu.setVisibility(View.INVISIBLE);

        this.ctrlUser = new CtrlUser(this);

        populateSecurityQuestions();

        //On confirm click
        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //All fields & their values
                EditText usernameField = findViewById(R.id.txtSignUpUsername);
                EditText emailField = findViewById(R.id.txtSignUpEmail);
                EditText passwordField = findViewById(R.id.txtSignUpPass);
                EditText confirmPasswordField = findViewById(R.id.txtSignUpPassConfirm);
                Spinner securityQuestion = findViewById(R.id.spinnerSignUpQuestion);
                EditText securityAnswer = findViewById(R.id.txtSignUpResponse);

                String usernameVal = usernameField.getText().toString();
                String emailVal = emailField.getText().toString();
                String passwordVal = passwordField.getText().toString();
                String confirmPasswordVal = confirmPasswordField.getText().toString();

                Integer securityQuestionVal = ((Question) securityQuestion.getSelectedItem()).getIdQuestion();
                String securityAnswerVal = securityAnswer.getText().toString();


                //If empty field(s)
                if(usernameVal.equals("") || emailVal.equals("") || passwordVal.equals("") || confirmPasswordVal.equals("") || securityAnswerVal.equals("") || securityQuestionVal.equals("")){
                    Toast.makeText(getBaseContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                }
                else{   //If every fields are filled

                    if(passwordVal.equals(confirmPasswordVal)){ //If passwords match

                        if(ctrlUser.createUser(usernameVal,emailVal,passwordVal,securityAnswerVal,securityQuestionVal)) //If we can create the user
                        {
                            loadConnectionPage();
                        }
                        else{   //This username is already in use
                            Toast.makeText(getBaseContext(), "Nom d'utilisateur déjà utilisé", Toast.LENGTH_LONG).show();
                        }


                    }
                    else{   //Passwords not matching
                        Toast.makeText(getBaseContext(), "Les mots de passes ne concordent pas", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        //On to connection button click
        findViewById(R.id.btnToConnection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadConnectionPage();
            }
        });

    }

    /**
     * Loads all the security questions into the spinner
     */
    public void populateSecurityQuestions(){
        Spinner spinnerSecurityQuestion = findViewById(R.id.spinnerSignUpQuestion);
        ArrayList<Question> arraySecurityQuestion = this.ctrlUser.getSecurityQuestions();   //Loads the questions from db

        ArrayAdapter<Question> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySecurityQuestion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSecurityQuestion.setAdapter(adapter);
    }

    /**
     * Changes to the Connection activity
     */
    public void loadConnectionPage(){
        ConnectionActivity ca = new ConnectionActivity();

        Intent intent = new Intent(SignUpActivity.this, ca.getClass());
        SignUpActivity.this.startActivity(intent);
    }
}
