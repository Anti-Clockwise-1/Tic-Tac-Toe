import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayGame {

	public static void main(String[] args) {
		Scanner val = new Scanner(System.in);	
		boolean test = true;
		int pos;

		System.out.println("Welcome to Tic-Tac-Toe\n");
		System.out.println("Choose a dimension for your board:\n(From 3 to 10)");
		while(test) {
			try {
				pos = val.nextInt();
				while(pos > 10 || pos < 3) { // For Unusable Integer Values
				    System.out.println();
				    System.out.println("---Input a Valid Dimension---\n");
				    pos = val.nextInt();
				}
				test = false;
				ArrayPlayer.setDimension(pos);
			}
	
			catch (InputMismatchException e) { // For Everything Else
				System.out.println();
				System.out.println("---Input a Valid Dimension---\n");
                if (val.hasNext()) {
                    val.next(); // Consume the invalid token
                }
			}
		}
		
		ArrayPlayer p1 = new ArrayPlayer(1);
		ArrayPlayer p2 = new ArrayPlayer(2);
		
		boolean play = true;
		Scanner s; // Declaring Scanner
		String r; // Declaring String to be used as scanner input
		int round = 1; // Holds what round the game is on
		
		System.out.println("\nGame Start!\n");
		while(play) {
			ArrayPlayer.setBoard(); 
			ArrayPlayer.showBoard();
			while(ArrayPlayer.turns < (ArrayPlayer.dim * ArrayPlayer.dim) && !p1.checkWin() && !p2.checkWin()) {
				p1.nextMove();
				ArrayPlayer.showBoard();
				
				if(p1.checkWin()) {
					break;
				}
				
				p2.nextMove();
				ArrayPlayer.showBoard();
			}
			
			// Winners:
			if(p1.checkWin()) {
				System.out.println("Player 1 Won");
				p1.wins++;
			}
			else if (p2.checkWin()) {
				System.out.println("Player 2 Won");
				p2.wins++;
			}
			else {
				System.out.println("Tie");
			}
			

			System.out.println("------------------------------------");
			p1.printResults();
			p2.printResults();
			System.out.println("------------------------------------\n");
			
			p1.resetBoard();
			p2.resetBoard();
			
			System.out.println("Play Again? Y/N:");
			s = new Scanner(System.in);
			r = s.nextLine().toLowerCase();
			while(!r.equals("y") && !r.equals("n")) {
				System.out.println("\n---Please input a valid response---");
				r = s.nextLine().toLowerCase();
			}
			
			round++;
			if(r.equals("n")) {
				play = false;
			}
			else {
				System.out.println("_______________________________________________________________\n\nGame: " + round);
			}
		}
		System.out.println("___________________________________\n\nThank you for Playing.");
	// End of Main
		val.close();
		ArrayPlayer.scan.close();
	}

// End of Class
}