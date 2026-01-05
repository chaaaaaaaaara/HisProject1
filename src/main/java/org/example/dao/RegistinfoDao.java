package org.example.dao;

import org.example.entity.Registinfo;
import org.example.util.DBUtil;
import java.sql.*;

public class RegistinfoDao {

    // 1. 获取当前最大病历号 (用于自动生成)
    public int getMaxRegistId() {
        int maxId = 100000; // 默认起点
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT MAX(id) FROM tb_registinfo";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                int dbMax = rs.getInt(1);
                if (dbMax > 0) maxId = dbMax;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return maxId;
    }

    // 2. 挂号 (插入数据)
    public boolean addRegistInfo(Registinfo info) {
        String sql = "INSERT INTO tb_registinfo(id, realname, gender, card_number, birthdate, age, home_address, dept_name, doctor_name, regist_level, is_book, registfee, regist_date, visit_state) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, info.getId());
            ps.setString(2, info.getRealname());
            ps.setString(3, info.getGender());
            ps.setString(4, info.getCardNumber());
            ps.setString(5, info.getBirthdate());
            ps.setInt(6, info.getAge());
            ps.setString(7, info.getHomeAddress());
            ps.setString(8, info.getDeptName());
            ps.setString(9, info.getDoctorName());
            ps.setString(10, info.getRegistLevel());
            ps.setString(11, info.getIsBook());
            ps.setDouble(12, info.getRegistfee());
            ps.setString(13, info.getRegistDate());
            ps.setInt(14, info.getVisitState());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 3. 根据ID查询挂号信息 (看诊和药房都要用)
    public Registinfo getRegistInfoById(int id) {
        Registinfo info = null;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM tb_registinfo WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                info = new Registinfo();
                info.setId(rs.getInt("id"));
                info.setRealname(rs.getString("realname"));
                info.setGender(rs.getString("gender"));
                info.setAge(rs.getInt("age"));
                info.setDeptName(rs.getString("dept_name"));
                info.setDoctorName(rs.getString("doctor_name"));
                info.setRegistLevel(rs.getString("regist_level"));
                info.setRegistDate(rs.getString("regist_date"));
                info.setDiagiosis(rs.getString("diagiosis"));
                info.setPrescrption(rs.getString("prescrption"));
                info.setDrugPrice(rs.getDouble("drug_price"));
                info.setVisitState(rs.getInt("visit_state"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return info;
    }

    // 4. 医生保存诊断信息 (更新状态为 2:已看诊)
    public boolean updateDiagnosis(Registinfo info) {
        String sql = "UPDATE tb_registinfo SET diagiosis=?, prescrption=?, drug_price=?, visit_state=2 WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, info.getDiagiosis());
            ps.setString(2, info.getPrescrption());
            ps.setDouble(3, info.getDrugPrice());
            ps.setInt(4, info.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 5. 药房发药 (更新状态为 3:已取药)
    public boolean dispenseDrug(int id) {
        String sql = "UPDATE tb_registinfo SET visit_state=3 WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}