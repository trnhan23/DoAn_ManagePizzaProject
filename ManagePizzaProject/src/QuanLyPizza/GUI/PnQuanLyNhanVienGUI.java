package QuanLyPizza.GUI;

import MyCustom.XuLyFileExcel;
import MyCustom.MyDialog;
import MyCustom.TransparentPanel;
import MyCustom.MyTable;
import MyCustom.ImagePanel;
import static Main.Main.changLNF;
import QuanLyPizza.BUS.DangNhapBUS;

import QuanLyPizza.BUS.NhanVienBUS;
import QuanLyPizza.BUS.PhanQuyenBUS;
import QuanLyPizza.BUS.TaiKhoanBUS;
import QuanLyPizza.DTO.NhanVien;
import QuanLyPizza.DTO.PhanQuyen;
import static QuanLyPizza.GUI.PnQuanLyKhuyenMaiGUI.logger;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PnQuanLyNhanVienGUI extends JPanel {

    public PnQuanLyNhanVienGUI() {
        changLNF("Windows");
        init();
        addControlsNhanVien();
        addEventsNhanVien();
        addEventsPhanQuyen();
    }
    public static final Logger logger = Logger.getLogger(PnQuanLyKhuyenMaiGUI.class);

    void init() {
        PropertyConfigurator.configure("src\\Log\\log4j.properties");
        //setLocationRelativeTo(null);
    }
    private PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    PhanQuyen quyen = PhanQuyenBUS.quyenTK;

    JLabel lblTabbedNhanVien, lblTabbedQuyen;
    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");
    final Color colorPanel = new Color(247, 247, 247);
    CardLayout cardNhanVienGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    JTextField txtMaNV, txtHo, txtTen, txtChucVu, txtTimNV;

    JTextField txtSDT, txtDiaChi, txtCCCD, txtEmail;
    JDateChooser dateOB;

    JComboBox<String> cmbGioiTinh;
    MyTable tblNhanVien;
    DefaultTableModel dtmNhanVien;
    JButton btnReset, btnThemNV, btnSuaNV, btnXoaNV, btnTimNV, btnCapTaiKhoan, btnResetMatKhau, btnXoaTaiKhoan, btnMoKhoaTaiKhoan, btnXuatExcel, btnNhapExcel;

    private void addControlsNhanVien() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;
        int h = 844;
        int gap = 10;
        int w1 = 10;
        int w2 = 10;
        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Nhân viên & Quyền">
        Font font = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedNhanVien = new JLabel("Nhân viên");
        lblTabbedNhanVien.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setIcon(tabbedSelected);
        lblTabbedNhanVien.setBounds(2, 2, 140, 37);
        lblTabbedNhanVien.setFont(font);
        lblTabbedNhanVien.setForeground(Color.white);
        lblTabbedNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuyen = new JLabel("Quyền");
        lblTabbedQuyen.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setIcon(tabbedDefault);
        lblTabbedQuyen.setBounds(143, 2, 140, 37);
        lblTabbedQuyen.setFont(font);
        lblTabbedQuyen.setForeground(Color.white);
        lblTabbedQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedNhanVien);
        pnTop.add(lblTabbedQuyen);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

        /*
        =========================================================================
                                    PANEL NHÂN VIÊN
        =========================================================================
         */
        JPanel pnNhanVien = new TransparentPanel();
        pnNhanVien.setLayout(new BoxLayout(pnNhanVien, BoxLayout.Y_AXIS));

        JPanel pnTopNV = new TransparentPanel();
        pnTopNV.setLayout(new BoxLayout(pnTopNV, BoxLayout.Y_AXIS));

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ NHÂN VIÊN</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        pnTopNV.add(pnTitle);
        pnTopNV.setBackground(Color.DARK_GRAY);
        //==========

        JPanel pnContainer = new TransparentPanel();
        pnContainer.setLayout(new BoxLayout(pnContainer, BoxLayout.X_AXIS));

        JPanel pnText = new TransparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));

        JPanel pnText1 = new TransparentPanel();
        pnText1.setLayout(new BoxLayout(pnText1, BoxLayout.Y_AXIS));
        //pnText.setLayout(new FlowLayout(FlowLayout.LEFT)); 

        txtMaNV = new JTextField(w1);
        txtMaNV.setEditable(false);
         txtHo = new JTextField(w1);
        txtHo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });

        txtTen = new JTextField(w1);
        txtTen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });

        cmbGioiTinh = new JComboBox<>();

        txtChucVu = new JTextField(w2);
        txtChucVu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });

        txtSDT = new JTextField(w2);
        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                String phoneNumber = txtSDT.getText();
                if ((phoneNumber.length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE)
                        || (!Character.isDigit(c) && !Character.isISOControl(c))
                        || (phoneNumber.length() == 0 && c != '0')) {
                    evt.consume();
                }
            }
        });
        
        txtDiaChi = new JTextField(w1);
        
        txtCCCD = new JTextField(w2);
        txtCCCD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                String phoneNumber = txtCCCD.getText();
                if ((phoneNumber.length() >= 12 && c != java.awt.event.KeyEvent.VK_BACK_SPACE)
                        || (!Character.isDigit(c) && !Character.isISOControl(c))) {
                    evt.consume();
                }
            }
        });
        
        txtEmail = new JTextField(w2);
        dateOB = new JDateChooser();
        dateOB.setDateFormatString("dd/MM/yyyy");
        dateOB.getCalendarButton().setPreferredSize(new Dimension(32, 32));
        dateOB.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));

        txtSDT.setFont(font);
        txtDiaChi.setFont(font);
        txtCCCD.setFont(font);
        txtEmail.setFont(font);
        dateOB.setFont(font);

        txtMaNV.setFont(font);
        txtHo.setFont(font);
        txtTen.setFont(font);
        cmbGioiTinh.setFont(font);
        txtChucVu.setFont(font);

        cmbGioiTinh.addItem("Chọn giới tính");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        JLabel lblMa, lblHo, lblTen, lblGioiTinh, lblChucVu;

        JLabel lblSDT, lblCCCD, lblDiaChi, lblEmail, lblNgaySinh;

        lblMa = new JLabel("Mã Nhân viên");
        lblHo = new JLabel("Họ đệm");
        lblTen = new JLabel("Tên");
        lblGioiTinh = new JLabel("Giới tính");
        lblChucVu = new JLabel("Chức vụ");

        lblSDT = new JLabel("Số điện thoại");
        lblCCCD = new JLabel("Số CCCD");
        lblDiaChi = new JLabel("Địa Chỉ");
        lblEmail = new JLabel("Email");
        lblNgaySinh = new JLabel("Ngày Sinh");

        lblSDT.setFont(font);
        lblCCCD.setFont(font);
        lblDiaChi.setFont(font);
        lblEmail.setFont(font);
        lblNgaySinh.setFont(font);

        lblMa.setFont(font);
        lblHo.setFont(font);
        lblTen.setFont(font);
        lblGioiTinh.setFont(font);
        lblChucVu.setFont(font);

        JPanel pnMa = new TransparentPanel();
        pnMa.setLayout(new BoxLayout(pnMa, BoxLayout.X_AXIS));
        pnMa.add(lblMa);
        pnMa.add(txtMaNV);
        pnText.add(pnMa);

        pnText.add(Box.createHorizontalStrut(gap));

        JPanel pnHo = new TransparentPanel();
        pnHo.setLayout(new BoxLayout(pnHo, BoxLayout.X_AXIS));
        pnHo.add(lblHo);
        pnHo.add(txtHo);
        pnText.add(pnHo);

        JPanel pnTen = new TransparentPanel();
        pnTen.setLayout(new BoxLayout(pnTen, BoxLayout.X_AXIS));
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnText.add(pnTen);

        JPanel pnGioiTinh = new TransparentPanel();
        pnGioiTinh.setLayout(new BoxLayout(pnGioiTinh, BoxLayout.X_AXIS));
        pnGioiTinh.add(lblGioiTinh);
        pnGioiTinh.add(cmbGioiTinh);
        pnText.add(pnGioiTinh);

        JPanel pnChucVu = new TransparentPanel();
        pnChucVu.setLayout(new BoxLayout(pnChucVu, BoxLayout.X_AXIS));
        pnChucVu.add(lblChucVu);
        pnChucVu.add(txtChucVu);
        pnText1.add(pnChucVu);

        JPanel pnSDT = new TransparentPanel();
        pnSDT.setLayout(new BoxLayout(pnSDT, BoxLayout.X_AXIS));
        pnSDT.add(lblSDT);
        pnSDT.add(txtSDT);
        pnText1.add(pnSDT);

        JPanel pnCCCD = new TransparentPanel();
        pnCCCD.setLayout(new BoxLayout(pnCCCD, BoxLayout.X_AXIS));
        pnCCCD.add(lblCCCD);
        pnCCCD.add(txtCCCD);
        pnText1.add(pnCCCD);

        JPanel pnDiaChi = new TransparentPanel();
        pnDiaChi.setLayout(new BoxLayout(pnDiaChi, BoxLayout.X_AXIS));
        pnDiaChi.add(lblDiaChi);
        pnDiaChi.add(txtDiaChi);
        pnText.add(pnDiaChi);

        JPanel pnEmail = new TransparentPanel();
        pnEmail.setLayout(new BoxLayout(pnEmail, BoxLayout.X_AXIS));
        pnEmail.add(lblEmail);
        pnEmail.add(txtEmail);
        pnText1.add(pnEmail);

        JPanel pnBOD = new TransparentPanel();
        pnBOD.setLayout(new BoxLayout(pnBOD, BoxLayout.X_AXIS));
        pnBOD.add(lblNgaySinh);
        pnBOD.add(dateOB);
        pnText1.add(pnBOD);

        Dimension lblSize = lblMa.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblHo.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblGioiTinh.setPreferredSize(lblSize);

        cmbGioiTinh.setPreferredSize(txtChucVu.getPreferredSize());

        Dimension lblSize1 = lblNgaySinh.getPreferredSize();
        lblSDT.setPreferredSize(lblSize1);
        lblCCCD.setPreferredSize(lblSize1);
        lblDiaChi.setPreferredSize(lblSize);
        lblEmail.setPreferredSize(lblSize1);
        lblNgaySinh.setPreferredSize(lblSize1);
        dateOB.setPreferredSize(lblSize1);

        lblChucVu.setPreferredSize(lblSize1);

        pnContainer.add(pnText);
        pnContainer.add(pnText1);
        //pnTopNV.add(pnText);
        pnTopNV.add(pnContainer);

        //==========
        JPanel pnTimNV = new TransparentPanel();
        JLabel lblTim = new JLabel("Từ khoá tìm");
        lblTim.setFont(font);
        txtTimNV = new JTextField(25);
        txtTimNV.setFont(font);
        btnTimNV = new JButton("Tìm kiếm");
        pnTimNV.add(lblTim);
        pnTimNV.add(txtTimNV);
        pnTimNV.add(btnTimNV);
        pnTopNV.add(pnTimNV);
        lblTim.setPreferredSize(lblSize);
        //==========
        JPanel pnButton = new TransparentPanel();

        btnThemNV = new JButton("Thêm");
        btnSuaNV = new JButton("Lưu");
        btnXoaNV = new JButton("Xoá");

        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemNV.setFont(fontButton);
        btnSuaNV.setFont(fontButton);
        btnXoaNV.setFont(fontButton);
        btnTimNV.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThemNV.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaNV.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaNV.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTimNV.setIcon(new ImageIcon("image/search.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        pnButton.add(btnThemNV);
        pnButton.add(btnSuaNV);
        pnButton.add(btnXoaNV);
        //pnButton.add(btnTimNV);
        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);

        Dimension btnSize = btnTimNV.getPreferredSize();
        btnThemNV.setPreferredSize(btnSize);
        btnSuaNV.setPreferredSize(btnSize);
        btnXoaNV.setPreferredSize(btnSize);
        btnTimNV.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);

        JPanel pnButton2 = new TransparentPanel();
        btnCapTaiKhoan = new JButton("Cấp tài khoản");
        btnResetMatKhau = new JButton("Mật khẩu/Quyền");
        btnXoaTaiKhoan = new JButton("Khoá tài khoản");
        btnMoKhoaTaiKhoan = new JButton("Mở khoá tài khoản");
        btnCapTaiKhoan.setIcon(new ImageIcon("image/icons8_man_with_key_32px.png"));
        btnResetMatKhau.setIcon(new ImageIcon("image/reset-password.png"));
        btnXoaTaiKhoan.setIcon(new ImageIcon("image/icons8_denied_32px.png"));
        btnMoKhoaTaiKhoan.setIcon(new ImageIcon("image/lock.png"));
        btnCapTaiKhoan.setFont(fontButton);
        btnResetMatKhau.setFont(fontButton);
        btnXoaTaiKhoan.setFont(fontButton);
        btnMoKhoaTaiKhoan.setFont(fontButton);
        pnButton2.add(btnCapTaiKhoan);
        if ("Quản Trị".equals(quyen.getQuyen())) {
            pnButton2.add(btnResetMatKhau);
        }

        pnButton2.add(btnXoaTaiKhoan);
        pnButton2.add(btnMoKhoaTaiKhoan);

        pnNhanVien.add(pnTopNV);
        pnNhanVien.add(pnButton);
        pnNhanVien.add(pnButton2);
        //===================TABLE NHÂN VIÊN=====================
        JPanel pnTableNhanVien = new TransparentPanel();
        pnTableNhanVien.setLayout(new BorderLayout());
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã nhân viên");
        dtmNhanVien.addColumn("Họ đệm");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Ngày Sinh");
        dtmNhanVien.addColumn("Số Điện Thoại");
        dtmNhanVien.addColumn("CCCD");
        dtmNhanVien.addColumn("Email");
        dtmNhanVien.addColumn("Địa Chỉ");
        dtmNhanVien.addColumn("Tài khoản");
        tblNhanVien = new MyTable(dtmNhanVien);
        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        pnTableNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);
        pnNhanVien.add(scrTblNhanVien);
        /*
        =========================================================================
                                    PANEL QUYỀN
        =========================================================================
         */
        JPanel pnPhanQuyen = new TransparentPanel();
        pnPhanQuyen.setLayout(new BoxLayout(pnPhanQuyen, BoxLayout.Y_AXIS));

        JPanel pnTitlePhanQuyen = new TransparentPanel();
        JLabel lblTitlePhanQuyen = new JLabel("<html><h1>Quản lý phân quyền</h1></html>");
        pnTitlePhanQuyen.add(lblTitlePhanQuyen);
        pnPhanQuyen.add(pnTitlePhanQuyen);

        JPanel pnCmbQuyen = new TransparentPanel();
        JLabel lblCmbQuyen = new JLabel("<html><b>Nhóm quyền:</b></html>");
        lblCmbQuyen.setFont(font);
        cmbQuyen = new JComboBox<String>();
        cmbQuyen.setFont(font);
        pnCmbQuyen.add(lblCmbQuyen);
        pnCmbQuyen.add(cmbQuyen);
        pnPhanQuyen.add(pnCmbQuyen);

        JPanel pnCheckNhapHang = new TransparentPanel();
        ckbNhapHang = new JCheckBox("Quản lý nhập hàng.");
        ckbNhapHang.setFont(font);
        pnCheckNhapHang.add(ckbNhapHang);
        pnPhanQuyen.add(pnCheckNhapHang);

        JPanel pnCheckGiamGia = new TransparentPanel();
        ckbQLGiamGia = new JCheckBox("Quản lý giảm giá.");
        ckbQLGiamGia.setFont(font);
        pnCheckGiamGia.add(ckbQLGiamGia);
        pnPhanQuyen.add(pnCheckGiamGia);

        JPanel pnCheckQLSanPham = new TransparentPanel();
        ckbQLSanPham = new JCheckBox("Quản lý sản phẩm.");
        ckbQLSanPham.setFont(font);
        pnCheckQLSanPham.add(ckbQLSanPham);
        pnPhanQuyen.add(pnCheckQLSanPham);

        JPanel pnCheckQLNhanVien = new TransparentPanel();
        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên.");
        ckbQLNhanVien.setFont(font);
        pnCheckQLNhanVien.add(ckbQLNhanVien);
        pnPhanQuyen.add(pnCheckQLNhanVien);

        JPanel pnCheckQLKhachHang = new TransparentPanel();
        ckbQLKhachHang = new JCheckBox("Quản lý khách hàng.");
        ckbQLKhachHang.setFont(font);
        pnCheckQLKhachHang.add(ckbQLKhachHang);
        pnPhanQuyen.add(pnCheckQLKhachHang);

        JPanel pnCheckQLThongKe = new TransparentPanel();
        ckbThongKe = new JCheckBox("Quản lý thống kê.");
        ckbThongKe.setFont(font);
        pnCheckQLThongKe.add(ckbThongKe);
        pnPhanQuyen.add(pnCheckQLThongKe);

        Dimension ckbSize = ckbQLKhachHang.getPreferredSize();
        cmbQuyen.setPreferredSize(ckbSize);
        ckbNhapHang.setPreferredSize(ckbSize);
        ckbQLSanPham.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLKhachHang.setPreferredSize(ckbSize);
        ckbThongKe.setPreferredSize(ckbSize);
        ckbQLGiamGia.setPreferredSize(ckbSize);

        JPanel pnButtonQuyen = new TransparentPanel();
        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xoá quyền");
        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);
        btnThemQuyen.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaQuyen.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaQuyen.setIcon(new ImageIcon("image/delete-icon.png"));
        pnButtonQuyen.add(btnThemQuyen);
        pnButtonQuyen.add(btnSuaQuyen);
        pnButtonQuyen.add(btnXoaQuyen);
        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        pnPhanQuyen.add(pnButtonQuyen);

        JPanel pnImage = new ImagePanel("image/backgoundPizza.jpg");
        pnImage.setPreferredSize(new Dimension(w, 250));
        pnPhanQuyen.add(pnImage);
        //========================
        pnCardTabNhanVien = new JPanel(cardNhanVienGroup);
        pnCardTabNhanVien.add(pnNhanVien, "1");
        lblTabbedQuyen.setVisible(false);
        if ("Quản Trị".equals(quyen.getQuyen())) {
            lblTabbedQuyen.setVisible(true);
            pnCardTabNhanVien.add(pnPhanQuyen, "2");
        }

        this.add(pnCardTabNhanVien);

        loadDataTblNhanVien();
        loadDataCmbQuyen();
    }

    JComboBox<String> cmbQuyen;
    JCheckBox ckbNhapHang, ckbQLSanPham, ckbQLNhanVien, ckbQLKhachHang, ckbThongKe, ckbQLGiamGia;
    JButton btnSuaQuyen, btnThemQuyen, btnXoaQuyen;

    private void addEventsNhanVien() {
        lblTabbedNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedSelected);
                lblTabbedQuyen.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "1");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        lblTabbedQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedQuyen.setIcon(tabbedSelected);
                lblTabbedNhanVien.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "2");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        tblNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        txtTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNhanVien();
            }
        });

        btnThemNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });

        btnSuaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaNhanVien();
            }
        });

        btnXoaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });

        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatExcel();
            }
        });

        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapExcel();
            }
        });

        btnCapTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });

        btnResetMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });

        btnXoaTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });
        btnMoKhoaTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyMoKhoaTaiKhoan();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTblNhanVien();
                txtMaNV.setText("");
                txtHo.setText("");
                txtTen.setText("");
                txtChucVu.setText("");
                txtTimNV.setText("");
                txtSDT.setText("");
                txtCCCD.setText("");
                txtEmail.setText("");
                txtDiaChi.setText("");
                Date currentDate = new Date();
                dateOB.setDate(currentDate);
                cmbGioiTinh.setSelectedIndex(0);
            }
        });
    }

    private void addEventsPhanQuyen() {
        cmbQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
    }

    private void xuLyXoaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để xoá!", MyDialog.ERROR_DIALOG);
            return;
        }
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() == MyDialog.CANCEL_OPTION) {
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        boolean flag = phanQuyenBUS.xoaQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
             String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
            String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

            logger.info(quyen + " " + logname + " xoá quyền: " + tenQuyen);
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");

        boolean flag = phanQuyenBUS.themQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để sửa!", MyDialog.ERROR_DIALOG);
            return;
        }
        String tenQuyen = cmbQuyen.getSelectedItem() + "";
        int nhapHang = ckbNhapHang.isSelected() ? 1 : 0;
        int sanPham = ckbQLSanPham.isSelected() ? 1 : 0;
        int giamGia = ckbQLGiamGia.isSelected() ? 1 : 0;

        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int khachHang = ckbQLKhachHang.isSelected() ? 1 : 0;
        int thongKe = ckbThongKe.isSelected() ? 1 : 0;

        boolean flag = phanQuyenBUS.suaQuyen(tenQuyen, nhapHang, sanPham, giamGia, nhanVien, khachHang, thongKe);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyHienThiChiTietQuyen() {
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        PhanQuyen phanQuyen = new PhanQuyen();
        for (PhanQuyen pq : dsq) {
            if (pq.getQuyen().equals(cmbQuyen.getSelectedItem())) {
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setNhapHang(pq.getNhapHang());
                phanQuyen.setQlSanPham(pq.getQlSanPham());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlKhachHang(pq.getQlKhachHang());
                phanQuyen.setThongKe(pq.getThongKe());
                phanQuyen.setQlGiamGia(pq.getQlGiamGia());
                break;
            }
        }
        ckbNhapHang.setSelected(false);
        ckbQLSanPham.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLKhachHang.setSelected(false);
        ckbThongKe.setSelected(false);
        ckbQLGiamGia.setSelected(false);
        if (phanQuyen.getNhapHang() == 1) {
            ckbNhapHang.setSelected(true);
        }
        if (phanQuyen.getQlSanPham() == 1) {
            ckbQLSanPham.setSelected(true);
        }
        if (phanQuyen.getQlNhanVien() == 1) {
            ckbQLNhanVien.setSelected(true);
        }
        if (phanQuyen.getQlKhachHang() == 1) {
            ckbQLKhachHang.setSelected(true);
        }
        if (phanQuyen.getThongKe() == 1) {
            ckbThongKe.setSelected(true);
        }
        if (phanQuyen.getQlGiamGia() == 1) {
            ckbQLGiamGia.setSelected(true);
        }

    }

    private void loadDataCmbQuyen() {
        phanQuyenBUS.docDanhSachQuyen();
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        cmbQuyen.removeAllItems();
        cmbQuyen.addItem("Chọn quyền");
        for (PhanQuyen pq : dsq) {
            cmbQuyen.addItem(pq.getQuyen());
        }
    }

    private void xuLyResetMatKhau() {
        String maNV = txtMaNV.getText();
        if (maNV.trim().equals("")) {
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgQuyen_MatKhau dialog = new DlgQuyen_MatKhau(maNV);
        dialog.setVisible(true);
    }

    private void xuLyCapTaiKhoan() {
        if (txtMaNV.getText().trim().equals("")) {
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgCapTaiKhoan dialog = new DlgCapTaiKhoan(txtMaNV.getText());
        dialog.setVisible(true);
        loadDataTblNhanVien();
    }

    private void xuLyKhoaTaiKhoan() {
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        taiKhoanBUS.khoaTaiKhoan(txtMaNV.getText());
        loadDataTblNhanVien();
        String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
        String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

        logger.info(quyen + " " + logname + " khoá tài khoản nhân viên: " +txtHo.getText()+" "+ txtTen.getText());
    }

    private void xuLyMoKhoaTaiKhoan() {
        TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
        taiKhoanBUS.moKhoaTaiKhoan(txtMaNV.getText());
        String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
        String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

        logger.info(quyen + " " + logname + " mở khoá cho nhân viên: " + txtHo.getText() + " " + txtTen.getText());
        loadDataTblNhanVien();
    }

    private void xuLyNhapExcel() {
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapExcel = new XuLyFileExcel();
        nhapExcel.nhapExcel(tblNhanVien);

        int row = tblNhanVien.getRowCount();
        for (int i = 0; i < row; i++) {
            String ho = tblNhanVien.getValueAt(i, 1) + "";
            String ten = tblNhanVien.getValueAt(i, 2) + "";
            String gioiTinh = tblNhanVien.getValueAt(i, 3) + "";
            String chucVu = tblNhanVien.getValueAt(i, 4) + "";

            nhanVienBUS.nhapExcel(ho, ten, gioiTinh, chucVu);

        }
        String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
        String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

        logger.info(quyen + " " + logname + " nhập excel");
    }

    private void xuLyXuatExcel() {
        XuLyFileExcel xuatExcel = new XuLyFileExcel();
        xuatExcel.xuatExcel(tblNhanVien);
    }

    private void xuLyXoaNhanVien() {
        String ma = txtMaNV.getText();
        boolean flag = nhanVienBUS.xoaNhanVien(ma);
        if (flag) {
            nhanVienBUS.docDanhSach();
            loadDataTblNhanVien();
        }
    }

    private void xuLySuaNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return;
        }
        String ma = txtMaNV.getText();
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String chucVu = txtChucVu.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String diachi = txtDiaChi.getText();
        Date birthdate = dateOB.getDate();
        if (nhanVienBUS.updateNhanVien(ma, ho, ten, gioiTinh, chucVu, birthdate, sdt, cccd, email, diachi)) {
            nhanVienBUS.docDanhSach();
            loadDataTblNhanVien();
            String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
            String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

            logger.info(quyen + " " + logname + " sửa nhân vien: " + ho + " " + ten);
        }
    }

    private void xuLyThemNhanVien() {
        if (cmbGioiTinh.getSelectedIndex() == 0) {
            new MyDialog("Hãy chọn giới tính!", MyDialog.ERROR_DIALOG);
            return;
        }
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        String gioiTinh = cmbGioiTinh.getSelectedItem() + "";
        String chucVu = txtChucVu.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String diachi = txtDiaChi.getText();
        Date birthdate = dateOB.getDate();

        if (nhanVienBUS.themNhanVien(ho, ten, gioiTinh, chucVu, birthdate, sdt, cccd, email, diachi)) {
            nhanVienBUS.docDanhSach();
            String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
            String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

            logger.info(quyen + " " + logname + " thêm nhân viên: " + ho + " " + txtTen.getText());
            loadDataTblNhanVien();
        }
    }

    private void xuLyTimKiemNhanVien() {
        ArrayList<NhanVien> dsnv = nhanVienBUS.timNhanVien(txtTimNV.getText());
        dtmNhanVien.setRowCount(0);
        for (NhanVien nv : dsnv) {
            Vector vec = new Vector();
            vec.add(nv.getMaNV());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());
            vec.add(nv.getNgaysinh());
            vec.add(nv.getSdt());
            vec.add(nv.getCccd());
            vec.add(nv.getEmail());
            vec.add(nv.getDiachi());

            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");

            if (trangThai == 0) {
                vec.add("Khoá");
            } else if (trangThai == 1) {
                vec.add("Hiệu lực");
            } else {
                vec.add("Chưa có");
            }

            dtmNhanVien.addRow(vec);
        }
    }

    private void xuLyClickTblNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row > -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(row, 0) + "");
            txtHo.setText(tblNhanVien.getValueAt(row, 1) + "");
            txtTen.setText(tblNhanVien.getValueAt(row, 2) + "");
            txtChucVu.setText(tblNhanVien.getValueAt(row, 4) + "");
            dateOB.setDate((Date) tblNhanVien.getValueAt(row, 5));
            txtSDT.setText(tblNhanVien.getValueAt(row, 6) + "");
            txtCCCD.setText(tblNhanVien.getValueAt(row, 7) + "");
            txtEmail.setText(tblNhanVien.getValueAt(row, 8) + "");
            txtDiaChi.setText(tblNhanVien.getValueAt(row, 9) + "");

            String gioiTinh = "";
            if ((tblNhanVien.getValueAt(row, 3) + "").equals("Nam")) {
                cmbGioiTinh.setSelectedIndex(1);
            } else {
                cmbGioiTinh.setSelectedIndex(2);
            }
        }
    }

    private void loadDataTblNhanVien() {
        dtmNhanVien.setRowCount(0);
        ArrayList<NhanVien> dsnv = nhanVienBUS.getDanhSachNhanVien();

        for (NhanVien nv : dsnv) {
            if (!"admin".equals(nv.getChucVu())&&!"Quản Trị".equals(nv.getChucVu())) {

                Vector vec = new Vector();
                vec.add(nv.getMaNV());
                vec.add(nv.getHo());
                vec.add(nv.getTen());
                vec.add(nv.getGioiTinh());
                vec.add(nv.getChucVu());
                vec.add(nv.getNgaysinh());
                vec.add(nv.getSdt());
                vec.add(nv.getCccd());
                vec.add(nv.getEmail());
                vec.add(nv.getDiachi());

                int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNV() + "");

                if (trangThai == 0) {
                    vec.add("Khoá");
                } else if (trangThai == 1) {
                    vec.add("Hiệu lực");
                } else {
                    vec.add("Chưa có");
                }

                dtmNhanVien.addRow(vec);
            }
        }
    }

    TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

}
