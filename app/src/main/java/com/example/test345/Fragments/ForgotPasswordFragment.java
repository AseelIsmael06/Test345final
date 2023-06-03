package com.example.test345.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.test345.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment
{
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        private String mParam1;
        private String mParam2;
        private Button btnForgot;
        private EditText etEmailforgot;
        private FirebaseAuth mAuth;

        private void Reset() {

            etEmailforgot = getView().findViewById(R.id.etEmailforgot);
            btnForgot = getView().findViewById(R.id.btnForgot);
            mAuth = FirebaseAuth.getInstance();
            btnForgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email = btnForgot.getText().toString();
                    if (email.trim().isEmpty()) {
                        Toast.makeText(getContext(), "SOMETHING FAILED ! " + "", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resetPassword();
                }
            });
        }

    private void resetPassword(){
        try{
            if(!etEmailforgot.getText().toString().isEmpty())
                mAuth.sendPasswordResetEmail(etEmailforgot.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "Password reset email has been sent.", Toast.LENGTH_SHORT).show();
                                LogInFragment LogInFragment=new LogInFragment();
                                FragmentManager manager=getFragmentManager();
                                manager.beginTransaction()
                                        .replace(R.id.FrameLayout,LogInFragment,LogInFragment.getTag())
                                        .commit();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Password reset failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            else{
                Toast.makeText(getContext(), "Missing fields identified.", Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public ForgotPasswordFragment() {
            // Required empty public constructor
        }
        public static ForgotPasswordFragment newInstance(String param1, String param2) {
            ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
            return inflater.inflate(R.layout.fragment_forgot_password, container, false);
        }
        @Override
        public void onStart() {
            super.onStart();
            Reset();
        }
}