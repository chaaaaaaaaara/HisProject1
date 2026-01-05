package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.dao.DoctorDao;
import org.example.dao.RegistinfoDao;
import org.example.entity.Doctor;
import org.example.entity.Registinfo;
import org.example.util.AlertUtil;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddRegistViewController implements Initializable {

    // 绑定界面控件
    @FXML private TextField id;
    @FXML private TextField realname;
    @FXML private RadioButton genderMan;
    @FXML private RadioButton genderWoman;
    @FXML private TextField cardnumber;
    @FXML private TextField age;
    @FXML private TextField homeAddress;

    @FXML private ComboBox<String> deptName;
    @FXML private ComboBox<String> registLevel;
    @FXML private ComboBox<String> doctorName;
    @FXML private CheckBox isBook;
    @FXML private Label registfee;

    private DoctorDao doctorDao = new DoctorDao();
    private RegistinfoDao registDao = new RegistinfoDao();
    public static Stage registStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. 自动生成病历号
        int nextId = registDao.getMaxRegistId() + 1;
        id.setText(String.valueOf(nextId));

        // 2. 也是最关键的：初始化科室下拉框
        List<String> depts = doctorDao.getAllDepartments();
        deptName.getItems().addAll(depts);

        // 3. 监听科室选择 -> 变动挂号级别
        deptName.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                registLevel.getItems().clear();
                doctorName.getItems().clear(); // 清空下级
                registLevel.getItems().addAll(doctorDao.getRegistLevelsByDept(newVal));
            }
        });

        // 4. 监听级别选择 -> 变动医生
        registLevel.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && deptName.getValue() != null) {
                doctorName.getItems().clear();
                List<Doctor> docs = doctorDao.getDoctorsByDeptAndLevel(deptName.getValue(), newVal);
                for (Doctor d : docs) {
                    doctorName.getItems().add(d.getRealname());
                }
            }
        });

        // 5. 监听医生选择 -> 变动价格
        doctorName.valueProperty().addListener((obs, oldVal, newVal) -> {
            updatePrice();
        });

        // 6. 监听病历本勾选 -> 变动价格
        isBook.selectedProperty().addListener((obs, oldVal, newVal) -> updatePrice());

        // 分组单选按钮
        ToggleGroup group = new ToggleGroup();
        genderMan.setToggleGroup(group);
        genderWoman.setToggleGroup(group);
    }

    // 计算价格的辅助方法
    private void updatePrice() {
        double price = 0.0;
        // 查医生费用
        if (deptName.getValue() != null && registLevel.getValue() != null && doctorName.getValue() != null) {
            List<Doctor> docs = doctorDao.getDoctorsByDeptAndLevel(deptName.getValue(), registLevel.getValue());
            for (Doctor d : docs) {
                if (d.getRealname().equals(doctorName.getValue())) {
                    price = d.getRegistfee();
                    break;
                }
            }
        }
        // 加病历本费
        if (isBook.isSelected()) {
            price += 1.0;
        }
        registfee.setText(String.valueOf(price));
    }

    @FXML
    public void handleRegist() {
        try {
            Registinfo info = new Registinfo();
            info.setId(Integer.parseInt(id.getText()));
            info.setRealname(realname.getText());
            info.setGender(genderMan.isSelected() ? "男" : "女");
            info.setCardNumber(cardnumber.getText());
            info.setAge(Integer.parseInt(age.getText()));
            info.setHomeAddress(homeAddress.getText());
            info.setDeptName(deptName.getValue());
            info.setRegistLevel(registLevel.getValue());
            info.setDoctorName(doctorName.getValue());
            info.setIsBook(isBook.isSelected() ? "是" : "否");
            info.setRegistfee(Double.parseDouble(registfee.getText()));
            info.setRegistDate(LocalDate.now().toString());
            info.setVisitState(1); // 1=已挂号

            if (registDao.addRegistInfo(info)) {
                AlertUtil.showAlert(registStage, "成功", "挂号成功", "请去诊室就诊");
                closeWindow();
            } else {
                AlertUtil.showAlert(registStage, "失败", "挂号失败", "数据库保存出错");
            }
        } catch (Exception e) {
            AlertUtil.showAlert(registStage, "错误", "数据无效", "请检查年龄是否为数字，且所有必填项已填");
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow() {
        if (registStage != null) registStage.close();
    }
}