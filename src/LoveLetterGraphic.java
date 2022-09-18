import java.awt.*;
import javax.swing.*;

public class LoveLetterGraphic extends JFrame{
	//public static final int WIDTH = 1200;
	//private static int HEIGHT = 600;
	
	public static final int WIDTH = 1650;
	private static int HEIGHT = 900;
	
	
	public LoveLetterGraphic(String framename) {
		super(framename);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add(new DrawingLLCardsPanel());
		add(new LoveLetterGraphicPanel());
		setVisible(true);
	}
}
