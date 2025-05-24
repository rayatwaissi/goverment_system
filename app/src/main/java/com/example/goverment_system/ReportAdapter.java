package com.example.goverment_system;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

            TextView txtTitle = convertView.findViewById(R.id.txtTitle);
            TextView txtMinistry = convertView.findViewById(R.id.txtMinistry);
            TextView txtDate = convertView.findViewById(R.id.txtdate);

            txtTitle.setText(report.getTitle());
            txtMinistry.setText(report.getMinistry());
            txtDate.setText(report.getDate());

            TextView statusBadge = convertView.findViewById(R.id.statusBadge);
            String localizedStatus = getLocalizedStatus(report.getStatus());
            statusBadge.setText(localizedStatus);

            // تغيّير اللون حسب الحالة
            switch (report.getStatus().toLowerCase()) {
                case "resolved":
                    statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_resolved));
                    break;
                case "rejected":
                    statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_rejected));
                    break;
                case "in review":
                    statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_in_review));
                    break;
                case "pending":
                case "new":
                    statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_pending));
                    break;
                default:
                    statusBadge.setBackgroundColor(Color.GRAY);
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

