/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.NghiepVu;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Uyen
 */
public class NghiepVuTester {
    private static Connection CONN;
    
    @Test
    @DisplayName("Kiểm thử số lượng nghiệp vụ >= 2")
    public void testQuantity() {
        try {
            Connection conn = jdbcUtil.getConn();
            
            NghiepVuService s = new NghiepVuService(conn);
            List<NghiepVu> cates = s.getNghiepVu();
            
            conn.close();
            
            Assertions.assertTrue(cates.size() >= 2);
        } catch (SQLException ex) {
            Logger.getLogger(NghiepVuTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueName() {
        try {
            Connection conn = jdbcUtil.getConn();
            
            NghiepVuService s = new NghiepVuService(conn);
            List<NghiepVu> cates = s.getNghiepVu();
            
            List<String> names = new ArrayList<>();
            cates.forEach(c -> names.add(c.getTenNghiepVu()));
            
            Set<String> testNames = new HashSet<>(names);
            
            conn.close();
            
            Assertions.assertEquals(names.size(), testNames.size());
        } catch (SQLException ex) {
            Logger.getLogger(NghiepVuTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
