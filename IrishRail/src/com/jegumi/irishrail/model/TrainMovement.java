package com.jegumi.irishrail.model;

public class TrainMovement {

    private String trainCode;
    private String location;
    private String arrival;
    private String departure;

    public TrainMovement(String trainCode, String location, String arrival,
            String departure) {
        super();
        this.trainCode = trainCode;
        this.location = location;
        this.arrival = arrival;
        this.departure = departure;
    }

    public String getTrainCode() {
        return trainCode;
    }
    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getArrival() {
        return arrival;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }


}
