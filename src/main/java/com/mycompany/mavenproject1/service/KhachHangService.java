/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangService {

    private Connection conn;

    public KhachHangService(Connection conn) {
        this.conn = conn;
    }

    public KhachHangService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<KhachHang> getListKhachHang(String kw) throws SQLException {
        if (kw == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM salemanager.khachhangthanthiet WHERE TenKhachHang like concat('%', ?, '%')";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<KhachHang> listkhachhang = new ArrayList<>();
        while (rs.next()) {
            KhachHang c = new KhachHang();
            c.setIdKhachHangThanThiet(rs.getInt("idKhachHangThanThiet"));
            c.setTenKhachHang(rs.getString("TenKhachHang"));
            c.setSDT(rs.getString("SDT"));
            c.setDiaChi(rs.getString("DiaChi"));
            c.setDiem(rs.getInt("Diem"));
            c.setCMND(rs.getString("CMND"));
            listkhachhang.add(c);
        }
        return listkhachhang;
    }

    public boolean deleleKhachHang(int id) throws SQLException {
        String sql = "DELETE FROM `salemanager`.`khachhangthanthiet` WHERE (`idKhachHangThanThiet` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        int row = stm.executeUpdate();
        return row > 0;
    }

    public boolean updateKhachHang(KhachHang kh) throws SQLException {
        String sql = "UPDATE `salemanager`.`khachhangthanthiet` SET "
                + "`TenKhachHang` = ?, "
                + "`SDT` = ?,"
                + " `DiaChi` = ?,"
                + " `Diem` = ?,"
                + " `CMND` = ? WHERE (`idKhachHangThanThiet` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kh.getTenKhachHang());
        stm.setString(2, kh.getSDT());
        stm.setString(3, kh.getDiaChi());
        stm.setInt(4, kh.getDiem());
        stm.setString(5, kh.getCMND());
        stm.setInt(6, kh.getIdKhachHangThanThiet());
        int row = stm.executeUpdate();
        return row > 0;
    }

    public KhachHang findKhachHangByCMND(String kw) throws SQLException {

        KhachHang kh = null;
        String sql = "SELECT * FROM salemanager.khachhangthanthiet where CMND=?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            return kh;
        } else {
            kh = new KhachHang();
            kh.setIdKhachHangThanThiet(rs.getInt("idKhachHangThanThiet"));
            kh.setTenKhachHang(rs.getString("TenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setDiem(rs.getInt("Diem"));
            kh.setCMND(rs.getString("CMND"));
            return kh;
        }

    }

    ;
     public KhachHang findKhachHangByID(int kw) throws SQLException {

        KhachHang kh = null;
        String sql = "SELECT * FROM salemanager.khachhangthanthiet where idKhachHangThanThiet=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, kw);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            return kh;
        } else {
            kh = new KhachHang();
            kh.setIdKhachHangThanThiet(rs.getInt("idKhachHangThanThiet"));
            kh.setTenKhachHang(rs.getString("TenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setDiem(rs.getInt("Diem"));
            kh.setCMND(rs.getString("CMND"));
            return kh;
        }

    }

    ;
       public KhachHang findKhachHangBySDT(String kw) throws SQLException {

        KhachHang kh = null;
        String sql = "SELECT * FROM salemanager.khachhangthanthiet where SDT=?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            return kh;
        } else {
            kh = new KhachHang();
            kh.setIdKhachHangThanThiet(rs.getInt("idKhachHangThanThiet"));
            kh.setTenKhachHang(rs.getString("TenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setDiem(rs.getInt("Diem"));
            kh.setCMND(rs.getString("CMND"));
            return kh;
        }

    }

    ;
    public KhachHang findKhachHangBySDTAndCMND(String sdt, String cmnd) throws SQLException {

        KhachHang kh = null;
        String sql = "SELECT * FROM salemanager.khachhangthanthiet where SDT=? and CMND=?;";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, sdt);
        stm.setString(2, cmnd);
        ResultSet rs = stm.executeQuery();
        if (!rs.next()) {
            return kh;
        } else {
            kh = new KhachHang();
            kh.setIdKhachHangThanThiet(rs.getInt("idKhachHangThanThiet"));
            kh.setTenKhachHang(rs.getString("TenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setDiem(rs.getInt("Diem"));
            kh.setCMND(rs.getString("CMND"));
            return kh;
        }

    }

    ;
    public boolean addKhachHang(KhachHang kh) throws SQLException {
        String sql = "INSERT INTO `salemanager`.`khachhangthanthiet` "
                + "(`TenKhachHang`, `SDT`, `DiaChi`, `Diem`, `CMND`)"
                + " VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kh.getTenKhachHang());
        stm.setString(2, kh.getSDT());
        stm.setString(3, kh.getDiaChi());
        stm.setInt(4, kh.getDiem());
        stm.setString(5, kh.getCMND());
        int row = stm.executeUpdate();
        return row > 0;
    }
}
