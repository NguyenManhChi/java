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
    private float giaSP;

    // Constructors
    public modelSanpham() {
    }

    public modelSanpham(int sanPhamID, String tenSP, int soLuong, float giaSP) {
        this.sanPhamID = sanPhamID;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
    }
    public modelSanpham( String tenSP, int soLuong, float giaSP) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
    }
    // Getters và Setters
    public int getSanPhamID() {
        return sanPhamID;
    }

    public void setSanPhamID(int sanPhamID) {
        this.sanPhamID = sanPhamID;
    }
    
    public float  getgiaSP() {
        return giaSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
      public void setgiaSP(float giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
