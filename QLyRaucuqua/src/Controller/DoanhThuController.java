/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.modelDoanhthu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leduc
 */
public class DoanhThuController {

    private Connection conn;

    // Constructor nhận kết nối Database
    public DoanhThuController(Connection conn) {
        this.conn = conn;
    }
    public List<modelDoanhthu> getAllDoanhThu() {
        List<modelDoanhthu> list = new ArrayList<>();
        try {
            // Truy vấn dữ liệu từ bảng DoanhThu
            String sql = "SELECT * FROM HoaDon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Duyệt qua kết quả truy vấn và tạo đối tượng modelDoanhThu
            while (rs.next()) {
                modelDoanhthu dt = new modelDoanhthu(
                        rs.getInt("HoaDonID"),
                        rs.getInt("KhachHangID"),
                        rs.getDate("NgayTao"),
                        rs.getFloat("TongTien")
                );
                list.add(dt); // Thêm đối tượng vào danh sách
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return list; // Trả về danh sách các đối tượng doanh thu
    }

    public boolean addHoaDon(int khachHangId, String ngayTao, float tongTien) {
        String sql = "INSERT INTO HoaDon (KhachHangID, NgayTao, TongTien) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, khachHangId);
            ps.setString(2, ngayTao); // NgayTao là dạng chuỗi
            ps.setFloat(3, tongTien);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<modelDoanhthu> searchDoanhthuByHoaDonID(int hoaDonID) {
        List<modelDoanhthu> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE HoaDonID = ?";  // Tìm kiếm theo ID hóa đơn
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hoaDonID);  // Set tham số HoaDonID
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelDoanhthu dt = new modelDoanhthu(
                        rs.getInt("HoaDonID"),
                        rs.getInt("KhachHangID"),
                        rs.getDate("NgayTao"),
                        rs.getFloat("TongTien")
                );
                list.add(dt);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return list;
    }

    public float getTongTienHoaDon(Connection conn) {
        float tongTien = 0;
        
        try {
            String sql = "SELECT SUM(TongTien) AS TongTienHoaDon FROM HoaDon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                tongTien = rs.getFloat("TongTienHoaDon");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        return tongTien;
    }
    public int getTongSoHoaDon(Connection conn) {
        int sodon = 0;
        
        try {
            String sql = "SELECT SUM(HoaDonID) AS TongDon FROM HoaDon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                sodon = rs.getInt("TongDon");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        return sodon;
    }
}
