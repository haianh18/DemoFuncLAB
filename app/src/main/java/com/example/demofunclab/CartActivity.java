package com.example.demofunclab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private List<Product> sampleProducts;
    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout emptyLayout;
    private MaterialCardView summaryCard;
    private TextView textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewCartItems);
        emptyLayout = findViewById(R.id.emptyStateLayout);
        summaryCard = findViewById(R.id.summaryCard);
        textTotal = findViewById(R.id.textTotal);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product(
                "Lab Package A",
                "2 tables - 2 chairs",
                "25/06/2025",
                "08:00 - 10:00",
                "Projector, Whiteboard",
                500000,
                R.drawable.ic_package
        ));
        sampleProducts.add(new Product(
                "Lab Package B",
                "4 tables - 8 chairs",
                "26/06/2025",
                "10:00 - 12:00",
                "Projector, Whiteboard, AC",
                900000,
                R.drawable.ic_package
        ));
        sampleProducts.add(new Product(
                "Lab Package C",
                "6 tables - 12 chairs",
                "27/06/2025",
                "14:00 - 16:00",
                "Projector, Whiteboard, AC, Sound System",
                1200000,
                R.drawable.ic_package
        ));
        sampleProducts.add(new Product(
                "Lab Package D",
                "8 tables - 16 chairs",
                "28/06/2025",
                "16:00 - 18:00",
                "Projector, Whiteboard, AC, Sound System, Video Conferencing",
                1500000,
                R.drawable.ic_package
        ));

        adapter = new ProductAdapter(this, sampleProducts, new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                // Optionally pass product info via intent extras
                intent.putExtra("product_name", product.getName());
                intent.putExtra("product_description", product.getDescription());
                intent.putExtra("product_date", product.getDate());
                intent.putExtra("product_time", product.getTime());
                intent.putExtra("product_facilities", product.getFacilities());
                intent.putExtra("product_price", product.getPrice());
                intent.putExtra("product_icon", product.getIconResId());
                startActivity(intent);
            }

            @Override
            public void onRemoveClick(int position) {
                sampleProducts.remove(position);
                adapter.notifyItemRemoved(position);
                updateCartState();
            }
        });
        recyclerView.setAdapter(adapter);
        updateCartState();

        // Back button logic
        findViewById(R.id.buttonBackToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCartState() {
        if (sampleProducts.isEmpty()) {
            emptyLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            summaryCard.setVisibility(View.GONE);
        } else {
            emptyLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            summaryCard.setVisibility(View.VISIBLE);
            int total = 0;
            for (Product p : sampleProducts) total += p.getPrice();
            textTotal.setText(String.valueOf(total));
        }
    }

}