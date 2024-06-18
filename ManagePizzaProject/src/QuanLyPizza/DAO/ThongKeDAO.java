package QuanLyPizza.DAO;

import QuanLyPizza.BUS.SanPhamBUS;
import QuanLyPizza.DTO.SanPham;
import QuanLyPizza.DTO.ThongKe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author User
 */
public class ThongKeDAO {
ThongKe thongKe = new ThongKe();
    public ThongKe getThongKe(int nam) {
        
        int[] tongThuQuy = new int[4];
        thongKe.setSoLuongSP(getTongSoLuongSP());
        thongKe.setSoLuongKH(getSoLuongKhachHang());
        thongKe.setSoLuongNV(getSoLuongNhanVien());
        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        thongKe.setTopSanPhamBanChay(getTopBanChay(nam));
        return thongKe;
    }
    
     public ThongKe getThongKe(String ngayBD,String ngayKT) {
         //ThongKe thongKe = new ThongKe();
         int[] tongTienTheo = new int[3];
         tongTienTheo[0]= getTongTienMoMo(ngayBD, ngayKT);
         tongTienTheo[1]= getTongTienChuyenKhoan(ngayBD, ngayKT);
         tongTienTheo[2]= getTongTienTienMat(ngayBD, ngayKT);
         thongKe.setTongTienTheoLoai(tongTienTheo);
         thongKe.setTopSanPhamBanChay(getTopBanChay(ngayBD,ngayKT));
         return thongKe;
     }

    private ArrayList<SanPham> getTopBanChay(int nam) {
        try {
            String sql = "SELECT TOP 5 MaSP, SUM(SoLuong) AS DaBan FROM cthoadon AS cth JOIN hoadon AS hd ON cth.MaHD = hd.MaHD WHERE YEAR(hd.NgayLap) = ? GROUP BY MaSP ORDER BY DaBan DESC;";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, nam);
            ResultSet rs = prep.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            SanPhamBUS spBUS = new SanPhamBUS();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setTenSP(spBUS.getTenSP(sp.getMaSP()));
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }

    private ArrayList<SanPham> getTopBanChay(String ngayBD, String ngayKT) {
        try {
            String sql = "SELECT TOP 5 MaSP, SUM(SoLuong) AS DaBan FROM cthoadon AS cth JOIN hoadon AS hd ON cth.MaHD = hd.MaHD WHERE hd.NgayLap  BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120) GROUP BY MaSP ORDER BY DaBan DESC;";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, ngayBD);
            prep.setString(2, ngayKT);
            ResultSet rs = prep.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            SanPhamBUS spBUS = new SanPhamBUS();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setTenSP(spBUS.getTenSP(sp.getMaSP()));
                dssp.add(sp);
            }
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }

    private int getTongSoLuongSP() {
        try {
            Connection conn = new MyConnect().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM sanpham");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; //kiểu String do có số 0 ở phía trước
        String thangKetThuc = "04"; //kiểu String do có số 0 ở phía trước
        String[] kq = new String[2];
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "04";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "07";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "10";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
        String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    private int getTongThuQuy(int nam, int quy) {
        String[] dateString = getDateString(nam, quy);
        try {
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    
    private int getTongTienMoMo(String ngayBD, String ngayKT) {
        
        try {
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120) AND ThanhToan = 'momo'");
            prep.setString(1, ngayBD);
            prep.setString(2, ngayKT);
           
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    private int getTongTienChuyenKhoan(String ngayBD, String ngayKT) {
        
        try {
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120) AND ThanhToan = 'bank'");
            prep.setString(1, ngayBD);
            prep.setString(2, ngayKT);
           
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    private int getTongTienTienMat(String ngayBD, String ngayKT) {
        
        try {
            Connection conn = new MyConnect().getConnection();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120) AND ThanhToan = 'cash'");
            prep.setString(1, ngayBD);
            prep.setString(2, ngayKT);
           
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    

    private int getSoLuongNhanVien() {
        try {
            Connection conn = new MyConnect().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM nhanvien");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private int getSoLuongKhachHang() {
        try {
            Connection conn = new MyConnect().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM khachhang");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public double getDoanhThuThang(int thang, int nam) {
        try {
            String thangBD = nam + "-" + thang + "-01";
            if (thang == 12) {
                nam++;
                thang = 0;
            }

            String thangKT = nam + "-" + (thang + 1) + "-01";
            String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120)";

            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, thangBD);
            pre.setString(2, thangKT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return Double.parseDouble(rs.getInt(1) + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nam;
    }

    public double getDoanhThuNgay(LocalDate ngay) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String ngayBD = ngay.format(formatter);

            String ngayKT = ngay.plusDays(1).format(formatter);
            String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120)";

            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ngayBD);
            pre.setString(2, ngayKT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // hoặc giá trị mặc định khác tùy thuộc vào trường hợp cụ thể của bạn
    }

    public double getDoanhThuNgay(String ngayBD, String ngayKT) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayLap BETWEEN CONVERT(DATE, ?, 120) AND CONVERT(DATE, ?, 120)";

            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ngayBD);
            pre.setString(2, ngayKT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // hoặc giá trị mặc định khác tùy thuộc vào trường hợp cụ thể của bạn
    }

    public double abc(int thang, int nam) {
        try {
            String d1 = nam + "-" + thang + "-01";
            String d2 = nam + "-" + (thang + 1) + "-01";
            String sql = "SELECT SUM(TongTien) FROM HoaDon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            Connection conn = new MyConnect().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, d1);
            pre.setString(2, d2);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }
}
