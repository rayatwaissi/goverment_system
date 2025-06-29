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

public class Change_passFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.change_password,container,false);

        TextView tvchange=view.findViewById(R.id.ch_pass);
        EditText email =view.findViewById(R.id.email_log);
        EditText code=view.findViewById(R.id.code_confirm);
        EditText pass=view.findViewById(R.id.password);
        EditText confirm_password=view.findViewById(R.id.confirm_password);
        Button confirm_code=view.findViewById(R.id.sub_code);

        tvchange.setText(R.string.change_pass_page);
        email.setHint(R.string.email_hint);
        pass.setHint(R.string.password_hint);
        code.setHint(R.string.code_confirm);
        confirm_password.setHint(R.string.confirm_password_hint);

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
        pass.setVisibility(View.INVISIBLE);
        confirm_password.setVisibility(View.INVISIBLE);
        confirm_code.setOnClickListener(v -> {
            String enter_email = email.getText().toString().trim();

            String confirm = code.getText().toString().trim();

            if (enter_email.isEmpty() ) {
                Toast.makeText(getContext(),  getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
                return;
            }
            if (confirm.isEmpty()) {
                Toast.makeText(getContext(),  getString(R.string.enter_code), Toast.LENGTH_SHORT).show();
                    return;
                }

            if (pass.getVisibility() == View.INVISIBLE && confirm_password.getVisibility() == View.INVISIBLE) {
                pass.setVisibility(View.VISIBLE);
                confirm_password.setVisibility(View.VISIBLE);
                confirm_code.setText(R.string.change_pass_page); // مثلاً: "تأكيد تغيير كلمة المرور"
                return;
            }

            // نقرأ كلمات المرور فقط بعد أن تظهر الحقول
            String Change_pass = pass.getText().toString().trim();
            String confirm_pass = confirm_password.getText().toString().trim();

            if (Change_pass.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.ch_pass), Toast.LENGTH_SHORT).show();
                return;
            }

            if (confirm_pass.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.passwords_not_match), Toast.LENGTH_SHORT).show();
                return;
            }

            if (!confirm_pass.equals(Change_pass)) {
                Toast.makeText(getContext(), getString(R.string.passwords_not_match), Toast.LENGTH_SHORT).show();
                return;
            }

        });

        return view;
    }
}
