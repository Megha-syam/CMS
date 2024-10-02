package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;

@Stateless
public class SendModel implements SendRemote {
	private static final double COST_PER_KG=33.0;

    @Override
    public void insert(String sname, String snumber, String saddress, String spincode, String scity,
                       String rname, String rnumber, String raddress, String rpincode, String rcity,
                       String ctype, double cweight) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "Syam@1608");

            String query = "INSERT INTO sendpackage (sname, snumber, saddress, spincode, scity, " +
                           "rname, rnumber, raddress, rpincode, rcity, ctype, cweight,cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, sname);
            pstmt.setString(2, snumber);
            pstmt.setString(3, saddress);
            pstmt.setString(4, spincode);
            pstmt.setString(5, scity);
            pstmt.setString(6, rname);
            pstmt.setString(7, rnumber);
            pstmt.setString(8, raddress);
            pstmt.setString(9, rpincode);
            pstmt.setString(10, rcity);
            pstmt.setString(11, ctype);
            pstmt.setDouble(12, cweight);
            double cost=calculateCost(cweight);
            pstmt.setDouble(13, cost);

            pstmt.executeUpdate();

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public double calculateCost(double weight) {
    	return weight*COST_PER_KG;
    }
}