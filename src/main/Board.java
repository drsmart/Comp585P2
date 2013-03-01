package main;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	private final int MINECOUNT = 10;
	
	private Cell[][] cells;
	private int rowCount;
	private int colCount;
	private int mineCount;
	private CellListener list;
	
	public Board(int rows, int cols)
	{
		list = new CellListener(this);
		
		rowCount = rows;
		colCount = cols;
		cells = new Cell[rows][cols];
		mineCount = MINECOUNT;
		
		this.setLayout(new GridLayout(rowCount, colCount));
		
		drawBoard();
		placeMines();
	}
	
	/**
	 * Draws the cells on the board
	 */
	private void drawBoard()
	{
		for(int row = 0; row < rowCount; row++)
		{
			for(int col = 0; col < colCount; col++)
			{
				Cell c = new Cell(row, col);
				cells[row][col] = c;
				c.addMouseListener(list);
				this.add(c);
			}
		}
	}
	
	/**
	 * Randomly places mines on the board
	 */
	private void placeMines()
	{
		int minesPlaced = 0;
		int row;
		int col;
		Random rndm = new Random();

		while (minesPlaced < mineCount)
		{
			row = rndm.nextInt(rowCount);
			col = rndm.nextInt(colCount);
			cells[row][col].setMined(true);
			minesPlaced++;
		}
	}
	
	/**
	 * Sweeps through all cells in the grid and confirms that all non-mined cells are uncovered
	 * @return true if all non-mined cells are uncovered false otherwise 
	 */
	public boolean allNonMinesUncovered()
	{
		for (Cell[] row: cells)
		{
			for(Cell cell: row)
			{
				if(cell.isMined() && !cell.isCovered())
					return false;
			}
		}
		return true;
	}
	
}