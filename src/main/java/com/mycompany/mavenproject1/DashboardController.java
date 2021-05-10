/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import com.mycompany.mavenproject1.pojo.NhanVien;
import com.mycompany.mavenproject1.service.NghiepVuService;
import com.mycompany.mavenproject1.service.NhanVienService;
import com.mycompany.mavenproject1.service.jdbcUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DashboardController implements Initializable {

    @FXML
    private Text txtname;
    @FXML
    private Text txtAccount;
    @FXML
    private Text txtChucVu;
    private NhanVien nvLogin;
    @FXML
    private Text txtWellCome;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoUser();
    }
    public void setInfoUser(){
        NhanVien nv = App.getNvLogin();
        txtname.setText(nv.getTenNhanVien());
        txtAccount.setText(nv.getTaiKhoan());
        String textWellCome = "Chào mừng "+nv.getTenNhanVien()+" Đến với hệ thống quản lý bán hàng";
        txtWellCome.setText(textWellCome);
        Connection conn;
        try {
            conn = jdbcUtil.getConn();
            NghiepVuService s = new NghiepVuService(conn);
            txtChucVu.setText(s.getNghepVuById(nv.getNghiepVu()).getTenNghiepVu());
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
