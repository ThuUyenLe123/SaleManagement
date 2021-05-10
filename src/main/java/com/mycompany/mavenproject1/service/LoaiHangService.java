/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import java.sql.Connection;
import com.mycompany.mavenproject1.pojo.LoaiHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiHangService {

    private Connection conn;

    public LoaiHangService(Connection conn) {
        this.conn = conn;
    }

    public LoaiHang getLoaiHangById(int id) throws SQLException {
        LoaiHang c = null;
        String sql = "SELECT * FROM salemanager.loaihang where idloaiHang=?";
        PreparedStatement stm;
        stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            c = new LoaiHang();
            c.setIdloaiHang(rs.getInt("idloaiHang"));
            c.setTenLoaiHang(rs.getString("tenLoaiHang"));
            c.setMoTa(rs.getString("moTa"));
            return c;
        } else {
            return c;
        }
    };
    public List<LoaiHang> getListLoaiHang() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM salemanager.loaihang;");
        List<LoaiHang> listLoaiHang = new ArrayList<>();
        while (r.next()) {
            LoaiHang lh = new LoaiHang();
            lh.setIdloaiHang(r.getInt("idloaiHang"));
            lh.setTenLoaiHang(r.getString("tenLoaiHang"));
            lh.setMoTa(r.getString("moTa"));
            listLoaiHang.add(lh);
        }
        return listLoaiHang;
    };
}
