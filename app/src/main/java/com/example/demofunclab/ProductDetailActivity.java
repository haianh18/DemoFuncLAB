package com.example.demofunclab;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get data from intent
        String name = getIntent().getStringExtra("product_name");
        String description = getIntent().getStringExtra("product_description");
        String date = getIntent().getStringExtra("product_date");
        String time = getIntent().getStringExtra("product_time");
        String facilities = getIntent().getStringExtra("product_facilities");
        int price = getIntent().getIntExtra("product_price", 0);
        int iconResId = getIntent().getIntExtra("product_icon", R.drawable.ic_package);

        // Set data to views
        ((TextView)findViewById(R.id.tv_package_name)).setText(name);
        ((TextView)findViewById(R.id.tv_package_info)).setText(description);
        ((TextView)findViewById(R.id.tv_date)).setText(date);
        ((TextView)findViewById(R.id.tv_duration)).setText(time);
        ((TextView)findViewById(R.id.textFacilities)).setText(facilities);
        ((TextView)findViewById(R.id.tv_package_price)).setText(price + "đ");
        // Set icon if you have an ImageView for it
        // ((ImageView)findViewById(R.id.iv_package_icon)).setImageResource(iconResId);

        // Back button
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }
}