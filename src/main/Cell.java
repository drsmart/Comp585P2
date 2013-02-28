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
	private int adjCount;
	private Image bgImg;
	
	public Cell(int x, int y)
	{
		row = x;
		col = y;
		JLabel l = new JLabel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("images/blank.gif"));
		width = img.getIconWidth();
		height = img.getIconHeight();
		bgImg = img.getImage();
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
	public void paintComponent(Graphics g)
	{
		if (bgImg != null)
			g.drawImage(bgImg, 0, 0, this);
	}
}
