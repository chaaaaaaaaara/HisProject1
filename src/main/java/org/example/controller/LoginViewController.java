package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.dao.DoctorDao;
import org.example.entity.Doctor;
import org.example.util.AlertUtil;

public class LoginViewController {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginButton;

    public static Stage primaryStage;

    @FXML
    public void loginButtonOnClick() {
        String uid = username.getText();
        String pwd = password.getText();
        boolean isLogin = false; // 标记是否登录成功
        int userLevel = 0;       // 记录身份

        // 1. 校验逻辑
        if (uid.isEmpty() || pwd.isEmpty()) {
            AlertUtil.showAlert(primaryStage, "失败", "错误", "账号密码不能为空");
            return;
        }

        // 2. 身份判断
        if ("guahao".equals(uid) && "guahao".equals(pwd)) {
            isLogin = true;
            userLevel = 1; // 挂号员
        } else if ("yaofang".equals(uid) && "yaofang".equals(pwd)) {
            isLogin = true;
            userLevel = 3; // 药剂师
        } else {
            // 查数据库找医生
            DoctorDao dao = new DoctorDao();
            Doctor doctor = dao.findDoctorById(uid);
            if (doctor != null && doctor.getPassword().equals(pwd)) {
                isLogin = true;
                userLevel = 2; // 医生
            } else {
                AlertUtil.showAlert(primaryStage, "失败", "登录失败", "账号或密码错误");
            }
        }

        // 3. 跳转逻辑 (重点！)
        if (isLogin) {
            try {
                // 设置全局身份
                MainViewController.level = userLevel;

                // 加载主界面
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/view/MainView.fxml"));
                Pane root = loader.load();

                // 切换场景
                Scene scene = new Scene(root);
                primaryStage.setTitle("东软云医院HIS系统 - 主菜单");
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen(); // 居中显示

                // 把主舞台传给主控制器
                MainViewController.primaryStage = primaryStage;

            } catch (Exception e) {
                e.printStackTrace();
                AlertUtil.showAlert(primaryStage, "错误", "系统错误", "无法加载主界面：" + e.getMessage());
            }
        }
    }
}