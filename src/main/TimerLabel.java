package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerLabel extends Counter
{
	
	public TimerLabel()
	{
		super();	
	}
	
	@Override
	public void increment()
	{
		numSelected++;
		setTimer(numSelected);
	}
	
	public void startTimer()
	{
		Timer t = new Timer(1000,timerListener);
		t.setInitialDelay(10);
		t.start();
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
