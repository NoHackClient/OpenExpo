package Expo.ui.swing;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.lwjgl.opengl.Display;











public class VisualSpoofWindow {
   private final JFrame P;
   private int a;
   private boolean p;
   private final VisualSpoofPreviewPanel D;
   private int R;
   private final AtomicBoolean C = new AtomicBoolean(false);

   private GraphicsConfiguration w() {
      GraphicsDevice[] var1 = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
      int var2 = Display.getX();
      int var3 = Display.getY();
      int var4 = var2 + Math.max(0, Display.getWidth() / 2);
      int var5 = var3 + Math.max(0, Display.getHeight() / 2);

      for (GraphicsDevice var9 : var1) {
         GraphicsConfiguration var10 = var9.getDefaultConfiguration();
         if (var10.getBounds().contains(var4, var5)) {
            return var10;
         }
      }

      return var1.length > 0 ? var1[0].getDefaultConfiguration() : null;
   }

   public VisualSpoofWindow(Runnable var1) {
      this.p = false;
      this.R = -1;
      this.a = -1;
      JFrame[] var2 = new JFrame[1];
      VisualSpoofPreviewPanel[] var3 = new VisualSpoofPreviewPanel[1];

      try {
         SwingUtilities.invokeAndWait(() -> {
            JFrame var4 = new JFrame("Expo VisualSpoof");
            var4.setAutoRequestFocus(false);
            var4.setFocusableWindowState(true);
            var4.setUndecorated(true);
            var4.setResizable(false);
            var4.setDefaultCloseOperation(0);
            var4.addWindowListener(new VisualSpoofWindowCloseListener(this, var1));
            VisualSpoofPreviewPanel var5x = new VisualSpoofPreviewPanel(null);
            var5x.setFocusable(false);
            var4.setContentPane(var5x);
            var4.setLocation(64, 64);
            var2[0] = var4;
            var3[0] = var5x;
         });
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }

      this.P = var2[0];
      this.D = var3[0];
   }

   public void o(BufferedImage var1) {
      if (var1 != null) {
         this.D.N(var1);
         if (this.P.isDisplayable() && this.C.compareAndSet(false, true)) {
            SwingUtilities.invokeLater(() -> {
               this.C.set(false);
               if (this.P.isDisplayable()) {
                  this.D.repaint();
               }
            });
         }
      }
   }

   public void i(int var1, int var2) {
      SwingUtilities.invokeLater(() -> {
         Dimension var3 = this.c(var1, var2);
         int var4 = Math.max(1, var3.width);
         int var5 = Math.max(1, var3.height);
         boolean var6 = this.R != var4 || this.a != var5;
         if (!this.p) {
            this.R = var4;
            this.a = var5;
            this.D.setPreferredSize(new Dimension(var4, var5));
            this.D.setSize(var4, var5);
            this.P.setSize(var4, var5);
            this.P.validate();
            this.T(var4, var5, true);
            this.P.setVisible(true);
            this.p = true;
         } else if (var6 || this.D.getWidth() <= 0 || this.D.getHeight() <= 0) {
            this.R = var4;
            this.a = var5;
            this.D.setPreferredSize(new Dimension(var4, var5));
            this.D.setSize(var4, var5);
            this.P.setSize(var4, var5);
            this.P.validate();
            this.T(var4, var5, false);
         }
      });
   }

   public void H() {
      SwingUtilities.invokeLater(() -> {
         if (this.P.isDisplayable()) {
            this.P.setVisible(false);
            this.P.dispose();
         }

         this.p = false;
      });
   }

   private void T(int var1, int var2, boolean var3) {
      GraphicsConfiguration var4 = this.w();
      if (var4 == null) {
         var4 = this.P.getGraphicsConfiguration();
      }

      Rectangle var5 = var4.getBounds();
      Insets var6 = Toolkit.getDefaultToolkit().getScreenInsets(var4);
      Rectangle var7 = new Rectangle(var5.x + var6.left, var5.y + var6.top, var5.width - var6.left - var6.right, var5.height - var6.top - var6.bottom);
      byte var8 = 16;
      int var9 = var7.x + var8;
      int var10 = var7.y + var8;
      if (!Display.isFullscreen()) {
         int var11 = Display.getX();
         int var12 = Display.getY();
         int var13 = Display.getWidth();
         int var14 = Display.getHeight();
         int var15 = var11 + var13 + var8;
         int var16 = var11 - var1 - var8;
         int var17 = this.v(var12, var7.y + var8, var7.y + Math.max(var8, var7.height - var2 - var8));
         if (var15 + var1 <= var7.x + var7.width - var8) {
            var9 = var15;
            var10 = var17;
         } else if (var16 >= var7.x + var8) {
            var9 = var16;
            var10 = var17;
         } else {
            var9 = this.v(var11, var7.x + var8, var7.x + Math.max(var8, var7.width - var1 - var8));
            var10 = this.v(var12 + var14 + var8, var7.y + var8, var7.y + Math.max(var8, var7.height - var2 - var8));
         }
      } else {
         var9 = var5.x;
         var10 = var5.y;
      }

      boolean var20 = this.P.getX() < var7.x
         || this.P.getY() < var7.y
         || this.P.getX() + this.P.getWidth() > var7.x + var7.width
         || this.P.getY() + this.P.getHeight() > var7.y + var7.height;
      if (var3 || var20) {
         this.P.setLocation(var9, var10);
      }
   }

   private int v(int var1, int var2, int var3) {
      return var3 < var2 ? var2 : Math.max(var2, Math.min(var3, var1));
   }

   private Dimension c(int var1, int var2) {
      GraphicsConfiguration var3 = this.w();
      double var4 = 1.0;
      double var6 = 1.0;
      Rectangle var8 = null;
      if (var3 != null) {
         AffineTransform var9 = var3.getDefaultTransform();
         if (var9 != null) {
            var4 = Math.max(1.0, var9.getScaleX());
            var6 = Math.max(1.0, var9.getScaleY());
         }

         var8 = var3.getBounds();
      }

      int var11 = Math.max(1, (int)Math.round(var1 / var4));
      int var10 = Math.max(1, (int)Math.round(var2 / var6));
      if (var8 != null) {
         var11 = Math.min(var11, var8.width);
         var10 = Math.min(var10, var8.height);
      }

      return new Dimension(var11, var10);
   }
}
