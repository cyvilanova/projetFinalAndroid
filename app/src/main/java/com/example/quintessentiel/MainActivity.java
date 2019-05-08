package com.example.quintessentiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        /* Lines to use when we no longer need an icon from the menu
        ImageView t = findViewById(R.id.toolBarLeftBlockImage);
        t.setVisibility(View.INVISIBLE);
        */
    }
}
