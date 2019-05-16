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

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
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
                            //Clears the preferences
                            SharedPreferences prefs;
                            prefs = getApplicationContext().getSharedPreferences("UserPref", 0);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.clear().commit();

                            Intent intentLogout = new Intent(getApplicationContext(), ConnectionActivity.class);
                            startActivity(intentLogout);

                            notification();
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

    /**
     * Show a notification
     */
    public void notification(){
        int NOTIFICATION_ID = 1;
        String NOTIFICATION_CHANNEL_ID = "basic";



        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0, ConnectionActivity,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Quintessentiel", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription(getString(R.string.notificationDesc));
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(getString(R.string.notificationTitle))
                .setContentText(getString(R.string.notificationDesc));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }

        }, 5000); // 5000ms delay
    }
}
