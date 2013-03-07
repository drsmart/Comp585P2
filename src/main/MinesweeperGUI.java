package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MinesweeperGUI extends JFrame
{
	private static final long serialVersionUID = 42L;
	private Container container;
	private Board board;
	private SmileyButton smiley;
	private JMenuBar menuBar;
	private GameManager listener;
	private Counter counter;
	private JPanel north;
	
	public MinesweeperGUI(GameManager manager)
	{
		super();
		north = new JPanel(new GridBagLayout());
		listener = manager;
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		board = new Board(10,10, listener);
		container.add(board, BorderLayout.CENTER);
		container.add(north, BorderLayout.NORTH);
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
		GridBagConstraints c = new GridBagConstraints();
		
		counter = new  Counter(10);
		smiley = new SmileyButton();
		smiley.setBorder(BorderFactory.createEmptyBorder());
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		north.add(counter, c);
		
		c.gridx = 1;
		
		north.add(smiley, c);
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
	
	public void markCell()
	{
		counter.decrement();
	}
	
	public void unMarkCell()
	{
		counter.increment();
	}
	
	
}
