package main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Cell.CellState;

public class GameManager extends MouseAdapter 
{
	private MinesweeperGUI window;
	private MenuListener menuListener;
	private Board board;
	private Cell current;
	private boolean gameOver;
	private boolean firstMove;
	
	public GameManager()
	{
		menuListener = new MenuListener(this);
		window = new MinesweeperGUI(this, menuListener);
		board = window.getBoard();
		gameOver = false;
		firstMove = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		current = (Cell)e.getSource();
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (!gameOver)
			window.setButton();
		if(firstMove)
		{
			firstMove = false;
			window.startTimer();
		}	
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		release(e.getButton());
	}
	
	/**
	 * Updates the ui depending on the clicked mouse button
	 * @param click the button clicked
	 */
	private void release(int click)
	{
		if (!gameOver)
		{
			window.setButton();
			if (click == MouseEvent.BUTTON1)
			{
				//Left Click
				if (!current.isMarked())
					board.uncoverAdjacentCells(current);		
				
				gameOver = isGameOver(current);
			}
			else if (click == MouseEvent.BUTTON3)
			{
				//Right Click
				current.mark();
				if (current.getState() == CellState.FLAGGED)
					window.markCell();
				else if (current.getState() == CellState.UNKNOWN)
					window.unMarkCell();
			}

			board.repaint();
		}
		
		if (gameOver)
		{
			window.stopTimer();
		}
	}
	
	/**
	 * Check if the game is over
	 * @param current the cell clicked on
	 * @return true if a bombed cell is clicked on fales otherwise
	 */
	private boolean isGameOver(Cell current)
	{
		boolean gameOver = false;
		if (!current.isMarked() && current.isMined())
		{
			window.gameOver(false);
			gameOver = true;
		}
		else if (board.allNonMinesUncovered())
		{
			window.gameOver(true);
			gameOver = true;
		}
		return gameOver;
	}
	
	/**
	 * Change game difficulty setting
	 * @param difficulty 
	 */
	protected void changeDifficulty(String difficulty)
	{
		window.setDifficulty(difficulty);
	}

	/**
	 * Start a new game 
	 */
	protected void newGame()
	{
		gameOver = false;
		firstMove = true;
		window.newGame();
	}
}