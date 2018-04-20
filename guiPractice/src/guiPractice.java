//Reference the required Java libraries
import java.applet.Applet; 
import java.awt.*; 

//The applet code
public class guiPractice extends Applet {

	public void paint(Graphics g) {
	
		//set the color of the rect
		g.setColor(Color.magenta);
		
		
		
		setBackground(Color.green);
		
		//Draw a rectangle width=250, height=100
		
		g.drawRect(50,50,250,100); 
		g.setColor(Color.yellow);
		g.fillRect(51,51,251,101);
		g.draw3DRect(500, 500, 100, 100, false);
		
		//Set the color to blue
		g.setColor(Color.blue); 
		
		//Write the message to the web page
		g.drawString("Look at me, I'm a Java Applet!",60,100); 
		
	}
}