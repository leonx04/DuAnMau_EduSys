/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV,MatKhau,HoTen,VaiTro) values (?,?,?,?)";
        JdbcHelper.update(sql,
                model.getMaNV(),
                model.getMatKhau(),
                model.getHoTen(),
                model.getVaiTro());
    }

    @Override
    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?";
        JdbcHelper.update(sql,
                model.getMatKhau(),
                model.getHoTen(),
                model.getVaiTro(),
                model.getMaNV());
    }

    @Override
    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien where MaNV = ?";
        JdbcHelper.update(sql, MaNV);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "select * from NhanVien";
        return selectBySql(sql);
    }

    @Override
    public NhanVien selectById(String MaNV) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        List<NhanVien> list = selectBySql(sql, MaNV);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
