package Lessons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//RandomGame
public class LessonSixSeven extends JFrame{
	int comp, user;
	private JButton button;
	private JTextField textfield;
	private JLabel prompt, result, difference;
	
	public LessonSixSeven() {
		setLayout(new FlowLayout());
		
		prompt = new JLabel("Enter a random number 1-20");
		add(prompt);
		
		textfield = new JTextField(2);
		add(textfield);
		
		button = new JButton("Guess!");
		add(button);
		
		result = new JLabel("");
		add(result);
		
		difference = new JLabel("");
		add(difference);
		
		event e = new event();
		button.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			comp = (int)(Math.random() * 20 + 1);
			try {
				user = (int)(Double.parseDouble(textfield.getText()));
				
				if (user == comp) {
					result.setText("You won the game!");
				} else if (user != comp) {
					result.setText("You lost the game!");
				}
				difference.setText("The random number generated was: " + comp);
			} catch (Exception ex) {
				difference.setText("Please enter numbers only!");
			}

			
		}
	}

	public static void main(String[] args) {
		LessonSixSeven gui = new LessonSixSeven();
		
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setTitle("Random Number Guesing Game");
		gui.setVisible(true);
		gui.setSize(300, 150);

	}

}
