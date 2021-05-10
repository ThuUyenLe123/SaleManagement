/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.pojo;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {
    private int idHoaDon;
    private int soLuong;
    private int idHangHoa;
    private BigDecimal donGia;

    public BigDecimal getDonGia() {
        return donGia;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "idHoaDon=" + idHoaDon + ", soLuong=" + soLuong + ", idHangHoa=" + idHangHoa + ", donGia=" + donGia + '}';
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdHangHoa() {
        return idHangHoa;
    }

    public void setIdHangHoa(int idHangHoa) {
        this.idHangHoa = idHangHoa;
    }
}
