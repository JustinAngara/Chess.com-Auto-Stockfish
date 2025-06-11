package com.chepuz.main;

import java.awt.AWTException;

public class RunThread implements Runnable{
  static boolean isOn = false;
  @Override
  public void run() {
    while(isOn) {
      try {
        System.out.println("running and shit");
        Main.goMove();
      } catch (AWTException e) {
        e.printStackTrace();
      }
    }
  }
  
}
