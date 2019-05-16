
package com.example.quintessentiel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.content.SharedPreferences;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quintessentiel.Product.MgrProduct;

public class MainActivity extends AppCompatActivity {



    private SharedPreferences prefs;
    private Button btnTest;
    private MgrProduct mgrProduct = new MgrProduct();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                testBd();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void testBd() {

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
