package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.login, container, false);
        EditText emailET = view.findViewById(R.id.email_login);
        EditText passwordET = view.findViewById(R.id.password_login);
        Button loginBtn = view.findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "please enter the password and email field", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                            // انتقال إلى الصفحة الرئيسية

                            requireActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_main, new HomeFragment())
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Toast.makeText(getContext(), "Failed ,inccorect password or email" , Toast.LENGTH_LONG).show();
                        }
                    });
        });
        TextView tvsignup = view.findViewById(R.id.tvSignUp);
        tvsignup.setOnClickListener(v -> goTSignUp());
        return view;



    }
     private void goTSignUp() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, new MainFragment())
                .addToBackStack(null)
                .commit();
    }
    }