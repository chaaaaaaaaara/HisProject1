package org.example.entity;

public class Registinfo {
    private Integer id;
    private String realname;
    private String gender;
    private String cardNumber;
    private String birthdate;
    private Integer age;
    private String homeAddress;
    private String deptName;
    private String doctorName;
    private String registLevel;
    private String isBook;
    private Double registfee;
    private String registDate;
    private Integer visitState;
    private String diagiosis;   // 诊断结果 (后面看诊要用)
    private String prescrption; // 处方 (后面看诊要用)
    private Double drugPrice;   // 药品费 (后面看诊要用)

    // 无参构造
    public Registinfo() {
    }

    // --- 下面就是你要的 Getter 和 Setter 方法 (我都帮你生成好了) ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRegistLevel() {
        return registLevel;
    }

    public void setRegistLevel(String registLevel) {
        this.registLevel = registLevel;
    }

    public String getIsBook() {
        return isBook;
    }

    public void setIsBook(String isBook) {
        this.isBook = isBook;
    }

    public Double getRegistfee() {
        return registfee;
    }

    public void setRegistfee(Double registfee) {
        this.registfee = registfee;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public Integer getVisitState() {
        return visitState;
    }

    public void setVisitState(Integer visitState) {
        this.visitState = visitState;
    }

    public String getDiagiosis() {
        return diagiosis;
    }

    public void setDiagiosis(String diagiosis) {
        this.diagiosis = diagiosis;
    }

    public String getPrescrption() {
        return prescrption;
    }

    public void setPrescrption(String prescrption) {
        this.prescrption = prescrption;
    }

    public Double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Double drugPrice) {
        this.drugPrice = drugPrice;
    }
}