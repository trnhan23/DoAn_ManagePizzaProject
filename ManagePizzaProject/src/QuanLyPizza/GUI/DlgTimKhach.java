package QuanLyPizza.GUI;

import QuanLyPizza.BUS.KhachHangBUS;
import QuanLyPizza.DAO.MyConnect;
import QuanLyPizza.DTO.KhachHang;
import MyCustom.MyTable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.table.DefaultTableModel;

public class DlgTimKhach extends JDialog {

    private KhachHangBUS khachHangBUS = new KhachHangBUS();
    public static KhachHang khachHangTimDuoc = null;

    public DlgTimKhach() {
        addControls();
        addEvents();
        ImageIcon icon1 = new ImageIcon("image/ManagerUI/pizza-shop.png"); // Đường dẫn tới biểu tượng của bạn
        this.setIconImage(icon1.getImage());
        this.setSize(500, 400);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblKhachHang;
    private DefaultTableModel dtmKhachHang;
    private JButton btnChon, btnThemKhach;

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
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Mã khách hàng");
        dtmKhachHang.addColumn("Họ");
        dtmKhachHang.addColumn("Tên");
        dtmKhachHang.addColumn("Số điện thoại");
        dtmKhachHang.addColumn("Email");
        dtmKhachHang.addColumn("Tổng chi tiêu");
        tblKhachHang = new MyTable(dtmKhachHang);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        pnTable.add(scrKhachHang, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThemKhach = new JButton("Thêm khách");
        btnChon.setFont(font);
        btnThemKhach.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThemKhach);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThemKhach.setPreferredSize(btnChon.getPreferredSize());

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
            
        });
        txtTuKhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               loadDataLenTable(txtTuKhoa.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonKhachHang();
            }
        });

        btnThemKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhach();
            }
        });
    }

    private void xuLyChonKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            int ma = Integer.parseInt(tblKhachHang.getValueAt(row, 0) + "");
            String ho = tblKhachHang.getValueAt(row, 1) + "";
            String ten = tblKhachHang.getValueAt(row, 2) + "";
            String gioiTinh = tblKhachHang.getValueAt(row, 3) + "";
            String email = tblKhachHang.getValueAt(row, 4) + "";
            int tongChiTieu = Integer.parseInt(tblKhachHang.getValueAt(row, 5) + "");

            khachHangTimDuoc = new KhachHang(ma, ho, ten, gioiTinh,email, tongChiTieu);
        }
        this.dispose();
    }

    private void xuLyThemKhach() {
        DlgThemKhachHang dlg = new DlgThemKhachHang();
        dlg.setVisible(true);
        if (dlg.checkThemKhach) {
            khachHangBUS.docDanhSach();
            loadDataLenTable();
        }
    }

    private void loadDataLenTable() {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
        if (dskh != null) {
            for (KhachHang kh : dskh) {
                Vector vec = new Vector();
                vec.add(kh.getMaKH());
                vec.add(kh.getHo());
                vec.add(kh.getTen());
                vec.add(kh.getSDT());
                vec.add(kh.getEmail());
                vec.add(kh.getTongChiTieu());
                dtmKhachHang.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmKhachHang.setRowCount(0);
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(tuKhoa);
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getSDT());
            vec.add(kh.getEmail());
            vec.add(kh.getTongChiTieu());
            dtmKhachHang.addRow(vec);
        }
    }

}
