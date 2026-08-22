package Expo.ui.vestige;

import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.Modules;
import Expo.module.impl.configuration.ClickGUI;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Language;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.RotationManager;
import Expo.util.Sneaky;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


public class VestigeClickGuiScreen extends GuiScreen {
   private static int c;
   private static Color k;
   private static Long[] n;
   private static String[] d;
   private static Map<Module, Boolean> p;
   private static long[] m;
   public final int Y;
   private static long a;
   private int R;
   private static int u;
   private Module o;
   private static Map i;
   private static String[] b;
   private static Integer[] g;
   private VestigeSelectedSetting s;
   private static Map r;
   private static Color q;
   private long F;
   private static Map<Category, List<Module>> E;
   private static Map<Category, ClickGuiPanel> L;
   private static Color w;
   private static int j;
   private String B;
   private static int Q;
   private static Map e;
   private static long[] f;
   public final int D;
   public final int M;
   private static Color h;
   private static Color t;
   public final int O;
   private static Object[] v;
   private static Color Z;
   private TextSetting K;
   private boolean I;


   public VestigeClickGuiScreen() {
      this.D = 20;
      this.M = 18;
      this.O = 14;
      this.Y = 0;
      this.B = "";
      M();
   }

   protected void keyTyped(char var1, int var2) {




      if (var2 == 1) {
         if (this.K != null) {
            this.Q();
         } else {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null) {
               this.mc.setIngameFocus();
            }

            this.j(1310, 48485, (char)41992);
         }
      } else if (this.K != null) {
         this.A(var1, var2);
      } else {
         this.Y(101849160492560L, var2);
      }
   }

   private Color z(Color var1, Color var2, double var3) {
      if (var3 > 1.0) {
         double var5 = var3 % 1.0;
         int var7 = (int)var3;
         var3 = var7 % 2 == 0 ? var5 : 1.0 - var5;
      }

      double var10 = 1.0 - var3;
      int var11 = (int)(var1.getRed() * var10 + var2.getRed() * var3);
      int var8 = (int)(var1.getGreen() * var10 + var2.getGreen() * var3);
      int var9 = (int)(var1.getBlue() * var10 + var2.getBlue() * var3);
      return new Color(var11, var8, var9);
   }

   private int I(Module var1, long var2, int var4, int var5, int var6, int var7, int var8, int var9) throws Throwable {



      List var14 = this.z(var1);
      int var15 = 0;

      for (Setting var17 : (Iterable<Setting>)(var14)) {
         int var18 = var4 + 1;
         int var19 = var5 - 1;
         int var20 = var6;
         int var21 = var6 + 14;
         if (var15 == 0) {
            var6 += 0;
            var20 = var6;
            var21 = var6 + 14;
         }

         if (this.d(var7, var8, var18, var20, var19, var21)) {
            this.g(var1, var17, var18, var19, var7, var9, 4230632914209L);
         }

         var6 += 14;
         var15++;
      }

      int var23 = var4 + 1;
      int var24 = var5 - 1;
      int var25 = var6;
      int var26 = var6 + 14;
      if (this.d(var7, var8, var23, var25, var24, var26)) {
         this.X(var1, 117129471363386L, var9);
      }

      return var6 + 14;
   }

   private void U(Module var1, int var2, int var3, int var4, int var5, byte var6, int var7, int var8) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var9 = ((long)var6 << 56 | (long)var7 << 32 >>> 8 | (long)var8 << 40 >>> 40) ^ a;
      long var11 = var9 ^ 111327569154952L;
      long var13 = var9 ^ 110088101975307L;
      long var15 = var9 ^ 82519644523746L;
      int var17 = (int)((var9 ^ 64958992063289L) >>> 32);
      int var18 = (int)((var9 ^ 64958992063289L) << 32 >>> 48);
      int var19 = (int)((var9 ^ 64958992063289L) << 48 >>> 48);
      long var20 = var9 ^ 23798664653348L;
      CustomFont var22 = this.s(var15);
      if (var1.o()) {
         for (int var23 = var2; var23 < var4; var23++) {
            Gui.drawRect(var23, var3, var23 + 1, var5, this.S(var20, var23));
         }
      } else {
         Gui.drawRect(var2, var3, var4, var5, w.getRGB());
      }

      if (this.B(var1)) {
         double var28 = var4 - 11;
         double var25 = var3 + 7;
         int var27 = new Color(225, 225, 225).getRGB();
         Gui.drawRect((int)var28, (int)var25, var4 - 5, (int)(var25 + 1.0), var27);
         Gui.drawRect((int)(var28 + 1.0), (int)(var25 + 1.0), var4 - 6, (int)(var25 + 2.0), var27);
         Gui.drawRect((int)(var28 + 2.0), (int)(var25 + 2.0), var4 - 7, (int)(var25 + 3.0), var27);
      }

      var22.T(
         var11, this.a(var1.Q(var17, (char)var18, (char)var19), var4 - var2 - 18, var13), var2 + 4, var3 + 5, Z.getRGB()
      );
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   private void g(Module var1, Setting var2, int var3, int var4, int var5, int var6, long var7) throws Throwable {






      if (var2 instanceof BooleanSetting) {
         ((BooleanSetting)var2).W(112370683098682L);
      } else if (var2 instanceof ModeSetting) {
         ModeSetting var19 = (ModeSetting)var2;
         if (var6 == 1) {
            var19.X();
         } else {
            var19.w(53199746843302L);
         }
      } else if (var2 instanceof NumberSetting) {
         this.s = new VestigeSelectedSetting(var2, null);
         this.a((NumberSetting)var2, 121452147764704L, var3, var4, var5);
      } else if (var2 instanceof PercentageSetting) {
         this.s = new VestigeSelectedSetting(var2, null);
         this.C((PercentageSetting)var2, var3, 65480692878177L, var4, var5);
      } else if (var2 instanceof ColorSetting) {
         this.s = new VestigeSelectedSetting(var2, null);
         this.U((ColorSetting)var2, var3, var4, var5, 92078714498853L);
      } else if (var2 instanceof TextSetting) {
         this.K = (TextSetting)var2;
         this.B = this.K.X();
      }
   }

   private void k(Module var1, NumberSetting var2, int var3, int var4, int var5, int var6, int var7, short var8, char var9, int var10) {
      long var11 = ((long)var8 << 48 | (long)var9 << 48 >>> 16 | (long)var10 << 32 >>> 32) ^ a;
      long var13 = var11 ^ 19195947813338L;
      int var15 = (int)((var11 ^ 103009702856462L) >>> 56);
      long var16 = (var11 ^ 103009702856462L) << 8 >>> 8;
      long var18 = var11 ^ 102570975700050L;
      long var20 = var11 ^ 17776830205785L;
      long var22 = var11 ^ 69509843605168L;
      CustomFont var24 = this.s(var22);
      double var25 = var5 - var3;
      double var27 = var3 + (var2.L() - var2.i()) * var25 / (var2.F() - var2.i());
      Gui.drawRect(var3, var4, var5, var6, q.getRGB());
      Gui.drawRect(
         var3,
         var4,
         (int)var27,
         var6,
         new Color(25, 25, 25).getRGB()
      );
      String var29 = var2.e((byte)var15, var1, var16) + " : " + var2.L();
      var24.T(var13, this.a(var29, var5 - var3 - 10, var20), var3 + 4, var4 + 3, t.getRGB());
      if (this.F(var2)) {
         this.a(var2, var18, var3, var5, var7);
      }
   }

   private int H( float var2, long var3) {
      var3 += 40L;
      float var9 = (float)((System.currentTimeMillis() + var3) % (int)(var2 * 1000.0F)) / (var2 * 1000.0F);
      float var10 = var9 * 2.0F;
      if (var10 > 1.0F) {
         var10 = 2.0F - var10;
      }

      return this.z(
            new Color(0, 200, 235),
            new Color(20, 75, 230),
            var10
         )
         .getRGB();
   }


   private void N(Module var1, ColorSetting var2, int var3, short var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      long var11 = ((long)var3 << 32 | (long)var4 << 48 >>> 32 | (long)var10 << 48 >>> 48) ^ a;
      long var13 = var11 ^ 106136566550234L;
      int var15 = (int)((var11 ^ 48779810111502L) >>> 56);
      long var16 = (var11 ^ 48779810111502L) << 8 >>> 8;
      long var18 = var11 ^ 107581453895769L;
      long var20 = var11 ^ 19174384607639L;
      long var22 = var11 ^ 86632618725808L;
      long var24 = var11 ^ 23113908418788L;
      CustomFont var26 = this.s(var22);
      double var27 = var7 - var5;
      float[] var29 = Color.RGBtoHSB(
         var2.k(var24) >> 16 & 255,
         var2.k(var24) >> 8 & 255,
         var2.k(var24) & 255,
         null
      );
      double var30 = var5 + var29[0] * var27;
      Gui.drawRect(var5, var6, var7, var8, q.getRGB());
      Gui.drawRect(
         var5,
         var6,
         (int)var30,
         var8,
         new Color(25, 25, 25).getRGB()
      );
      Gui.drawRect(var7 - 10, var6 + 2, var7 - 3, var8 - 2, var2.k(var24));
      String var32 = var2.e((byte)var15, var1, var16) + " : #" + var2.Q().toUpperCase();
      var26.T(var13, this.a(var32, var7 - var5 - 22, var18), var5 + 4, var6 + 3, t.getRGB());
      if (this.F(var2)) {
         this.U(var2, var5, var7, var9, var20);
      }
   }

   private void d(Module var1, HeaderSetting var2, int var3, int var4, int var5, int var6, long var7) {





      CustomFont var17 = this.s(13213047758594L);
      Gui.drawRect(var3, var4, var5, var6, q.getRGB());
      var17.T(37697014677608L, this.a(var2.U(133728681395781L, var1), var5 - var3 - 10, 38580066682603L), var3 + 5, var4 + 3, h.getRGB());
   }

   private void U(Module var1, Setting var2, int var3, long var4, int var6, int var7, int var8, int var9) {


      int var14 = 20296;






      int var27 = 2086799432;

      if (var2 instanceof BooleanSetting) {
         this.F(var1, (BooleanSetting)var2, var3, var6, var7, 88893057151773L, var8);
      } else if (var2 instanceof ModeSetting) {
         this.v(var1, (ModeSetting)var2, var3, 185474325321L, var6, var7, var8, (byte)160);
      } else if (var2 instanceof NumberSetting) {
         this.k(var1, (NumberSetting)var2, var3, var6, var7, var8, var9, (short)0, (char)15980, var27);
      } else if (var2 instanceof PercentageSetting) {
         this.I(var1, (PercentageSetting)var2, var3, var6, var7, var8, var9, 7131923145670L);
      } else if (var2 instanceof ColorSetting) {
         this.N(var1, (ColorSetting)var2, 20382, (short)50076, var3, var6, var7, var8, var9, var14);
      } else if (var2 instanceof HeaderSetting) {
         this.d(var1, (HeaderSetting)var2, var3, var6, var7, var8, 91641500148269L);
      } else if (var2 instanceof TextSetting) {
         this.X((byte)0, var1, (TextSetting)var2, var3, var6, 87686144156819L, var7, var8);
      } else {
         this.B(var1, var2, var3, var6, var7, 80920452746949L, var8);
      }
   }

   private void F(Module var1, BooleanSetting var2, int var3, int var4, int var5, long var6, int var8) {






      CustomFont var18 = this.s(13213047758594L);
      Gui.drawRect(var3, var4, var5, var8, q.getRGB());
      int var19 = var2.c() ? k.getRGB() : t.getRGB();
      var18.T(37697014677608L, this.a(var2.e((byte)0, var1, 121580628905660L), var5 - var3 - 10, 38580066682603L), var3 + 5, var4 + 3, var19);
   }

   public void drawScreen(int var1, int var2, float var3) {
       try {long var4 = 17448604102766L;








      Y(2549667498153L);
      M();
      this.B(2806383397540L);
      this.n(126550276172091L);
      double var19 = ClickGUI.scale.L() * 0.7;
      int var21 = (int)(var1 / var19);
      int var22 = (int)(var2 / var19);
      if (this.R != 0) {
         int var23 = (int)(this.R * 0.15);
         if (var23 == 0) {
            this.R = 0;
         } else {
            for (ClickGuiPanel var25 : L.values()) {
               ClickGuiPanel var26 = var25;
               ClickGuiPanel.set_E(var26, ClickGuiPanel.get_E(var26) + var23);
            }

            this.R -= var23;
         }
      }

      for (ClickGuiPanel var40 : L.values()) {
         if (ClickGuiPanel.get_O(var40)) {
            int var42 = var21 - ClickGuiPanel.get_z(var40);
            int var44 = var22 - ClickGuiPanel.get_d(var40);
            if (ClickGuiPanel.get_w(var40) || Math.abs(var42) > 3 || Math.abs(var44) > 3) {
               ClickGuiPanel.set_w(var40, true);
               ClickGuiPanel.set_v(var40, ClickGuiPanel.get_e(var40) + var42);
               ClickGuiPanel.set_E(var40, ClickGuiPanel.get_h(var40) + var44);
            }
         }
      }

      GL11.glPushMatrix();
      GL11.glScaled(var19, var19, 1.0);

      for (Category var45 : Category.values()) {
         ClickGuiPanel var27 = L.get(var45);
         int var28 = ClickGuiPanel.get_v(var27);
         int var29 = ClickGuiPanel.get_E(var27);
         int var30 = var28 + 116;
         int var31 = var29 + 20;
         this.q(var45, var28, 128967919821462L, var29, var30, var31);
         int var32 = var29 + 20;
         boolean var33 = true;
         if (ClickGuiPanel.get_S(var27)) {
            for (Module var35 : this.O(var45)) {
               int var36 = var32;
               int var37 = var32 + 18;
               this.U(var35, var28, var36, var30, var37, (byte)0, 4872547, 15864346);
               if (var33 && var35.o()) {
                  this.drawGradientRect(var28, var36, var30, var36 + 3, 1610612736, 83886080);
               }

               var32 += 18;
               if (this.O(var35)) {
                  var32 = this.g(var35, var28, var30, 115844804322685L, var32, var21);
               }

               var33 = false;
            }
         }
      }

      GL11.glPopMatrix();
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private List<Module> O(Category var1) {
      List var2 = E.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   private void J(Module var1, int var2, short var3, long var4) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var6 = ((long)var3 << 48 | 19237122751156L) ^ a;
      int var8 = (int)((var6 ^ 108387540786789L) >>> 48);
      long var9 = (var6 ^ 108387540786789L) << 16 >>> 16;
      long var11 = var6 ^ 16456983159172L;
      long var13 = var6 ^ 92674763003876L;
      if (var2 == 0) {
         if (this.mc.thePlayer != null) {
            var1.u((short)var8, var9);
         } else {
            var1.I(var11, !var1.o());
            Modules.c(var13);
         }
      } else if (var2 == 1 && this.B(var1)) {
         p.put(var1, !this.O(var1));
      }
   }

   private String q(Category var1, long var2) {





      return var1.x(12139, 2577, (short)47391).replace((char)95, (char)32);
   }

   private void Y(long var1, int var3) {



      if (this.o != null) {
         this.o.z(118276941480361L, var3);
         this.o = null;
         Modules.c(79608920009898L);
      }
   }

   private void a(NumberSetting var1, long var2, int var4, int var5, int var6) {
      var2 = a ^ var2;
      int var7 = (int)((var2 ^ 84536870594631L) >>> 56);
      long var8 = (var2 ^ 84536870594631L) << 8 >>> 8;
      float var10 = var5 - var4;
      float var11 = MathUtil.q(var6 - var4, 0.0F, var10);
      float var12 = var11 / var10;
      var1.o((byte)var7, var8, var12 * (var1.F() - var1.i()) + var1.i());
   }


   private void C(PercentageSetting var1, int var2, long var3, int var5, int var6) {



      double var10 = var5 - var2;
      double var12 = MathUtil.R(var6 - var2, 0.0, var10);
      int var14 = (int)Math.round(var12 / var10 * 100.0);
      var1.b(2856, 2304136789L, var14);
   }

   private boolean B(Module var1) {
      return true;
   }

   private void v(Module var1, ModeSetting var2, int var3, long var4, int var6, int var7, int var8, byte var9) {
      long var10 = (47481427282176L | (long)var9 << 56 >>> 56) ^ a;
      int var12 = (int)((var10 ^ 72073656517462L) >>> 48);
      int var13 = (int)((var10 ^ 72073656517462L) << 16 >>> 48);
      int var14 = (int)((var10 ^ 72073656517462L) << 32 >>> 32);
      long var15 = var10 ^ 4630954100786L;
      int var17 = (int)((var10 ^ 80180819658470L) >>> 56);
      long var18 = (var10 ^ 80180819658470L) << 8 >>> 8;
      long var20 = var10 ^ 5951220289201L;
      long var22 = var10 ^ 46709253085016L;
      CustomFont var24 = this.s(var22);
      Gui.drawRect(var3, var6, var7, var8, q.getRGB());
      String var25 = var2.e((byte)var17, var1, var18) + " : " + var2.a((char)var12, (short)var13, var1, var14);
      var24.T(var15, this.a(var25, var7 - var3 - 10, var20), var3 + 5, var6 + 3, t.getRGB());
   }

   private List<Setting> z(Module var1) {
      ArrayList var2 = new ArrayList();

      for (Setting var4 : var1.w()) {
         if (var4 instanceof BooleanSetting
            || var4 instanceof ModeSetting
            || var4 instanceof NumberSetting
            || var4 instanceof PercentageSetting
            || var4 instanceof ColorSetting
            || var4 instanceof HeaderSetting
            || var4 instanceof TextSetting) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public void onGuiClosed() {



      super.onGuiClosed();
      this.j(1310, 48485, (char)41992);
   }

   private CustomFont s(long var1) {


      return Font.m(23209167808904L);
   }

   private void B(Module var1, Setting var2, int var3, int var4, int var5, long var6, int var8) {






      CustomFont var18 = this.s(13213047758594L);
      Gui.drawRect(var3, var4, var5, var8, q.getRGB());
      var18.T(37697014677608L, this.a(var2.e((byte)0, var1, 121580628905660L), var5 - var3 - 10, 38580066682603L), var3 + 5, var4 + 3, t.getRGB());
   }

   public void initGui() {

      super.initGui();
      Y(2549667498153L);
      M();
      this.I = false;
      this.s = null;
      this.R = 0;
      this.F = System.currentTimeMillis();
   }

   private void X(byte var1, Module var2, TextSetting var3, int var4, int var5, long var6, int var8, int var9) {
      long var10 = ((long)var1 << 56 | 87686144156819L) ^ a;
      long var12 = var10 ^ 106485639931137L;
      int var14 = (int)((var10 ^ 48394242632661L) >>> 56);
      long var15 = (var10 ^ 48394242632661L) << 8 >>> 8;
      long var17 = var10 ^ 107230380499842L;
      long var19 = var10 ^ 86399652084331L;
      CustomFont var21 = this.s(var19);
      Gui.drawRect(var4, var5, var8, var9, q.getRGB());
      String var22 = this.K == var3 ? this.B + "_" : var3.X();
      String var23 = var3.e((byte)var14, var2, var15) + " : " + var22;
      var21.T(var12, this.a(var23, var8 - var4 - 10, var17), var4 + 5, var5 + 3, t.getRGB());
   }

   private int j(Module var1, int var2, int var3, int var4, int var5, short var6, int var7) {
      long var8 = ((long)var4 << 32 | (long)var5 << 48 >>> 32 | (long)var6 << 48 >>> 48) ^ a;
      long var10 = var8 ^ 131766091641713L;
      long var12 = (var8 ^ 64573270608002L) >>> 16;
      int var14 = (int)((var8 ^ 64573270608002L) << 48 >>> 48);
      long var15 = var8 ^ 130329525695986L;
      long var17 = var8 ^ 98519255493659L;
      long var19 = var8 ^ 27754493833594L;
      CustomFont var21 = this.s(var17);
      int var22 = var3 + 14;
      Gui.drawRect(var2, var3, var7, var22, q.getRGB());
      String var23 = var1.h() == 0 ? "NONE" : KeyBindUtil.p(var12, (char)var14, var1.h());
      String var24 = this.o == var1 ? "Bind : " + Language.z("clickgui.bind.press", var19) : "Bind : " + var23;
      var21.T(var10, this.a(var24, var7 - var2 - 10, var15), var2 + 5, var3 + 3, t.getRGB());
      return var22;
   }

   public static void q(int var0, char var1, short var2) {
      long var3 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      int var5 = (int)((var3 ^ 115671712918483L) >>> 56);
      long var6 = (var3 ^ 115671712918483L) << 8 >>> 8;
      long var8 = var3 ^ 22949780781753L;
      L.clear();
      p.clear();
      Minecraft var10 = MinecraftRef.c((byte)var5, var6);
      ScaledResolution var11 = new ScaledResolution(var10);
      int var12 = Math.max(1, Math.max(1, var11.getScaledWidth() - 40) / 126);
      Category[] var13 = Category.values();

      for (int var14 = 0; var14 < var13.length; var14++) {
         int var15 = var14 % var12;
         int var16 = var14 / var12;
         int var17 = 40 + var15 * 126;
         int var18 = 50 + var16 * 140;
         L.put(var13[var14], new ClickGuiPanel(var17, var8, var18, false, null));
      }
   }

   private boolean d(int var1, int var2, int var3, int var4, int var5, int var6) {
      return var1 >= var3 && var1 <= var5 && var2 >= var4 && var2 <= var6;
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var5 = Mouse.getDWheel();
      if (var5 != 0) {
         this.x(var5);
      }
   }

   private void A(char var3, int var4) {
      if (this.K != null) {
         if (var4 == 28 || var4 == 156) {
            this.Q();
         } else if (var4 == 14) {
            if (!this.B.isEmpty()) {
               this.B = this.B.substring(0, this.B.length() - 1);
            }
         } else if (GuiScreen.isCtrlKeyDown() && var4 == 47) {
            String var5 = GuiScreen.getClipboardString();
            if (var5 != null) {
               this.B = this.B + var5;
            }
         } else {
            if (ChatAllowedCharacters.isAllowedCharacter(var3)) {
               this.B = this.B + var3;
            }
         }
      }
   }


   private static void M() {
      if (E.isEmpty()) {
         for (Category var3 : Category.values()) {
            E.put(var3, new ArrayList<>());
         }

         for (Module var5 : ModuleManager.S) {
            E.computeIfAbsent(var5.f(), var0 -> new ArrayList<>()).add(var5);
         }
      }
   }

   private boolean F(Setting var1) {
      return this.s != null && VestigeSelectedSetting.R(this.s) == var1 && this.I;
   }

   private int g(Module var1, int var2, int var3, long var4, int var6, int var7) {






      List var13 = this.z(var1);
      int var14 = 0;

      for (Setting var16 : (Iterable<Setting>)(var13)) {
         int var17 = var2 + 1;
         int var18 = var3 - 1;
         int var19 = var6;
         int var20 = var6 + 14;
         if (var14 == 0) {
            Gui.drawRect(var17, var19, var18, var19 + 0, q.getRGB());
            var6 += 0;
            var19 = var6;
            var20 = var6 + 14;
         }

         this.U(var1, var16, var17, 122252038458843L, var19, var18, var20, var7);
         var6 += 14;
         var14++;
      }

      return this.j(var1, var2 + 1, var6, 22734, 13521, (short)29411, var3 - 1);
   }

   private void Q() {
      if (this.K != null) {
         this.K.O(this.B);
         this.K = null;
         this.B = "";
      }
   }

   private void U(ColorSetting var1, int var2, int var3, int var4, long var5) {
      long var7 = var5 ^ 4903815534963L;
      double var9 = var3 - var2;
      double var11 = MathUtil.R(var4 - var2, 0.0, var9);
      float var13 = (float)(var11 / var9);
      Color var14 = new Color(var1.k(var7));
      float[] var15 = Color.RGBtoHSB(var14.getRed(), var14.getGreen(), var14.getBlue(), null);
      float var16 = var15[1] <= 0.05F ? 0.9F : var15[1];
      float var17 = var15[2] <= 0.05F ? 0.95F : var15[2];
      int var18 = Color.HSBtoRGB(var13, var16, var17) & 16777215;
      var1.e(String.format("%06X", var18));
   }

   protected void mouseClicked(int var1, int var2, int var3) {
       try {long var4 = 41053911563077L;







      super.mouseClicked(var1, var2, var3);
      if (this.o != null && var3 > 2) {
         this.o.z(118276941480361L, KeyBindUtil.w((char)0, var3, 132797583844084L));
         this.o = null;
         Modules.c(79608920009898L);
      } else {
         double var18 = ClickGUI.scale.L();
         int var20 = (int)(var1 / var18);
         int var21 = (int)(var2 / var18);
         this.Q();
         this.I = true;

         for (Category var25 : Category.values()) {
            ClickGuiPanel var26 = L.get(var25);
            int var27 = ClickGuiPanel.get_v(var26);
            int var28 = ClickGuiPanel.get_E(var26);
            int var29 = var27 + 116;
            int var30 = var28 + 20;
            if (this.d(var20, var21, var27, var28, var29, var30)) {
               this.d(var26, var20, var21, var3);
            } else {
               int var31 = var28 + 20;
               if (ClickGuiPanel.get_S(var26)) {
                  for (Module var33 : this.O(var25)) {
                     int var34 = var31;
                     int var35 = var31 + 18;
                     if (this.d(var20, var21, var27, var34, var29, var35)) {
                        this.J(var33, var3, (short)0, 19237122751156L);
                     }

                     var31 += 18;
                     if (this.O(var33)) {
                        var31 = this.I(var33, 77463035357270L, var27, var29, var31, var20, var21, var3);
                     }
                  }
               }
            }
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private void d(ClickGuiPanel var1, int var2, int var3, int var4) {
      if (var4 == 0 || var4 == 1) {
         ClickGuiPanel.set_O(var1, true);
         ClickGuiPanel.set_w(var1, false);
         ClickGuiPanel.set_Q(var1, var4);
         ClickGuiPanel.set_z(var1, var2);
         ClickGuiPanel.set_d(var1, var3);
         ClickGuiPanel.set_e(var1, ClickGuiPanel.get_v(var1));
         ClickGuiPanel.set_h(var1, ClickGuiPanel.get_E(var1));
      }
   }

   private void B(long var1) {


      this.K(139064014055973L, this.mc.gameSettings.keyBindForward);
      this.K(139064014055973L, this.mc.gameSettings.keyBindLeft);
      this.K(139064014055973L, this.mc.gameSettings.keyBindRight);
      this.K(139064014055973L, this.mc.gameSettings.keyBindBack);
      this.K(139064014055973L, this.mc.gameSettings.keyBindJump);
   }

   static {
      a = 14637767574010L;
      Z = new Color(
         240,
         240,
         240
      );
      t = new Color(
         240,
         240,
         240
      );
      q = new Color(
         50,
         50,
         50
      );
      w = new Color(
         42,
         42,
         42
      );
      k = new Color(
         20, 210, 20
      );
      h = new Color(
         180, 180, 180
      );
      L = new EnumMap<>(Category.class);
      E = new EnumMap<>(Category.class);
      p = new HashMap<>();
   }

   protected void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
      this.I = false;
      this.s = null;

      for (ClickGuiPanel var7 : L.values()) {
         if (ClickGuiPanel.get_O(var7) && ClickGuiPanel.get_Q(var7) == var3) {
            if (var3 == 1 && !ClickGuiPanel.get_w(var7)) {
               ClickGuiPanel.set_S(var7, !ClickGuiPanel.get_S(var7));
            }

            ClickGuiPanel.set_O(var7, false);
            ClickGuiPanel.set_w(var7, false);
            ClickGuiPanel.set_Q(var7, -1);
         }
      }
   }

   private static void Y(long var0) {





      if (L.size() != Category.values().length) {
         q(11538, (char)22135, (short)25573);
      }
   }

   private void I(Module var1, PercentageSetting var2, int var3, int var4, int var5, int var6, int var7, long var8) {







      CustomFont var21 = this.s(13213047758594L);
      double var22 = var5 - var3;
      double var24 = var3 + var2.k() * var22 / 100.0;
      Gui.drawRect(var3, var4, var5, var6, q.getRGB());
      Gui.drawRect(
         var3,
         var4,
         (int)var24,
         var6,
         new Color(25, 25, 25).getRGB()
      );
      String var26 = var2.e((byte)0, var1, 121580628905660L) + " : " + var2.k() + "%";
      var21.T(37697014677608L, this.a(var26, var5 - var3 - 10, 38580066682603L), var3 + 4, var4 + 3, t.getRGB());
      if (this.F(var2)) {
         this.C(var2, var3, 65480692878177L, var5, var7);
      }
   }

   private void X(Module var1, long var2, int var4) {



      if (this.o == null) {
         this.o = var1;
      } else {
         this.o = null;
         if (var4 == 2) {
            var1.z(118276941480361L, 0);
            Modules.c(79608920009898L);
         }
      }
   }

   private void K(long var1, KeyBinding var3) {


      KeyBindUtil.A(82009306480869L, var3.getKeyCode(), GameSettings.isKeyDown(var3));
   }

   private int S(long var1, int var3) {


      int var6 = -252851571;
      return this.H( 4.0F, -var3 * 3L);
   }

   private void n(long var1) {


      if (this.mc.thePlayer == null) {
         this.F = System.currentTimeMillis();
      } else {
         long var5 = System.currentTimeMillis();
         long var7 = Math.max(1L, var5 - this.F);
         this.F = var5;
         float var9 = 0.15F * (float)var7;
         if (Keyboard.isKeyDown(205)) {
            RotationManager.r(RotationManager.p() + var9);
         }

         if (Keyboard.isKeyDown(203)) {
            RotationManager.r(RotationManager.p() - var9);
         }

         if (Keyboard.isKeyDown(200)) {
            RotationManager.v(74908232914960L, RotationManager.s() - var9);
         }

         if (Keyboard.isKeyDown(208)) {
            RotationManager.v(74908232914960L, RotationManager.s() + var9);
         }
      }
   }

   private void j(int var1, int var2, char var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ a;
      long var6 = var4 ^ 70548175423832L;
      this.o = null;
      this.I = false;
      this.s = null;
      this.Q();
      Modules.c(var6);
   }

   private String a(String var1, int var2, long var3) {
      var3 = a ^ var3;
      long var5 = var3 ^ 1139416079744L;
      long var7 = var3 ^ 37670734583827L;
      if (var1 == null) {
         return "";
      }

      CustomFont var9 = this.s(var7);
      if (var9.R(var1, var5) <= var2) {
         return var1;
      }

      String var10 = "...";
      String var11 = var1;

      while (!var11.isEmpty() && var9.R(var11 + var10, var5) > var2) {
         var11 = var11.substring(0, var11.length() - 1);
      }

      return var11 + var10;
   }

   private void x(int var1) {
      if (var1 > 0) {
         this.R = this.R + 30;
      } else if (var1 < 0) {
         this.R = this.R - 30;
      }
   }

   private void q(Category var1, int var2, long var3, int var5, int var6, int var7) {





      CustomFont var16 = this.s(13213047758594L);

      for (int var17 = var2; var17 < var6; var17++) {
         Gui.drawRect(var17, var5, var17 + 1, var7, this.S(90900632844740L, var17));
      }

      var16.T(37697014677608L, this.q(var1, 95043846217018L), var2 + 5, var5 + 6, Z.getRGB());
   }

   private boolean O(Module var1) {
      return p.getOrDefault(var1, false);
   }


}
