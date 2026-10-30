package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
    private String maKhachHang;
    private String hoTen;
    private String soDienThoai;
    private String CCCD;
    private LocalDate ngaySinh;
    private LoaiKhachHang loaiKhachHang;
    
	public KhachHang() {
		super();
	}

	public KhachHang(String maKhachHang, String hoTen, String soDienThoai, String cCCD, LocalDate ngaySinh,
			LoaiKhachHang loaiKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		CCCD = cCCD;
		this.ngaySinh = ngaySinh;
		this.loaiKhachHang = loaiKhachHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public String getCCCD() {
		return CCCD;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public LoaiKhachHang getLoaiKhachHang() {
		return loaiKhachHang;
	}

//	public void setMaKhachHang(String maKhachHang) {
//		this.maKhachHang = maKhachHang;
//	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}
    
	
    
}
