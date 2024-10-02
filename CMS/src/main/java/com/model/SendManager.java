package com.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class SendManager {
	private static final double COST_PER_KG=33.0;

    public List<SendBean> readData(String username) throws Exception {
    	 List<SendBean> L = new ArrayList<>();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "Syam@1608");
                PreparedStatement ps = con.prepareStatement("SELECT* FROM sendpackage where sname=? OR rname=?");
                ps.setString(1, username);
                ps.setString(2, username);
                ResultSet rs = ps.executeQuery();
               

                while (rs.next()) {
                    SendBean sb = new SendBean();
                    sb.setSname(rs.getString(2));                 
                    sb.setRname(rs.getString(7));
                    sb.setCtype(rs.getString(12));
                    sb.setCweight(rs.getDouble(13));
                    sb.setCost(rs.getDouble(14));
                    L.add(sb);
                }
       
        return L;
            }
}

