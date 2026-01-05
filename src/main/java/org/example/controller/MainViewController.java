package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.MainApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    // 当前登录用户的身份级别 (1:挂号员, 2:医生, 3:药剂师)
    public static int level;
    public static Stage primaryStage; // 主舞台

    @FXML private MenuItem ghmenu; // 挂号菜单项
    @FXML private MenuItem kzmenu; // 看诊菜单项
    @FXML private MenuItem yfmenu; // 药房菜单项

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 根据身份禁用菜单 (权限控制)
        if (level == 1) { // 挂号员
            kzmenu.setDisable(true); // 禁医生
            yfmenu.setDisable(true); // 禁药房
        } else if (level == 2) { // 医生
            ghmenu.setDisable(true);
            yfmenu.setDisable(true);
        } else if (level == 3) { // 药剂师
            ghmenu.setDisable(true);
            kzmenu.setDisable(true);
        }
    }

    // 点击“现场挂号”菜单触发
    @FXML
    public void addRegisterView() {
        try {
            // 加载挂号界面 (这个文件我们下一阶段再做，先写好逻辑)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/view/AddRegistView.fxml"));
            Pane root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("现场挂号");
            stage.initModality(Modality.APPLICATION_MODAL); // 锁住主窗口
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            // 如果文件还没做，暂时弹个窗提示
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("挂号界面开发中...");
            alert.show();
        }
    }

    // 点击“医生看诊”菜单
    @FXML
    public void addConsultationView() {
        openWindow("/org/example/view/ConsultationView.fxml", "医生看诊工作站");
    }

    // 点击“药房发药”菜单
    // 注意：去 MainView.fxml 把 MenuItem fx:id="yfmenu" 的 onAction 改成 "#addPharmacyView"
    @FXML
    public void addPharmacyView() {
        openWindow("/org/example/view/PharmacyView.fxml", "药房管理系统");
    }

    // 通用的打开窗口方法
    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("无法打开界面: " + e.getMessage());
            alert.show();
        }
    }
}