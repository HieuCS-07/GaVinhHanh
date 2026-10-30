package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.ConnectDB;
import entity.LoaiTrangThai;
import entity.Tau;

public class TauDAO {

	private static TauDAO instance;

	public static TauDAO getInstance() {
		return instance == null ? instance = new TauDAO() : instance;
	}

	public ArrayList<Tau> getAll() {
		String sql = "SELECT * FROM Tau";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			ArrayList<Tau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maTau = resultSet.getString(1);
				String tenTau = resultSet.getString(2);
				String trangThai = resultSet.getString(3);
				int soLuongGhe = resultSet.getInt(4);
				int soLuongToa = resultSet.getInt(5);

				LoaiTrangThai loaiTrangThaiTau;
				if (trangThai.equals("Hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
				else if (trangThai.equals("Dừng hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
				else
					loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;

				list.add(new Tau(maTau, tenTau, soLuongToa, soLuongGhe, loaiTrangThaiTau));
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

	public boolean add(Tau tau) {
		String sql = "INSERT INTO Tau (maTau, tenTau, trangThai, soLuongGhe, soLuongToaTau) VALUES (?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			String trangThai = switch (tau.getLoaiTrangThaiTau()) {
			case HOAT_DONG -> "Hoạt động";
			case DUNG_HOAT_DONG -> "Dừng hoạt động";
			case BAO_TRI -> "Bảo trì";
			};
			statement = con.prepareStatement(sql);
			statement.setString(1, tau.getMaTau());
			statement.setString(2, tau.getTenTau());
			statement.setString(3, trangThai);
			statement.setInt(4, tau.getSoLuongGhe());
			statement.setInt(5, tau.getSoLuongToaTau());
			int count = statement.executeUpdate();

			return count > 0;
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

	// Phương thức xóa tàu theo mã tàu
	public boolean delete(String maTau) {
		String sql = "DELETE FROM Tau WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTau); // Thiết lập mã tàu vào tham số câu lệnh

			int count = statement.executeUpdate(); // Thực thi câu lệnh xóa

			return count > 0; // Nếu xóa thành công, count > 0
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

		return false; // Trả về false nếu xóa không thành công
	}

	public boolean update(Tau tau) {
		String sql = "UPDATE Tau SET tenTau = ?, trangThai = ?, soLuongGhe = ?, soLuongToaTau = ? WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(5, tau.getMaTau());
			statement.setString(1, tau.getTenTau());
			statement.setString(2, tau.getLoaiTrangThaiTau().toString());
			statement.setInt(3, tau.getSoLuongGhe());
			statement.setInt(4, tau.getSoLuongToaTau());

			return statement.executeUpdate() > 0;
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

	// Lấy thông tin tàu theo mã tàu
	public Tau timTauTheoMa(String maTauTim) {
		String sql = "SELECT * FROM Tau WHERE maTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maTauTim);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String maTau = resultSet.getString(1);
				String tenTau = resultSet.getString(2);
				String trangThai = resultSet.getString(3);
				int soLuongGhe = resultSet.getInt(4);
				int soLuongToa = resultSet.getInt(5);

				LoaiTrangThai loaiTrangThaiTau;
				if (trangThai.equals("Hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
				else if (trangThai.equals("Dừng hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
				else
					loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;

				return new Tau(maTau, tenTau, soLuongGhe, soLuongToa, loaiTrangThaiTau);
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

	public ArrayList<Tau> timTauTheoNgay(LocalDateTime ngayKhoiHanh, LocalDateTime ngayKetThuc) {
		String sql = "	SELECT *\r\n" + "FROM Tau\r\n" + "WHERE maTau NOT IN (\r\n" + "    SELECT DISTINCT maTau\r\n"
				+ "    FROM ChuyenTau\r\n" + "    WHERE NOT (\r\n" + "        ngayKetThuc < ? OR\r\n"
				+ "        ngayKhoiHanh > ?\r\n" + "    )\r\n" + ");";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setTimestamp(1, Timestamp.valueOf(ngayKhoiHanh));
			statement.setTimestamp(2, Timestamp.valueOf(ngayKetThuc));
			resultSet = statement.executeQuery();

			ArrayList<Tau> list = new ArrayList<Tau>();

			while (resultSet.next()) {
				String maTau = resultSet.getString(1);
				String tenTau = resultSet.getString(2);
				String trangThai = resultSet.getString(3);
				int soLuongGhe = resultSet.getInt(4);
				int soLuongToa = resultSet.getInt(5);

				LoaiTrangThai loaiTrangThaiTau;
				if (trangThai.equals("Hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.HOAT_DONG;
				else if (trangThai.equals("Dừng hoạt động"))
					loaiTrangThaiTau = LoaiTrangThai.DUNG_HOAT_DONG;
				else
					loaiTrangThaiTau = LoaiTrangThai.BAO_TRI;

				list.add(new Tau(maTau, tenTau, soLuongGhe, soLuongToa, loaiTrangThaiTau));
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

	// Phương thức tạo mã tàu tự động
	public String generateMaTau() {
		String sql = "SELECT MAX(maTau) FROM Tau";
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				String lastMaTau = resultSet.getString(1); // Lấy mã tàu cuối cùng
				if (lastMaTau != null && lastMaTau.startsWith("Tau")) {
					// Lấy số cuối cùng của mã tàu
					int lastNumber = Integer.parseInt(lastMaTau.substring(3));
					// Tăng số lên 1 và tạo mã tàu mới
					return "Tau" + String.format("%03d", lastNumber + 1);
				}
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
		return "Tau001";// Nếu không có tàu nào, trả về mã tàu mặc định
	}

//	public List<Tau> layTTTauTheoTrangThai(TrangThaiTau trangThai) {
//		String sql = "SELECT * FROM Tau WHERE trangThai = ?";
//
//		Connection con = null;
//		PreparedStatement stmt = null;
//		ResultSet resultSet = null;
//		try {
//			con = Database.getInstance().getConnection();
//			stmt = con.prepareStatement(sql);
//			stmt.setInt(1, trangThai.getTrangThai());
//			resultSet = stmt.executeQuery();
//
//			List<Tau> list = new ArrayList<>();
//			while (resultSet.next()) {
//				String maTau = resultSet.getString("maTau");
//				String tenTau = resultSet.getString("tenTau");
//				int soToa = resultSet.getInt("soToa");
//				LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
//				String nhaSanXuat = resultSet.getString("nhaSanXuat");
//				float tocDoTB = resultSet.getFloat("tocDoTB");
//				float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
//				String ghiChu = resultSet.getString("ghiChu");
//
//				ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString("maChuyenTau"));
//
//				list.add(new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
//						chuyenTau));
//			}
//
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//				if (resultSet != null)
//					resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;
//	}
//
//	public Tau getTauByMaChuyenTau(String maChuyenTau) {
//		String sql = "SELECT * FROM Tau WHERE maChuyenTau = ?";
//
//		Connection con = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//
//		try {
//			con = Database.getInstance().getConnection();
//			preparedStatement = con.prepareStatement(sql);
//			preparedStatement.setString(1, maChuyenTau);
//			resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//				String maTau = resultSet.getString("maTau");
//				String tenTau = resultSet.getString("tenTau");
//				int soToa = resultSet.getInt("soToa");
//				LocalDate namSanXuat = resultSet.getDate("namSanXuat").toLocalDate();
//				String nhaSanXuat = resultSet.getString("nhaSanXuat");
//				float tocDoTB = resultSet.getFloat("tocDoTB");
//				float tocDoToiDa = resultSet.getFloat("tocDoToiDa");
//				TrangThaiTau trangThai = TrangThaiTau.fromInt(resultSet.getInt("trangThai"));
//				String ghiChu = resultSet.getString("ghiChu");
//
//				// Khởi tạo ChuyenTau từ maChuyenTau
//				ChuyenTau chuyenTau = new ChuyenTau(maChuyenTau);
//
//				// Tạo và trả về đối tượng Tau
//				return new Tau(maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu,
//						chuyenTau);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (resultSet != null)
//					resultSet.close();
//				if (preparedStatement != null)
//					preparedStatement.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;
//	}
//
//	public Map<String, Integer> layThongTinGhe(String maTau) {
//		String sql = """
//				SELECT
//				    COUNT(ghe.maGheTau) AS tongSoGhe,
//				    SUM(CASE WHEN ghe.trangThai = 'TRONG' THEN 1 ELSE 0 END) AS soGheConLai
//				FROM
//				    Tau tau
//				JOIN
//				    ToaTau toa ON tau.maTau = toa.maTau
//				JOIN
//				    GheTau ghe ON toa.maToaTau = ghe.maToaTau
//				WHERE
//				    tau.maTau = ?
//				""";
//
//		try {
//			Map<String, Integer> result = new HashMap<>();
//			Connection con = Database.getInstance().getConnection();
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, maTau);
//			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				result.put("tongSoGhe", rs.getInt("tongSoGhe"));
//				result.put("soGheConLai", rs.getInt("soGheConLai"));
//			}
//			return result;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//
//		}
//	}
}
