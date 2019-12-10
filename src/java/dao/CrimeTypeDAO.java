/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.CrimeType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author virajee
 */
public class CrimeTypeDAO {

    private Connection con = null;

    public CrimeTypeDAO() {
        try {
            con = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<CrimeType> getAllCrimeTypes() {
        ArrayList<CrimeType> all = new ArrayList<>();
        CrimeType c = null;

        String query = "SELECT * FROM crime_types";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("crime_id");
                String type = rs.getString("crime_type");
                c = new CrimeType(id, type);
                all.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public String getCrimeTypeAccordinToCrimeId(int crimeID) {
        String s = "";
        CrimeType c = null;

        String query = "SELECT crime_type from crime_types where crime_id in (select crimeType_id from crime_crimetype where crime_id=?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, crimeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString("crime_type");

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public int getNxtId() {
        int maxId = 0;
        int nextCriId = 0;
        String selectSt = "select MAX(crime_id) from crime_types";

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

    public int insert(CrimeType c) {
        int result = 0;
        String query = "";

        try {
            query = "INSERT INTO crime_types(crime_id,crime_type)VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getCrimeId());
            ps.setString(2, c.getCrimeType());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
