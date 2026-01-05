package org.example.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertUtil {
    // 弹出一个信息提示框
    public static void showAlert(Stage stage, String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // 消息类型
        if (stage != null) {
            alert.initOwner(stage);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait(); // 显示并等待用户点击确定
    }
}