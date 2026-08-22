package Expo.ui.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ConfigManagerWindowCloseAdapter extends WindowAdapter {
   private static final long a = 100160086535595L;
   final ConfigManagerWindow W;

   public void windowClosing(WindowEvent var1) {
      ConfigManagerWindow.S(this.W,0L);
   }

   ConfigManagerWindowCloseAdapter(ConfigManagerWindow var1) {
      this.W = var1;
   }
}
