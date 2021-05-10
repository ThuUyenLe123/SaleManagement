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
public class HangHoa {

    @Override
    public String toString() {
        return "HangHoa{" + "idHangHoa=" + idHangHoa + ", tenHang=" + tenHang + ", loaiHang=" + loaiHang + ", xuatXu=" + xuatXu + ", ngaySX=" + ngaySX + ", hanSD=" + hanSD + ", giaBan=" + giaBan + ", donViTinh=" + donViTinh + ", soLuong=" + soLuong + '}';
    }
    private int idHangHoa;
    private String tenHang;
    private int loaiHang;
    private int xuatXu;
    private Date ngaySX;
    private Date hanSD;
    private BigDecimal giaBan;
    private BigDecimal giaMua;

    public BigDecimal getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(BigDecimal giaMua) {
        this.giaMua = giaMua;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }
    private String donViTinh;
    private int soLuong;

    public int getIdHangHoa() {
        return idHangHoa;
    }

    public void setIdHangHoa(int idHangHoa) {
        this.idHangHoa = idHangHoa;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getLoaiHang() {
        return loaiHang;
    }

    public void setLoaiHang(int loaiHang) {
        this.loaiHang = loaiHang;
    }

    public int getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(int xuatXu) {
        this.xuatXu = xuatXu;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public Date getHanSD() {
        return hanSD;
    }

    public void setHanSD(Date hanSD) {
        this.hanSD = hanSD;
    }


    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
