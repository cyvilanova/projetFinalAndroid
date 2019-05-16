/****************************************
 Fichier : BaseActivity.java
 Auteure : David Gaulin
 Fonctionnalité : Side bar & toolbar
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
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class BaseActivity extends AppCompatActivity {

    private static String userName;


    protected void onCreateDrawer(boolean canOpen){

        //Sets the back arrow on the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(canOpen){    //If the activity can open the menu

            FrameLayout db = (FrameLayout) findViewById(R.id.drawer_button);

            //Clicks on the menu buttons
            db.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    setDisplayUserName();
                    // open right drawer
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.openDrawer(GravityCompat.END);
                    View view = getCurrentFocus();
                }

            });


            //Listener on the menu items in order to change page
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                @RequiresApi(api = Build.VERSION_CODES.N)
                public boolean onNavigationItemSelected(MenuItem menuItem) {

                    switch (menuItem.toString()) {
                        case "Catalogue":
                            Intent myIntent = new Intent(getApplicationContext(), CatalogActivity.class);
                            startActivity(myIntent);
                            break;
                        case "Mon panier":
                            Intent intent = getIntent();
                            Intent topass = new Intent(getApplicationContext(), Cart.class);

                            topass.putExtra("order", intent.getSerializableExtra("order"));
                            startActivity(topass);
                            break;
                        case "Recette personalisée":
                            Intent intentForm = new Intent(getApplicationContext(), FormActivity.class);
                            startActivity(intentForm);
                            break;
                        case "Notifications":
                            //Open page here
                            break;
                        case "Déconnexion":
                            //Open page here
                            break;
                        case "Mes commandes":
                            Intent myOrders = new Intent(getApplicationContext(), MyOrders.class);
                            startActivity(myOrders);
                            break;

                    }
                    return false;
                }
            });

        }
        else{
            Toast.makeText(getBaseContext(), "Impossible d'ouvrir le menu ici.", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Sets the username to display on the side bar
     */
    public void setDisplayUserName(){
        TextView lblName = (TextView) findViewById(R.id.nav_header_textView);

        String content;

        if(userName != null){   //If the user is connected
            content = getString(R.string.welcomeName,this.userName);
        }
        else{   //If the user isn't connected
            content = getString(R.string.notConnectedWelcome);
        }

        lblName.setText(content);
    }

    /**
     * Sets the username property
     * @param username
     */
    public void setUserName(String username){
        this.userName = username;
    }
}
