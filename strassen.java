import java.io.*;
import java.util.*;
import java.lang.Math;

public class strassen{
  final static int CUTOFF = 33;
  final static double P = 0.05;
public static void main (String[] args){
  // defines the command-line arguments
  // args[0] - 0, args[1] - dimension, args[2] - input file
  int bool = Integer.parseInt(args[0]);
  int dimension = Integer.parseInt(args[1]);
  String filepath = args[2];

  long startTime = 0;

  if (bool == 1) {
    startTime = System.nanoTime(); // stores the current time
  }

  if (bool == 2){
    printLength();
  } else {
    runStrassen(dimension, filepath);
  }

  if (bool == 1) {
    System.out.println("Time: " + (System.nanoTime() - startTime)/Math.pow(10,9) + "sec");
    startTime = System.nanoTime();
  }
}

private static void printLength(){
  int[][] graph = new int[1024][1024];
  Random rand = new Random();

  for (int i = 0; i < 1024; i++){
    for (int j = i; j < 1024; j++){
      if (i == j){
        graph[i][j] = 0;
      }
      else{
        if (rand.nextDouble() < P){
          graph[i][j] = 1;
          graph[j][i] = 1;
        }
        else {
          graph[i][j] = 0;
          graph[j][i] = 0;
        }
      }
    }
  }

  graph = strassen(strassen(graph, graph), graph);
  double value = 0;
  for(int i = 0; i < 1024; i++){
    value += graph[i][i];
  }
  System.out.println(value / 6);
  System.out.println("Expected: " + (178433024 * (P * P * P)));
}

private static void runStrassen(int dimension, String filepath) {
  int N = nearestpower(dimension);  // figure out what the nearest power is and then pad accordingly
  int[][] res = new int[N][N];
  int[][] mat1 = new int[N][N];
  int[][] mat2 = new int[N][N];

  int counter = 0;
  int row = 0;
  int d = 0;
  int row2 = 0;
  int d2 = 0;

  try{
    Scanner scanner = new Scanner(new File(filepath));
    while(scanner.hasNextInt()){

      if(counter < (dimension * dimension)){
        if (d >= dimension){
          row++;
          d = 0;
        }
        mat1[row][counter % dimension] = scanner.nextInt();
        d++;
      } else {
        if (d2 >= dimension){
          row2++;
          d2 = 0;
        }
        mat2[row2][(counter - (dimension * dimension)) % dimension] = scanner.nextInt();
        d2++;
      }
      counter++;
    }

  }
  catch(Exception e){System.out.println(e);}

// padding
  if(dimension != N){
    for (int i = 0; i < N; i++){
      if (i >= dimension){
        for (int j = 0; j < N; j++){
          mat1[i][j] = 0;
          mat2[i][j] = 0;
        }
      }
      for (int j = dimension; j < N; j++){
        mat1[i][j] = 0;
        mat2[i][j] = 0;
      }
    }
  }
  res = strassen(mat1, mat2);

  for(int i = 0; i < dimension; i++){
    System.out.println(res[i][i]);
  }
}

private static int[][] strassen(int mat1[][], int mat2[][]){
  int N = mat1.length;

  if (N < CUTOFF){
    return matrixMult(mat1, mat2);
  } else {
  int[][] res = new int[N][N];

  int[][] a = new int[N/2][N/2];
  int[][] b = new int[N/2][N/2];
  int[][] c = new int[N/2][N/2];
  int[][] d = new int[N/2][N/2];

  splitMatrix(mat1, a, 0, 0);
  splitMatrix(mat1, b, 0, N/2);
  splitMatrix(mat1, c, N/2, 0);
  splitMatrix(mat1, d, N/2, N/2);

  int[][] e = new int[N/2][N/2];
  int[][] f = new int[N/2][N/2];
  int[][] g = new int[N/2][N/2];
  int[][] h = new int[N/2][N/2];

  splitMatrix(mat2, e, 0, 0);
  splitMatrix(mat2, f, 0, N/2);
  splitMatrix(mat2, g, N/2, 0);
  splitMatrix(mat2, h, N/2, N/2);

  int[][] p1 = new int[N/2][N/2];
  int[][] p2 = new int[N/2][N/2];
  int[][] p3 = new int[N/2][N/2];
  int[][] p4 = new int[N/2][N/2];
  int[][] p5 = new int[N/2][N/2];
  int[][] p6 = new int[N/2][N/2];
  int[][] p7 = new int[N/2][N/2];

  p1 = strassen(a, subtractMatrix(f,h));
  p2 = strassen(addMatrix(a,b),h);
  p3 = strassen(addMatrix(c,d),e);
  p4 = strassen(d, subtractMatrix(g, e));
  p5 = strassen(addMatrix(a,d), addMatrix(e,h));
  p6 = strassen(subtractMatrix(b,d), addMatrix(g,h));
  p7 = strassen(subtractMatrix(a,c), addMatrix(e,f));

  int[][] m00 = new int[N/2][N/2];
  int[][] m01 = new int[N/2][N/2];
  int[][] m10 = new int[N/2][N/2];
  int[][] m11 = new int[N/2][N/2];

  m00 = addMatrix(subtractMatrix(addMatrix(p5, p4), p2), p6);
  m01 = addMatrix(p1, p2);
  m10 = addMatrix(p3, p4);
  m11 = subtractMatrix(subtractMatrix(addMatrix(p5, p1), p3), p7);

  joinMatrix(m00, res, 0, 0);
  joinMatrix(m01, res, 0, N/2);
  joinMatrix(m10, res, N/2, 0);
  joinMatrix(m11, res, N/2, N/2);

  return res;
  }
}

public static void joinMatrix(int sourcemat[][], int outputmat[][], int a, int b){
  int N = sourcemat.length;
  for (int a2 = a, i = 0; i < N; a2++, i++){
    for (int b2 = b, j = 0; j < N; b2++, j++){
        outputmat[a2][b2] = sourcemat[i][j];
    }
  }
}

public static void splitMatrix(int sourcemat[][], int outputmat[][], int a, int b){
  int N = outputmat.length;
  for (int a2 = a, i = 0; i < N; a2++, i++){
    for (int b2 = b, j = 0; j < N; b2++, j++){
        outputmat[i][j] = sourcemat[a2][b2];
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
  return res;
}

public static int[][] subtractMatrix(int mat1[][], int mat2[][]){
  int N = mat1.length;
  int[][] res = new int[N][N];
  for (int i = 0; i < N; i++){
    for (int j = 0; j < N; j++){
      res[i][j] = mat1[i][j] - mat2[i][j];
    }
  }
  return res;
}

public static int[][] matrixMult(int mat1[][], int mat2[][]){
  int N = mat1.length;
  int[][] res = new int[N][N];
  for (int i = 0; i < N; i++){
    for (int j = 0; j < N; j++){
      res[i][j] = 0;
      for (int s = 0; s < N; s++){
        res[i][j] += mat2[s][j] * mat1[i][s];
      }
    }
  }
  return res;
}

public static int nearestpower(int n){
  int value = 1;
  while (value < n) {
    value = value << 1;
  }
  return value;
}
}
