package QuanLyPizza.DAO;

import QuanLyPizza.DTO.HoaDon;

import java.sql.*;
import java.util.ArrayList;

public class HoaDonDAO {
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String sql = "select hd.MaHD, CONCAT(kh.Ho,' ',kh.Ten) as HoTenKH, CONCAT(nv.Ho,' ',nv.Ten) as HoTenNV, hd.NgayLap,hd.TongTien,hd.GhiChu,hd.ThanhToan  from hoadon hd join khachhang kh on hd.MaKH = kh.MaKH join nhanvien nv on hd.MaNV = nv.MaNV";
            Connection conn = new MyConnect().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setTenNV(rs.getString(3));
                Timestamp timestamp = rs.getTimestamp(4);
                hd.setNgayLap(new java.util.Date(timestamp.getTime()));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                hd.setThanhToan(rs.getString(7));
                dshd.add(hd);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dshd;
    }

    public boolean addHoaDon(HoaDon hd)   {
        boolean result = false;
        try {
            String sql1 = "UPDATE khachhang SET TongChiTieu=TongChiTieu+" + hd.getTongTien() + " WHERE MaKH=" + hd.getMaKH();
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(sql1);
            String sql = "INSERT INTO hoadon(MaKH, MaNV, NgayLap, TongTien, GhiChu,ThanhToan) VALUES(?, ?, ?, ?, ?,?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, hd.getMaKH());
            prep.setInt(2, hd.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, hd.getTongTien());
            prep.setString(5, hd.getGhiChu());
            prep.setString(6, hd.getThanhToan());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public int getMaHoaDonMoiNhat() {
        try {
            String sql = "SELECT MAX(maHD) FROM hoadon";
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<HoaDon> getListHoaDon(Date dateMin, Date dateMax) {
        try {
            
        	String sql = "SELECT hd.MaHD, CONCAT(kh.Ho,' ',kh.Ten) as HoTenKH, CONCAT(nv.Ho,' ',nv.Ten) as HoTenNV, hd.NgayLap,hd.TongTien,hd.GhiChu,hd.ThanhToan  FROM hoadon hd join khachhang kh on hd.MaKH = kh.MaKH join nhanvien nv on hd.MaNV = nv.MaNV WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
        	Connection conn = new MyConnect().getConnection();
        	PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();

            ArrayList<HoaDon> dshd = new ArrayList<>();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                 hd.setTenKH(rs.getString(2));
                hd.setTenNV(rs.getString(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                 hd.setThanhToan(rs.getString(7));
                dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
