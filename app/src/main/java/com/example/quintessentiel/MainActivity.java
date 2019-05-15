package com.example.quintessentiel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quintessentiel.Product.MgrProduct;


public class MainActivity extends AppCompatActivity {

    Button btnTest;
    MgrProduct mgrProduct = new MgrProduct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = new Intent(MainActivity.this, CatalogActivity.class);
        MainActivity.this.startActivity(myIntent);
        }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void testBd() {
        mgrProduct.getProductById(1);
        mgrProduct.insertProduct();
    }

}
