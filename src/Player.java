import java.awt.image.BufferedImage;

public class Player {
	private Card[] cards;
	private int playerNum;
	
	public Player(int num) {
		cards = new Card[2];
		playerNum = num;
	}
	
	public Player() {
		cards = new Card[2];
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
		/*if(cards[idx].getCardImg(idx) == null) {
			System.out.println("this");
		}*/
		return cards[idx].getCardImg(idx);
	}
	
	public BufferedImage getPlayerPic(int type) {
		if(type >-1 && type < 10) {
			Card tempCard = new Card(type);
			return tempCard.getCardImg();
		}
		return null;
	}
	
	public int getType(int idx) {
		if(cards[idx] == null) {
			return -1;
		}
		return cards[idx].getType();
	}
	
	public Card getCard(int idx) {
		return cards[idx];
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
