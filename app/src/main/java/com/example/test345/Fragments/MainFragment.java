package com.example.test345.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.test345.Activities.AddProductActivity;
import com.example.test345.Activities.AllProductsActivity;
import com.example.test345.R;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Button btnLogInMain, btnSignUpMain,AddProduct,All;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        func();
    }

    public void func() {

        btnLogInMain = getView().findViewById(R.id.btnLogInMain);
        AddProduct = getView().findViewById(R.id.AddProduct);
        btnSignUpMain = getView().findViewById(R.id.btnSignUpMain);
        All=getView().findViewById(R.id.btnAll);
        //TRANS FROM FRAGMENT TO FRAGMENT BY BUTTON CLICK

        btnSignUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment SignUpFragment = new SignUpFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.FrameLayout, SignUpFragment, SignUpFragment.getTag()).commit();
            }
        });

        //TRANS FROM FRAGMENT TO FRAGMENT BY BUTTON CLICK

        btnLogInMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LogInFragment LogInFragment = new LogInFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.FrameLayout, LogInFragment, LogInFragment.getTag()).commit();
            }
        });
        //TRANS FROM FRAGMENT TO ACTIVITY BY BUTTON CLICK

        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddProductActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AllProductsActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
    }
}



