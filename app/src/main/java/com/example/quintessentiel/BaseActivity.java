package com.example.quintessentiel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.quintessentiel.User.MgrUser;
import com.example.quintessentiel.User.User;

import java.sql.SQLOutput;


public class BaseActivity extends AppCompatActivity
{

    private static String userName;

    protected void onCreateDrawer(boolean canOpen){

        if(canOpen){



            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);
            FrameLayout db = (FrameLayout) findViewById(R.id.drawer_button);

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



            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {


                    switch(menuItem.toString()){
                        case "Catalogue":
                                //Open page here
                            break;
                        case "Mon panier":
                            //Open page here
                            break;
                        case "Recette personalisée":
                            //Open page here
                            break;
                        case "Notifications":
                            //Open page here
                            break;
                        case "Déconnexion":
                            //Open page here
                            break;

                    }
                    return false;
                }
            });

        }
        else{
            //Toast here to explain why you can't open it
            //a str could be passed to onCreateDrawer if necessary in order to
            //create custom messages
            //ex: can't to this while not connected
            //ex2: can't do .....
        }

    }

    public void setDisplayUserName(){
        TextView lblName = (TextView) findViewById(R.id.nav_header_textView);

        String content;

        if(userName != null){
            content = getString(R.string.welcomeName,this.userName);
        }
        else{
            content = getString(R.string.notConnectedWelcome);
        }

        lblName.setText(content);
    }

    public void setUserName(String username){
        this.userName = username;
    }
}

