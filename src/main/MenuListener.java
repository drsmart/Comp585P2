package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener
{
	private GameManager manager;

	public MenuListener(GameManager manager)
	{
		this.manager = manager;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		switch (command)
		{
			case "New":
				manager.newGame();
				break;
			case "Beginner":
			case "Intermediate":
			case "Expert":
				manager.changeDifficulty(command);
				break;
			case "Exit": 
				System.exit(0);
				break;
		}
	}

}
