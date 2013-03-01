package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellListener implements MouseListener 
{
	private Board board;
	
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		Cell c = (Cell)e.getSource();
		c.pressed();
		board.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		Cell c = (Cell)e.getSource();
		c.uncover();
		board.repaint();
	}



}
