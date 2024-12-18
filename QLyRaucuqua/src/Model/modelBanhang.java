/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author leduc
 */
public class modelBanhang {
    private int banHangID;
    private int hoaDonID;
    private int sanPhamID;
    private int soLuong;
    private double donGia;

    // Constructors
    public modelBanhang() {}

    public modelBanhang(int banHangID, int hoaDonID, int sanPhamID, int soLuong, double donGia) {
        this.banHangID = banHangID;
        this.hoaDonID = hoaDonID;
        this.sanPhamID = sanPhamID;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters và Setters
    public int getBanHangID() { return banHangID; }
    public void setBanHangID(int banHangID) { this.banHangID = banHangID; }

    public int getHoaDonID() { return hoaDonID; }
    public void setHoaDonID(int hoaDonID) { this.hoaDonID = hoaDonID; }

    public int getSanPhamID() { return sanPhamID; }
    public void setSanPhamID(int sanPhamID) { this.sanPhamID = sanPhamID; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
