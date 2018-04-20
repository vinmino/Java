package Lessons;

import java.awt.*;
import javax.swing.*;

//This Lesson makes a window with a sample label, text field, and button
public class LessonTwo extends JFrame {
	
	private JLabel label;
	private JButton button;
	private JTextField textfield;
	
	public LessonTwo() {
		setLayout(new FlowLayout());
		
		label = new JLabel("This is called a label");
		add(label);
		
		textfield = new JTextField(15);
		add(textfield);
		
		button = new JButton("Button");
		add(button);
	}
	
	public static void main(String args[]) {
		
		LessonTwo gui = new LessonTwo();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setSize(200, 200);
		gui.setTitle("Cool Name");
		
		
	}

}
