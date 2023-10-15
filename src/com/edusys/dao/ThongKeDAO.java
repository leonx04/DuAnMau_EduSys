/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    public List<Object[]> getNguoiHoc(){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeNguoiHoc}";
                rs = JdbcHelper.query(sql);
                while (rs.next()) {                    
                    Object[] model ={
                        rs.getInt("Nam"),
                        rs.getInt("SoLuong"),
                        rs.getDate("DauTien"),
                        rs.getDate("CuoiCung")
                    };
                    list.add(model);
                }
            } 
            finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Object[]> getBangDiem(Integer MaKH){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_BangDiem(?)}";
                rs = JdbcHelper.query(sql, MaKH);
                while (rs.next()) {                    
                    double diem = rs.getDouble("Diem");
                    String xeploai = "Xuất sắc";
                    if(diem < 0){
                        xeploai = "Chưa nhập";
                    }else if(diem < 3){
                        xeploai = "Kém";
                    }else if(diem < 5){
                        xeploai = "Yếu";
                    }else if(diem < 6.5){
                        xeploai = "Trung bình";
                    }else if(diem < 7.5){
                        xeploai = "Khá";
                    }else if(diem < 9){
                        xeploai = "Giỏi";
                    }
                    Object[] model = {
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        diem,
                        xeploai
                    };
                    list.add(model);
                }
            } 
            finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Object[]> getDiemTheoChuyenDe(){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDiem}";
                rs = JdbcHelper.query(sql);
                while (rs.next()) {                    
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoHV"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } 
            finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Object[]> getDoanhThu(int nam){
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu(?)}";
                rs = JdbcHelper.query(sql, nam);
                while (rs.next()) {                    
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoKH"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } 
            finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}
