package com.example.quintessentiel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quintessentiel.Product.MgrProduct;
import com.example.quintessentiel.Product.Product;

import java.util.ArrayList;


public class CatalogActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Product> products;

    MgrProduct mgrProduct = new MgrProduct();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog);

        mgrProduct.getAllProducts();
        products = mgrProduct.getProducts();
        listView = findViewById(R.id.productList);

        ProductAdapter adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adapter.getItem(position);
                Intent myIntent = new Intent(CatalogActivity.this, SingleProductActivity.class);

                    myIntent.putExtra("SELECTED_PRODUCT",product);
                    CatalogActivity.this.startActivity(myIntent);
            }
        });

    }

    public class ProductAdapter extends ArrayAdapter<Product> {
        Context context;
        ArrayList<Product> products;

        ProductAdapter(Context c, ArrayList<Product> products) {
            super(c, R.layout.catalog_row, R.id.product_name, products);
            this.context = c;
            this.products = products;
        }

        @NonNull
        @Override

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View productRow = layoutInflater.inflate(R.layout.catalog_row, parent, false);


            ImageView productImage = productRow.findViewById(R.id.product_image);
            TextView productTitle = productRow.findViewById(R.id.product_name);
            TextView productPrice = productRow.findViewById(R.id.product_price);
            TextView productDescription = productRow.findViewById(R.id.product_description);
            TextView productState = productRow.findViewById(R.id.product_qty);

            String[] imagepath = products.get(position).getImagePath().split("\\.");
            int path = getResources().getIdentifier(imagepath[0], "drawable", getPackageName());
            productImage.setImageResource(path);

            double priceValue = products.get(position).getPrice();
            int price = (int) priceValue;

            productTitle.setText(products.get(position).getName());
            productPrice.setText(price+" $");
            productDescription.setText(products.get(position).getDescription());

            if ((products.get(position).getQuantity()) > 0) {
                productState.setText("En stock");
            } else {
                productState.setText("En rupture de stock");
            }

            return productRow;
        }


    }
}