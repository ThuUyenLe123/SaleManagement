/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.service;

import java.sql.Connection;
import com.mycompany.mavenproject1.pojo.NghiepVu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class NghiepVuService {
    private Connection conn;
    public NghiepVuService(Connection conn){
        this.conn = conn;
    }
    public NghiepVu getNghepVuById(int id) throws SQLException{
        NghiepVu nv = null;
        String sql = "SELECT * FROM salemanager.nghiepvu where idnghiepvu=?";
        PreparedStatement stm;
        
        
            stm = this.conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                nv = new NghiepVu();
                nv.setIdNghiepVu(rs.getInt("idnghiepvu"));
                nv.setTenNghiepVu(rs.getString("tenNghiepVu"));
                return nv;
            }
            else{
                return nv;
            }
    };
    public List<NghiepVu> getNghiepVu () throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM salemanager.nghiepvu;");
        List<NghiepVu> lnv = new ArrayList<>();
         while (r.next()) {
            NghiepVu nv = new NghiepVu();
            nv.setIdNghiepVu(r.getInt("idnghiepvu"));
            nv.setTenNghiepVu(r.getString("tenNghiepVu"));
            lnv.add(nv);
        }
        return lnv;
    };
    
}
