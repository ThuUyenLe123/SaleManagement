
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.HoaDon;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
public class HoaDonService {

    private Connection conn;

    public HoaDonService(Connection conn) {
        this.conn = conn;
    }

    public List<HoaDon> getListHoaDon() throws SQLException {
        String sql = "SELECT * FROM salemanager.hoadon;";
        PreparedStatement stm = this.conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
        List<HoaDon> Bills = new ArrayList<>();
        while (rs.next()) {
            HoaDon b = new HoaDon();
            b.setIdHoaDon(rs.getInt("idHoaDon"));
            b.setIDNhanVienBanHang(rs.getInt("IDNhanVienBanHang"));
            b.setIDKhachHangThanThiet(rs.getInt("IDKhachHangThanThiet"));
            b.setNgayLap(rs.getDate("NgayLap"));
            b.setThanhTien(rs.getBigDecimal("ThanhTien"));
            b.setVAT(rs.getDouble("VAT"));
            b.setDiemKhachHangSuDung(rs.getInt("diemKhachHangSuDung"));
            Bills.add(b);
        }
        return Bills;
    }

    public HoaDon CreateHoaDon(HoaDon hd) throws SQLException {
        int row = 0;
        if (hd.getIDKhachHangThanThiet() == 0) {
            String sql = "INSERT INTO `salemanager`.`hoadon` (`NgayLap`,"
                    + " `ThanhTien`,"
                    + " `VAT`,"
                    + " `IDNhanVienBanHang`) VALUES (?,?,?,?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setDate(1, hd.getNgayLap());
            stm.setBigDecimal(2, hd.getThanhTien());
            stm.setDouble(3, hd.getVAT());
            stm.setInt(4, hd.getIDNhanVienBanHang());
            row = stm.executeUpdate();
        } else {
            String sql = "INSERT INTO `salemanager`.`hoadon` (`NgayLap`"
                    + ", `ThanhTien`"
                    + ", `VAT`"
                    + ", `IDNhanVienBanHang`"
                    + ", `IDKhachHangThanThiet`,`diemKhachHangSuDung`) VALUES (?,?,?,?,?,?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setDate(1, hd.getNgayLap());
            stm.setBigDecimal(2, hd.getThanhTien());
            stm.setDouble(3, hd.getVAT());
            stm.setInt(4, hd.getIDNhanVienBanHang());
            stm.setInt(5, hd.getIDKhachHangThanThiet());
            stm.setInt(6, hd.getDiemKhachHangSuDung());
            row = stm.executeUpdate();
        }

        HoaDon hdNew = null;
        if (row > 0) {
            String getHoaDonNew = "SELECT * FROM salemanager.hoadon ORDER BY idHoaDon DESC LIMIT 1;";
            PreparedStatement stmNew = this.conn.prepareStatement(getHoaDonNew);
            ResultSet rs = stmNew.executeQuery();
            while (rs.next()) {
                hdNew = new HoaDon();
                hdNew.setIdHoaDon(rs.getInt("idHoaDon"));
                hdNew.setIDNhanVienBanHang(rs.getInt("IDNhanVienBanHang"));
                hdNew.setIDKhachHangThanThiet(rs.getInt("IDKhachHangThanThiet"));
                hdNew.setNgayLap(rs.getDate("NgayLap"));
                hdNew.setThanhTien(rs.getBigDecimal("ThanhTien"));
                hdNew.setVAT(rs.getDouble("VAT"));
            }
            return hdNew;
        } else {
            return hdNew;
        }
    }

    public List<HoaDon> getListHoaDonByDate(Date kw) throws SQLException {
        if (kw == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM salemanager.hoadon where NgayLap=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setDate(1, kw);
        ResultSet rs = stm.executeQuery();
        List<HoaDon> Bills = new ArrayList<>();
        while (rs.next()) {
            HoaDon b = new HoaDon();
            b.setIdHoaDon(rs.getInt("idHoaDon"));
            b.setIDNhanVienBanHang(rs.getInt("IDNhanVienBanHang"));
            b.setIDKhachHangThanThiet(rs.getInt("IDKhachHangThanThiet"));
            b.setNgayLap(rs.getDate("NgayLap"));
            b.setThanhTien(rs.getBigDecimal("ThanhTien"));
            b.setVAT(rs.getDouble("VAT"));
            Bills.add(b);
        }
        return Bills;
    }

    public boolean deleleHoaDon(int IdhoaDon) throws SQLException {
        if (IdhoaDon <= 0) {
            throw new SQLDataException();
        }
        String sql = "DELETE FROM `salemanager`.`hoadon` WHERE (`idHoaDon` =?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, IdhoaDon);
        int row = stm.executeUpdate();
        return row > 0;
    }
//

    public boolean UpdateListHoaDon(HoaDon hd) throws SQLException {
        String sql = "UPDATE `salemanager`.`hoadon` SET `NgayLap` =?"
                + ", `ThanhTien` = ?"
                + ", `VAT` = ?"
                + ", `IDNhanVienBanHang` = ?"
                + " WHERE (`idHoaDon` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setDate(1, hd.getNgayLap());
        stm.setBigDecimal(2, hd.getThanhTien());
        stm.setDouble(3, hd.getVAT());
        stm.setInt(4, hd.getIDNhanVienBanHang());
        stm.setInt(5, hd.getIdHoaDon());
        int row = stm.executeUpdate();
        return row > 0;
    }

    public List<HoaDon> getListHoaDonByMonth(int month) throws SQLException{
        int year = java.time.LocalDate.now().getYear();
        String sql = "SELECT * FROM salemanager.hoadon where month(NgayLap) = ? and year(NgayLap)=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, month);
        stm.setInt(2, year);
        ResultSet rs = stm.executeQuery();
        List<HoaDon> Bills = new ArrayList<>();
        while (rs.next()) {
            HoaDon b = new HoaDon();
            b.setIdHoaDon(rs.getInt("idHoaDon"));
            b.setIDNhanVienBanHang(rs.getInt("IDNhanVienBanHang"));
            b.setIDKhachHangThanThiet(rs.getInt("IDKhachHangThanThiet"));
            b.setNgayLap(rs.getDate("NgayLap"));
            b.setThanhTien(rs.getBigDecimal("ThanhTien"));
            b.setVAT(rs.getDouble("VAT"));
            b.setDiemKhachHangSuDung(rs.getInt("diemKhachHangSuDung"));
            Bills.add(b);
        }
        return Bills;
    }
}
