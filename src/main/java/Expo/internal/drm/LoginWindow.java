package Expo.internal.drm;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow {
   private JPanel y;
   private boolean B;
   private final String a;
   private JButton z;
   private JTextField J;
   private static String[] c;
   private static Map e;
   private static long[] f;
   private static File P;
   private JFrame T;
   private JLayeredPane j;
   private final CountDownLatch q;
   private volatile String G;
   private JButton F;
   private static String[] d;
   private static long b;
   private JPasswordField u;
   private static Map h;
   public static boolean $skidonion$891820663;

   public LoginWindow(String var1) {
      this.q = new CountDownLatch(1);
      this.G = a(19204,0L);
      this.B = (((b(19200,0L)) & 1) != 0);
      this.a = var1;
   }

   private native void lambda$showUI$0();

   private native void lambda$onLogin$3();

   private static native String a(int var0, long var1);

   private static native void S(Runnable var0);

   private native void lambda$showNotice$8(String var1, int var2, int var3);

   private native void lambda$initUI$4(ActionEvent var1);

   private native void lambda$showNotice$6(String var1, int var2);

   private static native int b(int var0, long var1);

   private static native void lambda$null$7(JDialog var0, ActionEvent var1);

   private native void lambda$initUI$5(ActionEvent var1);

   private native void lambda$showUI$1();

   private static native void D(Window var0);

   private static native void s(Window var0);


   private native void lambda$null$2();

   public native String T();

}
