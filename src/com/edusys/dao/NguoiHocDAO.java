/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import com.edusys.entity.NguoiHoc;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{

    @Override
    public void insert(NguoiHoc model) {
        String sql = "INSERT INTO NguoiHoc (MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV) values(?,?,?,?,?,?,?,?)";
        JdbcHelper.update(sql,
                model.getMaNH(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV());
    }

    @Override
    public void update(NguoiHoc model) {
        String sql = "UPDATE NguoiHoc set HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ? where MaNH = ?";
        JdbcHelper.update(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.getGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaNH());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM NguoiHoc where MaNH = ?";
        JdbcHelper.update(sql, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        String sql = "SELECT * FROM NguoiHoc";
        return selectBySql(sql);
    }

    @Override
    public NguoiHoc selectById(String MaNH) {
        String sql = "SELECT * FROM NguoiHoc where MaNH = ?";
        List<NguoiHoc> list = selectBySql(sql,MaNH);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {                
                    NguoiHoc entity = new NguoiHoc();
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDienThoai(rs.getString("DienThoai"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayDK(rs.getDate("NgayDK"));
                    list.add(entity);
            }
            }finally{
                rs.getStatement().getConnection().close();
            } 
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeyword(String HoTen){
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return selectBySql(sql, "%"+HoTen+"%");
    }

    public List<NguoiHoc> selectNotInCourse(int MaKH, String HoTen) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return selectBySql(sql,"%"+HoTen+"%", MaKH);
    }
    
}

