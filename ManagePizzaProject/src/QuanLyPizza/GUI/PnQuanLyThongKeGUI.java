package QuanLyPizza.GUI;

import QuanLyPizza.BUS.ThongKeBUS;
import QuanLyPizza.DTO.ThongKe;
import MyCustom.TransparentPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

import java.awt.*;

import static Main.Main.changLNF;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.time.ZoneId;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;

public class PnQuanLyThongKeGUI extends JPanel {

    public PnQuanLyThongKeGUI() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    ThongKeBUS thongKeBUS = new ThongKeBUS();
    final Color colorPanel = new Color(70, 130, 180);
    JLabel lblThongKeThucDon, lblThongKeKhachHang, lblThongKeNhanVien, lblThongKeDoanhThu;
    JLabel lblDoanhThuQuy1, lblDoanhThuQuy2, lblDoanhThuQuy3, lblDoanhThuQuy4, lblTongDoanhThu;
    JButton btnView, btnBack;
    JComboBox<Integer> cmbNam, cmbThang, cmbNgayBD, cmbNgayKT;
    CardLayout cardLayoutThongKe = new CardLayout();
    JPanel pnMain;
    JLabel lblMon1, lblMon2, lblMon3, lblMon4, lblMon5, lblSoLuong1, lblSoLuong2, lblSoLuong3, lblSoLuong4, lblSoLuong5;
    private ChartPanel chartPanel;
    JPanel pnThongKeChiTiet, pnChart;
    JButton btn_filter, btnCheck;
    
    JLabel lblMoMo, lblCash, lblBank, lblSum;

    JDateChooser dateMinSearch, dateMaxSearch;

    final ImageIcon tabbedSelected = new ImageIcon("image/ManagerUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/ManagerUI/tabbed-btn.png");

    int ngayBD, ngayKT, thang, nam;
    String dateBD = "";
    String dateKT = "";

    private void addControls() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;
        int h = 844;

        int sizeW = 300;
        int sizeH = 55;

        int w1 = 300; // Chiều rộng mới
        int h1 = 150;

        //========================================
        pnMain = new TransparentPanel();
        pnMain.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pnMain.setLayout(cardLayoutThongKe);

        JPanel pnThongKeTong = new JPanel(null);
        pnThongKeTong.setBackground(colorPanel);
        JLabel lblTileThongKeTong, lblBackgroundThucDon, lblBackgroundKhachHang, lblBackgroundNhanVien, lblBackgroundDoanhThu;

        lblTileThongKeTong = new JLabel("THỐNG KÊ TỔNG QUÁT", JLabel.CENTER);
        lblTileThongKeTong.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnView = new JButton(new ImageIcon("image/icons8_view_40px.png"));
        ImageIcon iconThucDon = new ImageIcon("image/ManagerUI/food.png");
        ImageIcon iconKhachHang = new ImageIcon("image/ManagerUI/client.png");
        ImageIcon iconNhanVien = new ImageIcon("image/ManagerUI/employee.png");
        ImageIcon iconDoanhThu = new ImageIcon("image/ManagerUI/revenue.png");

// Thu nhỏ hình ảnh theo kích thước mới
        Image imageThucDon = iconThucDon.getImage().getScaledInstance(w1, h1, Image.SCALE_SMOOTH);
        Image imageKhachHang = iconKhachHang.getImage().getScaledInstance(w1, h1, Image.SCALE_SMOOTH);
        Image imageNhanVien = iconNhanVien.getImage().getScaledInstance(w1, h1, Image.SCALE_SMOOTH);
        Image imageDoanhThu = iconDoanhThu.getImage().getScaledInstance(w1, h1, Image.SCALE_SMOOTH);

// Tạo mới ImageIcon từ hình ảnh đã thu nhỏ
        ImageIcon scaledIconThucDon = new ImageIcon(imageThucDon);
        ImageIcon scaledIconKhachHang = new ImageIcon(imageKhachHang);
        ImageIcon scaledIconNhanVien = new ImageIcon(imageNhanVien);
        ImageIcon scaledIconDoanhThu = new ImageIcon(imageDoanhThu);

// Thiết lập JLabel với ImageIcon đã thu nhỏ
        lblBackgroundThucDon = new JLabel(scaledIconThucDon);
        lblBackgroundKhachHang = new JLabel(scaledIconKhachHang);
        lblBackgroundNhanVien = new JLabel(scaledIconNhanVien);
        lblBackgroundDoanhThu = new JLabel(scaledIconDoanhThu);

// Thiết lập vị trí và kích thước cho các JLabel
        lblTileThongKeTong.setBounds(0, 15, w, 50);
        btnView.setBounds(10, 10, 45, 45);
        lblBackgroundThucDon.setBounds(98, 85, w1, h1);
        lblBackgroundKhachHang.setBounds(563, 85, w1, h1);
        lblBackgroundNhanVien.setBounds(98, 250, w1, h1);
        lblBackgroundDoanhThu.setBounds(563, 250, w1, h1);

        btnView.setToolTipText("Xem chi tiết");
        btnView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblThongKeThucDon = new JLabel("100", JLabel.CENTER);
        lblThongKeKhachHang = new JLabel("46", JLabel.CENTER);
        lblThongKeNhanVien = new JLabel("23", JLabel.CENTER);
        lblThongKeDoanhThu = new JLabel("1.286.379.000", JLabel.CENTER);

        Font font = new Font("Tahoma", Font.BOLD, 28);
        lblThongKeThucDon.setFont(font);
        lblThongKeKhachHang.setFont(font);
        lblThongKeNhanVien.setFont(font);
        lblThongKeDoanhThu.setFont(font);

        lblTileThongKeTong.setForeground(Color.white);
        lblThongKeThucDon.setForeground(Color.white);
        lblThongKeKhachHang.setForeground(Color.white);
        lblThongKeNhanVien.setForeground(Color.white);
        lblThongKeDoanhThu.setForeground(Color.white);

        lblThongKeThucDon.setBounds(98, 100, sizeW, sizeH);
        lblThongKeKhachHang.setBounds(563, 100, sizeW, sizeH);
        lblThongKeNhanVien.setBounds(98, 270, sizeW, sizeH);
        lblThongKeDoanhThu.setBounds(563, 270, sizeW, sizeH);

        pnThongKeTong.add(lblTileThongKeTong);
        //pnThongKeTong.add(btnView);
        pnThongKeTong.add(lblThongKeThucDon);
        pnThongKeTong.add(lblThongKeKhachHang);
        pnThongKeTong.add(lblThongKeNhanVien);
        pnThongKeTong.add(lblThongKeDoanhThu);
        pnThongKeTong.add(lblBackgroundThucDon);
        pnThongKeTong.add(lblBackgroundKhachHang);
        pnThongKeTong.add(lblBackgroundNhanVien);
        pnThongKeTong.add(lblBackgroundDoanhThu);

        lblDoanhThuQuy1 = new JLabel("", JLabel.CENTER);
        lblDoanhThuQuy2 = new JLabel("", JLabel.CENTER);
        lblDoanhThuQuy3 = new JLabel("", JLabel.CENTER);
        lblDoanhThuQuy4 = new JLabel("", JLabel.CENTER);
        lblTongDoanhThu = new JLabel("", JLabel.CENTER);
        
        Font font1 = new Font("Tahoma", Font.BOLD, 22);
        
        
        lblDoanhThuQuy1.setFont(font1);
        lblDoanhThuQuy2.setFont(font1);
        lblDoanhThuQuy3.setFont(font1);
        lblDoanhThuQuy4.setFont(font1);
        font1 = new Font("Tahoma", Font.BOLD, 28);
        lblTongDoanhThu.setFont(font1);

        lblDoanhThuQuy1.setForeground(Color.WHITE);
        lblDoanhThuQuy2.setForeground(Color.WHITE);
        lblDoanhThuQuy3.setForeground(Color.WHITE);
        lblDoanhThuQuy4.setForeground(Color.WHITE);
        lblTongDoanhThu.setForeground(Color.WHITE);

        int x = 245;
        int y = 540;
        lblDoanhThuQuy1.setBounds(x, y, 167, 63);
        lblDoanhThuQuy2.setBounds(x += 167, y, 167, 63);
        lblDoanhThuQuy3.setBounds(x += 167, y, 167, 63);
        lblDoanhThuQuy4.setBounds(x += 167, y, 167, 63);
        lblTongDoanhThu.setBounds(260, 607, 667, 63);

        pnThongKeTong.add(lblTongDoanhThu);
        pnThongKeTong.add(lblDoanhThuQuy1);
        pnThongKeTong.add(lblDoanhThuQuy2);
        pnThongKeTong.add(lblDoanhThuQuy3);
        pnThongKeTong.add(lblDoanhThuQuy4);

        cmbNam = new JComboBox<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= year - 5; i--) {
            cmbNam.addItem(i);
        }
        cmbNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cmbNam.setBounds(w / 2 - 100, 430, 120, 35);
        pnThongKeTong.add(cmbNam);

        btn_filter = new JButton("Chi tiết");
        btn_filter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn_filter.setBounds((w / 2 - 100 / 2) + 140, 430, 100, 35);
        pnThongKeTong.add(btn_filter);

//        JLabel lblBackgroundBang = new JLabel(new ImageIcon("image/ManagerUI/bangThongKe.png"));
//        lblBackgroundBang.setBounds(98, 500, 834, 165);
//        pnThongKeTong.add(lblBackgroundBang);
        // Load hình ảnh từ tệp tin
        ImageIcon iconBangThongKe = new ImageIcon("image/ManagerUI/bangThongKe.png");

// Thu nhỏ hình ảnh theo kích thước mới
        Image scaledImage = iconBangThongKe.getImage().getScaledInstance(800, 200, Image.SCALE_SMOOTH);

// Tạo mới ImageIcon từ hình ảnh đã thu nhỏ
        ImageIcon scaledIconBangThongKe = new ImageIcon(scaledImage);

// Thiết lập JLabel với ImageIcon đã thu nhỏ
        JLabel lblBackgroundBang = new JLabel(scaledIconBangThongKe);

// Thiết lập vị trí và kích thước cho JLabel
        lblBackgroundBang.setBounds(98, 470, 800, 200);

// Thêm JLabel vào panel
        pnThongKeTong.add(lblBackgroundBang);

        pnMain.add(pnThongKeTong, "1");

        // ==============================================
        //              THỐNG KÊ CHI TIẾT
        // ==============================================
        pnThongKeChiTiet = new TransparentPanel(null);

        btnBack = new JButton(new ImageIcon("image/icons8_undo_40px.png"));
        btnBack.setToolTipText("Quay lại");
        btnBack.setBounds(10, 10, 45, 45);
        pnThongKeChiTiet.add(btnBack);

        JLabel lblBackGroundBangChiTiet = new JLabel(new ImageIcon("image/ManagerUI/bangChiTiet.png"));
        lblBackGroundBangChiTiet.setBounds(172, 10, 686, 363);
        //pnThongKeChiTiet.add(lblBackGroundBangChiTiet);
        
       
        
        lblMon1 = new JLabel("0");
        lblMon2 = new JLabel("0");
        lblMon3 = new JLabel("0");
        lblMon4 = new JLabel("0");
        lblMon5 = new JLabel("0");
        lblSoLuong1 = new JLabel("0", JLabel.CENTER);
        lblSoLuong2 = new JLabel("0", JLabel.CENTER);
        lblSoLuong3 = new JLabel("0", JLabel.CENTER);
        lblSoLuong4 = new JLabel("0", JLabel.CENTER);
        lblSoLuong5 = new JLabel("0", JLabel.CENTER);

        x = 236;
        y = 123;
        lblMon1.setBounds(x, y, 493, 50);
        lblMon2.setBounds(x, y += 50, 493, 50);
        lblMon3.setBounds(x, y += 50, 493, 50);
        lblMon4.setBounds(x, y += 50, 493, 50);
        lblMon5.setBounds(x, y += 50, 493, 50);
        x = 729;
        y = 123;
        lblSoLuong1.setBounds(x, y, 128, 50);
        lblSoLuong2.setBounds(x, y += 50, 128, 50);
        lblSoLuong3.setBounds(x, y += 50, 128, 50);
        lblSoLuong4.setBounds(x, y += 50, 128, 50);
        lblSoLuong5.setBounds(x, y += 50, 128, 50);

        lblMon1.setForeground(Color.BLACK);
        lblMon2.setForeground(Color.BLACK);
        lblMon3.setForeground(Color.BLACK);
        lblMon4.setForeground(Color.BLACK);
        lblMon5.setForeground(Color.BLACK);
        lblSoLuong1.setForeground(Color.BLACK);
        lblSoLuong2.setForeground(Color.BLACK);
        lblSoLuong3.setForeground(Color.BLACK);
        lblSoLuong4.setForeground(Color.BLACK);
        lblSoLuong5.setForeground(Color.BLACK);

        Font fontChiTiet = new Font("Tahoma", Font.BOLD, 18);
        lblMon1.setFont(fontChiTiet);
        lblMon2.setFont(fontChiTiet);
        lblMon3.setFont(fontChiTiet);
        lblMon4.setFont(fontChiTiet);
        lblMon5.setFont(fontChiTiet);
        lblSoLuong1.setFont(fontChiTiet);
        lblSoLuong2.setFont(fontChiTiet);
        lblSoLuong3.setFont(fontChiTiet);
        lblSoLuong4.setFont(fontChiTiet);
        lblSoLuong5.setFont(fontChiTiet);

//        pnThongKeChiTiet.add(lblMon1);
//        pnThongKeChiTiet.add(lblMon2);
//        pnThongKeChiTiet.add(lblMon3);
//        pnThongKeChiTiet.add(lblMon4);
//        pnThongKeChiTiet.add(lblMon5);
//        pnThongKeChiTiet.add(lblSoLuong1);
//        pnThongKeChiTiet.add(lblSoLuong2);
//        pnThongKeChiTiet.add(lblSoLuong3);
//        pnThongKeChiTiet.add(lblSoLuong4);
//        pnThongKeChiTiet.add(lblSoLuong5);
        //========BIỂU ĐỒ CỘT=============
        cmbThang = new JComboBox<>();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // Adding 1 to match 1-based month indexing
        for (int i = 1; i <= 12; i++) {
            cmbThang.addItem(i);
        }
        cmbThang.setSelectedItem(currentMonth);
        cmbThang.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cmbThang.setBounds(w / 2 - 100, 430, 120, 35);

        thang = (int) cmbThang.getSelectedItem();

        cmbNgayKT = new JComboBox<>();
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int daysInMonth = YearMonth.now().lengthOfMonth(); // Get the number of days in the current month
        for (int i = 1; i <= daysInMonth; i++) {
            cmbNgayKT.addItem(i);
        }
        cmbNgayKT.setSelectedItem(currentDay);
        cmbNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 18));

        ngayKT = (int) cmbNgayKT.getSelectedItem();

        cmbNgayBD = new JComboBox<>();
        for (int i = 1; i <= daysInMonth; i++) {
            cmbNgayBD.addItem(i);
        }
        cmbNgayBD.setSelectedItem(currentDay);
        cmbNgayBD.setFont(new Font("Tahoma", Font.PLAIN, 18));

        ngayBD = (int) cmbNgayBD.getSelectedItem();

        JPanel pnDate = new TransparentPanel();
        JLabel lblMaxNgay = new JLabel(" đến: ");
        lblMaxNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pnDate.setBounds(230, 50, 850, 100);
        JLabel lbl1 = new JLabel("Ngày ");
        lbl1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JLabel lbl2 = new JLabel("- Tháng - ");
        lbl2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pnDate.setBounds(230, 50, 500, 100);
        pnDate.add(lbl1);
        pnDate.add(cmbNgayBD);
        //pnDate.add(dateMinSearch);
        pnDate.add(lblMaxNgay);
        pnDate.add(cmbNgayKT);
        pnDate.add(lbl2);
        pnDate.add(cmbThang);
        //pnDate.add(dateMaxSearch);
        btnCheck = new JButton("Top bán");
        btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCheck.setBounds((w / 2 - 100 / 2) + 140, 400, 150, 35);
        pnDate.add(btnCheck);
        
        lblMoMo = new JLabel("", JLabel.LEFT);
        lblBank = new JLabel("", JLabel.LEFT);
        lblCash = new JLabel("", JLabel.LEFT);
        lblSum = new JLabel("", JLabel.LEFT);
        
        Font font2 = new Font("Tahoma", Font.BOLD, 20); 
        
        
        lblMoMo.setFont(font2);
        lblBank.setFont(font2);
        lblCash.setFont(font2);
        lblSum.setFont(font2);
        
        lblMoMo.setForeground(Color.BLACK);
        lblBank.setForeground(Color.BLACK);
        lblCash.setForeground(Color.BLACK);
        lblSum.setForeground(Color.BLACK);
        
        lblMoMo.setBounds(20,600, 200,20);
        lblBank.setBounds(20,610, 200,20);
        lblCash.setBounds(20,620, 200,40);
        lblSum.setBounds(20,640, 200,20);
        
        JPanel panelThanhToan = new TransparentPanel();
        panelThanhToan.setLayout(new BoxLayout(panelThanhToan, BoxLayout.Y_AXIS));
        panelThanhToan.setBounds(20, 550, 500, 300);
        panelThanhToan.add(lblMoMo);
        panelThanhToan.add(lblBank);
        panelThanhToan.add(lblCash);
        panelThanhToan.add(lblSum);
        
        pnChart = new TransparentPanel();
        pnChart.setBounds(0, 100, 1030, 450);
        chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension(1030, 400));

        //pnChart.add(pnDate,BorderLayout.NORTH);
        pnChart.add(chartPanel);
        //================================
        pnThongKeChiTiet.add(pnDate);
        pnThongKeChiTiet.add(pnChart);
        pnThongKeChiTiet.add(panelThanhToan);
        pnMain.add(pnThongKeChiTiet, "2");

        this.add(pnMain, BorderLayout.CENTER);
        nam = (int) cmbNam.getSelectedItem();
        dateBD = nam + "-" + thang + "-" + ngayBD;
        dateKT = nam + "-" + thang + "-" + ngayKT;
        hienTongTienTheoLoai(dateBD,dateKT);
        hienThiThongKe();
        
    }

    private void addEvents() {
        cmbThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //updateNgayBDComboBox();
                nam = (int) cmbNam.getSelectedItem();
                thang = (int) cmbThang.getSelectedItem();
                dateBD = nam + "-" + thang + "-" + ngayBD;
                dateKT = nam + "-" + thang + "-" + ngayKT;
                
                hienThiThongKe();
                hienTongTienTheoLoai(dateBD,dateKT);
                veLaiChartDate();
                
                cardLayoutThongKe.show(pnMain, "1");
                cardLayoutThongKe.show(pnMain, "2");
                
            }
        });

        cmbNgayBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nam = (int) cmbNam.getSelectedItem();
                ngayKT = (int) cmbNgayKT.getSelectedItem();
                ngayBD = (int) cmbNgayBD.getSelectedItem();
                dateBD = nam + "-" + thang + "-" + ngayBD;
                dateKT = nam + "-" + thang + "-" + ngayKT;
                
                hienTongTienTheoLoai(dateBD,dateKT);
                hienThiThongKe();
                veLaiChartDate();
               
                cardLayoutThongKe.show(pnMain, "1");
                cardLayoutThongKe.show(pnMain, "2");
                 
            }
        });

        cmbNgayKT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nam = (int) cmbNam.getSelectedItem();
                ngayKT = (int) cmbNgayKT.getSelectedItem();
                ngayBD = (int) cmbNgayBD.getSelectedItem();
                dateBD = nam + "-" + thang + "-" + ngayBD;
                dateKT = nam + "-" + thang + "-" + ngayKT;
                hienTongTienTheoLoai(dateBD,dateKT);
                hienThiThongKe();
                veLaiChartDate();
                
                cardLayoutThongKe.show(pnMain, "1");
                cardLayoutThongKe.show(pnMain, "2");
                
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                DlgLocThongKe dlg = new DlgLocThongKe(Integer.parseInt(cmbNam.getSelectedItem() + ""));
//                dlg.setVisible(true);
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMoMo.setText("");
                lblBank.setText("");
                lblCash.setText("");
                lblSum.setText("");
                hienThiThongKe();
                cardLayoutThongKe.show(pnMain, "1");
            }
        });
        cmbNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hienThiThongKe();
            }
        });
        btn_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hienThiThongKe();
                dateBD = nam + "-" + thang + "-" + ngayBD;
                dateKT = nam + "-" + thang + "-" + ngayKT;
                hienTongTienTheoLoai(dateBD,dateKT);
                veLaiChart();
                cardLayoutThongKe.show(pnMain, "2");
            }
        });
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateBD = nam + "-" + thang + "-" + ngayBD;
                dateKT = nam + "-" + thang + "-" + ngayKT;
               DlgLocThongKe dlg = new DlgLocThongKe(dateBD,dateKT);
                dlg.setVisible(true);
            }
        });

    }

    private void updateNgayBDComboBox() {
        cmbNgayBD.removeAllItems();
        int selectedMonth = (int) cmbThang.getSelectedItem();
        int daysInMonth = YearMonth.of(Year.now().getValue(), selectedMonth).lengthOfMonth(); // Get the number of days in the selected month
        for (int i = 1; i <= daysInMonth; i++) {
            cmbNgayBD.addItem(i);
        }
        cmbNgayKT.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cmbNgayKT.addItem(i);
        }

    }

    private void veLaiChart() {
        pnChart.removeAll();

        chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension(1030, 441));

        pnChart.add(chartPanel);
    }

    private void veLaiChartDate() {
        pnChart.removeAll();

        chartPanel = new ChartPanel(createChartDate());
        chartPanel.setPreferredSize(new Dimension(1030, 441));

        pnChart.add(chartPanel);
    }

    private JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu năm " + Integer.parseInt(cmbNam.getSelectedItem() + ""),
                "Tháng", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);

        return barChart;
    }

    private JFreeChart createChartDate() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu tháng " + Integer.parseInt(cmbThang.getSelectedItem()+"") + " năm "+nam,
                "Ngày", "Doanh thu",
                createDatasetDate(), PlotOrientation.VERTICAL, false, false, false);

        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            double value = thongKeBUS.getDoanhThuThang(i, Integer.parseInt(cmbNam.getSelectedItem() + ""));
            dataset.addValue(value, "Doanh thu", i + "");
        }
        return dataset;
    }

    private CategoryDataset createDatasetDate() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = ngayBD; i <= ngayKT; i++) {
            dateKT = nam + "-" + thang + "-" + (i + 1);
            dateBD = nam + "-" + thang + "-" + i;
            double value = thongKeBUS.getDoanhThuNgay(dateBD, dateKT);
            dataset.addValue(value, "Doanh thu", i + "");
        }
        return dataset;
    }

//    private CategoryDataset createDatasetDate() {
//        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        java.util.Date date = dateMinSearch.getDate();
//        LocalDate start = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        java.util.Date dateEnd = dateMaxSearch.getDate();
//        LocalDate end = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        while (!start.isAfter(end)) {
//            double value = thongKeBUS.getDoanhThuNgay(start);
//            dataset.addValue(value, "Doanh thu", start.toString());
//            start = start.plusDays(1);
//        }
//
//        return dataset;
//    }
    private DecimalFormat dcf = new DecimalFormat("###,###");

    private void hienThiThongKe() {
        ThongKe thongKe = thongKeBUS.thongKe(Integer.parseInt(cmbNam.getSelectedItem() + ""));
        lblThongKeThucDon.setText(dcf.format(thongKe.getSoLuongSP()) + " món");
        lblThongKeKhachHang.setText(dcf.format(thongKe.getSoLuongKH()));
        lblThongKeNhanVien.setText(dcf.format(thongKe.getSoLuongNV()));
        lblThongKeDoanhThu.setText(dcf.format(thongKe.getTongDoanhThu()));
        lblDoanhThuQuy1.setText(dcf.format(thongKe.getTongThuQuy(1)));
        lblDoanhThuQuy2.setText(dcf.format(thongKe.getTongThuQuy(2)));
        lblDoanhThuQuy3.setText(dcf.format(thongKe.getTongThuQuy(3)));
        lblDoanhThuQuy4.setText(dcf.format(thongKe.getTongThuQuy(4)));
        long doanhthu = thongKe.getTongDoanhThu();
        lblTongDoanhThu.setText(dcf.format(doanhthu));
        
        
    }
    private void hienTongTienTheoLoai(String ngayBD, String ngayKT){
        ThongKe thongke = thongKeBUS.thongKe(ngayBD, ngayKT);
        lblMoMo.setText("Momo: "+dcf.format(thongke.getTongTienTheoLoai(1)));
        lblBank.setText("Chuyển khoản: "+dcf.format(thongke.getTongTienTheoLoai(2)));
        lblCash.setText("Tiền mặt: "+dcf.format(thongke.getTongTienTheoLoai(3)));
        lblSum.setText("Tổng tiền: "+dcf.format(thongke.getTongTienTheoLoai(3)+thongke.getTongTienTheoLoai(2)+thongke.getTongTienTheoLoai(1)));
         
        
    }

}
