import java.util.*;

public class Game {

	public static void main(String[] args) {
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		boolean play = true;
		Scanner s;
		String r;
		
		while(play) {
			System.out.println("Welcome to Tic-Tac-Toe\n------------------------------------\n");
			
			while(p1.getTurns() < 9 && !p1.checkWin() && !p2.checkWin()) {
				p1.nextMove();
				p2.nextMove();
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
			
			System.out.println("Play Again? Y/N: \n");
			s = new Scanner(System.in);
			r = s.nextLine().toLowerCase();
			while(!r.equals("y") && !r.equals("n")) {
				System.out.println("Please input a valid response");
				System.out.println(r);
				r = s.nextLine().toLowerCase();
			}
			
			if(r.equals("n")) {
				play = false;
				System.out.println();
			}
		}
		System.out.println("Thank you for Playing.");
	}

}