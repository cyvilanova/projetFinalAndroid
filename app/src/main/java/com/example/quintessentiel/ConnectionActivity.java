package com.example.quintessentiel;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.quintessentiel.User.CtrlUser;

public class ConnectionActivity extends BaseActivity {

    private CtrlUser ctrlUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        super.onCreateDrawer(true);

        ImageView facebookLink = findViewById(R.id.facebook);
        FrameLayout btnSideMenu = findViewById(R.id.drawer_button);
        btnSideMenu.setVisibility(View.INVISIBLE);

        this.ctrlUser = new CtrlUser(this);

        if(this.ctrlUser.checkIfConnected()){   //If user is already connected
            loadCatalogPage();
        }
        else{   //If user is not connected
            // yet
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
                            ConnectionActivity.super.setUserName(usernameVal);
                            Intent myIntent = new Intent(getApplicationContext(), CatalogActivity.class);
                            startActivity(myIntent);
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

        facebookLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebook("316220211894882");
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

    /**
     *  Brings the user to the Quintessential Facebook page
     * @param pageId id of the facebook page to load
     */
    public void goToFacebook(String pageId){
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+pageId));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+pageId));
            startActivity(intent);
        }
    }



}
