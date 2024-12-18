/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author leduc
 */
public class modelSanpham {
    private int sanPhamID;
    private String tenSP;
    private int soLuong;

    // Constructors
    public modelSanpham() {}
    
    public modelSanpham(int sanPhamID, String tenSP, int soLuong) {
        this.sanPhamID = sanPhamID;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
    }

    // Getters và Setters
    public int getSanPhamID() { return sanPhamID; }
    public void setSanPhamID(int sanPhamID) { this.sanPhamID = sanPhamID; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
