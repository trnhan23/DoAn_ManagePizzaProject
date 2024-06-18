package QuanLyPizza.DAO;

import QuanLyPizza.DTO.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DangNhapDAO {
    TaiKhoanDAO tkDao = new  TaiKhoanDAO();
    public TaiKhoan dangNhap(TaiKhoan tk) throws Exception {
        try {
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap=? AND MatKhau=? AND TrangThai=1";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tk.getTenDangNhap());
            String hashedPass = tkDao.hashSHA512(tk.getMatKhau());
            pre.setString(2, hashedPass);
            ResultSet rs = pre.executeQuery();
            TaiKhoan tkLogin = null;
            if (rs.next()) {
                tkLogin = tk;
                tkLogin.setMaNhanVien(rs.getInt("MaNV"));
                tkLogin.setQuyen(rs.getString("Quyen"));
            }
            return tkLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}