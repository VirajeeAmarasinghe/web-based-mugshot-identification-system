/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Virajee
 */
public class DAOConnect {

    private static DAOConnect daoCon = new DAOConnect();

    private DAOConnect() {

    }

    public static DAOConnect getInsatnce() {
        return daoCon;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mugshot", "root", "");
        return cn;
    }
}
