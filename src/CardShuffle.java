import java.lang.Math;

public class CardShuffle {
	private int[] unshuffledCards = {0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9};;
	private int[] cards = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	
	public CardShuffle() {
		
		int rand = 0;
		for(int i = 0; i < 21; i++) {
			rand = (int)(Math.random()*21);
			while(unshuffledCards[rand] == -1) {
				rand = (int)(Math.random()*21);
			}
			cards[i] = unshuffledCards[rand];
			unshuffledCards[rand] = -1;
		}
		
	}
	
	public int[] returnCards() {
		return cards;
	}
}
