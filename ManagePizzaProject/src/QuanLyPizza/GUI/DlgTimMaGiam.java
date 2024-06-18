package QuanLyPizza.GUI;

import QuanLyPizza.BUS.GiamGiaBUS;
import QuanLyPizza.DTO.GiamGia;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import QuanLyPizza.DTO.SanPham;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DlgTimMaGiam extends JDialog {

    public static GiamGia maGiamTimDuoc = null;
    private GiamGiaBUS giamGiaBUS = new GiamGiaBUS();
    public int tongTien;
    public boolean thanhtoan;

    public DlgTimMaGiam(int tongTien) {
        this.tongTien = tongTien;
        addControls();
        addEvents();
        ImageIcon icon1 = new ImageIcon("image/ManagerUI/pizza-shop.png"); // Đường dẫn tới biểu tượng của bạn
        this.setIconImage(icon1.getImage());
        this.setSize(750, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblMaGiam;
    private DefaultTableModel dtmMaGiam;
    private JButton btnChon, btnThoat;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        //pnTop.add(txtTuKhoa);
        //con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmMaGiam = new DefaultTableModel();
        dtmMaGiam.addColumn("Mã");
        dtmMaGiam.addColumn("Chương trình");
        dtmMaGiam.addColumn("% Khuyến mãi");
        dtmMaGiam.addColumn("Điều kiện");
        dtmMaGiam.addColumn("Bắt đầu");
        dtmMaGiam.addColumn("Kết thúc");
        //dtmMaGiam.addColumn("Trạng thái");
        tblMaGiam = new MyTable(dtmMaGiam);
        JScrollPane scrMaGiam = new JScrollPane(tblMaGiam);
        pnTable.add(scrMaGiam, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThoat = new JButton("Thoát");
        btnChon.setFont(font);
        btnThoat.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThoat);
        con.add(pnButton, BorderLayout.SOUTH);

        TableColumnModel columnModelBanHang = tblMaGiam.getColumnModel();
        columnModelBanHang.getColumn(0).setPreferredWidth(56);
        columnModelBanHang.getColumn(1).setPreferredWidth(213);
        columnModelBanHang.getColumn(2).setPreferredWidth(30);
        columnModelBanHang.getColumn(3).setPreferredWidth(62);
        columnModelBanHang.getColumn(4).setPreferredWidth(58);
        columnModelBanHang.getColumn(5).setPreferredWidth(61);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblMaGiam.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblMaGiam.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblMaGiam.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblMaGiam.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblMaGiam.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblMaGiam.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThoat.setPreferredSize(btnChon.getPreferredSize());
        loadDataLen();
        if (thanhtoan) {

        }
//        else
//            loadDataLenTable();
    }

    private void addEvents() {

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonMaGiam();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThoat();
            }
        });
    }

    private void xuLyChonMaGiam() {
        int row = tblMaGiam.getSelectedRow();
        if (row > -1) {
            try {
//                if (tblMaGiam.getValueAt(row, 6).equals("Không hiệu lực")) {
//                    new MyDialog("Mã này đã hết hiệu lực!", MyDialog.ERROR_DIALOG);
//                    loadDataLenTable();
//                    return;
//                }
                int ma = Integer.parseInt(tblMaGiam.getValueAt(row, 0) + "");
                String ten = tblMaGiam.getValueAt(row, 1) + "";
                int phanTram = Integer.parseInt(tblMaGiam.getValueAt(row, 2) + "");
                String dieuKienst = tblMaGiam.getValueAt(row, 3) + "";
                dieuKienst = dieuKienst.replace(">", "");
                dieuKienst = dieuKienst.replace(",", "");
                int dieuKien = Integer.parseInt(dieuKienst);

                if (dieuKien > tongTien) {
                    new MyDialog("Không đủ điều kiện áp dụng mã giảm này!", MyDialog.ERROR_DIALOG);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date ngayBD = sdf.parse(tblMaGiam.getValueAt(row, 4) + "");
                Date ngayKT = sdf.parse(tblMaGiam.getValueAt(row, 5) + "");

                maGiamTimDuoc = new GiamGia();
                maGiamTimDuoc.setMaGiam(ma);
                maGiamTimDuoc.setTenGiamGia(ten);
                maGiamTimDuoc.setPhanTramGiam(phanTram);
                maGiamTimDuoc.setDieuKien(dieuKien);
                maGiamTimDuoc.setNgayBD(ngayBD);
                maGiamTimDuoc.setNgayKT(ngayKT);
            } catch (ParseException ex) {
            }
        }
        xuLyThoat();
    }

    private void xuLyThoat() {
        dispose();
    }

    private void loadDataLenTable() {
        dtmMaGiam.setRowCount(0);
        giamGiaBUS.docDanhSach();

        ArrayList<GiamGia> dsg = giamGiaBUS.getDanhSachGiamGia();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###");
        Date now = new Date();
        for (GiamGia gg : dsg) {
            Vector vec = new Vector();
            if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add(gg.getMaGiam());
                vec.add(gg.getTenGiamGia());
                vec.add(gg.getPhanTramGiam());
                vec.add(dcf.format(gg.getDieuKien()));
                vec.add(sdf.format(gg.getNgayBD()));
                vec.add(sdf.format(gg.getNgayKT()));
                dtmMaGiam.addRow(vec);
            }

        }
    }

    private void loadDataLen() {
        dtmMaGiam.setRowCount(0);
        giamGiaBUS.docDanhSach();

        ArrayList<GiamGia> dsg = giamGiaBUS.getDanhSachGiamGia(tongTien);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat(">###,###");
        Date now = new Date();
        for (GiamGia gg : dsg) {
            Vector vec = new Vector();
             if (gg.getNgayBD().before(now) && gg.getNgayKT().after(now)) {
                vec.add(gg.getMaGiam());
                vec.add(gg.getTenGiamGia());
                vec.add(gg.getPhanTramGiam());
                vec.add(dcf.format(gg.getDieuKien()));
                vec.add(sdf.format(gg.getNgayBD()));
                vec.add(sdf.format(gg.getNgayKT()));
                dtmMaGiam.addRow(vec);
            }
        }
    }

}
