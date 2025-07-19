package com.example.goverment_system;

public class ReportFirebase {
    private   String reportId;

    public String governorate;
    public String authority;
    public String issueType;
    public String date;
    public String title;
    public String description;
    public double latitude;
    public double longitude;
    public String imageUrl;
    public String userEmail;
    public String status;
    public String statusReason;

    public ReportFirebase(){

    }


    public ReportFirebase(String reportId,String governorate, String authority, String issueType, String date,
                          String title, String description, double latitude, double longitude,
                          String imageUrl, String userEmail, String status ,String statusReason) {
        this.reportId = reportId;
        this.governorate = governorate;
        this.authority = authority;
        this.issueType = issueType;
        this.date = date;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.userEmail = userEmail;
        this.status = status;
        this.statusReason = statusReason;
    }

    public ReportFirebase(String reportId,String governorate, String authority, String issueType, String date,
                          String title, String description, double latitude, double longitude,
                          String imageUrl, String userEmail) {
        this(reportId,governorate, authority, issueType, date, title, description, latitude, longitude, imageUrl, userEmail, "Pending","");
    }
    public  String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }


}
