package entity;

import java.util.Objects;

public class Ve {
	private String maVe;
	private LoaiVe loaiVe;
	private double giaVe;
	private KhachHang khachHang;
	private Ghe ghe;
	private ChuyenTau chuyenTau;
	
	public Ve() {
		super();
	}
	
	public Ve(String maVe, LoaiVe loaiVe, double giaVe, KhachHang khachHang, Ghe ghe, ChuyenTau chuyenTau) {
		super();
		this.maVe = maVe;
		this.loaiVe = loaiVe;
		this.khachHang = khachHang;
		this.ghe = ghe;
		this.chuyenTau = chuyenTau;
	}

	public String getMaVe() {
		return maVe;
	}

	public LoaiVe getLoaiVe() {
		return loaiVe;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public Ghe getGhe() {
		return ghe;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public void setLoaiVe(LoaiVe loaiVe) {
		this.loaiVe = loaiVe;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	
	
	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}
	
	

}
