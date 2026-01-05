package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.dao.RegistinfoDao;
import org.example.entity.Registinfo;
import org.example.util.AlertUtil;

public class PharmacyViewController {

    @FXML private TextField searchIdField;
    @FXML private TextArea prescriptionArea;
    @FXML private Label priceLabel;
    @FXML private Label statusLabel;
    @FXML private Button dispenseBtn;

    private RegistinfoDao dao = new RegistinfoDao();
    private Registinfo currentInfo;

    @FXML
    public void searchPatient() {
        String idStr = searchIdField.getText();
        if (idStr == null || idStr.isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr);
            currentInfo = dao.getRegistInfoById(id);

            if (currentInfo != null) {
                prescriptionArea.setText(currentInfo.getPrescrption());
                priceLabel.setText(String.valueOf(currentInfo.getDrugPrice()));

                int state = currentInfo.getVisitState();
                if (state == 1) {
                    statusLabel.setText("当前状态: 已挂号 (未看诊)");
                    dispenseBtn.setDisable(true);
                } else if (state == 2) {
                    statusLabel.setText("当前状态: 待取药");
                    dispenseBtn.setDisable(false); // 只有这时候能点
                } else if (state == 3) {
                    statusLabel.setText("当前状态: 已取药");
                    dispenseBtn.setDisable(true);
                }
            } else {
                AlertUtil.showAlert(null, "提示", "无果", "未找到信息");
                clearFields();
            }
        } catch (NumberFormatException e) {
            AlertUtil.showAlert(null, "错误", "错误", "病历号必须是数字");
        }
    }

    @FXML
    public void handleDispense() {
        if (currentInfo != null) {
            if (dao.dispenseDrug(currentInfo.getId())) {
                AlertUtil.showAlert(null, "成功", "发药成功", "库存已扣减，流程结束。");
                dispenseBtn.setDisable(true);
                statusLabel.setText("当前状态: 已取药");
            }
        }
    }

    private void clearFields() {
        prescriptionArea.setText("");
        priceLabel.setText("0.00");
        statusLabel.setText("");
        dispenseBtn.setDisable(true);
    }
}