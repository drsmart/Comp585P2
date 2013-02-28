package main;

import java.awt.Container;

import javax.swing.JFrame;

public class MinesweeperGUI extends JFrame
{
	private static final long serialVersionUID = 42L;
	private Container container;
	
	public MinesweeperGUI()
	{
		super();
		container = this.getContentPane();
		
		this.pack();
		this.setVisible(true);
	}

}
