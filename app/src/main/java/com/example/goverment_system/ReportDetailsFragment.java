package com.example.goverment_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.*;

public class ReportDetailsFragment extends Fragment {

    private TextView titleText, issueTypeText, GovernorateText, authorityText, dateText, descriptionText, statusText, statusReasonText;
    private TextView title, issueType, Governorate, authority, date, description, status, statusReason;

    private FirebaseDatabase database;

    private String reportId;

    public static ReportDetailsFragment newInstance(String reportId) {
        ReportDetailsFragment fragment = new ReportDetailsFragment();
        Bundle args = new Bundle();
        args.putString("reportId", reportId);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_report_details, container, false);

        // ربط الواجهات من ناحية اللغة
        title = view.findViewById(R.id.title);
        titleText = view.findViewById(R.id.titleText);
        issueType = view.findViewById(R.id.typeText);
        issueTypeText = view.findViewById(R.id.IssueType);

        Governorate = view.findViewById(R.id.Governorate);
        GovernorateText = view.findViewById(R.id.GovernorateText);
        authority = view.findViewById(R.id.Authority);
        authorityText = view.findViewById(R.id.authorityText);
        date = view.findViewById(R.id.Date);
        dateText = view.findViewById(R.id.dateText);
        description = view.findViewById(R.id.Discription);
        descriptionText = view.findViewById(R.id.descriptionText);
        status = view.findViewById(R.id.Status);
        statusText = view.findViewById(R.id.statusText);
        statusReasonText = view.findViewById(R.id.StatusReason);
        statusReason = view.findViewById(R.id.StatusReasonText);
        Button backbtn=view.findViewById(R.id.backBTN);


        // إعداد النصوص الثابتة
        title.setText(R.string.title_text);
        titleText.setHint(R.string.title_text);
        issueType.setText(R.string.type_text);
        issueTypeText.setHint(R.string.type_text);
        Governorate.setText(R.string.Governorate_text);
        GovernorateText.setHint(R.string.Governorate_text);
        authority.setText(R.string.authority_text);
        authority.setHint(R.string.authority_text);

        date.setText(R.string.date_text);
        dateText.setHint(R.string.date_text);
        description.setText(R.string.description_text);
        descriptionText.setHint(R.string.description_text);
        status.setText(R.string.status_text);
        statusText.setHint(R.string.status_text);
        statusReason.setText(R.string.statusReason_text);
        statusReasonText.setHint(R.string.statusReason_text);
        backbtn.setText(R.string.btnBack_show_report);

        // عمل سبب الحالة غير ظاهرة الا في حالة الرفض
        statusReason.setVisibility(View.INVISIBLE);
        statusReasonText.setVisibility(View.INVISIBLE);

        if (statusText.getText().toString().equals("Rejected") || statusText.getText().toString().equals("مرفوض")) {
            statusReason.setVisibility(View.VISIBLE);
            statusReasonText.setVisibility(View.VISIBLE);
        }

// الرجوع الى صفحة عرض الستيتاس
        backbtn.setOnClickListener(v -> {
    requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_main, new StatusFragment())
            .addToBackStack(null)
            .commit();
});

        return view;
    }
}
