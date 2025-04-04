import java.util.Scanner;
import java.util.Arrays;

public class StudentScoresAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = getNumberOfStudents(scanner);
        int[] scores = getScores(scanner, N);
        scanner.close();

        displayStatistics(scores);
        displayGraph(scores);
    }

    private static int getNumberOfStudents(Scanner scanner) {
        int N;
        while (true) {
            System.out.println("Enter the number of students: ");
            if (scanner.hasNextInt() && (N = scanner.nextInt()) > 0) {
                return N;
            }
            System.out.println("Invalid input! Please enter a positive number.");
            scanner.nextLine();
        }
    }

    private static int[] getScores(Scanner scanner, int N) {
        int[] scores = new int[N];
        System.out.println("Enter all grades (0-100): ");

        while (true) {
            boolean validInput = true;
            for (int i = 0; i < N; i++) {
                if (scanner.hasNextInt()) {
                    int grade = scanner.nextInt();
                    if (grade >= 0 && grade <= 100) {
                        scores[i] = grade;
                    } else {
                        validInput = false;
                        break;
                    }
                } else {
                    validInput = false;
                    break;
                }
            }
            if (validInput) break;
            System.out.println("Invalid input! Try again and ensure all grades are between 1-100.");
            scanner.nextLine();
        }
        return scores;
    }

    private static void displayStatistics(int[] scores) {
        int maxGrade = Arrays.stream(scores).max().orElse(0);
        int minGrade = Arrays.stream(scores).min().orElse(0);
        double averageGrade = Arrays.stream(scores).average().orElse(0.0);

        System.out.println("\nValues:");
        System.out.println("The maximum grade is " + maxGrade);
        System.out.println("The minimum grade is " + minGrade);
        System.out.printf("The average grade is %.2f\n", averageGrade);
    }

    private static void displayGraph(int[] scores) {
        int[] stats = new int[5];
        for (int grade : scores) {
            if (grade >= 0 && grade <= 20) stats[0]++; 
            else if (grade <= 40) stats[1]++;
            else if (grade <= 60) stats[2]++;
            else if (grade <= 80) stats[3]++;
            else stats[4]++;
        }

        int maxHeight = Arrays.stream(stats).max().orElse(0);
        System.out.println("\nGraph:\n");

        for (int row = maxHeight; row > 0; row--) {
            System.out.printf(" %2d > ", row);
            for (int count : stats) {
                System.out.print(count >= row ? "  #######  " : "           ");
            }
            System.out.println();
        }
        
        System.out.println("    +-----------+-----------+----------+----------+----------+");
        System.out.println("    I    0-20   I   21-40   I   41-60   I   61-80   I   81-100  I");

    }    
}