package Expo.ui.swing;

import Expo.setting.settings.TextSetting;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class ConfigManagerTextSettingListener implements DocumentListener {
   final boolean[] G;
   final JTextField X;
   final TextSetting j;
   final ConfigManagerWindow x;

   private void V() {
      if (!this.G[0]) {
         ConfigManagerWindow.P(this.x, true);
         this.G[0] = true;
         this.j.O(this.X.getText());
         this.G[0] = false;
      }
   }

   public void removeUpdate(DocumentEvent var1) {
      this.V();
   }

   public void insertUpdate(DocumentEvent var1) {
      this.V();
   }

   ConfigManagerTextSettingListener(ConfigManagerWindow var1, boolean[] var2, TextSetting var3, JTextField var4) {
      this.x = var1;
      this.G = var2;
      this.j = var3;
      this.X = var4;
   }

   public void changedUpdate(DocumentEvent var1) {
      this.V();
   }
}
