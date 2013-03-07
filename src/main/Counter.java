package main;

import java.awt.FlowLayout;

import javax.swing.*;

public class Counter extends JPanel
{
	private JLabel hundreds;
	private JLabel tens;
	private JLabel ones;
	private ImageIcon[] numbers;
	private int numSelected;
	private int maxMines;
	
	public Counter(int numOfMines)
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		maxMines = numSelected = numOfMines;
		numbers = new ImageIcon[10];
		initTimer(numSelected);
		initNumbers();
		setTimer(numSelected);
		
	}
	
	private void setTimer(int numMines)
	{
		int hundreds = numMines / 100;
		int tens = (numMines / 10) % 10;
		int ones = numMines % 10;
		
		this.hundreds.setIcon(numbers[hundreds]);
		this.tens.setIcon(numbers[tens]);
		this.ones.setIcon(numbers[ones]);
		
		this.add(this.hundreds);
		this.add(this.tens);
		this.add(this.ones);
	}
	
	private void initTimer(int numMines)
	{	
		this.hundreds = new JLabel();
		this.tens = new JLabel();
		this.ones = new JLabel();
	}
	
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
	
	public void increment()
	{
		if (numSelected < maxMines)
		{
			numSelected++;
			setTimer(numSelected);
		}
	}
	
	public void decrement()
	{
		if (numSelected > 0)
		{
			numSelected--;
			setTimer(numSelected);
		}
	}
}
