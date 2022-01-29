public class Department {
    String departmentId;
    String departmentName;
    int numOfMembers;

    public Department(String departmentId, String departmentName, int numOfMembers) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.numOfMembers = numOfMembers;
    }

    public String toString() {
        return String.format("ID: %-8s",this.departmentId) 
                + String.format("Department Name: %-18s",this.departmentName)
                + String.format("Number of Staff: %-4d", this.numOfMembers);
    }

    public String getDepartmentName() {
        return this.departmentName;
    }
    public void setNumOfMembers() {
        this.numOfMembers++;
    }
}
