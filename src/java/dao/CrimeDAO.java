/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Crime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author virajee
 */
public class CrimeDAO {

    private Connection con = null;

    public CrimeDAO() {
        try {
            con = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public int getNxtId() {
        int maxId = 0;
        int nextCriId = 0;
        String selectSt = "select MAX(crime_id) from crime_record";

        try {
            PreparedStatement ps = con.prepareStatement(selectSt);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt(1);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
            return 0;
        }

        nextCriId = maxId + 1;
        return nextCriId;
    }

    public ArrayList<Crime> getCrime(int criminalID) {
        ArrayList<Crime> c = new ArrayList<>();
        String selectSt = "select * from crime_record where crime_id in(select crime_id from crime_criminal where criminal_id=?)";
        try {
            PreparedStatement ps = con.prepareStatement(selectSt);
            ps.setInt(1, criminalID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cId = rs.getInt("crime_id");
                String des = rs.getString("description");
                Date date = rs.getDate("crime_date");
                Time time = rs.getTime("crime_time");
                Crime crime = new Crime(cId, des, date, time);
                c.add(crime);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public int insertIntoCrimeRecordTable(Crime c) {
        int result = 0;
        String query = "";

        try {
            query = "INSERT INTO crime_record(description,crime_date,crime_time)VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, c.getDescription());
            ps.setDate(2, c.getCrimeDate());
            ps.setTime(3, c.getCrimeTime());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertIntoCrimeCriminalTable(Crime c) {
        int result = 0;
        String query = "";

        try {
            query = "INSERT INTO crime_criminal(crime_id,criminal_id)VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getCrime_id());
            ps.setInt(2, c.getOneCriminalID());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertIntoCrimeCrimeTypeTable(Crime c) {
        int result = 0;
        String query = "";

        try {
            query = "INSERT INTO crime_crimeType(crime_id,crimeType_id)VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getCrime_id());
            ps.setInt(2, c.getTypeId());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteFromCrimeRecord(int id) {
        int result = 0;
        String query = "DELETE FROM crime_record WHERE crime_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteFromCrimeCriminal(int id) {
        int result = 0;
        String query = "DELETE FROM crime_criminal WHERE crime_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteFromCrimeCrimeType(int id) {
        int result = 0;
        String query = "DELETE FROM crime_crimeType WHERE crime_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
