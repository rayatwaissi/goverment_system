package com.example.goverment_system;

public class ReportFirebase {

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

    public ReportFirebase() {}

    // كونستركتور كامل مع status
    public ReportFirebase(String governorate, String authority, String issueType, String date,
                          String title, String description, double latitude, double longitude,
                          String imageUrl, String userEmail, String status) {
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
    }

    // كونستركتور بدون status يعين قيمة افتراضية "جديد"
    public ReportFirebase(String governorate, String authority, String issueType, String date,
                          String title, String description, double latitude, double longitude,
                          String imageUrl, String userEmail) {
        this(governorate, authority, issueType, date, title, description, latitude, longitude, imageUrl, userEmail, "New");
    }
}
