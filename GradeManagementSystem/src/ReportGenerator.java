import java.util.ArrayList;
import java.util.Comparator;

public class ReportGenerator {

    static String[] subjects = {
            "Mathematics", "Science", "English", "History", "Computer"
    };

    public static void generatePerformanceReport(ArrayList<StudentGrade> students) {

        System.out.println("\n=== PERFORMANCE REPORT ===");
        System.out.println("Total Students: " + students.size());

        System.out.println("\nSUBJECT AVERAGES:");
        for (int i = 0; i < 5; i++) {
            double sum = 0;
            for (StudentGrade s : students)
                sum += s.marks[i];
            System.out.printf("• %-12s: %.2f%n",
                    subjects[i], sum / students.size());
        }

        students.sort(Comparator.comparingDouble(s -> -s.average));

        System.out.println("\nTOP PERFORMERS:");
        int rank = 1;
        for (StudentGrade s : students) {
            s.rank = rank;
            System.out.printf("%d. %s - Average: %.2f%n",
                    rank++, s.name, s.average);
        }

        int[] gradeCount = new int[5];
        for (StudentGrade s : students) {
            switch (s.grade) {
                case 'A' -> gradeCount[0]++;
                case 'B' -> gradeCount[1]++;
                case 'C' -> gradeCount[2]++;
                case 'D' -> gradeCount[3]++;
                case 'F' -> gradeCount[4]++;
            }
        }

        System.out.println("\nGRADE DISTRIBUTION:");
        System.out.println("• A Grade: " + gradeCount[0]);
        System.out.println("• B Grade: " + gradeCount[1]);
        System.out.println("• C Grade: " + gradeCount[2]);
        System.out.println("• D Grade: " + gradeCount[3]);
        System.out.println("• F Grade: " + gradeCount[4]);
    }
}
