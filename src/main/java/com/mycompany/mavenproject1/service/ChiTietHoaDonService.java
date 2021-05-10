/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.ChiTietHoaDon;
import java.math.BigDecimal;
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
public class ChiTietHoaDonService {

    private Connection conn;

    public ChiTietHoaDonService(Connection conn) {
        this.conn = conn;
    }

    public List<ChiTietHoaDon> getListChiTietById(int idHoaDon) throws SQLException {
        if (idHoaDon <= 0) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM salemanager.chitiethoadon where IDHoaDon=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, idHoaDon);
        ResultSet rs = stm.executeQuery();
        List<ChiTietHoaDon> Bill = new ArrayList<>();
        while (rs.next()) {
            ChiTietHoaDon b = new ChiTietHoaDon();
            b.setIdHoaDon(rs.getInt("IDHoaDon"));
            b.setIdHangHoa(rs.getInt("IDHangHoa"));
            b.setSoLuong(rs.getInt("SoLuong"));
            b.setDonGia(rs.getBigDecimal("DonGia"));
            Bill.add(b);
        }
        return Bill;
    }

    public boolean deleleHoaDon(int iDHoaDon, int iDHangHoa) throws SQLException {
        if (iDHoaDon <= 0 || iDHangHoa <= 0) {
            throw new SQLDataException();
        }
        String sql = "DELETE FROM `salemanager`.`chitiethoadon` WHERE"
                + " (`IDHoaDon` = ?)"
                + " and"
                + " (`IDHangHoa` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, iDHoaDon);
        stm.setInt(2, iDHangHoa);
        int row = stm.executeUpdate();
        return row > 0;
    }

    public boolean AddChiTietHoaDon(ChiTietHoaDon b) throws SQLException {
        String sql = "INSERT INTO `salemanager`.`chitiethoadon` (`IDHoaDon`,"
                + " `SoLuong`,"
                + " `IDHangHoa`,"
                + " `DonGia`)"
                + " VALUES (?, ?, ?, ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, b.getIdHoaDon());
        stm.setInt(2, b.getSoLuong());
        stm.setInt(3, b.getIdHangHoa());
        stm.setBigDecimal(4, b.getDonGia());
        int row = stm.executeUpdate();
        return row > 0;
    }

//    public boolean UpdateListHoaDon(HoaDon hd) throws SQLException {
//        String sql = "UPDATE `salemanager`.`hoadon` SET `NgayLap` =?"
//                + ", `ThanhTien` = ?"
//                + ", `VAT` = ?"
//                + ", `IDNhanVienBanHang` = ?"
//                + ", `IDKhachHangThanThiet` = ? WHERE (`idHoaDon` = ?);";
//        PreparedStatement stm = this.conn.prepareStatement(sql);
//        stm.setDate(1, hd.getNgayLap());
//        stm.setBigDecimal(2, hd.getThanhTien());
//        stm.setDouble(3, hd.getVAT());
//        stm.setInt(4, hd.getIDNhanVienBanHang());
//        stm.setInt(5, hd.getIDKhachHangThanThiet());
//        stm.setInt(6, hd.getIdHoaDon());
//        int row = stm.executeUpdate();
//        return row > 0;
//    } 
}
