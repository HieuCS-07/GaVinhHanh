-- Tạo database GaVinhHanh
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'GaVinhHanh')
BEGIN
    CREATE DATABASE GaVinhHanh;
END

-- Chuyển tới database GaVinhHanh
USE GaVinhHanh;

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
	maNhanVien NVARCHAR(10) PRIMARY KEY,
	hoTen NVARCHAR(50) NOT NULL,
	gioiTinh NVARCHAR(4) NOT NULL,
	cccd NVARCHAR(12) UNIQUE NOT NULL,
	soDienThoai NVARCHAR(10) NOT NULL,
	trangThai NVARCHAR(15) NOT NULL,
	loaiNhanVien NVARCHAR(20) NOT NULL,
	ngayVaoLam DATE NOT NULL,
	queQuan NVARCHAR(20) NOT NULL
);

-- Tạo bảng TaiKhoai
CREATE TABLE TaiKhoan (
	maTaiKhoan NVARCHAR(10) PRIMARY KEY,
	tenDangNhap NVARCHAR(10) UNIQUE NOT NULL,
	matKhau NVARCHAR(12) NOT NULL,
	trangThai NVARCHAR(20) NOT NULL,
	maNhanVien NVARCHAR(10) NOT NULL,
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien) ON DELETE CASCADE
);

-- Tạo bảng CaLam
CREATE TABLE CaLam (
	maCaLam NVARCHAR(12) PRIMARY KEY,
	thoiGianBatDau DateTime NOT NULL,
	thoiGianKetThuc DateTime NOT NULL,
	maNhanVien NVARCHAR(10) NOT NULL,
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien) ON DELETE CASCADE
);

-- Tạo bảng HanhKhach (Khách Hàng)
CREATE TABLE KhachHang (
    maKhachHang NVARCHAR(10) PRIMARY KEY,
    hoTen VARCHAR(50) NOT NULL,
    cccd NVARCHAR(12) UNIQUE NOT NULL,
	soDienThoai NVARCHAR(10) NOT NULL,
	ngaySinh DATE NOT NULL,
	loaiKhachHang NVARCHAR(20) NOT NULL,
);

-- Tạo bảng KhuyenMai
CREATE TABLE KhuyenMai (
	maKhuyenMai NVARCHAR(10) PRIMARY KEY,
	tenKhuyenMai NVARCHAR(30) NOT NULL,
	ngayBatDau DATE NOT NULL,
	ngayKetThuc DATE NOT NULL,
	loaiKhuyenMai NVARCHAR(30) NOT NULL,
	phanTramKhuyenMai FLOAT NOT NULL
);

-- Tạo bảng Tau (Tàu)
CREATE TABLE Tau (
    maTau NVARCHAR(10) PRIMARY KEY,
    tenTau NVARCHAR(30) NOT NULL,
    trangThai NVARCHAR(20) NOT NULL,
    soLuongGhe INT NOT NULL,
    soLuongToaTau INT NOT NULL
);

-- Tạo bảng ToaTau (Toa Tàu)
CREATE TABLE ToaTau (
    maToaTau NVARCHAR(10) PRIMARY KEY,
    maTau NVARCHAR(10) NOT NULL,
    tenTau NVARCHAR(50) NOT NULL,
    loaiToaTau NVARCHAR(20) NOT NULL,
    trangThai NVARCHAR(20) NOT NULL,
    dieuHoa BIT NOT NULL,
    soLuongGhe INT NOT NULL,
    FOREIGN KEY (maTau) REFERENCES Tau(maTau) ON DELETE CASCADE
);

-- Tạo bảng Ghe (Ghế)
CREATE TABLE Ghe (
    maGhe NVARCHAR(3) PRIMARY KEY,
    maToaTau NVARCHAR(10) NOT NULL,
    trangThai VARCHAR(20) NOT NULL,
    FOREIGN KEY (maToaTau) REFERENCES ToaTau(maToaTau) ON DELETE CASCADE
);

-- Tạo bảng ChuyenTau (Chuyến Tàu)
CREATE TABLE ChuyenTau (
    maChuyenTau NVARCHAR(30) PRIMARY KEY,
    maTau NVARCHAR(10) NOT NULL,
    gaKhoiHanh NVARCHAR(20) NOT NULL,
    gaKetThuc NVARCHAR(20) NOT NULL,
    ngayKhoiHanh DATETIME NOT NULL,
	ngayKetThuc DATETIME NOT NULL,
    trangThai BIT NOT NULL,
    soGheDat INT NOT NULL,
    soGheTrong INT NOT NULL,
    FOREIGN KEY (maTau) REFERENCES Tau(maTau) ON DELETE CASCADE
);

-- Tạo bảng Ve (Vé)
CREATE TABLE Ve (
    maVe NVARCHAR(13) PRIMARY KEY,
    maChuyenTau NVARCHAR(30) NOT NULL,
    maGhe NVARCHAR(3) NOT NULL,
    giaVe DECIMAL(10,2) NOT NULL,
	loaiVe NVARCHAR(20) NOT NULL,
    maKhachHang NVARCHAR(10) NOT NULL,
    FOREIGN KEY (maChuyenTau) REFERENCES ChuyenTau(maChuyenTau) ON DELETE CASCADE,
    FOREIGN KEY (maGhe) REFERENCES Ghe(maGhe) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang) ON DELETE CASCADE
);

-- Tạo bảng HoaDon
CREATE TABLE HoaDon (
	maHoaDon NVARCHAR(15) PRIMARY KEY,
	ngayLap DATE NOT NULL,
	thueVAT FLOAT NOT NULL,
	tongTien DECIMAL(18,2) NOT NULL,
	ghiChu NVARCHAR(100),
	maNhanVien NVARCHAR(10) NOT NULL,
	maKhachHang NVARCHAR(10) NOT NULL,
	maVe NVARCHAR(13) NOT NULL,
	maKhuyenMai NVARCHAR(10) NOT NULL,
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien) ON DELETE CASCADE,
	FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang) ON DELETE CASCADE,
	FOREIGN KEY (maVe) REFERENCES Ve(maVe) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai) ON DELETE CASCADE
);

INSERT INTO Tau (maTau, tenTau, trangThai, soLuongGhe, soLuongToaTau) 
VALUES 
('Tau001', 'SaoVang', N'Hoạt động', 48, 7),
('Tau002', 'BacNam', N'Hoạt động', 60, 10),
('Tau003', 'DongDuong', N'Bảo trì', 36, 8),
('Tau004', 'TayNguyen', N'Hoạt động', 54, 9),
('Tau005', 'SongHong', N'Hoạt động', 30, 7),
('Tau006', 'SaiGon', N'Hoạt động', 40, 7),
('Tau007', 'BienDong', N'Bảo trì', 50, 8),
('Tau008', 'Mekong', N'Hoạt động', 26, 9),
('Tau009', 'LangSen', N'Dừng hoạt động', 58, 10),
('Tau010', 'TruongSon', N'Hoạt động', 44, 8);

INSERT INTO ChuyenTau (maChuyenTau, maTau, gaKhoiHanh, gaKetThuc, ngayKhoiHanh, ngayKetThuc, trangThai, soGheDat, soGheTrong)
VALUES
('Hanoi-Saigon-150425-1000-01', 'Tau001', N'Hà Nội', N'Sài Gòn', '2025-04-15 10:00:00', '2025-04-15 20:00:00', 1, 0, 100),
('Hanoi-Saigon-150425-1000-02', 'Tau002', N'Hà Nội', N'Sài Gòn', '2025-04-15 10:00:00', '2025-04-15 20:00:00', 1, 0, 100),
('Saigon-Hanoi-150425-1100-03', 'Tau003', N'Sài Gòn', N'Hà Nội', '2025-04-15 11:00:00', '2025-04-15 21:00:00', 1, 0, 100),
('Saigon-Hanoi-150425-1100-04', 'Tau004', N'Sài Gòn', N'Hà Nội', '2025-04-15 11:00:00', '2025-04-15 21:00:00', 1, 0, 100),
('Hanoi-Hue-150425-1300-05', 'Tau005', N'Hà Nội', N'Huế', '2025-04-15 13:00:00', '2025-04-15 17:00:00', 1, 0, 100),
('Hanoi-Hue-150425-1300-06', 'Tau006', N'Hà Nội', N'Huế', '2025-04-15 13:00:00', '2025-04-15 17:00:00', 1, 0, 100),
('Hue-Hanoi-150425-1400-07', 'Tau007', N'Huế', N'Hà Nội', '2025-04-15 14:00:00', '2025-04-15 18:00:00', 1, 0, 100),
('Hue-Hanoi-150425-1400-08', 'Tau008', N'Huế', N'Hà Nội', '2025-04-15 14:00:00', '2025-04-15 18:00:00', 1, 0, 100),
('Hanoi-DaNang-150425-1500-09', 'Tau009', N'Hà Nội', N'Đà Nẵng', '2025-04-15 15:00:00', '2025-04-15 19:00:00', 1, 0, 100),
('DaNang-Hanoi-150425-1600-10', 'Tau010', N'Đà Nẵng', N'Hà Nội', '2025-04-15 16:00:00', '2025-04-15 20:00:00', 1, 0, 100),
('Hanoi-Saigon-160425-0600-11', 'Tau001', N'Hà Nội', N'Sài Gòn', '2025-04-16 06:00:00', '2025-04-16 16:00:00', 1, 0, 100),
('Saigon-Hanoi-160425-0700-12', 'Tau003', N'Sài Gòn', N'Hà Nội', '2025-04-16 07:00:00', '2025-04-16 17:00:00', 1, 0, 100),
('Hanoi-Saigon-160425-0800-13', 'Tau002', N'Hà Nội', N'Sài Gòn', '2025-04-16 08:00:00', '2025-04-16 18:00:00', 1, 0, 100),
('Saigon-Hue-160425-0900-14', 'Tau004', N'Sài Gòn', N'Huế', '2025-04-16 09:00:00', '2025-04-16 13:00:00', 1, 0, 100),
('Hue-Saigon-160425-1000-15', 'Tau005', N'Huế', N'Sài Gòn', '2025-04-16 10:00:00', '2025-04-16 14:00:00', 1, 0, 100),
('Hue-Saigon-160425-1000-16', 'Tau006', N'Huế', N'Sài Gòn', '2025-04-16 10:00:00', '2025-04-16 14:00:00', 1, 0, 100),
('Hanoi-DaNang-160425-1100-17', 'Tau007', N'Hà Nội', N'Đà Nẵng', '2025-04-16 11:00:00', '2025-04-16 15:00:00', 1, 0, 100),
('DaNang-Hanoi-160425-1200-18', 'Tau008', N'Đà Nẵng', N'Hà Nội', '2025-04-16 12:00:00', '2025-04-16 16:00:00', 1, 0, 100),
('Hanoi-Saigon-160425-1300-19', 'Tau009', N'Hà Nội', N'Sài Gòn', '2025-04-16 13:00:00', '2025-04-16 17:00:00', 1, 0, 100),
('Saigon-Hanoi-160425-1400-20', 'Tau010', N'Sài Gòn', N'Hà Nội', '2025-04-16 14:00:00', '2025-04-16 18:00:00', 1, 0, 100);

SELECT * FROM ChuyenTau

SELECT * FROM ChuyenTau WHERE maTau = 'Tau010'

SELECT * 
FROM ChuyenTau 
WHERE gaKhoiHanh = N'Hà Nội' 
  AND gaKetThuc = N'Sài Gòn'
  AND ngayKhoiHanh = '2025-04-15 10:00:00';

SELECT DISTINCT t.*
FROM ChuyenTau ct JOIN Tau t ON ct.maTau = t.maTau
WHERE (ct.ngayKhoiHanh >= '2025-04-15 18:00:00' OR ct.ngayKhoiHanh <= '2025-04-15 22:00:00') OR (ct.ngayKetThuc <= '2025-04-15 22:00:00' AND ct.ngayKetThuc >= '2025-04-15 18:00:00');

SELECT maTau
FROM Tau
WHERE maTau NOT IN (
    SELECT DISTINCT maTau
    FROM ChuyenTau
    WHERE NOT (
        (ngayKhoiHanh >= '2025-04-15 18:00:00' OR ngayKhoiHanh <= '2025-04-15 22:00:00') OR (ngayKetThuc <= '2025-04-15 22:00:00' AND ngayKetThuc >= '2025-04-15 18:00:00')
    )
);

SELECT *
FROM Tau
WHERE maTau NOT IN (
    SELECT DISTINCT maTau
    FROM ChuyenTau
    WHERE NOT (
        ngayKetThuc < '2025-04-15 18:00:00' OR
        ngayKhoiHanh > '2025-04-15 22:00:00'
    )
);

CREATE TABLE accounts (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

INSERT INTO accounts (username, password)
VALUES ('admin', '123456'), ('user1', '1');




