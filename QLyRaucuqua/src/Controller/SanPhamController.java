package Controller;

import Model.modelSanpham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamController {

    private Connection conn;

    // Constructor nhận kết nối Database
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
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return list;
    }
    

    // Lấy danh sách sản phẩm với chỉ những thông tin cần thiết cho bán hàng (ID, tên, giá)
    public List<Object[]> getAllSP() {
        List<Object[]> list = new ArrayList<>();
        try {
            String sql = "SELECT sanphamID, tenSP, giaSP FROM sanpham";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] sp = new Object[] {
                    rs.getInt("sanphamID"),
                    rs.getString("tenSP"),
                    rs.getFloat("giaSP")
                };
                list.add(sp);
            }
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
            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false; // Nếu có lỗi, trả về false
    }

    // Sửa thông tin sản phẩm
    public boolean updateSanPham(modelSanpham sp) {
        String sql = "UPDATE sanpham SET tenSP = ?, soluong = ?, giaSP = ? WHERE sanphamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getTenSP());
            stmt.setInt(2, sp.getSoLuong());
            stmt.setFloat(3, sp.getgiaSP());
            stmt.setInt(4, sp.getSanPhamID());
            return stmt.executeUpdate() > 0; // Trả về true nếu ít nhất 1 hàng được cập nhật
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }

    // Xóa sản phẩm
    public boolean deleteSanPham(int sanphamID) {
        try {
            String sql = "DELETE FROM sanpham WHERE sanphamID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sanphamID);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }

    // Tìm kiếm sản phẩm theo tên
    public List<modelSanpham> searchSanPhamByName(String tenSP) {
        List<modelSanpham> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE tenSP LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return list;
    }

    // Kiểm tra xem sản phẩm có tồn tại không
    public boolean isSanPhamExists(int id) {
        String sql = "SELECT COUNT(*) FROM sanpham WHERE sanphamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có kết quả > 0 thì tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy ID của sản phẩm theo tên
    public int getSanPhamID(String tenSP) {
        String sql = "SELECT sanphamID FROM sanpham WHERE tenSP = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenSP);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("sanphamID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nếu không tìm thấy sản phẩm
    }
}
