/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author leduc
 */
import Model.modelSanpham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamController {
    private Connection conn;

    public SanPhamController(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<modelSanpham> getAllSanPham() throws SQLException {
        List<modelSanpham> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            modelSanpham sp = new modelSanpham(
                rs.getInt("sanphamID"),
                rs.getString("tenSP"),
                rs.getInt("soluong")
            );
            list.add(sp);
        }
        return list;
    }

    // Thêm sản phẩm mới
    public void addSanPham(modelSanpham sp) throws SQLException {
        String sql = "INSERT INTO sanpham (tenSP, soluong) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, sp.getTenSP());
        stmt.setInt(2, sp.getSoLuong());
        stmt.executeUpdate();
    }

    // Sửa thông tin sản phẩm
    public void updateSanPham(modelSanpham sp) throws SQLException {
        String sql = "UPDATE sanpham SET tenSP = ?, soluong = ? WHERE sanphamID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, sp.getTenSP());
        stmt.setInt(2, sp.getSoLuong());
        stmt.setInt(3, sp.getSanPhamID());
        stmt.executeUpdate();
    }

    // Xóa sản phẩm
    public void deleteSanPham(int sanphamID) throws SQLException {
        String sql = "DELETE FROM sanpham WHERE sanphamID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, sanphamID);
        stmt.executeUpdate();
    }

    // Tìm kiếm sản phẩm theo tên
    public List<modelSanpham> searchSanPhamByName(String tenSP) throws SQLException {
        List<modelSanpham> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE tenSP LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + tenSP + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            modelSanpham sp = new modelSanpham(
                rs.getInt("sanphamID"),
                rs.getString("tenSP"),
                rs.getInt("soluong")
            );
            list.add(sp);
        }
        return list;
    }
}

