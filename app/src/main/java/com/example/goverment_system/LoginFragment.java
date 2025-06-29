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

        TextView titleTV = view.findViewById(R.id.login_page);
        EditText emailET = view.findViewById(R.id.email_login);
        EditText passwordET = view.findViewById(R.id.password_login);
        Button loginBtn = view.findViewById(R.id.sub_code);
        TextView signUpTV = view.findViewById(R.id.tvSignUp);

        titleTV.setText(R.string.login_title);
        emailET.setHint(R.string.email_hint);
        passwordET.setHint(R.string.password_hint);
        loginBtn.setText(R.string.login_button);
        signUpTV.setText(R.string.signup_prompt);

        loginBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(),  getString(R.string.enter_email_password), Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                            // انتقال إلى الصفحة الرئيسية

                            requireActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_main, new HomeFragment())
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Toast.makeText(getContext(), getString(R.string.login_failed) , Toast.LENGTH_LONG).show();
                        }
                    });
        });
        TextView tvsignup = view.findViewById(R.id.tvSignUp);
        tvsignup.setOnClickListener(v -> goTSignUp());

        TextView forgot=view.findViewById(R.id.forgot_pass);
        forgot.setText(R.string.forgot_password);
     forgot.setOnClickListener(v -> goTchpass());
        return view;



    }
     private void goTSignUp() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, new MainFragment())
                .addToBackStack(null)
                .commit();
    }
    private void goTchpass() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, new Change_passFragment())
                .addToBackStack(null)
                .commit();
    }
    }