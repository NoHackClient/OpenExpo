package Expo.ui.raven;

import Expo.enums.BlurDirection;
import Expo.module.Category;
import Expo.module.Modules;
import Expo.module.impl.configuration.ClickGUI;
import Expo.module.impl.configuration.Font;
import Expo.util.Sneaky;
import Expo.util.render.CustomFont;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;













public class RavenClickGuiScreen extends GuiScreen {
   private static long[] e;
   public static Map<Category, RavenCategoryPanel> P;
   public ScheduledFuture<?> C;
   private int y;
   private static long[] b;
   public final RavenFramebuffer Z;
   private static long a;
   public static ScheduledExecutorService A;
   private static Map g;
   public RavenAnimation w;
   public RavenAnimation F;
   public static List<Category> h;
   public Runnable t;

   private boolean D() {
      for (RavenCategoryPanel var2 : P.values()) {
         for (RavenModuleRow var4 : var2.s()) {
            for (AbstractRavenSettingRow var6 : var4.H) {
               if (var6 instanceof RavenBindRow && ((RavenBindRow)var6).Y) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void V(Runnable var1) {
      this.t = var1;
   }

   public void keyTyped(char var1, int var2) {

      if (var2 == 1 && !this.D()) {
         this.mc.displayGuiScreen(null);
         Modules.c(0L);
      } else {
         for (RavenCategoryPanel var10 : P.values()) {
            if (var10.D() && !var10.s().isEmpty()) {
               for (RavenElement var12 : var10.s()) {
                  var12.c(var1, var2, 13495899756495L);
               }
            }
         }
      }
   }

   public void initGui() {
      super.initGui();
      new GuiTextField(
            1,
            this.mc.fontRendererObj,
            22,
            this.height - 100,
            150,
            20
         )
         .setMaxStringLength(256);
   }

   public void onGuiClosed() {

      this.F = null;
      if (this.C != null) {
         this.C.cancel(true);
         this.C = null;
      }

      for (RavenCategoryPanel var6 : P.values()) {
         var6.V = false;

         for (RavenElement var8 : var6.s()) {
            var8.W(21955934067838L);
         }
      }
   }

   public void handleMouseInput() {

      super.handleMouseInput();
      int var6 = Mouse.getDWheel();
      if (var6 != 0) {
         this.I((short)0, var6);
      }
   }

   public static CustomFont t() {
      return Font.m(0L);
   }

   static {
      a = 52686871891298L;
   }

   public void P() {
      (this.w = new RavenAnimation(500.0F)).y();
      this.C = A.schedule(() -> (this.F = new RavenAnimation(650.0F)).y(), 650L, TimeUnit.MILLISECONDS);
   }



   public RavenClickGuiScreen(long var1) {
      var1 = a ^ var1;
      long var3 = var1 ^ 107644631695784L;
      int var5 = (int)((var1 ^ 140304623654453L) >>> 32);
      int var6 = (int)((var1 ^ 140304623654453L) << 32 >>> 40);
      int var7 = (int)((var1 ^ 140304623654453L) << 56 >>> 56);
      this.Z = new RavenFramebuffer(var3, BlurDirection.LR);
      this.t = null;
      this.y = 0;
      Expo.internal.jnic.StockClientBootstrap.P(var5);
   }

   public void I(short var1, int var3) {
      if (var3 > 0) {
         this.y = this.y + 30;
      } else if (var3 < 0) {
         this.y = this.y - 30;
      }
   }

   public void drawScreen(int var1, int var2, float var3) {
       try {long var4 = 118078875334807L;

      int var12 = (int)((var4 ^ 70362990281711L) << 48 >>> 48);




      double var24 = ClickGUI.scale.L();
      if (this.y != 0) {
         int var26 = (int)(this.y * 0.15);
         if (var26 == 0) {
            this.y = 0;
         } else {
            for (RavenCategoryPanel var28 : P.values()) {
               var28.k(var28.T() + var26);
            }

            this.y -= var26;
         }
      }

      GL11.glPushMatrix();
      GL11.glScaled(var24, var24, 1.0);
      int var42 = (int)(var1 / var24);
      int var43 = (int)(var2 / var24);
      this.Z.c(0, 0, (int)(this.width / var24), (int)(this.height / var24), 12509909862183L);
      this.Z.a(24462996452178L, 0.0F, 0.0F, (int)(this.width / var24), (int)(this.height / var24), 1.0F, 0.1F);
      int var44 = (int)(this.height / 3 / var24);
      int var29 = (int)(this.width / 2 / var24);
      int var30 = 30 - this.w.F(0, 30, 3);
      long[] var21 = new long[]{1500L};
      long var22 = 2L;
      t().p("E", (var29 + 1 - var30 - 12), var44, 136234257403985L, RenderUtil.M(21658, (short)51297, var22, var21));
      var21 = new long[]{1000L};
      var22 = 2L;
      t().p("x", (var29 - var30 - 4), var44, 136234257403985L, RenderUtil.M(21658, (short)51297, var22, var21));
      var21 = new long[]{500L};
      var22 = 2L;
      t().p("p", (var29 - var30 + 4), var44, 136234257403985L, RenderUtil.M(21658, (short)51297, var22, var21));
      var21 = new long[]{0L};
      var22 = 2L;
      t().p("o", (var29 - var30 + 12), var44, 136234257403985L, RenderUtil.M(21658, (short)51297, var22, var21));
      this.drawVerticalLine(var29 - 27 - var30, var44 - 4, var44 + 12, Color.white.getRGB());
      this.drawVerticalLine(var29 + 27 + var30, var44 - 4, var44 + 12, Color.white.getRGB());
      if (this.F != null) {
         int var31 = this.F.F(0, 54, 2);
         this.drawHorizontalLine(var29 - 27, var29 - 27 + var31, var44 - 4, -1);
         this.drawHorizontalLine(
            var29 + 27,
            var29 + 27 - var31,
            var44 + 12,
            -1
         );
      }

      for (Category var32 : h) {
         RavenCategoryPanel var33 = P.get(var32);
         var33.T(t(), 74323137311508L);
         var33.h(var42, var43);

         for (RavenElement var35 : var33.s()) {
            var35.c(var42, 19302466336767L, var43);
         }
      }

      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      if (this.t != null) {
         this.t.run();
      }

      this.t = null;
      GL11.glPopMatrix();
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }



   public boolean doesGuiPauseGame() {
      return false;
   }

   public void mouseClicked(int var1, int var2, int var3) {
       try {long var4 = 38062450391851L;







      double var16 = ClickGUI.scale.L();
      int var18 = (int)(var1 / var16);
      int var19 = (int)(var2 / var16);
      Iterator var20 = h.stream().map(var0 -> P.get(var0)).iterator();

      label74:
      while (true) {
         RavenCategoryPanel var21 = null;

         while (var20.hasNext()) {
            var21 = (RavenCategoryPanel)var20.next();
            if (var21.w(var18, var19) && !var21.g(12208444526123L, var18, var19) && !var21.p(var18, var19, 40203282441482L) && var3 == 0) {
               var21.m(true);
               var21.u = var18 - var21.X();
               var21.N = var19 - var21.T();
            }

            if (var21.p(var18, var19, 40203282441482L) && var3 == 0 || (var21.w(var18, var19) || var21.p(var18, var19, 40203282441482L)) && var3 == 1) {
               var21.U(!var21.D(), (char)0, 1813062116, (short)24628);
            }

            if (var21.g(12208444526123L, var18, var19) && var3 == 0) {
               var21.S(!var21.g());
            }

            if (var21.D() && !var21.s().isEmpty()) {
               for (RavenElement var23 : var21.s()) {
                  var23.r((char)0, var18, var19, var3, 44844385241715L);
               }
               continue label74;
            }
         }

         if (var21 != null) {
            h.remove(var21.w);
            h.add(var21.w);
         }

         return;
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void mouseReleased(int var1, int var2, int var3) {



      double var9 = ClickGUI.scale.L();
      int var11 = (int)(var1 / var9);
      int var12 = (int)(var2 / var9);
      if (var3 == 0) {
         for (RavenCategoryPanel var14 : P.values()) {
            var14.m(false);
            if (var14.D() && !var14.s().isEmpty()) {
               for (RavenElement var16 : var14.s()) {
                  var16.f(var11, var12, var3, 27125, (short)12922, 25037);
               }
            }
         }
      }
   }



}
