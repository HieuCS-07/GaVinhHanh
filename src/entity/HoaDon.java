package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayLap;
	private double thueVAT;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private KhuyenMai khuyenMai;
	private Ve ve;
	private String ghiChu;
	private double tongTien;
	
	public HoaDon() {
		super();
	}

	public HoaDon(String maHoaDon, LocalDate ngayLap, double thueVAT, NhanVien nhanVien, KhachHang khachHang,
			KhuyenMai khuyenMai, Ve ve, String ghiChu, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.thueVAT = thueVAT;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.khuyenMai = khuyenMai;
		this.ve = ve;
		this.ghiChu = ghiChu;
		this.tongTien = tongTien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public double getThueVAT() {
		return thueVAT;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public Ve getVe() {
		return ve;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public void setThueVAT(double thueVAT) {
		this.thueVAT = thueVAT;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	
	
}
