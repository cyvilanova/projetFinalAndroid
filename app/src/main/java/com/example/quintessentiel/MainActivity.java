package com.example.quintessentiel;


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
    //Test code
    private Button btnTest;
    private MgrProduct mgrProduct = new MgrProduct();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        prefs = this.getSharedPreferences("UserPref", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();

        ConnectionActivity sa = new ConnectionActivity();
        Intent intent = new Intent(MainActivity.this,sa.getClass());
        MainActivity.this.startActivity(intent);
        //Test code
        /**
        btnTest = (Button) findViewById(R.id.btnTest);

        btnTest.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                testBd();
            }
        });


        CtrlUser ctrluser = new CtrlUser(this);
        ctrluser.checkCredentials("David","abc123");
        //ctrluser.createUser("hule","quebeccoisepic@gmail.com","abc123","abc123","gros roux",1);
        System.out.println("USER CONNECTED");


        prefs = getApplicationContext().getSharedPreferences("UserPref", 0); // 0 - for private mode

        System.out.println(prefs.getAll());



         **/



    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void testBd() {
        mgrProduct.getProductById(1);
        mgrProduct.insertProduct();
    }

}
/**






    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            btnTest.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    testBd();
                }
            });
        }



    }
**/