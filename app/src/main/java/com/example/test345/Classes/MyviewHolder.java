package com.example.test345.Classes;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test345.R;

import org.checkerframework.checker.nullness.qual.NonNull;

public class MyviewHolder extends RecyclerView.ViewHolder {
    public TextView productName, proPrice;
    public MyviewHolder(@NonNull View itemView) {
        super(itemView);
        productName= itemView.findViewById(R.id.tvNameProductRow);
        proPrice =itemView.findViewById(R.id.tvPriceProductRow);
    }
}
