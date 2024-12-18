/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author leduc
 */
import Model.modelKhachhang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangController {
    private Connection conn;

    public KhachHangController(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tất cả khách hàng
    public List<modelKhachhang> getAllKhachHang() throws SQLException {
        List<modelKhachhang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            modelKhachhang kh = new modelKhachhang(
                rs.getInt("KhachHangID"),
                rs.getString("TenKH"),
                rs.getString("SoDienThoai"),
                rs.getString("DiaChi")
            );
            list.add(kh);
        }
        return list;
    }

    // Thêm khách hàng mới
    public boolean addKhachHang(modelKhachhang kh) {
        String sql = "INSERT INTO KhachHang (TenKH, SoDienThoai, DiaChi) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSoDienThoai());
            stmt.setString(3, kh.getDiaChi());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa thông tin khách hàng
    public boolean updateKhachHang(modelKhachhang kh) {
        String sql = "UPDATE KhachHang SET TenKH = ?, SoDienThoai = ?, DiaChi = ? WHERE KhachHangID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSoDienThoai());
            stmt.setString(3, kh.getDiaChi());
            stmt.setInt(4, kh.getKhachHangID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa khách hàng theo ID
    public boolean deleteKhachHang(int khachHangID) {
        String sql = "DELETE FROM KhachHang WHERE KhachHangID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, khachHangID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm khách hàng theo tên hoặc số điện thoại
    public List<modelKhachhang> searchKhachHang(String keyword) {
        List<modelKhachhang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE TenKH LIKE ? OR SoDienThoai LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelKhachhang kh = new modelKhachhang(
                    rs.getInt("KhachHangID"),
                    rs.getString("TenKH"),
                    rs.getString("SoDienThoai"),
                    rs.getString("DiaChi")
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

