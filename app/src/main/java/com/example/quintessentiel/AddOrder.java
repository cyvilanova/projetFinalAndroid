package com.example.quintessentiel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quintessentiel.Order.CtrlOrder;
import com.example.quintessentiel.Order.Order;


public class AddOrder extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_order);
        super.onCreateDrawer(true);

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("order");

        TextView tps = findViewById(R.id.tps);
        tps.setText(String.format("TPS : %.2f", order.getTps()));

        TextView tvq = findViewById(R.id.tvq);
        tvq.setText(String.format("TPS : %.2f", order.getTvq()));

        TextView total = findViewById(R.id.total);
        total.setText(String.format("TPS : %.2f", order.getTotal()));

        Button btn = findViewById(R.id.command);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CtrlOrder ctrlOrder = new CtrlOrder();
                ctrlOrder.addOrder(order);
                finish();
            }
        });
    }
}