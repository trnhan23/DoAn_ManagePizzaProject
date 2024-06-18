package QuanLyPizza.DAO;

import QuanLyPizza.DTO.LoaiSP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaiDAO {

    public ArrayList<LoaiSP> getDanhSachLoai()   {
        try {
            String sql = "SELECT * FROM Loai";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<LoaiSP> dsl = new ArrayList<>();
            while (rs.next()) {
                LoaiSP loai = new LoaiSP();
                loai.setMaLoai(rs.getInt(1));
                loai.setTenLoai(rs.getString(2));
                dsl.add(loai);
            }
            return dsl;
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean themLoai(LoaiSP loai)   {
        try {
            String sql = "insert into loai(TenLoai) "
                    + "values ("
                    + "N'" + loai.getTenLoai() + "')";
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(LoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean xoaLoai(int maLoai)  {
        try {
            String sql = "DELETE FROM loai WHERE MaLoai=" + maLoai;
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean suaLoai(int maLoai, String ten) {
        try {
            String sql = "UPDATE Loai SET TenLoai='N" + ten + "' WHERE MaLoai=" + maLoai;
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }

}
