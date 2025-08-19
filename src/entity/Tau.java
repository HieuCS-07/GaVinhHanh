package entity;

import java.util.Objects;

public class Tau {
	private String maTau;
	private String tenTau;
	private int soLuongToaTau;
	private int soLuongGhe;
	private LoaiTrangThai loaiTrangThaiTau;

	public Tau() {
		super();
	}

	public Tau(String maTau, String tenTau, int soLuongToaTau, int soLuongGhe, LoaiTrangThai loaiTrangThaiTau) {
		super();
		this.maTau = maTau;
		this.tenTau = tenTau;
		this.soLuongToaTau = soLuongToaTau;
		this.soLuongGhe = soLuongGhe;
		this.loaiTrangThaiTau = loaiTrangThaiTau;
	}

	public String getMaTau() {
		return maTau;
	}

	public String getTenTau() {
		return tenTau;
	}

	public int getSoLuongToaTau() {
		return soLuongToaTau;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public LoaiTrangThai getLoaiTrangThaiTau() {
		return loaiTrangThaiTau;
	}

	public void setMaTau(String maTau) {
		this.maTau = maTau;
	}

	public void setTenTau(String tenTau) {
		this.tenTau = tenTau;
	}

	public void setSoLuongToaTau(int soLuongToaTau) {
		this.soLuongToaTau = soLuongToaTau;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public void setLoaiTrangThaiTau(LoaiTrangThai loaiTrangThaiTau) {
		this.loaiTrangThaiTau = loaiTrangThaiTau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tau other = (Tau) obj;
		return Objects.equals(maTau, other.maTau);
	}

}
