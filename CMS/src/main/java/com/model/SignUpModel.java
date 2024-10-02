package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;

@Stateless
public class SignUpModel implements SignUpRemote {

    @Override
    public void insert(String username, String email, String password, String phno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "Syam@1608");

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO signupdata (username, email, password, phno) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, phno);
            pstmt.executeUpdate();

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(String username, String password) {
        boolean loggedIn = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "Syam@1608");

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM signupdata WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                loggedIn = true;
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return loggedIn;
    }
}