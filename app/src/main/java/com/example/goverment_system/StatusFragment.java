package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;



import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {


    private ListView listViewReports;
    private List<Report> reports;
    private ReportAdapter adapter;
    ImageButton arrow_back;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_status, container, false);

        arrow_back=view.findViewById(R.id.backArrow);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_main,new HomeFragment())
                        .commit();
            }
        });

        //ربط ListView الموجود في التصميم لعرض البلاغات.
        //إنشاء قائمة فارغة لتخزين البلاغات
        //إنشاء Adapter لربط القائمة reports مع ListView باستخدام ReportAdapter
        //تعيين الـ Adapter إلى الـ ListView لبدء عرض البيانات (حتى لو فارغة الآن)
        listViewReports = view.findViewById(R.id.listViewReports);
        reports = new ArrayList<>();
        adapter = new ReportAdapter(requireContext(), reports);
        listViewReports.setAdapter(adapter);


        return view;
    }

}
