package main;

import java.awt.GridLayout;
import main.Cell.CellState;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel
{
	enum Direction {NW, N, NE, E, SE, S, SW, W}
	
	private final Direction[] directions = {Direction.NW, Direction.N, Direction.NE, Direction.E, Direction.SE, Direction.S, Direction.SW, Direction.W};
	private final int MINECOUNT = 10;	
	private Cell[][] cells;
	private int rowCount;
	private int colCount;
	private int mineCount;
	private GameManager list;
	
	public Board(int rows, int cols, int mineCount, GameManager listener)
	{
		list = listener;
		
		rowCount = rows;
		colCount = cols;
		cells = new Cell[rows][cols];
		this.mineCount = mineCount;
		
		this.setLayout(new GridLayout(rowCount, colCount));
		
		drawBoard();
		placeMines();
		calcAdjCount();
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
	 * Resets the grid to its initial state
	 */
	public void newGame()
	{
		coverAll();
		placeMines();
		calcAdjCount();
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
			if (!cells[row][col].isMined())
			{
				cells[row][col].setMined(true);
				minesPlaced++;
			}	
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
				if(!cell.isMined() && cell.isCovered())
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Calculates and sets the adjacency count for all cells in the grid
	 */
	private void calcAdjCount()
	{
		for (int row = 0; row < rowCount; row++)
		{
			for (int col = 0; col < colCount; col++)
			{
				Cell c = cells[row][col];
				
				if (c.isMined()==false)
				{
					int count = 0;
					
					for (int i = 0; i < 8; i++)
					{
						Cell n = getNeighbor(c, directions[i]);
						c.addNeighbor(n);
							if (n != null && n.isMined())
							{
								count++;
							}
					}
					c.setAdjCount(count);
				}
			}
		}
	}
	
	/**
	 * Determines if a given cell position is valid
	 * @param row the row position of the cell 
	 * @param col the column column position of the cell
	 * @return true if the position is valid false otherwise
	 */
	private boolean isValid(int row, int col)
	{
		if ((row >= 0) && (row < rowCount) && (col >= 0) && (col < colCount))
			return true;
		else 
			return false;
	}
	
	/**
	 * Gets the neighbor of a cell
	 * @param c the cell to get the neighbor
	 * @param dir the direction of the neighbor
	 * @return the neighbor of the cell c if it exists null otherwise
	 */
	private Cell getNeighbor(Cell c, Direction dir)
	{
		int row = c.getRow();
		int col = c.getCol();
		
		switch (dir)
		{
			case NW:
				row--;
				col--;
				break;
			case N:
				row--;
				break;
			case NE:
				row--;
				col++;
				break;
			case E:
				col++;
				break;
			case SE:
				row++;
				col++;
				break;
			case S:
				row++;
				break;
			case SW:
				row++;
				col--;
				break;
			case W:
				col--;
				break;
				
		}
		
		if (isValid(row, col))
			return cells[row][col];
		else
			return null;
	}
	
	/**
	 * Uncovers all cells adjacent to the clicked cell that are not mined
	 * @param current the cell clicked
	 */
	public void uncoverAdjacentCells(Cell current)
	{
		if (current == null)
			return;
		
		else if (!current.isCovered())
			return;
		
		else if (current.isCovered() && current.getAdjCount() == 0)
		{
			current.uncover();
			for ( int i = 0; i < current.getNeighborCount(); i++)
			{
				uncoverAdjacentCells(current.getNeighbor(i));
			}
		}
		
		else if (current.isCovered() && current.getAdjCount() >= 1)
		{
			current.uncover();
			return;
		}
	}
	
	/**
	 * Updates the board state to the appropriate game over state
	 * @param win boolean representing whether the player won or lost
	 */
	public void gameOver(boolean win)
	{
		if (win)
			markAll();
		else
			uncoverAll();
	}
	
	private void markAll()
	{
		for (Cell [] row : cells)
		{
			for (Cell cell : row)
			{
				if (cell.isMined())
					cell.flag();
			}
		}
		repaint();
	}
	
	private void uncoverAll()
	{
		for (Cell [] row : cells)
		{
			for (Cell cell : row)
			{
				if (cell.isMined())
					cell.uncover();
			}
		}
	}
	
	public void coverAll()
	{
		for (Cell [] row : cells)
		{
			for (Cell cell : row)
			{
				cell.reset();
			}
		}
	}
	
	public void setMineCount(int mineCount)
	{
		this.mineCount = mineCount; 
	}
	
	@Override
	public String toString()
	{
		String board = "";
		
		for (int row = 0; row < rowCount; row++)
		{
			for (int col = 0; col < colCount; col++)
			{
				board = board + cells[row][col].toString() + " ";
			}
			board += "\n";
		}
		
		return board;
	}
}