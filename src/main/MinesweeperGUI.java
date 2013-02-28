package main;

import java.awt.Container;

import javax.swing.JFrame;

public class MinesweeperGUI extends JFrame
{
	private static final long serialVersionUID = 42L;
	private Container container;
	private Board board;
	
	public MinesweeperGUI()
	{
		super();
		container = this.getContentPane();
		board = new Board(10,10);
		this.add(board);
		this.pack();
		this.setVisible(true);
	}

}
