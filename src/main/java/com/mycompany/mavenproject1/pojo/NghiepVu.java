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
public class NghiepVu {
    private int idNghiepVu;
    private String tenNghiepVu;

    @Override
    public String toString() {
        return this.tenNghiepVu;
    }

    public int getIdNghiepVu() {
        return idNghiepVu;
    }

    public void setIdNghiepVu(int idNghiepVu) {
        this.idNghiepVu = idNghiepVu;
    }

    public String getTenNghiepVu() {
        return tenNghiepVu;
    }

    public void setTenNghiepVu(String tenNghiepVu) {
        this.tenNghiepVu = tenNghiepVu;
    }
}
