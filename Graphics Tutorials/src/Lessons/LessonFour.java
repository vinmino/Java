package Lessons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This program shows how to make an event listener for a button work
public class LessonFour extends JFrame {
	
	private JLabel label;
	private JButton button;
	
	public LessonFour() {
		setLayout(new FlowLayout());
		
		button = new JButton("Click");
		
		label = new JLabel("");
		
		add(button);
		add(label);
		
		event e = new event();
		button.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText("This is the coolstuff");
		}
	}
	
	
	public static void main(String[] args) {
		
		LessonFour gui = new LessonFour();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setSize(100,100);
		gui.setVisible(true);
		gui.setTitle("Events");
		
	}

}
