package Lessons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This lesson goes over using different events
public class LessonFive extends JFrame{
	
	private JLabel label1, label2;
	private JButton button1, button2;
	private int x = 0, y = 0;
	
	public LessonFive() {
		setLayout(new FlowLayout());
		
		button1 = new JButton("Click Me!");
		add(button1);
		
		label1 = new JLabel("");
		add(label1);
		
		button2 = new JButton("Click AGAIN");
		add(button2);
		
		label2 = new JLabel("");
		add(label2);
		
		
		event1 p = new event1();
		button1.addActionListener(p);
		
		event2 q = new event2();
		button2.addActionListener(q);
		
		
	}
	
	public class event1 implements ActionListener {
		public void actionPerformed(ActionEvent p) {
			if (x == 0) {
				label1.setText("This is not cool.");
				x++;
			} else {
				label1.setText("");
				x--;
			}
		}
	}
	
	public class event2 implements ActionListener {
		public void actionPerformed(ActionEvent q) {
			if (y == 0) {
				label2.setText("Lol, yes it is, it's really cool");
				y++;
			} else {
				label2.setText("");
				y--;
			}
		}
	}
	
	public static void main(String[] args) {
		LessonFive gui = new LessonFive();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setSize(300,300);
		gui.setTitle("Multiple Events");

	}

}
