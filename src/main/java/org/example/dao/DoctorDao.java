package org.example.dao;

import org.example.entity.Doctor;
import org.example.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorDao {

    // 根据账号(id)查询医生信息
    public Doctor findDoctorById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Doctor doctor = null;

        try {
            conn = DBUtil.getConnection();
            // SQL语句：根据ID查人
            String sql = "SELECT * FROM tb_doctor WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setId(rs.getString("id"));
                doctor.setRealname(rs.getString("realname"));
                doctor.setPassword(rs.getString("password"));
                doctor.setDeptName(rs.getString("dept_name"));
                doctor.setRegistLevel(rs.getString("regist_level"));
                doctor.setRegistfee(rs.getDouble("registfee"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return doctor;
    }

    // 1. 获取所有不重复的科室名称
    public java.util.List<String> getAllDepartments() {
        java.util.List<String> list = new java.util.ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT DISTINCT dept_name FROM tb_doctor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) list.add(rs.getString("dept_name"));
        } catch (Exception e) { e.printStackTrace(); }
        finally { DBUtil.close(rs, ps, conn); }
        return list;
    }

    // 2. 根据科室获取挂号级别
    public java.util.List<String> getRegistLevelsByDept(String dept) {
        java.util.List<String> list = new java.util.ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT DISTINCT regist_level FROM tb_doctor WHERE dept_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dept);
            rs = ps.executeQuery();
            while (rs.next()) list.add(rs.getString("regist_level"));
        } catch (Exception e) { e.printStackTrace(); }
        finally { DBUtil.close(rs, ps, conn); }
        return list;
    }

    // 3. 根据科室和级别获取医生
    public java.util.List<Doctor> getDoctorsByDeptAndLevel(String dept, String level) {
        java.util.List<Doctor> list = new java.util.ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM tb_doctor WHERE dept_name = ? AND regist_level = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dept);
            ps.setString(2, level);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getString("id"));
                d.setRealname(rs.getString("realname"));
                d.setRegistfee(rs.getDouble("registfee"));
                list.add(d);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally { DBUtil.close(rs, ps, conn); }
        return list;
    }
}