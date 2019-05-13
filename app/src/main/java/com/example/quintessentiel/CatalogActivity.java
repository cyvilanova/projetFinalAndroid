package com.example.quintessentiel;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quintessentiel.Product.MgrProduct;


public class CatalogActivity extends AppCompatActivity {

    MgrProduct mgrProduct = new MgrProduct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void testBd() {
        mgrProduct.getProductById(1);
        mgrProduct.insertProduct();
    }

}