package com.jegumi.irishrail.model;

public class Station {

    private String Desc;
    private String Alias;
    private long Longitude;
    private long Latitude;
    private String Code;
    private int id;

    public Station() {
        super();
    }

    public Station(String desc, String alias, long longitude, long latitude, String code, int id) {
        super();
        Desc = desc;
        Alias = alias;
        Longitude = longitude;
        Latitude = latitude;
        Code = code;
        this.id = id;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public long getLongitude() {
        return Longitude;
    }

    public void setLongitude(long longitude) {
        Longitude = longitude;
    }

    public long getLatitude() {
        return Latitude;
    }

    public void setLatitude(long latitude) {
        Latitude = latitude;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
