//
import java.util.*;
public class Player {
	// Valid is Static so All Instances: Player 1 and Player 2 Share the Variable
	static HashSet<Integer> used = new HashSet<Integer>();
	static int turns = 0;
	int no;
	HashSet<Integer> moves = new HashSet<Integer>();
	int wins = 0;
	
	public Player(int z) {
		no = z;
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
		if(turns < 9) {		
			Scanner scan = new Scanner(System.in);
			System.out.println("Player " + no + " move: ");
			System.out.println("\tPick a Position: ");
			boolean test = true;
			int pos;
			
			while(test) {	
				try {
					pos = scan.nextInt();
					
					while(used.contains(pos) || pos < 0 || pos > 8) { // For Unusable Integer Values
					    System.out.println();
					    System.out.println("---Please choose a valid Position---");
					    System.out.println("\tPick a Position: ");
					    pos = scan.nextInt();
					}
					used.add(pos);
					moves.add(pos);
					turns++;
					test = false;
					System.out.println();
				}
		
				catch (InputMismatchException e) { // For Everything Else
					System.out.println();
					System.out.println("---Whole Numbers please---");
	                if (scan.hasNext()) {
	                    scan.next(); // Consume the invalid token
	                }
				}
			}
		}
	}
	
	// Get Turns
	public Integer getTurns() {
		return turns;
	}
	
	// Clear Board
	public void resetBoard() {
		used.clear();
		moves.clear();
		turns = 0;
	}
	
	public void printResults() {
		System.out.println("Player " + no + " total wins: " + wins);
	}
	
	public void showBoard() {
		
	}
	// End of Class
}