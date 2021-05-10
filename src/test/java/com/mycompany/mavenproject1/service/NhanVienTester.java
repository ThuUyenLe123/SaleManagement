/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.NhanVien;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;
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
public class NhanVienTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtil.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Test
    @DisplayName("Tìm thấy List Nhân Viên theo kw")
    public void testWithKeyWord() {
        try {
            NhanVienService s = new NhanVienService(conn);
            List<NhanVien> employees = s.getListNhanVien("tuan");
            
            employees.forEach(p -> {
                Assertions.assertTrue(p.getTaiKhoan().toLowerCase().contains("tuan"));
            });
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    @DisplayName("Tìm Nhân Viên theo kw")
    public void testUnknownWithKeyWord() {
        try {
            NhanVienService s = new NhanVienService(conn);
            List<NhanVien> employees = s.getListNhanVien("43*&^&^GYGFUY");
            
            Assertions.assertEquals(0, employees.size());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testException() {
        Assertions.assertThrows(SQLDataException.class, () -> {
            new NhanVienService(conn).getListNhanVien(null);
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new NhanVienService(conn).getListNhanVien("");
        });
    }
    
    
    @ParameterizedTest
    @CsvSource({"'',anan12, 11, 1", "An,'', 11, 1", "An,anan12, '', 1", 
                "An,anan12, 11, 0"})
    @DisplayName("Thêm Nhân Viên Với Username Hoặc Name Hoặc Pass Hoặc Major empty")
    @Tag("critical")
    public void  testAddEmployeeNullNameUserNamePassMajor(String name,
            String username, String pass,int majorId) throws SQLException  { 
        
            NhanVienService s = new NhanVienService(conn);
            NhanVien e = new NhanVien();
            e.setTenNhanVien(name);
            e.setTaiKhoan(username);
            e.setMatKhau(pass);
            e.setNghiepVu(majorId);
            if (!name.isEmpty() && !username.isEmpty() && !pass.isEmpty() && majorId != 0){
                Assertions.assertFalse(s.addNhanVien(e));
        }
            
       
            
        
    }
    
    /*@ParameterizedTest
    @CsvSource({"Nam,nam12, 11, 2"})
    @DisplayName("Thêm Nhân Viên Thành Công")
    public void testAddEmployeeSuccess(String name, String username, String pass,
    int majorId) throws SQLException {
    NhanVienService s = new NhanVienService(conn);
    
    NhanVien e = new NhanVien();
    e.setTenNhanVien(name);
    e.setTaiKhoan(username);
    e.setMatKhau(pass);
    e.setNghiepVu(majorId);
    if (s.findAccount(username).toString() != e.getTaiKhoan()){
    
    Assertions.assertTrue(s.addNhanVien(e));
    }
    
    }*/
    
    
    
    
    
    
    @ParameterizedTest
    @DisplayName("update-employee")
    @CsvSource({"12,Uyên,uyen26,99,1"})
    public void testUpdateEmployee(int Id, String name,String username, String pass,
                int majorId) throws SQLException {
        
        NhanVienService s = new NhanVienService(conn);
            
        NhanVien e = new NhanVien(); 
        
        e.setMaNhanVien(Id);
        e.setTenNhanVien(name);
        e.setTaiKhoan(username);
        e.setMatKhau(pass);
        e.setNghiepVu(majorId); 
        
        Assertions.assertTrue(s.updateNhanVien(e));
    }
    
    
    @ParameterizedTest
    @DisplayName("update-employee-null-Name-or-username-or-pass")
    @CsvSource({"12,Uyên,,99,1","12,Uyên,uyen27,,1","12,,uyen28,99,1"})
    public void testUpdateEmployeeFailed(int Id, String name, String username, 
            String pass, int majorId) {  
          
        try {
            NhanVienService s = new NhanVienService(conn);
            NhanVien e = new NhanVien();
            e.setMaNhanVien(Id);
            
            
            e.setTenNhanVien(name);
            e.setTaiKhoan(username);
            e.setMatKhau(pass);
            e.setNghiepVu(majorId);
            Assertions.assertFalse(s.updateNhanVien(e));
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }        
           
    
    /*@ParameterizedTest
    @CsvSource({"99,false", "44,true"})
    @DisplayName("Xóa Nhân Viên")
    public void testDeleteEmployee(int employeeId, boolean expected)
    throws SQLException {
    
    NhanVien e = new NhanVien();
    e.setMaNhanVien(employeeId);
    
    NhanVienService s = new NhanVienService(conn);
    Assertions.assertEquals(expected ,s.deleleNhanVien(employeeId));
    
    }*/
}
