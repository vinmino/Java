package Lessons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LessonEight extends JFrame {
	
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem item;
	
	public LessonEight() {
		setLayout(new FlowLayout());
		
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		menu = new JMenu("File");
		menubar.add(menu);
		
		item = new JMenuItem("Exit");
		menu.add(item);
		
		event e = new event();
		item.addActionListener(e);
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		LessonEight gui = new LessonEight();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setSize(250,200);
		gui.setTitle("Menu Bar");
		
		
	}

}
