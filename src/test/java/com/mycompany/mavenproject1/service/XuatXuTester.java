/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.XuatXu;
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
 * @author Admin
 */
public class XuatXuTester {
    private static Connection CONN;
    
    @Test
    @DisplayName("Kiểm thử số lượng  >= 3")
    public void testQuantity() {
        try {
            Connection conn = jdbcUtil.getConn();
            
            XuatXuService s = new XuatXuService(conn);
            List<XuatXu> origin = s.getListXuatXu();
            
            conn.close();
            
            Assertions.assertTrue(origin.size() >= 3);
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueName() {
        try {
            Connection conn = jdbcUtil.getConn();
            
            XuatXuService s = new XuatXuService(conn);
            List<XuatXu> cates = s.getListXuatXu();
            
            List<String> names = new ArrayList<>();
            cates.forEach(c -> names.add(c.getNoiXuatXu()));
            
            Set<String> testNames = new HashSet<>(names);
            
            conn.close();
            
            Assertions.assertEquals(names.size(), testNames.size());
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

