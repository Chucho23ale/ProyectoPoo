package logic;

import java.util.ArrayList;
import gui.*;

public class Sudoku {
  private Puzzle puzzle;
  private Board board;

  public Sudoku(){
    puzzle = new Puzzle();
    board = new Board(this);
  }


  public int[][] getWorkingGrid(){
    return puzzle.getWorkingGrid();
  }


  public ArrayList<Integer> check(final int[][] numbers) {
      String messageStr = "";
      ArrayList<Integer> ar = new ArrayList<>();

      puzzle.updateGrid(numbers);
      ar = puzzle.check();
      if (ar.isEmpty()) {
          messageStr = "Congratulation You have won the Game in " + board.getTime();
          board.showMessage(messageStr);
          return ar;

      } else {
          messageStr = "Sorry You have failed. ";
          board.showMessage(messageStr);
          return ar;
      }
  }

}
