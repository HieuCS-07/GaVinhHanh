package dao;

import entity.*;
import connectDB.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeDAO {
	public List<Ve> getAllVe() {
		List<Ve> dsVe = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM Ve";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maVe = rs.getString("maVe");
				String loaiVe = rs.getString("loaiVe");
				String maKhachHang = rs.getString("maKhachHang");
				String maGhe = rs.getString("maGhe");
				String maChuyenTau = rs.getString("maChuyenTau");
				double giaVe = rs.getDouble("giaVe");

				// Lấy thông tin các đối tượng liên quan từ các DAO tương ứng
				LoaiVe loaiVe1 = null;
				if ("Khứ hồi".equals(loaiVe))
					loaiVe1 = LoaiVe.KHU_HOI;
				if ("Một chiều".equals(loaiVe))
					loaiVe1 = LoaiVe.MOT_CHIEU;

				KhachHangDAO khachHangDAO = new KhachHangDAO();
				KhachHang khachHang = khachHangDAO.getKhachHangByID(maKhachHang);

				GheDAO gheDAO = new GheDAO();
				Ghe ghe = gheDAO.getGheByID(maGhe);

				ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
				ChuyenTau chuyenTau = chuyenTauDAO.timKiemTheoMa(maChuyenTau);

				Ve ve = new Ve(maVe, loaiVe1, giaVe, khachHang, ghe, chuyenTau);
				dsVe.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVe;
	}

	public boolean createVe(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "INSERT INTO Ve (maVe, maLoaiVe, maKhachHang, maGhe, maChuyenTau) VALUES (?, ?, ?, ?, ?)";
			String loaiVe = switch (ve.getLoaiVe()) {
			case MOT_CHIEU -> "Một chiều";
			case KHU_HOI -> "Khứ hồi";
			};
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ve.getMaVe());
			stmt.setString(2, loaiVe);
			stmt.setString(3, ve.getKhachHang().getMaKhachHang());
			stmt.setString(4, ve.getGhe().getMaGhe());
			stmt.setString(5, ve.getChuyenTau().getMaChuyenTau());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean updateVe(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "UPDATE Ve SET maLoaiVe = ?, maKhachHang = ?, maGhe = ?, maChuyenTau = ? WHERE maVe = ?";
			String loaiVe = switch (ve.getLoaiVe()) {
			case MOT_CHIEU -> "Một chiều";
			case KHU_HOI -> "Khứ hồi";
			};
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loaiVe);
			stmt.setString(2, ve.getKhachHang().getMaKhachHang());
			stmt.setString(3, ve.getGhe().getMaGhe());
			stmt.setString(4, ve.getChuyenTau().getMaChuyenTau());
			stmt.setString(5, ve.getMaVe());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean deleteVe(String maVe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "DELETE FROM Ve WHERE maVe = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maVe);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public Ve getVeByID(String maVe) {
		Ve ve = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM Ve WHERE maVe = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maVe);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String loaiVe = rs.getString("loaiVe");
				String maKhachHang = rs.getString("maKhachHang");
				String maGhe = rs.getString("maGhe");
				String maChuyenTau = rs.getString("maChuyenTau");
				double giaVe = rs.getDouble("giaVe");

				// Lấy thông tin các đối tượng liên quan từ các DAO tương ứng
				LoaiVe loaiVe1 = null;
				if ("Khứ hồi".equals(loaiVe))
					loaiVe1 = LoaiVe.KHU_HOI;
				if ("Một chiều".equals(loaiVe))
					loaiVe1 = LoaiVe.MOT_CHIEU;

				KhachHangDAO khachHangDAO = new KhachHangDAO();
				KhachHang khachHang = khachHangDAO.getKhachHangByID(maKhachHang);

				GheDAO gheDAO = new GheDAO();
				Ghe ghe = gheDAO.getGheByID(maGhe);

				ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
				ChuyenTau chuyenTau = chuyenTauDAO.timKiemTheoMa(maChuyenTau);

				ve = new Ve(maVe, loaiVe1, giaVe, khachHang, ghe, chuyenTau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ve;
	}

	public List<Ve> getVeByKhachHang(String maKhachHang) {
		List<Ve> dsVe = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM Ve WHERE maKhachHang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maKhachHang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maVe = rs.getString("maVe");
				String loaiVe = rs.getString("loaiVe");
				String maGhe = rs.getString("maGhe");
				String maChuyenTau = rs.getString("maChuyenTau");
				double giaVe = rs.getDouble("giaVe");

				// Lấy thông tin các đối tượng liên quan từ các DAO tương ứng
				LoaiVe loaiVe1 = null;
				if ("Khứ hồi".equals(loaiVe))
					loaiVe1 = LoaiVe.KHU_HOI;
				if ("Một chiều".equals(loaiVe))
					loaiVe1 = LoaiVe.MOT_CHIEU;

				KhachHangDAO khachHangDAO = new KhachHangDAO();
				KhachHang khachHang = khachHangDAO.getKhachHangByID(maKhachHang);

				GheDAO gheDAO = new GheDAO();
				Ghe ghe = gheDAO.getGheByID(maGhe);

				ChuyenTauDAO chuyenTauDAO = new ChuyenTauDAO();
				ChuyenTau chuyenTau = chuyenTauDAO.timKiemTheoMa(maChuyenTau);

				Ve ve = new Ve(maVe, loaiVe1, giaVe, khachHang, ghe, chuyenTau);
				dsVe.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVe;
	}
}