import java.io.*;
import java.util.*;
public class archive{
  public static void main(String[] args){
    int[][] arr1 = { { 1, 2 }, { 3, 4 } };
    int[][] arr2 = { { 1, 2 }, { 3, 4 } };

    String filepath = args[0];

    int counter = 0;
    int d = 0;
    try{
      Scanner scanner = new Scanner(new File(filepath));
      while(scanner.hasNextInt()){
        System.out.println(scanner.nextInt());

        counter++;
      }

    }
    catch(Exception e){System.out.println(e);}
  }


  public static int nearestpower(int n){

    int value = 1;
    while (value < n) {
      value = value << 1;
    }
    return value;
  }

  public static int[][] matrixMult(int mat1[][], int mat2[][]){
    int N = mat1.length;
    int[][] res = new int[N][N];
    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        res[i][j] = 0;
        for (int s = 0; s < N; s++){
          res[i][j] += mat1[i][s] * mat2[s][j];
        }
      }
    }
    return res;
  }
}

/**
for (int i = 0; i < N; i++){
  for(int j = 0; j < N; j++) {
    if(i < N/2){
      if (j < N/2){
        res[i][j] = one[i][j];
      }
      else {
        res[i][j] = three[i][j - N/2];
      }
    } else {
      if (j < N/2){
        res[i][j] = two[N/2 - i][j];
      }
      else{
        res[i][j] = four[i - N/2][j - N/2];
      }
    }
  }
}
**/
