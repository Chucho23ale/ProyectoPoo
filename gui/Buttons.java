package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.io.*;

public class Buttons extends JPanel implements ActionListener, Serializable{

    private JButton check;
    private JButton save;
    private JButton load;
    private Board board;
    private Timer timer;

    public JLabel timeLabel;

    public long lastTickTime;


    public Buttons(Board board) {
        this.board = board;

        timeLabel = new JLabel();

        setBounds(450, 10, 130, 500);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new FlowLayout());
        setBackground(new Color(250,250,250));

        check = new JButton ("Check");
        save = new JButton("Save");
        load = new JButton("Load");


        timeLabel.setFont(new Font("Tahoma", 0, 18));
        timeLabel.setForeground(new java.awt.Color(51, 51, 51));
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timeLabel.setText(String.format("%02d:%02d:%02d:%02d", 0, 0, 0, 0));
        timeLabel.setIconTextGap(0);


        check.setBackground(new java.awt.Color(255, 255, 255));
        check.setFont(new Font("Tahoma", 0, 12));
        check.addActionListener(this);

        save.setBackground(new java.awt.Color(255, 255, 255));
        save.setFont(new Font("Tahoma", 0, 12));
        save.addActionListener(this);

        load.setBackground(new java.awt.Color(255, 255, 255));
        load.setFont(new Font("Tahoma", 0, 12));
        load.addActionListener(this);


        add(check);
        add(save);
        add(load);
        add(timeLabel);

        timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long runningTime = System.currentTimeMillis() - lastTickTime;
                    Duration duration = Duration.ofMillis(runningTime);
                    long hours = duration.toHours();
                    duration = duration.minusHours(hours);
                    long minutes = duration.toMinutes();
                    duration = duration.minusMinutes(minutes);
                    long millis = duration.toMillis();
                    long seconds = millis / 1000;
                    millis -= (seconds * 1000);
                    timeLabel.setText(String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, millis/10));
                }
            });

        startTimer();

    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==check){
          if (board.check()){
            stopTimer();
          }
        }
        else if (e.getSource()==save){
          board.save();
        }
        else if(e.getSource()==load){
          board.load();
        }
    }

    public void startTimer() {
      if (!timer.isRunning()) {
          lastTickTime = System.currentTimeMillis();
          timer.start();
      }
    }

    public void stopTimer(){
      if(timer.isRunning())
        timer.stop();
    }

}
