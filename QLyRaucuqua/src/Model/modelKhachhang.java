/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author leduc
 */
public class modelKhachhang {
    private int khachHangID;
    private String tenKH;
    private String soDienThoai;
    private String diaChi;

    // Constructors
    public modelKhachhang() {}
    
    public modelKhachhang(int khachHangID, String tenKH, String soDienThoai, String diaChi) {
        this.khachHangID = khachHangID;
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }
    public int getKhachHangID() { return khachHangID; }
    public void setKhachHangID(int khachHangID) { this.khachHangID = khachHangID; }

    public String getTenKH() { return tenKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
}
