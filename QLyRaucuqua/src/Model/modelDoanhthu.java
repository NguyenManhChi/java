/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author leduc
 */
public class modelDoanhthu {
    private int doanhthuID;
    private int khachhangID;
    private Date ngayThanhToan;
    private float tongTien;

    // Constructor
    public modelDoanhthu(int doanhthuID, int khachhangID, Date ngayThanhToan, float tongTien) {
        this.doanhthuID = doanhthuID;
        this.khachhangID = khachhangID;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
    }
    
    // Getters and Setters
    public int getDoanhthuID() {
        return doanhthuID;
    }

    public void setDoanhthuID(int doanhthuID) {
        this.doanhthuID = doanhthuID;
    }

    public int getKhachhangID() {
        return khachhangID;
    }

    public void setKhachhangID(int khachhangID) {
        this.khachhangID = khachhangID;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}

