/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.service.KhachHangService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnDasboard;
    private AnchorPane PaneEmployee;
    @FXML
    private StackPane containerPane;
    @FXML
    private Button btnStatistic;
    @FXML
    private Button btnBill;
    @FXML
    private Button btnSell;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnLogout.setOnMouseClicked(e -> {
            try {
                LoadScene("Login.fxml", e);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        try {
            LoadPane("Dashboard.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.btnEmployee.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("EmployeeManagement.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnProduct.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("ProductManagement.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         this.btnStatistic.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("Static.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnDasboard.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnCustomer.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("CustomerManagement.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnBill.setOnMouseClicked(e -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("BIllManagement.fxml"));
                containerPane.getChildren().removeAll();
                containerPane.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btnSell.setOnMouseClicked(e -> {
            try {
                LoadScene("secondary.fxml", e);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void LoadPane(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        containerPane.getChildren().removeAll();
        containerPane.getChildren().setAll(pane);
    }
}
