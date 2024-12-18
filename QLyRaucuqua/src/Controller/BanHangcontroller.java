/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author leduc
 */
import Model.modelBanhang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanHangcontroller {
    private Connection conn;

    public BanHangcontroller(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tất cả đơn bán hàng
    public List<modelBanhang> getAllBanhang() throws SQLException {
        List<modelBanhang> list = new ArrayList<>();
        String sql = "SELECT * FROM Banhang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            modelBanhang bh = new modelBanhang(
                rs.getInt("BanHangID"),
                rs.getInt("HoaDonID"),
                rs.getInt("SanPhamID"),
                rs.getInt("SoLuong"),
                rs.getDouble("DonGia")
            );
            list.add(bh);
        }
        return list;
    }

    // Thêm đơn bán hàng
    public void addBanhang(modelBanhang bh) throws SQLException {
        String sql = "INSERT INTO Banhang (HoaDonID, SanPhamID, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bh.getHoaDonID());
        stmt.setInt(2, bh.getSanPhamID());
        stmt.setInt(3, bh.getSoLuong());
        stmt.setDouble(4, bh.getDonGia());
        stmt.executeUpdate();
    }

    // Sửa đơn bán hàng
    public void updateBanhang(modelBanhang bh) throws SQLException {
        String sql = "UPDATE Banhang SET HoaDonID = ?, SanPhamID = ?, SoLuong = ?, DonGia = ? WHERE BanHangID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bh.getHoaDonID());
        stmt.setInt(2, bh.getSanPhamID());
        stmt.setInt(3, bh.getSoLuong());
        stmt.setDouble(4, bh.getDonGia());
        stmt.setInt(5, bh.getBanHangID());
        stmt.executeUpdate();
    }

    // Xóa đơn bán hàng
    public void deleteBanhang(int banHangID) throws SQLException {
        String sql = "DELETE FROM Banhang WHERE BanHangID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, banHangID);
        stmt.executeUpdate();
    }

    // Tìm kiếm đơn bán hàng theo HoaDonID
    public List<modelBanhang> searchBanhangByHoaDonID(int hoaDonID) throws SQLException {
        List<modelBanhang> list = new ArrayList<>();
        String sql = "SELECT * FROM Banhang WHERE HoaDonID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, hoaDonID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            modelBanhang bh = new modelBanhang(
                rs.getInt("BanHangID"),
                rs.getInt("HoaDonID"),
                rs.getInt("SanPhamID"),
                rs.getInt("SoLuong"),
                rs.getDouble("DonGia")
            );
            list.add(bh);
        }
        return list;
    }
}
