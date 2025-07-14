package com.example.goverment_system;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

    public class ReportAdapter extends ArrayAdapter<Report> {
        private Context context;
        private List<Report> reportList;

        public ReportAdapter(Context context, List<Report> reports) {
            super(context, 0, reports);
            this.context = context;
            this.reportList = reports;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Report report = reportList.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_report, parent, false);
            }

            TextView status_btn = convertView.findViewById(R.id.statusBadge);
            TextView txtTitle = convertView.findViewById(R.id.txtTitle);
            TextView txtMinistry = convertView.findViewById(R.id.txtMinistry);
            TextView txtDate = convertView.findViewById(R.id.txtdate);

            txtTitle.setText(report.getTitle());
            txtMinistry.setText(report.getMinistry());
            txtDate.setText(report.getDate());

            String localizedStatus = getLocalizedStatus(report.getStatus());
            status_btn.setText(localizedStatus);

            // فتح صفحة التفاصيل عند الضغط
            status_btn.setOnClickListener(v -> {
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;
                    // إنشاء الفراجمنت وإرسال reportId إليه
                    ReportDetailsFragment detailsFragment = ReportDetailsFragment.newInstance(report.getReportId());

                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, detailsFragment) //  R.id.fragment_main هو مكان عرض الفراجمنتات
                            .addToBackStack(null)
                            .commit();
                }
            });


            // تغيّير اللون حسب الحالة
            switch (report.getStatus().toLowerCase()) {
                case "resolved":
                    status_btn.setBackgroundColor(ContextCompat.getColor(context, R.color.status_resolved));
                    break;
                case "rejected":
                    status_btn.setBackgroundColor(ContextCompat.getColor(context, R.color.status_rejected));
                    break;
                case "in review":
                    status_btn.setBackgroundColor(ContextCompat.getColor(context, R.color.status_in_review));
                    break;
                case "pending":
                case "new":
                    status_btn.setBackgroundColor(ContextCompat.getColor(context, R.color.status_pending));
                    break;
                default:
                    status_btn.setBackgroundColor(Color.GRAY);
            }

            return convertView;


    }

        // ضعيها خارج getView
        private String getLocalizedStatus(String key) {
            switch (key.toLowerCase()) {
                case "pending":
                case "new":
                    return context.getString(R.string.status_pending);
                case "in review":
                    return context.getString(R.string.status_in_review);
                case "resolved":
                    return context.getString(R.string.status_resolved);
                case "rejected":
                    return context.getString(R.string.status_rejected);
                default:
                    return key;
            }
        }
    }

