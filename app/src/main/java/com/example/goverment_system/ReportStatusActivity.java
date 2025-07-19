package com.example.goverment_system;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.firebase.database.core.Repo;

import java.util.ArrayList;

public class ReportStatusActivity extends AppCompatActivity {

    ListView listViewReports;
    ReportAdapter adapter;
    ArrayList<Report> reportList;
    FirebaseDatabase database;
    DatabaseReference reportsRef;
    FirebaseAuth mAuth;
    String userEmail;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_status); // ربط مع XML


        status.setText(getString(R.string.status_title));
        // تعريف العناصر
        listViewReports = findViewById(R.id.listViewReports);
        reportList = new ArrayList<>();
        adapter = new ReportAdapter(this, reportList);
        listViewReports.setAdapter(adapter);



        mAuth = FirebaseAuth.getInstance();
        userEmail = mAuth.getCurrentUser().getEmail();
        database = FirebaseDatabase.getInstance();
        reportsRef = database.getReference("reports");

        reportsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reportList.clear();
                for (DataSnapshot reportSnapshot : snapshot.getChildren()) {
                    ReportFirebase reportFirebase = reportSnapshot.getValue(ReportFirebase.class);
                    if (reportFirebase != null && reportFirebase.userEmail.trim().equals(userEmail.trim())) {

                        String reportId = snapshot.getKey();

                        Report report = new Report(
                                reportId,
                                reportFirebase.title,
                                reportFirebase.authority,
                                reportFirebase.date,
                                reportFirebase.status,
                                reportFirebase.userEmail

                        );
                        reportList.add(report);
                    }

                }
                adapter.notifyDataSetChanged();
                Toast.makeText(ReportStatusActivity.this, "Loaded " + reportList.size() + " reports", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReportStatusActivity.this, "Failed to load reports.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
