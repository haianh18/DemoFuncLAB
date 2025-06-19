package com.example.demofunclab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
        void onRemoveClick(int position);
    }

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_lab, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textProductName.setText(product.getName());
        holder.textProductDescription.setText(product.getDescription());
        holder.textBookingDate.setText(product.getDate());
        holder.textBookingTime.setText(product.getTime());
        holder.textFacilities.setText(product.getFacilities());
        holder.textPrice.setText(product.getPrice() + "đ");
        holder.imageLabIcon.setImageResource(product.getIconResId());
        holder.itemView.setOnClickListener(v -> listener.onProductClick(product));
        holder.buttonRemove.setOnClickListener(v -> listener.onRemoveClick(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageLabIcon;
        TextView textProductName, textProductDescription, textBookingDate, textBookingTime, textFacilities, textPrice;
        View buttonRemove;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageLabIcon = itemView.findViewById(R.id.imageLabIcon);
            textProductName = itemView.findViewById(R.id.textProductName);
            textProductDescription = itemView.findViewById(R.id.textProductDescription);
            textBookingDate = itemView.findViewById(R.id.textBookingDate);
            textBookingTime = itemView.findViewById(R.id.textBookingTime);
            textFacilities = itemView.findViewById(R.id.textFacilities);
            textPrice = itemView.findViewById(R.id.textPrice);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
        }
    }
}
