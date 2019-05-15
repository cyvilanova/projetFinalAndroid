
package com.example.quintessentiel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quintessentiel.Order.CtrlOrder;
import com.example.quintessentiel.Order.MgrOrder;
import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.MgrProduct;

public class MainActivity extends AppCompatActivity {

    Button btnTest;
    MgrProduct mgrProduct = new MgrProduct();



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
        Intent intent = new Intent(this, Cart.class);
        intent.putExtra("id_client", 1);

        mgrProduct.getProductById(1);
        mgrProduct.getProductById(2);
        MgrOrder mgrOrder = new MgrOrder();

        Order o = mgrOrder.getInfoOrder(1);


        intent.putExtra("order", o);
        startActivity(intent);
    }
}
