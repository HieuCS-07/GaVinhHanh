package entity;

import java.util.Objects;

public class TaiKhoan {
	private String maTaiKhoan;
	private String tenDangNhap;
	private String matKhau;
	private NhanVien nhanVien;
	private boolean trangThai;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String maTaiKhoan, String tenDangNhap, String matKhau, NhanVien nhanVien, boolean trangThai) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
		this.trangThai = trangThai;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

//	public void setMaTaiKhoan(String maTaiKhoan) {
//		this.maTaiKhoan = maTaiKhoan;
//	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTaiKhoan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maTaiKhoan, other.maTaiKhoan);
	}

}
