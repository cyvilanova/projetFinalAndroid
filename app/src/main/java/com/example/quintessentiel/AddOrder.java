package com.example.quintessentiel;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;



public class AddOrder extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_order);
        super.onCreateDrawer(true);



    }
}