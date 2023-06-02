package com.example.test345.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test345.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Product> ProductList;

    public RecyclerViewAdapter(Context context, List<Product> ProductList) {
        this.ProductList = ProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = ProductList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount()
    {
        return ProductList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView Name,price;
        public View v;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivPhotoProductRow);
            Name = itemView.findViewById(R.id.tvNameProductRow);
            price=itemView.findViewById(R.id.tvPriceProductRow);
            v = itemView;
        }

        public void bind(Product product) {
            Name .setText(product.getProductName());
            price .setText(product.getProPrice());
            Picasso.get().load(product.getProPhoto()).into(imageView);
        }
    }
}



