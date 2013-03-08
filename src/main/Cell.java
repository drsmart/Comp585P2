package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Cell extends JPanel 
{
	protected enum CellState {UNMARKED, FLAGGED, UNKNOWN}
	
	private int row;
	private int col;
	private int width;
	private int height;
	private boolean mined;
	private boolean covered;
	private int adjCount;
	private Image bgImg;
	private CellState cellState;
	private ArrayList<Cell> neighbors;
	
	
	public Cell(int x, int y)
	{
		row = x;
		col = y;
		adjCount = 0;
		mined = false;
		covered = true;
		cellState = CellState.UNMARKED;
		neighbors = new ArrayList<Cell>();
		ImageIcon img = new ImageIcon(this.getClass().getResource("images/blank.gif"));
		width = img.getIconWidth();
		height = img.getIconHeight();
		bgImg = img.getImage();
		this.setPreferredSize(new Dimension(width, height));	
	}
	
	public void reset()
	{
		adjCount = 0;
		mined = false;
		covered = true;
		cellState = CellState.UNMARKED;
		neighbors = new ArrayList<Cell>();
		cover();
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setAdjCount(int count)
	{
		adjCount = count;
	}
	
	public int getAdjCount()
	{
		return adjCount;
	}
	
	public int getNeighborCount()
	{
		return neighbors.size();
	}
	
	public void setMined(boolean mined)
	{
		this.mined = mined;
	}
	
	public boolean isMined()
	{
		return mined;
	}
	
	public boolean isMarked()
	{
		if (cellState == CellState.UNMARKED)
			return false;
		else
			return true;
	}
	
	public boolean isCovered()
	{
		return covered;
	}
	
	public void addNeighbor(Cell n)
	{
		neighbors.add(n);
	}
	
	public Cell getNeighbor(int n)
	{
		return neighbors.get(n);
	}
	
	public void setCovered(boolean covered)
	{
		this.covered = covered;
	}
	
	public void paintComponent(Graphics g)
	{
		if (bgImg != null)
			g.drawImage(bgImg, 0, 0, this);
	}
	
	/**
	 * Uncover the cell
	 */
	public void uncover()
	{
		if (cellState == CellState.UNMARKED)
		{
			String pic = "";
			covered = false;
			if (mined && !covered)
				pic = "bombdeath.gif";
			else if (!covered && !mined)
				pic = "open" + String.valueOf(adjCount) + ".gif";
			setImage(pic);
		}		
	}
	
	/**
	 * Set the cell state as flagged, unknown, or unmarked
	 */
	public void mark()
	{
		if (covered)
		{
			String img = "";
			
			switch (cellState)
			{
				case UNMARKED:
					cellState = CellState.FLAGGED;
					img = "bombflagged.gif";
					break;
				case FLAGGED:
					cellState = CellState.UNKNOWN;
					img = "bombquestion.gif";
					break;
				case UNKNOWN:
					cellState = CellState.UNMARKED;
					img = "blank.gif";
					break;
			}
			
			setImage(img);
		}
	}
	
	/**
	 * Marks the cell with a flag
	 */
	public void flag()
	{
		setImage("bombflagged.gif");
	}
	
	/**
	 * Marks the cell with a flag
	 */
	public void cover()
	{
		covered = true;
		setImage("blank.gif");
	}
	
	public void pressed()
	{
		bgImg = (new ImageIcon(this.getClass().getResource("images/open0.gif"))).getImage();
	}
	
	private void setImage(String image)
	{
		bgImg = (new ImageIcon(this.getClass().getResource("images/" + image))).getImage();
	}
	
	public CellState getState()
	{
		return cellState;
	}

	public void setState(CellState state)
	{
		cellState = state;		
	}
	@Override
	public String toString()
	{
		String cell = "";
		
		if (mined)
			cell = "x";
		else if (adjCount == 0)
			cell = "_";
		else
			cell = String.valueOf(adjCount);
		return cell;
	}
}
