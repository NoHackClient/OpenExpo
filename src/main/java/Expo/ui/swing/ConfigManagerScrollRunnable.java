package Expo.ui.swing;

import java.awt.Point;
import javax.swing.JScrollPane;

class ConfigManagerScrollRunnable implements Runnable {
   final JScrollPane v;
   final ConfigManagerWindow W;

   public void run() {
      this.v.getViewport().setViewPosition(new Point(0, 0));
   }

   ConfigManagerScrollRunnable(ConfigManagerWindow var1, JScrollPane var2) {
      this.W = var1;
      this.v = var2;
   }
}
