package com.chepuz.main;
import java.util.HashMap;

public class Logic {
  
  public static int[][] mirrorHorizontally(int[][] matrix) {
    int n = matrix.length; // Number of rows (assuming square matrix)
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n / 2; j++) {
            // Swap elements in the row to flip horizontally
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i][n - 1 - j];
            matrix[i][n - 1 - j] = temp;
        }
    }

    return matrix;
  }
  public static int[][] rotate90DegreesClockwise(int[][] matrix) {
    int n = matrix.length; // Assuming the matrix is square (n x n)
    int[][] rotated = new int[n][n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            rotated[j][n - 1 - i] = matrix[i][j];
        }
    }

    return rotated;
  }
  public static void convertToChessboard(int[][] colors) {
    System.out.println(colors);
    for(int col = 0; col<8; col++) {
      for(int row = 0; row<8; row++) {
        int p = colors[col][row];
        System.out.print(p+", ");
      }
      System.out.println();
    }
    
    String[][] letters = getPieces(mirrorHorizontally(rotate90DegreesClockwise(colors)));
    for(int col = 0; col<8; col++) {
      for(int row = 0; row<8; row++) {
        String p = letters[col][row];
        System.out.print(p+", ");
      }
      System.out.println();
    }
  }
  
  public static String[][] getPieces(int[][] colors) {
    // Create a HashMap to map integers to chess pieces
    HashMap<Integer, String> pieceMap = new HashMap<>();
    pieceMap.put(36, "p");        // Black pawn
    pieceMap.put(1036, "P");     // White pawn
    pieceMap.put(38, "n");       // Black knight
    pieceMap.put(1038, "N");     // White knight
    pieceMap.put(7040, "b");       // Black bishop
    pieceMap.put(8037, "B");     // White bishop
    pieceMap.put(39, "r");       // Black rook
    pieceMap.put(1039, "R");     // White rook
    pieceMap.put(31, "q");       // Black queen
    pieceMap.put(1031, "Q");     // White queen
    pieceMap.put(44, "k");       // Black king
    pieceMap.put(1044, "K");     // White king
    
    pieceMap.put(7000, ".");

    // Initialize a String array for the result
    String[][] temp = new String[8][8];

    // Iterate through the colors array
    for (int row = 0; row < colors.length; row++) {
        for (int col = 0; col < colors[row].length; col++) {
            int value = colors[row][col];

            // Determine whether the piece is white or black
            if (value != 0) {
                int closestKey = findClosestKey(pieceMap, value); // Find the closest piece key
                String piece = pieceMap.getOrDefault(closestKey, ".");
                temp[row][col] = piece; 
            } else {
                temp[row][col] = ".";
            }
        }
    }
    
    boolean b = isWhite();
    
    if(!b) { // is black
      temp = flipBoard(temp);
    }
    
    String fen = getFENNotation(temp,b ? "w" : "b");
    doCall(fen);
    return temp;
  }
  
  public static void doCall(String fen) {
    Stockfish s = new Stockfish();
    if (!s.startEngine()) {
      System.err.println("Failed to start Stockfish engine.");
      return;
    }
    String move = s.getBestMove(fen, 500);
    
    manageDisplay(move);
  }
  
  public static void manageDisplay(String move) {
    if(move==null) {
      return;
    }
    int[][] coordinatePlane = Main.po.translateNotation(move);
    Main.po.display(coordinatePlane, Main.isWhite);

  }
  
  public static String[][] flipBoard(String[][] a) {
    int n = a.length; // Assuming the board is square (8x8)
    String[][] t = new String[n][n];

    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            // Flip vertically and horizontally
            t[row][col] = a[n - 1 - row][n - 1 - col];
        }
    }

    return t;
  }
  public static boolean isWhite() {
    return Main.isWhite;
  }

  private static int findClosestKey(HashMap<Integer, String> pieceMap, int value) {
      int closestKey = -1;
      int smallestDifference = Integer.MAX_VALUE;
  
      for (int key : pieceMap.keySet()) {
          int difference = Math.abs(key - value);
          if (difference < smallestDifference) {
              smallestDifference = difference;
              closestKey = key;
          }
      }
  
      return closestKey;
  }
  
  
  public static String getFENNotation(String[][] board, String activeColor) {

    StringBuilder fen = new StringBuilder();


    // Iterate through rows (in correct order this time)
    for (int rank = 0; rank < 8; rank++) {
        int emptyCount = 0;
        for (int file = 0; file < 8; file++) {
            String piece = board[rank][file];
            if (piece.equals(".")) {
                emptyCount++;
            } else {
                if (emptyCount > 0) {
                    fen.append(emptyCount);
                    emptyCount = 0;
                }
                fen.append(piece);
            }
        }
        if (emptyCount > 0) {
            fen.append(emptyCount);
        }
        fen.append("/");
    }

    // Remove trailing slash
    fen.deleteCharAt(fen.length() - 1);

    // Add active color and castling rights (assuming no castling for now)
    fen.append(" ").append(activeColor);
    fen.append(" - -");
    fen.append(" 0");

    // Add fullmove number (assuming 1 for now)
    fen.append(" 1");
    System.out.println(fen.toString());
    return fen.toString();
  }
  
}
