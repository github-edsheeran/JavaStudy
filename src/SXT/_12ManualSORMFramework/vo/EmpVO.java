package SXT._12ManualSORMFramework.vo;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 联表查询对应的实体类
 * @author: LiuDongMan
 * @createdDate: 2019-10-21 10:13
 **/
public class EmpVO {
    private Integer id;
    private String empName;
    private Long totalSalary;
    private String deptName;
    private String address;

    public EmpVO() {
    }

    public EmpVO(Integer id, String empName, Long totalSalary, String deptName, String address) {
        this.id = id;
        this.empName = empName;
        this.totalSalary = totalSalary;
        this.deptName = deptName;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
