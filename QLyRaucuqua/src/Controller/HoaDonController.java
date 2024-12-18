/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author leduc
 */
import Model.modelHoadon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonController {
    private Connection conn;

    // Constructor để truyền vào kết nối cơ sở dữ liệu
    public HoaDonController(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tất cả hóa đơn
    public List<modelHoadon> getAllHoaDon() throws SQLException {
        List<modelHoadon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            modelHoadon hoaDon = new modelHoadon(
                rs.getInt("HoaDonID"),
                rs.getInt("UserID"),
                rs.getInt("KhachHangID"),
                rs.getDate("NgayTao"),
                rs.getBigDecimal("TongTien")
            );
            list.add(hoaDon);
        }
        return list;
    }

    // Thêm hóa đơn mới
    public void addHoaDon(modelHoadon hoaDon) throws SQLException {
        String sql = "INSERT INTO HoaDon (UserID, KhachHangID, NgayTao, TongTien) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, hoaDon.getUserID());
        stmt.setInt(2, hoaDon.getKhachHangID());
        stmt.setDate(3, (Date) hoaDon.getNgayTao());
        stmt.setBigDecimal(4, hoaDon.getTongTien());
        stmt.executeUpdate();
    }

    // Cập nhật hóa đơn
    public void updateHoaDon(modelHoadon hoaDon) throws SQLException {
        String sql = "UPDATE HoaDon SET UserID = ?, KhachHangID = ?, NgayTao = ?, TongTien = ? WHERE HoaDonID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, hoaDon.getUserID());
        stmt.setInt(2, hoaDon.getKhachHangID());
        stmt.setDate(3, (Date) hoaDon.getNgayTao());
        stmt.setBigDecimal(4, hoaDon.getTongTien());
        stmt.setInt(5, hoaDon.getHoaDonID());
        stmt.executeUpdate();
    }

    // Xóa hóa đơn
    public void deleteHoaDon(int hoaDonID) throws SQLException {
        String sql = "DELETE FROM HoaDon WHERE HoaDonID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, hoaDonID);
        stmt.executeUpdate();
    }

    // Tìm kiếm hóa đơn theo ID
    public modelHoadon getHoaDonByID(int hoaDonID) throws SQLException {
        String sql = "SELECT * FROM HoaDon WHERE HoaDonID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, hoaDonID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new modelHoadon(
                rs.getInt("HoaDonID"),
                rs.getInt("UserID"),
                rs.getInt("KhachHangID"),
                rs.getDate("NgayTao"),
                rs.getBigDecimal("TongTien")
            );
        } else {
            return null;
        }
    }

    // Lấy hóa đơn theo khách hàng
    public List<modelHoadon> getHoaDonByKhachHangID(int khachHangID) throws SQLException {
        List<modelHoadon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE KhachHangID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, khachHangID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            modelHoadon hoaDon = new modelHoadon(
                rs.getInt("HoaDonID"),
                rs.getInt("UserID"),
                rs.getInt("KhachHangID"),
                rs.getDate("NgayTao"),
                rs.getBigDecimal("TongTien")
            );
            list.add(hoaDon);
        }
        return list;
    }
}
