/****************************************
 Fichier: Order.java
 Auteur: Catherine Bronsard
 Fonctionnalité: Commande
 Date : 11-4-2019

 Vérification :
 Date               Nom                   Approuvé
 =========================================================


 Historique de modifications :
 Date               Nom     Description
 =========================================================
 11-4-2019           CB      Création
 ****************************************/
package com.example.quintessentiel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.Product;

import java.util.ArrayList;


public class Cart extends AppCompatActivity {

    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);


        order = (Order) getIntent().getSerializableExtra("order");

        ListView listView = findViewById(R.id.cart_products);


        ProductAdapter adapter = new ProductAdapter(this, order.getProducts());
        listView.setAdapter(adapter);
    }

    class ProductAdapter extends ArrayAdapter<Product> {

        Context context;
        ArrayList<Product> products;

        ProductAdapter(Context c, ArrayList<Product> products) {
            super(c, R.layout.cart, R.id.text, products);
            this.context = c;
            this.products = products;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.cart_rows, parent, false);

            ImageView imageView = row.findViewById(R.id.product_image);
            TextView product_name = row.findViewById(R.id.product_name);
            TextView product_qty = row.findViewById(R.id.product_qty);
            TextView product_desc = row.findViewById(R.id.product_description);
            TextView product_price = row.findViewById(R.id.product_price);


            int path = getResources().getIdentifier(products.get(position).getImagePath(), "drawable", getPackageName());

            imageView.setImageResource(path);
            product_name.setText(products.get(position).getName());
            product_qty.setText(Integer.toString(products.get(position).getQuantity()));
            product_price.setText(Double.toString(products.get(position).getPrice()));
            product_desc.setText(products.get(position).getDescription());

            return row;
        }
    }
}
