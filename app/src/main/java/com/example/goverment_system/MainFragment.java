package com.example.goverment_system;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // تحميل اللغة أولاً


        View view = inflater.inflate(R.layout.activity_main, container, false);

        Button login = view.findViewById(R.id.login);
        Button signup = view.findViewById(R.id.signup);
        TextView welcomeText = view.findViewById(R.id.wlcm);
        Button btnLanguage = view.findViewById(R.id.btnLanguage);

        // ضبط نص زر اللغة حسب اللغة الحالية
        btnLanguage.setText(getCurrentLang().equals("ar") ? "English" : "العربية");

        // عند الضغط، يتم تغيير اللغة وحفظها
        btnLanguage.setOnClickListener(v -> {
            String currentLang = getCurrentLang();
            String newLang = currentLang.equals("ar") ? "en" : "ar";
            setLocale(newLang);
        });

        // ضبط النصوص حسب اللغة
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

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

        // حفظ اللغة
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

        // إعادة تشغيل النشاط لتطبيق اللغة
        getActivity().recreate();
    }



    private String getCurrentLang() {
        return getResources().getConfiguration().locale.getLanguage();
    }

}
