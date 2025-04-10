import java.util.Scanner;

public class MatrixMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Matrix Multiplication Program\n");

        int rowA = readInt(scanner, "Enter the number of rows for Matrix A: ");
        int colA = readInt(scanner, "Enter the number of columns for Matrix A: ");
        int rowB = readInt(scanner, "Enter the number of rows for Matrix B: ");
        int colB = readInt(scanner, "Enter the number of columns for Matrix B: ");

        // Check matrix multiplication compatibility
        if (colA != rowB) {
            System.out.println(" Matrix multiplication not possible. Columns of A must equal rows of B.");
            return;
        }

        int[][] matrixA = new int[rowA][colA];
        int[][] matrixB = new int[rowB][colB];

        System.out.println("\nEnter values for Matrix A:");
        inputMatrix(scanner, matrixA);

        System.out.println("\nEnter values for Matrix B:");
        inputMatrix(scanner, matrixB);

        int[][] result = multiplyMatrix(matrixA, matrixB);

        System.out.println("\n Result of Matrix A x Matrix B:");
        displayMatrix(result);

        scanner.close();
    }

    // Method to read validated integer input
    private static int readInt(Scanner scanner, String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                return num;
            }
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // clear invalid input
        }
    }

    // Method to take matrix input from user
    private static void inputMatrix(Scanner scanner, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("Enter %d values for row %d (space-separated):%n", matrix[0].length, i + 1);
            for (int j = 0; j < matrix[0].length; j++) {
                while (!scanner.hasNextInt()) {
                    System.out.print("Enter a number: ");
                    scanner.next();
                }
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    // Method to multiply two matrices
    private static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    // Method to display a matrix
    private static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
    }
}
