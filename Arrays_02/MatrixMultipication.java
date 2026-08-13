package Arrays_02;
import java.util.*;
public class MatrixMultipication {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Declare two input matrices and one result matrix
        int[][] A = new int[3][3];
        int[][] B = new int[3][3];
        int[][] C = new int[3][3];

        // Take input for Matrix A
        System.out.println("Enter elements of Matrix A (3x3):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // Take input for Matrix B
        System.out.println("Enter elements of Matrix B (3x3):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // Multiply Matrix A and Matrix B
        // C[i][j] = sum of A[i][k] * B[k][j]
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // Display the resulting matrix
        System.out.println("Resultant Matrix:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        // Close the Scanner
        sc.close();
    }
}