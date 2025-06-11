
package com.chepuz.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GridDebugger extends JFrame {

    static int[] offsetX = {0, 1, 1, 2, 3, 4, 5, 7};
    static int[] offsetY = offsetX;

    static final int BOARD_SIZE = 775;
    static final int NUM_SQUARES = 8;
    static final int SQUARE_SIZE = BOARD_SIZE / NUM_SQUARES;

    public GridDebugger() {
        setTitle("Grid Debugger Chessboard Analyzer");
        setSize(BOARD_SIZE, BOARD_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setLocation(240, 187);
        setBackground(new Color(0, 0, 0, 0));

        BufferedImage chessboard = new BufferedImage(BOARD_SIZE, BOARD_SIZE, BufferedImage.TYPE_INT_ARGB);
        BufferedImage visualized = Screenshot.analyzeChessboard(chessboard);
        setContentPane(new JLabel(new ImageIcon(visualized)));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GridDebugger debugger = new GridDebugger();
            debugger.setVisible(true);
        });
    }
}
