package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setTimer(numSelected);
	}
	
	public void startTimer()
	{
		
		timer.start();
	}
	
	public void stopTimer()
	{
		timer.stop();
	}
	
	ActionListener timerListener = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//while(true);
			increment();
		}
		
	};
}
