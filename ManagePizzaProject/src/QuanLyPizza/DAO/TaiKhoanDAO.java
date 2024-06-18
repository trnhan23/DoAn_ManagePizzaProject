package QuanLyPizza.DAO;

import QuanLyPizza.BUS.DangNhapBUS;
import QuanLyPizza.DTO.TaiKhoan;
import java.awt.Dialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TaiKhoanDAO {

    public boolean themTaiKhoan(int maNV, String tenDangNhap, String quyen) {
        try {
            String sql = "INSERT INTO taikhoan(MaNV, TenDangNhap, MatKhau, Quyen,TrangThai) "
                    + "VALUES (?, ?, ?, ?,1)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            pre.setString(2, tenDangNhap);
            String hashedPass = hashSHA512(tenDangNhap);
            pre.setString(3, hashedPass);
            pre.setString(4, quyen);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = '" + tenDangNhap + "'";
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNV) {
        try {
            String sql = "SELECT TenDangNhap FROM TaiKhoan WHERE MaNV=" + maNV;
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            String hashed = hashSHA512(tenDangNhap);
            pre.setString(1, hashed);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean datLaiQuyen(int maNV, String quyen) {
        try {
            String sql = "UPDATE TaiKhoan SET Quyen=? WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, quyen);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public String getQuyenTheoMa(int maNV) {
        try {
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNV=" + maNV;
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean khoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE TaiKhoan SET TrangThai=0 WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    

    public boolean moKhoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE TaiKhoan SET TrangThai=1 WHERE MaNV=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNV=? AND MatKhau=?";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            String hashedOldPass = hashSHA512(matKhauCu);
            String hashedNewPass = hashSHA512(matKhauMoi);
            pre.setString(1, hashedNewPass);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaNhanVien());
            pre.setString(3, hashedOldPass);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public int getTrangThai(int ma) {
        try {
            String sql = "SELECT TrangThai FROM TaiKhoan WHERE MaNV=" + ma;
            Connection conn = new MyConnect().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
    
    public  String hashSHA512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(input.getBytes());

            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
           
            e.printStackTrace();
            return null;
        }
    }
}
