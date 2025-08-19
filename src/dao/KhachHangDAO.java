package dao;

import entity.*;
import connectDB.ConnectDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> dsKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                String CCCD = rs.getString("cccd");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String loaiKhachHang = rs.getString("loaiKhachHang");
                LoaiKhachHang loaiKhachHang1 = null;
                if("Trẻ dưới 6 tuổi".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.TRE_EM_DUOI_6;
                if("Trẻ dưới từ 6 đến 10".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.TRE_EM_TU_6_DEN_10;
                if("Người lớn".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.NGUOI_LON;
                if("Người cao tuổi".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.NGUOI_CAO_TUOI;
                if("Học sinh sinh viên".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.HOC_SINH_SINH_VIEN;
                

                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, CCCD, ngaySinh, loaiKhachHang1);
                dsKhachHang.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public KhachHang getKhachHangByID(String maKhachHang) {
        KhachHang khachHang = null;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                String CCCD = rs.getString("CCCD");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String loaiKhachHang = rs.getString("loaiKhachHang");
                LoaiKhachHang loaiKhachHang1 = null;
                if("Trẻ dưới 6 tuổi".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.TRE_EM_DUOI_6;
                if("Trẻ dưới từ 6 đến 10".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.TRE_EM_TU_6_DEN_10;
                if("Người lớn".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.NGUOI_LON;
                if("Người cao tuổi".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.NGUOI_CAO_TUOI;
                if("Học sinh sinh viên".equals(loaiKhachHang))
                	loaiKhachHang1 = LoaiKhachHang.HOC_SINH_SINH_VIEN;

                khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, CCCD, ngaySinh, loaiKhachHang1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public boolean addKhachHang(KhachHang khachHang) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, CCCD, ngaySinh, maLoaiKhachHang) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, khachHang.getMaKhachHang());
            stmt.setString(2, khachHang.getHoTen());
            stmt.setString(3, khachHang.getSoDienThoai());
            stmt.setString(4, khachHang.getCCCD());
            stmt.setDate(5, Date.valueOf(khachHang.getNgaySinh()));
            String loaiKhachHang = switch (khachHang.getLoaiKhachHang()) {
			case TRE_EM_DUOI_6 -> "Trẻ dưới 6 tuổi";
			case TRE_EM_TU_6_DEN_10 -> "Trẻ dưới từ 6 đến 10";
			case NGUOI_LON -> "Người lớn";
			case NGUOI_CAO_TUOI -> "Người cao tuổi";
			case HOC_SINH_SINH_VIEN -> "Học sinh sinh viên";
			};
            stmt.setString(6, loaiKhachHang);
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