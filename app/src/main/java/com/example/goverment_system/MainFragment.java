package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        Button login = view.findViewById(R.id.login);
        Button signup = view.findViewById(R.id.signup);
        TextView welcomeText = view.findViewById(R.id.wlcm);

        // ضبط النصوص حسب لغة الجهاز
        login.setText(R.string.login);
        signup.setText(R.string.sign_up);
        welcomeText.setText(R.string.welcome);



        login.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new LoginFragment())
                        .addToBackStack(null)
                        .commit()
        );

       signup.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new SignupFragment())
                        .addToBackStack(null)
                        .commit()
        );
        return view;
    }
}
