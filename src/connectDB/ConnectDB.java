package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() {
		String url = "jdbc:sqlserver://LTV-HIEUVO\\SQLMASTER:1433;databaseName=GaVinhHanh;encrypt=true;trustServerCertificate=true;";
		String user = "sa";
		String password = "123456789";
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("Kết nối thành công!");
			} else {
				System.out.println("Đã có kết nối đến cơ sở dữ liệu.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
				System.out.println("Ngắt kết nối thành công!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		return con;
	}
}