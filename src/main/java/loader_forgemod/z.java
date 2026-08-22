package loader_forgemod;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;











public class z extends JPanel {
   private final ResourceBundle l;
   private final JFrame m;
   private PipedInputStream n;
   private PipedOutputStream o;
   private boolean p;
   private static ExecutorService q;
   private JLabel r;
   private JTextField s;
   private JLabel t;
   private JPasswordField u;
   private JButton v;
   private JLabel w;
   private JLabel x;
   private JLabel y;

   public z(JFrame var1) {
      this.m = var1;
      this.l = ResourceBundle.getBundle("tech.skidonion.verification.lang");
      this.aO();
      this.aA();
   }

   private native void aA();

   public native int aB();

   private native void aO();

   static native void aR(z var0, KeyEvent var1);

   static native void aS(z var0, MouseEvent var1);

   static native void aT(z var0, MouseEvent var1);

   static native void aU(z var0, MouseEvent var1);

   static native void aV(z var0, MouseEvent var1);

   static native void aW(z var0, MouseEvent var1);

   static native void aX(z var0, MouseEvent var1);

   static native void aY(z var0, MouseEvent var1);

   static native void aZ(z var0, MouseEvent var1);

   static native void ba(z var0, MouseEvent var1);
}
