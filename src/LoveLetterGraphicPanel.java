import java.awt.*;
import java.lang.Math;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LoveLetterGraphicPanel extends JPanel implements KeyListener, MouseListener{
	private BufferedImage bsc, b1, b2, head, ref, token, c9;
	
	private int key;
	private int players = 3;
	private int start;
	private int[] cards;
	private Player[] plys;
	private int idx;
	
	private boolean discarded;
	
	private boolean seeRef, addCard, initialCards, firstCard;
	private boolean see0, see1, see2;
	private int currPlayer;
	
	//start = 4 vars
	private int currPlay;
	private int startPlay;
	private boolean see;
	
	private LLText text;
	private boolean drawn;
	private boolean chosen;
	private int chosenCard;
	private boolean out0, out1, out2;
	private boolean done;
	private boolean reset;
	private boolean safe0, safe1, safe2;
	private int[][] played;
	private boolean countess;
	
	public LoveLetterGraphicPanel() {
		plys = new Player[players];
		// other inputs
		key = -1;
		addKeyListener(this);
		
		start = 0;
		addMouseListener(this);
		
		players = 3;
		
		cards = new int[21];
		CardShuffle c = new CardShuffle();
		cards = c.returnCards();
		
		currPlayer = 0; //starts off with player 1
		addCard = false;
		
		
		idx = 0;
		initialCards = false;
		firstCard = false;
		
		see0 = false;
		see1 = false;
		see2 = false;
		
		startPlay = -1;
		
		discarded = false;
		
		see = false;
		
		text = new LLText();
		drawn = false;
		
		chosen = false;;
		chosenCard = -1;
		
		done = false;
		reset = false;
		safe0 = false; safe1 = false; safe2 = false;
		
		played = new int[3][6];
		
		countess = false;
		
		try {
			bsc = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/BrokenSealCard.PNG"));
			b1 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLCardBack_1.png"));
			b2 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLCardBack_2.png"));
			head = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLHeader.jpg"));
			ref = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLReference.png"));
			token = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLToken.png"));
		}catch(Exception E) {
			System.out.println("Exception Error");
			return;
		}
	}
	
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {
		key = e. getKeyChar()-'0';
		System.out.println(key);
		repaint();
	}
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {	}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("loc is("+x+", "+y+")");
		//On first click, header pic disappears and game starts
		if(start == 0) {
			start = 1;
		}
		//when click on ref pic, gets bigger/smaller
		
		if(discarded && e.getButton() == e.BUTTON1) {
			if(x >= 20 && y >= 20 && x <= 90 && y <= 132 && !seeRef)
				seeRef = true;
			//g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-100, null);
			else if(x >= 20 && y >= 20 && x <= getWidth()/5 && y <= getHeight()-200 && seeRef) {
				seeRef = false;
			}
			else if(initialCards != true && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282) {
				//if(firstCard && ((currPlayer == 1) || (plys[currPlayer] != null))) currPlayer = (currPlayer+1)%3;
				addCard = true;
				System.out.println("addCard: " + addCard);
				System.out.println("currPlayer: " + currPlayer);
			}
		}
		if(!discarded && e.getButton() == e.BUTTON1) {
			if(x >= 20 && y >= 20 && x <= 90 && y <= 132)
				seeRef = true;
			else if(x >= 20 && y >= 20 && x <= getWidth()/5 && y <= getHeight()-200) {
				seeRef = false;
			}
			else if(initialCards != true && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282) {
				discarded = true;
			}
		}
		if(start == 3 && e.getButton() == e.BUTTON1) {
			if(x >= getWidth()/5+80 && y >= 50 && x <= getWidth()/5 + getWidth()/5+40 && y <= 90) {
				System.out.println(00);
				see0 = !see0;
				see1 = false;
				see2 = false;
			}	
			else if(x >= getWidth()/5*2+80 && y >= 50 && x <= getWidth()/5*2 + getWidth()/5+40 && y <= 90) {
				System.out.println(11);
				see0 = false;
				see1 = !see1;
				see2 = false;
				
			}
			else if(x >= getWidth()/5*3+80 && y >= 50 && x <= getWidth()/5*3 + getWidth()/5+40 && y <= 90) {
				System.out.println(22);
				see0 = false;
				see1 = false;
				see2 = !see2;
			}
			//getWidth()/5+80, 110, 60, 30
			if(x >= getWidth()/5+80 && y >= 110 && x <= getWidth()/5+140 && y <= 140) {
				see0 = false; see1 = false; see2 = false;
				start = 4;
			}	
				
		}
		
		if(start == 4 && e.getButton() == e.BUTTON1) {
			//g.fillRect(140 + getWidth()/5 + 40, 40, 100, 30);
			if(x >= 150 + getWidth()/5 + 40 && y>= 40 && x <= 150 + getWidth()/5 + 140 && y <=  70) {
				System.out.println("SEE");
				see = !see;
			}
			if(startPlay > -1 && plys[startPlay].isEmpty(1) && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282) {
				drawn = true;
			}
			if(!drawn && !chosen) {
				/*g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);*/
				if(x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20){
					if((startPlay > -1 && plys[startPlay].getType(0) == 5 || plys[startPlay].getType(0) == 7) && plys[startPlay].getType(1) == 8) {
						countess = true;
					}else {
						chosenCard = 0;
						chosen = true;
						System.out.println("Chosen: " + chosenCard);
					}
					
				}else if(x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20){
					if((startPlay > -1 && plys[startPlay].getType(0) == 5 || plys[startPlay].getType(0) == 7) && plys[startPlay].getType(1) == 8) {
						countess = true;
					}else {
						chosenCard = 1;
						chosen = true;
						System.out.println("Chosen: " + chosenCard);
					}
				}
			}
			
			if((see0 ||see1 ||see2) && startPlay > -1) {
				int play = -1;
				System.out.println("Start Play: " + startPlay);
				if(startPlay != -1 && chosenCard != -1) play = plys[startPlay].getType(chosenCard);
				switch(play) {
				case 0:
					done = true;
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					if(startPlay == 0) safe0 = true;
					else if(startPlay == 1) safe1 = true;
					else if(startPlay == 2) safe2 = true;
					done = true;
					break;
				case 5:
					break;
				case 6: 
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					if(startPlay == 0) out0 = true;
					else if(startPlay == 1) out1 = true;
					else if(startPlay == 2) out2 = true;
					done = true;
					break;
				}
			}
		}
		repaint();
	}
	
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LoveLetterGraphicPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new LoveLetterGraphicPanel());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	
	public void paint(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(0,0,getWidth(), getHeight());
		if(start == 0) {
			g.drawImage(head,  0, 0, getWidth(), getHeight(), null);
		}
		
		switch(key) {
			case 1: g.fillRect(0,0,getWidth(), getHeight());
				break;
		}
		
		
		
		if(start == 1) { //this stage changes the screen to right before card distrubution
			repaint();
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 130, getHeight()-175);
			start++;
		}else if(start == 2) { //card distribution
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			
			
			
			if(seeRef) {
				System.out.println("true");
				g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
			}else {
				g.drawImage(ref, 20, 20, 70, 112, null);
			}
			
			if(discarded) {
				g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
			}
			
			g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
			
			g.setColor(Color.yellow);
			g.drawString("Discarded", getWidth()-90, 150);
			g.setColor(Color.WHITE);
			g.drawString("New Cards", getWidth()-90, 300);
			g.drawString("Cards: " + (cards.length - idx), getWidth()-90, 315);
			
			if(!seeRef) {
				g.drawString("1. each person draws a card from the \"new", 20, 150);
				g.drawString("cards\" deck", 20, 165);
				g.drawString("2. each person clicks to view the card they ", 20, 185);
				g.drawString("start with before starting the game", 20, 200);
			}
			
			
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 130, getHeight()-175);
			
			//current player box
			g.setColor(Color.GRAY);
			g.fillRect(20 + getWidth()/5 + 20, 20,getWidth()-150-getWidth()/5, getHeight()- 230);
			
			//System.out.println("idx: " + idx);
			//System.out.println("idx val: " + cards[idx]);
			
			if(idx == 0 && discarded) {
				cards[idx] = -1;
				idx++;
			}
			
			if(see0) {
				g.drawImage(b1, getWidth()/4-10, getHeight()-150, 140, 225, null);
			}
			if(see1) {
				g.drawImage(b1, getWidth()/2-20, getHeight()-150, 140, 225, null);
			}
			if(see2) {
				g.drawImage(b1, getWidth()- (getWidth()/5) - 120, getHeight()-150, 140, 225, null);
			}
			
			if(addCard) {
				if(currPlayer == 0) {
					see0 = true;
					plys[0] = new Player(0);
					for(int j = 0; j < 3; j++) {
						if(j == 0) {
							plys[0].changeCard(new Card(cards[idx]), 0);
							
							cards[idx] = -1;
							idx++;
						}else {
							plys[0].emptyCard(j);
						}
					}
					System.out.println(0 + "!");
					firstCard = true;
					addCard = false;
					currPlayer++;
				}else if(currPlayer == 1) {
					see1 = true;
					plys[1] = new Player(1);
					for(int k = 0; k < 3; k++) {
						if(k == 0) {
							plys[1].changeCard(new Card(cards[idx]), 0);
							cards[idx] = -1;
							idx++;
						}else {
							plys[1].emptyCard(k);
						}
					}
					System.out.println(1 + "!");
					
					addCard = false;
					currPlayer++;
				}else if(currPlayer == 2) {
					see2 = true;
					plys[2] = new Player(2);
					for(int m = 0; m < 3; m++) {
						if(m == 0) {
							plys[2].changeCard(new Card(cards[idx]), 0);
							cards[idx] = -1;
							idx++;
						}else {
							plys[2].emptyCard(m);
						}
					}
					initialCards = true;
					System.out.println(2+ "!");
					addCard = false;
					start = 3;
					
					for(int i = 0; i < 3; i++) {
						//System.out.println(plys[i].toString());
					}
					for(int n = 0; n < cards.length; n++) {
						System.out.print(cards[n] + ", ");
					}
				}
			}
			//System.out.println("plys length: " + plys.length);
			
																		//System.out.println(start);
			if(start ==3) {
				see0 = false; see1 = false; see2 = false;
			}
		
			
			repaint();
		}else if(start == 3){ //looking at 
			
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			
			g.drawString("1. each person draws a card from the \"new", 20, 150);
			g.drawString("cards\" deck", 20, 165);
			g.drawString("2. each person clicks to view the card they ", 20, 185);
			g.drawString("start with before starting the game", 20, 200);
			
			if(seeRef) {
				System.out.println("true");
				g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
			}else {
				g.drawImage(ref, 20, 20, 70, 112, null);
			}
			
			g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
			g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
			
			g.setColor(Color.yellow);
			g.drawString("Discarded", getWidth()-90, 150);
			g.setColor(Color.WHITE);
			g.drawString("New Cards", getWidth()-90, 300);
			g.drawString("Cards: " + (cards.length - idx), getWidth()-90, 315);
			
			if(!seeRef) {
				g.drawString("1. each person draws a card from the \"new", 20, 150);
				g.drawString("cards\" deck", 20, 165);
				g.drawString("2. each person clicks to view the card they ", 20, 185);
				g.drawString("start with before starting the game", 20, 200);
			}
			
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 130, getHeight()-175);
			
			//player cards;
			g.drawImage(b1, getWidth()/4-10, getHeight()-150, 140, 225, null);
			g.drawImage(b1, getWidth()/2-20, getHeight()-150, 140, 225, null);
			g.drawImage(b1, getWidth()- (getWidth()/5) - 120, getHeight()-150, 140, 225, null);
			
			//current player box
			g.setColor(Color.GRAY);
			g.fillRect(20 + getWidth()/5 + 20, 20,getWidth()-150-getWidth()/5, getHeight()- 230);
			g.setColor(Color.black); //start button
			g.fillRect(getWidth()/5+80, 110, 60, 30);
			g.setColor(Color.white);
			g.drawString("Start", getWidth()/5+90 , 130);
			
			//to see initial cards
			g.setColor(Color.black);
			g.drawString("Click Buttons to see each player's initial card", getWidth()/5+80, 40);
			
			g.setColor(Color.WHITE);//buttons to see intial cards
			g.fillRect(getWidth()/5 + 80, 50, getWidth()/5-10, 40);
			g.fillRect(getWidth()/5*2 + 80, 50, getWidth()/5-10, 40);
			g.fillRect(getWidth()/5*3 + 80, 50, getWidth()/5-19, 40);

			g.setColor(Color.black);
			g.drawString("Player 1", getWidth()/5 + 90, 70);
			g.drawString("Player 2", getWidth()/5*2 + 90, 70);
			g.drawString("Player 3", getWidth()/5*3 + 90, 70);
			
			
			
			if(see0) {
				if(plys[0].getCardPic(0) == null) {
					System.out.println("nope");
				}
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5 + 80, 50, getWidth()/5-10, 40);
				g.setColor(Color.black);
				g.drawString("Player 1", getWidth()/5 + 90, 70);
				
				g.drawImage(plys[0].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}else if(see1) {
				if(plys[0].getCardPic(0) == null) {
					System.out.println("nope");
				}
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5*2 + 80, 50, getWidth()/5-10, 40);
				g.setColor(Color.black);
				g.drawString("Player 2", getWidth()/5*2 + 90, 70);
				
				g.drawImage(plys[1].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}else if(see2) {
				if(plys[0].getCardPic(0) == null) {
					System.out.println("nope");
				}
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5*3 + 80, 50, getWidth()/5-19, 40);
				g.setColor(Color.black);
				g.drawString("Player 3", getWidth()/5*3 + 90, 70);
				
				g.drawImage(plys[2].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}
			
			System.out.println("cards: " + (cards.length-idx));
			
		}else if(start == 4) {
		if(startPlay == -1)startPlay = (int)(Math.random()*3);
		//System.out.println();
		System.out.println("rand num start " + startPlay);
		
		int rand = 0;
		for(int i = 0; i < 10; i++) {
			rand = (int)(Math.random()*3);
			System.out.println("rand num " + rand);
		}
		//regualr graphics
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		
		g.drawString("1. each person draws a card from the \"new", 20, 150);
		g.drawString("cards\" deck", 20, 165);
		g.drawString("2. each person clicks to view the card they ", 20, 185);
		g.drawString("start with before starting the game", 20, 200);
		
		if(seeRef) {
			System.out.println("true");
			g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
		}else {
			g.drawImage(ref, 20, 20, 70, 112, null);
		}
		
		g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
		g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
		
		g.setColor(Color.yellow);
		g.drawString("Discarded", getWidth()-90, 150);
		g.setColor(Color.WHITE);
		g.drawString("New Cards", getWidth()-90	, 300);
		g.drawString("Cards: " + (cards.length - idx), getWidth()-90, 315);
		
		if(!seeRef) {
			g.drawString("1. each person draws a card from the \"new", 20, 150);
			g.drawString("cards\" deck", 20, 165);
			g.drawString("2. each person clicks to view the card they ", 20, 185);
			g.drawString("start with before starting the game", 20, 200);
		}
		
		g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
		g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
		g.drawString("Player 3", getWidth() - (getWidth()/5) - 120, getHeight()-175);
		
		//player cards;
		g.drawImage(b1, getWidth()/4-10, getHeight()-150, 140, 225, null);
		g.drawImage(b1, getWidth()/2-20, getHeight()-150, 140, 225, null);
		g.drawImage(b1, getWidth()- (getWidth()/5) - 120, getHeight()-150, 140, 225, null);
		
		System.out.println("Drawn: " + drawn);
		if(drawn && startPlay == 0) {
			see0 = true;
		}else if(drawn && startPlay == 1) {
			see1 = true;
		}else if(drawn && startPlay == 2) {
			see2 = true;
		}
		
		if(see0) {
			g.drawImage(b1, getWidth()/4+10, getHeight()-150, 140, 225, null);
		}else if(see1) {
			g.drawImage(b1, getWidth()/2, getHeight()-150, 140, 225, null);
		}else if(see2) {
			g.drawImage(b1, getWidth()- (getWidth()/5) - 100, getHeight()-150, 140, 225, null);
		}
		
		//current player box
		g.setColor(Color.GRAY);
		g.fillRect(20 + getWidth()/5 + 20, 20,getWidth()-150-getWidth()/5, getHeight()- 230);
		//end regular graphics
		
		g.setColor(Color.black);
		g.fillRect(20 + getWidth()/5 + 40, 40, 120, 30);
		//g.fillRect(20 + getWidth()/5 + 40, 40, 100, 30);
		g.fillRect(20 + getWidth()/5 + 100, 410 + getHeight()/20, getWidth()-300-getWidth()/5, 50);
		
		g.setColor(Color.white);
		g.drawString("Current Player: " + (startPlay + 1), getWidth()/5 + 70, 60);
		g.fillRect(20 + getWidth()/5 + 100, 90, getWidth()-300-getWidth()/5, 300 + getHeight()/20);
		if(see) {
			g.setColor(Color.yellow);
			g.fillRect(150 + getWidth()/5 + 40, 40, 100, 30);
		}else {
			g.fillRect(150 + getWidth()/5 + 40, 40, 100, 30);
		}
		
		g.setColor(Color.black); //placeholders
		g.drawString("SEE", getWidth()/5 + 200, 60);
		g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
		
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
		
		if(startPlay > -1 && chosen) {
			g.setColor(Color.red);
			if(chosenCard == 0) {
				g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			}else if(chosenCard == 1) {
				g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			}
			g.setColor(Color.white);
			if(startPlay > -1)System.out.println("Chosen Card: " + text.getText(plys[startPlay].getType(chosenCard)));
			if(startPlay > -1) g.drawString(text.getText(plys[startPlay].getType(chosenCard)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
			
			
			if(plys[startPlay].getType(chosenCard) == 1 || plys[startPlay].getType(chosenCard) == 0 || plys[startPlay].getType(chosenCard) == 3 || plys[startPlay].getType(chosenCard) == 7) {
				g.setColor(Color.gray);
				System.out.println("Chosen Card: " + plys[startPlay].getType(chosenCard));
				if(startPlay == 0) {
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);

				}else if(startPlay == 1) {
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
				}else if(startPlay == 2) {
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
					
				}
				g.setColor(Color.black);
				if(startPlay == 0) {
					g.drawString("Player 2", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 180);
					g.drawString("Player 3", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 230);
				}else if(startPlay == 1) {
					g.drawString("Player 1", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 130);
					g.drawString("Player 3", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 230);
				}else if(startPlay == 2) {
					g.drawString("Player 1", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 130);
					g.drawString("Player 2", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 180);
					
				}
			}
		}else if(countess){
			g.setColor(Color.white);
			g.drawString((text.getText(-2)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
		}else {
			g.setColor(Color.white);
			g.drawString((text.getText(-1)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
		}
		
		if(startPlay > -1 && see) {
			g.drawImage(plys[startPlay].getCardPic(0), 9 + getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			if(!(plys[startPlay].isEmpty(1))) {
				g.drawImage(plys[startPlay].getCardPic(1), 9 + getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);

			}
		}
		
		
		
		if(startPlay > -1 && drawn) {
			plys[startPlay].changeCard(new Card(cards[idx]), 1);
			idx++;
			drawn = false;
			repaint();
		}
	}
		
	}
	
	
	
}
