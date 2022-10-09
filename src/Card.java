import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Card {
	private BufferedImage c0, c1, c2, c3, c4, c5, c6, c7, c8, c9;
	private int type;
	private BufferedImage typePic;
	//private String action;
	
	public Card() {
		try {
			// character cards
			c0 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Spy.png"));
			c1 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Guard.png"));
			c2 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Priest.png"));
			c3 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Baron.png"));
			c4 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Handmaid.png"));
			c5 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Prince.png"));
			c6 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Chancellor.png"));
			c7 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_King.png"));
			c8 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Countess.png"));
			c9 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Princess.png"));
		}catch(Exception E) {
			System.out.println("Exception Error");
			//return;
		}
		
		type = -1;
	}
	
	public Card(int t) {
		type = t;
		//action = "new card";
		
		try {
			// character cards
			c0 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Spy.png"));
			c1 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Guard.png"));
			c2 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Priest.png"));
			c3 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Baron.png"));
			c4 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Handmaid.png"));
			c5 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Prince.png"));
			c6 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Chancellor.png"));
			c7 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_King.png"));
			c8 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Countess.png"));
			c9 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/characters/LLCard_Princess.png"));
		}catch(Exception E) {
			System.out.println("Exception Error");
			//return;
		}
		
		
		switch(type) {
		case 0: typePic = c0;
			break;
		case 1: typePic = c1;
			break;
		case 2: typePic = c2;
			break;
		case 3: typePic = c3;
			break;
		case 4: typePic = c4;
			break;
		case 5: typePic = c5;	
			break;
		case 6: typePic = c6;
			break;
		case 7: typePic = c7;
			break;
		case 8: typePic = c8;
			break;
		case 9: typePic = c9;
		}
	}
	
	public BufferedImage getCardImg(int idx) {
		if(idx < 0 || idx > 2) {
			return null;
		}
		return typePic;
	}
	
	public BufferedImage getCardImg() {
		return typePic;
	}
	
	public int getType() {
		return type;
	}
	
	public void changeCard(int idx) {
		type = idx;
		switch(type) {
		case 0: typePic = c0;
			if(typePic != null)System.out.println("type 0 nope");
		    //System.out.println("type 0");
			break;
		case 1: typePic = c1;
			System.out.println("type 1");
			break;
		case 2: typePic = c2;
			System.out.println("type 2");
			break;
		case 3: typePic = c3;
			System.out.println("type 3");
			break;
		case 4: typePic = c4;
			System.out.println("type 4");
			break;
		case 5: typePic = c5;
			System.out.println("type 5");	
			break;
		case 6: typePic = c6;
			System.out.println("type 6");
			break;
		case 7: typePic = c7;
			System.out.println("type 7");
			break;
		case 8: typePic = c8;
			System.out.println("type 8");
			break;
		case 9: typePic = c9;
			System.out.println("type 9");
		}
	}
	
	
	public String toString() {
		return "This card is type: " + type;
	}
	
	
}
