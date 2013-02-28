package main;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	private Cell[][] cells;
	private int rowCount;
	private int colCount;
	private int cellHeight;
	private int cellWidth;
	private int mineCount;
	
	public Board(int rows, int cols)
	{
		super();
		rowCount = rows;
		colCount = cols;
		this.setLayout(new GridLayout(rowCount, colCount));
		drawBoard();
	}
	
	private void drawBoard()
	{
		for(int row = 0; row < rowCount; row++)
		{
			for(int col = 0; col < colCount; col++)
			{
				this.add(new Cell(row, col));
			}
		}
	}
}
