import java.util.*;

public class HashPlayer implements PlayerInterface {
	static HashSet<Integer> used = new HashSet<Integer>(); // Records moves that were played already
	static HashMap<Integer, String> bothMoves = new HashMap<Integer, String>(); // Records moves by both players
	static int turns = 0; // Count of total number of turns, shared by both players
	static Scanner scan;
	
	int no; // Player Number, necessary to differentiate between different Players and assign Symbols (X and O)
	HashSet<Integer> moves = new HashSet<Integer>(); // Records individual player's moves
	int wins = 0; // Records individual player's win
	
	// Player Number --> Take in the player number to be used
	public HashPlayer(int no) {
		this.no = no;
	}
	
	//Start Board
	public static void setBoard() {
        for(int i = 0; i < 9; i++) {
        	bothMoves.put(i, " ");
        }
	}
	
	// Display Game Board
	public static void showBoard() {
        System.out.println("+---+---+---+");
        for(int i = 0; i <= 6; i += 3) {
        	System.out.print("| ");
        	System.out.print(bothMoves.get(i));
        	System.out.print(" | ");
        	System.out.print(bothMoves.get(i+1));
        	System.out.print(" | ");
        	System.out.print(bothMoves.get(i+2));
        	System.out.print(" |");
        	System.out.println();
        }
        System.out.println("+---+---+---+\n");
	}
	
	// Clear Board
	public void resetBoard() {
		used.clear();
		moves.clear();
		bothMoves.clear();
		turns = 0;
	}
	
	// Check if Player has Won
	public boolean checkWin() {
		if(
			moves.contains(0) && moves.contains(1) && moves.contains(2) ||
			moves.contains(3) && moves.contains(4) && moves.contains(5) ||
			moves.contains(6) && moves.contains(7) && moves.contains(8) ||
			
			moves.contains(0) && moves.contains(4) && moves.contains(8) ||
			moves.contains(1) && moves.contains(4) && moves.contains(7) ||
			moves.contains(2) && moves.contains(4) && moves.contains(6) ||
			moves.contains(0) && moves.contains(3) && moves.contains(6) ||
			moves.contains(2) && moves.contains(5) && moves.contains(8)) {
				return true;
			}
		return false;
	}
	
	// Next Move
	public void nextMove() {
			System.out.println("___________________________________\n");
			System.out.println("Player " + no + " move: ");
			System.out.println("\tPick a Position: (0 - 8)");
			scan = new Scanner(System.in);	
			boolean test = true;
			int pos;

			while(test) {
				try {
					pos = scan.nextInt();
					while(used.contains(pos) || pos < 0 || pos > 8) { // For Unusable Integer Values
					    System.out.println();
					    System.out.println("---Please choose a valid Position---\n");
					    showBoard();
					    pos = scan.nextInt();
					}
					if(no == 1) {
						bothMoves.put(pos, "O");
					}
					else {
						bothMoves.put(pos, "X");
					}
					used.add(pos);
					moves.add(pos);
					turns++;
					test = false;
					System.out.println();
				}
		
				catch (InputMismatchException e) { // For Everything Else
					System.out.println();
					System.out.println("---Whole Numbers please---\n");
					showBoard();
	                if (scan.hasNext()) {
	                    scan.next(); // Consume the invalid token
	                }
				}
			}
		}
	
	// Display Results
	public void printResults() {
		System.out.println("Player " + no + " total wins: " + wins);
	}
		
	// End of Class
}