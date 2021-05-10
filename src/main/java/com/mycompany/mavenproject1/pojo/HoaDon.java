/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private int idHoaDon;
    private Date NgayLap;
    private BigDecimal ThanhTien;

    @Override
    public String toString() {
        return "HoaDon{" + "idHoaDon=" + idHoaDon + ", NgayLap=" + NgayLap + ", ThanhTien=" + ThanhTien + ", VAT=" + VAT + ", IDNhanVienBanHang=" + IDNhanVienBanHang + ", IDKhachHangThanThiet=" + IDKhachHangThanThiet + '}';
    }
    private double VAT;
    private int IDNhanVienBanHang;
    private int IDKhachHangThanThiet;
    private int diemKhachHangSuDung;
    public int getDiemKhachHangSuDung() {
        return diemKhachHangSuDung;
    }
    public void setDiemKhachHangSuDung(int diemKhachHangSuDung) {
        this.diemKhachHangSuDung = diemKhachHangSuDung;
    }
    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public BigDecimal getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(BigDecimal ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public int getIDNhanVienBanHang() {
        return IDNhanVienBanHang;
    }

    public void setIDNhanVienBanHang(int IDNhanVienBanHang) {
        this.IDNhanVienBanHang = IDNhanVienBanHang;
    }

    public int getIDKhachHangThanThiet() {
        return IDKhachHangThanThiet;
    }

    public void setIDKhachHangThanThiet(int IDKhachHangThanThiet) {
        this.IDKhachHangThanThiet = IDKhachHangThanThiet;
    }
    
}   
