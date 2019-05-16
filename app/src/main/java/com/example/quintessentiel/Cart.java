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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.Product;

import java.util.ArrayList;


public class Cart extends BaseActivity {

    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        super.onCreateDrawer(true);

        order = (Order) getIntent().getSerializableExtra("order");

        if (order == null) {
            Log.d("FML", "onCreate: ORDER == NULL");
            finish();
        }
        else {

            Button btn = findViewById(R.id.command_cart);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AddOrder.class);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }
            });
            ListView listView = findViewById(R.id.cart_products);


            ProductAdapter adapter = new ProductAdapter(this, order.getProducts(), order.getQuantities());
            listView.setAdapter(adapter);
            justifyListViewHeightBasedOnChildren(listView);
        }
    }

    class ProductAdapter extends ArrayAdapter<Product> {

        Context context;
        ArrayList<Product> products;
        ArrayList<Integer> quantities;

        ProductAdapter(Context c, ArrayList<Product> products, ArrayList<Integer> quantities) {
            super(c, R.layout.order_products_rows, R.id.text, products);
            this.context = c;
            this.products = products;
            this.quantities = quantities;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.order_products_rows, parent, false);

            ImageView imageView = row.findViewById(R.id.product_image);
            TextView product_name = row.findViewById(R.id.product_name);
            TextView product_qty = row.findViewById(R.id.product_qty);
            TextView product_price = row.findViewById(R.id.product_price);

            String[] imagepath = products.get(position).getImagePath().split("\\.");
            int path = getResources().getIdentifier(imagepath[0], "drawable", getPackageName());
            imageView.setImageResource(path);
            product_name.setText(products.get(position).getName());
            product_qty.setText(Integer.toString(quantities.get(position)));
            product_price.setText(Double.toString(products.get(position).getPrice()));

            return row;
        }
    }
    public void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }
}
