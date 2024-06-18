/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyPizza.BUS;

import QuanLyPizza.DAO.ThongKeDAO;
import QuanLyPizza.DTO.ThongKe;
import java.time.LocalDate;

import java.util.ArrayList;

/**
 * @author User
 */
public class ThongKeBUS {

    public ThongKeDAO thongKeDAO = new ThongKeDAO();
    private ArrayList<Double> doanhThuThang;

    public ThongKe thongKe(int nam) {
        return thongKeDAO.getThongKe(nam);
    }
    
    public ThongKe thongKe(String ngayBD, String ngayKT) {
        return thongKeDAO.getThongKe(ngayBD,ngayKT);
    }

    public double getDoanhThuThang(int thang, int nam) {
        return thongKeDAO.getDoanhThuThang(thang, nam);
    }
    
    public double getDoanhThuNgay(LocalDate date) {
        return thongKeDAO.getDoanhThuNgay(date);
    }
    
    public double getDoanhThuNgay(String dateBD, String dateKT) {
        return thongKeDAO.getDoanhThuNgay(dateBD,dateKT);
    }
    
    
    
}