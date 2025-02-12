package com.chepuz.main;
import java.awt.AWTException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.Timer;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;


public class Main implements NativeKeyListener {
  static Timer t; 
  static PieceOverlay po = new PieceOverlay();
  private JFrame frame;
  public static Overlay o;
  public static boolean isWhite = true;
//  public static ThreadPoolExecutor executor;
  /**
   * Launch the application.
   * @throws AWTException 
   * @throws IOException 
   * @throws MalformedURLException 
   */
  
  public static void goMove() throws AWTException {
    o.turnOn();
  }
  
  
  public static void main(String[] args) throws MalformedURLException, IOException {
    
    po.main(null);
//    executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Main window = new Main();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   * @throws AWTException 
   */
  public Main() throws AWTException {

    //create overlay object
    o = new Overlay();
    MouseMoverHelper.main(null);
    t = new Timer(250,(ActionEvent e)->{
      try {
        Main.goMove();
      } catch (AWTException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 800, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setAlwaysOnTop(true);
    JButton btnNewButton = new JButton("Show overlay");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {      
        try {
          o.turnOn();
        } catch (AWTException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    });
    btnNewButton.setBounds(78, 135, 140, 23);
    frame.getContentPane().add(btnNewButton);
    
    JButton btnNewButton_1 = new JButton("White");
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        isWhite = true;
      }
    });
    btnNewButton_1.setBounds(25, 225, 140, 23);
    frame.getContentPane().add(btnNewButton_1);
    
    JButton btnNewButton_2 = new JButton("Black");
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        isWhite = false;
      }
    });
    btnNewButton_2.setBounds(179, 225, 140, 23);
    frame.getContentPane().add(btnNewButton_2);
//    t.start();
  }

  

  
}
