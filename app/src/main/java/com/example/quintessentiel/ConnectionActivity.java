package com.example.quintessentiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quintessentiel.User.CtrlUser;

public class ConnectionActivity extends BaseActivity {

    private CtrlUser ctrlUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        super.onCreateDrawer(true);

        this.ctrlUser = new CtrlUser(this);

        if(this.ctrlUser.checkIfConnected()){   //If user is already connected
            loadCatalogPage();
        }
        else{   //If user is not connected yet
            //On connection button click

            findViewById(R.id.btnConnection).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    EditText usernameField = findViewById(R.id.txtUsername);
                    EditText passwordField = findViewById(R.id.txtPassword);

                    String usernameVal = usernameField.getText().toString();
                    String passwordVal = passwordField.getText().toString();


                    if(usernameVal.equals("") || passwordVal.equals("")){   //If fields are empty
                        System.out.println("please enter something");
                    }
                    else{

                        if(ctrlUser.checkCredentials(usernameVal,passwordVal)){
                            System.out.println("CONNECTED");
                            ConnectionActivity.super.setUserName(usernameVal);
                        }
                        else{
                            System.out.println("ERROR, CAN'T CONNECT");
                        }

                        System.out.println("Done");
                    }

                }
            });

            findViewById(R.id.btnToSignUp).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadSignUpPage();
                }
            });
        }


        findViewById(R.id.toolBarLeftBlockImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /**
     * Brings the user to the catalog page once he's connected
     */
    public void loadCatalogPage(){
        System.out.println("load catalog");
    }

    /**
     * Brings the user to the connection page
     */
    public void loadSignUpPage(){
        System.out.println("LOADING....");
        SignUpActivity sa = new SignUpActivity();

        Intent intent = new Intent(ConnectionActivity.this,sa.getClass());
        ConnectionActivity.this.startActivity(intent);
    }



}
