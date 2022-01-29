import java.util.Date;

public class Employee extends Staff implements ICalculator{
    private double overtimeHour;

    public Employee (String staffId, String name, int age, 
                        double coefSalary, Date startDate, double numLeaveDays, 
                        String departmentName, double overtimeHour) {
        super(staffId, name, age, coefSalary, startDate, numLeaveDays, departmentName);
        this.overtimeHour = (double) Math.round(overtimeHour * 10) / 10;
    }

    public void calculateSalary() {
        long baseSalary = 3000000;
        double salary = getCoefSalary() * baseSalary + overtimeHour * 200000;
        setSalary(Double.valueOf(salary).longValue());
    }

    @Override
    public void displayInformation() {
        String leftAlignFormat = "ID: %-8sName: %-20sAge: %-4sCoef Salary: %-6sSalary: %-10s" +
                                    "Hire Date: %-12sDepartment: %-15sLeave Days: %-5sOvertime Hours: %-5s%n";
        System.out.format(leftAlignFormat, getId(), 
                                            getName(),
                                            getAge(),
                                            getCoefSalary(),
                                            getSalary(),
                                            HumanResources.df.format(getStartDate()),
                                            getDepartmentName(),
                                            getNumLeaveDays(),
                                            overtimeHour);
    }
}
