import java.util.Comparator;

public class SortBySalary implements Comparator<Staff> {
    
    public int compare (Staff o2, Staff o1) {
        long distance = o1.getSalary() - o2.getSalary();
        return (int) distance;
    }
    
}
