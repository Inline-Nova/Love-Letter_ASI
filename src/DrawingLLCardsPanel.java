import java.awt.*;
import java.lang.Math;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DrawingLLCardsPanel extends JPanel implements MouseListener{
	private BufferedImage b1, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9;
	
	private int idx0, idx1, idx2, idx3, idx4, idx5, idx6, idx7, idx8, idx9 = 0;
	
	private boolean newCard, start, distributed, picked1, picked2, picked3;
	
	private int[] cards;
	private int cardIdx;
	
	//for the simulation
	private int ply0, ply1, drawn;
	private boolean changePly0, changeDrawn, changePly1, background, change, run;
	private Card chosenPly0, chosenDrawn, chosenPly1, pickedCard;
	private int prev0, prevD, prev1;
	private boolean changePly0Min, changeDrawnMin, changePly1Min;
	private String print;
	private boolean printed;
	
	DrawingLLCardsPanel() {
		cards = new int[21]; //instantiates the var and uses CardShuffle class to shuffle cards in deck
		CardShuffle c = new CardShuffle();
		cards = c.returnCards();
		System.out.print("cards: ");
		for(int i = 0; i < cards.length; i++) {
			System.out.print(cards[i] + ", ");
		}
		System.out.println();
		
		newCard = false;
		start = false;
		distributed = false;
		
		cardIdx = 0;
		
		background = false;
		
		print = "";
		printed = false;
		
		//instantiate sim variables
		ply0 = -1; ply1 = -1; drawn = -1; //the values for each part of the sim as chosen by the user
		prev0 = -1;
		prevD = -1;
		prev1 = -1;
		changePly0 = false; changeDrawn = false; changePly1 = false; //whether or not current part is being changeDrawn
		changePly0Min = false; changeDrawnMin = false; changePly1Min = false;
		chosenPly0 = new Card();
		chosenDrawn = new Card();
		chosenPly1 = new Card();
		pickedCard = new Card();
		
		run = false;
		
		try {
			b1 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLCardBack_1.png"));
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
			return;
		}
		addMouseListener(this);
	}
	
	
	
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//System.out.println("loc is("+x+", "+y+")");
		if(e.getButton() == e.BUTTON1) {
			if(x >= 20 && y >= 20 && x <= 145 && y <= 220) { //when revealing each card
				System.out.println("clicked");
				newCard = true;
			}
			
			if(distributed && x >= getWidth()-380 && y >= 430 && x<= getWidth()-150 && y <= 470) { //buttons for player to change sim cards
				System.out.println("change ply0");
				changePly0 = true;
				changeDrawn = false;
				changePly1 = false;
			}
			else if(distributed && x >= getWidth()-380 && y >= 500 && x<= getWidth()-150 && y <= 540) {
				System.out.println("change ply1");
				changePly0 = false;
				changeDrawn = true;
				changePly1 = false;
			}
			else if(distributed && x >= getWidth()-380 && y >= 570 && x<= getWidth()-150 && y <= 610) {
				System.out.println("change drawn");
				changePly0 = false;
				changeDrawn = false;
				changePly1 = true;
			}
			
			//areas player can click to pick certain cards
			if(distributed && (changePly0 || changeDrawn || changePly1)) {
				
				
				/*if(changePly0) {
					prev = chosenPly0.getType();
				}else if(changeDrawn) {
					prev = chosenDrawn.getType();
				}else if(changePly1) {
					prev = chosenPly1.getType();
				}*/
				
				
					if(x >=165 && y >= 20 && x <= 290 && y <= 220) {
						System.out.println(0);
						if(idx0 > 0) {
							if(changePly0 && prev0 !=0) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(0);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=0) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(0);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=0) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(0);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						
						change();
					}else if(x >= 310 && y >= 20 && x <= 435 && y <= 220) {
						System.out.println(1);
						if(idx1 > 0) {
							if(changePly0 && prev0 !=1) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(1);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=1) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(1);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=1) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(1);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 455 && y >= 20 && x <= 580 && y <= 220) {
						if(idx2 > 0) {
							if(changePly0 && prev0 !=2) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(2);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=2) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(2);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=2) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(2);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 600 && y >= 20 && x <= 725 && y <= 220) {
						
						if(idx3 > 0) {
							if(changePly0 && prev0 !=3) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(3);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=3) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(3);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=3) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(3);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 745 && y >= 20 && x <= 870 && y <= 220) {
						if(idx4 > 0) {
							if(changePly0 && prev0 !=4) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(4);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=4) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(4);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=4) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(4);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 890 && y >= 20 && x <= 1015 && y <= 220) {
						if(idx5 > 0) {
							if(changePly0 && prev0 !=5) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(5);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=5) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(5);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=5) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(5);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 1035 && y >= 20 && x <= 1160 && y <= 220) {
						if(idx6 > 0) {
							if(changePly0 && prev0 !=6) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(6);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=6) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(6);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=6) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(6);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 1180 && y >= 20 && x <= 1305 && y <= 220) {
						if(idx7 > 0) {
							if(changePly0 && prev0 !=7) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(7);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=7) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(7);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=7) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(7);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 1325 && y >= 20 && x <= 1450 && y <= 220) {
						if(idx8 > 0) {
							if(changePly0 && prev0 !=8) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(8);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=8) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(8);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=8) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(8);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
					}else if(x >= 1470 && y >= 20 && x <= 1595 && y <= 220) {
						if(idx9 > 0) {
							if(changePly0 && prev0 !=9) {
								prev0 = chosenPly0.getType();
								chosenPly0.changeCard(9);
								if(prev0 == -1) changePly0Min = true;
							}else if(changeDrawn && prevD !=9) {
								prevD = chosenDrawn.getType();
								chosenDrawn.changeCard(9);
								if(prevD == -1) changeDrawnMin = true;
							}else if(changePly1 && prev1 !=9) {
								prev1 = chosenPly1.getType();
								chosenPly1.changeCard(9);
								if(prev1 == -1) changePly1Min = true;
							}
						}else {
							System.out.println("error0");
							changePly0 = false; changeDrawn = false; changePly1 = false;
						}
						change();
						//changePly0 = false; changeDrawn = false; changePly1 = false;
					}
					
					
					
					
					
			}
			
			//red button
			if(x>= 150+getWidth()-560+30 && y>= 420 + getHeight()-500 && x <=150+getWidth()-560+30 + (getWidth()-560+30)-150 && y <= 420 + getHeight()-500+50) {
				System.out.println(100);
				changePly0 = false; changeDrawn = false; changePly1 = false;
				run = !run;
			}
			
			
			if (run && x>=190 && y>= 460 && x<= 190 + getWidth()/7 && y <=440 + getHeight()-530) {
				System.out.println("hi");
				pickedCard = chosenPly0;
				run();
			}
			if (run && x>= 280 + getWidth()/7 - 50 && y>= 470 && x<= 280 + getWidth()/7 - 50 + getWidth()/7 && y <= 470+getHeight()-530) {
				//280 + getWidth()/7 - 50, 440, getWidth()/7, getHeight()-530
				//280 + getWidth()/7 - 50, 470, getWidth()/7, getHeight()-530
				System.out.println("hello");
				pickedCard = chosenDrawn;
				run();
			}
	}
		repaint();
		
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LoveLetterGraphicPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new DrawingLLCardsPanel());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public void paint(Graphics g) {
		// 70 112
		
		if(run) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.black);
			//g.fillRect(150 + getWidth()-560 + 30, 400, getWidth() - (150 + getWidth()-560 + 30) - 150, getHeight()-530);
			g.fillRect(160,  390, getWidth()-560, 30);
			g.setColor(Color.white);
			g.drawString(print, 180, 410);
			g.setColor(Color.black);
			g.drawString("Now click on the \"Change ____\"", 180+getWidth()-560, 640);
			g.drawString("buttons to change which card takes", 180+getWidth()-560, 655);
			g.drawString("each place of the simulation", 180+getWidth()-560, 670);
			g.drawString("(By then clicking on the top of the preferred card", 180+getWidth()-560, 685);
			g.drawString("pile). A \"run simulation\" should pop up", 180+getWidth()-560, 700);
			g.drawString("once every place is filled with a card", 180+getWidth()-560, 715);
			g.drawString("If, you then tap one of player one's", 180+getWidth()-560, 730);
			g.drawString("cards (drawn or \"player 1\"), you", 180+getWidth()-560, 745);
			g.drawString("will see what will occur when that card", 180+getWidth()-560, 760);
			g.drawString("is played", 180+getWidth()-560, 775);
		}else {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.black);
			g.drawString("Now click on the \"Change ____\"", 180+getWidth()-560, 640);
			g.drawString("buttons to change which card takes", 180+getWidth()-560, 655);
			g.drawString("each place of the simulation", 180+getWidth()-560, 670);
			g.drawString("(By then clicking on the top of the preferred card", 180+getWidth()-560, 685);
			g.drawString("pile). A \"run simulation\" should pop up", 180+getWidth()-560, 700);
			g.drawString("once every place is filled with a card", 180+getWidth()-560, 715);
			g.drawString("If, you then tap one of player one's", 180+getWidth()-560, 730);
			g.drawString("cards (drawn or \"player 1\"), you", 180+getWidth()-560, 745);
			g.drawString("will see what will occur when that card", 180+getWidth()-560, 760);
			g.drawString("is played", 180+getWidth()-560, 775);
		}
		if(!distributed) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.black);
			g.drawImage(b1, 20,20, 125, 200, null);
			g.drawString("Click on the card above", 20, 240);
			g.drawString("to view the shuffled deck", 20, 255);
			g.drawString("one by one and sorted", 20, 270);
			g.drawString("until the cards are used", 20, 285);
			g.drawString("and the original card", 20, 300);
			g.drawString("cannot be seen", 20, 315);
		}else {
			g.setColor(Color.black);
			g.fillRect(20, 20, 125, 200);
		}
		
		
		
		for(int i = 1; i < 11; i++) {
		g.setColor(Color.gray);
		g.fillRect(20 + (145*i), 20, 125, 200);
		}
		start = true;
		
		
		
		if(newCard) {
			//System.out.println("cardIdx: " + cardIdx);
			//System.out.println("cards.length: " + cards.length);
			if(cardIdx >= cards.length) {
				//System.out.println("nope");
				
				distributed = true;
				
			}else {
				System.out.println("entered");
				int currCard = cards[cardIdx];
				System.out.println("currCard: " + currCard);
				switch(currCard) {
				case 0:
					//System.out.println(0);
					//g.drawImage(c0, 20 + (145),20 + (20*idx0), 125, 200, null);
					
					idx0++;
					cardIdx++;
					repaint();
					break;
				case 1:
					//System.out.println(1);
					//g.drawImage(c1, 20 + (145*2),20 + (20*idx1), 125, 200, null);
					idx1++;
					cardIdx++;
					repaint();
					break;
				case 2:
					//System.out.println(2);
					//g.drawImage(c2, 20 + (145*3),20 + (20*idx2), 125, 200, null);
					idx2++;
					cardIdx++;
					repaint();
					break;
				case 3:
					//System.out.println(3);
					//g.drawImage(c3, 20 + (145*4),20 + (20*idx3), 125, 200, null);
					idx3++;
					cardIdx++;
					repaint();
					break;
				case 4:
					//System.out.println(4);
					//g.drawImage(c4, 20 + (145*5),20 + (20*idx4), 125, 200, null);
					idx4++;
					cardIdx++;
					repaint();
					break;
				case 5:
					//System.out.println(5);
					//g.drawImage(c5, 20 + (145*6),20 + (20*idx5), 125, 200, null);
					idx5++;
					cardIdx++;
					repaint();
					break;
				case 6:
					//System.out.println(6);
					//g.drawImage(c6, 20 + (145*7),20 + (20*idx6), 125, 200, null);
					idx6++;
					cardIdx++;
					repaint();
					break;
				case 7:
					//System.out.println(7);
					//g.drawImage(c7, 20 + (145*8),20 + (20*idx7), 125, 200, null);
					idx7++;
					cardIdx++;
					repaint();
					break;
				case 8:
					//System.out.println(8);
					//g.drawImage(c8, 20 + (145*9),20 + (20*idx8), 125, 200, null);
					idx8++;
					cardIdx++;
					repaint();
					break;
				case 9:
					//System.out.println(9);
					//g.drawImage(c9, 20 + (145*10),20 + (20*idx9), 125, 200, null);
					idx9++;
					cardIdx++;
					repaint();
					break;
				}
			}
			newCard = false;
		}
		
		if(background) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			for(int i = 1; i < 11; i++) {
				g.setColor(Color.gray);
				g.fillRect(20 + (145*i), 20, 125, 200);
			}
			g.setColor(Color.black);
			g.fillRect(20, 20, 125, 200);
			//g.fillRect(200 - 50,400,getWidth()-560,getHeight()-450);
			background = false;
		}
		
		//draw out the cards up to the current amount of each card has come from the deck
		for(int i = 0; i < idx0; i++) {
			g.drawImage(c0, (20 + (145)),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx1; i++) {
			g.drawImage(c1, 20 + (145)*2,20 + (30*i), 125, 200, null);
			//System.out.print(idx1);
			//System.out.println();
		}
		for(int i = 0; i < idx2; i++) {
			g.drawImage(c2, 20 + (145*3),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx3; i++) {
			g.drawImage(c3, 20 + (145*4),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx4; i++) {
			g.drawImage(c4, 20 + (145*5),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx5; i++) {
			g.drawImage(c5, 20 + (145*6),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx6; i++) {
			g.drawImage(c6, 20 + (145*7),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx7; i++) {
			g.drawImage(c7, 20 + (145*8),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx8; i++) {
			g.drawImage(c8, 20 + (145*9),20 + (30*i), 125, 200, null);
		}
		for(int i = 0; i < idx9; i++) {
			g.drawImage(c9, 20 + (145*10),20 + (30*i), 125, 200, null);
		}
		
		//covers the initial card
		g.setColor(Color.black);
		g.fillRect(200 - 40,430,getWidth()-560,getHeight()-460);
		g.setColor(Color.white);
		g.drawString("Player 1", 200, 460);
		g.drawString("Drawn Card", 280 + getWidth()/7 - 30, 460);
		g.drawString("Player 2", getWidth() - getWidth()/7 - 400 - 30, 460);
		
		//sect for player sim once all cards are drawn
		g.setColor(Color.gray);
		g.fillRect(240 - 50, 470, getWidth()/7, getHeight()-530);
		g.fillRect(280 + getWidth()/7 - 50, 470, getWidth()/7, getHeight()-530);
		g.fillRect(getWidth() - getWidth()/7 - 400 - 50, 470, getWidth()/7, getHeight()-530);
		
		
		//draw out the cards up to the current amount of each card has come from the deck
		/*System.out.println("idx0: " + idx0);
		System.out.println("idx1: " + idx1);
		System.out.println("idx2: " + idx2);
		System.out.println("idx3: " + idx3);
		System.out.println("idx4: " + idx4);
		System.out.println("idx5: " + idx5);
		System.out.println("idx6: " + idx6);
		System.out.println("idx7: " + idx7);
		System.out.println("idx8: " + idx8);
		System.out.println("idx9: " + idx9);*/
				for(int i = 0; i < idx0; i++) {
					g.drawImage(c0, (20 + (145)),20 + (30*i), 125, 200, null);
					
				}
				for(int i = 0; i < idx1; i++) {
					g.drawImage(c1, 20 + (145)*2,20 + (30*i), 125, 200, null);
					//System.out.print(idx1);
					//System.out.println();
				}
				for(int i = 0; i < idx2; i++) {
					g.drawImage(c2, 20 + (145*3),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx3; i++) {
					g.drawImage(c3, 20 + (145*4),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx4; i++) {
					g.drawImage(c4, 20 + (145*5),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx5; i++) {
					g.drawImage(c5, 20 + (145*6),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx6; i++) {
					g.drawImage(c6, 20 + (145*7),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx7; i++) {
					g.drawImage(c7, 20 + (145*8),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx8; i++) {
					g.drawImage(c8, 20 + (145*9),20 + (30*i), 125, 200, null);
				}
				for(int i = 0; i < idx9; i++) {
					g.drawImage(c9, 20 + (145*10),20 + (30*i), 125, 200, null);
				}
		
		//System.out.println("run: " + run);
		if(distributed && !run) { //show buttons for choosing each player's card
			g.fillRect(150 + getWidth()-560 + 30, 430, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			g.fillRect(150 + getWidth()-560 + 30, 500, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			g.fillRect(150 + getWidth()-560 + 30, 570, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			
			g.setColor(Color.yellow);
			if(changePly0){
				g.fillRect(150 + getWidth()-560 + 30, 430, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			}else if(changeDrawn) {
				g.fillRect(150 + getWidth()-560 + 30, 500, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			}else if(changePly1) {
				g.fillRect(150 + getWidth()-560 + 30, 570, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			}
			
			g.setColor(Color.black);
			g.drawString("Change Player 1", 150 + getWidth()-560 + 50, 455);
			g.drawString("Change Drawn", 150 + getWidth()-560 + 50, 525);
			g.drawString("Change Player 2", 150 + getWidth()-560 + 50, 595);
			
		}
		
		//if(chosenDrawn.getCardImg() != null)			g.drawImage(chosenDrawn.getCardImg(), 100,100,100,100 , null);
		if(chosenPly0.getCardImg() != null) {
			g.drawImage(chosenPly0.getCardImg(),  240 - 50, 470, getWidth()/7, getHeight()-530, null);
			repaint();
		}
		if(chosenDrawn.getCardImg() != null) {
			g.drawImage(chosenDrawn.getCardImg(), 280 + getWidth()/7 - 50, 470, getWidth()/7, getHeight()-530, null);
			repaint();
		}
		if(chosenPly1.getCardImg() != null) {
			g.drawImage(chosenPly1.getCardImg(), getWidth() - getWidth()/7 - 400 - 50, 470, getWidth()/7, getHeight()-530, null);
			repaint();
		}
		
		if(chosenPly0.getType() > -1 && chosenPly1.getType() > -1 && chosenDrawn.getType() > -1) {
			Color myRed = new Color(186,56, 56);
			g.setColor(myRed);
			g.fillRect(150 + getWidth()-560 + 30, 420 + getHeight()-450 - 50, getWidth() - (150 + getWidth()-560 + 30) - 150, 50);
			
			g.setColor(Color.white);
			if(run) {
				g.drawString("Stop Simulator", 150 + getWidth()-560 + 50, getHeight()-50);
			}else {
				g.drawString("Run Simulator", 150 + getWidth()-560 + 50, getHeight()-50);
			}
			
		}
	}
	
	public void run() {
		print = "";
		int type = pickedCard.getType();
		switch(type) {
		case 0: 
			print = print + "If you are the only one to have drawn the spy and are still the only one by the end of the game, you get a token";
			break;
		case 1: 
			print = print + "Guess the card of the other player. If that player has the mentioned card, they are out of the round";
			break;
		case 2: 
			print = print + "You may look at another player\'s hand";
			break;
		case 3:
			if(pickedCard.getType() == chosenPly0.getType() && chosenDrawn.getType() > chosenPly1.getType()) {
				print = print + "You compare non chosen cards and because your card has greater value, the other player is out of the round";
			}else if(pickedCard.getType() == chosenPly0.getType() && chosenDrawn.getType() < chosenPly1.getType()) {
				print = print + "You compare non chosen cards and because the other player\'s card has greater value, you are out of the round";
			}else if(pickedCard.getType() == chosenDrawn.getType() && chosenPly0.getType() > chosenPly1.getType()) {
				print = print + "You compare non chosen cards and because your card has greater value, the other player is out of the round";
			}else if(pickedCard.getType() == chosenDrawn.getType() && chosenPly0.getType() < chosenPly1.getType()) {
				print = print + "You compare non chosen cards and because the other player\'s card has greater value, you are out of the round";
			}else if(pickedCard.getType() == chosenPly1.getType()) {
				print = print + "You compare non chosen cards and because your card and the other player\'s cards are equal, no one is out";
			}
			break;
		case 4: 
			print = print + "Until your next round, ignore the affects from the other player\'s hand";
			break;
		case 5: 
			print = print + "Choose either yourself or the other player to discard their card and draw another";
			break;
		case 6: 
			print = print + "Draw 2 cards. Keep 1 and put the other 2 on the botton of the deck";
			break;
		case 7:
			print = print + "Trade hands with the other player";
			break;
		case 8:
			print = print + "You are out of the game";
			break;
		}
		System.out.println(print);
		printed = true;
		
	}
	
	
	public void change() {
		System.out.println();
		System.out.println("chosen0: " + chosenPly0.getType());
		if(changePly0 && prev0 != chosenPly0.getType()) {
			System.out.println(0);
			System.out.println("chosenPly0: " + chosenPly0.getType());
			System.out.println("idx0: " + idx0);
			System.out.println(0);
			if(chosenPly0.getType() == 0 && idx0 > 1)idx0--;
			else if(chosenPly0.getType() == 1 && idx1 > 0) idx1--;
			else if(chosenPly0.getType() == 2 && idx2 > 0) idx2--;
			else if(chosenPly0.getType() == 3 && idx3 > 0) idx3--;
			else if(chosenPly0.getType() == 4 && idx4 > 0) idx4--;
			else if(chosenPly0.getType() == 5 && idx5 > 0) idx5--;
			else if(chosenPly0.getType() == 6 && idx6 > 0) idx6--;
			else if(chosenPly0.getType() == 7 && idx7 > 0) idx7--;
			else if(chosenPly0.getType() == 8 && idx8 > 0) idx8--;
			else if(chosenPly0.getType() == 9 && idx9 > 0) idx9--;
		}else if(changeDrawn && prevD != chosenDrawn.getType()) {
			if(chosenDrawn.getType() == 0 && idx0 > 1)idx0--;
			else if(chosenDrawn.getType() == 1 && idx1 > 0) idx1--;
			else if(chosenDrawn.getType() == 2 && idx2 > 0) idx2--;
			else if(chosenDrawn.getType() == 3 && idx3 > 0) idx3--;
			else if(chosenDrawn.getType() == 4 && idx4 > 0) idx4--;
			else if(chosenDrawn.getType() == 5 && idx5 > 0) idx5--;
			else if(chosenDrawn.getType() == 6 && idx6 > 0) idx6--;
			else if(chosenDrawn.getType() == 7 && idx7 > 0) idx7--;
			else if(chosenDrawn.getType() == 8 && idx8 > 0) idx8--;
			else if(chosenDrawn.getType() == 9 && idx9 > 0) idx9--;
		}else if(changePly1 && prev1 != chosenPly1.getType()) {
			if(chosenPly0.getType() == 0 && idx0 > 1)idx0--;
			else if(chosenPly1.getType() == 1 && idx1 > 0) idx1--;
			else if(chosenPly1.getType() == 2 && idx2 > 0) idx2--;
			else if(chosenPly1.getType() == 3 && idx3 > 0) idx3--;
			else if(chosenPly1.getType() == 4 && idx4 > 0) idx4--;
			else if(chosenPly1.getType() == 5 && idx5 > 0) idx5--;
			else if(chosenPly1.getType() == 6 && idx6 > 0) idx6--;
			else if(chosenPly1.getType() == 7 && idx7 > 0) idx7--;
			else if(chosenPly1.getType() == 8 && idx8 > 0) idx8--;
			else if(chosenPly1.getType() == 9 && idx9 > 0) idx9--;
		}
		
		
		System.out.println("prev0: " + prev0);
		System.out.println("prevD: " + prevD);
		System.out.println("prev1: " + prev1);
		System.out.println("change0: " + changePly0);
		System.out.println("change1: " + changeDrawn);
		System.out.println("changeDrawn: " + changePly1);
		
		if(changePly0 && prev0 != chosenPly0.getType()) {
			if(prev0 == 0) idx0++;
			else if(prev0 == 1) idx1++;
			else if(prev0 == 2) idx2++;
			else if(prev0 == 3) idx3++;
			else if(prev0 == 4) idx4++;
			else if(prev0 == 5) idx5++;
			else if(prev0 == 6) idx6++;
			else if(prev0 == 7) idx7++;
			else if(prev0 == 8) idx8++;
			else if(prev0 == 9) idx9++;
		}else if(changeDrawn && prevD != chosenDrawn.getType()) {
			if(prevD == 0) idx0++;
			else if(prevD == 1) idx1++;
			else if(prevD == 2) idx2++;
			else if(prevD == 3) idx3++;
			else if(prevD == 4) idx4++;
			else if(prevD == 5) idx5++;
			else if(prevD == 6) idx6++;
			else if(prevD == 7) idx7++;
			else if(prevD == 8) idx8++;
			else if(prevD == 9) idx9++;
		}else if(changePly1 && prev1 != chosenPly1.getType()) {
			if(prev1 == 0) idx0++;
			else if(prev1 == 1) idx1++;
			else if(prev1 == 2) idx2++;
			else if(prev1 == 3) idx3++;
			else if(prev1 == 4) idx4++;
			else if(prev1 == 5) idx5++;
			else if(prev1 == 6) idx6++;
			else if(prev1 == 7) idx7++;
			else if(prev1 == 8) idx8++;
			else if(prev1 == 9) idx9++;
		}
		
		if(changePly0Min) {
			prev0 = chosenPly0.getType();
		}else if(changeDrawnMin) {
			prevD = chosenDrawn.getType();
		}else if(changePly1Min) {
			prev1 = chosenPly1.getType();
		}
		
		System.out.println("idx0: " + idx0);
		System.out.println("idx1: " + idx1);
		System.out.println("idx2: " + idx2);
		System.out.println("idx3: " + idx3);
		System.out.println("idx4: " + idx4);
		System.out.println("idx5: " + idx5);
		System.out.println("idx6: " + idx6);
		System.out.println("idx7: " + idx7);
		System.out.println("idx8: " + idx8);
		System.out.println("idx9: " + idx9);
		
		changePly0 = false; changeDrawn = false; changePly1 = false;
		background = true;
	}
	
	
	
}
