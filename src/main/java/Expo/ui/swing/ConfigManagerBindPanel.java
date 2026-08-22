package Expo.ui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;


class ConfigManagerBindPanel extends JPanel {
   private int e;
   private static Integer[] h;
   private static Map i;
   private static long[] g;
   private static String[] d;
   private int G;
   private static String[] c;
   private final JTextPane R;
   private boolean H;
   private JScrollPane a;
   private static long b;
   private final List<String> T;
   private final JTextField r;
   private static Map f;

   static native int d(ConfigManagerBindPanel var0);

   public ConfigManagerBindPanel(long var1) {
      super(new BorderLayout(5, 5));
      var1 = b ^ var1;
      this.R = new JTextPane();
      this.r = new JTextField();
      this.T = new ArrayList<>();
      this.e = b(26079,0L);
      this.H = (((b(18423,0L)) & 1) != 0);
      this.G = b(4221,0L);
      this.u(0L);
   }

   static native JTextField S(ConfigManagerBindPanel var0);

   private static native String a(int var0, long var1);

   static native int g(ConfigManagerBindPanel var0, int var1);

   public native void g();

   private static native int b(int var0, long var1);

   private native void u(long var1);

   static native List A(ConfigManagerBindPanel var0);

}
