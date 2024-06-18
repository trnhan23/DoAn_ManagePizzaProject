package QuanLyPizza.GUI;

import QuanLyPizza.BUS.ThongKeBUS;
import QuanLyPizza.DTO.SanPham;
import QuanLyPizza.DTO.ThongKe;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DlgLocThongKe extends javax.swing.JDialog {

    ThongKeBUS thongKeBUS = new ThongKeBUS();
    JButton btnBack;
    JLabel lblMon1, lblSoLuong1;
    int x = 200;
    int y = 160-50;

    int a = 575;
    int b = 160-50;

    public DlgLocThongKe(String ngayBD,String ngayKT) {

        initComponents();
        addControl();
        addEvent();
        hienThiThongKe(ngayBD, ngayKT);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    private void addControl() {


        btnBack = new JButton(new ImageIcon("image/icons8_undo_40px.png"));
        btnBack.setToolTipText("Quay lại");
        btnBack.setBounds(10, 10, 45, 45);
        pnThongKeChiTiet.add(btnBack);


    }

    private void addEvent() {
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void hienThiThongKe(String ngayBD,String ngayKT) {
        ThongKe thongKe = thongKeBUS.thongKe( ngayBD,ngayKT);

        for (SanPham sp : thongKe.getTopSanPhamBanChay()) {

            lblMon1 = new JLabel("");
            lblSoLuong1 = new JLabel("", JLabel.CENTER);
            lblMon1.setBounds(x, y += 50, 493, 50);
            lblSoLuong1.setBounds(a, b+=50, 128, 50);
            lblMon1.setForeground(Color.BLACK);
            lblSoLuong1.setForeground(Color.BLACK);
            Font fontChiTiet = new Font("Tahoma", Font.BOLD, 18);
            lblMon1.setFont(fontChiTiet);
            lblSoLuong1.setFont(fontChiTiet);
            lblMon1.setText(sp.getTenSP());
            lblSoLuong1.setText("" + sp.getSoLuong());
            pnThongKeChiTiet.add(lblMon1);
            pnThongKeChiTiet.add(lblSoLuong1);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnThongKeChiTiet = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\LT NN TT\\PROJECT\\ManagePizzaProject\\image\\ManagerUI\\bangChiTiet.png")); // NOI18N

        javax.swing.GroupLayout pnThongKeChiTietLayout = new javax.swing.GroupLayout(pnThongKeChiTiet);
        pnThongKeChiTiet.setLayout(pnThongKeChiTietLayout);
        pnThongKeChiTietLayout.setHorizontalGroup(
            pnThongKeChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongKeChiTietLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnThongKeChiTietLayout.setVerticalGroup(
            pnThongKeChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongKeChiTietLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnThongKeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnThongKeChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnThongKeChiTiet;
    // End of variables declaration//GEN-END:variables
}
