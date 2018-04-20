package Lessons;

import java.awt.*;
import javax.swing.*;

//This Lesson prints a picture in a Java window
public class LessonThree extends JFrame {
	
	private ImageIcon image;
	private JLabel label;
	
	public LessonThree() {
		setLayout(new FlowLayout());
		
		image = new ImageIcon(getClass().getResource("kara1.jpg"));
		label = new JLabel(image);
		add(label);
	}
	
	
	public static void main(String[] args) {
		LessonThree gui = new LessonThree();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.pack();
		gui.setTitle("Kara Moulton");
		
	}

}
