package com.example.test345.Activities;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test345.Classes.Product;
import com.example.test345.R;
import com.example.test345.Classes.RecyclerViewAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllProductsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter<Product, RecyclerViewAdapter.ViewHolder> adapter;
    private CollectionReference collectionRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        attachComponents();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    private void attachComponents() {
        recyclerView = findViewById(R.id.productList);

        collectionRef = FirebaseFirestore.getInstance().collection("SignImages");
        // Define and set the layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        LoadData();
    }
    private void LoadData() {
        Query query = collectionRef.orderBy("ProductName", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Product> options = new FirestoreRecyclerOptions.Builder<Product>()
                .setQuery(query,Product.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Product, RecyclerViewAdapter.ViewHolder>(options) {
            @NonNull
            @Override
            public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
                return new RecyclerViewAdapter.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position, @NonNull Product model) {
                holder.bind(model);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
