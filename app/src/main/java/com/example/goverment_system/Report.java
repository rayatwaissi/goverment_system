package com.example.goverment_system;

public class Report {
    private String reportId;
    private String title;
    private String ministry;
    private String date;
    private String status;
    private String userEmail;

    public Report() {}

    public Report(String title, String ministry, String date, String status, String userEmail) {
        this.title = title;
        this.ministry = ministry;
        this.date = date;
        this.status = status;
        this.userEmail = userEmail;
    }

    public String getReportId() {
        return reportId;
    }
    public String getTitle() {
        return title;
    }

    public String getMinistry() {
        return ministry;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
