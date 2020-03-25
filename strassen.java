import java.io.*;
import java.util.Random;
import java.lang.Math;

public class strassen{
const int CUTOFF = 32;
public static void main (String[] args){
  // defines the command-line arguments
  // args[0] - 0, args[1] - dimension, args[2] - input file
  int bool = Integer.parseInt(args[0]);
  int dimension = Integer.parseInt(args[1]);
  int N = nearestpower(dimension); // figure out what the nearest power is and then pad accordingly

  String filepath = args[2];
  FileInputStream instream = new FileInputStream(filepath);

  // Handle padding with powers of 2

  long startTime = 0;
  if (bool == 1) {
    startTime = System.nanoTime(); // stores the current time
  }

  // logic to run strassen

  if (bool == 1) {
    System.out.println(sum);
    System.out.println("Time: " + (System.nanoTime() - startTime)/Math.pow(10,9) + "sec");
    startTime = System.nanoTime();
  }
}

public static int[][] strassen(int mat1[][], int mat2[][]){
  int N = mat1.length;

  // implementing cut-off
  if (N <= CUTOFF){
    matrixMult(mat1[][], mat2[][]);
  }

  int[][] res = new int[N][N];

  // how to divide up matrix accordingly

  int[][] a = new int[N / 2][N / 2];
  int[][] b = new int[N / 2][N / 2];
  int[][] c = new int[N / 2][N / 2];
  int[][] d = new int[N / 2][N / 2];

  int[][] e = new int[N / 2][N / 2];
  int[][] f = new int[N / 2][N / 2];
  int[][] g = new int[N / 2][N / 2];
  int[][] h = new int[N / 2][N / 2];

  int[][] p1 = strassen(addMatrix(a,d), addMatrix(e,d));
  int[][] p2 = strassen(addMatrix(c,d),e);
  int[][] p3 = strassen(a, subtractMatrix(f, h));
  int[][] p4 = strassen(d, subtractMatrix(g, e));
  int[][] p5 = strassen(addMatrix(a,b), h);
  int[][] p6 = strassen(addMatrix(c,a), addMatrix(e,f));
  int[][] p7 = strassen(subtractMatrix(b, d), addMatrix(g, h));

  return res;
}

public static void splitMatrix(int sourcemat[][], int outputmat[][], int a, int b){
  for (int i = 0; i < (b-a); i++){
    for (int j = 0; j < (b-a); j++){
        outputmat[i]  
    }
  }
}

public static int[][] addMatrix(int mat1[][], int mat2[][]){
  int N = mat1.length;
  int[][] res = new int[N][N];
  for (int i = 0; i < N; i++){
    for (int j = 0; j < N; j++){
      res[i][j] = mat1[i][j] + mat2[i][j];
    }
  }
}

public static int[][] subtractMatrix(int mat1[][], int mat2[][]){
  int N = mat1.length;
  int[][] res = new int[N][N];
  for (int i = 0; i < N; i++){
    for (int j = 0; j < N; j++){
      res[i][j] = mat1[i][j] - mat2[i][j];
    }
  }
}

public static int[][] matrixMult(int mat1[][], int mat2[][]){
  int N = mat1.length;
  int[][] res = new int[N][N];
  for (int i = 0; i < N; i++){
    for (int j = 0; j < N; j++){
        res[i][j] = 0;
        for (int k = 0; k < N; k++){
            res[i][j] += mat1[i][k] * mat2[k][j];
        }
    }
  }
  return res;
}

public static int nearestpower(int n){
  // how to pad!
}
}
