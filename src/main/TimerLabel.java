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
	}
	
	@Override
	public void increment()
	{
		numSelected++;
		setCounter(numSelected);
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
