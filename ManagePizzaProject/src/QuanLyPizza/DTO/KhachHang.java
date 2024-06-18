package QuanLyPizza.DTO;

public class KhachHang {
    private int maKH;
    private String ho;
    private String ten;
    private String sdt;
    private String email;
    private int tongChiTieu;
    
    public KhachHang() {
    }

    public KhachHang(int maKH, String ho, String ten, String sdt, String email, int tongChiTieu) {
        this.maKH = maKH;
        this.ho = ho;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.tongChiTieu = tongChiTieu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSDT() {
        return sdt;
    }

    public void setSDT(String sdt) {
        this.sdt = sdt;
    }

    public int getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(int tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }


}
