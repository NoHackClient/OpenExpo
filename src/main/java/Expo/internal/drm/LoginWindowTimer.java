package Expo.internal.drm;

import java.awt.event.ActionListener;
import javax.swing.Timer;











class LoginWindowTimer extends Timer {
   final LoginWindow a;

   LoginWindowTimer(LoginWindow var1, int var2, ActionListener var3) {
      super(var2, var3);
      this.a = var1;
      this.setRepeats(false);
      this.start();
   }
}
