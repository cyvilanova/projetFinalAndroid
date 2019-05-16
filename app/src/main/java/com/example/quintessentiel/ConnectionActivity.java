/****************************************
 Fichier : ConnectionActivity.java
 Auteure : David Gaulin
 Fonctionnalité : M1 - Login
 Date : 2019-05-08
 Vérification :
 Date Nom Approuvé
 =========================================================
 Historique de modifications :
 Date Nom Description
 =========================================================
 ****************************************/
package com.example.quintessentiel;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.quintessentiel.User.CtrlUser;

public class ConnectionActivity extends BaseActivity {

    private CtrlUser ctrlUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        super.onCreateDrawer(true);

        ImageView facebookLink = findViewById(R.id.facebook);
        //Hide the side bar menu
        FrameLayout btnSideMenu = findViewById(R.id.drawer_button);
        btnSideMenu.setVisibility(View.INVISIBLE);

        this.ctrlUser = new CtrlUser(this);

        if(this.ctrlUser.checkIfConnected()){   //If user is already connected
            loadCatalogPage();
        }
        else{   //If user is not connected

            //On connection button click
            findViewById(R.id.btnConnection).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //Loads every fields & their values
                    EditText usernameField = findViewById(R.id.txtUsername);
                    EditText passwordField = findViewById(R.id.txtPassword);

                    String usernameVal = usernameField.getText().toString();
                    String passwordVal = passwordField.getText().toString();


                    if(usernameVal.equals("") || passwordVal.equals("")){   //If fields are empty
                        Toast.makeText(getBaseContext(), "Veuillez remplir les champs correctement", Toast.LENGTH_LONG).show();
                    }
                    else{   //Fields are all filled

                        if(ctrlUser.checkCredentials(usernameVal,passwordVal)){
                            final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 500);
                            tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                        if(ctrlUser.checkCredentials(usernameVal,passwordVal)){ //Connection successfull
                            ConnectionActivity.super.setUserName(usernameVal);
                            loadCatalogPage();
                        }
                        else{   //Wrong infos given
                            Toast.makeText(getBaseContext(), "Mauvais renseignements", Toast.LENGTH_LONG).show();
                        }


                    }

                }
            });

            //On the to sign up button click
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
        Intent myIntent = new Intent(getApplicationContext(), CatalogActivity.class);
        startActivity(myIntent);
    }

    /**
     * Brings the user to the connection page
     */
    public void loadSignUpPage(){
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
