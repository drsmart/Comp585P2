package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JLabel topR;
	private JLabel topL;
	private JLabel bottomR;
	private JLabel bottomL;
	private JLabel horizontal;
	private JLabel vertical;
	
	public MinesweeperGUI(GameManager manager)
	{
		super();
		setLayout(new GridBagLayout());
		north = new JPanel(new GridBagLayout());
		listener = manager;
		container = this.getContentPane();
		board = new Board(10,10, listener);
		createMenus();
		createButton();
		layoutComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	private void layoutComponents()
	{
		topR = new JLabel(new ImageIcon(this.getClass().getResource("images/bordertr.gif")));
		topL = new JLabel(new ImageIcon(this.getClass().getResource("images/bordertl.gif")));
		bottomR = new JLabel(new ImageIcon(this.getClass().getResource("images/borderbr.gif")));
		bottomL = new JLabel(new ImageIcon(this.getClass().getResource("images/borderbl.gif")));
		vertical = new JLabel(new ImageIcon(this.getClass().getResource("images/borderlr.gif")));
		horizontal = new JLabel();
		
		horizontal.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
//		c.weightx = 1;
//		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		container.add(topL, c);
		
		c.gridx = 2;
		container.add(topR, c);
		
		c.gridx = 0;
		c.gridy = 4;
		
		container.add(bottomL, c);
		c.gridx = 2;
		container.add(bottomR, c);
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;

		JPanel t = new JPanel();
		t.setBackground(Color.white);
		
		c.gridx = 1;
		c.gridy = 1;
		container.add(north, c);
		
		c.gridy = 3;
		container.add(board, c);
		
		c.gridx = 0;
		c.gridy = 2;
		container.add(new JLabel(new ImageIcon(this.getClass().getResource("images/borderjointl.gif"))), c);
		
		c.gridx = 2;
		container.add(new JLabel(new ImageIcon(this.getClass().getResource("images/borderjointr.gif"))), c);
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
		
		smiley.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				listener.newGame();
			}
		});
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
		board.gameOver(won);
	}
	
	public void newGame()
	{
		board.newGame();
		timer.reset();
		smiley.reset();
		this.repaint();
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
