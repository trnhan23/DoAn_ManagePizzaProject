package QuanLyPizza.BUS;

import QuanLyPizza.DAO.HoaDonDAO;
import QuanLyPizza.DTO.HoaDon;
import MyCustom.MyDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonBUS {

    private ArrayList<HoaDon> listHoaDon;
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();

    public ArrayList<HoaDon> getListHoaDon()   {
        listHoaDon = hoaDonDAO.getListHoaDon();
        return listHoaDon;
    }

    public void luuHoaDon(int maKH, String nhanVien, int tongTien, String ghiChu,String thanhToan)  {
        HoaDon hd = new HoaDon();
        String[] arrNV = nhanVien.split(" - ");
        int maNV = Integer.parseInt(arrNV[0]);
        hd.setMaNV(maNV);
        hd.setMaKH(maKH);
        hd.setGhiChu(ghiChu);
        hd.setTongTien(tongTien);
        hd.setThanhToan(thanhToan);
        hoaDonDAO.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonDAO.getMaHoaDonMoiNhat();
    }

    public HoaDon getHoaDon(String maHD) {
        int ma = Integer.parseInt(maHD);
        for (HoaDon hd : listHoaDon) {
            if (hd.getMaHD() == ma)
                return hd;
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoGia(String min, String max) {
        try {
            int minPrice = Integer.parseInt(min);
            int maxPrice = Integer.parseInt(max);
            ArrayList<HoaDon> dshd = new ArrayList<>();
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() >= minPrice && hd.getTongTien() <= maxPrice)
                    dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            //new MyDialog("Hãy nhập khoảng giá hợp lệ", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoNgay(Date min, Date max) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = min;
            Date maxDate = max;

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            //ArrayList<HoaDon> dshd = hoaDonDAO.getListHoaDon(dateMin, dateMax);
            listHoaDon = hoaDonDAO.getListHoaDon(dateMin, dateMax);
            
            return listHoaDon;
        } catch (Exception e) {
            new MyDialog("Hãy nhập khoảng ngày hợp lệ!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }
}
