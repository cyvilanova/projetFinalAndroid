
package com.example.quintessentiel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
/****************************************
 Fichier: Order.java
 Auteur: Catherine Bronsard
 Fonctionnalité: Commande
 Date : 8-4-2019

 Vérification :
 Date               Nom                   Approuvé
 =========================================================


 Historique de modifications :
 Date               Nom     Description
 =========================================================
 8-4-2019           CB      Création
 12-4-2019           CB      Avec intent
 ****************************************/

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.quintessentiel.Order.CtrlOrder;
import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.*;

import java.util.ArrayList;

public class MyOrders extends BaseActivity {

    ListView listView;
    int id_client = 0;
    ArrayList<Order> orders = new ArrayList<>();
    CtrlOrder ctrlOrder = new CtrlOrder();

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_orders);
        super.onCreateDrawer(true);
        SharedPreferences prefs;
        prefs = this.getSharedPreferences("UserPref", 0);
        id_client = prefs.getInt("userId", 0);

        if (id_client == 0) {

        }
        else {
            orders = ctrlOrder.getAllOrdersClient(id_client);

            Log.d("FML", "onCreate: " + orders.get(0).getQuantities());

            listView = findViewById(R.id.orders_listView);

            OrdersAdapter adapter = new OrdersAdapter(this, orders);
            listView.setAdapter(adapter);
        }
    }

    class OrdersAdapter extends ArrayAdapter<Order> {

        Context context;
        ArrayList<Order> orders;

        OrdersAdapter(Context c, ArrayList<Order> orders){
            super(c, R.layout.order_rows, R.id.text, orders);
            this.context = c;
            this.orders = orders;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.order_rows, parent, false);

            TextView date = row.findViewById(R.id.date);
            TextView no_order = row.findViewById(R.id.no_order);
            TextView state_order = row.findViewById(R.id.state_order);
            TextView sous_total = row.findViewById(R.id.sous_total);
            TextView taxes = row.findViewById(R.id.taxes);
            TextView total = row.findViewById(R.id.total);

            date.setText(orders.get(position).getDate());
            no_order.setText("No : " + Integer.toString(orders.get(position).getId()));
            state_order.setText(Integer.toString(orders.get(position).getIdState()));
            sous_total.setText(Double.toString(orders.get(position).getTotal()-(orders.get(position).getTps() + orders.get(position).getTvq())));
            taxes.setText((Double.toString(orders.get(position).getTps() + orders.get(position).getTvq())));
            total.setText((Double.toString(orders.get(position).getTotal())));


            ListView listView = row.findViewById(R.id.list_order);
            ProductAdapter productAdapter = null;
            for (int i = 0; i < orders.get(position).getProducts().size(); i++) {
                ArrayList<Product> pro = new ArrayList<>();
                pro.add(orders.get(position).getProducts().get(i));
                productAdapter = new ProductAdapter(context, pro, orders.get(position).getQuantities());
            }

            productAdapter = new ProductAdapter(context, orders.get(position).getProducts(), orders.get(position).getQuantities());

            listView.setAdapter(productAdapter);
            justifyListViewHeightBasedOnChildren(listView);
            return row;
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
}
