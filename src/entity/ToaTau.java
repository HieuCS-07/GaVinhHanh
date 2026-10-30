package entity;

import java.util.Objects;

public class ToaTau {
	private String maToaTau;
	private String tenToaTau;
	private int soLuongGhe;
	private LoaiToaTau loaiToaTau;
	private Tau tau;
	private boolean dieuHoa;
	private LoaiTrangThai trangThai;

	public ToaTau() {
		super();
	}

	public ToaTau(String maToaTau, String tenToaTau, int soLuongGhe, LoaiToaTau loaiToaTau, Tau tau, boolean dieuHoa,
			LoaiTrangThai trangThai) {
		super();
		this.maToaTau = maToaTau;
		this.tenToaTau = tenToaTau;
		this.soLuongGhe = soLuongGhe;
		this.loaiToaTau = loaiToaTau;
		this.tau = tau;
		this.dieuHoa = dieuHoa;
		this.trangThai = trangThai;
	}

	public String getMaToa() {
		return maToaTau;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public LoaiToaTau getLoaiToaTau() {
		return loaiToaTau;
	}

	public Tau getTau() {
		return tau;
	}

	public boolean isDieuHoa() {
		return dieuHoa;
	}

	public LoaiTrangThai getTrangThai() {
		return trangThai;
	}

//	public void setMaToa(String maToa) {
//		this.maToa = maToa;
//	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public void setLoaiToaTau(LoaiToaTau loaiToaTau) {
		this.loaiToaTau = loaiToaTau;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	public void setDieuHoa(boolean dieuHoa) {
		this.dieuHoa = dieuHoa;
	}

	public void setTrangThai(LoaiTrangThai trangThai) {
		this.trangThai = trangThai;
	}

	public String getTenToaTau() {
		return tenToaTau;
	}

	public void setTenToaTau(String tenToaTau) {
		this.tenToaTau = tenToaTau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maToaTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToaTau other = (ToaTau) obj;
		return Objects.equals(maToaTau, other.maToaTau);
	}

}
