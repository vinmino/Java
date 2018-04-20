package Lessons;

import javax.swing.JFrame;

//This Lesson goes over how to set up the window and make it appear
public class LessonOne extends JFrame{

	public static void main(String[] args) {
		LessonOne gui = new LessonOne();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setSize(400, 400);
		gui.setVisible(true);
		gui.setTitle("This is Fucking Cool!");
	}

}