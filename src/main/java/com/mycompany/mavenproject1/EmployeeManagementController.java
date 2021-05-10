/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.Utils;
import com.mycompany.mavenproject1.pojo.NghiepVu;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EmployeeManagementController implements Initializable {

    @FXML
    private TableView<NhanVien> tbEmployee;
    @FXML
    private TextField kwEmployee;
    @FXML
    private ComboBox<NghiepVu> cbNghiepVu;
    @FXML
    private TextField txtPasswordEmployee;
    @FXML
    private TextField txtAccountEmployee;
    @FXML
    private TextField txtNameEmployee;
    @FXML
    private Button btnAddEmployee;
    @FXML
    private Button btnUpdateEmpyee;
    @FXML
    private Button btnDeleteEmployee;
    @FXML
    private Button btnRsInputEmployee;
    @FXML
    private Button btnSearchEMployee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = jdbcUtil.getConn();
            NghiepVuService s = new NghiepVuService(conn);
            this.cbNghiepVu.setItems(FXCollections.observableList(s.getNghiepVu()));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.kwEmployee.textProperty().addListener((obj) -> {
            LoadDataTableEmployee(this.kwEmployee.getText());
        });
        loadTables();
        LoadDataTableEmployee("");
        this.btnRsInputEmployee.setOnMouseClicked(e -> {
            ResetInputEmployee();
            btnDeleteEmployee.setDisable(true);
            btnUpdateEmpyee.setDisable(true);
            btnAddEmployee.setDisable(false);
        });
        this.tbEmployee.setRowFactory(obj -> {
            TableRow r = new TableRow();
            r.setOnMouseClicked(evt -> {

                try {
                    Connection conn = jdbcUtil.getConn();
                    NhanVien nv = this.tbEmployee.getSelectionModel().getSelectedItem();
                    SetDisableButtonEmployee(false);
                    NghiepVuService s = new NghiepVuService(conn);
                    txtNameEmployee.setText(nv.getTenNhanVien());
                    txtAccountEmployee.setText(nv.getTaiKhoan());
                    txtPasswordEmployee.setText(nv.getMatKhau());
                    cbNghiepVu.getSelectionModel().select(s.getNghepVuById(nv.getNghiepVu()));
                    conn.close();
                    btnDeleteEmployee.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION)
                                    .showAndWait().ifPresent(bt -> {
                                        if (bt == ButtonType.OK) {
                                            try {
                                                Connection conn = jdbcUtil.getConn();
                                                NhanVienService s = new NhanVienService(conn);

                                                if (s.deleleNhanVien(nv.getMaNhanVien())) {
                                                    Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                                    SetDisableButtonEmployee(true);
                                                    ResetInputEmployee();
                                                    LoadDataTableEmployee("");
                                                } else {
                                                    Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                                }

                                                conn.close();
                                            } catch (SQLException ex) {

                                                ex.printStackTrace();
                                                Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                        }
                    });
                    btnUpdateEmpyee.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                NhanVien nvUpdate = new NhanVien();
                                nvUpdate.setMaNhanVien(nv.getMaNhanVien());
                                try {
                                    if (checkValidateInphut() == true) {
                                        Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
                                    } else {
                                        nvUpdate.setTenNhanVien(txtNameEmployee.getText());
                                        nvUpdate.setTaiKhoan(txtAccountEmployee.getText());
                                        nvUpdate.setMatKhau(txtPasswordEmployee.getText());
                                        nvUpdate.setNghiepVu(cbNghiepVu.getSelectionModel().getSelectedItem().getIdNghiepVu());
                                        Connection conn = jdbcUtil.getConn();
                                        NhanVienService s = new NhanVienService(conn);
                                        if (s.updateNhanVien(nvUpdate) == true) {
                                            Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                            SetDisableButtonEmployee(true);
                                            ResetInputEmployee();
                                            LoadDataTableEmployee("");

                                        } else {
                                            Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                        }
                                        conn.close();
                                    }

                                } catch (NullPointerException evnt) {
                                    Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
                                }
//                                if (checkValidateInphut() == true) {
//                                    Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
//                                } else {
//
//                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return r;
        });
    }

    public void ResetInputEmployee() {
        this.txtAccountEmployee.setText(null);
        this.txtNameEmployee.setText(null);
        this.txtPasswordEmployee.setText(null);
        this.cbNghiepVu.getSelectionModel().select(null);
        this.btnAddEmployee.setDisable(false);
    }

    public void SetDisableButtonEmployee(boolean isAction) {
        if (isAction == true) {
            btnDeleteEmployee.setDisable(true);
            btnUpdateEmpyee.setDisable(true);
            btnAddEmployee.setDisable(false);
        } else {
            btnDeleteEmployee.setDisable(false);
            btnUpdateEmpyee.setDisable(false);
            btnAddEmployee.setDisable(true);
        }

    }

    public void LoadDataTableEmployee(String kw) {
        try {
            this.tbEmployee.getItems().clear();
            Connection conn = jdbcUtil.getConn();
            NhanVienService s = new NhanVienService(conn);
            this.tbEmployee.setItems(FXCollections.observableList(s.getListNhanVien(kw)));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTables() {
        TableColumn colId = new TableColumn("Mã Nhân Viên");
        colId.setCellValueFactory(new PropertyValueFactory("MaNhanVien"));
        TableColumn colName = new TableColumn("Tên Nhân Viên");
        colName.setCellValueFactory(new PropertyValueFactory("TenNhanVien"));
        TableColumn colAccount = new TableColumn("Tài Khoản");
        colAccount.setCellValueFactory(new PropertyValueFactory("TaiKhoan"));
        TableColumn colPassword = new TableColumn("Mật Khẩu");
        colPassword.setCellValueFactory(new PropertyValueFactory("MatKhau"));
        this.tbEmployee.getColumns().addAll(colId, colName, colAccount, colPassword);
    }

    public boolean checkValidateInphut() {
        return txtAccountEmployee.getText().isEmpty()
                || txtNameEmployee.getText().isEmpty()
                || txtPasswordEmployee.getText().isEmpty();
    }

    ;

    ;
    @FXML
    private void addNhanVien(ActionEvent event) {
        try {
            Connection conn = jdbcUtil.getConn();
            NhanVienService s = new NhanVienService(conn);
            NhanVien nv = new NhanVien();
            try {
                if (checkValidateInphut() == true) {
                    Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
                } else {
                    if (s.findAccount(txtAccountEmployee.getText()) != null) {
                        Utils.getBox("Tài Khoản Đã Tồn Tại", Alert.AlertType.INFORMATION).show();
                    } else {
                        nv.setTaiKhoan(txtAccountEmployee.getText());
                        nv.setMatKhau(txtPasswordEmployee.getText());
                        nv.setTenNhanVien(txtNameEmployee.getText());
                        try {
                            nv.setNghiepVu(this.cbNghiepVu.getSelectionModel().getSelectedItem().getIdNghiepVu());
                            if (s.addNhanVien(nv) == true) {
                                conn.close();
                                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                SetDisableButtonEmployee(true);
                                LoadDataTableEmployee("");
                                this.ResetInputEmployee();
                            } else {
                                Utils.getBox("FAILED", Alert.AlertType.INFORMATION).show();
                                conn.close();
                            }
                        } catch (NullPointerException e) {
                            Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
                        }

                    }

                }
            } catch (NullPointerException e) {
                Utils.getBox("Vui Lòng Nhập Đầy Đủ Thông Tin", Alert.AlertType.INFORMATION).show();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
