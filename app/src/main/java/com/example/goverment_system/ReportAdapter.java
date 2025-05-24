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
        Report report=reportList.get(position);

        /*

       if (convertView == null) معناها:

"إذا لم يكن هناك عنصر عرض (View) قديم جاهز، أنشئ واحدًا جديدًا من التصميم الجاهز.*/
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_report, parent, false);
        }

        TextView txtTitle=convertView.findViewById(R.id.txtTitle);
        TextView txtMinistry=convertView.findViewById(R.id.txtMinistry);
        TextView txtDate=convertView.findViewById(R.id.txtdate);

        txtTitle.setText(report.getTitle());
        txtMinistry.setText(report.getMinistry());
        txtDate.setText(report.getDate());

        TextView statusBadge = convertView.findViewById(R.id.statusBadge);
        statusBadge.setText(report.getStatus());

        // تغيير اللون حسب الحالة
        switch (report.getStatus()) {
            case "Resolved":
                statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_resolved));
                break;
            case "Rejected":
                statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_rejected));
                break;
            case "In Review":
                statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_in_review));
                break;
            case "Pending":
                statusBadge.setBackgroundColor(ContextCompat.getColor(context, R.color.status_pending));
                break;
            default:
                statusBadge.setBackgroundColor(Color.GRAY);
        }
        return convertView;
    }
}