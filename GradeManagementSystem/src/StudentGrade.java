import java.io.Serializable;

public class StudentGrade implements Serializable {
    String name;
    double[] marks;
    double average;
    char grade;
    boolean isPass;
    int rank;

    public StudentGrade(String name, double[] marks) {
        this.name = name;
        this.marks = marks;
    }
}
