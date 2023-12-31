import java.util.*;

public class ArrayPlayer implements PlayerInterface{
	static int dim; // dimensions of board
	static String[][] bothMoves; // both player's moves, will be used to display board
	static Scanner scan;
	static int turns = 0; // total number of turns
	static int temp = 0; // used to determine if the way boards needs to be displayed changes (1-digit v 2-digit nums)
	
	boolean[][] moves = new boolean[dim][dim]; // individual player moves
	int wins = 0; // individual player wins
	int playerNum; // the player number, will be either 1 or 2
	
	public ArrayPlayer(int player) {
		playerNum = player;
	}

	public static void setDimension(int dimension) {
		dim = dimension;
		bothMoves = new String[dim][dim];
	}
	
	public static void setBoard() {
		temp = 0;
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				if(temp < 10 && dim > 3) {
					bothMoves[i][j] = "0" + temp + "";
				}
				else {
					bothMoves[i][j] = temp + "";
				}
				temp++;
			}
		}
	}

	public static void showBoard() {
		if(temp < 10) {
			for(int a = 0; a < dim; a++) {
				System.out.print("+---");
			}
			System.out.print("+\n");
			for(int i = 0; i < dim; i++) {
				System.out.print("| ");
			    for(int j = 0; j < dim; j++) {
			    	System.out.print(bothMoves[i][j]);
			        System.out.print(" | ");
			       }
		        System.out.print("\n");
				for(int a = 0; a < dim; a++) {
					System.out.print("+---");
				}
				System.out.print("+\n");
			}
		}
		else {
			for(int a = 0; a < dim; a++) {
				System.out.print("+----");
			}
			System.out.print("+\n");
			for(int i = 0; i < dim; i++) {
				System.out.print("| ");
			    for(int j = 0; j < dim; j++) {
			    	if(bothMoves[i][j] == "O" || bothMoves[i][j] == "X") {
			    		System.out.print(" " + bothMoves[i][j]);
			    	}
			    	else {
			    		System.out.print(bothMoves[i][j]);
			    	}
			        System.out.print(" | ");
			       }
		        System.out.print("\n");
				for(int a = 0; a < dim; a++) {
					System.out.print("+----");
				}
				System.out.print("+\n");
			}
		}
	}	

	public int getRow(int pos) {
		return pos / dim;
	}
	
	public int getCol(int pos) {
		return pos % dim;
	}
	
	public boolean check(int pos) {
		if(bothMoves[getRow(pos)][getCol(pos)] == "O" || bothMoves[getRow(pos)][getCol(pos)] == "X") {
			return true;
		}
		return false;
	}
	
	public boolean checkWin() {
		int max = dim;
		int temp = 0;
		int h = 0;
		int v = 0;
		
		// Checking Forward Diagonal values
		for(int z = 0; z < dim; z++) {
			if(moves[z][z]) {
				temp++;
				if(temp == max) {
					return true;
				}
			}
		}
		temp = 0;
		
		// Checking Backward Diagonal Values
		int z1 = 0;
		int z2 = dim-1;
		while(z2 >= 0 && z1 <= dim) {
			if(moves[z1][z2]) {
				temp++;
				if(temp == max) {
					return true;
				}
			}
			z1++;
			z2--;
		}
		temp = 0;
		
		while(h < dim && v < dim) {
			// Checking Horizontal
			for(int i = 0; i < dim; i++) {
				if(moves[h][i]) {
					temp++;
					if(temp == max) {
						return true;
					}
				}
			}
			temp = 0;
			
			// Checking Vertical Values
			for(int j = 0; j < dim; j++) {
				if(moves[j][v]) {
					temp++;
					if(temp == max) {
						return true;
					}
				}
			}
			temp = 0;
			
		h++;
		v++;
		}
		return false;
	}
	
	public void nextMove() {
		System.out.println("___________________________________\n");
		System.out.println("Player " + playerNum + " move: ");
		System.out.println("\tPick a Position: (0 - " + (dim * dim - 1) + ")");
		scan = new Scanner(System.in);	
		boolean test = true;
		int pos;

		while(test) {
			try {
				pos = scan.nextInt();
				while(pos < 0 || pos > (dim * dim - 1) || check(pos)) { // For Unusable Integer Values
				    System.out.println();
				    System.out.println("---Please choose a valid Position---\n");
				    showBoard();
				    pos = scan.nextInt();
				}
				int row = getRow(pos);
				int col = getCol(pos);
				if(playerNum == 1) {
					bothMoves[row][col] = "O";
				}
				else if(playerNum == 2){
					bothMoves[row][col] = "X";
				}
				moves[row][col] = true;
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
	
	public void resetBoard() {
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				moves[i][j] = false;
				bothMoves[i][j] = null;
			}
		}
		turns = 0;
	}

	public void printResults() {
		System.out.println("Player " + playerNum + " total wins: " + wins);
	}
}