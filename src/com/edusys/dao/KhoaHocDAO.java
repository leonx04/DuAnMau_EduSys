/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import com.edusys.entity.KhoaHoc;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{

    @Override
    public void insert(KhoaHoc model) {
        String sql = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?,?,?,?,?,?)";
        JdbcHelper.update(sql, 
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV());
    }

    @Override
    public void update(KhoaHoc model) {
        String sql = "UPDATE KhoaHoc set MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ? where MaKH = ?";
        JdbcHelper.update(sql, 
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }

    @Override
    public void delete(Integer MaKH) {
        String sql = "DELETE FROM KhoaHoc where MaKH = ?";
        JdbcHelper.update(sql, MaKH);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String sql = "SELECT * FROM KhoaHoc";
        return selectBySql(sql);
    }

    @Override
    public KhoaHoc selectById(Integer MaKH) {
        String sql = "SELECT * FROM KhoaHoc where MaKH = ?";
        List<KhoaHoc> list = selectBySql(sql, MaKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    KhoaHoc entity = new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
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
    
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql = "select * from khoahoc where MaCD = ?";
        return this.selectBySql(sql, macd);
    }
    
    public List<Integer> selectYears(){
        String sql = "select distinct year(NgayKG) as year from khoahoc order by year desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
