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
    HoaDonID INT FOREIGN KEY REFERENCES HoaDon(HoaDonID), 
    SanPhamID INT FOREIGN KEY REFERENCES sanpham(sanphamID), 
    SoLuong INT NOT NULL,                       
    DonGia DECIMAL(10,2) NOT NULL,               
    ThanhTien AS (SoLuong * DonGia)             
);
CREATE TABLE DoanhThu ( 
	DoanhThuID INT PRIMARY KEY IDENTITY(1,1), 
	HoaDonID INT FOREIGN KEY REFERENCES HoaDon(HoaDonID), 
	TongTien DECIMAL(18,2), 
	NgayThu DATE 
);

ALTER TABLE sanpham
ADD  giaSP FLOAT;
