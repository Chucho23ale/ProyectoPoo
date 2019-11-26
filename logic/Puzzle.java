package logic;

import java.util.Random;
import java.util.ArrayList;

public class Puzzle {
    public static final int MAX_FILAS = 9;
    public static final int MAX_COLUMNAS = 9;

    private int [][] workingGrid;
    private int [][] solvedGrid;

    public Puzzle () {
        workingGrid = new int [MAX_FILAS][MAX_COLUMNAS];
        solvedGrid = new int[MAX_FILAS][MAX_COLUMNAS];
        solvedGrid = getWorkingGrid.getGrid();

        for (int i = 0; i < MAX_FILAS; i++) {
            for (int j = 0; j < MAX_COLUMNAS; j++) {
                workingGrid[i][j] = 0;
            }
        }
        initRanNum();
    }

    public void initRanNum() {
        final Random r = new Random();
        final ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < MAX_FILAS; i++) {
            numbers.clear();
            for (int j = 0; j < 4; j++) {
                int ranNum;
                do {
                    ranNum = r.nextInt(9);
                } while (numbers.contains(ranNum));
                numbers.add(ranNum);
            }
            for (int k = 0; k < 4; k++) {
                final int column = numbers.get(k);
                workingGrid[i][column] = solvedGrid[i][column];
            }
        }
    }

    public int[][] getWorkingGrid() {
        return workingGrid;
    }

    public int[][] getSolvedGrid() {
        return solvedGrid;
    }

    public void updateGrid(final int[][] numbers) {
        workingGrid = numbers;
    }

    public ArrayList<Integer> check(){
        ArrayList<Integer> ar = new ArrayList<>();
        for(int i = 0; i < MAX_FILAS; i++){
            for(int j = 0; j < MAX_COLUMNAS; j++){
                if (workingGrid[i][j] != solvedGrid [i][j]){
                    ar.add(i);
                    //System.out.println("i="+i);
                    ar.add(j);
                    //System.out.println("j="+j);
                }
            }
        }
        return ar;
    }

}
