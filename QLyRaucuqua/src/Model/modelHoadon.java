/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author leduc
 */
public class modelHoadon {
    private int hoaDonID;
    private int userID;
    private int khachHangID;
    private Date ngayTao;
    private BigDecimal tongTien;

    // Constructors
    public modelHoadon() {}

    public modelHoadon(int hoaDonID, int userID, int khachHangID, Date ngayTao, BigDecimal tongTien) {
        this.hoaDonID = hoaDonID;
        this.userID = userID;
        this.khachHangID = khachHangID;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    // Getters và Setters
    public int getHoaDonID() { return hoaDonID; }
    public void setHoaDonID(int hoaDonID) { this.hoaDonID = hoaDonID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getKhachHangID() { return khachHangID; }
    public void setKhachHangID(int khachHangID) { this.khachHangID = khachHangID; }

    public Date getNgayTao() { return ngayTao; }
    public void setNgayTao(Date ngayTao) { this.ngayTao = ngayTao; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }
}
