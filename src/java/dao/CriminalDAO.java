/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Criminal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author virajee
 */
public class CriminalDAO {

    private Connection con = null;

    public CriminalDAO() {
        try {
            con = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public int getNxtId() {
        int maxId = 0;
        int nextCriId = 0;
        String selectSt = "select MAX(cri_id) from criminal";

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

    public int insert(Criminal c) {
        int result = 0;
        String query = "";

        try {
            query = "INSERT INTO criminal(cri_id,f_name,m_name,l_name,gender,dob,nic,address,nick_name)VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getCriminalID());
            ps.setString(2, c.getFirstName());
            ps.setString(3, c.getMiddleName());
            ps.setString(4, c.getLastName());
            ps.setString(5, c.getGender());
            ps.setDate(6, c.getBirthDay());
            ps.setString(7, c.getNic());
            ps.setString(8, c.getAddress());
            ps.setString(9, c.getNickName());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Criminal search(int criminalID) {
        Criminal c = null;
        InputStream in = null;

        String query = "SELECT * FROM criminal WHERE cri_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, criminalID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fName = rs.getString("f_name");
                String mName = rs.getString("m_name");
                String lName = rs.getString("l_name");
                String gender = rs.getString("gender");
                Date db = rs.getDate("dob");
                String nic = rs.getString("nic");
                String address = rs.getString("address");
                String nickName = rs.getString("nick_name");
                in = rs.getBinaryStream(10);

                c = new Criminal(criminalID, fName, mName, lName, gender, db, nic, address, nickName, in);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public Set<Integer> getAllCriminalID() {
        Set<Integer> ids = new LinkedHashSet<>();

        String query = "SELECT cri_id FROM criminal";

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("cri_id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public Set<Integer> getAllCriminalIDsAccordingToCrimeType(int crime_type) {
        Set<Integer> ids = new LinkedHashSet<>();

        String query = "SELECT criminal_id FROM crime_criminal where crime_id in(SELECT crime_id FROM crime_crimetype where crimeType_id=?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, crime_type);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("criminal_id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public int updateImage(String saveFile, int cID) {
        int result = 0;

        String query = "UPDATE criminal SET image=? where cri_id=?";

        FileInputStream fis;
        try {
            File f = new File(saveFile);
            fis = new FileInputStream(f);
            PreparedStatement ps = con.prepareStatement(query);

            ps.setBinaryStream(1, (InputStream) fis, (int) (f.length()));
            ps.setInt(2, cID);
            result = ps.executeUpdate();
            ps.close();
        } catch (FileNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }
}
