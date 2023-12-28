import java.util.*;

public class HashGame {

	public static void main(String[] args) {
		HashPlayer p1 = new HashPlayer(1);
		HashPlayer p2 = new HashPlayer(2);
		HashPlayer sys = new HashPlayer(0);
		
		boolean play = true;
		Scanner s;
		String r;
		int round = 1;
		
		System.out.println("Welcome to Tic-Tac-Toe");
		while(play) {
			sys.setBoard(); 
			while(sys.getTurns() < 9 && !p1.checkWin() && !p2.checkWin()) {
				p1.nextMove();
				sys.showBoard();
				
				if(p1.checkWin()) {
					break;
				}
				
				p2.nextMove();
				sys.showBoard();
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
	}

// End of Class
}