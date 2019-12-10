/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author virajee
 */
public class CrimeType {

    private int crimeId;
    private String crimeType;

    public CrimeType() {
    }

    public CrimeType(int crimeId, String crimeType) {
        this.crimeId = crimeId;
        this.crimeType = crimeType;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public int getCrimeId() {
        return crimeId;
    }

    public String getCrimeType() {
        return crimeType;
    }

}
