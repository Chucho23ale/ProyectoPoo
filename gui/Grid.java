package gui;

//import java.awt.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Component;
import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Grid extends JPanel implements Serializable {
  private JTextField[][] textGrid;
  public long lastTickedTime;




  public Grid(){

    setBounds(0, 10, 450, 500);
    setLayout(new GridLayout(9, 9, 5, 5));
    setOpaque(false);

  }


  public void createBoard(int[][] puzzle) {
      textGrid = new JTextField[9][9];

      for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
              textGrid[i][j] = new JTextField();
              if (0 < puzzle[i][j] && puzzle[i][j] <= 9) {
                textGrid[i][j].setEditable(false);
                textGrid[i][j].setText(""+puzzle[i][j]);
                textGrid[i][j].setBackground(new Color(255, 255, 255));
                textGrid[i][j].setFont(new Font("Tahoma", Font.BOLD, 24));

              }else{
                textGrid[i][j].setEditable(true);
                textGrid[i][j].setBackground(new Color(255,255,255));
                textGrid[i][j].setFont(new Font("Tahoma", 0, 24));

              }
              textGrid[i][j].setHorizontalAlignment(JTextField.CENTER);
              add(textGrid[i][j]);

          }
      }
      this.setVisible(true);
  }



  public int[][] getAns() {
      int ans[][] = new int[9][9];

      for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
              try {
                  ans[i][j] = Integer.parseInt(textGrid[i][j].getText());
              } catch (NumberFormatException e) {
                  ans[i][j] = 0;
              }
          }
      }

      return ans;
  }

  public void update(ArrayList<Integer> wanswers){
    boolean wrong = false;
    for (int i = 0; i < 9; i++){
      for(int j = 0; j < 9; j++){
        for (int k = 0; k < wanswers.size()/2; k++){
          if(i == wanswers.get(k*2) && j == wanswers.get(k*2+1)){
            textGrid[i][j].setBackground(new Color(255,200,200));
            wrong = true;
          }
        }
        if(!wrong)
            textGrid[i][j].setBackground(new Color(255,255,255));
          wrong = false;
      }
    }
  }

}
