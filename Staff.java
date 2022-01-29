import java.util.Date;

public abstract class Staff  {
    private String staffId;
    private String name;
    private int age;
    private double coefSalary;
    private Date startDate;
    private double numLeaveDays;
    private String departmentName;
    private long salary;

    public Staff (String staffId, String name, int age, double coefSalary, 
                    Date startDate, double numLeaveDays, String departmentName) {
        this.staffId = staffId;
        this.name = name;
        this.age = age;
        setCoefSalary(coefSalary);
        this.startDate = startDate;
        setNumLeaveDays(numLeaveDays);
        this.departmentName = departmentName;
    }

    public abstract void displayInformation();
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    };
    public void setName(String name) {
        this.name = name;
    };
    public void setAge(int age) {
        this.age = age;
    };
    public void setCoefSalary(double coefSalary) {
        this.coefSalary = (double) Math.round(coefSalary * 100) / 100;
    };
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    };
    public void setNumLeaveDays(double numLeaveDays) {
        this.numLeaveDays = (double) Math.round(numLeaveDays * 10) / 10;
    };
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    };
    public void setSalary(long salary) {
        this.salary = salary;
    };

    public String getId() {
        return staffId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getCoefSalary() {
        return coefSalary;
    }
    public Date getStartDate() {
        return startDate;
    }
    public double getNumLeaveDays() {
        return numLeaveDays;
    }
    public String getDepartmentName() { 
        return departmentName;
    }
    public long getSalary() {
        return salary;
    }
}

