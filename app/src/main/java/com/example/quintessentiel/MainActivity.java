/*package com.example.quintessentiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/

package com.example.quintessentiel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.quintessentiel.Order.Order;
import com.example.quintessentiel.Product.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_orders);

        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> pro1 = new ArrayList<>();
        ArrayList<Product> pro2 = new ArrayList<>();

        Product p1 = new Product("POUR ORDER 1", "description1", 42, 15.00);
        p1.setId(1);
        Product p12 = new Product("POUR ORDER 1-2", "description1-2", 421, 115.00);
        p12.setId(2);
        Product p2 = new Product("POUR ORDER 2", "description2", 69, 13444.00);
        p2.setId(3);



        pro1.add(p1);
        pro1.add(p12);
        pro2.add(p2);

        Order order1 = new Order(1,1, pro1,1, 1, 0.00, 0.00);
        Order order2 = new Order(2, 2, pro2,1, 1, 0.00, 0.00);


        orders.add(order1);
        orders.add(order2);

        listView = findViewById(R.id.orders_listView);

        OrdersAdapter adapter = new OrdersAdapter(this, orders);
        listView.setAdapter(adapter);
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

            date.setText("date");
            no_order.setText("No : " + Integer.toString(orders.get(position).getId()));
            state_order.setText(Integer.toString(orders.get(position).getIdState()));
            sous_total.setText("SOUS TOTAL");
            taxes.setText("TAXES");
            total.setText("TOTAL");

            ListView listView = row.findViewById(R.id.list_order);

            for (int i = 0; i < orders.get(position).getProducts().size(); i++) {
                ArrayList<Product> pro = new ArrayList<>();
                pro.add(orders.get(position).getProducts().get(i));
                ProductAdapter productAdapter = new ProductAdapter(context, pro);
            }

            ProductAdapter productAdapter = new ProductAdapter(context, orders.get(position).getProducts());


            listView.setAdapter(productAdapter);
            justifyListViewHeightBasedOnChildren(listView);
            return row;
        }


        class ProductAdapter extends ArrayAdapter<Product> {

            Context context;
            ArrayList<Product> products;

            ProductAdapter(Context c, ArrayList<Product> products) {
                super(c, R.layout.order_products_rows, R.id.text, products);
                this.context = c;
                this.products = products;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View row = layoutInflater.inflate(R.layout.order_products_rows, parent, false);

                ImageView imageView = row.findViewById(R.id.product_image);
                TextView product_name = row.findViewById(R.id.product_name);
                TextView product_qty = row.findViewById(R.id.product_qty);
                TextView product_price = row.findViewById(R.id.product_price);

                int path = getResources().getIdentifier("img" + products.get(position).getId(), "drawable", getPackageName());

                imageView.setImageResource(path);
                product_name.setText(products.get(position).getName());
                product_qty.setText(Integer.toString(products.get(position).getQuantity()));
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
