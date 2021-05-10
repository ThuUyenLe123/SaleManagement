/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.KhachHang;
import java.math.BigDecimal;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Uyen
 */
public class KhachHangTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtil.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    @DisplayName("Kiem thu chuc nang tìm khách hàng theo tên")
    public void testWithKeyWord() {
    try {
        KhachHangService s = new KhachHangService(conn);
        List<KhachHang> customers = s.getListKhachHang("tuan");

        customers.forEach(c -> {
        Assertions.assertTrue(c.getTenKhachHang().toLowerCase().contains("tuan"));
        });
        } catch (SQLException ex) {
        Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @DisplayName("Kiem thu chuc nang tìm danh sách khách hàng theo tên")
    public void testUnknownWithKeyWord() {
        try {
        KhachHangService s = new KhachHangService(conn);
        List<KhachHang> customers = s.getListKhachHang("43*&^&^GYGFUYGFUYGFHGD%$");

        Assertions.assertEquals(0, customers.size());
        } catch (SQLException ex) {
        Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
        new KhachHangService(conn).getListKhachHang(null);
        });
    }
    
    @Test
        public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
        new KhachHangService(conn).getListKhachHang("");
        });
    }
    
    
    @ParameterizedTest
    @CsvSource({",090,997,aaa,0", "uyen,,989,aaa,0", "uyen,0970,,aaa,0", 
                "uyen,0906,99,,0"})
    @DisplayName("Thêm Khách Hàng Với SDT Hoặc Name Hoặc CMND Hoặc DiaChi empty")
    @Tag("critical")
    public void  testAddCustomerNullNameUserNamePassMajor(String name, String sdt, String cmnd,
            String diachi, int diem) { 
        
        try {
            KhachHangService s = new KhachHangService(conn);
            KhachHang c = new KhachHang();
            c.setTenKhachHang(name);
            c.setSDT(sdt);
            c.setCMND(cmnd);
            c.setDiaChi(diachi);
            c.setDiem(diem);
            
            Assertions.assertFalse(s.addKhachHang(c));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    @ParameterizedTest
    @DisplayName("update-customer")
    @CsvSource({"44,uyen,09089,435,aaa,100"})
    public void testUpdateCustomer(int Id, String name, String sdt, String cmnd,
                String diachi, int diem) throws SQLException {
            
        KhachHangService s = new KhachHangService(conn);
        KhachHang c = new KhachHang();
        c.setIdKhachHangThanThiet(Id);
        c.setTenKhachHang(name);
        c.setSDT(sdt);
        c.setCMND(cmnd);
        c.setDiaChi(diachi);
        c.setDiem(diem); 
        
        Assertions.assertTrue(s.updateKhachHang(c));
    }
    
    
    @ParameterizedTest
    @DisplayName("update-customer-null-Name-or-sdt-or-cmnd-or-diachi")
    @CsvSource({"44,,090,998,aaa,0", "44,uyen,,998,aaa,0", "44,uyen,090,,aaa,0", 
                "44,uyen,090,99,,0"})
    public void testUpdateCustomerFailed(int Id, String name, String sdt, String cmnd,
                String diachi, int diem) {  
          
        try {
            KhachHangService s = new KhachHangService(conn);
            KhachHang c = new KhachHang();
            c.setIdKhachHangThanThiet(Id);
            c.setTenKhachHang(name);
            c.setSDT(sdt);
            c.setCMND(cmnd);
            c.setDiaChi(diachi);
            c.setDiem(diem); 
            Assertions.assertFalse(s.updateKhachHang(c));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }       
    
    /*@ParameterizedTest
    @DisplayName("Xóa khách hàng")
    @CsvSource({"99,false", "26,true"})
    public void testDeleteCustomer(int customerId, boolean expected) throws SQLException {
    
    KhachHang c = new KhachHang();
    c.setIdKhachHangThanThiet(customerId);
    
    KhachHangService s = new KhachHangService(conn);
    Assertions.assertEquals(expected ,s.deleleKhachHang(customerId));
    }*/
    }
    
    
    
        

        

