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
    public List<modelSanpham> getAllSanPham() {
        List<modelSanpham> list = new ArrayList<>();
        try {

            String sql = "SELECT * FROM sanpham";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                modelSanpham sp = new modelSanpham(
                        rs.getInt("sanphamID"),
                        rs.getString("tenSP"),
                        rs.getInt("soluong"),
                        rs.getFloat("giaSP")
                );
                list.add(sp);
            }
            return list;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return list;
    }

    // Thêm sản phẩm mới
    public boolean addSanPham(modelSanpham sp) {
        try {
            String sql = "INSERT INTO sanpham (tenSP, soluong, giaSP) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getTenSP());
            stmt.setInt(2, sp.getSoLuong());
            stmt.setFloat(3, sp.getgiaSP());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }

    // Sửa thông tin sản phẩm
    public boolean updateSanPham(modelSanpham sp) throws SQLException {
        String sql = "UPDATE sanpham SET tenSP = ?, soluong = ?, giaSP = ? WHERE sanphamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getTenSP());
            stmt.setInt(2, sp.getSoLuong());
            stmt.setFloat(3, sp.getgiaSP());
            stmt.setInt(4, sp.getSanPhamID());
            return stmt.executeUpdate() > 0; // Returns true if at least one row is updated
        }
    }


    // Xóa sản phẩm
    public boolean deleteSanPham(int sanphamID) {
        try {
            String sql = "DELETE FROM sanpham WHERE sanphamID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sanphamID);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
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
                    rs.getInt("soluong"),
                    rs.getFloat("giaSP")
            );
            list.add(sp);
        }
        return list;
    }

    public boolean isThucphamExists(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM sanpham WHERE sanphamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count > 0
            }
        }
        return false;
    }
    public int getSanphamID(String tenSP) {
        String sql = "SELECT sanphamID FROM sanpham WHERE tenSP = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenSP);  // Thay 'tenKH' bằng tên khách hàng cần tìm
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("KhachHangID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  // Nếu không tìm thấy, trả về 0
    }
}
