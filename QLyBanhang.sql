CREATE DATABASE QuanLyRauCuQua;
GO

USE QuanLyRauCuQua;
Create table Acccount (
	UserID INT PRIMARY KEY IDENTITY(1,1),
	Username NVARCHAR(50) UNIQUE NOT NULL,
    Password NVARCHAR(255) NOT NULL,
);
CREATE TABLE KhachHang (
    KhachHangID INT PRIMARY KEY IDENTITY(1,1), 
    TenKH NVARCHAR(50) NOT NULL,             
    SoDienThoai NVARCHAR(15) UNIQUE,          
    DiaChi NVARCHAR(255)                    
);

Create table sanpham(
	sanphamID INT PRIMARY KEY IDENTITY(1,1),
	tenSP NVARCHAR(50),
	soluong INT,
	giaSP float,
);
CREATE TABLE HoaDon (
    HoaDonID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT FOREIGN KEY REFERENCES Acccount(UserID), 
	KhachHangID INT FOREIGN KEY REFERENCES KhachHang(KhachHangID),
    NgayTao DATE DEFAULT GETDATE(),
    TongTien DECIMAL(12,2) DEFAULT 0
);

CREATE TABLE Banhang (
    BanHangID INT PRIMARY KEY IDENTITY(1,1),
    KhachHangID INT FOREIGN KEY REFERENCES KhachHang(KhachHangID),
    SanPhamID INT FOREIGN KEY REFERENCES sanpham(sanphamID),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10, 2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia)          
);
CREATE TABLE DoanhThu ( 
	DoanhThuID INT PRIMARY KEY IDENTITY(1,1), 
	HoaDonID INT FOREIGN KEY REFERENCES HoaDon(HoaDonID), 
	TongTien DECIMAL(18,2), 
	NgayThu DATE 
);

CREATE TRIGGER UpdateBanhangOnSanphamChange
ON sanpham
AFTER UPDATE
AS
BEGIN
    -- Chỉ khi giá sản phẩm thay đổi
    IF UPDATE(giaSP)
    BEGIN
        -- Cập nhật Đơn giá trong bảng Banhang
        UPDATE bh
        SET bh.DonGia = sp.giaSP
        FROM Banhang bh
        INNER JOIN inserted sp ON bh.SanPhamID = sp.sanphamID;
    END
END
