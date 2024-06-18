/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyPizza.DAO;

import QuanLyPizza.DTO.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author User
 */
public class KhachHangDAO {
	
    public ArrayList<KhachHang> getListKhachHang()   {
        try {
            String sql = "SELECT * FROM KhachHang WHERE TinhTrang=1";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
                kh.setEmail(rs.getString(7));
                dskh.add(kh);
            }
            return dskh;
        } catch (SQLException ex) {
        }
        return null;
    }

    public KhachHang getKhachHang(int maKH)   {
        KhachHang kh = null;
        try {
            String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, maKH);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setTongChiTieu(rs.getInt(5));
                kh.setEmail(rs.getString(7));
            }
        } catch (SQLException ex) {
            return null;
        }
        return kh;
    }

    public boolean addKhachHang(KhachHang kh)  {
        
        try {
            String sql = "INSERT INTO khachhang(Ho,Ten,SDT,TongChiTieu,TinhTrang,Email) VALUES(?,?,?,0,1,?)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
           
            prep.setString(1, kh.getHo());
            prep.setString(2, kh.getTen());
            prep.setString(3, kh.getSDT());
            prep.setString(4, kh.getEmail());
           
            return prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        
    }

    public boolean deleteKhachHang(int maKH)   {
        boolean result = false;
        try {
            String sql = "UPDATE khachhang SET TinhTrang=0 WHERE MaKH=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, maKH);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateKhachHang(int maKH, KhachHang kh)   {
        boolean result = false;
        try {
            String sql = "UPDATE khachhang SET Ho=?, Ten=?, SDT=?, Email = ? WHERE MaKH=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, kh.getHo());
            prep.setString(2, kh.getTen());
            prep.setString(3, kh.getSDT());
            prep.setString(4, kh.getEmail());
            prep.setInt(5, maKH);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu)   {
        boolean result = false;
        try {
            String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
            Connection conn = new MyConnect().getConnection();
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
}
