package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private double phanTramGiam;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private LoaiKhuyenMai loaiKhuyenMai;

	public KhuyenMai() {
		super();
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, double phanTramGiam, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, LoaiKhuyenMai loaiKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.phanTramGiam = phanTramGiam;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.loaiKhuyenMai = loaiKhuyenMai;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public double getPhanTramGiam() {
		return phanTramGiam;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public LoaiKhuyenMai getLoaiKhuyenMai() {
		return loaiKhuyenMai;
	}

//	public void setMaKhuyenMai(String maKhuyenMai) {
//		this.maKhuyenMai = maKhuyenMai;
//	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public void setPhanTramGiam(double phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public void setLoaiKhuyenMai(LoaiKhuyenMai loaiKhuyenMai) {
		this.loaiKhuyenMai = loaiKhuyenMai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

}
