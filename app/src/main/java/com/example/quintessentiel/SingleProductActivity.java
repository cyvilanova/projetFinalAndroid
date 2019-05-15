package com.example.quintessentiel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quintessentiel.Product.Product;

import org.w3c.dom.Text;

public class SingleProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_product);
        Product product = (Product) getIntent().getSerializableExtra("SELECTED_PRODUCT");

        TextView title = findViewById(R.id.productTitle);
        ImageView picture = findViewById(R.id.productImage);
        TextView price = findViewById(R.id.productPrice);
        TextView description = findViewById(R.id.productDescription);
        TextView state = findViewById(R.id.lblproductState);

        title.setText(product.getName());
        picture.setImageResource(getDrawable(product));
        price.setText(product.getPrice().toString()+"$");
        description.setText(product.getDescription());

        if(product.getQuantity()>0){
            state.setText("En stock.");
        }else{
            state.setText("En rupture de stock.");
        }




    }

    public int getDrawable(Product product){
        String[] imagepath = product.getImagePath().split("\\.");
        int path = getResources().getIdentifier(imagepath[0], "drawable", getPackageName());

        return path;
    }
}
