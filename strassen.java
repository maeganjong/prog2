import java.io.*;
import java.util.Random;
import java.lang.Math;

public class strassen{
    public static void main (String[] args){
        // defines the command-line arguments
        // args[0] - 0, args[1] - dimension, args[2] - input file
        int bool = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int times = Integer.parseInt(args[2]); // file path??

        long startTime = 0;
        if (bool == 1) {
            startTime = System.nanoTime(); // stores the current time
        }

        // insert magical code

        if (bool == 1) {
            System.out.println(sum);
            System.out.println("Time: " + (System.nanoTime() - startTime)/Math.pow(10,9) + "sec");
            startTime = System.nanoTime();
        }
    }

    public static int[][] strassen(int mat1[][], int mat2[][]){
        int N = mat1.length;
        int[][] res = new int[N][N];

        int[][] a = new int[N / 2][N / 2];
        int[][] b = new int[N / 2][N / 2];
        int[][] c = new int[N / 2][N / 2];
        int[][] d = new int[N / 2][N / 2];

        int[][] e = new int[N / 2][N / 2];
        int[][] f = new int[N / 2][N / 2];
        int[][] g = new int[N / 2][N / 2];
        int[][] h = new int[N / 2][N / 2];

        return res;
    }
    public static int[][] matrixMult(int mat1[][], int mat2[][]){
        int i, j, k;
        int N = mat1.length;
        int[][] res = new int[N][N];
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                res[i][j] = 0;
                for (k = 0; k < N; k++)
                    res[i][j] += mat1[i][k]
                                * mat2[k][j];
            }
        }

        return res;
    }
}
