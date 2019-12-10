/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author virajee
 */
public class Criminal {

    private int criminalID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date birthDay;
    private String nic;
    private String address;
    private String nickName;
    private InputStream imageStream;

    public Criminal() {
    }

    public Criminal(int criminalID, String firstName, String middleName, String lastName, String gender, Date birthDay, String nic, String address, String nickName, InputStream imageStream) {
        this.criminalID = criminalID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.nic = nic;
        this.address = address;
        this.nickName = nickName;
        this.imageStream = imageStream;
    }

    public Criminal(int criminalID, String firstName, String middleName, String lastName, String gender, Date birthDay, String nic, String address, String nickName) {
        this.criminalID = criminalID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.nic = nic;
        this.address = address;
        this.nickName = nickName;
    }

    public Criminal(String firstName, String middleName, String lastName, String gender, Date birthDay, String nic, String address, String nickName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.nic = nic;
        this.address = address;
        this.nickName = nickName;

    }

    public void setCriminalID(int criminalID) {
        this.criminalID = criminalID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public int getCriminalID() {
        return criminalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getNic() {
        return nic;
    }

    public String getAddress() {
        return address;
    }

    public String getNickName() {
        return nickName;
    }

    public InputStream getImageStream() {
        return imageStream;
    }

}
