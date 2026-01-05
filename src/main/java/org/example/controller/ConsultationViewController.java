package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.dao.RegistinfoDao;
import org.example.entity.Registinfo;
import org.example.util.AlertUtil;

public class ConsultationViewController {

    @FXML private TextField searchIdField;
    @FXML private TextField realname, age, gender, deptName, registLevel, registDate;
    @FXML private TextArea diagiosis, prescription;
    @FXML private TextField drugPrice;

    private RegistinfoDao dao = new RegistinfoDao();
    private Registinfo currentInfo; // 当前正在看诊的患者

    // 1. 查询患者
    @FXML
    public void searchPatient() {
        String idStr = searchIdField.getText();
        if (idStr == null || idStr.isEmpty()) {
            AlertUtil.showAlert(null, "提示", "查询失败", "请输入病历号");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            currentInfo = dao.getRegistInfoById(id);
            if (currentInfo != null) {
                // 回显数据到界面
                realname.setText(currentInfo.getRealname());
                age.setText(String.valueOf(currentInfo.getAge()));
                gender.setText(currentInfo.getGender());
                deptName.setText(currentInfo.getDeptName());
                registLevel.setText(currentInfo.getRegistLevel());
                registDate.setText(currentInfo.getRegistDate());

                // 如果已经看过诊，把之前的诊断也显示出来
                if (currentInfo.getDiagiosis() != null) diagiosis.setText(currentInfo.getDiagiosis());
                if (currentInfo.getPrescrption() != null) prescription.setText(currentInfo.getPrescrption());
                if (currentInfo.getDrugPrice() != null) drugPrice.setText(String.valueOf(currentInfo.getDrugPrice()));
            } else {
                AlertUtil.showAlert(null, "提示", "查询无果", "未找到该病历号的挂号信息");
            }
        } catch (NumberFormatException e) {
            AlertUtil.showAlert(null, "错误", "格式错误", "病历号必须是数字");
        }
    }

    // 2. 保存诊断
    @FXML
    public void saveConsultation() {
        if (currentInfo == null) {
            AlertUtil.showAlert(null, "错误", "操作无效", "请先查询患者");
            return;
        }
        try {
            String diag = diagiosis.getText();
            String pre = prescription.getText();
            String priceStr = drugPrice.getText();

            if (diag.isEmpty() || pre.isEmpty() || priceStr.isEmpty()) {
                AlertUtil.showAlert(null, "提示", "信息不全", "诊断、处方和价格不能为空");
                return;
            }

            // 更新对象数据
            currentInfo.setDiagiosis(diag);
            currentInfo.setPrescrption(pre);
            currentInfo.setDrugPrice(Double.parseDouble(priceStr));

            if (dao.updateDiagnosis(currentInfo)) {
                AlertUtil.showAlert(null, "成功", "保存成功", "看诊完成！状态已更新。");
            } else {
                AlertUtil.showAlert(null, "失败", "保存失败", "数据库错误");
            }
        } catch (NumberFormatException e) {
            AlertUtil.showAlert(null, "错误", "格式错误", "药品总价必须是数字");
        }
    }
}