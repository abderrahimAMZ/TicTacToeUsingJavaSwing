package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class TicTacToe extends JFrame {
    private Map<String, String> gameStateData = new HashMap<>();
    private int clicks = 0;
    private boolean stopGame = false;
    private JButton[] buttons = new JButton[9];

    char[] Abc = {'A', 'B', 'C'};
    char[] fromOneToThree = {'1', '2', '3'};
    final boolean[] isX = {true};
    public TicTacToe() {
        super("Tic Tac Toe");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLayout(new BorderLayout());
        initComponents();

    }

    private void initComponents() {

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.decode("#00DBAA"));
        JPanel statusReset = new JPanel();
        statusReset.setBackground(Color.decode("#00DBAA"));
        JLabel labelStatus = new JLabel("Game is not started");
        labelStatus.setName("LabelStatus");
        JButton ButtonReset = new JButton("Reset");
        ButtonReset.setBackground(Color.lightGray);
        ButtonReset.setName("ButtonReset");
        statusReset.setLayout(new BorderLayout());
        statusReset.add(labelStatus, BorderLayout.WEST);
        statusReset.add(ButtonReset, BorderLayout.EAST);

        add(gamePanel, BorderLayout.CENTER);
        add(statusReset, BorderLayout.SOUTH);
        gamePanel.setLayout(new GridLayout(3, 3, 2, 2));
        for (int i = 2,j=0; i >= 0; i--) {
            for (char alpha : Abc) {
                JButton button = new JButton(" ");
                button.setName("Button" + alpha + fromOneToThree[i]);
                button.setBackground(Color.decode("#FAC020"));
                gameStateData.put(button.getName()," ");
                buttons[j] = button;
                j++;
                button.addActionListener(e -> {
                    if (stopGame == false && gameStateData.get(button.getName())== " ") {
                        clicks++;
                        labelStatus.setText("Game in progress");
                        if (isX[0]) {
                            button.setText("X");
                            button.setFont(new Font("Arial",Font.PLAIN,50));
                            isX[0] = false;
                            gameStateData.put(button.getName(), "X");
                        } else {
                            button.setText("O");
                            isX[0] = true;
                            gameStateData.put(button.getName(), "O");
                            button.setFont(new Font("Arial",Font.PLAIN,50));
                        }
                        gameLogic(labelStatus);
                        if (clicks == 9 && stopGame == false){
                            labelStatus.setText("Draw");
                        }
                    }
                });

                gamePanel.add(button);
            }
        }

        ButtonReset.addActionListener(e -> {
            resetGame(labelStatus);
        });
    }
    private void resetGame(JLabel ourlabel){
        for (int i = 2; i >= 0; i--) {
            for (char alpha : Abc) {
                gameStateData.computeIfPresent("Button" + alpha + fromOneToThree[i],(k,v)-> " ");

            }
            for(int j=0;j<9;j++){
                buttons[j].setText(" ");
            }
            ourlabel.setText("Game is not started");
            stopGame = false;
            isX[0] = true;
            clicks = 0;
        }
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
        ourlabel.setText(gameStateData.get(Cell)+ " wins");
        stopGame = true;

    }
}
