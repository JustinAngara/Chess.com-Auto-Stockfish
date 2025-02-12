package com.chepuz.main;

import java.awt.AWTException;

public class RunThread implements Runnable{
  static boolean isOn = false;
  @Override
  public void run() {
    while(isOn) {
      // TODO Auto-generated method stub
      try {
        System.out.println("running and shit");
        Main.goMove();
      } catch (AWTException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
}
