import java.awt.*;
import java.lang.Math;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LoveLetterGraphicPanel extends JPanel implements MouseListener{
	private BufferedImage bsc, b1, b2, head, ref, token, c9, broke, winScreen;
	
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
	private int startPlay;
	private boolean see;
	
	private LLText text;
	private boolean drawn;
	private boolean chosen;
	private int chosenCard;
	private boolean out0, out1, out2;
	private boolean done;
	private boolean finished;
	private boolean safe0, safe1, safe2, othersSafe;
	private int[][] played;
	private boolean countess, maid, out, guess, card5, card6;
	private int picked;
	private int guessed;
	private int compareWin;
	private int plays;
	private Player drawnCards = new Player();
	
	private int tempWin;
	private int[] wins;
	private boolean play;
	private int fullWin;
	
	public LoveLetterGraphicPanel() {
		plys = new Player[players];
		// other inputs
		
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
		
		
		
		discarded = false;
		
		
		//for the game
		startPlay = -1;
		
		see = false;
		
		text = new LLText();
		drawn = false;
		
		chosen = false;;
		chosenCard = -1;
		
		done = false;
		safe0 = false; safe1 = false; safe2 = false; othersSafe = false;
		
		countess = false; maid = false; out = false;
		picked = -1;
		play = false; card5 = false; card6 = false;
		guessed = -1; guess = false;
		compareWin = -1;
		
		fullWin = -1;
		
		//for general game
		played = new int[3][6];
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[0].length; j++) {
				played[i][j] = -1;
			}
		}
		tempWin = -1;
		wins = new int[3];
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[0].length; j++) {
				played[i][j] = -1;
			}
		}
		
		try {
			bsc = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/BrokenSealCard.PNG"));
			b1 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLCardBack_1.png"));
			b2 = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLCardBack_2.png"));
			head = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLHeader.jpg"));
			ref = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLReference.png"));
			token = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLToken.png"));
			winScreen = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/LLWins.jpg"));
			broke = ImageIO.read(LoveLetterGraphicPanel.class.getResource("/LLPics/BrokenSealCard.PNG"));
		}catch(Exception E) {
			System.out.println("Exception Error");
			return;
		}
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
				//System.out.println("addCard: " + addCard);
				//System.out.println("currPlayer: " + currPlayer);
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
				//System.out.println(00);
				see0 = !see0;
				see1 = false;
				see2 = false;
			}	
			else if(x >= getWidth()/5*2+80 && y >= 50 && x <= getWidth()/5*2 + getWidth()/5+40 && y <= 90) {
				//System.out.println(11);
				see0 = false;
				see1 = !see1;
				see2 = false;
				
			}
			else if(x >= getWidth()/5*3+80 && y >= 50 && x <= getWidth()/5*3 + getWidth()/5+40 && y <= 90) {
				//System.out.println(22);
				see0 = false;
				see1 = false;
				see2 = !see2;
			}
			//getWidth()/5+80, 110, 60, 30
			if(x >= getWidth()/5+80 && y >= 110 && x <= getWidth()/5+140 && y <= 140) {
				see0 = false; see1 = false; see2 = false;
				start = 4;
				drawn = false;
			}	
				
		}
		
		if(start == 4 && e.getButton() == e.BUTTON1) {
			//System.out.println("Chosen: " + chosen);
			//g.fillRect(140 + getWidth()/5 + 40, 40, 100, 30);
			if(x >= 150 + getWidth()/5 + 40 && y>= 40 && x <= 150 + getWidth()/5 + 140 && y <=  70) {
				//System.out.println("SEE");
				see = !see;
			}
			if(startPlay > -1 && !chosen && chosenCard == -1 && plys[startPlay].isEmpty(1) && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282 && see) {
				//System.out.println("cats");
				drawn = true;
			}
			if(play && !chosen  && see) {
				/*g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
		g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);*/
				if(x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20){
					if((startPlay > -1 && plys[startPlay].getType(0) == 5 || plys[startPlay].getType(0) == 7) && plys[startPlay].getType(1) == 8) {
						countess = true;
					}else {
						chosenCard = 0;
						chosen = true;
						//System.out.println("Chosen: " + chosenCard);
					}
					
				}else if(x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20){
					if((startPlay > -1 && plys[startPlay].getType(1) == 5 || plys[startPlay].getType(1) == 7) && plys[startPlay].getType(0) == 8) {
						countess = true;
					}else {
						chosenCard = 1;
						chosen = true;
						//System.out.println("Chosen: " + chosenCard);
					}
				}
			}
			
			/*
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
			 */
			
			
			if((see0 ||see1 ||see2) && startPlay > -1 && see && chosen && !done) {
				if(!drawn) {
					plays = -1;
				}
				//System.out.println("Start Play: " + startPlay);
				if(!drawn && startPlay != -1 && chosenCard != -1 && !plys[startPlay].isEmpty(chosenCard)) plays = plys[startPlay].getType(chosenCard);
				switch(plays) {
				case 0:
					//System.out.println(0);
					done = true;
					break;
				case 1:
					//System.out.println(1);
					if(!out0 && startPlay == 0 && (safe1 || out1) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out1 && startPlay == 1 && (safe0 || out0) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out2 && startPlay == 2 && (safe0 || out0) && (safe1 || out1)) {
						othersSafe = true;
						done = true;
					}else {
						if(picked == -1) {
							if(x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20) {
								//System.out.println("SAFE0: " + safe0);
								//System.out.println("SAFE1: " + safe1);
								//System.out.println("SAFE2: " + safe2);
								//System.out.println("MAID: " + maid);
								if(!safe0 && !out0 && y >= 110 && y <= 110 + 30) {
									picked = 0;
									maid = false;
								}else if(!safe1  && !out1 && y >= 160  && y <= 160 + 30) {
									picked = 1;
									maid = false;
								}else if(!safe2 && !out2 && y >= 210 && y <= 210 + 30) {
									picked = 2;
									maid = false;
								}else if((safe0 || safe1 || safe2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
									maid = true;
								}else if((out0 || out1 || out2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
									out = true;
								}
							}
							
						}
						/*
						 * g.fillRect(getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20, 120 + (32*i), (getWidth()-380-getWidth()/5)/6, 20);
						 */
						if(picked > -1 && guessed == -1) {
							if(x >= getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20 && x <= getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20 + (getWidth()-380-getWidth()/5)/6) {
								//System.out.println("SAFE0: " + safe0);
								//System.out.println("SAFE1: " + safe1);
								//System.out.println("SAFE2: " + safe2);
								//System.out.println("MAID: " + maid);
								if(y >= 120 + (32*0) && y <= 120 + (32*0) + 20) {
									guessed = 0;
								}else if(y >= 120 + (32*1) && y <= 120 + (32*1) + 20) {
									guessed = 2;
								}else if(y >= 120 + (32*2) && y <= 120 + (32*2) + 20) {
									guessed = 3;
								}else if(y >= 120 + (32*3) && y <= 120 + (32*3) + 20) {
									guessed = 4;
								}else if(y >= 120 + (32*4) && y <= 120 + (32*4) + 20) {
									guessed = 5;
								}else if(y >= 120 + (32*5) && y <= 120 + (32*5) + 20) {
									guessed = 6;
								}else if(y >= 120 + (32*6) && y <= 120 + (32*6) + 20) {
									guessed = 7;
								}else if(y >= 120 + (32*7) && y <= 120 + (32*7) + 20) {
									guessed = 8;
								}else if(y >= 120 + (32*8) && y <= 120 + (32*8) + 20) {
									guessed = 9;
								}
							}
						}
						
						if(picked > -1 && guessed > -1 && plys[picked].getType(0) == guessed) {
							guess = true;
							
						}
						if(guessed > -1 && picked > -1) {
							done = true;
						}
					}
					
					
					
					break;
				case 2:
					//System.out.println(2);
					if(!out0 && startPlay == 0 && (safe1 || out1) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out1 && startPlay == 1 && (safe0 || out0) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out2 && startPlay == 2 && (safe0 || out0) && (safe1 || out1)) {
						othersSafe = true;
						done = true;
					}else {
					
					if(picked == -1) {
						
						if(x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20) {
							//System.out.println("SAFE0: " + safe0);
							//System.out.println("SAFE1: " + safe1);
							//System.out.println("SAFE2: " + safe2);
							//System.out.println("MAID: " + maid);
							if(startPlay != 0 && !safe0 && !out0 && y >= 110 && y <= 110 + 30) {
								picked = 0;
								maid = false;
								done = true;
							}else if(startPlay != 1 && !safe1 && !out1 && y >= 160  && y <= 160 + 30) {
								picked = 1;
								maid = false;
								done = true;
							}else if(startPlay != 2 && !safe2 && !out2 && y >= 210 && y <= 210 + 30) {
								picked = 2;
								maid = false;
								done = true;
							}else if(startPlay != 0 && safe0 && !out0 && y >= 110 && y <= 110 + 30) {
								maid = true;
							}else if(startPlay != 1 && safe1 && !out1 && y >= 160  && y <= 160 + 30) {
								maid = true;
							}else if(startPlay != 2 && safe2 && !out2 && y >= 210 && y <= 210 + 30) {
								maid = true;
							}else if((out0 || out1 || out2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
								out = true;
							}
						}
						
						
						
						/*if(startPlay == 0) {
							if(!safe1 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 160 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 160 + 30) {
								picked = 1;
								done = true;
							}else if(!safe2 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 210 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 210 + 30) {
								picked = 2;
								done = true;
							}
						}else if(startPlay == 1) {
							if(!safe0 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 110 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 110 + 30) {
								picked = 0;
								done = true;
							}else if(!safe2 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 210 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 210 + 30) {
								picked = 2;
								done = true;
							}
						}else if(startPlay == 2) {
							if(!safe0 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 110 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 110 + 30) {
								picked = 0;
								done = true;
							}else if(!safe1 && x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && y >= 160 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y <= 160 + 30) {
								picked = 1;
								done = true;
							}else {
								
							}
						}*/
					}
					
					}
					break;
				case 3:
					//System.out.println(3);
					if(!out0 && startPlay == 0 && (safe1 || out1) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out1 && startPlay == 1 && (safe0 || out0) && (safe2 || out2)) {
						othersSafe = true;
						done = true;
					}else if(!out2 && startPlay == 2 && (safe0 || out0) && (safe1 || out1)) {
						othersSafe = true;
						done = true;
					}else {
					
					if(picked == -1) {
						if(x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20) {
							//System.out.println("SAFE0: " + safe0);
							//System.out.println("SAFE1: " + safe1);
							//System.out.println("SAFE2: " + safe2);
							//System.out.println("MAID: " + maid);
							if(startPlay != 0 && !safe0 && !out0 && y >= 110 && y <= 110 + 30) {
								picked = 0;
								maid = false;
								done = true;
							}else if(startPlay != 1 && !safe1 && !out1 && y >= 160  && y <= 160 + 30) {
								picked = 1;
								maid = false;
								done = true;
							}else if(startPlay != 2 && !safe2 && !out2 && y >= 210 && y <= 210 + 30) {
								picked = 2;
								maid = false;
								done = true;
							}else if(startPlay != 0 && safe0 && !out0 && y >= 110 && y <= 110 + 30) {
								maid = true;
							}else if(startPlay != 1 && safe1 && !out1 && y >= 160  && y <= 160 + 30) {
								maid = true;
							}else if(startPlay != 2 && safe2 && !out2 && y >= 210 && y <= 210 + 30) {
								maid = true;
							}else if((out0 || out1 || out2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
								out = true;
							}
						}
					}
					
					if(picked > -1) {
						if(chosenCard == 0) {
							if(plys[startPlay].getType(1) > plys[picked].getType(0)) {
								compareWin = 0;
							}else if(plys[startPlay].getType(1) < plys[picked].getType(0)) {
								compareWin = 1;
							}else if(plys[startPlay].getType(1) == plys[picked].getType(0)) {
								compareWin = 2;
							}
						}else if(chosenCard == 1) {
							if(plys[startPlay].getType(0) > plys[picked].getType(0)) {
								compareWin = 0;
							}else if(plys[startPlay].getType(0) < plys[picked].getType(0)) {
								compareWin = 1;
							}else if(plys[startPlay].getType(0) == plys[picked].getType(0)) {
								compareWin = 2;
							}
						}
						done = true;
						System.out.println("COMPARE: " + compareWin);
					}
					}
					break;
				case 4:
					//System.out.println(4);
					if(startPlay == 0) safe0 = true;
					else if(startPlay == 1) safe1 = true;
					else if(startPlay == 2) safe2 = true;
					done = true;
					break;
				case 5:
					card5 = true;
					//System.out.println(5);
					if(picked == -1) {
						if(x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20) {
							//System.out.println("SAFE0: " + safe0);
							//System.out.println("SAFE1: " + safe1);
							//System.out.println("SAFE2: " + safe2);
							//System.out.println("MAID: " + maid);
							//System.out.println("DRAWN: " + drawn);
							if(!safe0 && y >= 110 && y <= 110 + 30) {
								picked = 0;
								if(startPlay != picked && plys[picked].getType(0) == 9) out0 = true;
								maid = false;
							}else if(!safe1 && y >= 160  && y <= 160 + 30) {
								picked = 1;
								if(startPlay != picked && plys[picked].getType(0) == 9) out1 = true;
								maid = false;
							}else if(!safe2 && y >= 210 && y <= 210 + 30) {
								picked = 2;
								if(startPlay != picked && plys[picked].getType(0) == 9) out2 = true;
								maid = false;
							}else if((y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
								maid = true;
							}else if((out0 || out1 || out2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
								out = true;
							}
						}
						//System.out.println("PICKED: " + picked);
					}
						//System.out.println("PICKED 2: " + picked);
						//System.out.println("DRAWN: " + drawn);
						if(picked > -1 && !drawn && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282 && see) {
							//System.out.println("DRAWN 2: " + drawn);
							drawn = true;
							
							for(int i = 0; i < played[startPlay].length; i++) {
								if(played[startPlay][i] == -1) {
									played[startPlay][i] = plys[startPlay].getType(chosenCard);
									break;
								}
							}
							
							if(picked == startPlay) {
								for(int j = 0; j < played[picked].length; j++) {
									if(played[picked][j] == -1) {
										if(chosenCard == 0) {
											played[picked][j] = plys[picked].getType(1);
										}else if(chosenCard == 1) {
											played[picked][j] = plys[picked].getType(0);
										}
										break;
									}
								}
								plys[startPlay].emptyCard(0);
								plys[startPlay].emptyCard(1);
								plys[startPlay].changeCard(new Card(cards[idx]), 0);
								idx++;
								done = true;
							}else if (picked != startPlay) {
								for(int j = 0; j < played[picked].length; j++) {
									if(played[picked][j] == -1) {
										played[picked][j] = plys[picked].getType(0);
										break;
									}
								}
								plys[picked].emptyCard(0);
								plys[picked].changeCard(new Card(cards[idx]), 0);
								idx++;
								
								if(chosenCard == 0) {
									plys[startPlay].changeCard(new Card(plys[startPlay].getType(1)), 0);
									plys[startPlay].emptyCard(1);
								}else if(chosenCard == 1) {
									plys[startPlay].emptyCard(1);
								}
								done = true;
						}
							
							
							
							
							//System.out.println("Picked: " + picked);
							//System.out.println("plys 0.0: " + plys[startPlay].getType(0) + " + plys 0.1: " + plys[startPlay].getType(1));
							//System.out.println("plys 1.0: " + plys[picked].getType(0) + " + plys 1.1: " + plys[picked].getType(1));
							
					}
					break;
				case 6: 
					done = true;
					/*if(drawnCards.isEmpty(0)) {
						drawnCards.changeCard(new Card(idx), 0);
						cards[idx] = -1;
						idx++;
						drawnCards.changeCard(new Card(idx), 1);
						cards[idx] = -1;
						idx++;
					}
					
					System.out.println("DRAWN: " + drawn);
					System.out.println("DRAWN 0: " + drawnCards.getType(0));
					System.out.println("DRAWN 1: " + drawnCards.getType(1));
					
					if(!drawn && x >= getWidth()-90 && y >= 170 && x <= getWidth()-20 && y <= 282 && see) {
						drawn = true;
						card6 = true;
						for(int i = 0; i < played[startPlay].length; i++) {
							if(played[startPlay][i] == -1) {
								played[startPlay][i] = 6;
								break;
							}
						}
					}
					
					//x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20
					//x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20
					//x >= getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y >= 110 && x >= getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 + (getWidth()-330-getWidth()/5)/6 && y <= 110 + 260 + getHeight()/20
					
					if(card6 && x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6) + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20) {
						System.out.println(0);
						chosenCard = 0;
					}else if(card6 && x >= getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 && y >= 110 && x <= (getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6)*2 + (getWidth()-220-getWidth()/5)/6) && y <= 110 + 260 + getHeight()/20) {
						System.out.println(1);
						chosenCard = 1;
					}else if(card6 && x >= getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 && y >= 110 && x >= getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20 + (getWidth()-330-getWidth()/5)/6 && y <= 110 + 260 + getHeight()/20) {
						System.out.println(2);
						chosenCard = 2;
					}
					
					if(chosenCard == 0) {
						if(picked == 0) {
							plys[startPlay].changeCard(new Card(plys[startPlay].getType(1)), 0);
							plys[startPlay].emptyCard(1);
						}else if(picked == 1) {
							plys[startPlay].emptyCard(1);
						}
						done = true;
					}else if(chosenCard == 1) {
						plys[startPlay].changeCard(drawnCards.getCard(0), 0);
						plys[startPlay].emptyCard(1);
						done = true;
					}else if(chosenCard == 2) {
						plys[startPlay].changeCard(drawnCards.getCard(1), 0);
						plys[startPlay].emptyCard(1);
						done = true;
					}
					System.out.println("DONE: " + done);*/
					break;
				case 7:
					//System.out.println(7);
					if(!out0 && startPlay == 0 && safe1 && safe2) {
						othersSafe = true;
						done = true;
					}else if(!out1 && startPlay == 1 && safe0 && safe2) {
						othersSafe = true;
						done = true;
					}else if(!out2 && startPlay == 2 && safe0 && safe1) {
						othersSafe = true;
						done = true;
					}else {
						if(picked == -1) {
							if(x >= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 && x <= getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20) {
								/*//System.out.println("SAFE0: " + safe0);
								//System.out.println("SAFE1: " + safe1);
								//System.out.println("SAFE2: " + safe2);
								//System.out.println("MAID: " + maid);*/
								if(!safe0 && !out0 && y >= 110 && y <= 110 + 30) {
									picked = 0;
									maid = false;
								}else if(!safe1  && !out1 && y >= 160  && y <= 160 + 30) {
									picked = 1;
									maid = false;
								}else if(!safe2 && !out2 && y >= 210 && y <= 210 + 30) {
									picked = 2;
									maid = false;
								}else if((safe0 || safe1 || safe2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
									maid = true;
								}else if((out0 || out1 || out2) && (y >= 110 && y <= 110 + 30) && (y >= 160 && y <= 160 + 30) || (y >= 210 && y <= 210 + 30)) {
									out = true;
								}
								
								//System.out.println("Card 7 Picked: " + picked);
								if(picked > -1) {
									//System.out.println("7 PLYS 1: " + plys[startPlay].getType(0));
									//System.out.println("7 PLYS 1: " + plys[startPlay].getType(1));
									Card tempCard = new Card(plys[picked].getType(0));
									
									
									
									//plys[startPlay].emptyCard(1);
									if(chosenCard == 0) {
										plys[picked].changeCard(new Card(plys[startPlay].getType(1)), 0);
										plys[startPlay].changeCard(tempCard, 1);
										
									}else if(chosenCard == 1) {
										plys[picked].changeCard(new Card(plys[startPlay].getType(0)), 0);
										plys[startPlay].changeCard(tempCard, 0);
										
									}
									
									//System.out.println("7 PLYS 1: " + plys[startPlay].getType(0));
									//System.out.println("7 PLYS 1: " + plys[startPlay].getType(1));
									
									done = true;							
								}
							}
							
						}
						
						
						
						
					}
					break;
				case 8:
					//System.out.println(8);
					done = true;
					break;
				case 9:
					//System.out.println(9);
					if(startPlay == 0) out0 = true;
					else if(startPlay == 1) out1 = true;
					else if(startPlay == 2) out2 = true;
					done = true;
					break;
				}
			}
			if(done && x >= 20 + getWidth()/5 + 100 + getWidth()/2 && y >= 410 + getHeight()/20 + 10 && x <= 20 + getWidth()/5 + 100 + getWidth()/2 + getWidth()-(20 + getWidth()/5 + 100 + getWidth()/2) - 195 && y <= 410 + getHeight()/20 + 10 + 30){
				finished = true;
			}
			
		}
			//20 + getWidth()/5 + 100 + getWidth()/2, 410 + getHeight()/20 + 10, getWidth()-(20 + getWidth()/5 + 100 + getWidth()/2) - 195, 30
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
		
		
		
		
		if(start == 1) { //this stage changes the screen to right before card distrubution
			repaint();
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 120, getHeight()-175);
			start++;
		}else if(start == 2) { //card distribution
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			
			
			
			if(seeRef) {
				////System.out.println("true");
				g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
			}else {
				g.drawImage(ref, 20, 20, 70, 112, null);
			}
			
			if(discarded) {
				g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
			}
			
			g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
			
			if(discarded) {
				g.setColor(Color.yellow);
				g.drawString("Discarded (" + 1 + ")", getWidth()-90, 150);
			}else {
				g.setColor(Color.yellow);
				g.drawString("Discarded (" + 0 + ")", getWidth()-90, 150);
			}
			
			
			g.setColor(Color.WHITE);
			g.drawString("New Cards", getWidth()-90, 300);
			g.drawString("Cards: " + (cards.length - idx), getWidth()-90, 315);
			
			
			
			if(!seeRef) {
				g.drawString("1. each person draws a card from the \"new", 20, 150);
				g.drawString("cards\" deck", 20, 165);
				g.drawString("2. each person clicks to view the card they ", 20, 185);
				g.drawString("start with before starting the game", 20, 200);
				g.drawString("3. After clicking done, you are starting the game", 20, 220);
				g.drawString("4. follow the directions of the skiny black bar int the center", 20, 240);
				g.drawString("5. first player to 5 wins, wins the game", 20, 260);
				g.drawString("+ click refrence card to see it bigger/smaller", 20, 280);
				g.drawString("+ card 6 does not work correctly", 20, 295);
				//g.drawString("start with before starting the game", 20, 260);
				//g.drawString("start with before starting the game", 20, 275);
			}
			
			
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 120, getHeight()-175);
			
			//current player box
			g.setColor(Color.GRAY);
			g.fillRect(20 + getWidth()/5 + 20, 20,getWidth()-150-getWidth()/5, getHeight()- 230);
			
			////System.out.println("idx: " + idx);
			////System.out.println("idx val: " + cards[idx]);
			
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
					for(int j = 0; j < 2; j++) {
						if(j == 0) {
							plys[0].changeCard(new Card(cards[idx]), 0);
							
							cards[idx] = -1;
							
							idx++;
							/*if(idx == cards.length) {
								idx = 0;
							}*/
						}else {
							plys[0].emptyCard(j);
						}
					}
					////System.out.println(0 + "!");
					firstCard = true;
					addCard = false;
					currPlayer++;
				}else if(currPlayer == 1) {
					see1 = true;
					plys[1] = new Player(1);
					for(int k = 0; k < 2; k++) {
						if(k == 0) {
							plys[1].changeCard(new Card(cards[idx]), 0);
							cards[idx] = -1;
							idx++;
						}else {
							plys[1].emptyCard(k);
						}
					}
					////System.out.println(1 + "!");
					
					addCard = false;
					currPlayer++;
				}else if(currPlayer == 2) {
					see2 = true;
					plys[2] = new Player(2);
					for(int m = 0; m < 2; m++) {
						if(m == 0) {
							plys[2].changeCard(new Card(cards[idx]), 0);
							cards[idx] = -1;
							idx++;
						}else {
							plys[2].emptyCard(m);
						}
					}
					initialCards = true;
					////System.out.println(2+ "!");
					addCard = false;
					start = 3;
					
					/*for(int i = 0; i < 3; i++) {
						////System.out.println(plys[i].toString());
					}
					for(int n = 0; n < cards.length; n++) {
						//System.out.print(cards[n] + ", ");
					}*/
				}
			}
			////System.out.println("plys length: " + plys.length);
			
																		////System.out.println(start);
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
			g.drawString("3. After clicking done, you are starting the game", 20, 220);
			g.drawString("4. follow the directions of the skiny black bar int the center", 20, 240);
			g.drawString("5. first player to 5 wins, wins the game", 20, 260);
			g.drawString("+ click refrence card to see it bigger/smaller", 20, 280);
			g.drawString("+ card 6 does not work correctly", 20, 295);
			//g.drawString("start with before starting the game", 20, 260);
			//g.drawString("start with before starting the game", 20, 275);
			
			if(seeRef) {
				////System.out.println("true");
				g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
			}else {
				g.drawImage(ref, 20, 20, 70, 112, null);
			}
			
			g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
			g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
			
			g.setColor(Color.yellow);
			g.drawString("Discarded (" + 1 + ")", getWidth()-90, 150);
			
			
			g.setColor(Color.WHITE);
			g.drawString("New Cards", getWidth()-90, 300);
			g.drawString("Cards: " + (cards.length - idx), getWidth()-90, 315);
			
			if(!seeRef) {
				g.drawString("1. each person draws a card from the \"new", 20, 150);
				g.drawString("cards\" deck", 20, 165);
				g.drawString("2. each person clicks to view the card they ", 20, 185);
				g.drawString("start with before starting the game", 20, 200);
				g.drawString("3. After clicking done, you are starting the game", 20, 220);
				g.drawString("4. follow the directions of the skiny black bar int the center", 20, 240);
				g.drawString("5. first player to 5 wins, wins the game", 20, 260);
				g.drawString("+ click refrence card to see it bigger/smaller", 20, 280);
				g.drawString("+ card 6 does not work correctly", 20, 295);
				//g.drawString("start with before starting the game", 20, 260);
				//g.drawString("start with before starting the game", 20, 275);
			}
			
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 120, getHeight()-175);
			
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
			g.fillRect(getWidth()/5 + 80, 50, getWidth()/5-40, 40);
			g.fillRect(getWidth()/5*2 + 80, 50, getWidth()/5-40, 40);
			g.fillRect(getWidth()/5*3 + 80, 50, getWidth()/5-40, 40);

			g.setColor(Color.black);
			g.drawString("Player 1", getWidth()/5 + 90, 70);
			g.drawString("Player 2", getWidth()/5*2 + 90, 70);
			g.drawString("Player 3", getWidth()/5*3 + 90, 70);
			
			for(int i = 0; i < wins.length; i++) {
				if(wins[i] > 0) {
					if(i == 0) {
						g.drawImage(token, getWidth()/4-40, getHeight()-200+(40*wins[i]), 30, 30, null);
					}else if(i ==1) {
						g.drawImage(token, getWidth()/2-60, getHeight()-200+(40*wins[i]), 30, 30, null);
					}else if(i == 2) {
						g.drawImage(token, getWidth()- (getWidth()/5) - 200+(40*wins[i]), getHeight()-150+(40*wins[i]), 30, 30, null);
					}
				}
			}
			
			
			if(see0) {
				
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5 + 80, 50, getWidth()/5-40, 40);
				g.setColor(Color.black);
				g.drawString("Player 1", getWidth()/5 + 90, 70);
				
				g.drawImage(plys[0].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}else if(see1) {
				
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5*2 + 80, 50, getWidth()/5-40, 40);
				g.setColor(Color.black);
				g.drawString("Player 2", getWidth()/5*2 + 90, 70);
				
				g.drawImage(plys[1].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}else if(see2) {
				
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5*3 + 80, 50, getWidth()/5-40, 40);
				g.setColor(Color.black);
				g.drawString("Player 3", getWidth()/5*3 + 90, 70);
				
				g.drawImage(plys[2].getCardPic(0), getWidth()/2, 110, 140, 225, null);
			}
			
			////System.out.println("cards: " + (cards.length-idx));
			
		}else if(start == 4) {
		if(startPlay == -1)startPlay = (int)(Math.random()*3);
		////System.out.println();
		////System.out.println("rand num start " + startPlay);
	
		//regualr graphics
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(), getHeight());
		
		g.drawString("1. each person draws a card from the \"new", 20, 150);
		g.drawString("cards\" deck", 20, 165);
		g.drawString("2. each person clicks to view the card they ", 20, 185);
		g.drawString("start with before starting the game", 20, 200);
		g.drawString("3. After clicking done, you are starting the game", 20, 220);
		g.drawString("4. follow the directions of the skiny black bar int the center", 20, 240);
		g.drawString("5. first player to 5 wins, wins the game", 20, 260);
		g.drawString("+ click refrence card to see it bigger/smaller", 20, 280);
		g.drawString("+ card 6 does not work correctly", 20, 295);
		//g.drawString("start with before starting the game", 20, 260);
		//g.drawString("start with before starting the game", 20, 275);
		
		if(seeRef) {
			////System.out.println("true");
			g.drawImage(ref, 20, 20, getWidth()/5, getHeight()-200, null);
		}else {
			g.drawImage(ref, 20, 20, 70, 112, null);
		}
		
		g.drawImage(b2, getWidth()-90, 20, 70, 112, null);
		g.drawImage(b1, getWidth()-90, 170, 70, 112, null);
		
		g.setColor(Color.yellow);
		g.drawString("Discarded (" + 1 + ")", getWidth()-90, 150);
		
		g.setColor(Color.WHITE);
		g.drawString("New Cards", getWidth()-90	, 300);
		int cnt = 0;
		for(int i = 0; i < cards.length; i++) {
			if(cards[i] != -1) {
				cnt++;
			}
		}
		g.drawString("Cards: " + cnt, getWidth()-90, 315);
		
		if(!seeRef) {
			g.drawString("1. each person draws a card from the \"new", 20, 150);
			g.drawString("cards\" deck", 20, 165);
			g.drawString("2. each person clicks to view the card they ", 20, 185);
			g.drawString("start with before starting the game", 20, 200);
			g.drawString("3. After clicking done, you are starting the game", 20, 220);
			g.drawString("4. follow the directions of the skiny black bar int the center", 20, 240);
			g.drawString("5. first player to 5 wins, wins the game", 20, 260);
			g.drawString("+ click refrence card to see it bigger/smaller", 20, 280);
			g.drawString("+ card 6 does not work correctly", 20, 295);
			//g.drawString("start with before starting the game", 20, 260);
			//g.drawString("start with before starting the game", 20, 275);
		}
		
		
		
		
		
		//player cards;
		g.drawImage(b1, getWidth()/4-10, getHeight()-150, 140, 225, null);
		g.drawImage(b1, getWidth()/2-20, getHeight()-150, 140, 225, null);
		g.drawImage(b1, getWidth()- (getWidth()/5) - 120, getHeight()-150, 140, 225, null);
		
		////System.out.println("Drawn: " + drawn);
		if(drawn && startPlay == 0) {
			see0 = true;
		}else if(drawn && startPlay == 1) {
			see1 = true;
		}else if(drawn && startPlay == 2) {
			see2 = true;
		}
		
		if(see0) {
			g.drawImage(b1, getWidth()/4+10, getHeight()-150, 140, 225, null);
			if(picked == 0 && card5) {
				if(done) {
					g.drawImage(b1, getWidth()/4+10, getHeight()-150, 140, 225, null);
				}
			}
		}else if(see1) {
			g.drawImage(b1, getWidth()/2, getHeight()-150, 140, 225, null);
			if(picked == 1 && card5) {
				if(done) {
					g.drawImage(b1, getWidth()- (getWidth()/5) - 100, getHeight()-150, 140, 225, null);
				}
			}
		}else if(see2) {
			g.drawImage(b1, getWidth()- (getWidth()/5) - 100, getHeight()-150, 140, 225, null);
			if(picked == 2 && card5) {
				if(done) {
					g.drawImage(b1, getWidth()- (getWidth()/5) - 100, getHeight()-150, 140, 225, null);
				}
			}
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
		
		//copy
		
		//Showing the cards that have already been played on screen
		/*
		 if(see0) {
			g.drawImage(b1, getWidth()/4+10, getHeight()-150, 140, 225, null);
		}else if(see1) {
			g.drawImage(b1, getWidth()/2, getHeight()-150, 140, 225, null);
		}else if(see2) {
			g.drawImage(b1, getWidth()- (getWidth()/5) - 100, getHeight()-150, 140, 225, null);
		}
		 */
		for(int j = 0; j < 3; j++) {
			if(played[j][0] != -1) {
				for(int i = 0; i < played[0].length; i++) {
					if(played[j][i] != -1) {
						if(j == 0) {
							g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()/4+10 + 140 + 20, getHeight()-180 + (30*i), 70, 112, null);
						}else if(j == 1) {
							g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()/2 + 140 + 15, getHeight()-180 + (30*i), 70, 112, null);
						}else if(j == 2) {
							g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()- (getWidth()/5) - 100 + 140 + 15, getHeight()-180 + (30*i), 70, 112, null);
						}
						
					}
				}
			}
		}
		
		
		if(startPlay > -1 && chosen && play && !out && !plys[startPlay].isEmpty(chosenCard) && !card6) {
			//System.out.println("CHOSEN CARD: " + plys[startPlay].getType(chosenCard));
			if(plys[startPlay].getType(chosenCard) != 6) {
				g.setColor(Color.red);
				if(chosenCard == 0) {
					g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
				}else if(chosenCard == 1) {
					g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
				}
			}
			
			g.setColor(Color.white);
			//if(startPlay > -1 && !plys[startPlay].isEmpty(chosenCard))//System.out.println("Chosen Card: " + text.getText(plys[startPlay].getType(chosenCard)));
			if(compareWin < 0 && !othersSafe && guessed < 0 && !guess && !maid && startPlay > -1 && !plys[startPlay].isEmpty(chosenCard)) {
				g.drawString(text.getText(plys[startPlay].getType(chosenCard)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
			}else if(!guess && maid){
				//System.out.println("hi maid");
				g.setColor(Color.white);
				g.drawString((text.getText(-3)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
			}else if(guess) {
				g.setColor(Color.white);
				g.drawString((text.getText(-6)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
				if(picked == 0) {
					out0 = true;
				}else if(picked == 1) {
					out1 = true;
				}else if(picked == 2) {
					out2 = true;
				}
			}else if (!guess && guessed > -1) {
				g.setColor(Color.white);
				g.drawString((text.getText(-7)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
			}else if(othersSafe) {
				g.setColor(Color.white);
				g.drawString((text.getText(-5)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
			}else if(compareWin > -1) {
				g.setColor(Color.white);
				if(compareWin == 0) {
					g.drawString((text.getText(-8)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
					if(picked == 0) {
						out0 = true;
					}else if(picked == 1) {
						out1 = true;
					}else if(picked == 2) {
						out2 = true;
					}
				}else if(compareWin == 1) {
					g.drawString((text.getText(-9)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
					if(startPlay == 0) {
						out0 = true;
					}else if(startPlay == 1) {
						out1 = true;
					}else if(startPlay == 2) {
						out2 = true;
					}
				}else if(compareWin == 2) {
					g.drawString((text.getText(-10)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
				}
			}
			
			if(plys[startPlay].getType(chosenCard) == 1 || plys[startPlay].getType(chosenCard) == 2 || plys[startPlay].getType(chosenCard) == 3 || plys[startPlay].getType(chosenCard) == 7) {
				g.setColor(Color.gray);
				////System.out.println("Chosen Card: " + plys[startPlay].getType(chosenCard));
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
				g.setColor(Color.yellow);
				if(picked != -1) {
					switch(picked) {
					case 0:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
						break;
					case 1:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
						break;
					case 2:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
						break;
					}
				}
				g.setColor(Color.black);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
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
				
				
				
				if(picked != -1 && chosenCard != 5 && (plys[startPlay].getType(chosenCard) == 2 || plys[startPlay].getType(chosenCard) == 3)) {
					//g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
					g.drawImage(plys[picked].getCardPic(0), getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
				}
			}
			
			if(card5) {
				g.setColor(Color.gray);
				g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
				g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
				g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
				
				g.setColor(Color.yellow);
				if(picked != -1) {
					switch(picked) {
					case 0:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
						break;
					case 1:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
						break;
					case 2:
						g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
						break;
					}
				}
				
				g.setColor(Color.black);
				g.drawString("Player 1", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 130);
				g.drawString("Player 2", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 180);
				g.drawString("Player 3", getWidth()/5 + 115 + ((getWidth()-300-getWidth()/5)/6)*2 + 30 + (getWidth()-220-getWidth()/5)/6, 230);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
				g.drawRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
			}
			
			//g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			
			if(plys[startPlay].getType(chosenCard) == 1) {
				
				for(int i = 0; i < 9; i++) {
					g.setColor(Color.gray);
					g.fillRect(getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20, 120 + (32*i), (getWidth()-380-getWidth()/5)/6, 20);
					if(i >= 1) {
						////System.out.println("I: " + i);
						////System.out.println(guessed);
						if(guessed != 0 && guessed == (i+1)) {
							g.setColor(Color.yellow);
							g.fillRect(getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20, 120 + (32*i), (getWidth()-380-getWidth()/5)/6, 20);
						}
						g.setColor(Color.black);
						g.drawString("Card " + (i+1), getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20 + 10, 120 + (32*i) + 15);
					}else {
						if(guessed == 0) {
							g.setColor(Color.yellow);
							g.fillRect(getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20, 120 + (32*i), (getWidth()-380-getWidth()/5)/6, 20);
						}
						g.setColor(Color.black);
						g.drawString("Card " + i, getWidth()/5 + 120 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 34 + getWidth()/20 + 10, 120 + (32*i) + 15);
					}
					
				}
			}
			
		}else if(countess){
			g.setColor(Color.white);
			g.drawString((text.getText(-2)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
		}else if(out){
			//System.out.println("hi out");
			g.setColor(Color.white);
			g.drawString((text.getText(-4)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
		}else {
			g.setColor(Color.white);
			g.drawString((text.getText(-1)), 40 + getWidth()/5 + 100, 440 + getHeight()/20);
		}
		
		
		
		if(startPlay > -1 && !plys[startPlay].isEmpty(0) && see && !card6) {
			g.drawImage(plys[startPlay].getCardPic(0), 9 + getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			if(!(plys[startPlay].isEmpty(1))) {
				g.drawImage(plys[startPlay].getCardPic(1), 9 + getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);

			}
		}
		
		
		if(startPlay != -1 && chosenCard != -1 && startPlay > -1 && chosenCard > -1 && card6) {
			g.setColor(Color.black);
			if(card6 && chosenCard == 0) {
				g.setColor(Color.yellow);
			}
			g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			g.setColor(Color.black);
			if(card6 && chosenCard == 1) {
				g.setColor(Color.yellow);
			}
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			g.setColor(Color.black);
			if(card6 && chosenCard == 2) {
				g.setColor(Color.yellow);
				g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			}
			if(chosenCard == 0 && !plys[startPlay].isEmpty(1)) {
				g.drawImage(plys[startPlay].getCardPic(1), 9 + getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			}else if(chosenCard == 1 && !plys[startPlay].isEmpty(0)) {
				g.drawImage(plys[startPlay].getCardPic(0), 9 + getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			}
			if(!drawnCards.isEmpty(0)) {
				g.drawImage(drawnCards.getCardPic(0), 9 + getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			}
			if(!drawnCards.isEmpty(1)) {
				g.drawImage(drawnCards.getCardPic(1), getWidth()/5 + 109 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			}
		}
		
		
		g.setColor(Color.black);
		if(out0)g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
		if(out1)g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
		if(out2)g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
		
		
		if(startPlay > -1 && drawn) {
			plys[startPlay].changeCard(new Card(cards[idx]), 1);
			cards[idx] = -1;
			idx++;
			drawn = false;
			play = true;
			repaint();
		}
		
		if(out0) {
			g.setColor(Color.red);
			g.drawString("Player 1 (OUT)", getWidth()/4 - 10, getHeight()-175);
			g.drawImage(broke, getWidth()/4-10, getHeight()-150, 160, 225, null);
			
			
		}else {
			g.setColor(Color.WHITE);
			g.drawString("Player 1", getWidth()/4 - 10, getHeight()-175);
		}
		if(out1) {
			g.setColor(Color.red);
			g.drawString("Player 2 (OUT)", getWidth()/2 - 20, getHeight()-175);
			g.drawImage(broke, getWidth()/2-20, getHeight()-150, 160, 225, null);
		}else {
			g.setColor(Color.WHITE);
			g.drawString("Player 2", getWidth()/2 - 20, getHeight()-175);
		}
		if(out2) {
			g.setColor(Color.red);
			g.drawString("Player 3 (OUT)", getWidth() - (getWidth()/5) - 120, getHeight()-175);
			g.drawImage(broke, getWidth()- (getWidth()/5) - 120, getHeight()-150, 160, 225, null);
		}else {
			g.setColor(Color.WHITE);
			g.drawString("Player 3", getWidth() - (getWidth()/5) - 120, getHeight()-175);
		}
		
		
		//paste
		if(done && card5) {
			g.fillRect(getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 40 + (getWidth()-220-getWidth()/5)/6 + 40 + getWidth()/20, 110, (getWidth()-220-getWidth()/5)/6, 260 + getHeight()/20);
			
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 110, 40 + getWidth()/20, 30);
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 160, 40 + getWidth()/20, 30);
			g.fillRect(getWidth()/5 + 100 + ((getWidth()-300-getWidth()/5)/6)*2 + 20 + (getWidth()-220-getWidth()/5)/6, 210, 40 + getWidth()/20, 30);
			
			g.setColor(Color.black);
			g.fillRect(getWidth()/4-10, getHeight()-150, 160, 225);
			g.fillRect(getWidth()/2-40, getHeight()-150, 160, 225);
			g.fillRect(getWidth()- (getWidth()/5) - 100, getHeight()-150, 160, 225);
			
			//g.drawImage(b1, getWidth()/4-10, getHeight()-150, 140, 225, null);
			//g.drawImage(b1, getWidth()/2-20, getHeight()-150, 140, 225, null);
			//g.drawImage(b1, getWidth()- (getWidth()/5) - 120, getHeight()-150, 140, 225, null);
			
			
			
			g.drawImage(plys[startPlay].getCardPic(0), 9 + getWidth()/5 + 60 + ((getWidth()-300-getWidth()/5)/6), 120, (getWidth()-330-getWidth()/5)/6, 240 + getHeight()/20, null);
			
			for(int j = 0; j < 3; j++) {
				if(played[j][0] != -1) {
					for(int i = 0; i < played[0].length; i++) {
						if(played[j][i] != -1) {
							if(j == 0) {
								g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()/4+10 + 140 + 20, getHeight()-180 + (30*i), 70, 112, null);
							}else if(j == 1) {
								g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()/2 + 140 + 15, getHeight()-180 + (30*i), 70, 112, null);
							}else if(j == 2) {
								g.drawImage(plys[j].getPlayerPic(played[j][i]), getWidth()- (getWidth()/5) - 100 + 140 + 15, getHeight()-180 + (30*i), 70, 112, null);
							}
							
						}
					}
				}
			}
			g.fillRect(20 + getWidth()/5 + 100, 410 + getHeight()/20, getWidth()-300-getWidth()/5, 50);
			g.setColor(Color.red);
			//g.fillRect(20 + getWidth()/5 + 100, 410 + getHeight()/20, getWidth()-300-getWidth()/5, 50); 
			g.fillRect(20 + getWidth()/5 + 100 + getWidth()/2, 410 + getHeight()/20 + 10, getWidth()-(20 + getWidth()/5 + 100 + getWidth()/2) - 195, 30);
			g.setColor(Color.black);
			g.drawString("Click When Done", 20 + getWidth()/5 + 115 + getWidth()/2, 410 + getHeight()/20 + 30);
		}
		
		if(done && !card5) {
			if(finished && !card6 && plys[startPlay].getType(chosenCard)!= 5) {
				//System.out.println("Chosen: " + plys[startPlay].getType(chosenCard) + " done");
				for(int i = 0; i < played[startPlay].length; i++) {
					if(played[startPlay][i] == -1) {
						played[startPlay][i] = plys[startPlay].getType(chosenCard);
						break;
					}
				}
			}
			
			
			
			/*//System.out.println("Played: ");
			for(int i = 0; i < played.length; i++) {
				for(int j = 0; j < played.length; j++) {
					//System.out.print(played[i][j] + ", ");
				}
				//System.out.println();
			}
			//System.out.println();*/
			//plys[startPlay].changeCard(new Card(plys[startPlay].getType(chosenCard)), 0);
			//plys[startPlay].emptyCard(1);
			if(!card5) {
				if(card5 || card6) {
					g.setColor(Color.black);
					g.fillRect(20 + getWidth()/5 + 100, 410 + getHeight()/20, getWidth()-300-getWidth()/5, 50);
				}
				
				g.setColor(Color.red);
				//g.fillRect(20 + getWidth()/5 + 100, 410 + getHeight()/20, getWidth()-300-getWidth()/5, 50); 
				g.fillRect(20 + getWidth()/5 + 100 + getWidth()/2, 410 + getHeight()/20 + 10, getWidth()-(20 + getWidth()/5 + 100 + getWidth()/2) - 195, 30);
				g.setColor(Color.black);
				g.drawString("Click When Done", 20 + getWidth()/5 + 115 + getWidth()/2, 410 + getHeight()/20 + 30);
			}
			
			
		}
		
		if(finished) {
			//plys[startPlay].getType(chosenCard);
			if(card5) {
				//System.out.println("ISTG PLEASE WORK");
			}else if(card6) {
				//
			}else if(plys[startPlay].getType(chosenCard) == 7) {
				if(chosenCard == 0) {
					plys[startPlay].changeCard(new Card(plys[startPlay].getType(1)), 0);
				}
				plys[startPlay].emptyCard(1);
			}else if(chosenCard == 0 && !drawn) {
				plys[startPlay].changeCard(new Card(plys[startPlay].getType(1)), 0);
				plys[startPlay].emptyCard(1);
			}else if(chosenCard == 1 && !drawn) {
				plys[startPlay].emptyCard(1);
			}else if(chosenCard == 0 && drawn) {
				Card tempCard = new Card(plys[startPlay].getType(1));
				plys[startPlay].changeCard(tempCard, 0);
				plys[startPlay].emptyCard(1);
			}else if(chosenCard == 1 && drawn) {
				plys[startPlay].emptyCard(1);
			}
			reset();
		}
	}else if(start == 5) {
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(), getHeight());
		g.setColor(Color.black);
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 5);
		g.setFont(newFont);
		if(fullWin >= 0) {
			g.drawString("Winner is player " + (fullWin +1), getWidth()/2-10, getHeight()/2-100);
		}else if(fullWin == -4) {
			g.drawString("They all Win", getWidth()/2-10, getHeight()/2-100);
		}else if(fullWin == -3) {
			g.drawString("Players 2 and 3 win", getWidth()/2-10, getHeight()/2-100);
		}else if(fullWin == -2) {
			g.drawString("Players 1 and three win", getWidth()/2-10, getHeight()/2-100);
		}else if(fullWin == -1) {
			g.drawString("Players 1 and 2 win", getWidth()/2-10, getHeight()/2-100);
		}
		
	}
		
	}
	
	public void playEvent(int event) {
		
	}
	
	public void reset() {
		//System.out.println("reseting");
		if(out0 && out1) {
			tempWin = 2;
		}else if(out1 && out2) {
			tempWin = 0;
		}else if(out0 && out2) {
			tempWin = 1;
		}
		if(idx >= cards.length && cards[0] == -1) {
			if(!out0 && plys[0].getType(0) > plys[1].getType(0) && plys[0].getType(0) > plys[2].getType(0)) {
				tempWin = 0;
			}else if(!out0 && plys[1].getType(0) > plys[0].getType(0) && plys[1].getType(0) > plys[2].getType(0)) {
				tempWin = 1;
			}else if(!out0 && plys[2].getType(0) > plys[0].getType(0) && plys[2].getType(0) > plys[1].getType(0)) {
				tempWin = 2;
			}
		}
		if(tempWin != -1) {
			fullReset();
		}
			
			
			
			startPlay = (startPlay + 1)%3;
			if(startPlay == 0 && out0) {
				startPlay = (startPlay + 1)%3;
			}
			if(startPlay == 1 && out1) {
				startPlay = (startPlay + 1)%3;
			}
			if(startPlay == 2 && out2) {
				startPlay = (startPlay + 1)%3;
			}
			
			if(safe0 && startPlay == 0) {
				safe0 = false;
				if(!safe1 || !safe2) {
					maid = false;
				}
			}else if(safe1 && startPlay == 1) {
				safe1 = false;
				if(!safe0 || !safe2) {
					maid = false;
				}
			}else if(safe2 && startPlay == 2) {
				safe2 = false;
				if(!safe0 || !safe1) {
					maid = false;
				}
			}
			
			see = false;
			
			drawn = false;
			
			chosen = false;;
			chosenCard = -1;
			card5 = false; card6 = false;
			done = false;
			finished = false;
			
			countess = false; 
			picked = -1;
			guessed = -1; guess = false;
			
			play = false;
			
			see0 = false; see1 = false; see2 = false; othersSafe = false;
			compareWin = -1;
			
			drawn = false;
			drawnCards.emptyCard(0);
			drawnCards.emptyCard(1);
			
			repaint();
		}
	
	public void fullReset() {
		//System.out.println("Full Reset");
		if(tempWin == 0) {
			wins[0]++;
		}else if(tempWin == 1) {
			wins[1]++;
		}else if(tempWin == 2) {
			wins[2]++;
		}
		
		int spy0 = 0; int spy1 = 0; int spy2 = 0;
		
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[0].length; j++) {
				if(played[i][j] == 0) {
					spy0++;
				}
			}
		}
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[1].length; j++) {
				if(played[i][j] == 0) {
					spy1++;
				}
			}
		}
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[2].length; j++) {
				if(played[i][j] == 0) {
					spy2++;
				}
			}
		}
		if(spy0 > 0 && spy1 == 0 && spy2 == 0) {
			wins[0] += spy0;
		}else if(spy0 == 0 && spy1 > 0 && spy2 == 0) {
			wins[1] += spy1;
		}else if(spy0 == 0 && spy1 == 0 && spy2 > 0) {
			wins[2] += spy2;
		}
		
		
		//start = 0;
		//addMouseListener(this);
		
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
		
		out0 = false;
		out1 = false;
		out2 = false;
		
		
		discarded = false;
		
		
		//for the game
		startPlay = tempWin;
		
		see = false;
		
		text = new LLText();
		drawn = false;
		
		chosen = false;;
		chosenCard = -1;
		
		card5 = false;
		done = false;
		safe0 = false; safe1 = false; safe2 = false; othersSafe = false;
		
		for(int i = 0; i < played.length; i++) {
			for(int j = 0; j < played[0].length; j++) {
				played[i][j] = -1;
			}
		}
		
		countess = false; maid = false; out = false;
		picked = -1;
		play = false;
		guessed = -1; guess = false;
		compareWin = -1;
		if(wins[0] >= 2 || wins[1] >= 2 || wins[2] >= 2) {
			start = 5;
			if(wins[0] == wins[1] && wins[0] == wins[2]) {
				fullWin = -4; //all win
			}else if(wins[0] == wins[1] && wins[0] > wins[2]) {
				fullWin = -1; //0 and 1 win
			}else if(wins[1] == wins[2] && wins[1] > wins[0]) {
				fullWin = -3; //1 and 2 win
			}else if(wins[0] == wins[2] && wins[0] > wins[1]) {
				fullWin = -2; //0 and 2 win
			}else if(wins[0] > wins[1] && wins[0] > wins[2]) {
				fullWin = 0; //0 wins
			}else if(wins[1] > wins[0] && wins[1] > wins[2]) {
				fullWin = 1; //2 wins
			}else if(wins[2] > wins[0] && wins[2] > wins[1]) {
				fullWin = 2; //3 wins
			}
			
		}
	}
	
}
