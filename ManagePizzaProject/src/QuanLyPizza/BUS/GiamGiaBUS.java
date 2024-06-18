package QuanLyPizza.BUS;

import QuanLyPizza.DAO.GiamGiaDAO;
import QuanLyPizza.DTO.GiamGia;
import MyCustom.MyDialog;

import java.util.ArrayList;
import java.util.Date;

public class GiamGiaBUS {

    private ArrayList<GiamGia> listGiamGia = null;
    private GiamGiaDAO giamGiaDAO = new GiamGiaDAO();
    private GiamGia giamGia = new GiamGia();

    public GiamGiaBUS() {
        docDanhSach();
    }
    public void docGiamGia(int maGiam){
        this.giamGia= giamGiaDAO.getGiamGia(maGiam);
    }
    public void docDanhSach() {
        this.listGiamGia = giamGiaDAO.getDanhSachMaGiam();
        
    }
    public void docDanhSach(int tongTien) {
        this.listGiamGia = giamGiaDAO.getDanhSachMaGiam(tongTien);
    }

    public ArrayList<GiamGia> getDanhSachGiamGia(int tongTien) {
//        if (this.listGiamGia == null)
            docDanhSach(tongTien);
        return this.listGiamGia;
    }
    public ArrayList<GiamGia> getDanhSachGiamGia() {
       // if (this.listGiamGia == null)
            docDanhSach();
        return this.listGiamGia;
    }
    public GiamGia getGiamGia(int maGiam){
        docGiamGia(maGiam);
        return this.giamGia;
    }
    public boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.themMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean xoaMaGiam(String ma){
        try {
            int maGiam = Integer.parseInt(ma);
            return giamGiaDAO.xoaMaGiam(maGiam);}
        catch(Exception ex){
            return false;
        }
    }
    public boolean suaMaGiam(String ma, String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ma.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int maGiam = Integer.parseInt(ma);
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setMaGiam(maGiam);
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.suaMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
