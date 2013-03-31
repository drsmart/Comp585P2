package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;
import javax.swing.Timer;

public class TimerLabel extends Counter
{
	private Timer timer;
	
	public TimerLabel()
	{
		super();
		timer = new Timer(1000,timerListener);
		timer.setInitialDelay(10);
		updateDisplay();
	}
	
	@Override
	public void increment()
	{
		if (numSelected < 1000)
		{
			numSelected++;
			setCounter(numSelected);
			updateDisplay();
		}
	}
	
	protected void setCounter(int numMines)
	{
		numSelected = numMines;
		h = numMines / 100;
		t = (numMines / 10) % 10;
		o = numMines % 10;
	}
	
	protected void updateDisplay()
	{
		hundreds.setIcon(numbers[h]);
		tens.setIcon(numbers[t]);
		ones.setIcon(numbers[o]);	
	}
	
	public void startTimer()
	{
		timer.start();
	}
	
	public void stopTimer()
	{
		timer.stop();
	}
	
	public void reset()
	{
		setCounter(0);
		updateDisplay();
		numSelected = 0;
		timer.stop();
	}
	
	ActionListener timerListener = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			increment();
		}
		
	};
}
