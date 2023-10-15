/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import com.edusys.entity.HocVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer>{

    @Override
    public void insert(HocVien model) {
        String sql = "INSERT INTO HocVien(MaKH,MaNH,Diem) values(?,?,?)";
        JdbcHelper.update(sql, 
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem());
    }

    @Override
    public void update(HocVien model) {
        String sql = "UPDATE HocVien set MaKH = ?, MaNH = ?, Diem = ? where MaHV = ?";
        JdbcHelper.update(sql, 
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem(),
                model.getMaHV());
    }

    @Override
    public void delete(Integer MaHV) {
        String sql = "DELETE FROM HocVien where MaHV = ?";
        JdbcHelper.update(sql, MaHV);
    }

    @Override
    public List<HocVien> selectAll() {
        String sql = "SELECT * FROM HocVien";
        return selectBySql(sql);
    }

    @Override
    public HocVien selectById(Integer MaHV) {
        String sql = "SELECT * FROM HocVien where MaHV = ?";
        List<HocVien> list = selectBySql(sql, MaHV);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    HocVien entity = new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<HocVien> selectByKhoaHoc(int maKH){
        String sql = "select * from HOCVIEN where MaKH = ?";
        return this.selectBySql(sql, maKH);
    }
    
}
