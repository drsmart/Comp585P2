package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellListener implements MouseListener 
{
	private Board board;
	private Cell current;
	
	public CellListener(Board board)
	{
		this.board = board;
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
		
		if (click == MouseEvent.BUTTON1)
		{
			//Left Click
			board.uncoverAdjacentCells(current);
			//current.uncover();
			
			isGameOver(current);
		}
		else if (click == MouseEvent.BUTTON3)
		{
			//Right Click
			current.mark();
		}
		
		board.repaint();
	}
	
	private void isGameOver(Cell current)
	{
		if (current.isMined())
		{
			System.out.println("Lose");
		}
		else if (board.allNonMinesUncovered())
		{
			System.out.println("Win");
		}
	}

}
