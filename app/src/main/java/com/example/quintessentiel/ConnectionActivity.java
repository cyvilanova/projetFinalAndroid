package com.example.quintessentiel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.quintessentiel.User.CtrlUser;

public class ConnectionActivity extends AppCompatActivity {

    private CtrlUser ctrlUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

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

        //Side bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.drawer_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open right drawer
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.END);


                View view = getCurrentFocus();

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                view.clearFocus(); //The parent of this elements needs to be focusable
            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                System.out.println(menuItem);
                return false;
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
        SignUpActivity ca = new SignUpActivity();

        Intent intent = new Intent(ConnectionActivity.this,ca.getClass());
        ConnectionActivity.this.startActivity(intent);
    }



}
