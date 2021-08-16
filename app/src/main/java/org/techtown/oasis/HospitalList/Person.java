package org.techtown.oasis.HospitalList;

public class Person {
    private int imageResId;
    private String name;
    private String hospitalName;
    private double distance;
    private String waitingTime;

    public Person(int imageResId,String name, String hospitalName, double distance, String waitingTime) {
        this.imageResId = imageResId;
        this.name = name;
        this.hospitalName = hospitalName;
        this.distance = distance;
        this.waitingTime = waitingTime;
    }

    public int getImageResId() { return imageResId; }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime() {
        this.waitingTime = waitingTime;
    }
}
