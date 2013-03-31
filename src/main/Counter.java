package main;

import java.awt.FlowLayout;
import javax.swing.*;

public class Counter extends JPanel
{
	protected JLabel hundreds;
	protected JLabel tens;
	protected JLabel ones;
	protected ImageIcon[] numbers;
	private ImageIcon negative;
	int h;
	int t;
	int o;
	protected int numSelected;
	private int maxMines;
	
	public Counter()
	{
		initLabel(0);
	}
	
	public Counter(int numOfMines)
	{
		initLabel(numOfMines);
	}

	/**
	 * Initialize counter components
	 * @param numMines the counter value
	 */
	private void initLabel(int numMines)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		maxMines = numSelected = numMines;
		numbers = new ImageIcon[10];
		initDisplay(numSelected);
		initNumbers();
		setCounter(numSelected);
		negative = new ImageIcon(this.getClass().getResource("images/moves-.gif"));
	}
	
	/**
	 * Set the counter value
	 * @param numMines the counter value
	 */
	protected void setCounter(int numMines)
	{
		boolean neg = false;

		if(numMines< 0)
			neg = true;
				
		if(neg)
		{
			hundreds.setIcon(negative);
			numMines *= -1;
		}
		else
			this.hundreds.setIcon(numbers[h]);
		
		h = numMines / 100;
		t = (numMines / 10) % 10;
		o = numMines % 10;
		
		this.tens.setIcon(numbers[t]);
		this.ones.setIcon(numbers[o]);	
	}
	
	/**
	 * Update the display
	 */
	protected void updateDisplay()
	{
		hundreds.setIcon(numbers[h]);
		tens.setIcon(numbers[t]);
		ones.setIcon(numbers[o]);	
	}
	
	/**
	 * Initialize the counter display
	 * @param number that will be displayed
	 */
	private void initDisplay(int numMines)
	{	
		this.hundreds = new JLabel();
		this.tens = new JLabel();
		this.ones = new JLabel();
		
		this.add(this.hundreds);
		this.add(this.tens);
		this.add(this.ones);
	}
	
	/**
	 * Initialize the counter
	 */
	private void initNumbers()
	{
		StringBuilder imageName = new StringBuilder("moves");
		String suffix;
		for (int i = 0; i < numbers.length; i++)
		{
			suffix = String.valueOf(i) + ".gif";
			imageName.append(suffix);
			numbers[i] = new ImageIcon(this.getClass().getResource("images/" + imageName.toString()));
			imageName.setLength(5);
		}
	}
	
	/**
	 * Increment the mine counter
	 */
	public void increment()
	{
		if (numSelected < maxMines)
		{
			numSelected++;
			setCounter(numSelected);
		}
	}
	
	/**
	 * Decrement the mine counter
	 */
	public void decrement()
	{
			numSelected--;
			setCounter(numSelected);
	}
	
	/**
	 * Set the maximum number of mines on the board
	 * @param mines the maximum number of mines
	 */
	private void setMax(int mines)
	{
		maxMines = mines;
	}
	
	/**
	 * Reset the counter
	 * @param mines the number of mines on the board
	 */
	public void reset(int mines)
	{
		numSelected = mines;
		setMax(mines);
		setCounter(mines);
	}
}
