package QuanLyPizza.DAO;

import QuanLyPizza.DTO.GiamGia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GiamGiaDAO {

    public ArrayList<GiamGia> getDanhSachMaGiam() {
        try {
            String sql = "SELECT * FROM giamgia";
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<GiamGia> dsgg = new ArrayList<>();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGiam(rs.getInt(1));
                gg.setTenGiamGia(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(4));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
                dsgg.add(gg);
            }
            return dsgg;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<GiamGia> getDanhSachMaGiam(int tongTien) {
        try {
            String sql = "SELECT * FROM giamgia where DieuKien < ?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, tongTien);
            ResultSet rs = pre.executeQuery();
            ArrayList<GiamGia> dsgg = new ArrayList<>();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGiam(rs.getInt(1));
                gg.setTenGiamGia(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(4));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
                dsgg.add(gg);
            }
            return dsgg;
        } catch (Exception e) {
        }
        return null;
    }

    public GiamGia getGiamGia(int maGiam) {
        GiamGia gg = null;
        try {
            String sql = "SELECT * FROM giamgia WHERE MaGiam=? ";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, maGiam);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                gg= new GiamGia();
                gg.setMaGiam(maGiam);
                gg.setTenGiamGia(rs.getString(2));
                gg.setPhanTramGiam(rs.getInt(3));
                gg.setDieuKien(rs.getInt(4));
                gg.setNgayBD(rs.getDate(5));
                gg.setNgayKT(rs.getDate(6));
            }
        } catch (SQLException ex) {
            return null;
        }
        return gg;
    }

    public boolean themMaGiam(GiamGia gg) {
        try {
            String sql = "INSERT INTO giamgia(TenGiamGia, PhanTramGiam, DieuKien, NgayBD, NgayKT) "
                    + "VALUES (?, ?, ?, ?, ?)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, gg.getTenGiamGia());
            pre.setInt(2, gg.getPhanTramGiam());
            pre.setInt(3, gg.getDieuKien());

            pre.setTimestamp(4, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean suaMaGiam(GiamGia gg) {
        try {
            String sql = "UPDATE giamgia SET TenGiamGia=?, PhanTramGiam=?, DieuKien=?, NgayBD=?, NgayKT=? WHERE MaGiam=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, gg.getTenGiamGia());
            pre.setInt(2, gg.getPhanTramGiam());
            pre.setInt(3, gg.getDieuKien());

            pre.setTimestamp(4, new java.sql.Timestamp(gg.getNgayBD().getTime()));
            pre.setTimestamp(5, new java.sql.Timestamp(gg.getNgayKT().getTime()));

            pre.setInt(6, gg.getMaGiam());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMaGiam(int ma) {
        try {
            String sql = "delete from giamgia where MaGiam=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ma);

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
