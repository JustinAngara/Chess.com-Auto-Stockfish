package com.chepuz.main;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Overlay {
    private static JFrame frame;
    
    private int x, y, w, h;
    private JLabel label;
    
    

    public Overlay() {
        // Create bounds
        x = 225;
        w = 1032 - x;

        y = 153;
        h = 961 - y;

        // Set up the JFrame
        frame = new JFrame();
        frame.setBounds(x, y-(960+100), w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

        label = new JLabel();
        label.setBounds(5, 5, w, h); // Correct bounds for JLabel within JFrame
        frame.getContentPane().add(label);

        frame.setAlwaysOnTop(true);
        frame.setFocusable(false);
        frame.setVisible(true);

    }

    public void turnOn() throws AWTException {
        // Capture screenshot and set it as icon for JLabel
        BufferedImage image = Screenshot.captureScreenshot(x, y, w, h);
        label.setIcon(new ImageIcon(image));
        
    }
    
    

}
