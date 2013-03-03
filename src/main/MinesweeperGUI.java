package main;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MinesweeperGUI extends JFrame
{
	private static final long serialVersionUID = 42L;
	private Container container;
	private Board board;
	private JMenuBar menuBar;
	
	public MinesweeperGUI()
	{
		super();
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		board = new Board(10,10);
		container.add(board, BorderLayout.CENTER);
		createMenus();
		this.pack();
		this.setVisible(true);
	}
	
	private void createMenus()
	{
		menuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");
		
		menuBar.add(game);
		menuBar.add(help);
		this.setJMenuBar(menuBar);
	}

}
