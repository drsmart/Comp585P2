package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameManager implements MouseListener 
{
	private MinesweeperGUI window;
	private Board board;
	private Cell current;
	private boolean gameOver;
	
	public GameManager()
	{
		window = new MinesweeperGUI(this);
		board = window.getBoard();
		gameOver = false;
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
			}
			
			board.repaint();
		}
		
		if (gameOver)
		{
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