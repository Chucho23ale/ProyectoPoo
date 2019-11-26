package gui;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import logic.*;

public class Board extends JFrame {


    private Sudoku sudoku;
    private Buttons buttons;
    private Grid grid;


    public Board(Sudoku sudoku) {

        this.sudoku = sudoku;
        buttons = new Buttons(this);
        grid = new Grid();

        getContentPane().add(new ImagePanel("base.png"));

        initialize();


    }


    private void initialize() {
        int[][] puzzle = sudoku.getWorkingGrid();
        grid.createBoard(puzzle);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Sudoku");
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setMinimumSize(new Dimension(600, 550));
        setBackground(new Color(255,255,255));
        setLayout(null);
        setVisible(true);

        add(buttons);
        add(grid);





    }

    public boolean check(){
      int[][] answer = grid.getAns();
      ArrayList<Integer> wanswers = new ArrayList<>();
      wanswers = sudoku.check(answer);
      if(wanswers.isEmpty())
        return true;
      grid.update(wanswers);
      return false;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getTime(){
      return buttons.timeLabel.getText();
    }

    public void save(){
      Grid tempG = grid;

      grid.lastTickedTime = buttons.lastTickTime;


      try {
        FileOutputStream fileOut = new FileOutputStream("savedGrid.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tempG);
        out.close();
        fileOut.close();
      } catch (IOException i) {
        i.printStackTrace();
      }

    }

    public void load(){
      Grid newGrid = null;
      try {
        FileInputStream fileIn = new FileInputStream("savedGrid.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        newGrid = (Grid) in.readObject();
        in.close();
        fileIn.close();
      } catch (IOException i) {
        System.out.println("x");
        i.printStackTrace();
        return;
      } catch (ClassNotFoundException c) {
        showMessage("No saved games");
        c.printStackTrace();
        return;
      }

      buttons.lastTickTime = newGrid.lastTickedTime;

      remove(grid);
      remove(buttons);
      getContentPane().removeAll();
      add(newGrid);
      add(buttons);
      getContentPane().add(new ImagePanel("base.png"));
      revalidate();
      repaint();
  }

}
