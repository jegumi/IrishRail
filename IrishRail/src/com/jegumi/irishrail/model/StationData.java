package com.jegumi.irishrail.model;

public class StationData {

    private String stationName;
    private String origin;
    private String destination;
    private String trainCode;
    private String lastLocation;
    private String late;
    private String arrival;
    private String status;
    private TrainMovement trainMovement;

    public StationData(String stationName, String origin, String destination, String trainCode,
            String lastLocation, String late, String arrival, String status) {
        super();
        this.stationName = stationName;
        this.origin = origin;
        this.destination = destination;
        this.trainCode = trainCode;
        this.lastLocation = lastLocation;
        this.late = late;
        this.arrival = arrival;
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TrainMovement getTrainMovement() {
        return trainMovement;
    }

    public void setTrainMovement(TrainMovement trainMovement) {
        this.trainMovement = trainMovement;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
