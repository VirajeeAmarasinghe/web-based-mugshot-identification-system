/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author virajee
 */
public class LoginDAO {

    private Connection con = null;

    public LoginDAO() {
        try {
            con = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public Login getLoginData(String email) {
        Login l = null;
        String selectSt = "select * from login where email=?";

        try {
            PreparedStatement ps = con.prepareStatement(selectSt);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("user_name");
                String pass = rs.getString("password");
                l = new Login(email, userName, pass);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

    public boolean alreadyHasEmail(String email) {
        boolean result = false;

        String selectSt = "select * from login where email=?";
        try {
            PreparedStatement ps = con.prepareStatement(selectSt);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public boolean alreadyHasUsername(String user) {
        boolean result = false;

        String selectSt = "select * from login where user_name=?";
        try {
            PreparedStatement ps = con.prepareStatement(selectSt);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public int update(Object ob) {
        int result = 0;
        Login l = (Login) ob;

        String query = "UPDATE login SET password=? where email=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, l.getPass());
            ps.setString(2, l.getEmail());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(Object ob) {
        int result = 0;
        Login l = (Login) ob;

        String query = "INSERT INTO login(email,user_name,password)VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, l.getEmail());
            ps.setString(2, l.getUserName());
            ps.setString(3, l.getPass());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
