package main;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	private final int MINECOUNT = 10;
	
	private Cell[][] cells;
	private int rowCount;
	private int colCount;
	private int cellHeight;
	private int cellWidth;
	private int mineCount;
	private CellListener list;
	public Board(int rows, int cols)
	{
		super();
		list = new CellListener(this);
		rowCount = rows;
		colCount = cols;
		this.setLayout(new GridLayout(rowCount, colCount));
		drawBoard();
	}
	
	private void drawBoard()
	{
		int minesPlaced = 0;
		for(int row = 0; row < rowCount; row++)
		{
			for(int col = 0; col < colCount; col++)
			{
				Cell c = new Cell(row, col);
				if (minesPlaced < MINECOUNT)
				{
					c.setMined(true);
					minesPlaced++;
				}
				c.addMouseListener(list);
				this.add(c);
			}
		}
	}
}
