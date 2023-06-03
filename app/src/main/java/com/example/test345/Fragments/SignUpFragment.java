package com.example.test345.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.test345.Classes.FirebaseServices;
import com.example.test345.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.checkerframework.checker.nullness.qual.NonNull;
public class SignUpFragment extends Fragment
{
    private Button btnSignUpSIGNUP;
      private ImageButton Goprevious;
    private EditText etUsernameSIGNUP,etPasswordSIGNUP;
    private FirebaseServices fbs;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        etUsernameSIGNUP = getView().findViewById(R.id.etUsernameSIGNUP);
        fbs=FirebaseServices.getInstance();
        etPasswordSIGNUP = getView().findViewById(R.id.etPasswordSIGNUP);
        btnSignUpSIGNUP = getView().findViewById(R.id.btnSignUpSIGNUP);
        Goprevious=getView().findViewById(R.id.ivGoToMainSignUp);
        Goprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment MainFragment = new MainFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.FrameLayout, MainFragment, MainFragment.getTag()).commit();
            }
        });
        btnSignUpSIGNUP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String eMail = etUsernameSIGNUP.getText().toString();
                String password = etPasswordSIGNUP.getText().toString();
                if (eMail.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty!!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().createUserWithEmailAndPassword(eMail, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        AllProductsFragment AllProductsFragment = new AllProductsFragment();
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction().replace(R.id.FrameLayout, AllProductsFragment, AllProductsFragment.getTag()).commit();

                    }
                });
            }
        });
    }
}