package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.controller.LoginViewController;

import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. 加载 FXML 文件
        FXMLLoader loader = new FXMLLoader();
        // 注意路径：/org/example/view/LoginView.fxml
        URL url = getClass().getResource("/org/example/view/LoginView.fxml");
        if (url == null) {
            System.out.println("❌ 错误：找不到FXML文件！请检查resources文件夹结构。");
            return;
        }
        loader.setLocation(url);

        // 2. 加载布局
        Pane root = loader.load();

        // 3. 将 Stage 传给 Controller (为了方便弹窗)
        LoginViewController controller = loader.getController();
        controller.primaryStage = primaryStage;

        // 4. 显示窗口
        primaryStage.setTitle("东软云医院HIS系统");
        primaryStage.setScene(new Scene(root, 600, 400)); // 宽度600，高度400
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}