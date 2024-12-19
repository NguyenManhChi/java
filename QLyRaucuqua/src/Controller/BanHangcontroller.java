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

    public List<Object[]> getAllSPcuaKhachHang(int idKhachhang) {
        List<Object[]> list = new ArrayList<>();
        try {
            // Sử dụng tham số idKhachhang để lọc sản phẩm của khách hàng và đảm bảo rằng các bảng được kết nối đúng
            String sql = "SELECT sp.tenSP, bh.DonGia, bh.soluong, bh.ThanhTien FROM sanpham sp JOIN Banhang bh ON sp.SanPhamID = bh.SanPhamID WHERE bh.KhachHangID = ?";

            // Tạo PreparedStatement để tránh SQL Injection và sử dụng tham số
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idKhachhang);  // Gán tham số vào câu truy vấn

            ResultSet rs = pstmt.executeQuery();

            // Kiểm tra nếu kết quả trả về không rỗng
            if (!rs.isBeforeFirst()) {
                System.out.println("Không có sản phẩm nào cho khách hàng với ID: " + idKhachhang);
                return list;  // Trả về danh sách rỗng nếu không có sản phẩm
            }

            // Duyệt qua các kết quả trả về và thêm vào list
            while (rs.next()) {
                Object[] sp = new Object[]{
                    rs.getString("tenSP"), // Tên sản phẩm
                    rs.getInt("soluong"), // Số lượng
                    rs.getFloat("DonGia"), // Đơn giá
                    rs.getFloat("ThanhTien") // Thành tiền
                };
                list.add(sp);
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi khi truy vấn cơ sở dữ liệu: " + ex.getMessage());
        }
        return list;
    }

    public void themSanPhamVaoGiỏ(int khachHangID, String tenSanPham, int soLuong, float donGia) {
        try {
            // Câu lệnh SQL để thêm sản phẩm vào giỏ hàng
            String sql = "INSERT INTO Banhang (KhachHangID, SanPhamID, soluong, DonGia) "
                    + "VALUES (?, (SELECT SanPhamID FROM SanPham WHERE tenSP = ?), ?, ?)";

            // Tạo PreparedStatement và gán tham số
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, khachHangID); // ID khách hàng
            pstmt.setString(2, tenSanPham); // Tên sản phẩm
            pstmt.setInt(3, soLuong); // Số lượng
            pstmt.setFloat(4, donGia); // Đơn giá

            // Thực thi câu lệnh
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi khi thêm sản phẩm vào giỏ hàng: " + ex.getMessage());
        }
    }

    // Sửa đơn bán hàng
    public void capNhatSoLuongSanPham(int khachHangID, String tenSanPham, int newQuantity) {
        try {
            // Câu lệnh SQL để cập nhật số lượng sản phẩm
            String sql = "UPDATE Banhang SET soluong = ? WHERE KhachHangID = ? AND SanPhamID = (SELECT SanPhamID FROM SanPham WHERE tenSP = ?)";

            // Tạo PreparedStatement và gán tham số
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newQuantity); // Số lượng mới
            pstmt.setInt(2, khachHangID); // ID khách hàng
            pstmt.setString(3, tenSanPham); // Tên sản phẩm

            // Thực thi câu lệnh
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Lỗi khi cập nhật số lượng sản phẩm: " + ex.getMessage());
        }
    }

    public List<Object[]> getSanPhamByKhachHangID(int khachHangID) {
        List<Object[]> list = new ArrayList<>();
        try {
            String sql = "SELECT sp.TenSP, sp.GiaSP FROM SanPham sp WHERE sp.KhachHangID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, khachHangID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] sp = new Object[]{
                    rs.getString("TenSP"),
                    rs.getFloat("GiaSP")
                };
                list.add(sp);
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

        return list;
    }

    // Xóa đơn bán hàng
    public boolean deleteBanhang(int khachHangID, String tenSanPham) {
        try {
            // SQL xóa sản phẩm khỏi giỏ hàng của khách hàng
            String sql = "DELETE FROM Banhang WHERE KhachHangID = ? AND SanPhamID = (SELECT sanphamID FROM sanpham WHERE tenSP = ?)";

            // Tạo PreparedStatement để tránh SQL Injection và sử dụng tham số
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, khachHangID);  // Gán KhachHangID vào câu truy vấn
            pstmt.setString(2, tenSanPham);  // Gán tên sản phẩm vào câu truy vấn

            // Thực hiện câu lệnh DELETE và kiểm tra số dòng bị ảnh hưởng
            int rowsAffected = pstmt.executeUpdate();

            // Nếu xóa thành công, số dòng bị ảnh hưởng sẽ lớn hơn 0
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();  // In lỗi nếu có
            return false;  // Trả về false nếu gặp lỗi
        }
    }
    public boolean deleteBanhang(int khachHangID) {
        try {
            // SQL xóa sản phẩm khỏi giỏ hàng của khách hàng
            String sql = "DELETE FROM Banhang WHERE KhachHangID = ?";

            // Tạo PreparedStatement để tránh SQL Injection và sử dụng tham số
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, khachHangID);  // Gán KhachHangID vào câu truy vấn
            

            // Thực hiện câu lệnh DELETE và kiểm tra số dòng bị ảnh hưởng
            int rowsAffected = pstmt.executeUpdate();

            // Nếu xóa thành công, số dòng bị ảnh hưởng sẽ lớn hơn 0
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();  // In lỗi nếu có
            return false;  // Trả về false nếu gặp lỗi
        }
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
