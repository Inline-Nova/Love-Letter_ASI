import java.awt.*;
import java.lang.Math;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class LLText {
	private String text;
	
	public LLText() {
		text = "";
	}
	
	public String getText(int event) {
		switch(event) {
		case -10: text = "Both your\'s and the other player\'s cards are equal, neither is out";
			break;
		case -9: text = "Other player is worth more, you are out";
			break;
		case -8: text = "Your card is worth more, other player is out";
			break;
		case -7: text = "You guessed incorrectly, the chosen player is not out";
			break;
		case -6: text = "You guessed correctly, the chosen player is out";
			break;
		case -5: text = "Both players are safe due to Handmaiden or out of the round so move onto the next player";
			break;
		case -4: text = "The player you chose is currently out, pick another person";
			break;
		case -3: text = "The player you chose is currently safe due to the Handmaiden";
			break;
		case -2: text = "If you have the King or Prince but have the Countess, you must choose the Countesss";
			break;
		case -1: text = "Current Player click the \"SEE\" button and draw a card";
			break;
		case 0: text = "At the end of the round, if you are the only player to have played a spy, you get 1 token";
			break;
		case 1: text = "Guess another player's card (not saying Guard). If you are correct, they are out of the round";
			break;
		case 2: text = "Look at another player's hand";
			break;
		case 3: text = "Choose another player to compare cards with, whoever has the lowest is out of the round";
			break;
		case 4: text = "Until your next turn, ignore all effects from other player's cards";
			break;
		case 5: text = "Choose any player (including yourself) to discard his or her hand and draw a new card";
			break;
		case 6: text = "Draw 2 cards, keep 1 and put the others on the bottom of the deck";
			break;
		case 7: text = "Trade hands with another player of your choice";
			break;
		case 8: text = "If you have this card and the King or Prince, you must discard this card";
			break;
		case 9: text = "You are out of the Round";
			break;
		}
		return text;
	}
	
}
