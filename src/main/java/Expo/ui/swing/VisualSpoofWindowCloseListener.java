package Expo.ui.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class VisualSpoofWindowCloseListener extends WindowAdapter {
   final VisualSpoofWindow T;
   final Runnable X;

   public void windowClosing(WindowEvent var1) {
      this.X.run();
   }

   VisualSpoofWindowCloseListener(VisualSpoofWindow var1, Runnable var2) {
      this.T = var1;
      this.X = var2;
   }
}
