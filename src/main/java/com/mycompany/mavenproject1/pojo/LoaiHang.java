/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.pojo;

/**
 *
 * @author Admin
 */
public class LoaiHang {

    @Override
    public String toString() {
        return this.tenLoaiHang;
    }
    private int idloaiHang;
    private String tenLoaiHang;
    private String moTa;
    public int getIdloaiHang() {
        return idloaiHang;
    }

    public void setIdloaiHang(int idloaiHang) {
        this.idloaiHang = idloaiHang;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
  
}
