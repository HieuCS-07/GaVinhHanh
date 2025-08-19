package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNhanVien;
    private String hoTen;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String CCCD;
    private String soDienThoai;
    private boolean trangThai;
    private LoaiNhanVien loaiNhanVien;
    private LocalDate ngayVaoLam;
    private String queQuan;
    
	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien, String hoTen, LocalDate ngaySinh, boolean gioiTinh, String cCCD,
			String soDienThoai, boolean trangThai, LoaiNhanVien loaiNhanVien, LocalDate ngayVaoLam, String queQuan) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		CCCD = cCCD;
		this.soDienThoai = soDienThoai;
		this.trangThai = trangThai;
		this.loaiNhanVien = loaiNhanVien;
		this.ngayVaoLam = ngayVaoLam;
		this.queQuan = queQuan;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public String getCCCD() {
		return CCCD;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public LoaiNhanVien getLoaiNhanVien() {
		return loaiNhanVien;
	}

	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}

	public String getQueQuan() {
		return queQuan;
	}

//	public void setMaNhanVien(String maNhanVien) {
//		this.maNhanVien = maNhanVien;
//	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	
	
	
}

