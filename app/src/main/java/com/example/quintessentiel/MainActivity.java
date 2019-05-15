package com.example.quintessentiel;

import android.support.v7.app.AppCompatDelegate;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import com.example.quintessentiel.Product.MgrProduct;
import com.example.quintessentiel.User.CtrlUser;


public class MainActivity extends AppCompatActivity {



    private SharedPreferences prefs;
    private Button btnTest;
    private MgrProduct mgrProduct = new MgrProduct();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        //Clears the preferences
        prefs = this.getSharedPreferences("UserPref", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();

        //Launches the connection activity
        ConnectionActivity sa = new ConnectionActivity();
        Intent intent = new Intent(MainActivity.this,sa.getClass());
        MainActivity.this.startActivity(intent);

    }

}