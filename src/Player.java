import java.awt.image.BufferedImage;

public class Player {
	private Card[] cards;
	private int playerNum;
	
	public Player(int num) {
		cards = new Card[3];
		playerNum = num;
	}
	
	public void changeCard(Card c, int idx) {
		cards[idx] = c;
	}
	
	public void emptyCard(int idx) {
		cards[idx] = null;
	}
	
	public boolean isEmpty(int idx) {
		return cards[idx] == null;
	}
	
	public BufferedImage getCardPic(int idx) {
		if(idx < 0 || idx > 2) return null;
		if(cards[idx].getCardImg(idx) == null) {
			System.out.println("this");
		}
		return cards[idx].getCardImg(idx);
	}
	
	public int getType(int idx) {
		return cards[idx].getType();
	}
	
	public String toString() {
		String result = "player num " + playerNum + ": ";
		for(int i = 0; i < cards.length; i++) {
			if(cards[i] == null) result = result + " null";
			else result = result + cards[i].toString() + ", ";
		}
		return result;
	}
}
