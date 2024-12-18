/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBO;

/**
 *
 * @author leduc
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyRauCuQua;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";       // Tên đăng nhập SQL Server
    private static final String PASSWORD = "12345"; // Mật khẩu SQL Server

    // Phương thức để tạo kết nối
    public Connection getConnection() {
        try {
            // Load driver cho SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Đã load driver thành công!");
            
            // Tạo kết nối
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối đến SQL Server thành công!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy Driver SQL Server!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối SQL Server!");
            e.printStackTrace();
        }
        return null;
    }
}

