//creating a new board
public class Board {
  private Piece[][] pieces = new Piece[8][8];
  private String currentPlayer;
  private String otherPlayer;

  public Board() {
    // Setting all pieces in correct locations
    pieces[0][0] = new Rook("Black");
    pieces[0][1] = new Knight("Black");
    pieces[0][2] = new Bishop("Black");
    pieces[0][3] = new Queen("Black");
    pieces[0][4] = new King("Black");
    pieces[0][5] = new Bishop("Black");
    pieces[0][6] = new Knight("Black");
    pieces[0][7] = new Rook("Black");
    pieces[1][0] = new Pawn("Black");
    pieces[1][1] = new Pawn("Black");
    pieces[1][2] = new Pawn("Black");
    pieces[1][3] = new Pawn("Black");
    pieces[1][4] = new Pawn("Black");
    pieces[1][5] = new Pawn("Black");
    pieces[1][6] = new Pawn("Black");
    pieces[1][7] = new Pawn("Black");
    pieces[7][0] = new Rook("White");
    pieces[7][1] = new Knight("White");
    pieces[7][2] = new Bishop("White");
    pieces[7][3] = new Queen("White");
    pieces[7][4] = new King("White");
    pieces[7][5] = new Bishop("White");
    pieces[7][6] = new Knight("White");
    pieces[7][7] = new Rook("White");
    pieces[6][0] = new Pawn("White");
    pieces[6][1] = new Pawn("White");
    pieces[6][2] = new Pawn("White");
    pieces[6][3] = new Pawn("White");
    pieces[6][4] = new Pawn("White");
    pieces[6][5] = new Pawn("White");
    pieces[6][6] = new Pawn("White");
    pieces[6][7] = new Pawn("White");

    // Setting white as the first player
    currentPlayer = "White";
    otherPlayer = "Black";
  }
  
  //Checks if a move is valid or not
  public boolean isValidMove(int x1, int y1, int x2, int y2) {
    // moves outside of the board or of pieces that aren't your own are not valid
    if (pieces[y1][x1] == null || pieces[y1][x1].getColor().equals(otherPlayer) || x2 < 0 || y2 < 0 || x2 > 7 || y2 > 7 || x1 == x2 && y1 == y2) {
      return false;
    }
    // Pawns can move two squares if they haven't moved yet, one square normally, and diagonally one square when capturing
    if (pieces[y1][x1].getName().equals("P")) {
      int dy = (currentPlayer.equals("White")) ? -1 : 1;
      if (y2 == y1 + dy) {
        if (x2 == x1 && pieces[y2][x2] == null) {
          return true;
        }
        else if (Math.abs(x2 - x1) == 1 && pieces[y2][x2] != null && pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
      else if (y2 == y1 + 2 * dy && y1 == (currentPlayer.equals("White") ? 6 : 1)) {
        if (x2 == x1 && pieces[y1 + dy][x2] == null && pieces[y2][x2] == null) {
          return true;
        }
      }
    }
    // The rook can move horizontally and vertically
    if (pieces[y1][x1].getName().equals("R")) {
      if (x1 == x2 || y1 == y2) {
        if (x1 == x2) {
          int minY = Math.min(y1, y2);
          int maxY = Math.max(y1, y2);
          for (int i = minY + 1; i < maxY; i++) {
            if (pieces[i][x1] != null) {
              return false;
            }
          }
        } else {
          int minX = Math.min(x1, x2);
          int maxX = Math.max(x1, x2);
          for (int i = minX + 1; i < maxX; i++) {
            if (pieces[y1][i] != null) {
              return false;
            }
          }
        }
        if (pieces[y2][x2] == null || pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
    }
    // The Knight can move in an L-shaped pattern
    if (pieces[y1][x1].getName().equals("N")) {
      int dx = Math.abs(x2 - x1);
      int dy = Math.abs(y2 - y1);
      if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
        if (pieces[y2][x2] == null || pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
    }
    // The bishop can move diagonally 
    if (pieces[y1][x1].getName().equals("B")) {
      if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
        int dx = Integer.signum(x2 - x1);
        int dy = Integer.signum(y2 - y1);
        int x = x1 + dx;
        int y = y1 + dy;
        while (x != x2 || y != y2) {
          if (pieces[y][x] != null) {
            return false;
          }
          x += dx;
          y += dy;
        }
        if (pieces[y2][x2] == null || pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
    }
    // The Queen can move any number of squares horizontally, vertically, or diagonally.
    if (pieces[y1][x1].getName().equals("Q")) {
      if (x1 == x2 || y1 == y2 || Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
        if (x1 == x2 || y1 == y2) {
          if (x1 == x2) {
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            for (int i = minY + 1; i < maxY; i++) {
              if (pieces[i][x1] != null) {
                return false;
              }
            }
          } else {
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            for (int i = minX + 1; i < maxX; i++) {
              if (pieces[y1][i] != null) {
                return false;
              }
            }
          }
        } else {
          int dx = Integer.signum(x2 - x1);
          int dy = Integer.signum(y2 - y1);
          int x = x1 + dx;
          int y = y1 + dy;
          while (x != x2 || y != y2) {
            if (pieces[y][x] != null) {
              return false;
            }
            x += dx;
            y += dy;
          }
        }
        if (pieces[y2][x2] == null || pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
    }
    // The king can move in any square adjacent to itself
    if (pieces[y1][x1].getName().equals("K")) {
      int dx = Math.abs(x2 - x1);
      int dy = Math.abs(y2 - y1);
      if (dx <= 1 && dy <= 1) {
        if (pieces[y2][x2] == null || pieces[y2][x2].getColor().equals(otherPlayer)) {
          return true;
        }
      }
    }  
    return false;
  }
  
  // Move the pieces and handle pawn promotion when it reaches the end of the board
  public void move(int x1, int y1, int x2, int y2) {
    if (isValidMove(x1, y1, x2, y2)) {
      pieces[y2][x2] = pieces[y1][x1];
      pieces[y1][x1] = null;
      if (pieces[y2][x2].getName().equals("P") && pieces[y2][x2].getColor().equals("White") && y2 == 0) {
        pieces[y2][x2] = new Queen("White");
      }
      if (pieces[y2][x2].getName().equals("P") && pieces[y2][x2].getColor().equals("Black") && y2 == 7) {
        pieces[y2][x2] = new Queen("Black");
      }
    }
  }
  
  // Displays the board with colors depending on the color of the pieces
  public void display() {
    System.out.println("  a  b  c  d  e  f  g  h  ");
    for (int i = 0; i < 8; i++) {
      System.out.print(8-i);
      for (int j = 0; j < 8; j++) {
        String txtColor;
        String name;
        
        if (pieces[i][j] != null) {
          name = pieces[i][j].getName();
          if (pieces[i][j].getColor().equals("Black")) {
            txtColor = "\u001B[31m";
          }
          else {
            txtColor = "\u001B[34m";
          }
        }
        else {
          name = " ";
          txtColor = "\u001B[37m";
        }

        System.out.print("[" + txtColor  + name + "\u001B[0m" + "]");
      }
      
      System.out.println(8-i);
    }
    
    System.out.println("  a  b  c  d  e  f  g  h  ");
  }
  
  // gets the current color
  public String getCurrentPlayer() {
    return currentPlayer;
  }
  
  // switches players between turns
  public void switchPlayer() {
    if (currentPlayer.equals("White")) {
      currentPlayer = "Black";
      otherPlayer = "White";
    }
    else {
      currentPlayer = "White";
      otherPlayer = "Black";
    }
  }
  
  // Checks if a player won
  public boolean checkWin() {
    int kingsCount = 0;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        Piece piece = pieces[y][x];
        if (piece != null && piece.getName().equals("K")) {
          kingsCount++;
        }
      }
    }
    return kingsCount != 2;
  }

}
