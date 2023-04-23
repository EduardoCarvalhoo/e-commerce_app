package com.example.e_commerce_app.presentation.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private final ProductList productList;

    public HomeAdapter(ProductList productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new HomeViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(productList.getProducts().get(position));
    }

    @Override
    public int getItemCount() {
        return productList.getProducts().size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView itemProductImageView;
        TextView itemBrandTextView;
        TextView itemCategoryTextView;
        TextView itemPriceTextView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemProductImageView = itemView.findViewById(R.id.item_product_image_view);
            itemBrandTextView = itemView.findViewById(R.id.item_product_name);
            itemCategoryTextView = itemView.findViewById(R.id.item_product_device_category_text_view);
            itemPriceTextView = itemView.findViewById(R.id.item_product_device_price_text_view);
        }

        public void bind(Product product) {
            itemBrandTextView.setText(product.getBrand());
            itemCategoryTextView.setText(product.getCategory());
            String price = itemView.getResources().getString(R.string.item_product_dollar_sign_real, product.getPrice());
            itemPriceTextView.setText(price);
            Glide.with(this.itemView).load(product.getImageUrl()).into(itemProductImageView);
        }
    }
}


