package com.example.quintessentiel;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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


        ctrlUser = new CtrlUser(this);
        populateSecurityQuestions();

        findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


                if (usernameVal.equals("") || emailVal.equals("") || passwordVal.equals("") || confirmPasswordVal.equals("") || securityAnswerVal.equals("") || securityQuestionVal.equals("")) {

                    //Empty field(s)
                    System.out.println("empty field(s)");
                } else {

                    if (passwordVal.equals(confirmPasswordVal)) {

                        if (ctrlUser.createUser(usernameVal, emailVal, passwordVal, securityAnswerVal, securityQuestionVal)) {
                            loadConnectionPage();
                        } else {
                            System.out.println("didnt work");
                        }


                    } else {   //Passwords not matching
                        System.out.println("Not matching!");
                    }
                }
            }
        });

        findViewById(R.id.btnToConnection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadConnectionPage();
            }
        });

        findViewById(R.id.toolBarLeftBlockImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void populateSecurityQuestions() {
        Spinner spinnerSecurityQuestion = findViewById(R.id.spinnerSignUpQuestion);
        ArrayList<Question> arraySecurityQuestion = this.ctrlUser.getSecurityQuestions();

        ArrayAdapter<Question> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySecurityQuestion);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSecurityQuestion.setAdapter(adapter);
    }

    public void loadConnectionPage() {
        ConnectionActivity ca = new ConnectionActivity();

        Intent intent = new Intent(SignUpActivity.this, ca.getClass());
        SignUpActivity.this.startActivity(intent);
    }
}
