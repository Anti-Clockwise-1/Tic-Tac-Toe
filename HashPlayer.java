import java.util.*;

public class HashPlayer implements PlayerInterface {
	// Valid is Static so All Instances: Player 1 and Player 2 Share the Variable
	static HashSet<Integer> used = new HashSet<Integer>();
	static HashMap<Integer, String> bothMoves = new HashMap<Integer, String>();
	static int turns = 0;
	
	int no; // Player Number
	HashSet<Integer> moves = new HashSet<Integer>();
	int wins = 0;
	
	// Player Number
	public HashPlayer(int z) {
		no = z;
	}
	
	//Start Board
	public void setBoard() {
        for(int i = 0; i < 9; i++) {
        	bothMoves.put(i, " ");
        }
	}
	
	// Display Game Board
	public void showBoard() {
        System.out.println("+---+---+---+");
        for(int i = 0; i <= 6; i+=3) {
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
		if(turns < 9) {		
			@SuppressWarnings("resource") // There is a Scanner resource leak as there is no scan.close()
			Scanner scan = new Scanner(System.in);
			System.out.println("___________________________________\n");
			System.out.println("Player " + no + " move: ");
			System.out.println("\tPick a Position: (0 - 8)");
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
					used.add(pos);
					if(no == 1) {
						bothMoves.put(pos, "O");
					}
					else {
						bothMoves.put(pos, "X");
					}
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
	}
	
	// Get Turns
	public Integer getTurns() {
		return turns;
	}
	
	// Display Results
	public void printResults() {
		System.out.println("Player " + no + " total wins: " + wins);
	}
		
	// End of Class
}