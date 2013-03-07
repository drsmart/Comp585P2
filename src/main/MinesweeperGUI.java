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
	
	private static final int BEGINNERSIZE = 8;
	private static final int INTERMEDIATE = 16;
	private static final int EXPERTROWS = 16;
	
	private static final int BEGINNERMINECOUNT = 10;
	private static final int INTERMEDIATEMINECOUNT = 40;
	private static final int EXPERTMINECOUNT = 99;
	
	private Container container;
	private Board board;
	private SmileyButton smiley;
	private JMenuBar menuBar;
	private GameManager listener;
	private Counter counter;
	private TimerLabel timer;
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
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
		
		timer = new TimerLabel();
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
		
		c.gridx = 2;
		
		north.add(timer, c);
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

	public void startTimer()
	{
		timer.startTimer();
	}
	public void stopTimer()
	{
		timer.stopTimer();
	}	
}
