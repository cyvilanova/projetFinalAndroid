package com.example.quintessentiel;

import android.support.v7.app.AppCompatDelegate;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        //Clears the preferences
        prefs = this.getSharedPreferences("UserPref", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();

        //Launches the connection activity
        ConnectionActivity sa = new ConnectionActivity();
        Intent intent = new Intent(MainActivity.this, sa.getClass());
        MainActivity.this.startActivity(intent);

    }
}