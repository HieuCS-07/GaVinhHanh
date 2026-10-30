package dao;

import entity.*;
import connectDB.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToaTauDAO {
    public List<ToaTau> getAllToaTau() {
        List<ToaTau> dsToaTau = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM ToaTau";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maToaTau = rs.getString("maToaTau");
                String tenToaTau = rs.getString("tenTau");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String maTau = rs.getString("maTau");
                String loaiToaTau = rs.getString("loaiToaTau");
                LoaiToaTau loaiToaTau2 = null;
                if("Ghế cứng".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GHE_CUNG;
                if("Ghế mền".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GHE_MEN;
                if("Giường nằm".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GIUONG_NAM;
                Boolean dieuHoa = rs.getBoolean("dieuHoa");
                String trangThai = rs.getString("trangThai");
                LoaiTrangThai loaiTrangThai = null;
                if("Hoạt động".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.HOAT_DONG;
                if("Dừng hoạt động".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.DUNG_HOAT_DONG;
                if("Bảo trì".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.BAO_TRI;
                
                TauDAO tauDAO = new TauDAO();
                Tau tau = tauDAO.timTauTheoMa(maTau);

                ToaTau toaTau = new ToaTau(maToaTau, tenToaTau, soLuongGhe, loaiToaTau2, tau, dieuHoa, loaiTrangThai);
                dsToaTau.add(toaTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsToaTau;
    }

    public ToaTau getToaTauByID(String maToaTau) {
        ToaTau toaTau = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM ToaTau WHERE maToaTau = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maToaTau);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tenToaTau = rs.getString("tenTau");
                int soLuongGhe = rs.getInt("soLuongGhe");
                String maTau = rs.getString("maTau");
                String loaiToaTau = rs.getString("loaiToaTau");
                LoaiToaTau loaiToaTau2 = null;
                if("Ghế cứng".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GHE_CUNG;
                if("Ghế mền".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GHE_MEN;
                if("Giường nằm".equals(loaiToaTau))
                	loaiToaTau2 = LoaiToaTau.GIUONG_NAM;
                Boolean dieuHoa = rs.getBoolean("dieuHoa");
                String trangThai = rs.getString("trangThai");
                LoaiTrangThai loaiTrangThai = null;
                if("Hoạt động".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.HOAT_DONG;
                if("Dừng hoạt động".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.DUNG_HOAT_DONG;
                if("Bảo trì".equals(trangThai))
                	loaiTrangThai = LoaiTrangThai.BAO_TRI;
                
                TauDAO tauDAO = new TauDAO();
                Tau tau = tauDAO.timTauTheoMa(maTau);

                toaTau = new ToaTau(maToaTau, tenToaTau, soLuongGhe, loaiToaTau2, tau, dieuHoa, loaiTrangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toaTau;
    }
}