/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.pojo.NghiepVu;
import com.mycompany.mavenproject1.pojo.NhanVien;
import com.mycompany.mavenproject1.service.NghiepVuService;
import com.mycompany.mavenproject1.service.NhanVienService;
import com.mycompany.mavenproject1.service.jdbcUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbErro;
    public NhanVien nhanVienLogin;
    
    public NhanVien getNhanVienLogin() {
        return nhanVienLogin;
    }
    
    public void setNhanVienLogin(NhanVien nhanVienLogin) {
        this.nhanVienLogin = nhanVienLogin;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }
    
    public void showDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
        
    }    
    
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            try (Connection conn = jdbcUtil.getConn()) {
                NhanVienService nvsv = new NhanVienService(conn);
                NghiepVuService nghiepVuSv = new NghiepVuService(conn);
                NhanVien nv = nvsv.Login(username, password);
                if (nv != null) {
                    String alert = "Wellcome " + nv.getTenNhanVien() + "!";
                    showDialog(alert, null, "Successful");
                    int idNghiepVu = nv.getNghiepVu();
                    NghiepVu nghiepVu = nghiepVuSv.getNghepVuById(idNghiepVu);
                    App.setNvLogin(nv);
                    if ("quanLy".equals(nghiepVu.getTenNghiepVu())) {
                        LoadScene("home.fxml", event);
                    } else {
                        LoadScene("secondary.fxml", event);
                    }
                } else {
                    lbErro.setText("Password or Username not correct");
                }
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SetUserLogin(NhanVien nv) {
        this.nhanVienLogin = nv;
    }

    public void LoadScene(String fxml, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(scene);
        window.show();
    }
    
}
