/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author virajee
 */
public class Crime {

    private int crime_id;
    private String description;
    private Date crimeDate;
    private Time crimeTime;
    private int[] criminalID;
    private int[] crimeType;
    private int oneCriminalID;
    private int typeId;

    public Crime() {
    }

    public Crime(int crime_id, String description, Date crimeDate, Time crimeTime, int[] criminalID, int[] crimeType, int oneCriminalID, int typeId) {
        this.crime_id = crime_id;
        this.description = description;
        this.crimeDate = crimeDate;
        this.crimeTime = crimeTime;
        this.criminalID = criminalID;
        this.crimeType = crimeType;
        this.oneCriminalID = oneCriminalID;
        this.typeId = typeId;
    }

    public Crime(String description, Date crimeDate, Time crimeTime) {
        this.description = description;
        this.crimeDate = crimeDate;
        this.crimeTime = crimeTime;
    }

    public Crime(int crime_id, String description, Date crimeDate, Time crimeTime) {
        this.crime_id = crime_id;
        this.description = description;
        this.crimeDate = crimeDate;
        this.crimeTime = crimeTime;
    }

    public Crime(int crime_id, int[] criminalID) {
        this.crime_id = crime_id;
        this.criminalID = criminalID;
    }

    public Crime(int crime_id, int oneCriminalID) {
        this.crime_id = crime_id;
        this.oneCriminalID = oneCriminalID;
    }

    public Crime(int crime_id, int typeId, String text) {
        this.crime_id = crime_id;
        this.typeId = typeId;
        text = "differ the constructors";
    }

    public void setCrime_id(int crime_id) {
        this.crime_id = crime_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCrimeDate(Date crimeDate) {
        this.crimeDate = crimeDate;
    }

    public void setCrimeTime(Time crimeTime) {
        this.crimeTime = crimeTime;
    }

    public void setCriminalID(int[] criminalID) {
        this.criminalID = criminalID;
    }

    public void setCrimeType(int[] crimeType) {
        this.crimeType = crimeType;
    }

    public void setOneCriminalID(int oneCriminalID) {
        this.oneCriminalID = oneCriminalID;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCrime_id() {
        return crime_id;
    }

    public String getDescription() {
        return description;
    }

    public Date getCrimeDate() {
        return crimeDate;
    }

    public Time getCrimeTime() {
        return crimeTime;
    }

    public int[] getCriminalID() {
        return criminalID;
    }

    public int[] getCrimeType() {
        return crimeType;
    }

    public int getOneCriminalID() {
        return oneCriminalID;
    }

    public int getTypeId() {
        return typeId;
    }

}
