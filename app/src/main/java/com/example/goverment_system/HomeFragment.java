package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
     ImageButton logout=view.findViewById(R.id.logoutbtn);
     logout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             requireActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.fragment_main, new MainFragment())
                     .commit();
         }
     });
        return view;
    }
}
