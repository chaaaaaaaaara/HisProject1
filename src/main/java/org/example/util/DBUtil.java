package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // 1. 数据库配置
    // 注意：数据库名是 his，如果你的数据库名不一样，请修改这里
    private static final String URL = "jdbc:mysql://localhost:3306/his?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    // 🔥【重要】请把下面的 123456 改成你安装 MySQL 时设置的密码
    private static final String PASSWORD = "root";

    // 2. 加载驱动（静态代码块，只执行一次）
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("❌ 错误：找不到 MySQL 驱动！请检查 pom.xml 是否刷新成功。");
        }
    }

    // 3. 获取连接的方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 4. 关闭资源的方法 (Connection, Statement, ResultSet)
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 5. 测试方法：右键运行这个文件，看看能不能打印“连接成功”
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✅ 恭喜！数据库连接成功！");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("❌ 连接失败！请检查账号密码或数据库名称。");
            e.printStackTrace();
        }
    }
}