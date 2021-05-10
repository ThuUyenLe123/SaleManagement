/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.pojo.NghiepVu;
import com.mycompany.mavenproject1.service.NghiepVuService;
import com.mycompany.mavenproject1.service.jdbcUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SecondaryController implements Initializable {

    @FXML
    private StackPane containerPane;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnRegistration;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnDasboard;
    @FXML
    private Button btnManagement;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadPane("Dashboard.fxml");

        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkRole();
        btnManagement.setOnMouseClicked(e -> {
            try {
                LoadScene("home.fxml", e);
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnSell.setOnMouseClicked(e -> {
            try {
                LoadPane("sell.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnDasboard.setOnMouseClicked(e -> {
            try {
                LoadPane("Dashboard.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnLogout.setOnMouseClicked(e -> {
            try {
                LoadScene("Login.fxml", e);
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnRegistration.setOnMouseClicked(e -> {
            try {
                LoadPane("CustomerManagement.fxml");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void LoadScene(String fxml, MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public void checkRole() {
        try {
            Connection conn = jdbcUtil.getConn();
            NghiepVuService s = new NghiepVuService(conn);
            NghiepVu nghiepVu = s.getNghepVuById(App.getNvLogin().getNghiepVu());
            if ("quanLy".equals(nghiepVu.getTenNghiepVu())) {
                btnManagement.setVisible(true);
            } else {

                btnManagement.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadPane(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        containerPane.getChildren().removeAll();
        containerPane.getChildren().setAll(pane);
    }

}
