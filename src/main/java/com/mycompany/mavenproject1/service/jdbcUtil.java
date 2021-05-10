/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;
/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class jdbcUtil {
    static {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
             ex.printStackTrace();  
        }  
    }
        public static Connection getConn() throws SQLException {
            return DriverManager.getConnection("jdbc:mysql://localhost/salemanager", "root", "123456");
    }
}
