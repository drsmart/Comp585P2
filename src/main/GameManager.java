package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Cell.CellState;

public class GameManager implements MouseListener 
{
	private MinesweeperGUI window;
	private Board board;
	private Cell current;
	private boolean gameOver;
	private boolean firstMove;
	
	public GameManager()
	{
		window = new MinesweeperGUI(this);
		board = window.getBoard();
		gameOver = false;
		firstMove = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		current = (Cell)e.getSource();
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
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
//		Cell c = (Cell)e.getSource();
//		c.pressed();
//		board.repaint();	
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		release(e.getButton());
	}

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
			newGame();
		}
	}
	
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

	private void newGame()
	{
		board.newGame();
		board.repaint();
	}
}