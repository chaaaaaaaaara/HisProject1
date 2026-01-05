package org.example.entity;

public class Doctor {
    private String id;          // 对应数据库 id
    private String realname;    // 对应 realname
    private String password;    // 对应 password
    private String deptName;    // 对应 dept_name
    private String registLevel; // 对应 regist_level
    private Double registfee;   // 对应 registfee

    // 无参构造
    public Doctor() {}

    // 全参构造
    public Doctor(String id, String realname, String password, String deptName, String registLevel, Double registfee) {
        this.id = id;
        this.realname = realname;
        this.password = password;
        this.deptName = deptName;
        this.registLevel = registLevel;
        this.registfee = registfee;
    }

    // Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRealname() { return realname; }
    public void setRealname(String realname) { this.realname = realname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public String getRegistLevel() { return registLevel; }
    public void setRegistLevel(String registLevel) { this.registLevel = registLevel; }
    public Double getRegistfee() { return registfee; }
    public void setRegistfee(Double registfee) { this.registfee = registfee; }
}