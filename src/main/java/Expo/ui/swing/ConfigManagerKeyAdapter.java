package Expo.ui.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class ConfigManagerKeyAdapter extends KeyAdapter {
   final ConfigManagerWindow Y;

   ConfigManagerKeyAdapter(ConfigManagerWindow var1) {
      this.Y = var1;
   }

   public void keyPressed(KeyEvent var1) {
      ConfigManagerWindow.P(this.Y, true);
   }
}
