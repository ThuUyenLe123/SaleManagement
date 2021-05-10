/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.HangHoa;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author Admin
 */
public class HangHoaTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtil.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    @Tag("Search-product-name")
    public void testWithKeyWord() {
        try {
            HangHoaService s = new HangHoaService(conn);
            List<HangHoa> products = s.getListHangHoa("ga");
            
            products.forEach(p -> {
                Assertions.assertTrue(p.getTenHang().toLowerCase().contains("ga"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @Tag("Search-product-name")
    public void testWithUnknownKeyWord() {
        try {
            HangHoaService s = new HangHoaService(conn);
            List<HangHoa> products = s.getListHangHoa("agdjhsgdjhsa232432");
            
            Assertions.assertEquals(0, products.size());
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new HangHoaService(conn).getListHangHoa(null);
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new HangHoaService(conn).getListHangHoa("");
        });
    }
    
    @Test
    @Tag("add-product")
    public void tesAddProductWithInvalidOrigin() {
        try {
            HangHoa p = new HangHoa();
            p.setTenHang("Bia Tiger");
            p.setGiaBan(new BigDecimal(15000));
            p.setGiaMua(new BigDecimal(10000));
            p.setXuatXu(99);
            p.setSoLuong(18);
            p.setDonViTinh("chai");
            p.setLoaiHang(3);
            
            HangHoaService s = new HangHoaService(conn);
            Assertions.assertFalse(s.addHangHoa(p));
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @Tag("add-product")
    public void tesAddProductWithInvalidCate() {
        try {
            HangHoa p = new HangHoa();
            p.setTenHang("Bia Tiger");
            p.setGiaBan(new BigDecimal(15000));
            p.setGiaMua(new BigDecimal(10000));
            p.setXuatXu(1);
            p.setSoLuong(18);
            p.setDonViTinh("chai");
            p.setLoaiHang(77);
            
            HangHoaService s = new HangHoaService(conn);
            Assertions.assertFalse(s.addHangHoa(p));
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @ParameterizedTest
    @CsvSource({", 10000, 15000, 100, 2, 2, chai", 
         "Bia Tiger,10000, 15000, 100, 2 , 2,"})
    @DisplayName("Thêm Khách Hàng Với Name Hoặc Unit empty")
    @Tag("critical")
    public void  testAddProductNullNameUnit(String name, BigDecimal importPrice,
            BigDecimal price, int count,  int cateId, int originId, String unit) { 
        
        try {
            HangHoaService s = new HangHoaService(conn);
            
            HangHoa p = new HangHoa();
            p.setTenHang(name);
            p.setGiaMua(importPrice);
            p.setGiaBan(price);
            p.setDonViTinh(unit);
            p.setSoLuong(count);
            p.setLoaiHang(cateId);
            p.setXuatXu(originId);
            
            Assertions.assertFalse(s.addHangHoa(p));
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    
    /*@ParameterizedTest
    @DisplayName("update-product")
    @CsvSource({"61,Bia Hanoi, 12000, 17000, 100, 3, 1, chai"})
    public void testUpdateProduct(int Id, String name, BigDecimal importPrice,
    BigDecimal price, int count,  int cateId, int originId, String unit)
    throws SQLException {
    
    HangHoaService s = new HangHoaService(conn);
    
    HangHoa p = new HangHoa();
    p.setIdHangHoa(Id);
    p.setTenHang(name);
    p.setGiaMua(importPrice);
    p.setGiaBan(price);
    p.setDonViTinh(unit);
    p.setSoLuong(count);
    p.setLoaiHang(cateId);
    p.setXuatXu(originId);
    
    Assertions.assertTrue(s.updateHangHoa(p));
    }*/
    
    
    @ParameterizedTest
    @DisplayName("update-product-null-Name-or-unit")
    @CsvSource({"315,, 10000, 13000, 100, 2, 2,chai ",
                "315,Bia SaiGon,10000 ,13000 ,100 , 2, 2, "})
    public void testUpdateProductFailed(int Id, String name, BigDecimal importPrice,
            BigDecimal price, int count,  int cateId, int originId, String unit) {  
          
        try {
            HangHoaService s = new HangHoaService(conn);
            HangHoa p = new HangHoa();
            p.setIdHangHoa(Id);
            p.setTenHang(name);
            p.setGiaMua(importPrice);
            p.setGiaBan(price);
            p.setDonViTinh(unit);
            p.setSoLuong(count);
            p.setLoaiHang(cateId);
            p.setXuatXu(originId);
            
            Assertions.assertFalse(s.updateHangHoa(p));
            
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }       
    
    
    /*@ParameterizedTest
    @CsvSource({"99,false", "18,true"})
    public void testDeleteProduct(int productId, boolean expected)
    throws SQLException {
    
    HangHoa p = new HangHoa();
    p.setIdHangHoa(productId);
    
    HangHoaService s = new HangHoaService(conn);
    Assertions.assertEquals(expected ,s.deleleHangHoa(productId));
    
    }*/
    
    
    
}
