package entity;

import java.util.Objects;

public class Ghe {
	private String maGhe;
	private ToaTau toaTau;
	private boolean trangThai;

	public Ghe() {
		super();
	}

	public Ghe(String maGhe, ToaTau toaTau, boolean trangThai) {
		super();
		this.maGhe = maGhe;
		this.toaTau = toaTau;
		this.trangThai = trangThai;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public ToaTau getToaTau() {
		return toaTau;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

//	public void setMaGhe(String maGhe) {
//		this.maGhe = maGhe;
//	}

	public void setToaTau(ToaTau toaTau) {
		this.toaTau = toaTau;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGhe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghe other = (Ghe) obj;
		return Objects.equals(maGhe, other.maGhe);
	}

}
