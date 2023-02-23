package org.example;

public class Main {
    public static void main(String[] args) {
        Runnable chil3ba = new Runnable() {
            @Override
            public void run() {
                new TicTacToe();
            }
        };
        chil3ba.run();
    }
}