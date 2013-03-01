package main;

import java.awt.BorderLayout;
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
		container.setLayout(new BorderLayout());
		board = new Board(10,10);
		container.add(board, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

}
