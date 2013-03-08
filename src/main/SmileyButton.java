package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SmileyButton extends JLabel
{
	private static final String SMILE = "facesmile.gif";
	private static final String FACEOOH = "faceooh.gif";
	private static final String FACEDEAD = "facedead.gif";
	private static final String FACEWIN = "facewin.gif";
	private static final String PREFIX = "images/";
	private boolean pressed; 
	
	public SmileyButton()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(PREFIX + SMILE)));
		pressed = false;
	}
	
	public void reset()
	{
		this.setIcon(new ImageIcon(this.getClass().getResource(PREFIX + SMILE)));
	}
	
	public void minePressed()
	{
		pressed = !pressed;
		if (pressed)
			setIcon(new ImageIcon(this.getClass().getResource(PREFIX + FACEOOH)));
		else
			setIcon(new ImageIcon(this.getClass().getResource(PREFIX + SMILE)));
		
	}

	public void gameOver(boolean won)
	{
		if (won)
			setIcon(new ImageIcon(this.getClass().getResource(PREFIX + FACEWIN)));
		else
			setIcon(new ImageIcon(this.getClass().getResource(PREFIX + FACEDEAD)));
		repaint();
	}
}
