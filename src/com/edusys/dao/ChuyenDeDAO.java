/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import java.util.List;
import com.edusys.utils.JdbcHelper;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{

    @Override
    public void insert(ChuyenDe model) {
        String sql = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES(?,?,?,?,?,?)";
        JdbcHelper.update(sql, 
                model.getMaCD(),
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa());
    }

    @Override
    public void update(ChuyenDe model) {
        String sql = "UPDATE ChuyenDe set TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? where MaCD = ?";
        JdbcHelper.update(sql, 
                model.getTenCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getHinh(),
                model.getMoTa(),
                model.getMaCD());
    }

    @Override
    public void delete(String MaCD) {
        String sql = "DELETE FROM ChuyenDe where MaCD = ?";
        JdbcHelper.update(sql, MaCD);
    }
    
    @Override
    public List<ChuyenDe> selectAll() {
        String sql = "SELECT * FROM ChuyenDe";
        return selectBySql(sql);
    }
    
    @Override
    public ChuyenDe selectById(String MaCD) {
        String sql = "SELECT * FROM ChuyenDe where MaCD = ?";
        List<ChuyenDe> list = selectBySql(sql, MaCD);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {                    
                    ChuyenDe entity = new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setMoTa(rs.getString("MoTa"));
                    list.add(entity);
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
    
}
