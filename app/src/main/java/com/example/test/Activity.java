package com.example.test;

public class Activity {
    private int activityId;
    private String startTime;
    private String endTime;
    private String introduction;
    private String classification;
    private String place;
    private String host;

    public Activity(int activityId, String startTime, String endTime, String introduction, String classification, String place, String host) {
        this.activityId = activityId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduction = introduction;
        this.classification = classification;
        this.place = place;
        this.host = host;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
