package QuanLyPizza.BUS;

import QuanLyPizza.DAO.NhanVienDAO;
import QuanLyPizza.DTO.NhanVien;
import MyCustom.MyDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NhanVienBUS {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
     //   if (this.listNhanVien == null) {
            docDanhSach();
     //   }
        return this.listNhanVien;
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String chucVu, Date dob, String sdt, String cccd, String email, String diachi) {
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        sdt = sdt.trim();
        cccd = cccd.trim();
        email = email.trim();
        diachi = diachi.trim();
        if (ten.equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chức vụ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

// Tạo một đối tượng Calendar đại diện cho ngày hiện tại
        Calendar todayCalendar = Calendar.getInstance();

// Trừ đi 18 năm từ ngày hiện tại
        todayCalendar.add(Calendar.YEAR, -18);

// So sánh ngày sinh với ngày hiện tại trừ 18 năm
        if (dobCalendar.after(todayCalendar)) {
            // Nếu ngày sinh nhỏ hơn ngày hiện tại trừ 18 năm, nghĩa là đủ tuổi
            new MyDialog("Nhân viên chưa đủ 18 tuổi!", MyDialog.ERROR_DIALOG);
            return false;
        }

        // Kiểm tra tính hợp lệ của số điện thoại
        if (!sdt.matches("\\d{10,11}")) {
            new MyDialog("Số điện thoại không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của số CCCD
        if (!cccd.matches("\\d{9,12}")) {
            new MyDialog("Số CCCD không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của email
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
           new MyDialog("Email không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của địa chỉ
        if (diachi.isEmpty()) {
            new MyDialog("Vui lòng không để trống địa chỉ!", MyDialog.ERROR_DIALOG);
            return false;
        }

        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        nv.setNgaysinh(dob);
        nv.setSdt(sdt);
        nv.setCccd(cccd);
        nv.setEmail(email);
        nv.setDiachi(diachi);
        boolean flag = nvDAO.themNhanVien(nv);
        if (!flag) {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ho, String ten, String gioiTinh, String chucVu, Date dob, String sdt, String cccd, String email, String diachi) {
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        sdt = sdt.trim();
        cccd = cccd.trim();
        email = email.trim();
        diachi = diachi.trim();
        if (ten.equals("")) {
            new MyDialog("Tên không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chức vụ không được để trống!", MyDialog.ERROR_DIALOG);
            return false;
        }

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

// Tạo một đối tượng Calendar đại diện cho ngày hiện tại
        Calendar todayCalendar = Calendar.getInstance();

// Trừ đi 18 năm từ ngày hiện tại
        todayCalendar.add(Calendar.YEAR, -18);

// So sánh ngày sinh với ngày hiện tại trừ 18 năm
        if (dobCalendar.after(todayCalendar)) {
            // Nếu ngày sinh nhỏ hơn ngày hiện tại trừ 18 năm, nghĩa là đủ tuổi
            new MyDialog("Nhân viên chưa đủ 18 tuổi!", MyDialog.ERROR_DIALOG);
            return false;
        }

        // Kiểm tra tính hợp lệ của số điện thoại
        if (!sdt.matches("\\d{10,11}")) {
            new MyDialog("Số ĐT không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của số CCCD
        if (!cccd.matches("\\d{9,12}")) {
            new MyDialog("Số CCCD không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của email
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
           new MyDialog("Email không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }

// Kiểm tra tính hợp lệ của địa chỉ
        if (diachi.isEmpty()) {
            new MyDialog("Vui lòng không để trống địa chỉ!", MyDialog.ERROR_DIALOG);
            return false;
        }

        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        nv.setNgaysinh(dob);
        nv.setSdt(sdt);
        nv.setCccd(cccd);
        nv.setEmail(email);
        nv.setDiachi(diachi);
        boolean flag = nvDAO.updateNhanVien(nv);
        if (!flag) {
            new MyDialog("Cập nhập thất bại!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cập nhập thành công!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa)
                    || nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa)
                    || nv.getSdt().toLowerCase().contains(tuKhoa)|| nv.getCccd().toLowerCase().contains(tuKhoa)|| nv.getEmail().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new MyDialog("Chưa chọn nhân viên!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu) {
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.nhapExcel(nv);
        return flag;
    }
}
