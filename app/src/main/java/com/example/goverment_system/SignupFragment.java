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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFragment extends Fragment {
    private FirebaseAuth mAuth;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.signup, container, false);


        TextView titleTV = view.findViewById(R.id.signup_page);
        EditText nameET = view.findViewById(R.id.name);
        EditText emailET = view.findViewById(R.id.email);
        EditText passwordET = view.findViewById(R.id.password);
        EditText confirmPasswordET = view.findViewById(R.id.ConfirmPass);
        EditText phoneET = view.findViewById(R.id.phone);
        Button registerBtn = view.findViewById(R.id.signup_btn);
        TextView tvlogin = view.findViewById(R.id.tvlogin);

        // تعيين النصوص حسب لغة الجهاز
        titleTV.setText(R.string.signup_title);
        nameET.setHint(R.string.full_name_hint);
        emailET.setHint(R.string.email_hint);
        phoneET.setHint(R.string.phone_hint);
        passwordET.setHint(R.string.password_hint);
        confirmPasswordET.setHint(R.string.confirm_password_hint);
        registerBtn.setText(R.string.signup_button);
        tvlogin.setText(R.string.login_prompt);

        registerBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String email = emailET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            String confirmPassword = confirmPasswordET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                return;
            }
            if(!email.contains("@"))
            {
                Toast.makeText(getContext(), getString(R.string.email_check), Toast.LENGTH_SHORT).show();
                return;
            }
            if(!phone.matches("^(079|078|077)[0-9]{7}$"))
            {
                Toast.makeText(getContext(),  getString(R.string.invalid_phone), Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), getString(R.string.passwords_not_match), Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();

                            // أنشئ مرجع لقاعدة البيانات
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users");

                            // خزّن البيانات كمجموعة بيانات بسيطة
                            User user = new User(name, email, phone);
                            dbRef.child(userId).setValue(user)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(getContext(),getString(R.string.registration_success), Toast.LENGTH_SHORT).show();
                                     //انتقل لصفحة home
                                      requireActivity().getSupportFragmentManager().beginTransaction()
                                              .replace(R.id.fragment_main,new HomeFragment())
                                              .addToBackStack(null).commit();
                                    })
                                    .addOnFailureListener(e -> {
                                        String message = getString(R.string.data_failed, e.getMessage());
                                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                    });

                        } else {
                            String message = getString(R.string.registration_failed, task.getException().getMessage());
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                        }
                    });
        });


        tvlogin.setOnClickListener(v -> goToLogin());






        return view;
    };
    private void goToLogin() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, new MainFragment())
                .addToBackStack(null)
                .commit();
    }
}



