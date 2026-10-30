package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class CaLam {
	private String maCaLam;
	private LocalDateTime thoiGianBatDau;
	private LocalDateTime thoiGianKetThuc;
	private NhanVien nhanVien;

	public CaLam() {
		super();
	}

	public CaLam(String maCaLam, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc, NhanVien nhanVien) {
		super();
		this.maCaLam = maCaLam;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.nhanVien = nhanVien;
	}

	public String getMaCaLam() {
		return maCaLam;
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

//	public void setMaCaLam(String maCaLam) {
//		this.maCaLam = maCaLam;
//	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCaLam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaLam other = (CaLam) obj;
		return Objects.equals(maCaLam, other.maCaLam);
	}

}
