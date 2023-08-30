import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//developing main class
public class Main {
  // Letters to mark horizontal coordinates of board
  public static ArrayList<String> letters = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

  // Recursively get user input
  public static int[] getCoords(Scanner sc, Board board) {
    System.out.print("Position of piece you want to move: ");
    String p1 = sc.nextLine();
    System.out.print("Position you want to move to: ");
    String p2 = sc.nextLine();
    if (!board.isValidMove(letters.indexOf(p1.substring(0,1)), 8-Integer.parseInt(p1.substring(1,2)), letters.indexOf(p2.substring(0,1)), 8-Integer.parseInt(p2.substring(1,2)))) {
      System.out.println("Invalid move.");
      return getCoords(sc, board);
    }
    int[] coords = {letters.indexOf(p1.substring(0,1)), 8-Integer.parseInt(p1.substring(1,2)), letters.indexOf(p2.substring(0,1)), 8-Integer.parseInt(p2.substring(1,2))};
    return coords;
  }
  
  public static void main(String[] args) {

    // Initialize board and scanner
		Board board = new Board();
    Scanner sc = new Scanner(System.in);
    
    while (true) {
      // Displaying the board
      board.display();
      System.out.println(board.getCurrentPlayer() + " to move.");

      // Getting user input and moving pieces
      int[] coords = getCoords(sc, board);
      board.move(coords[0], coords[1], coords[2], coords[3]);
      
      // check win and display the correct player wins
      if (board.checkWin()) {
        System.out.print("\033[H\033[2J");
        board.display();
        System.out.println(board.getCurrentPlayer() + " wins!");
        break;
      }

      // Switch player and clear console
      board.switchPlayer();
      System.out.print("\033[H\033[2J");

      
    }
	}
}