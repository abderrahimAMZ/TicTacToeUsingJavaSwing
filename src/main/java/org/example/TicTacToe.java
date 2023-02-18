package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TicTacToe extends JFrame {
    private Map<String, String> gameStateData = new HashMap<>();
    private boolean stopGame = false;
    private JButton[] buttons = new JButton[9];


    public TicTacToe() {
        super("Tic Tac Toe");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLayout(new BorderLayout());
        initComponents();

    }

    private void initComponents() {
        final boolean[] isX = {true};
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.CYAN);
        JPanel statusReset = new JPanel();
        statusReset.setBackground(Color.GREEN);
        JLabel labelStatus = new JLabel("game is not started");
        JButton buttonReset = new JButton("Reset");
        statusReset.setLayout(new BorderLayout());
        statusReset.add(labelStatus, BorderLayout.WEST);
        statusReset.add(buttonReset, BorderLayout.EAST);

        add(gamePanel, BorderLayout.CENTER);
        add(statusReset, BorderLayout.SOUTH);

        gamePanel.setLayout(new GridLayout(3, 3, 2, 2));
        char[] Abc = {'A', 'B', 'C'};
        char[] fromOneToThree = {'1', '2', '3'};
        for (int i = 2,j=0; i >= 0; i--) {
            for (char alpha : Abc) {
                JButton button = new JButton(/*""+alpha+fromOneToThree[i]*/);
                button.setName("Button" + alpha + fromOneToThree[i]);
                gameStateData.put(button.getName()," ");
                buttons[j] = button;
                j++;
                button.addActionListener(e -> {
                    System.out.println(this.getTitle());
                    System.out.println(e.toString());
                    if (stopGame == false && gameStateData.get(button.getName())== " ") {
                        labelStatus.setText("game is on!");
                        if (isX[0]) {
                            button.setText("X");
                            isX[0] = false;
                            gameStateData.put(button.getName(), "X");
                        } else {
                            button.setText("O");
                            isX[0] = true;
                            gameStateData.put(button.getName(), "O");

                        }
                        gameLogic(labelStatus);
                    }
                });

                gamePanel.add(button);
            }
        }

        buttonReset.addActionListener(e -> {
            for (int i = 2; i >= 0; i--) {
                for (char alpha : Abc) {
                    gameStateData.computeIfPresent("Button" + alpha + fromOneToThree[i],(k,v)-> " ");

                }
                for(int j=0;j<9;j++){
                    buttons[j].setText(" ");
                }
                stopGame = false;

            }
        });
    }
    private void gameLogic(JLabel ourlabel) {
        if (gameStateData.get("ButtonA1") == gameStateData.get("ButtonB1") && gameStateData.get("ButtonB1") == gameStateData.get("ButtonC1") && gameStateData.get("ButtonB1") != " ") {
            whichPlayerIsWinning("ButtonA1",ourlabel);
        } else if (gameStateData.get("ButtonA2") == gameStateData.get("ButtonB2") && gameStateData.get("ButtonB2") == gameStateData.get("ButtonC2") && gameStateData.get("ButtonB2") != " ") {
            whichPlayerIsWinning("ButtonA2",ourlabel);
        } else if (gameStateData.get("ButtonA3") == gameStateData.get("ButtonB3") && gameStateData.get("ButtonB3") == gameStateData.get("ButtonC3") && gameStateData.get("ButtonB3") != " ") {

            whichPlayerIsWinning("ButtonA3",ourlabel);
        } else if (gameStateData.get("ButtonA1") == gameStateData.get("ButtonA2") && gameStateData.get("ButtonA2") == gameStateData.get("ButtonA3") && gameStateData.get("ButtonA1") != " ") {

            whichPlayerIsWinning("ButtonA1",ourlabel);
        } else if (gameStateData.get("ButtonB1") == gameStateData.get("ButtonB2") && gameStateData.get("ButtonB2") == gameStateData.get("ButtonB3") && gameStateData.get("ButtonB1") != " ") {

            whichPlayerIsWinning("ButtonB1",ourlabel);
        } else if (gameStateData.get("ButtonC1") == gameStateData.get("ButtonC2") && gameStateData.get("ButtonC2") == gameStateData.get("ButtonC3") && gameStateData.get("ButtonC1") != " ") {
            whichPlayerIsWinning("ButtonC1",ourlabel);
        } else if (gameStateData.get("ButtonC1") == gameStateData.get("ButtonB2") && gameStateData.get("ButtonB2") == gameStateData.get("ButtonA3") && gameStateData.get("ButtonC1") != " ") {

            whichPlayerIsWinning("ButtonC1",ourlabel);
        }
        else if (gameStateData.get("ButtonA1") == gameStateData.get("ButtonB2") && gameStateData.get("ButtonB2") == gameStateData.get("ButtonC3") && gameStateData.get("ButtonA1") != " "){

            whichPlayerIsWinning("ButtonA1",ourlabel);
        }
    }
    private void whichPlayerIsWinning(String Cell,JLabel ourlabel){
        ourlabel.setText(gameStateData.get(Cell)+ "Wins!");
        stopGame = true;

    }
}
