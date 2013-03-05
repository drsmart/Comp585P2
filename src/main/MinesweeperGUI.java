package main;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MinesweeperGUI extends JFrame
{
	private static final long serialVersionUID = 42L;
	private Container container;
	private Board board;
	private SmileyButton smiley;
	private JMenuBar menuBar;
	private GameManager listener;
	
	public MinesweeperGUI(GameManager manager)
	{
		super();
		listener = manager;
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		board = new Board(10,10, listener);
		container.add(board, BorderLayout.CENTER);
		createMenus();
		createButton();
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
	
	private void createButton()
	{
		smiley = new SmileyButton();
		smiley.setBorder(BorderFactory.createEmptyBorder());
		container.add(smiley, BorderLayout.NORTH);
	}
	
	public void setButton()
	{
		smiley.minePressed();
	}

	public Board getBoard()
	{
		return board;
	}
	
	public void gameOver(boolean won)
	{
		smiley.gameOver(won);
	}

}
