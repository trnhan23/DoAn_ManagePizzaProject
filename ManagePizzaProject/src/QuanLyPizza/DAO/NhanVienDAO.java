package QuanLyPizza.DAO;

import QuanLyPizza.DTO.NhanVien;
import MyCustom.MyDialog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            String sql = "SELECT * FROM nhanvien";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<NhanVien> dssv = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();

                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setNgaysinh(rs.getDate(6));
                nv.setSdt(rs.getString(7));
                nv.setCccd(rs.getString(8));
                nv.setEmail(rs.getString(9));
                nv.setDiachi(rs.getString(10));

                dssv.add(nv);
            }
            return dssv;
        } catch (SQLException e) {
        }

        return null;
    }

    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(0, maNV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setChucVu(rs.getString(5));
                nv.setNgaysinh(rs.getDate(6));
                nv.setSdt(rs.getString(7));
                nv.setCccd(rs.getString(8));
                nv.setEmail(rs.getString(9));
                nv.setDiachi(rs.getString(10));
            }
        } catch (SQLException e) {
            return null;
        }

        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "UPDATE nhanvien SET Ho=?, Ten=?, GioiTinh=?, ChucVu=?, NgaySinh =?, SDT=?, CCCD =?, Email=?,DiaChi=? WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            pre.setTimestamp(5, new java.sql.Timestamp(nv.getNgaysinh().getTime()));
            pre.setString(6, nv.getSdt());
            pre.setString(7, nv.getCccd());
            pre.setString(8, nv.getEmail());
            pre.setString(9, nv.getDiachi());
            pre.setInt(10, nv.getMaNV());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean deleteNhanVien(int maNV) {
        boolean result = false;
        try {
            String sql = "DELETE FROM taikhoan WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean themNhanVien(NhanVien nv) {
        boolean result = false;
        try {
            String sql = "INSERT INTO nhanvien(Ho, Ten, GioiTinh, ChucVu,NgaySinh,SDT,CCCD, Email,DiaChi ) " +
                    "VALUES(?, ?, ?, ?,?,?,?,?,?)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            pre.setTimestamp(5, new java.sql.Timestamp(nv.getNgaysinh().getTime()));
            pre.setString(6, nv.getSdt());
            pre.setString(7, nv.getCccd());
            pre.setString(8, nv.getEmail());
            pre.setString(9, nv.getDiachi());
            result = pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean nhapExcel(NhanVien nv) {
        try {
            String sql = "DELETE * FROM nhanvien; " +
                    "INSERT INTO NhanVien(Ho, Ten, GioiTinh, ChucVu) " +
                    "VALUES(?, ?, ?, ?)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getChucVu());
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }
}
