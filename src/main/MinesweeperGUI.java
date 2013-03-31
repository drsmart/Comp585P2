package main;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

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
	private JLabel horizontalTop;
	private JLabel verticalR;
	
	private int rows;
	private int cols;
	private int mineCount;
	
	private MenuListener menuListener;

	private JLabel horizontalMid;

	private JLabel horizontalBottom;

	private JLabel verticalL;
	
	public MinesweeperGUI(GameManager manager, MenuListener menuListener)
	{
		super();
		setLayout(new GridBagLayout());
		this.menuListener = menuListener;
		north = new JPanel(new GridBagLayout());
		listener = manager;
		container = this.getContentPane();
		
		rows = BEGINNERSIZE;
		cols = BEGINNERSIZE;
		mineCount = BEGINNERMINECOUNT;
		
		board = new Board(rows, cols, mineCount, listener);
		this.setVisible(true);
		createMenus();
		createTimerBar();
		layoutComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.pack();
	}
	
	/**
	 * Initialize JLabels that represent the borders around the game board
	 */
	private void createBorder()
	{
		topR = new JLabel(new ImageIcon(this.getClass().getResource("images/bordertr.gif")));
		topL = new JLabel(new ImageIcon(this.getClass().getResource("images/bordertl.gif")));
		bottomR = new JLabel(new ImageIcon(this.getClass().getResource("images/borderbr.gif")));
		bottomL = new JLabel(new ImageIcon(this.getClass().getResource("images/borderbl.gif")));
				
		Image t = (new ImageIcon(this.getClass().getResource("images/bordertb.gif"))).getImage();
		t = t.getScaledInstance(board.getWidth(), t.getHeight(this), java.awt.Image.SCALE_FAST);
		horizontalTop = new JLabel(new ImageIcon(t));
		horizontalMid = new JLabel(new ImageIcon(t));
		horizontalBottom = new JLabel(new ImageIcon(t));
		
		t =(new ImageIcon(this.getClass().getResource("images/borderlr.gif"))).getImage(); 
		t = t.getScaledInstance(t.getWidth(this), board.getHeight(), java.awt.Image.SCALE_FAST);
		verticalR = new JLabel(new ImageIcon(t));
		verticalL = new JLabel(new ImageIcon(t));
	}
	
	/**
	 * Position GUI components
	 */
	private void layoutComponents()
	{
		createBorder();
		
		GridBagConstraints c = new GridBagConstraints();
		
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
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 1;
		c.gridy = 1;
		container.add(north, c);
		
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		container.add(board, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		container.add(new JLabel(new ImageIcon(this.getClass().getResource("images/borderjointl.gif"))), c);
		
		c.gridx = 2;
		container.add(new JLabel(new ImageIcon(this.getClass().getResource("images/borderjointr.gif"))), c);
		
		c.gridx = 1;
		c.gridy = 0;
		
		container.add(horizontalTop, c);
		c.gridy = 2;
		container.add(horizontalMid, c);
		c.gridy = 4;
		container.add(horizontalBottom, c);
		c.gridx = 0;
		c.gridy = 3;
		container.add(verticalL, c);
		c.gridx = 2;
		container.add(verticalR, c);
		
		
		Image t =(new ImageIcon(this.getClass().getResource("images/borderlr.gif"))).getImage(); 
		t = t.getScaledInstance(t.getWidth(this), 26, java.awt.Image.SCALE_FAST);
		
		c.gridx = 0;
		c.gridy = 1;
		container.add(new JLabel(new ImageIcon(t)), c);
		
		c.gridx = 2;
		container.add(new JLabel(new ImageIcon(t)), c);
		
	}
	
	/**
	 * Resize the game board border when the window size changes
	 */
	private void resizeBorder()
	{
		Image t = (new ImageIcon(this.getClass().getResource("images/bordertb.gif"))).getImage();
		t = t.getScaledInstance(board.getWidth(), t.getHeight(this), java.awt.Image.SCALE_FAST);
		horizontalTop.setIcon(new ImageIcon(t));
		horizontalMid.setIcon(new ImageIcon(t));
		horizontalBottom.setIcon(new ImageIcon(t));
		
		t =(new ImageIcon(this.getClass().getResource("images/borderlr.gif"))).getImage(); 
		t = t.getScaledInstance(t.getWidth(this), board.getHeight(), java.awt.Image.SCALE_FAST);
		verticalR.setIcon(new ImageIcon(t));
		verticalL.setIcon(new ImageIcon(t));
	}
	
	/**
	 * Create the menus
	 */
	private void createMenus()
	{
		menuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");
		
		JMenuItem newGame = new JMenuItem("New");
		JMenuItem exit = new JMenuItem("Exit");
		
		
		JRadioButtonMenuItem beginner = new JRadioButtonMenuItem("Beginner");
		JRadioButtonMenuItem intermediate = new JRadioButtonMenuItem("Intermediate");
		JRadioButtonMenuItem expert = new JRadioButtonMenuItem("Expert");
		
		beginner.setSelected(true);
		beginner.setActionCommand("Beginner");
		intermediate.setActionCommand("Intermediate");
		expert.setActionCommand("Expert");
		

		ButtonGroup group = new ButtonGroup();
		group.add(beginner);
		group.add(intermediate);
		group.add(expert);
		
		// Add Game menu items
		game.add(newGame);
		game.add(beginner);
		game.add(intermediate);
		game.add(expert);
		game.add(exit);
		
		//Add listener to menu items
		newGame.addActionListener(menuListener);
		beginner.addActionListener(menuListener);
		intermediate.addActionListener(menuListener);
		expert.addActionListener(menuListener);
		exit.addActionListener(menuListener);
		
		menuBar.add(game);
		menuBar.add(help);
		
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Create the timer, counter bar
	 */
	private void createTimerBar()
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
		c.anchor = GridBagConstraints.WEST;
		
		north.add(counter, c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		north.add(smiley, c);
		
		c.gridx = 2;
		c.anchor = GridBagConstraints.EAST;
		north.add(timer, c);
	}
	
	/**
	 * Sets the smiley face button to mine pressed state
	 */
	public void setButton()
	{
		smiley.minePressed();
	}
	
	/**
	 * Returns the game board
	 * @return the game board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Handles the game over event
	 * @param won whether the player won or not
	 */
	public void gameOver(boolean won)
	{
		smiley.gameOver(won);
		board.gameOver(won);
	}
	
	/**
	 * Sets the difficulty of the game
	 * @param difficulty the difficulty to set the game to
	 */
	public void setDifficulty(String difficulty)
	{
		switch (difficulty)
		{
			case "Beginner":
				rows = cols = BEGINNERSIZE;
				mineCount = BEGINNERMINECOUNT;
				break;
				
			case "Intermediate":
				rows = cols = INTERMEDIATE;
				mineCount = INTERMEDIATEMINECOUNT;
				break;
				
			case "Expert":
				rows = EXPERTROWS;
				cols = 2 * rows;
				mineCount = EXPERTMINECOUNT;
				break;
		}
		
		board.setDifficulty(rows, cols, mineCount);
		resizeBorder();
		newGame();
		this.pack();
	}
	
	/**
	 * Reset GUI components and start a new game
	 */
	public void newGame()
	{
		board.newGame();
		timer.reset();
		smiley.reset();
		counter.reset(mineCount);
		this.repaint();
	}
	
	/**
	 * Decrement mine counter
	 */
	public void markCell()
	{
		counter.decrement();
	}
	
	/**
	 * Increment mine counter
	 */
	public void unMarkCell()
	{
		counter.increment();
	}

	/**
	 * Start the game timer
	 */
	public void startTimer()
	{
		timer.startTimer();
	}
	
	/**
	 * Stop the game timer
	 */
	public void stopTimer()
	{
		timer.stopTimer();
	}	
}
