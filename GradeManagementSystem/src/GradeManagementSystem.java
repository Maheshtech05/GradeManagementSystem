import java.util.ArrayList;
import java.util.Scanner;

public class GradeManagementSystem {

    static ArrayList<StudentGrade> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static String[] subjects = {
            "Mathematics", "Science", "English", "History", "Computer"
    };

    public static void main(String[] args) {

        loadSampleData();

        int choice;
        do {
            System.out.println("\n=== GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Calculate Averages");
            System.out.println("4. Find Top Performers");
            System.out.println("5. Generate Performance Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudentMarks();
                case 2 -> viewAllStudents();
                case 3 -> calculateAverages();
                case 4 -> findTopPerformers();
                case 5 -> ReportGenerator.generatePerformanceReport(students);
                case 6 -> System.out.println("Exiting System...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }


    static void addStudentMarks() {
        System.out.println("\n=== ADD STUDENT MARKS ===");
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        double[] marks = new double[5];

        for (int i = 0; i < 5; i++) {
            do {
                System.out.print(subjects[i] + ": ");
                marks[i] = sc.nextDouble();
                if (marks[i] < 0 || marks[i] > 100)
                    System.out.println("Marks must be between 0 and 100");
            } while (marks[i] < 0 || marks[i] > 100);
        }

        StudentGrade s = new StudentGrade(name, marks);
        s.average = GradeCalculator.calculateAverage(marks);
        s.grade = GradeCalculator.calculateGrade(s.average);
        s.isPass = GradeCalculator.checkPass(marks);

        students.add(s);
        System.out.println("Student added successfully!");
    }


    static void loadSampleData() {
        students.add(new StudentGrade("Mahesh Ayerathan", new double[]{92, 88, 90, 85, 94}));
        students.add(new StudentGrade("Priya Sharma", new double[]{86, 91, 89, 84, 88}));
        students.add(new StudentGrade("Arjun Kumar", new double[]{78, 82, 75, 80, 79}));
        students.add(new StudentGrade("Sana Khan", new double[]{69, 74, 71, 68, 73}));

        for (StudentGrade s : students) {
            s.average = GradeCalculator.calculateAverage(s.marks);
            s.grade = GradeCalculator.calculateGrade(s.average);
            s.isPass = GradeCalculator.checkPass(s.marks);
        }
    }

    static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");


        System.out.printf("%-20s", "Student Name");
        for (String sub : subjects) {
            System.out.printf("%-12s", sub);
        }
        System.out.println();

        System.out.println("--------------------------------------------------------------------------");


        for (StudentGrade s : students) {
            System.out.printf("%-20s", s.name);
            for (double m : s.marks) {
                System.out.printf("%-12.2f", m);
            }
            System.out.println();
        }
    }


    static void calculateAverages() {
        System.out.println("\n=== STUDENT AVERAGES ===");
        for (StudentGrade s : students) {
            System.out.printf("%-18s : Avg = %.2f | Grade = %c | %s%n",
                    s.name, s.average, s.grade,
                    s.isPass ? "PASS" : "FAIL");
        }
    }

    static void findTopPerformers() {
        students.sort((a, b) -> Double.compare(b.average, a.average));
        System.out.println("\n=== TOP PERFORMERS ===");
        for (StudentGrade s : students) {
            System.out.printf("%s - %.2f%n", s.name, s.average);
        }
    }
}
