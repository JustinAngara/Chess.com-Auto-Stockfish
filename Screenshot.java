package com.chepuz.main;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/*
 * club is amazing
 * 
 * x vals:
 * {}
 * 
 * */
public class Screenshot {
  static int offsetX[] = {0,0,1,2,3,4,5,6};
  static int offsetY[] = offsetX;
    
  /**
     * Captures a screenshot of the specified area.
     *
     * @param x the X coordinate of the top-left corner.
     * @param y the Y coordinate of the top-left corner.
     * @param w the width of the area.
     * @param h the height of the area.
     * @return a BufferedImage of the captured area.
     * @throws AWTException if the Robot class encounters an error.
     */
    public static BufferedImage captureScreenshot(int x, int y, int w, int h) throws AWTException {
        // Define the area to capture
        Rectangle captureArea = new Rectangle(x, y, w, h);
        Robot robot = new Robot();

        // Capture and return the screenshot
        BufferedImage i = robot.createScreenCapture(captureArea);


        analyzeChessboard(i);

        return i;
    }


    public static BufferedImage analyzeChessboard(BufferedImage chessboard) {
      int boardSize = 807; // The chessboard size in pixels
      int numSquares = 8; // Number of squares along one dimension
      int squareSize = boardSize / numSquares; // Size of each square in pixels
      int[][] colors = new int[numSquares][numSquares]; // 2D array for colors
      
      System.out.println(squareSize);
      
      for (int row = 0; row < numSquares; row++) {
        for (int col = 0; col < numSquares; col++) {
            System.out.println("(" + row + ", " + col + ")");
            int mid = (squareSize / 2);
            int x = (row * squareSize) + mid + offsetX[row];
            int y = (col * squareSize) + mid + offsetY[col];
            System.out.println("Center X: " + x + ", Y: " + y);
            
            // Initialize counters
            int totalCount = 0;
            
            int midRGB = chessboard.getRGB(x, y+8);
            int red = (midRGB >> 16) & 0xFF;
            
            if(red >= 200 && red!=237) {
              totalCount+=1000;
            }
            
            
            
            // Loop through the pixels in the vertical range
            for (int i = -1 * mid + 7; i <= mid - 7; i+=2) {
                // Ensure we are within bounds
                if (y + i >= 0 && y + i < chessboard.getHeight()) {
                    int count = getCount(chessboard.getRGB(x, y + i));
                    totalCount += count; // Accumulate the count
//                    chessboard.setRGB(x, y + i, new Color(170, 170, 170).getRGB()); // Debug visualization
                    
                }
            }
            chessboard.setRGB(x, y -38, new Color(255,0,0).getRGB()); // Debug visualization
            
            if(getCount(chessboard.getRGB(x, y-5))==0 && getCount(chessboard.getRGB(x, y-38))!=0) {
              // bishop
              totalCount+=7000;
            }
            
            // Assign the total count to the colors array
            colors[row][col] = totalCount;
        }
    }
      
      
      //THIS WILL HANDLE LOGIC FFS DON'T FORGET
      Logic.convertToChessboard(colors);
      
      return chessboard; // Return the 2D array of colors
  }
  public static int getCount(int rgb) {
    
    int red = (rgb >> 16) & 0xFF;   // Red is in bits 16-23
    int green = (rgb >> 8) & 0xFF;  // Green is in bits 8-15
    int blue = rgb & 0xFF;
    int z = red+green+blue;
    if((red==237 && green == 214 && blue==176) ||
        (red==184 && green==135 && blue==98) ||
        (z<=50)) {
      return 0;
    }
    
    System.out.println(z);
    return 1;
  }
  
    
}
