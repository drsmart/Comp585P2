package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cell extends JPanel 
{
	private int row;
	private int col;
	private int width;
	private int height;
	private boolean mined;
	private boolean covered;
	private int adjCount;
	private Image bgImg;
	
	public Cell(int x, int y)
	{
		row = x;
		col = y;
		mined = false;
		covered = true;
		ImageIcon img = new ImageIcon(this.getClass().getResource("images/blank.gif"));
		width = img.getIconWidth();
		height = img.getIconHeight();
		bgImg = img.getImage();
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
	public void setMined(boolean mined)
	{
		this.mined = mined;
	}
	
	public void paintComponent(Graphics g)
	{
		if (bgImg != null)
			g.drawImage(bgImg, 0, 0, this);
	}
	
	public void uncover()
	{
		String pic = "";
		covered = false;
		if (mined && !covered)
			pic = "bombdeath.gif";
		else 
			pic = "open0.gif";
		
		bgImg = (new ImageIcon(this.getClass().getResource("images/" + pic))).getImage();
	}
	
	public void mark()
	{
		
	}
	
	public void pressed()
	{
		bgImg = (new ImageIcon(this.getClass().getResource("images/open0.gif"))).getImage();
	}
}
