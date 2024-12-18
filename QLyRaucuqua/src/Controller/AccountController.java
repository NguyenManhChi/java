package Controller;

import Model.modelAccount;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountController {

    private Connection conn;

    public AccountController(Connection conn) {
        this.conn = conn;
    }

    // 1. Lấy danh sách tất cả tài khoản
    public List<modelAccount> getAllAccounts() throws SQLException {
        List<modelAccount> list = new ArrayList<>();
        String sql = "SELECT * FROM ACCCOUNT";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            modelAccount account = new modelAccount(
                    rs.getInt("UserID"), // Lấy giá trị từ cột UserID
                    rs.getString("Username"),
                    rs.getString("Password")
            );
            list.add(account);
        }
        return list;
    }

    // 2. Thêm tài khoản
    public boolean addAccount(modelAccount account) {
        String hashedPassword = hashPassword(account.getPassword());
        String sql = "INSERT INTO Acccount (Username, Password) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, hashedPassword); // Lưu mật khẩu đã hash
            int rowsAffected = pstmt.executeUpdate();

            // Kiểm tra nếu có dòng được chèn thành công
            if (rowsAffected > 0) {
                System.out.println("Tài khoản đã được thêm thành công!");
                return true;
            } else {
                System.out.println("Không thể thêm tài khoản!");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi thêm tài khoản: " + ex.getMessage());
        }
        return false;
    }

    // 3. Sửa thông tin tài khoản
    public boolean updateAccount(modelAccount account) {
        String sql = "UPDATE ACCCOUNT SET Username = ?, Password = ? WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setInt(3, account.getUserID());  // Sửa tham số index để cập nhật UserID
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa tài khoản theo ID
    public boolean deleteAccount(int userID) {
        String sql = "DELETE FROM ACCCOUNT WHERE UserID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức hash mật khẩu
    public String hashPassword(String password) {
        try {
            // Sử dụng SHA-256 để mã hóa mật khẩu
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Kiểm tra tài khoản đã tồn tại hay chưa
    public boolean checkAccountExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM Acccount WHERE Username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số lượng > 0 thì tài khoản đã tồn tại
            }
            return false;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }

    public boolean checkPassword(String inputPassword, String storedHashedPassword) {
        String hashedInputPassword = hashPassword(inputPassword);
        System.out.println("Input hashed: " + hashedInputPassword); // In ra để kiểm tra
        System.out.println("Stored hashed: " + storedHashedPassword); // In ra để kiểm tra
        return hashedInputPassword.equals(storedHashedPassword); // So sánh
    }

    // Check đăng nhập
    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM Acccount WHERE Username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("Password"); // Lấy mật khẩu đã hash từ DB
                return checkPassword(password, storedHashedPassword); // So sánh mật khẩu đã hash
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
