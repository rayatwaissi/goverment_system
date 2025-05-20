package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button RepIssue=view.findViewById(R.id.RIssue);
        RepIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new ReportFragment())
                        .commit();
            }
        });

        Button viewStatus=view.findViewById(R.id.VRS);
        viewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new StatusFragment())
                        .commit();
            }
        });
        return view;
    }
}
