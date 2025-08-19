package dao;

import entity.*;
import connectDB.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GheDAO {
    public List<Ghe> getAllGhe() {
        List<Ghe> dsGhe = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM Ghe";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maGhe = rs.getString("maGhe");
                String maToaTau = rs.getString("maToaTau");
                boolean trangThai = rs.getBoolean("trangThai");

                ToaTauDAO toaTauDAO = new ToaTauDAO();
                ToaTau toaTau = toaTauDAO.getToaTauByID(maToaTau);

                Ghe ghe = new Ghe(maGhe, toaTau, trangThai);
                dsGhe.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGhe;
    }

    public Ghe getGheByID(String maGhe) {
        Ghe ghe = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM Ghe WHERE maGhe = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maGhe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maToaTau = rs.getString("maToaTau");
                boolean trangThai = rs.getBoolean("trangThai");

                ToaTauDAO toaTauDAO = new ToaTauDAO();
                ToaTau toaTau = toaTauDAO.getToaTauByID(maToaTau);

                ghe = new Ghe(maGhe, toaTau, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ghe;
    }

    public boolean updateTrangThaiGhe(String maGhe, boolean trangThai) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE Ghe SET trangThai = ? WHERE maGhe = ?";
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, trangThai);
            stmt.setString(2, maGhe);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
}