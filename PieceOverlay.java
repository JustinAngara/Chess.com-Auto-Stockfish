package com.chepuz.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PieceOverlay {

    private static JFrame frame;
    private static int x1;
    private static int x2;
    private static int y1;
    private static int y2;
    static JPanel panel, secondPanel;
    static Timer dT;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        x1 = 255;
        y1 = 153;
        x2 = 1257;
        y2 = 1114;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PieceOverlay window = new PieceOverlay();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public PieceOverlay() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    public static int[][] translateNotation(String s) {
        int[][] coordinates = new int[2][2]; // Array to store starting and ending squares

        // Extract starting and ending squares
        String[] squares = new String []{s.substring(0,2),s.substring(2,4)}; 

        if (squares.length != 2) {
            throw new IllegalArgumentException("Invalid notation format: " + s);
        }

        for (int i = 0; i < 2; i++) {
            String square = squares[i];
            // Convert file (letter) to index (0-based)
            coordinates[i][1] = square.charAt(0) - 'a'; 

            // Convert rank (number) to index (7-based)
            coordinates[i][0] = 7 - (square.charAt(1) - '1');
        }
        System.out.println(coordinates[0][0]+", "+coordinates[0][1]);
        return coordinates;
    }

    public static void display(int[][] s, boolean isWhite) {
      frame.setVisible(true);
      panel.setVisible(true);
      secondPanel.setVisible(true);

      // Flip coordinates if not white's perspective
      if (!isWhite) {
          s[0][0] = 7 - s[0][0]; // Flip row
          s[0][1] = 7 - s[0][1]; // Flip column
          s[1][0] = 7 - s[1][0]; // Flip row
          s[1][1] = 7 - s[1][1]; // Flip column
      }

      // Calculate pixel positions
      int xc1 = (s[0][1]) * 100;
      int yc1 = (s[0][0]) * 100;

      int xc2 = (s[1][1]) * 100;
      int yc2 = (s[1][0]) * 100;

      System.out.println("Starting Square: [" + s[0][0] + ", " + s[0][1] + "], x1: " + xc1 + ", y1: " + yc1);
      System.out.println("Ending Square: [" + s[1][0] + ", " + s[1][1] + "]");

      // Update panel positions
      panel.setBounds(xc1 - 20, yc1 + 20, 80, 80);
      secondPanel.setBounds(xc2 - 20, yc2 + 20, 80, 80);

      // Timer to hide the panels after a delay
      dT = new Timer(200, (ActionEvent e) -> {
          panel.setVisible(false);
          secondPanel.setVisible(false);

          panel.setBounds(-100, -100, 80, 80);
          secondPanel.setBounds(-100, -100, 80, 80);
          frame.setVisible(false);
          dT.stop();
      });

      dT.start();
  }

    
    public static void initialize() {
        frame = new JFrame();
        frame.setBounds(x1, y1, x2-x1, y2-y1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
        panel.setBounds(-2000, -2000, 158, 158);
        frame.getContentPane().add(panel);
        
        secondPanel = new JPanel();
        secondPanel.setBackground(new Color(0, 0, 0, 0));
        secondPanel.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
        secondPanel.setBounds(-2000, -2000, 158, 158);
        frame.getContentPane().add(secondPanel);
        
        
        // sets frame transparent
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setFocusable(false);
        
//      int[][] coordinatePlane = translateNotation("e2e4");
//      display(coordinatePlane);
        
    }
}