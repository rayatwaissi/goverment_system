package com.example.goverment_system;

public class Report {
    private  String title ,ministry , date ,status;

    public  Report(){};

    public Report(String title,String ministry,String date, String status){
        this.title=title;
        this.ministry=ministry;
        this.date=date;
        this.status=status;    }

    public String getTitle() {
        return title;
    }
    public String getMinistry(){
        return ministry;
    }

    public String getDate(){
        return date;
    }
    public String getStatus(){
        return status;
    }
}
