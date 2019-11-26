/*
 * Author: Jesús Alejandro Morales Migueles
 * Date: 19/10/2019
 * Reads a file
 */
package logic;

import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class getWorkingGrid {
  public static int[][] grid;

  public static int [][] getGrid(){

    Random r = new Random ();//creats object to generate random number
    int ranNumber = 1 + r.nextInt (3);//generates random number
    String path= "grids/"+ranNumber+".txt";

    try {
      File f = new File(path);//opens the file with the path provided
      Scanner reader = new Scanner(f);//reads line by line
      grid = new int[9][9];
      while(reader.hasNextLine()) {
         for (int i=0; i<grid.length; i++) {
            String[] line = reader.nextLine().trim().split(" ");
            for (int j=0; j<line.length; j++) {
               grid[i][j] = Integer.parseInt(line[j]);
            }
         }
      }
      reader.close();


    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return grid;
  }
}
