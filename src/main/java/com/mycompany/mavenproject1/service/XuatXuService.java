/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.pojo.XuatXu;
import java.sql.Connection;
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
public class XuatXuService {
      private Connection conn;
    public XuatXuService(Connection conn){
        this.conn = conn;
    }
      public XuatXu getXuatXuById(int id) throws SQLException {
        XuatXu xx = null;
        String sql = "SELECT * FROM salemanager.xuatxuhanghoa where idxuatxuhanghoa=?";
        PreparedStatement stm;
        stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            xx = new XuatXu();
            xx.setIdXuatXu(rs.getInt("idxuatxuhanghoa"));
            xx.setNoiXuatXu(rs.getString("noiXuatXu"));
            xx.setMoTa(rs.getString("moTa"));
            return xx;
        } else {
            return xx;
        }
    };
    public List<XuatXu> getListXuatXu () throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM salemanager.xuatxuhanghoa;");
        List<XuatXu> listXuatXu = new ArrayList<>();
         while (r.next()) {
            XuatXu xx = new XuatXu();
            xx.setIdXuatXu(r.getInt("idxuatxuhanghoa"));
            xx.setNoiXuatXu(r.getString("noiXuatXu"));
            xx.setMoTa(r.getString("moTa"));
            listXuatXu.add(xx);
        }
        return listXuatXu;
    };
}
