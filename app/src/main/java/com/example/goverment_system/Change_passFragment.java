package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class Change_passFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.change_password,container,false);

        TextView tvchange=view.findViewById(R.id.ch_pass);
        EditText email =view.findViewById(R.id.email_log);

        Button confirm_code_btn=view.findViewById(R.id.sub_code);

        tvchange.setText(R.string.change_pass_page);
        email.setHint(R.string.email_hint);

         confirm_code_btn.setText(R.string.submit);

        ImageButton back= view.findViewById(R.id.back_to_welcom);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new MainFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        confirm_code_btn.setOnClickListener(v -> {
            String enter_email = email.getText().toString().trim();

            if (enter_email.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(enter_email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), getString(R.string.reset_email_sent), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), getString(R.string.reset_email_failed), Toast.LENGTH_SHORT).show();
                        }
                    });
        });


        return view;
    }
}
