/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.pojo.ChiTietHoaDon;
import com.mycompany.mavenproject1.pojo.HoaDon;
import com.mycompany.mavenproject1.pojo.KhachHang;
import com.mycompany.mavenproject1.service.ChiTietHoaDonService;
import com.mycompany.mavenproject1.service.HoaDonService;
import com.mycompany.mavenproject1.service.KhachHangService;
import com.mycompany.mavenproject1.service.jdbcUtil;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BillManagementController implements Initializable {

    @FXML
    private TextField txtIDBill;
    @FXML
    private TextField txtIDNhanVIen;
    @FXML
    private TextField txtDonGiaHangHoa;
    @FXML
    private DatePicker pcNgayLap;
    @FXML
    private TextField txtVAT;
    @FXML
    private TextField txtThanhTien;
    @FXML
    private Text txtIDKhachHang;
    @FXML
    private Text txtTenKhachHang;
    @FXML
    private Text txtSDTKhachHang;
    @FXML
    private Text txtDiaChiKhachHang;
    @FXML
    private Text txtCMNDKhachHang;
    @FXML
    private Text txtDiemTichLuy;
    private Button btnEditBill;
    private Button btnDeleteBill;
    @FXML
    private Button btnPrintBill;
    @FXML
    private DatePicker pcSearch;
    @FXML
    private TableView<ChiTietHoaDon> tbChiTietBill;
    @FXML
    private TableView<HoaDon> tbListBill;
    @FXML
    private Button btnSuaListHoaDon;
    @FXML
    private Button btnXoaListHoaDon;
    @FXML
    private TextField txtDiemKhachHangSD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadTables();
        LoadBill();
        LoadDataBills();
        this.txtIDBill.setDisable(true);
        this.txtIDKhachHang.setDisable(true);
        this.tbListBill.setRowFactory(obj -> {
            TableRow r = new TableRow();
            SetDisableButtonListBill(false);
            r.setOnMouseClicked(e -> {
                SetDisableButtonListBill(true);
                CheckIsEmpty();
                RestInputKhacHang();
                HoaDon hd = this.tbListBill.getSelectionModel().getSelectedItem();
                try {
                    Connection conn = jdbcUtil.getConn();
                    KhachHangService khs = new KhachHangService(conn);
                    KhachHang kh = khs.findKhachHangByID(hd.getIDKhachHangThanThiet());
                    if (kh != null) {
                        txtIDKhachHang.setText(Integer.toString(kh.getIdKhachHangThanThiet()));
                        txtTenKhachHang.setText(kh.getTenKhachHang());
                        txtSDTKhachHang.setText(kh.getSDT());
                        txtDiaChiKhachHang.setText(kh.getDiaChi());
                        txtCMNDKhachHang.setText(kh.getCMND());
                        txtDiemTichLuy.setText(Integer.toString(kh.getDiem()));
                    }

                    conn.close();

                } catch (SQLException ex) {
                    Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtIDBill.setText(Integer.toString(hd.getIdHoaDon()));
                txtIDNhanVIen.setText(Integer.toString(hd.getIDNhanVienBanHang()));
                pcNgayLap.setValue(convertToLocalDateViaSqlDate(hd.getNgayLap()));
                txtThanhTien.setText(hd.getThanhTien().toString());
                LoadDataChiTietHoaDon(Integer.parseInt(txtIDBill.getText()));
//                NumberFormat format = NumberFormat.getPercentInstance(Locale.US);
//                String percentage = format.format(hd.getVAT());
                txtVAT.setText(Double.toString(hd.getVAT()));
                txtDiemKhachHangSD.setText(Integer.toString(hd.getDiemKhachHangSuDung()));
                btnSuaListHoaDon.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {

                            if (CheckIsEmpty() == true) {
                                Utils.getBox("Vui Long Nhap Thong Tin Day Du", Alert.AlertType.ERROR).show();
                            } else {
                                Connection conn = jdbcUtil.getConn();
                                HoaDonService s = new HoaDonService(conn);
                                HoaDon hdUpdate = new HoaDon();
                                hdUpdate.setIdHoaDon(hd.getIdHoaDon());
                                hdUpdate.setNgayLap(Date.valueOf(pcNgayLap.getValue()));
                                hdUpdate.setIDNhanVienBanHang(Integer.parseInt(txtIDNhanVIen.getText()));
                                hdUpdate.setIDKhachHangThanThiet(hd.getIDKhachHangThanThiet());
                                hdUpdate.setThanhTien(new BigDecimal(txtThanhTien.getText()));
                                hdUpdate.setVAT(Double.parseDouble(txtVAT.getText()));
                                if (s.UpdateListHoaDon(hdUpdate)) {
                                    Utils.getBox("SUCCESSFULLY", Alert.AlertType.INFORMATION).show();
                                    conn.close();
                                    LoadDataBills();
                                } else {
                                    Utils.getBox("FAILDED", Alert.AlertType.INFORMATION).show();
                                    conn.close();
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                btnXoaListHoaDon.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        Utils.getBox("Ban chac chan xoa khong?", Alert.AlertType.CONFIRMATION)
                                .showAndWait().ifPresent(bt -> {
                                    if (bt == ButtonType.OK) {
                                        try {
                                            Connection conn = jdbcUtil.getConn();
                                            HoaDonService s = new HoaDonService(conn);

                                            if (s.deleleHoaDon(hd.getIdHoaDon())) {
                                                Utils.getBox("SUCCESSFUL", Alert.AlertType.INFORMATION).show();
                                                SetDisableButtonListBill(true);
                                                ResetInput();
                                                LoadDataBills();
                                                tbChiTietBill.getItems().clear();
                                            } else {
                                                Utils.getBox("FAILED", Alert.AlertType.ERROR).show();
                                            }

                                            conn.close();
                                        } catch (SQLException ex) {

                                            ex.printStackTrace();
                                            Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });

                    }
                });
            });
            return r;
        });
        this.pcSearch.getEditor().textProperty().addListener((obj) -> {
            try {
                Connection conn = jdbcUtil.getConn();
                HoaDonService s = new HoaDonService(conn);
                if (this.pcSearch.getEditor().getText().isEmpty() != true) {
                    this.tbListBill.setItems(FXCollections.observableList(s.getListHoaDonByDate(Date.valueOf(this.pcSearch.getValue()))));
                }

            } catch (SQLException ex) {
                Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        pcNgayLap.getEditor().setDisable(true);
    }

    public double ParseDouble(String s) {
        double x = Double.parseDouble(s.replace("%", "")) / 100;
        return x;
    }

    public void ResetInput() {
        txtIDBill.setText("");
        txtIDNhanVIen.setText("");
        txtThanhTien.setText("");
        txtVAT.setText("");
        pcNgayLap.setValue(null);
    }

    public void RestInputKhacHang() {
        txtIDKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSDTKhachHang.setText("");
        txtDiaChiKhachHang.setText("");
        txtCMNDKhachHang.setText("");
        txtDiemTichLuy.setText("");
    }

    public boolean CheckIsEmpty() {
        return txtIDNhanVIen.getText().isEmpty()
                || txtThanhTien.getText().isEmpty()
                || txtVAT.getText().isEmpty();
    }

    public void SetDisableButtonListBill(boolean isAction) {
        if (isAction == true) {
            btnSuaListHoaDon.setDisable(false);
            btnXoaListHoaDon.setDisable(false);
        } else {
            btnSuaListHoaDon.setDisable(true);
            btnXoaListHoaDon.setDisable(true);
        }
    }

    public void SetDisableButtonBill(boolean isAction) {
        if (isAction == true) {
            btnDeleteBill.setDisable(false);
            btnEditBill.setDisable(false);
            btnPrintBill.setDisable(false);
        } else {
            btnDeleteBill.setDisable(true);
            btnEditBill.setDisable(true);
            btnPrintBill.setDisable(true);

        }
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void LoadTables() {
        TableColumn colIdBill = new TableColumn("ID Hóa Đơn");
        colIdBill.setCellValueFactory(new PropertyValueFactory("idHoaDon"));
        TableColumn colIdNhanVien = new TableColumn("ID Nhân Viên");
        colIdNhanVien.setCellValueFactory(new PropertyValueFactory("IDNhanVienBanHang"));
        TableColumn colNgayLap = new TableColumn("Ngày Lập");
        colNgayLap.setCellValueFactory(new PropertyValueFactory("NgayLap"));
        TableColumn colThanhTIen = new TableColumn("Thành Tiền");
        colThanhTIen.setCellValueFactory(new PropertyValueFactory("ThanhTien"));
        TableColumn colVAT = new TableColumn("VAT");
        colVAT.setCellValueFactory(new PropertyValueFactory("VAT"));
        this.tbListBill.getColumns().addAll(colIdBill, colIdNhanVien, colNgayLap, colThanhTIen, colVAT);
    }

    public void LoadBill() {
        TableColumn colIdHH = new TableColumn("Mã Hàng Hóa");
        colIdHH.setCellValueFactory(new PropertyValueFactory("idHangHoa"));
        TableColumn colSoLuong = new TableColumn("Số Lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        TableColumn colDonGia = new TableColumn("Đơn Giá");
        colDonGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        this.tbChiTietBill.getColumns().addAll(colIdHH, colSoLuong, colDonGia);
    }

    public void LoadDataBills() {
        try {
            this.tbListBill.getItems().clear();
            Connection conn = jdbcUtil.getConn();
            HoaDonService s = new HoaDonService(conn);
            this.tbListBill.setItems(FXCollections.observableList(s.getListHoaDon()));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadDataChiTietHoaDon(int id){
        try {
            this.tbChiTietBill.getItems().clear();
            Connection conn = jdbcUtil.getConn();
            ChiTietHoaDonService s = new ChiTietHoaDonService(conn);
            this.tbChiTietBill.setItems(FXCollections.observableList(s.getListChiTietById(id)));
        } catch (SQLException ex) {
            Logger.getLogger(BillManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
