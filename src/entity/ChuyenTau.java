package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {
	private String maChuyenTau;
	private String gaKhoiHanh;
	private String gaKetThuc;
	private LocalDateTime ngayKhoiHanh;
	private LocalDateTime ngayKetThuc;
	private Tau tau;
	private boolean trangThai;
//    private int soGheDaDat;
//    private int soGheTrong;

	public ChuyenTau() {
		super();
	}

	public ChuyenTau(String maChuyenTau, String gaKhoiHanh, String gaKetThuc, LocalDateTime ngayKhoiHanh,
			LocalDateTime ngayKetThuc, Tau tau, boolean trangThai) {
		super();
		this.maChuyenTau = maChuyenTau;
		this.gaKhoiHanh = gaKhoiHanh;
		this.gaKetThuc = gaKetThuc;
		this.ngayKhoiHanh = ngayKhoiHanh;
		this.ngayKetThuc = ngayKetThuc;
		this.tau = tau;
		this.trangThai = trangThai;
//		this.soGheDaDat = soGheDaDat;
//		this.soGheTrong = soGheTrong;
	}

	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public String getGaKhoiHanh() {
		return gaKhoiHanh;
	}

	public String getGaKetThuc() {
		return gaKetThuc;
	}

	public LocalDateTime getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}

	public Tau getTau() {
		return tau;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

//	public int getSoGheDaDat() {
//		return soGheDaDat;
//	}
//
//	public int getSoGheTrong() {
//		return soGheTrong;
//	}

	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public void setGaKhoiHanh(String gaKhoiHanh) {
		this.gaKhoiHanh = gaKhoiHanh;
	}

	public void setGaKetThuc(String gaKetThuc) {
		this.gaKetThuc = gaKetThuc;
	}

	public void setNgayKhoiHanh(LocalDateTime ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}

	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

//	private void setSoGheDaDat(int soGheDaDat) {
//		this.soGheDaDat = soGheDaDat;
//	}
//
//	public void setSoGheTrong(int soGheTrong) {
//		this.soGheTrong = soGheTrong;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(maChuyenTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maChuyenTau, other.maChuyenTau);
	}

	public int getSoGheTrong() {
		return 100;
	}

}
