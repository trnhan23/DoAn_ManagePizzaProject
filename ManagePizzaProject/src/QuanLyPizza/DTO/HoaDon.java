package QuanLyPizza.DTO;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class HoaDon {
    private int maHD;
    private int maKH;
    private String tenKH;
   
    private int maNV;
    private String tenNV;
    private Date ngayLap;
    private int tongTien;
    private String ghiChu;
    private String thanhToan;
    

    public HoaDon() {
    }

    public HoaDon(int maHD, int maKH, String tenKH, int maNV, String tenNV, Date ngayLap, int tongTien, String ghiChu, String thanhToan) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.thanhToan = thanhToan;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
