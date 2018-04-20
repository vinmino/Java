package PixelFun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

public class PixelFun extends JFrame {

    final int HEIGHT = 800;
    final int WIDTH = 800;

    static int numOfPoints = 300;
    int radius = 300;

    int delay = 10;
    Timer t;
    DrawArea dA;

    static PixelFun pf;

    public PixelFun(int numOfPoints) throws HeadlessException {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        dA = new DrawArea(numOfPoints, radius);
        setContentPane(dA);

    }

    public static void main(String[] args) {

        pf = new PixelFun(numOfPoints);
        pf.t = new Timer(pf.delay, pf.dA);
        pf.t.start();

        JDialog controls = new JDialog();
        controls.setLocationRelativeTo(pf);
        controls.setLayout(new GridLayout(3, 8));
        controls.setVisible(true);
        controls.setTitle("Controls");
        controls.isAlwaysOnTop();


        JLabel timesTableLable = new JLabel("timesTable: ");
        JTextField timesTableTextField = new JTextField(5);
        JButton timesTableButton = new JButton("Apply");
        timesTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                pf.t.setDelay(Integer.parseInt(FPSTextField.getText()));

            }
        });

        JLabel PointsLable = new JLabel("# of Points: ");
        JTextField PointsTextField = new JTextField(5);
        JButton PointsButton = new JButton("Apply");
        PointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        JButton colorFormatButton = new JButton("Apply");
        colorFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                pf.dA.inc = 0.0;
                pf.dA.repaint();
            }
        });

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

        controls.pack();


    }

}

class DrawArea extends JPanel implements ActionListener {
    double[][] vertices;
    double timesTable = 0.0;
    double radius;
    double inc = 0.000;
    int alpha = 100;
    int colormode = 1;
    int rainbow = 1;

    public DrawArea(int numOfPoints, double radius) {
        vertices = new double[numOfPoints][2];
        double angle;
        double x;
        double y;
        this.radius = radius;
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
        Shape background = new Rectangle2D.Float(0, 0, 1600, 800);
        g2.fill(background);

        if (colormode == 1) {
            circle(g2, vertices, timesTable, rainbow);
        }
        if (colormode == 2) {
            circle(g2, vertices, timesTable, rainbow + colormode);
        }

    }

    public void circle(Graphics2D g2, double[][] vertices, double timesTable, int colorMode) {
        g2.translate(800 / 2.0, 800 / 2.25);

        double x;
        double y;
        for (int i = 0; i < vertices.length; i++) {
            x = vertices[i][0];
            y = vertices[i][1];
            g2.setColor(Color.BLACK);
            Shape pixel = new Rectangle2D.Double(x, y, 3, 3);
            g2.fill(pixel);
        }

        if (colorMode == 3) {
            int rgb = Color.HSBtoRGB(0.2f * (float) timesTable, 1.0f, 1.0f);
            g2.setColor(new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, alpha));
        }
        if (colorMode == 1) {
            g2.setColor(new Color(Color.HSBtoRGB(0.2f * (float) timesTable, 1.0f, 1.0f)));
        }

        double result;
        for (int i = 0; i < vertices.length; i++) {
            result = (timesTable * i) % vertices.length;
            if (colorMode == 2) {
                g2.setColor(new Color(Color.HSBtoRGB((1.0f / vertices.length) * i, 1.0f, 1.0f)));
            }
            if (colorMode == 4) {
                int rgb = Color.HSBtoRGB((1.0f / vertices.length) * i, 1.0f, 1.0f);
                g2.setColor(new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, alpha));
            }
            Shape line = new Line2D.Double(vertices[i][0], vertices[i][1], vertices[(int) result][0], vertices[(int) result][1]);
            g2.draw(line);
        }

        DecimalFormat df = new DecimalFormat("0.######");
        g2.setColor(Color.WHITE);
        g2.drawString(df.format(timesTable) + " | " + df.format(inc), -20, -1 * ((int) radius + 30));
        g2.drawString("Vinnie is a God", -40, ((int) radius + 30));

        g2.translate(-800 / 2.0, -800 / 2.25);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timesTable += inc;
        repaint();
    }

}