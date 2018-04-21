package PixelFun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

//extends JFrame to make the window work when the class is used
public class PixelFun extends JFrame {
    //Data
    final int HEIGHT = 800;
    final int WIDTH = 800;

    static int numOfPoints = 300;
    int radius = 300;

    int delay = 10;
    Timer t;
    DrawArea dA;

    static PixelFun pf;

    //Constructor
    public PixelFun(int numOfPoints) throws HeadlessException {
        //sets up the window settings
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //creates and adds the Draw Area class to the JFrame window
        dA = new DrawArea(numOfPoints, radius);
        setContentPane(dA);

    }

    //Main method
    public static void main(String[] args) {

        //Makes the window
        pf = new PixelFun(numOfPoints);
        pf.t = new Timer(pf.delay, pf.dA); //the timer is what animates it. It changes the timesTable value by the increment (inc) after the set delay in milliseconds
        pf.t.start();

        //makes a dialog window to control the data and math for the lines and colors and speed
        JDialog controls = new JDialog();
        controls.setLocationRelativeTo(pf);
        controls.setLayout(new GridLayout(3, 8));
        controls.setVisible(true);
        controls.setTitle("Controls");
        controls.isAlwaysOnTop();

        //This next entire section makes all the visual parts that will go into the Dialog window for the controls
        JLabel timesTableLable = new JLabel("timesTable: ");
        JTextField timesTableTextField = new JTextField(5);
        JButton timesTableButton = new JButton("Apply");
        timesTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takes the value from the timesTableTextField and sets the timesTable value to that. Then repaints
                pf.dA.timesTable = Double.parseDouble(timesTableTextField.getText());
                pf.dA.repaint();
            }
        });

        JLabel incLable = new JLabel("inc: ");
        JTextField incTextField = new JTextField(5);
        JButton incButton = new JButton("Apply");
        incButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takes the value from the incTextField and sets the inc value to that. Then repaints
                pf.dA.inc = Double.parseDouble(incTextField.getText());
                pf.dA.repaint();
            }
        });

        JLabel FPSLable = new JLabel("FPS: ");
        JTextField FPSTextField = new JTextField(5);
        JButton FPSButton = new JButton("Apply");
        FPSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takes the value from the delayTableTextField and sets the delay value to that. Then repaints
                pf.t.setDelay(Integer.parseInt(FPSTextField.getText()));
            }
        });

        JLabel PointsLable = new JLabel("# of Points: ");
        JTextField PointsTextField = new JTextField(5);
        JButton PointsButton = new JButton("Apply");
        PointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //This closes the current window and recreates the window method with a new number of points, and recalculates these x and y values
                //Then is restarts the timer
                pf.setVisible(false);
                pf = new PixelFun(Integer.parseInt(PointsTextField.getText()));
                pf.t = new Timer(pf.delay, pf.dA);
                pf.t.start();
            }
        });

        JLabel alphaLable = new JLabel("Alpha: ");
        JTextField alphaTextField = new JTextField(5);
        JButton alphaButton = new JButton("Apply");
        alphaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takes the value from the alphaTableTextField and sets the alpha value to that. Then repaints
                pf.dA.alpha = Integer.parseInt(alphaTextField.getText());
                pf.dA.repaint();
            }
        });

        JLabel alphaToggleLabel = new JLabel("Alpha Toggle");
        JLabel alphaToggleStat = new JLabel("OFF");
        JButton alphaToggleButton = new JButton("ALPHA");
        alphaToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // checks the state of if the alpha is toggled on or off, then flips it, changing the transparency decision
                if (pf.dA.colormode == 1) {
                    alphaToggleStat.setText("ON");
                    pf.dA.colormode = 2;
                } else {
                    alphaToggleStat.setText("OFF");
                    pf.dA.colormode = 1;
                }
            }
        });

        JLabel colorFormatLabel = new JLabel("Color Format");
        JLabel colorFormatStat = new JLabel("Circle");
        JButton colorFormatButton = new JButton("Toggle");
        colorFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks the color format and flips it, changing the coloring decisions
                if (pf.dA.rainbow == 1) {
                    pf.dA.rainbow = 2;
                    colorFormatStat.setText("Line");
                } else {
                    pf.dA.rainbow = 1;
                    colorFormatStat.setText("Circle");
                }
            }
        });

        JLabel stopLable1 = new JLabel("STOP!: ");
        JLabel stopLable2 = new JLabel("STOP!: ");
        JButton stopButton = new JButton("STOP!");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sets the increment to 0 so the animation stops moving. then repaints
                pf.dA.inc = 0.0;
                pf.dA.repaint();
            }
        });

        //All the graphical opbjects that were just created are not added to the window
        controls.add(timesTableLable);
        controls.add(incLable);
        controls.add(FPSLable);
        controls.add(PointsLable);
        controls.add(alphaLable);
        controls.add(alphaToggleLabel);
        controls.add(colorFormatLabel);
        controls.add(stopLable1);

        controls.add(timesTableTextField);
        controls.add(incTextField);
        controls.add(FPSTextField);
        controls.add(PointsTextField);
        controls.add(alphaTextField);
        controls.add(alphaToggleStat);
        controls.add(colorFormatStat);
        controls.add(stopLable2);

        controls.add(timesTableButton);
        controls.add(incButton);
        controls.add(FPSButton);
        controls.add(PointsButton);
        controls.add(alphaButton);
        controls.add(alphaToggleButton);
        controls.add(colorFormatButton);
        controls.add(stopButton);

        //makes the control dialog window the correct size based on its contents
        controls.pack();


    }

}

//This is where all of the graphics go
class DrawArea extends JPanel implements ActionListener { //is an action listener so that it can be animated with the timer
    //Data
    double[][] vertices; //array that will hold the calculated x and y coordinate for each point on the circle
    double timesTable = 0.0; //controls the number that is being multiplied and modulosed by
    double radius;
    double inc = 0.000; //controls how the TimesTable value changes every update from the timer
    int alpha = 100; //controls the alpha value for the transparency
    int colormode = 1; //controls if the colors have transparency or not. If they do, it is slowed down greatly
    int rainbow = 1; //controls if the entire circle changes colors or if the lines themselves change colors

    //Constructor
    public DrawArea(int numOfPoints, double radius) {
        vertices = new double[numOfPoints][2];
        double angle;
        double x;
        double y;
        this.radius = radius;
        //This for loop calculates the points on the circle
        for (int i = 0; i < vertices.length; i++) {
            angle = 2 * Math.PI * i / numOfPoints;
            x = radius * Math.cos(Math.PI + angle);
            y = radius * Math.sin(Math.PI + angle);
            vertices[i][0] = x;
            vertices[i][1] = y;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        Shape background = new Rectangle2D.Float(0, 0, 800, 800); //greats a black backgroud
        g2.fill(background);

        //controls the alpha or not. Honestly this part was confusing for me and i cant explain it bc i just got it to work Haha :)
        if (colormode == 1) {
            circle(g2, vertices, timesTable, rainbow);
        }
        if (colormode == 2) {
            circle(g2, vertices, timesTable, rainbow + colormode);
        }

    }

    //this method creates the lines and therefore the design
    public void circle(Graphics2D g2, double[][] vertices, double timesTable, int colorMode) {
        g2.translate(800 / 2.0, 800 / 2.25); //translate to the center of the window

        double x;
        double y;

        //draws the points on the circle. Honestly, now that I look at this it is stupid to even do, but it helps with explaining if the color is changed
        for (int i = 0; i < vertices.length; i++) {
            x = vertices[i][0];
            y = vertices[i][1];
            g2.setColor(Color.BLACK);
            Shape pixel = new Rectangle2D.Double(x, y, 3, 3);
            g2.fill(pixel);
        }

        //transparency and color decision
        if (colorMode == 3) {
            int rgb = Color.HSBtoRGB(0.2f * (float) timesTable, 1.0f, 1.0f);
            g2.setColor(new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, alpha));
        }
        if (colorMode == 1) {
            g2.setColor(new Color(Color.HSBtoRGB(0.2f * (float) timesTable, 1.0f, 1.0f)));
        }

        double result;
        for (int i = 0; i < vertices.length; i++) {
            result = (timesTable * i) % vertices.length; //THIS IS THE MATH THAT DECIDES WHAT LINES GO WHERE. THIS IS THE ENTIRE POINT OF THE PROGRAM
            //another transparency and color decision
            if (colorMode == 2) {
                g2.setColor(new Color(Color.HSBtoRGB((1.0f / vertices.length) * i, 1.0f, 1.0f)));
            }
            if (colorMode == 4) {
                int rgb = Color.HSBtoRGB((1.0f / vertices.length) * i, 1.0f, 1.0f);
                g2.setColor(new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, alpha));
            }
            //draws the line
            Shape line = new Line2D.Double(vertices[i][0], vertices[i][1], vertices[(int) result][0], vertices[(int) result][1]);
            g2.draw(line);
        }

        //Adds the values counters on the top and the little signature on the bottom Haha :)
        DecimalFormat df = new DecimalFormat("0.00000");
        g2.setColor(Color.WHITE);
        g2.drawString(df.format(timesTable) + " | " + df.format(inc), -20, -1 * ((int) radius + 30));
        g2.drawString("Vinnie is a God", -40, ((int) radius + 30));

        //translates back to the top left corner. This was more useful when I had two circles going, but that was just too many calculations
        g2.translate(-800 / 2.0, -800 / 2.25);

    }

    //This is what animates and then repaints the graphics everytime the Timer changes after the set delay
    @Override
    public void actionPerformed(ActionEvent e) {
        timesTable += inc;
        repaint();
    }

}
