package QuanLyPizza.GUI;

import QuanLyPizza.BUS.LoaiBUS;
import QuanLyPizza.BUS.SanPhamBUS;
import QuanLyPizza.DTO.LoaiSP;
import QuanLyPizza.DTO.SanPham;

import static Main.Main.changLNF;

import MyCustom.XuLyFileExcel;
import MyCustom.MyDialog;
import MyCustom.MyFileChooser;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import QuanLyPizza.BUS.DangNhapBUS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PnQuanLySanPhamGUI extends JPanel {

    public PnQuanLySanPhamGUI() {
        changLNF("Windows");
        init();
        addControlsSanPham();
        addEventsSanPham();
    }
    public static final Logger logger = Logger.getLogger(PnQuanLyKhuyenMaiGUI.class);

    void init() {
        PropertyConfigurator.configure("src\\Log\\log4j.properties");
        //setLocationRelativeTo(null);
    }
    SanPhamBUS spBUS = new SanPhamBUS();
    LoaiBUS loaiBUS = new LoaiBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSanPham;
    DefaultTableModel dtmSanPham;
    JTextField txtMa, txtTen, txtsoLuong, txtdonViTinh, txtdonGia, txtTimKiem;
    JComboBox<String> cmbLoai;
    JButton btnThem, btnSua, btnXoa, btnTim, btnChonAnh, btnReset, btnXuatExcel, btnNhapExcel, btnBanlai;
    JLabel lblAnhSP;

    boolean isActive = true;

    JComboBox<String> cmbFilter;

    private void addControlsSanPham() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        int w = 1030;
        int h = 844;

        JPanel pnTitle = new TransparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>QUẢN LÝ SẢN PHẨM</h1></html>");
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitle.add(lblTitle);
        pnTitle.add(btnReset);
        this.add(pnTitle);

        JPanel pnThongTin = new TransparentPanel();
        pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new TransparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        JLabel lblMa, lblTen, lblLoai, lblSoLuong, lblDonViTinh, lblDonGia;

        lblMa = new JLabel("Mã sản phẩm");
        lblTen = new JLabel("Tên sản phẩm");
        lblLoai = new JLabel("Loại");
        lblSoLuong = new JLabel("Số lượng");
        lblDonViTinh = new JLabel("Đơn vị tính");
        lblDonGia = new JLabel("Đơn giá");

        txtMa = new JTextField(15);
        txtMa.setEditable(false);
        txtTen = new JTextField(15);
        txtTen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });

        cmbLoai = new JComboBox<String>();
        txtsoLuong = new JTextField(15);
        txtsoLuong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });
        txtdonViTinh = new JTextField(15);
        txtdonViTinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });
        txtdonGia = new JTextField(15);
        txtdonGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && !Character.isISOControl(c)) {
                    evt.consume();
                }
            }
        });

        JPanel pnMa = new TransparentPanel();
        lblMa.setFont(font);
        txtMa.setFont(font);
        pnMa.add(lblMa);
        pnMa.add(txtMa);
        pnTextField.add(pnMa);

        JPanel pnTen = new TransparentPanel();
        lblTen.setFont(font);
        txtTen.setFont(font);
        pnTen.add(lblTen);
        pnTen.add(txtTen);
        pnTextField.add(pnTen);

        JPanel pnLoai = new TransparentPanel();
        lblLoai.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtMa.getPreferredSize());
        pnLoai.add(lblLoai);
        pnLoai.add(cmbLoai);
        pnTextField.add(pnLoai);

        JPanel pnSoLuong = new TransparentPanel();
        lblSoLuong.setFont(font);
        txtsoLuong.setFont(font);
        pnSoLuong.add(lblSoLuong);
        pnSoLuong.add(txtsoLuong);
        pnTextField.add(pnSoLuong);

        JPanel pnDonViTinh = new TransparentPanel();
        lblDonViTinh.setFont(font);
        txtdonViTinh.setFont(font);
        pnDonViTinh.add(lblDonViTinh);
        pnDonViTinh.add(txtdonViTinh);
        pnTextField.add(pnDonViTinh);

        JPanel pnDonGia = new TransparentPanel();
        lblDonGia.setFont(font);
        txtdonGia.setFont(font);
        pnDonGia.add(lblDonGia);
        pnDonGia.add(txtdonGia);
        pnTextField.add(pnDonGia);

        Dimension lblSize = lblDonViTinh.getPreferredSize();
        lblMa.setPreferredSize(lblSize);
        lblTen.setPreferredSize(lblSize);
        lblLoai.setPreferredSize(lblSize);
        lblSoLuong.setPreferredSize(lblSize);
        lblDonViTinh.setPreferredSize(lblSize);
        lblDonGia.setPreferredSize(lblSize);

        pnThongTin.add(pnTextField);

        //=================PANEL ẢNH==========
        JPanel pnAnh = new TransparentPanel();
        pnAnh.setLayout(new BoxLayout(pnAnh, BoxLayout.Y_AXIS));

        JPanel pnChuaAnh = new TransparentPanel();
        pnChuaAnh.setPreferredSize(new Dimension((int) pnAnh.getPreferredSize().getWidth(), 250));
        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(200, 200));
        lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnhSP.setIcon(getAnhSP(""));
        pnChuaAnh.add(lblAnhSP);
        pnAnh.add(pnChuaAnh);

        JPanel pnButtonAnh = new TransparentPanel();
        pnButtonAnh.setPreferredSize(new Dimension(
                (int) pnChuaAnh.getPreferredSize().getHeight(), 40));
        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(font);
        pnButtonAnh.add(btnChonAnh);
        pnChuaAnh.add(pnButtonAnh);

        pnThongTin.add(pnAnh);
        this.add(pnThongTin);

        JPanel pnButton = new TransparentPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        cmbFilter = new JComboBox<>();
        btnBanlai = new JButton("Bán lại");

        cmbFilter.addItem("Còn bán");
        cmbFilter.addItem("Ngưng bán");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        cmbFilter.setFont(fontButton);
        btnBanlai.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/search.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnBanlai.setIcon(new ImageIcon("image/arrow.png"));

        JPanel pnTimKiem = new TransparentPanel();
        JLabel lblTimKiem = new JLabel("Từ khoá tìm");
        lblTimKiem.setFont(font);
        txtTimKiem = new JTextField(20);
        txtTimKiem.setFont(font);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        //pnTimKiem.add(btnTim);
        this.add(pnTimKiem);

        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);

        pnButton.add(btnXuatExcel);
        pnButton.add(btnNhapExcel);
        pnButton.add(btnBanlai);
        pnButton.add(cmbFilter);

        Dimension btnSize = btnTim.getPreferredSize();
        btnThem.setPreferredSize(btnSize);
        btnSua.setPreferredSize(btnSize);
        btnXoa.setPreferredSize(btnSize);
        btnTim.setPreferredSize(btnSize);
        btnXuatExcel.setPreferredSize(btnSize);
        btnNhapExcel.setPreferredSize(btnSize);
        cmbFilter.setPreferredSize(btnSize);
        btnBanlai.setPreferredSize(btnSize);

        this.add(pnButton);

        //============PANEL BẢNG===========
        JPanel pnTable = new TransparentPanel(new BorderLayout());
        //====================Bảng hàng hoá====================
        //<editor-fold defaultstate="collapsed" desc="Bảng sản phẩm">
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên SP");
        dtmSanPham.addColumn("Loại SP");
        dtmSanPham.addColumn("Đơn giá");
        dtmSanPham.addColumn("Số lượng");
        dtmSanPham.addColumn("Đơn vị tính");
        dtmSanPham.addColumn("Ảnh");
        //dtmSanPham.addColumn("Trạng thái");
        tblSanPham = new MyTable(dtmSanPham);

        tblSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSanPham.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelBanHang = tblSanPham.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(77);
        columnModelBanHang.getColumn(1).setPreferredWidth(282);
        columnModelBanHang.getColumn(2).setPreferredWidth(120);
        columnModelBanHang.getColumn(3).setPreferredWidth(85);
        columnModelBanHang.getColumn(4).setPreferredWidth(138);
        columnModelBanHang.getColumn(5).setPreferredWidth(140);
        columnModelBanHang.getColumn(6).setPreferredWidth(0);
        //columnModelBanHang.getColumn(7).setPreferredWidth(20);

        JScrollPane scrTblSanPham = new JScrollPane(tblSanPham);
        //</editor-fold>
        pnTable.add(scrTblSanPham, BorderLayout.CENTER);
        this.add(pnTable);

        loadDataCmbLoai();
        loadDataLenBangSanPham();
        btnBanlai.setEnabled(false);
    }

    private void addEventsSanPham() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnh("");
                loadDataLenBangSanPham();
                txtMa.setText("");
                txtTen.setText("");
                txtdonGia.setText("");
                txtdonViTinh.setText("");
                txtsoLuong.setText("");
                //loadDataCmbLoai();
                cmbLoai.setSelectedIndex(0);
            }
        });

        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoLoai();
            }
        });
        btnBanlai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyBanLaiSanPham();
            }
        });

        cmbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện thay đổi index ở đây
                int selectedIndex = cmbFilter.getSelectedIndex();
                if (selectedIndex == 0) {
                    isActive = true;
                    btnBanlai.setEnabled(false);
                    loadAnh("");
                    loadDataLenBangSanPham();
                    txtMa.setText("");
                    txtTen.setText("");
                    txtdonGia.setText("");
                    txtdonViTinh.setText("");
                    txtsoLuong.setText("");
                    //loadDataCmbLoai();
                    cmbLoai.setSelectedIndex(0);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnXoa.setEnabled(true);
                } else {
                    isActive = false;

                    loadAnh("");
                    loadDataLenBangSanPham();
                    txtMa.setText("");
                    txtTen.setText("");
                    txtdonGia.setText("");
                    txtdonViTinh.setText("");
                    txtsoLuong.setText("");

                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                    btnXoa.setEnabled(false);
                    btnBanlai.setEnabled(true);
                    //loadDataCmbLoai();
                    cmbLoai.setSelectedIndex(0);
                }

                // Gọi phương thức loadDataTableSanPhamBan khi index thay đổi
            }
        });

        tblSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSanPham();
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

        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSanPham();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSanPham();
            }
        });

        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });

        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }

        });
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapFileExcel();
            }
        });
    }

    private void xuLyNhapFileExcel() {
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblSanPham);

        int row = tblSanPham.getRowCount();
        for (int i = 0; i < row; i++) {
            String ten = tblSanPham.getValueAt(i, 1) + "";
            String loai = tblSanPham.getValueAt(i, 2) + "";
            String donGia = tblSanPham.getValueAt(i, 3) + "";
            String soLuong = tblSanPham.getValueAt(i, 4) + "";
            String donViTinh = tblSanPham.getValueAt(i, 5) + "";
            String anh = tblSanPham.getValueAt(i, 6) + "";

            spBUS.nhapSanPhamTuExcel(ten, loai, soLuong, donViTinh, anh, donGia);
        }
    }

    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSanPham);
    }

    private void loadAnh(String anh) {
        lblAnhSP.setIcon(getAnhSP(anh));
    }

    private void xuLyClickTblSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            String donGia = tblSanPham.getValueAt(row, 3) + "";
            String soLuong = tblSanPham.getValueAt(row, 4) + "";
            String donViTinh = tblSanPham.getValueAt(row, 5) + "";
            String anh = tblSanPham.getValueAt(row, 6) + "";

            txtMa.setText(ma);
            txtTen.setText(ten);
            txtdonGia.setText(donGia);
            txtsoLuong.setText(soLuong);
            txtdonViTinh.setText(donViTinh.replace(",", ""));

            int flag = 0;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    flag = i;
                    break;
                }
            }
            cmbLoai.setSelectedIndex(flag);
            loadAnh("image/SanPham/" + anh);
        }
    }

    private void loadDataLenBangSanPham() {
        if (isActive) {
            spBUS.docListSanPhamConBan();
        } else {
            spBUS.docListSanPhamNgungBan();
        }

        dtmSanPham.setRowCount(0);
        ArrayList<SanPham> dssp;
        if (isActive) {
            dssp = spBUS.getListSanPhamConBan();
        } else {
            dssp = spBUS.getListSanPhamNgungBan();
        }
        //  ArrayList<SanPham> dssp = spBUS.getListSanPham();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
//            if (sp.getTrangThai() == 1) {
//                vec.add("Còn bán");
//            } else {
//                vec.add("Ngưng bán");
//            }
            dtmSanPham.addRow(vec);
        }
    }

    private void loadDataCmbLoai() {
        cmbLoai.removeAllItems();

        ArrayList<LoaiSP> dsl = loaiBUS.getDanhSachLoai();
        cmbLoai.addItem("0 - Chọn loại");
        for (LoaiSP loai : dsl) {
            cmbLoai.addItem(loai.getMaLoai() + " - " + loai.getTenLoai());
        }
        cmbLoai.addItem("Khác...");
    }

    private void xuLyThemLoai() {
        int x = cmbLoai.getSelectedIndex();
        if (x == cmbLoai.getItemCount() - 1) {
            DlgQuanLyLoai loaiGUI = new DlgQuanLyLoai();
            loaiGUI.setVisible(true);
            loadDataCmbLoai();
        }
    }

    private void xuLyThemSanPham() {
        String anh = fileAnhSP.getName();
        System.out.println(fileAnhSP.getName());
        boolean flag = spBUS.themSanPham(txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText());
        spBUS.docListSanPham();
        loadDataLenBangSanPham();
        luuFileAnh();
        String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
        String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

        logger.info(quyen + " " + logname + " thêm sản phẩm : " + txtTen.getText() + " với số lượng: " + txtsoLuong.getText());
    }

    File fileAnhSP;

    private void xuLySuaSanPham() {
        String anh = fileAnhSP.getName();
        boolean flag = spBUS.suaSanPham(txtMa.getText(),
                txtTen.getText(),
                cmbLoai.getSelectedItem() + "",
                txtsoLuong.getText(),
                txtdonViTinh.getText(),
                anh,
                txtdonGia.getText());
        spBUS.docListSanPham();
        loadDataLenBangSanPham();
        luuFileAnh();
    }

    private void xuLyXoaSanPham() {
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = spBUS.xoaSanPham(txtMa.getText());
            if (flag) {
                loadDataLenBangSanPham();
                String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
                String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

                logger.info(quyen + " " + logname + " xử lý ngưng bán sản phẩm: " + txtTen.getText());
            }
        }
    }

    private void xuLyBanLaiSanPham() {

        MyDialog dlg = new MyDialog("Bán lại sản phẩm này?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = spBUS.banLaiSanPham(txtMa.getText());
            if (flag) {
                loadDataLenBangSanPham();
                String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
                String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();

                logger.info(quyen + " " + logname + " mở bán lại sản phẩm: " + txtTen.getText());
            }
        }
    }

    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(fileAnhSP.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("image/SanPham/" + fileAnhSP.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh() {
        JFileChooser fileChooser = new MyFileChooser("image/SanPham/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAnhSP = fileChooser.getSelectedFile();
            lblAnhSP.setIcon(getAnhSP(fileAnhSP.getPath()));
        }
    }

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    private void xuLyTimKiem() {
        String ten = txtTimKiem.getText().toLowerCase();
        dtmSanPham.setRowCount(0);
        ArrayList<SanPham> dssp = null;
        dssp = spBUS.getSanPhamTheoTen(ten);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
            dtmSanPham.addRow(vec);
        }
        //MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dssp.size(), MyDialog.INFO_DIALOG);
    }

    private void xuLyTimKiemTheoLoai() {
        dtmSanPham.setRowCount(0);
        ArrayList<SanPham> dssp = null;

        if (cmbLoai.getItemCount() > 0) {
            String loai = cmbLoai.getSelectedItem() + "";
            String loaiArr[] = loai.split("-");
            String loaiSP = loaiArr[0].trim();

            if (loaiSP.equals("0")) {
                if (isActive) {
                    dssp = spBUS.getListSanPhamConBan();
                } else {
                    dssp = spBUS.getListSanPhamNgungBan();
                }
            } else {
                dssp = spBUS.getSanPhamTheoLoai(loaiSP);
            }
        } else {
            if (isActive) {
                dssp = spBUS.getListSanPhamConBan();
            } else {
                dssp = spBUS.getListSanPhamNgungBan();
            }
        }
        int i = 0;
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (SanPham sp : dssp) {
            Vector vec = new Vector();
            vec.add(sp.getMaSP());
            vec.add(sp.getTenSP());
            String tenLoai = loaiBUS.getTenLoai(sp.getMaLoai());
            vec.add(tenLoai);
            vec.add(dcf.format(sp.getDonGia()));
            vec.add(dcf.format(sp.getSoLuong()));
            vec.add(sp.getDonViTinh());
            vec.add(sp.getHinhAnh());
//            if (sp.getTrangThai() == 1) {
//                vec.add("Còn bán");
//            } else {
//                vec.add("Ngưng bán");
//            }
            dtmSanPham.addRow(vec);
        }
    }
}
