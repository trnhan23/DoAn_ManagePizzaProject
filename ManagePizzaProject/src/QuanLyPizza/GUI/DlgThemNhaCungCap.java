package QuanLyPizza.GUI;

import QuanLyPizza.BUS.DangNhapBUS;
import QuanLyPizza.BUS.NhaCungCapBUS;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DlgThemNhaCungCap extends javax.swing.JDialog {

    public DlgThemNhaCungCap() {
        init();
        checkThemNCC = false;
        initComponents();
        ImageIcon icon1 = new ImageIcon("image/ManagerUI/pizza-shop.png"); // Đường dẫn tới biểu tượng của bạn
        this.setIconImage(icon1.getImage());
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setResizable(false);
    }
    public static final Logger logger = Logger.getLogger(DlgThemNhaCungCap.class);
     void init(){
         PropertyConfigurator.configure("src\\Log\\log4j.properties");
        setLocationRelativeTo(null);}
    private boolean checkThemNCC = false;

    public boolean getCheckThemNCC() {
        return checkThemNCC;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenNCC = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtDienThoai = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Thêm mới Nhà cung cấp");
        jPanel1.add(jLabel1);

        txtTenNCC.setColumns(15);
        txtTenNCC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenNCC.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên nhà cung cấp"));
        txtTenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNCCActionPerformed(evt);
            }
        });
        txtTenNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenNCCKeyTyped(evt);
            }
        });

        txtDiaChi.setColumns(15);
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDiaChi.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        txtDienThoai.setColumns(15);
        txtDienThoai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDienThoai.setBorder(javax.swing.BorderFactory.createTitledBorder("Điện thoại"));
        txtDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDienThoaiActionPerformed(evt);
            }
        });
        txtDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDienThoaiKeyTyped(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(77, 40));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem);

        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.setPreferredSize(new java.awt.Dimension(77, 40));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel3.add(btnHuy);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDienThoai)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenNCC))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
        boolean flag = nhaCungCapBUS.themNhaCungCap(txtTenNCC.getText(), txtDiaChi.getText(), txtDienThoai.getText());
        checkThemNCC = flag;
        if (flag) {
            String logname = DangNhapBUS.taiKhoanLogin.getTenDangNhap();
        String quyen = DangNhapBUS.taiKhoanLogin.getQuyen();
       
            logger.info(quyen +" "+logname+" thêm mới nhà cung cấp: "+txtTenNCC.getText());
            this.dispose();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNCCActionPerformed
        txtDiaChi.requestFocus();
    }//GEN-LAST:event_txtTenNCCActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        txtDienThoai.requestFocus();
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDienThoaiActionPerformed
        btnThem.doClick();
    }//GEN-LAST:event_txtDienThoaiActionPerformed

    private void txtTenNCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNCCKeyTyped
       char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ' && !Character.isISOControl(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTenNCCKeyTyped

    private void txtDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDienThoaiKeyTyped
       char c = evt.getKeyChar();
        String phoneNumber = txtDienThoai.getText();

        if ((phoneNumber.length() >= 10 && c != java.awt.event.KeyEvent.VK_BACK_SPACE)
                || (!Character.isDigit(c) && !Character.isISOControl(c))
                || (phoneNumber.length() == 0 && c != '0')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDienThoaiKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables
}
