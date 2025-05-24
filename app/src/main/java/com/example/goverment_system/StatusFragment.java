package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    private ListView listViewReports;
    private List<Report> reports;
    private ReportAdapter adapter;
    private ImageButton arrow_back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_status, container, false);

        TextView status=view.findViewById(R.id.status);
        status.setText(R.string.status_title);
        arrow_back = view.findViewById(R.id.backArrow);
        arrow_back.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_main, new HomeFragment())
                    .commit();
        });

        listViewReports = view.findViewById(R.id.listViewReports);
        reports = new ArrayList<>();
        adapter = new ReportAdapter(requireContext(), reports);
        listViewReports.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userEmail = mAuth.getCurrentUser().getEmail();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reportsRef = database.getReference("reports");

        reportsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reports.clear();
                for (DataSnapshot reportSnapshot : snapshot.getChildren()) {
                    ReportFirebase reportFirebase = reportSnapshot.getValue(ReportFirebase.class);
                    if (reportFirebase != null && reportFirebase.userEmail != null &&
                            reportFirebase.userEmail.trim().equals(userEmail.trim())) {

                        Report report = new Report(
                                reportFirebase.title,
                                reportFirebase.authority,
                                reportFirebase.date,
                                reportFirebase.status,
                                reportFirebase.userEmail
                        );
                        reports.add(report);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // هنا ممكن تعرضي رسالة خطأ للمستخدم اذا حبيت
            }
        });
    }
}
