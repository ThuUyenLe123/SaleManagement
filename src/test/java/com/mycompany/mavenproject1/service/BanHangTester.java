/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.HangHoa;
import com.mycompany.mavenproject1.service.ChiTietHoaDonService;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Uyen
 */
public class BanHangTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtil.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BanHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Test
    @Tag("Search-product-name")
    public void testSearchWithKeyWord() {
        try {
            HangHoaService s = new HangHoaService(conn);
            
            List<HangHoa> products = s.getListHangHoaConTrongKho("Coca");
            products.forEach(p -> {
                Assertions.assertTrue(p.getTenHang().toLowerCase().contains("Coca".toLowerCase()) );
            });
        } catch (SQLException ex) {
            Logger.getLogger(BanHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @Tag("Search-product-name")
    public void testSearchWithUnknownKeyWord() {
        try {
            HangHoaService s = new HangHoaService(conn);
            List<HangHoa> products = s.getListHangHoaConTrongKho("agdjhsgdjhsa232432");
            
            Assertions.assertEquals(0, products.size());
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    @Tag("Search-product-CateId")
    public void testSearchCateId() {
        try {
            HangHoaService s = new HangHoaService(conn);
            
            List<HangHoa> products = s.SeachHangHoaConLaiByIdHangHoa(2);
            products.forEach(p -> {
                Assertions.assertTrue(p.getLoaiHang()== 2);
            });
        } catch (SQLException ex) {
            Logger.getLogger(BanHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new HangHoaService(conn).getListHangHoaConTrongKho(null);
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new HangHoaService(conn).getListHangHoaConTrongKho("");
        });
    }
    
    
    
}