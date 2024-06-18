package Main;

import QuanLyPizza.DAO.MyConnect;
import QuanLyPizza.GUI.DangNhapGUI;
import QuanLyPizza.GUI.DlgTimMaGiam;


public class Main {

    public static void main(String[] args) {
       new MyConnect();
      // System.out.println(new MyConnect().getConnection());
     changLNF("Nimbus");
     DangNhapGUI login = new DangNhapGUI();
      login.showWindow();
        
    }

    public static void changLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }
}
