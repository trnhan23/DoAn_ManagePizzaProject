package QuanLyPizza.GUI;

import QuanLyPizza.BUS.CTHoaDonBUS;
import QuanLyPizza.BUS.HoaDonBUS;
import MyCustom.MyDialog;
import QuanLyPizza.BUS.GiamGiaBUS;
import QuanLyPizza.DTO.GiamGia;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class XuatHoaDonGUI extends JDialog {

    private HoaDonBUS hoadonBUS = new HoaDonBUS();
    private CTHoaDonBUS ctHoaDonBUS = new CTHoaDonBUS();
    private GiamGiaBUS giamgiaBUS = new GiamGiaBUS();
    private DlgTimKhach timKhachUI = new DlgTimKhach();
    private DlgTimMaGiam timMaUI;
    private String thanhToan="";

    public XuatHoaDonGUI() {
        init();
        ImageIcon icon1 = new ImageIcon("image/ManagerUI/pizza-shop.png"); // Đường dẫn tới biểu tượng của bạn
        this.setIconImage(icon1.getImage());
        checkBanHang = false;
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        GiamGia gg = giamgiaBUS.getGiamGia(4);
        timMaUI.maGiamTimDuoc=gg;
        txtMaGiam.setText(timMaUI.maGiamTimDuoc.getMaGiam() + " - " + timMaUI.maGiamTimDuoc.getTenGiamGia());
        
        radCash.setSelected(true);
        
        buttonGroup1.add(radBank);
        buttonGroup1.add(radCash);
        buttonGroup1.add(radMomo);
        
        customEvents();
    }
    public static final Logger logger = Logger.getLogger(XuatHoaDonGUI.class);
     void init(){
        PropertyConfigurator.configure("src\\Log\\log4j.properties");
        setLocationRelativeTo(null);
    }
    private ArrayList<Vector> dsGioHang;
    private int tongTien;
    private String nhanVien;

    public XuatHoaDonGUI(ArrayList<Vector> dsGioHang, int tongTien, Object nhanVien) {
        this();
        this.tongTien = tongTien;
        this.dsGioHang = dsGioHang;
        this.nhanVien = (String) nhanVien;
        DecimalFormat dcf = new DecimalFormat("###,###");
        txtTongTien.setText(dcf.format(tongTien));
    }

    private void customEvents() {
        txtTenKhach.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void removeUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void insertUpdate(DocumentEvent e) {
                checkKhachMa();
            }
        });

        txtMaGiam.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void removeUpdate(DocumentEvent e) {
                checkKhachMa();
            }

            public void insertUpdate(DocumentEvent e) {
                checkKhachMa();
            }
        });
    }

    private void checkKhachMa() {
        if (!txtTenKhach.getText().equals("") && !txtMaGiam.getText().equals("")) {
            btnThanhToan.setEnabled(true);
        } else {
            btnThanhToan.setEnabled(false);
        }
    }

    private void xuLyHienThiHoaDon() {
        txtHoaDon.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");
        
        String[] parts = nhanVien.split("- "); 
        String nhanvien = parts[1];
        
        String[] partKHstr = txtTenKhach.getText().split("- ");
        String khachhang = partKHstr[1];
        
        String thanhtoan="";
        
        if("cash".equals(thanhToan)){
            thanhtoan = "Tiền mặt";
        }
        else if("bank".equals(thanhToan)){
            thanhtoan = "Chuyển khoản";
        }
        else
        {
            thanhtoan ="Momo";
        }
        
        
        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>HOÁ ĐƠN THANH TOÁN</h1>";
        hd += "Nhân viên: " + nhanvien + "<br/>";
        hd += "Ngày lập: " + dtf.format(now) + "<br/>";
        hd += "Khách hàng: " + khachhang + "<br/>";
        hd += "Hình thức thanh toán: " + thanhtoan + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>STT</th>"
                + "<th>Tên SP</th>"
                + "<th>Số lượng</th>"
                + "<th>Đơn giá</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        int i=0;
        for (Vector vec : dsGioHang) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + (i+1) + "</td>";
            hd += "<td style='text-align:left;'>" + vec.get(1) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(2) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(3) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(4) + "</td>";
            hd += "</tr>";
            i++;
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng cộng</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        if (timMaUI.maGiamTimDuoc != null) {
            int percent = 0;
            // lấy phần trăm giảm
            percent = timMaUI.maGiamTimDuoc.getPhanTramGiam();
            if (tongTien >= timMaUI.maGiamTimDuoc.getDieuKien()) {
                tongTien = tongTien - (tongTien * percent / 100);
            } else {
                new MyDialog("Không đủ điều kiện nhận ưu đãi!", MyDialog.ERROR_DIALOG);
                btnTimMaGiam.setEnabled(true);
                return;
            }
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Khuyến mãi</td>";
        hd += "<td style='text-align:center;'>" + timMaUI.maGiamTimDuoc.getPhanTramGiam() + "%" + "</td>";
        hd += "</tr>";
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Thành tiền</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(tongTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        txtHoaDon.setText(hd);
        txtTongTien.setText(dcf.format(tongTien));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHoaDon = new javax.swing.JEditorPane();
        txtTenKhach = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaGiam = new javax.swing.JTextField();
        btnTimMaGiam = new javax.swing.JButton();
        btnTimKhach = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        radCash = new javax.swing.JRadioButton();
        radBank = new javax.swing.JRadioButton();
        radMomo = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("Chi tiết hoá đơn");
        jPanel1.add(jLabel1);

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setEnabled(false);
        btnThanhToan.setPreferredSize(new java.awt.Dimension(128, 45));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel2.add(btnThanhToan);

        btnInHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInHoaDon.setText("In hoá đơn");
        btnInHoaDon.setEnabled(false);
        btnInHoaDon.setPreferredSize(new java.awt.Dimension(128, 45));
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel2.add(btnInHoaDon);

        txtHoaDon.setEditable(false);
        jScrollPane1.setViewportView(txtHoaDon);

        txtTenKhach.setEditable(false);
        txtTenKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Khách hàng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Thanh toán");

        txtMaGiam.setEditable(false);
        txtMaGiam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnTimMaGiam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimMaGiam.setText("...");
        btnTimMaGiam.setPreferredSize(new java.awt.Dimension(40, 28));
        btnTimMaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaGiamActionPerformed(evt);
            }
        });

        btnTimKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTimKhach.setText("...");
        btnTimKhach.setPreferredSize(new java.awt.Dimension(40, 28));
        btnTimKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhachActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Mã giảm");

        radCash.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        radCash.setText("Tiền mặt");

        radBank.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        radBank.setText("Chuyển khoản");

        radMomo.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        radMomo.setText("MoMo");
        radMomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radMomoActionPerformed(evt);
            }
        });

        jLabel7.setText("Số TK:  3144273467 (BIDV)");

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\LT NN TT\\PROJECT\\ManagePizzaProject\\image\\momo.jpg")); // NOI18N

        jLabel9.setText("Số MoMo: 0326344084");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaGiam, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhach)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radBank, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radCash, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addGap(158, 158, 158)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radMomo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnTimMaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radBank, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radCash, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radMomo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, radBank});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radMomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radMomoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radMomoActionPerformed

    public static boolean checkBanHang = false;

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {
        checkBanHang = false;
        if (txtTenKhach.getText().equals("")) {
            new MyDialog("Xin chọn khách hàng", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtMaGiam.getText().equals("")) {
            new MyDialog("Xin chọn mã giảm", MyDialog.ERROR_DIALOG);
            return;
        }
         thanhToan = "cash";
        if(radBank.isSelected())
            thanhToan="bank";
        if(radMomo.isSelected())
            thanhToan="momo";
        xuLyHienThiHoaDon();
        btnInHoaDon.setEnabled(true);
       
        hoadonBUS.luuHoaDon(DlgTimKhach.khachHangTimDuoc.getMaKH(), nhanVien, tongTien, "Đã thanh toán",thanhToan);
        logger.info("Đã thanh toán: "+tongTien+" qua: "+thanhToan);
        for (Vector vec : dsGioHang) {
            String maSP = vec.get(0) + "";
            String soLuong = vec.get(2) + "";
            String donGia = vec.get(3) + "";
            String thanhTien = vec.get(4) + "";
            ctHoaDonBUS.addCTHoaDon(maSP, soLuong, donGia, thanhTien);
        }
        btnThanhToan.setEnabled(false);
        btnTimMaGiam.setEnabled(false);
        btnTimKhach.setEnabled(false);
        radBank.setEnabled(false);
        radCash.setEnabled(false);
        radMomo.setEnabled(false);
        checkBanHang = true;
    }
    
    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!txtHoaDon.getText().equals("")) {
                txtHoaDon.print();
                this.dispose();
            }
        } catch (PrinterException ex) {
        }
    }


    private void btnTimKhachActionPerformed(java.awt.event.ActionEvent evt) {
        timKhachUI.setVisible(true);
        if (timKhachUI.khachHangTimDuoc != null) {
            txtTenKhach.setText(timKhachUI.khachHangTimDuoc.getMaKH() + " - " + timKhachUI.khachHangTimDuoc.getHo() + " " + timKhachUI.khachHangTimDuoc.getTen());
        }
    }


    private void btnTimMaGiamActionPerformed(java.awt.event.ActionEvent evt) {
        timMaUI = new DlgTimMaGiam(tongTien);
        timMaUI.thanhtoan=true;
        timMaUI.tongTien=tongTien;
        timMaUI.setVisible(true);
        if (timMaUI.maGiamTimDuoc != null) {
            txtMaGiam.setText(timMaUI.maGiamTimDuoc.getMaGiam() + " - " + timMaUI.maGiamTimDuoc.getTenGiamGia());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKhach;
    private javax.swing.JButton btnTimMaGiam;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radBank;
    private javax.swing.JRadioButton radCash;
    private javax.swing.JRadioButton radMomo;
    private javax.swing.JEditorPane txtHoaDon;
    private javax.swing.JTextField txtMaGiam;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
