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
public class KhachHang {
    private int idKhachHangThanThiet;
    private String TenKhachHang;
    private String SDT;
    private String DiaChi;
    private int Diem;
    private String CMND;    
    public int getIdKhachHangThanThiet() {
        return idKhachHangThanThiet;
    }

    public void setIdKhachHangThanThiet(int idKhachHangThanThiet) {
        this.idKhachHangThanThiet = idKhachHangThanThiet;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    @Override
    public String toString() {
        return TenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int Diem) {
        this.Diem = Diem;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }
   
 
}
