import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;

import java.io.FileReader;
import java.io.BufferedReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;

/**
 * The Human Resources program
 * 
 * @author Mai Thi Yen Linh - FX14122
 * @since 2022-01-08
 */
public class HumanResources {
    // creat common variables
    private static Scanner input = new Scanner(System.in);
    private static Calendar cal = Calendar.getInstance();
    final static LocalDate today = LocalDate.now();
    final static int currYear = cal.get(Calendar.YEAR);
    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private static IdGenerator generator = new IdGenerator();
    private static ArrayList<Staff> staffList = new ArrayList<Staff>();
    private static ArrayList<Department> departmentList = new ArrayList<Department>();
    private static int lastEmployeeId, lastDepartmentId, lastManagerId;
    private static int optionFunction;
    public static void main(String[] args) {
        //create staff list and department list
        createEmployeeList();
        createManagerList();
        createDepartmentList();

        // chooesing the function of application and execute that function
        do {
            optional();
            optionFunction = checkIntNumber(8, "Chọn chức năng: ");
            executeOptional();
        } while (optionFunction != 0);
        System.out.println("----Thoát chương trình----");
    }
    public static int checkIntNumber(int maxNumber, String mess) {
        int option;
        while (true) {
            try {
                System.out.print(mess);
                option = input.nextInt();
                if (option >= 0 && option <= maxNumber) {
                    break;
                }
            }
            catch (Exception e) {
                input.next();
                continue;
            }
        }
        return option;
    }
    // overloading 
    public static int checkIntNumber(int minNumber, int maxNumber, String mess) {
        int option;
        while (true) {
            try {
                System.out.print(mess);
                option = input.nextInt();
                if (option > minNumber && option < maxNumber) {
                    break;
                }
            }
            catch (Exception e) {
                input.next();
                continue;
            }
        }
        return option;
    }
    
    public static double checkDoubleNumber(double maxNumber, String mess) {
        double option;
        while (true) {
            try {
                System.out.print(mess);
                option = input.nextDouble();
                if (option >= 0 && option <= maxNumber) {
                    break;
                }
            }
            catch (Exception e) {
                input.next();
                continue;
            }
        }
        return option;
    }
    // overloading 
    public static double checkDoubleNumber(double minNumber, double maxNumber, String mess) {
        double option;
        while (true) {
            try {
                System.out.print(mess);
                option = input.nextDouble();
                if (option > minNumber && option < maxNumber) {
                    break;
                }
            }
            catch (Exception e) {
                input.next();
                continue;
            }
        }
        return option;
    }
    // 
    public static String scannerString(String mess) {
        System.out.print(mess);
        input.nextLine();
        return input.nextLine();
    }
    // display the funtionals of application
    public static void optional() {
        System.out.println("----***************************************************************----");
        System.out.println("Type 1 - Hiển thị danh sách hiện có trong công ty");
        System.out.println("Type 2 - Hiển thị các bộ phận trong công ty");
        System.out.println("Type 3 - Hiển thị các nhân viên theo từng bộ phận");
        System.out.println("Type 4 - Thêm nhân viên mới vào công ty");
        System.out.println("Type 5 - Tìm kiếm thông tin nhân viên theo tên hoặc mã nhân viên");
        System.out.println("Type 6 - Hiển thị bảng lương của nhân viên toàn công ty");
        System.out.println("Type 7 - Hiển thị bảng lương theo thứ tự tăng dần");
        System.out.println("Type 8 - Hiển thị bảng lương theo thứ tự giảm dần");
        System.out.println("Type 0 - Thoát");
        System.out.println("----***************************************************************----");    
    }
    public static void optionEndFunction() {
        System.out.println("----***************************************************************----");
        System.out.println("Type 0 - Thoát chương trình");
        System.out.println("Type 1 - Quay trở lại bảng điều khiển");
        optionFunction = checkIntNumber(1, "");
    }
    public static void optionEndFunction(String mess) {
        System.out.println("----***************************************************************----");
        System.out.println("Type 0 - Thoát chương trình");
        System.out.println("Type 1 - Quay trở lại bảng điều khiển chương trình");
        System.out.println("Type 2 - " + mess);
        optionFunction = checkIntNumber(2, "");
    }
    //making employee list of company
    public static void createEmployeeList() {
        String employeeFile = "./employees.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(employeeFile));
            generator.init("EMP", 0);
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Employee employee = new Employee(generator.generate(), 
                                                    row[0], 
                                                    currYear - Integer.parseInt(row[1]), 
                                                    Double.parseDouble(row[3]), 
                                                    df.parse(row[2]), 
                                                    Double.parseDouble(row[5]), 
                                                    row[4], 
                                                    Double.parseDouble(row[6]));
                employee.calculateSalary();
                staffList.add(employee);
            }
            lastEmployeeId = generator.getId();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //making manager list of company
    public static void createManagerList() {
        String managerFile = "./manager.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(managerFile));
            generator.init("MGT", 0);
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Manager manager = new Manager(generator.generate(), 
                                                    row[0], 
                                                    currYear - Integer.parseInt(row[1]), 
                                                    Double.parseDouble(row[3]), 
                                                    df.parse(row[2]), 
                                                    Double.parseDouble(row[5]), 
                                                    row[4], 
                                                    row[6]);
                manager.calculateSalary();
                staffList.add(manager);
            }
            lastManagerId = generator.getId();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //making department list of company
    public static void createDepartmentList() {
        String employeeFile = "./department.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(employeeFile));
            generator.init("DPT", 0);
            while ((line = reader.readLine()) != null) {
                int count = 0;
                for (Staff staff : staffList) {
                    if (line.equalsIgnoreCase(staff.getDepartmentName())) {
                        count++;
                    }
                }
                Department department = new Department(generator.generate(10),
                                                        line,
                                                        count);
                departmentList.add(department);
            }
            lastDepartmentId = generator.getId();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    // display the staffs in company
    public static void displayStaff() {
        for (Staff staff : staffList) {
            staff.displayInformation();
        }
        optionEndFunction();
    }
    // overloading of displayStaff method
    public static void displayStaff(String departmentName) {
        System.out.println("----------Danh sách nhân viên " + departmentName + "----------");
        int count = 0;
        for (Staff staff : staffList) {
            if(departmentName.equalsIgnoreCase(staff.getDepartmentName())) {
                count++;
                staff.displayInformation();
            } 
        }
        if(count == 0) {
            System.out.println("Không có nhân viên thuộc bộ phận");
        }
        optionEndFunction("Quay trở lại bảng chọn bộ phận");
    }
    // display the optional table to chooose the departments in the company
    public static String chooseDepartment(String mess) {
        System.out.println("----Danh sách các bộ phận trong công ty----");
        String[] departmentName = new String [departmentList.size() + 1];
        int i = 0;
        departmentName[0] = mess;
        for (Department department : departmentList) {
            departmentName[++i] = department.getDepartmentName();
            System.out.format("Type %-2d - %s", i, departmentName[i]);
            System.out.println();
        }
        System.out.println("Type 0  - " + departmentName[0]);
        int option = checkIntNumber(departmentName.length - 1, "Chọn bộ phận: ");
        return option == 0 ? "0" : departmentName[option];
    }
    // display all the departments in the company
    public static void displayDepartment() {
        System.out.println("Danh sách bộ phận trong công ty");
        for (Department department : departmentList) {
            System.out.println(department.toString());
        }
        optionEndFunction();
    }
    //add new staff, user give info and save to staff list
    public static void addNewStaff() {
        String date;
        Date hireDate;
        
        System.out.println("Type 1 - Add new employee");
        System.out.println("Type 2 - Add new manager");
        System.out.println("Type 3 - Quay trở lại bảng điều khiển");
        System.out.println("Type 0 - Thoát chương trình");

        int option = checkIntNumber(3, "Chọn kiểu thêm nhân viên: ");
        if (option == 0) {
            optionFunction = 0;
            return;
        } else if (option == 3) {
            optionFunction = 1;
            return;
        }
        String nameStaff = scannerString("Nhập tên nhân viên: ");
        int yearOfBirth = checkIntNumber(0, currYear - 18, "Nhập năm sinh nhân viên: ");
        while(true) {
            try {
                System.out.print("Nhập ngày bắt đầu làm việc(dd-mm-yyyy): ");
                date = input.next();
                hireDate = df.parse(date);
                LocalDate localDate = Instant.ofEpochMilli(hireDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                if (localDate.getYear() >= (yearOfBirth + 18) && localDate.isBefore(today)) {
                    break;
                }
            }
            catch (Exception e) {
                input.next();
                continue;
            }
        }
        double leaveDays = checkDoubleNumber(5, "Nhập số ngày nghỉ phép của nhân viên (< 5 ngày): ");
        System.out.println("Chọn bộ phận làm việc của nhân viên: ");
        String departmentName = chooseDepartment("Add new department");
        if(departmentName.equals("0")) {
            generator.init("DPT", lastDepartmentId);
            departmentName = scannerString("Nhập bộ phận làm việc của nhân viên : ");
            Department department = new Department(generator.generate(10),departmentName,1);
            departmentList.add(department);
        } else {
            for (Department department : departmentList) {
                if (department.getDepartmentName().equals(departmentName)) {
                    department.setNumOfMembers();
                    break;
                }
            }
        }
        if (option == 1) {
            generator.init("EMP", lastEmployeeId);
            double coefSalary = checkDoubleNumber(0, 5, "Nhập hệ số lương của nhân viên (0-5): ");
            double overtimeHour = checkDoubleNumber(28, "Nhập số giờ làm thêm của nhân viên (< 28 giờ): ");
            Employee employee = new Employee(generator.generate(),
                                                nameStaff,
                                                currYear - yearOfBirth,
                                                coefSalary,
                                                hireDate,
                                                leaveDays,
                                                departmentName,
                                                overtimeHour);
            employee.calculateSalary();
            staffList.add(employee);
            employee.displayInformation();
        } else if (option == 2) {
            generator.init("MGT", lastManagerId);
            double coefSalary = checkDoubleNumber(6, 10, "Nhập hệ số lương của nhân viên (6-10): ");
            String[] titleList = {"Business Leader", "Project Leader", "Technical Leader"};
            int i = 0;
            for (String title : titleList) {
                System.out.format("Type %d - %s", ++i, title);
                System.out.println();
            }
            String titleName = titleList[checkIntNumber(0, titleList.length + 1, "Chọn chức danh cho nhân viên: ") - 1];
            Manager manager = new Manager(generator.generate(),
                                                nameStaff,
                                                currYear - yearOfBirth,
                                                coefSalary,
                                                hireDate,
                                                leaveDays,
                                                departmentName,
                                                titleName);
            manager.calculateSalary();
            staffList.add(manager);
            manager.displayInformation();
        }
        optionEndFunction("Tiếp tục thêm nhân viên");
    }
    // searching staff by name and method
    public static void searchStaff() {
        System.out.println("Type 1 - Searching by name");
        System.out.println("Type 2 - Searching by staff id");
        System.out.println("Type 3 - Quay trở lại bảng điều khiển");
        System.out.println("Type 0 - Thoát chương trình");

        int option = checkIntNumber(3, "Chọn kiểu tìm kiếm: ");
        if (option == 0) {
            optionFunction = 0;
            return;
        } else if (option == 3) {
            optionFunction = 1;
            return;
        } else if (option == 1) {
            String searchingName = scannerString("Nhập tên nhân viên cần tìm kiếm: ");
            int count = 0;
            for (Staff staff : staffList) {
                String referenceName = staff.getName().toLowerCase();
                if (referenceName.contains(searchingName.toLowerCase())) {
                    count++;
                    staff.displayInformation();
                } 
            }
            if (count == 0) {
                System.out.println("Không có nhân viên nào phù hợp");
            }
        } else {
            String searchingId = scannerString("Nhập mã nhân viên cần tìm kiếm (xxx000): ");
            int count = 0;
            for (Staff staff : staffList) {
                if (staff.getId().equalsIgnoreCase(searchingId)) {
                    count++;
                    staff.displayInformation();
                } 
            }
            if (count == 0) {
                System.out.println("Không có nhân viên nào phù hợp");
            }
        }
        optionEndFunction("Tiếp tục tìm kiếm nhân viên");
    }
    // display salayry of every staff in company with id, name and department
    public static void displaySalary() {
        System.out.println("             Bảng lương nhân viên toàn công ty                     ");
        String leftAlignFormat = "| %-8s | %-20s | %-10s | %-15s |%n";
        System.out.format("+----------+----------------------+------------+-----------------+%n");
        System.out.format("| ID Staff | Name                 | Salary     | Department      |%n");
        System.out.format("+----------+----------------------+------------+-----------------+%n");
        for(Staff staff: staffList) {
            System.out.format(leftAlignFormat, staff.getId(), staff.getName(), staff.getSalary(), staff.getDepartmentName());
        }
        optionEndFunction();
    }
    public static void executeOptional() {
        switch (optionFunction) {
            case 1: 
                System.out.println("Danh sách toàn bộ nhân viên công ty");
                displayStaff();
                break;
            case 2: 
                displayDepartment();
                break;
            case 3: 
                System.out.println("Hiển thị danh sách nhân viên theo bộ phận công ty");
                do {
                    String departmentName = chooseDepartment("Quay trở lại bảng điều khiển chương trình");
                    if(departmentName.equalsIgnoreCase("0")) {
                        break;
                    }
                    displayStaff(departmentName);
                } while (optionFunction != 0 && optionFunction != 1);
                break;
            case 4: 
                do {
                    addNewStaff();
                } while (optionFunction != 0 && optionFunction != 1);
                break;
            case 5: 
                do {
                    searchStaff();
                } while (optionFunction != 0 && optionFunction != 1);
                break;
            case 6: 
                displaySalary();
                break;
            case 7: 
                System.out.println("Danh sách toàn bộ nhân viên công ty theo mức lương tăng dần");
                Collections.sort(staffList, new SortBySalary());
                Collections.reverse(staffList);
                displayStaff();
                break;
            case 8: 
                System.out.println("Danh sách toàn bộ nhân viên công ty theo mức lương giảm dần");
                Collections.sort(staffList, new SortBySalary());
                displayStaff();
                break;
        }
    }
}
