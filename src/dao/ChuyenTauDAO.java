package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.*;
import entity.ChuyenTau;
import entity.LoaiTrangThai;
import entity.Tau;

public class ChuyenTauDAO {

	private static ChuyenTauDAO instance;
	private static TauDAO tauDAO;

	public static ChuyenTauDAO getInstance() {
		return instance == null ? instance = new ChuyenTauDAO() : instance;
	}

	public ArrayList<ChuyenTau> getAll() {
		String sql = "SELECT * FROM ChuyenTau";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);
			tauDAO = new TauDAO();
			ArrayList<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh = resultSet.getString(3);
				String gaKetThuc = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				
				
				list.add(
						new ChuyenTau(maChuyenTau, gaKhoiHanh, gaKetThuc, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean add(ChuyenTau entity) {
		String sql = "INSERT INTO ChuyenTau(maChuyenTau, maTau, gaKhoiHanh, gaKetThuc, ngayKhoiHanh, ngayKetThuc, trangThai, soGheDat, soGheTrong) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, entity.getMaChuyenTau());
			statement.setString(2, entity.getTau().getMaTau());
			statement.setString(3, entity.getGaKhoiHanh());
			statement.setString(4, entity.getGaKetThuc());
			statement.setTimestamp(5, Timestamp.valueOf(entity.getNgayKhoiHanh()));
			statement.setTimestamp(6, Timestamp.valueOf(entity.getNgayKetThuc()));
			statement.setBoolean(7, entity.isTrangThai());
			statement.setInt(8, 0);
			statement.setInt(9, 0);
			int count = statement.executeUpdate();

			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean update(ChuyenTau entity) {
		String sql = "UPDATE ChuyenTau SET gaKhoiHanh = ?, gaKetThuc = ?, ngayKhoiHanh = ?, ngayKetThuc = ?, trangThai = ?, soGheDat = ?, soGheTrong = ? WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(8, entity.getMaChuyenTau());
			statement.setString(1, entity.getGaKhoiHanh());
			statement.setString(2, entity.getGaKetThuc());
			statement.setTimestamp(3, Timestamp.valueOf(entity.getNgayKhoiHanh()));
			statement.setTimestamp(4, Timestamp.valueOf(entity.getNgayKetThuc()));
			statement.setBoolean(5, entity.isTrangThai());
			statement.setInt(6, 0);
			statement.setInt(7, 0);
			int count = statement.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public ChuyenTau timKiemTheoMa(String maTim) {
		String sql = "SELECT * FROM ChuyenTau WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTim);
			resultSet = statement.executeQuery();
			ArrayList<ChuyenTau> list = new ArrayList<>();
			if (resultSet.next()) {
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh = resultSet.getString(3);
				String gaKetThuc = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				return new ChuyenTau(maChuyenTau, gaKhoiHanh, gaKetThuc, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<ChuyenTau> timKiemTheoMaTau(String maTauTim) {
		String sql = "SELECT * FROM ChuyenTau WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTauTim);
			resultSet = statement.executeQuery();
			ArrayList<ChuyenTau> list = new ArrayList<>();
			
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh = resultSet.getString(3);
				String gaKetThuc = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh, gaKetThuc, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public ArrayList<ChuyenTau> timKiemChuyenTau(String gaKhoiHanh, String gaKetThuc, LocalDateTime ngayKhoiHanh, LocalDateTime ngayKetThuc) {
		String sql = "SELECT * FROM ChuyenTau WHERE gaKhoiHanh = ? AND gaKetThuc = ? AND CAST(ngayKhoiHanh AS DATE) = ? AND CAST(ngayKetThuc AS DATE) = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, gaKhoiHanh);
			statement.setString(2, gaKetThuc);
			statement.setDate(3, Date.valueOf(ngayKhoiHanh.toLocalDate()));
			statement.setDate(4, Date.valueOf(ngayKetThuc.toLocalDate()));
			resultSet = statement.executeQuery();

			ArrayList<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh1 = resultSet.getString(3);
				String gaKetThuc1 = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh1, gaKetThuc1, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<ChuyenTau> timKiemChuyenTauMotChieu(String gaKhoiHanh, String gaKetThuc, LocalDateTime ngayDi) {
		String sql = "SELECT * FROM ChuyenTau WHERE gaKhoiHanh = ? AND gaKetThuc = ? ";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, gaKhoiHanh);
			statement.setString(2, gaKetThuc);
//			statement.setDate(3, Date.valueOf(ngayDi.toLocalDate()));
//			System.out.println(Date.valueOf(ngayDi.toLocalDate()));
			resultSet = statement.executeQuery();

			ArrayList<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				System.out.println(1);
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh1 = resultSet.getString(3);
				String gaKetThuc1 = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh1, gaKetThuc1, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<ChuyenTau> timKiemChuyenTauKhuHoi(String gaKhoiHanh, String gaKetThuc, LocalDateTime ngayDi, LocalDateTime ngayVe) {
		String sql = "SELECT * FROM ChuyenTau WHERE (gaKhoiHanh = ? AND gaKetThuc = ? AND CAST(ngayKhoiHanh AS DATE) = ?) OR ((gaKhoiHanh = ? AND gaKetThuc = ? AND CAST(ngayKhoiHanh AS DATE) = ?))";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, gaKhoiHanh);
			statement.setString(2, gaKetThuc);
			statement.setDate(3, Date.valueOf(ngayDi.toLocalDate()));
			statement.setString(4, gaKetThuc);
			statement.setString(5, gaKhoiHanh);
			statement.setDate(6, Date.valueOf(ngayDi.toLocalDate()));
			resultSet = statement.executeQuery();

			ArrayList<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString(1);
				String maTau = resultSet.getString(2);
				Tau tau = tauDAO.timTauTheoMa(maTau);
				String gaKhoiHanh1 = resultSet.getString(3);
				String gaKetThuc1 = resultSet.getString(4);
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp(5).toLocalDateTime();
				LocalDateTime thoiGianKetThuc = resultSet.getTimestamp(6).toLocalDateTime();
				Boolean trangThai = resultSet.getBoolean(7);

				list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh1, gaKetThuc1, thoiGianKhoiHanh, thoiGianKetThuc, tau, trangThai));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public boolean xoaChuyenTau(String maChuyenTau) {
		String sql = "DELETE FROM ChuyenTau WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maChuyenTau);

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int demSoTauTrenCungChuyen(String gaKhoiHanh, String gaKetThuc, LocalDateTime ngayKhoiHanh) {
		String sql = "SELECT COUNT(*) FROM ChuyenTau WHERE gaKhoiHanh = ? AND gaKetThuc = ? AND ngayKhoiHanh = ?";

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, gaKhoiHanh);
			statement.setString(2, gaKetThuc);
			statement.setTimestamp(3, Timestamp.valueOf(ngayKhoiHanh));

			ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1); // số lượng chuyến đã có
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
