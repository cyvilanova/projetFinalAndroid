package com.example.quintessentiel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


import com.example.quintessentiel.Order.MgrOrder;
import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.Product;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SingleProductActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_product);
        super.onCreateDrawer(true);

        Product product = (Product) getIntent().getSerializableExtra("SELECTED_PRODUCT");

        TextView title = findViewById(R.id.productTitle);
        ImageView picture = findViewById(R.id.productImage);
        TextView price = findViewById(R.id.productPrice);
        TextView description = findViewById(R.id.productDescription);
        TextView state = findViewById(R.id.lblproductState);
        Button addProduct = findViewById(R.id.btnAddCart);

        title.setText(product.getName());
        picture.setImageResource(getDrawable(product));
        price.setText(product.getPrice().toString()+"$");
        description.setText(product.getDescription());

        if(product.getQuantity()>0){
            state.setText("En stock.");
        }else{
            state.setText("En rupture de stock.");
        }

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });



    }

    public int getDrawable(Product product){
        String[] imagepath = product.getImagePath().split("\\.");
        int path = getResources().getIdentifier(imagepath[0], "drawable", getPackageName());

        return path;
    }

    public void addToCart(){
        ArrayList<Product> productToAdd= new ArrayList<>();
        Product product = (Product) getIntent().getSerializableExtra("SELECTED_PRODUCT");
        productToAdd.add(product);

        SharedPreferences prefs;
        prefs = this.getSharedPreferences("UserPref", 0);
        int id_client = prefs.getInt("userId", 0);

        int state = 2;
        int method = 2;
        double tps = product.getPrice()*0.05;
        double tvq = product.getPrice()*0.09975;

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(c);

        ArrayList<Integer> qty = new ArrayList<>();
        qty.add(1);

        Order newOrder = new Order(state,productToAdd,id_client,method,tps,tvq,date,qty);
        MgrOrder orderManager = new MgrOrder();

        //Remove comments once addOrder Function is done!
        //orderManager.addOrder(newOrder);

    }

}
