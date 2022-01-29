import java.util.Date;

public class Manager extends Staff implements ICalculator {
    private String title;

    public Manager (String staffId, String name, int age, 
                        double coefSalary, Date startDate, double numLeaveDays, 
                        String departmentName, String title) {
        super(staffId, name, age, coefSalary, startDate, numLeaveDays, departmentName);
        this.title = title;
    }

    public void calculateSalary() {
        long baseSalary = 5000000;
        double salary = getCoefSalary() * baseSalary + calculateBonus();
        setSalary(Double.valueOf(salary).longValue());
    }

    public double calculateBonus() {
        long bonus = 0;
        if (title.equalsIgnoreCase("Business Leader")) {
            bonus = 8000000;
        } else if (title.equalsIgnoreCase("Project Leader")) {
            bonus = 5000000;
        } else if (title.equalsIgnoreCase("Technical Leader")) {
            bonus = 6000000;
        }
        return bonus;
    }

    @Override
    public void displayInformation() {
        String leftAlignFormat = "ID: %-8sName: %-20sAge: %-4sCoef Salary: %-6sSalary: %-10s" +
                                    "Hire Date: %-12sDepartment: %-15sLeave Days: %-5sTitle: %-15s%n";
        System.out.format(leftAlignFormat, getId(), 
                                            getName(),
                                            getAge(),
                                            getCoefSalary(),
                                            getSalary(),
                                            HumanResources.df.format(getStartDate()),
                                            getDepartmentName(),
                                            getNumLeaveDays(),
                                            title);
    }
}

