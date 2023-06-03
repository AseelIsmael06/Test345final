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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyviewHolder> {
    private List<Product> ProductList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, List<Product> ProductList) {
        this.ProductList = ProductList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.productName.setText("name product: " + ProductList.get(position).getProductName());
        holder.proPrice.setText("price product: " + ProductList.get(position).getProPrice());

    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }
}



