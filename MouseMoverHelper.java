package com.chepuz.main;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class MouseMoverHelper implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent arg0) {
        
        if(arg0.getKeyCode()==NativeKeyEvent.VC_UP) {
            
            moveX(0,-1);
        }
        if(arg0.getKeyCode()==NativeKeyEvent.VC_DOWN) {
            
            moveX(0,1);
        }
        if(arg0.getKeyCode()==NativeKeyEvent.VC_LEFT) {
            
            moveX(-1,0);
        }
        if(arg0.getKeyCode()==NativeKeyEvent.VC_RIGHT) {
            
            moveX(1,0);
        }
        if(arg0.getKeyCode()==NativeKeyEvent.VC_C) {
          try {
            Main.goMove();
          } catch (AWTException e) {
            e.printStackTrace();
          }
        }
        if(arg0.getKeyCode()==NativeKeyEvent.VC_F) {
          // run thread
//          Main.executor.execute(new RunThread());
        } 
        if(arg0.getKeyCode()==NativeKeyEvent.VC_V) {
          // run thread
          System.out.println("shit");
//          RunThread.isOn = !RunThread.isOn;
        } 
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent arg0) {
        
        
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent arg0) {
        
    }
    public void moveX(int x, int y) {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x1 = (int) b.getX();
        int y1 = (int) b.getY();
        try {
            // These coordinates are screen coordinates


            // Move the cursor
            Robot robot = new Robot();
            robot.mouseMove(x1+x, y1+y);
            System.out.println("("+(x1+x*1)+", "+(y1+y*1)+")");
        } catch (AWTException e) {
        }
    }
    public static void main(String[] args) {
        GlobalScreen.addNativeKeyListener(new MouseMoverHelper());
        LogManager.getLogManager().reset();

        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
        
        }
    }
}
