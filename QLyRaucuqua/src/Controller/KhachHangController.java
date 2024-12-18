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
    public List<modelKhachhang> getAllKhachHang() {
        List<modelKhachhang> list = new ArrayList<>();
        try {
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
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
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
    // getID
    public int getKhachHangID(String tenKH) {
        String sql = "SELECT KhachHangID FROM KhachHang WHERE TenKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenKH);  // Thay 'tenKH' bằng tên khách hàng cần tìm
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("KhachHangID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  // Nếu không tìm thấy, trả về 0
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

    public boolean isKhachHangExists(int id) {
        String sql = "SELECT COUNT(*) FROM KhachHang WHERE KhachHangID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Thay thế tham số ? bằng KhachHangID
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu số lượng > 0, nghĩa là khách hàng tồn tại
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;  // Nếu không có khách hàng với id này
    }

}
