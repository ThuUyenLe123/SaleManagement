/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.HangHoa;
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
public class HangHoaService {

    private Connection conn;

    public HangHoaService(Connection conn) {
        this.conn = conn;
    }

    public List<HangHoa> getListHangHoa(String kw) throws SQLException {
        if (kw == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM salemanager.hanghoa WHERE tenHang like concat('%', ?, '%')";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<HangHoa> products = new ArrayList<>();
        while (rs.next()) {
            HangHoa p = new HangHoa();
            p.setIdHangHoa(rs.getInt("idHangHoa"));
            p.setTenHang(rs.getString("tenHang"));
            p.setLoaiHang(rs.getInt("loaiHangId"));
            p.setXuatXu(rs.getInt("xuatXuId"));
            p.setGiaBan(rs.getBigDecimal("giaBan"));
            p.setDonViTinh(rs.getString("donViTinh"));
            p.setSoLuong(rs.getInt("soLuong"));
            p.setNgaySX(rs.getDate("ngaySX"));
            p.setHanSD(rs.getDate("hanSD"));
            p.setGiaMua(rs.getBigDecimal("giaMua"));
            products.add(p);
        }
        return products;
    }

    public List<HangHoa> getListHangHoaConTrongKho(String kw) throws SQLException {
        if (kw == null) {
            throw new SQLDataException();
        }
        String sql = "SELECT * FROM salemanager.hanghoa WHERE tenHang like concat('%', ?, '%')";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        List<HangHoa> products = new ArrayList<>();
        while (rs.next()) {
            if (rs.getInt("soLuong") > 0) {
                HangHoa p = new HangHoa();
                p.setIdHangHoa(rs.getInt("idHangHoa"));
                p.setTenHang(rs.getString("tenHang"));
                p.setLoaiHang(rs.getInt("loaiHangId"));
                p.setXuatXu(rs.getInt("xuatXuId"));
                p.setGiaBan(rs.getBigDecimal("giaBan"));
                p.setDonViTinh(rs.getString("donViTinh"));
                p.setSoLuong(rs.getInt("soLuong"));
                p.setNgaySX(rs.getDate("ngaySX"));
                p.setHanSD(rs.getDate("hanSD"));
                products.add(p);
            }

        }
        return products;
    }

    public List<HangHoa> SeachHangHoaConLaiByIdHangHoa(int idLoaiHang) throws SQLException {
        String sql = "SELECT * FROM salemanager.hanghoa where loaiHangId=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, idLoaiHang);
        ResultSet rs = stm.executeQuery();
        List<HangHoa> products = new ArrayList<>();
        while (rs.next()) {
            if (rs.getInt("soLuong") > 0) {
                HangHoa p = new HangHoa();
                p.setIdHangHoa(rs.getInt("idHangHoa"));
                p.setTenHang(rs.getString("tenHang"));
                p.setLoaiHang(rs.getInt("loaiHangId"));
                p.setXuatXu(rs.getInt("xuatXuId"));
                p.setGiaBan(rs.getBigDecimal("giaBan"));
                p.setDonViTinh(rs.getString("donViTinh"));
                p.setSoLuong(rs.getInt("soLuong"));
                p.setNgaySX(rs.getDate("ngaySX"));
                p.setHanSD(rs.getDate("hanSD"));
                products.add(p);
            }

        }
        return products;

    }

    public boolean addHangHoa(HangHoa hh) throws SQLException {
        String sql = "INSERT INTO `salemanager`.`hanghoa` (`tenHang`, `loaiHangId`, `xuatXuId`, `ngaySX`, `hanSD`, `giaBan`, `donViTinh`, `soLuong`,`giaMua`) VALUES (?,?,?,?, ?, ?, ?, ?,?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, hh.getTenHang());
        stm.setInt(2, hh.getLoaiHang());
        stm.setInt(3, hh.getXuatXu());
        stm.setDate(4, hh.getNgaySX());
        stm.setDate(5, hh.getHanSD());
        stm.setBigDecimal(6, hh.getGiaBan());
        stm.setString(7, hh.getDonViTinh());
        stm.setInt(8, hh.getSoLuong());
        stm.setBigDecimal(9, hh.getGiaMua());
        int row = stm.executeUpdate();
        return row > 0;
    }

    public boolean updateHangHoa(HangHoa hh) throws SQLException {
        String sql = "UPDATE `salemanager`.`hanghoa` SET "
                + "`tenHang` = ?,"
                + " `loaiHangId` = ?,"
                + " `xuatXuId` = ?,"
                + " `ngaySX` = ?,"
                + " `hanSD` = ?,"
                + " `giaBan` = ?,"
                + " `donViTinh` = ?,"
                + " `soLuong` = ?,"
                + " `giaMua`=?"
                + " WHERE (`idHangHoa` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, hh.getTenHang());
        stm.setInt(2, hh.getLoaiHang());
        stm.setInt(3, hh.getXuatXu());
        stm.setDate(4, hh.getNgaySX());
        stm.setDate(5, hh.getHanSD());
        stm.setBigDecimal(6, hh.getGiaBan());
        stm.setString(7, hh.getDonViTinh());
        stm.setInt(8, hh.getSoLuong());
        stm.setBigDecimal(9, hh.getGiaMua());
        stm.setInt(10, hh.getIdHangHoa());
        int row = stm.executeUpdate();
        return row > 0;
    }

    public boolean deleleHangHoa(int hangHoaId) throws SQLException {
        String sql = "DELETE FROM `salemanager`.`hanghoa` WHERE (`idHangHoa` = ?);";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, hangHoaId);
        int row = stm.executeUpdate();
        return row > 0;
    }

    public HangHoa getHangHoaById(int idHangHoa) throws SQLException {
        if (idHangHoa <= 0) {
            throw new SQLDataException();
        }
        HangHoa p = null;
        String sql = "SELECT * FROM salemanager.hanghoa where idHangHoa=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, idHangHoa);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            p = new HangHoa();
            p.setIdHangHoa(rs.getInt("idHangHoa"));
            p.setTenHang(rs.getString("tenHang"));
            p.setLoaiHang(rs.getInt("loaiHangId"));
            p.setXuatXu(rs.getInt("xuatXuId"));
            p.setGiaBan(rs.getBigDecimal("giaBan"));
            p.setDonViTinh(rs.getString("donViTinh"));
            p.setSoLuong(rs.getInt("soLuong"));
            p.setNgaySX(rs.getDate("ngaySX"));
            p.setHanSD(rs.getDate("hanSD"));
            p.setGiaMua(rs.getBigDecimal("giaMua"));
            return p;
        }
        return p;
    }

    public boolean updateSoLuongHangHoa(int idHangHoa, int SoLuong) throws SQLException {
        if (idHangHoa <= 0 || SoLuong < 0) {
            throw new SQLDataException();
        }
        String sql = "UPDATE `salemanager`.`hanghoa` SET `soLuong` = ? WHERE (`idHangHoa` =?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, SoLuong);
        stm.setInt(2, idHangHoa);
        int row = stm.executeUpdate();
        return row > 0;
    }

}
