package Expo.module.impl.visual;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ArrayListBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import Expo.ui.ArrayListEntry;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import Expo.util.render.ShaderRenderer;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;


public class ArrayList extends Module implements EventSubscriber {
   public static TextSetting customText;
   public static NumberSetting barWidth;
   private static final float o = 0.75F;
   public static NumberSetting offsetY;
   public static NumberSetting rectangleYSpace;
   public static ModeSetting gradientMode;
   public static BooleanSetting textShadow;
   public static NumberSetting rectangleYEdge;
   private static Map x;
   private static long a;
   public static ModeSetting barColor;
   private final java.util.ArrayList<Float> d;
   public static PercentageSetting backgroundOpacity;
   public static ColorSetting customColor;
   private static Object[] C;
   public static BooleanSetting bar;
   private static String[] E;
   private static Map h;
   public static BooleanSetting suffixNameLowercase;
   public static ModeSetting mode;
   public static BooleanSetting showSuffix;
   private static String[] e;
   private static String[] b;
   public static BooleanSetting moduleNameLowercase;
   public static NumberSetting scale;
   public static BooleanSetting icons;
   public static ColorSetting textCustomColor;
   public static BooleanSetting splitSuffixAndName;
   public static ModeSetting textColor;
   public static BooleanSetting onlyShowSuffixModules;
   public static NumberSetting offsetX;
   private final java.util.ArrayList<ArrayListEntry> U;
   private static long[] m;

   private static void N(CustomFont var0, String var1, float var2, float var3, int var4, boolean var5, float var6, long var7, double var9) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {






      String var16 = textColor.Y();
      boolean var17 = gradientMode != null
         && gradientMode.R("LEFT_RIGHT")
         && (var16.equals("THEME") || var16.equals("THEME_CUSTOM"));
      if (var17) {
         m(var0, var1, var2, 43968468732776L, var3, var4, var5, var6, var9, var16.equals("THEME_CUSTOM"));
      } else {
         g(28962, var0, var1, 16056385, var2, var3, var4, var5, (byte)51, var6);
      }
   }

   public String g(long var1) {
      return mode.Y();
   }

   private void D(float var1, float var2, float var3, float var4, int var5, float var6, boolean var7, boolean var8, boolean var9, boolean var10, long var11) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      boolean var15 = !var10;
      boolean var16 = !var9;
      boolean var17 = !var8;
      boolean var18 = !var7;
      float var19 = var6;
      int var20 = var5;
      float var21 = var4;
      float var22 = var3;
      float var23 = var2;
      float var24 = var1;
      this.K(119483497834473L, var24, var23, var22, var21, var20, var19, var18, var17, var16, var15);
   }

   private ArrayListRect h(
      int var1, float var2, boolean var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10, boolean var11, float var12
   ) {
      for (int var13 = var1; var13 < this.U.size(); var13++) {
         ArrayListEntry var14 = this.U.get(var13);
         float var15 = MathUtil.k(ArrayListEntry.l(var14), ArrayListEntry.f(var14), var12);
         var15 = MathUtil.q(var15, 0.0F, 1.0F);
         boolean var16 = ArrayListEntry.w(var14);
         float var17 = B(var15);
         if ((var16 || !(var17 <= 0.0F)) && (ArrayListEntry.i(var14) || !var11)) {
            float var18 = ArrayListEntry.j(var14) + var6 + var7;
            float var19 = ArrayListEntry.T(var14) == 0.0F ? 0.0F : ArrayListEntry.T(var14) + var8 * 2.0F;
            float var20 = var18 + var19 + ArrayListEntry.c(var14);
            float var21 = V(1.0F - var15);
            float var22 = var16 ? 1.0F - var17 : var21;
            float var23 = var22 * (var20 + var5 + 10.0F * var9);
            float var24 = var16 ? 0.0F : var21 * 3.6F * var9;
            return this.r(var3, var4, var5, var20, var23, var2, var2 + var10, var24, var9);
         }
      }

      return null;
   }


   private void m(float var1, float var2, float var3, long var4, float var6, int var7, float var8, ArrayListRect var9, ArrayListRect var10, float var11, boolean var12) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {




      float var19 = 0.05F;
      boolean var20 = var9 != null && Math.abs(Expo.module.impl.visual.ArrayListRect.J(var9) - var2) <= var19;
      float var21 = 0.0F;
      float var22 = 0.0F;
      if (var20) {
         var21 = Math.max(var1, Expo.module.impl.visual.ArrayListRect.H(var9));
         var22 = Math.min(var3, Expo.module.impl.visual.ArrayListRect.N(var9));
         var20 = var22 > var21 + var19;
      }

      boolean var23 = var10 != null && Math.abs(Expo.module.impl.visual.ArrayListRect.e(var10) - var6) <= var19;
      float var24 = 0.0F;
      float var25 = 0.0F;
      if (var23) {
         var24 = Math.max(var1, Expo.module.impl.visual.ArrayListRect.H(var10));
         var25 = Math.min(var3, Expo.module.impl.visual.ArrayListRect.N(var10));
         var23 = var25 > var24 + var19;
      }

      boolean var26 = var20 && var21 <= var1 + var19;
      boolean var27 = var20 && var22 >= var3 - var19;
      boolean var28 = var23 && var24 <= var1 + var19;
      boolean var29 = var23 && var25 >= var3 - var19;
      if (!var20) {
         if (var12) {
            var27 = true;
         } else {
            var26 = true;
         }
      }

      if (!var20 && !var23) {
         this.D(var1, var2, var3, var6, var7, var8, var26, var27, var29, var28, 105458755338260L);
      } else {
         java.util.ArrayList var30 = this.d;
         var30.clear();
         this.z(var30, var1, var1, var3);
         this.z(var30, var3, var1, var3);
         if (var20) {
            this.z(var30, var21, var1, var3);
            this.z(var30, var22, var1, var3);
         }

         if (var23) {
            this.z(var30, var24, var1, var3);
            this.z(var30, var25, var1, var3);
         }

         if (var30.size() >= 2) {
            var30.sort(null);
            float var31 = Math.max(0.0F, var11);
            boolean var32 = var20 && var21 <= var1 + 0.01F;
            boolean var33 = var20 && var22 >= var3 - 0.01F;
            boolean var34 = var23 && var24 <= var1 + 0.01F;
            boolean var35 = var23 && var25 >= var3 - 0.01F;
            if (!var20) {
               if (var12) {
                  var33 = true;
               } else {
                  var32 = true;
               }
            }

            float var36 = (Float)var30.get(0);

            for (int var37 = 1; var37 < var30.size(); var37++) {
               float var38 = (Float)var30.get(var37);
               if (!(var38 <= var36 + 0.01F)) {
                  float var39 = (var36 + var38) * 0.5F;
                  boolean var40 = var20 && var39 >= var21 - 0.01F && var39 <= var22 + 0.01F;
                  boolean var41 = var23 && var39 >= var24 - 0.01F && var39 <= var25 + 0.01F;
                  float var42 = var2 + (var40 ? var31 : 0.0F);
                  float var43 = var6 - (var41 ? var31 : 0.0F);
                  var42 = Math.round(var42 * 2.0F) / 2.0F;
                  var43 = Math.round(var43 * 2.0F) / 2.0F;
                  if (var43 < var42) {
                     float var44 = (var42 + var43) * 0.5F;
                     var42 = var44;
                     var43 = var44;
                  }

                  boolean var54 = var36 <= var1 + 0.01F;
                  boolean var45 = var38 >= var3 - 0.01F;
                  boolean var46 = var54 && !var32;
                  boolean var47 = var54 && !var34;
                  boolean var48 = var45 && !var33;
                  boolean var49 = var45 && !var35;
                  this.K(119483497834473L, var36, var42, var38, var43, var7, var8, var46, var48, var49, var47);
               }
            }

            if (var23 && var25 > var24) {
               float var51 = Math.round(var6 * 2.0F) / 2.0F;
               Expo.util.render.RenderUtil.c(125644905353792L, var24, var51 - var31, var25, var51 + var31, var7);
            }
         }
      }
   }

   private static float V(long var0, float var2, boolean var3, boolean var4) {
      return (2.0F + (var3 && var4 ? 1.0F : 0.0F)) * var2 + I(var2);
   }

   private static float V(float var0) {
      var0 = MathUtil.q(var0, 0.0F, 1.0F);
      return var0 * var0 * var0;
   }

   private static void m(CustomFont var0, String var1, float var2, long var3, float var5, int var6, boolean var7, float var8, double var9, boolean var11) {



      float var16 = MathUtil.q(var8, 0.0F, 1.0F);
      float var17 = Math.max(1.0F, var0.R(var1, 52019766876817L));
      var0.A(var1, var2, var5, var6, 103391699357661L, var7, (var5x, var6x, var7x, var8x) -> {


         if (var8x != null) {
            return u(var8x, var16);
         }

         double var17x = (var6x + var7x * 0.5F) / var17;
         int var19 = var11 ? Theme.Z(var9, 23812163747166L, var17x) : Theme.L(var9, 72497430032154L, var17x);
         return u(var19, var16);
      });
   }

   private static float k(float var0, boolean var3, boolean var4) {
      return (2.0F + (!var3 && var4 ? 1.0F : 0.0F)) * var0 + I(var0);
   }

   public void onPostTick(PostTickEvent var1, int var2, long var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      this.N(131948769169783L);
   }

   private static void g(int var0, CustomFont var1, String var2, int var3, float var4, float var5, int var6, boolean var7, byte var8, float var9) {
      long var10 = ((long)var0 << 32 | (long)var3 << 40 >>> 32 | (long)var8 << 56 >>> 56) ^ a;
      long var12 = var10 ^ 66429511616123L;
      var9 = MathUtil.q(var9, 0.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, var9);
      var1.v(var2, var4, var5, var6, var12, var7);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   private void N(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      long var10001 = 59909721217249L;


      int var5 = (int)(var10001 << 48 >>> 48);

      var10001 = 116568669000716L;


      CustomFont var17 = Font.Q(73094284682035L);
      float var18 = scale.L();
      String var19 = mode.Y();
      boolean var20 = var19.contains("RIGHT");
      boolean var21 = var19.contains("CURVE");
      boolean var22 = splitSuffixAndName.c();
      boolean var23 = showSuffix.c();
      boolean var24 = moduleNameLowercase.c();
      boolean var25 = suffixNameLowercase.c();
      float var26 = 2.0F * var18;
      float var27 = V(0L, var18, var20, var21);
      float var28 = k(var18, var20, var21);

      for (int var29 = 0; var29 < this.U.size(); var29++) {
         ArrayListEntry var30 = this.U.get(var29);
         ArrayListEntry.e(var30, ArrayListEntry.l(var30));
         ArrayListEntry.g(var30, false);
      }

      HashMap var42 = new HashMap();

      for (int var43 = 0; var43 < this.U.size(); var43++) {
         ArrayListEntry var31 = this.U.get(var43);
         var42.put(ArrayListEntry.s(var31), var31);
      }

      List var44 = ModuleManager.S;

      for (int var45 = 0; var45 < var44.size(); var45++) {
         Module var32 = (Module)var44.get(var45);
         if (var32.D() && var32.o()) {
            boolean var33 = var23 && var32.r() && var32.g(20826436655957L) != null;
            String var34 = var24 ? var32.t(13948, 53670, (short)var5).toLowerCase() : var32.t(13948, 53670, (short)var5);
            String var35 = var33 ? (var25 ? var32.g(20826436655957L).toLowerCase() : var32.g(20826436655957L)) : "";
            float var36 = var17.R(var34, 52019766876817L) * var18;
            float var37 = var33 ? var17.R(var35, 52019766876817L) * var18 : 0.0F;
            float var38 = var33 && var22 && var21 ? var18 : 0.0F;
            float var39 = var36 + var27 + var28 + var37 + (var37 == 0.0F ? 0.0F : var26 * 2.0F) + var38;
            ArrayListEntry var40 = (ArrayListEntry)var42.get(var32);
            if (var40 == null) {
               var40 = new ArrayListEntry(var32, null);
               var42.put(var32, var40);
               this.U.add(var40);
            }

            ArrayListEntry.k(var40, var34);
            ArrayListEntry.t(var40, var35);
            ArrayListEntry.p(var40, var33);
            ArrayListEntry.p(var40, var36);
            ArrayListEntry.o(var40, var37);
            ArrayListEntry.F(var40, var38);
            ArrayListEntry.z(var40, var39);
            ArrayListEntry.f(var40, true);
            ArrayListEntry.g(var40, true);
         }
      }

      for (int var46 = 0; var46 < this.U.size(); var46++) {
         ArrayListEntry var47 = this.U.get(var46);
         if (!ArrayListEntry.J(var47)) {
            ArrayListEntry.f(var47, false);
         }

         float var48 = ArrayListEntry.w(var47) ? 1.0F : 0.0F;
         float var49 = 0.38F;
         ArrayListEntry var50 = var47;
         ArrayListEntry.X(var50, ArrayListEntry.l(var50) + (var48 - ArrayListEntry.l(var47)) * var49);
         if (Math.abs(var48 - ArrayListEntry.l(var47)) <= 0.015F) {
            ArrayListEntry.X(var47, var48);
         }
      }

      this.U.removeIf(var0 -> !ArrayListEntry.w(var0) && ArrayListEntry.l(var0) <= 0.0F);
      this.U.sort((var0, var1x) -> Float.compare(ArrayListEntry.O(var1x), ArrayListEntry.O(var0)));
   }

   private static void a() {
      C[0] = "T-Qc\u0017`p";
      C[1] = long.class;
      E[1] = "java/lang/Long";
      C[2] = "4*,\u001c\u001bo\u0003=(\u0016VK\u00146r\n";
      C[3] = "?\u001e\u0001yP/M";
      C[4] = void.class;
      E[4] = "java/lang/Void";
      C[5] = "o\u0012FL\u0015>d\u001dW\u0003t0o\u0016SY";
      C[6] = "A\u000b\u0002\u0018\u0001[\b\\\ra\u0015k\n]\r\u0006\u001c\u001b\u001a\bYaH\u000b\u0012\u0019U\u0005\u0001[\u001f\u00143[\u0010\u001a\u0010\u001d\u0002\n\f\u0017\u0013d\b\bJ\f\tXU\u000bL\u0016q";
   }

   private static int u(int var0, float var3) {
      var3 = MathUtil.q(var3, 0.0F, 1.0F);
      int var4 = Math.round((var0 >> 24 & 255) * var3);
      return var0 & 16777215 | var4 << 24;
   }

   private void s(float var1, float var2, float var3, float var4, int var5, long var6, String var8) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      this.f(var1, var2, 49215341321510L, var3, var4, var5, var8, null, null);
   }

   private void K(long var1, float var3, float var4, float var5, float var6, int var7, float var8, boolean var9, boolean var10, boolean var11, boolean var12) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (var8 <= 0.0F) {
         Expo.util.render.RenderUtil.c(125644905353792L, var3, var4, var5, var6, var7);
      } else {
         float var17 = Math.max(0.0F, var5 - var3);
         float var18 = Math.max(0.0F, var6 - var4);
         if (!(var17 <= 0.0F) && !(var18 <= 0.0F)) {
            float var19 = Math.min(var17, var18) * 0.5F * var8;
            if (var19 <= 0.0F) {
               Expo.util.render.RenderUtil.c(125644905353792L, var3, var4, var5, var6, var7);
            } else if (!var9 && !var10 && !var11 && !var12) {
               Expo.util.render.RenderUtil.c(125644905353792L, var3, var4, var5, var6, var7);
            } else {
               try {
                  ShaderRenderer.F(var3, var4, var17, 2001336113403L, var18, var19, var7, var9, var10, var11, var12);
               } catch (Throwable var20) {
                  Expo.util.render.RenderUtil.c(125644905353792L, var3, var4, var5, var6, var7);
               }
            }
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      ArrayListBinder.l(var3, this);
   }

   private void f(float var1, float var2, long var3, float var5, float var6, int var7, String var8, ArrayListRect var9, ArrayListRect var10) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (var8.contains("CURVE")) {
         this.m(var1, var2, var5, 83626183259914L, var6, var7, 0.75F, var9, var10, 0.5F, var8.contains("RIGHT"));
      } else {
         Expo.util.render.RenderUtil.c(125644905353792L, var1, var2, var5, var6, var7);
      }
   }

   public void onRender2D(Render2DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {







      long var10001 = 124394953261363L;


      int var22 = (int)(var10001 << 56 >>> 56);



      var10001 = 116568669000716L;


      CustomFont var38 = Font.Q(73094284682035L);
      GlStateManager.pushMatrix();
      ScaledResolution var39 = var1.C;
      float var40 = scale.L();
      GlStateManager.scale(var40, var40, var40);
      String var41 = mode.Y();
      boolean var42 = var41.contains("RIGHT");
      boolean var43 = var41.contains("CURVE");
      boolean var44 = icons.c();
      boolean var45 = textShadow.c();
      boolean var46 = onlyShowSuffixModules.c();
      boolean var47 = bar.c();
      int var48 = 255 * backgroundOpacity.k() / 100 << 24;
      float var49 = 2.0F * var40;
      float var50 = V(0L, var40, var42, var43);
      float var51 = k(var40, var42, var43);
      float var52 = rectangleYEdge.L() * (var43 ? 2.0F : 1.0F) * var40;
      float var53 = rectangleYSpace.L() * var40;
      float var54 = var38.o(60714858652844L) * var40;
      float var55 = var54 + var52 * 2.0F;
      float var56 = var44 ? (var55 - 4.0F * var40) / var40 : 0.0F;
      float var57 = var44 ? (var43 ? 2.0F : 1.0F) * var40 : 0.0F;
      float var58 = var44 ? var55 + var57 : 0.0F;
      float var59 = (1.0F + offsetY.L()) * var40;
      float var60 = barWidth.L() * var40;
      float var61 = var42 ? var39.getScaledWidth() - offsetX.L() * var40 : offsetX.L() * var40;
      float var62 = var42 ? var61 + (var43 ? 2.0F * var40 : 0.0F) : offsetX.L() * var40 - var60;
      String var63 = customText.X();
      if (!var63.isEmpty()) {
         float var64 = var61 - (var42 ? var38.R(var63, 52019766876817L) * var40 : 0.0F) + (var47 ? (var42 ? 2.0F * var40 : -2.0F * var40) : 0.0F);
         var38.v(var63, var64 / var40, (var59 - (var38.o(60714858652844L) + 1.0F) * var40) / var40, -1, 88827598794260L, true);
      }

      double var110 = 0.0;
      double var66 = 0.0;
      ArrayListRect var68 = null;

      for (int var69 = 0; var69 < this.U.size(); var69++) {
         ArrayListEntry var70 = this.U.get(var69);
         Module var71 = ArrayListEntry.s(var70);
         float var72 = MathUtil.k(ArrayListEntry.l(var70), ArrayListEntry.f(var70), var1.r);
         var72 = MathUtil.q(var72, 0.0F, 1.0F);
         boolean var73 = ArrayListEntry.w(var70);
         float var74 = B(var72);
         if (var73 || !(var74 <= 0.0F)) {
            boolean var75 = ArrayListEntry.i(var70);
            if (var75 || !var46) {
               int var76 = barColor.Y().equals("THEME")
                  ? Theme.S(var110, 35338930340239L)
                  : (barColor.Y().equals("THEME_CUSTOM") ? Theme.X(65301174328177L, var110) : customColor.k(96531491288662L));
               var110 += Theme.offset.L();
               double var77 = var66;
               int var79 = textColor.Y().equals("THEME")
                  ? Theme.S(var77, 35338930340239L)
                  : (textColor.Y().equals("THEME_CUSTOM") ? Theme.X(65301174328177L, var77) : textCustomColor.k(96531491288662L));
               var66 += Theme.offset.L();
               String var80 = ArrayListEntry.V(var70);
               String var81 = ArrayListEntry.m(var70);
               float var82 = ArrayListEntry.j(var70) + var50 + var51;
               float var83 = ArrayListEntry.T(var70) == 0.0F ? 0.0F : ArrayListEntry.T(var70) + var49 * 2.0F;
               float var84 = var82 + var83 + ArrayListEntry.c(var70);
               float var85 = 1.0F - var74;
               float var86 = V(1.0F - var72);
               float var87 = var73 ? var85 : var86;
               float var88 = var87 * (var84 + var58 + 10.0F * var40);
               float var89 = var73 ? 0.0F : var86 * 3.6F * var40;
               float var90 = var73 ? 0.96F + 0.04F * var74 : 1.0F - 0.04F * var86;
               int var91 = u(var48, var74);
               int var92 = u(var76, var74);
               int var93 = u(var79, var74);
               int var94 = u(-5592406, var74);
               float var95 = var59;
               float var96 = var59 + var55;
               float var97 = var73 ? 0.18F + 0.82F * var74 : B(var72);
               float var98 = var59 + (var55 + var53) * var97;
               ArrayListRect var99 = this.r(var42, var61, var58, var84, var88, var95, var96, var89, var40);
               ArrayListRect var100 = var43 ? this.h(var69 + 1, var98, var42, var61, var58, var50, var51, var49, var40, var55, var46, var1.r) : null;
               if (var47) {
                  float var101 = var42 ? var62 + var88 : var62 - var88;
                  float var102 = var101 + var60 * var40 * 0.5F;
                  float var103 = (var95 + var96 + var53) * 0.5F + var89 * 0.5F;
                  GlStateManager.pushMatrix();
                  GlStateManager.translate(var102 / var40, var103 / var40, 0.0F);
                  GlStateManager.scale(var90, var90, 1.0F);
                  GlStateManager.translate(-var102 / var40, -var103 / var40, 0.0F);
                  Expo.util.render.RenderUtil.c(
                     125644905353792L, var101 / var40, (var95 + var89 * 0.5F) / var40, (var101 + var60 * var40) / var40, (var96 + var53 + var89 * 0.5F) / var40, var92
                  );
                  GlStateManager.popMatrix();
               }

               float var112 = (var95 + var55 - var52 - var54 + var89) / var40;
               float var113 = (var95 + 2.0F * var40 + var89) / var40;
               if (var42) {
                  float var114 = var61 - var58 - var84 + var88;
                  float var104 = var61 - var58 + var88;
                  float var105 = (var114 + var104) * 0.5F;
                  float var106 = (var95 + var96) * 0.5F + var89 * 0.5F;
                  GlStateManager.pushMatrix();
                  GlStateManager.translate(var105 / var40, var106 / var40, 0.0F);
                  GlStateManager.scale(var90, var90, 1.0F);
                  GlStateManager.translate(-var105 / var40, -var106 / var40, 0.0F);
                  this.f(Expo.module.impl.visual.ArrayListRect.H(var99), Expo.module.impl.visual.ArrayListRect.e(var99), 49215341321510L, Expo.module.impl.visual.ArrayListRect.N(var99), Expo.module.impl.visual.ArrayListRect.J(var99), var91, var41, var68, var100);
                  if (var44) {
                     float var107 = var61 - var58 + var57 + var88;
                     float var108 = var61 + var88;
                     this.s(var107 / var40, (var95 + var89) / var40, var108 / var40, (var96 + var89) / var40, var91, 50263086633642L, var41);
                     Expo.util.render.RenderUtil.u((var61 - var58 + var57 + 2.0F * var40 + var88) / var40, var113, var56, Category.n(var71.f()));
                  }

                  N(var38, var80, (var61 - var58 - var84 + var50 + var88) / var40, var112, var93, var45, var74, 34995704477528L, var77);
                  if (var75) {
                     g(
                        28962,
                        var38,
                        var81,
                        16056385,
                        (var61 - var58 - var84 + var82 + ArrayListEntry.c(var70) + var49 + var88) / var40,
                        var112,
                        var94,
                        var45,
                        (byte)var22,
                        var74
                     );
                  }

                  GlStateManager.popMatrix();
               } else {
                  float var115 = var61 + var58 - var88;
                  float var116 = var61 + var58 + var84 - var88;
                  float var117 = (var115 + var116) * 0.5F;
                  float var118 = (var95 + var96) * 0.5F + var89 * 0.5F;
                  GlStateManager.pushMatrix();
                  GlStateManager.translate(var117 / var40, var118 / var40, 0.0F);
                  GlStateManager.scale(var90, var90, 1.0F);
                  GlStateManager.translate(-var117 / var40, -var118 / var40, 0.0F);
                  this.f(Expo.module.impl.visual.ArrayListRect.H(var99), Expo.module.impl.visual.ArrayListRect.e(var99), 49215341321510L, Expo.module.impl.visual.ArrayListRect.N(var99), Expo.module.impl.visual.ArrayListRect.J(var99), var91, var41, var68, var100);
                  if (var44) {
                     float var119 = var61 - var88;
                     float var120 = var61 + var58 - var57 - var88;
                     this.s(var119 / var40, (var95 + var89) / var40, var120 / var40, (var96 + var89) / var40, var91, 50263086633642L, var41);
                     Expo.util.render.RenderUtil.u((var61 + 2.0F * var40 - var88) / var40, var113, var56, Category.n(var71.f()));
                  }

                  N(var38, var80, (var61 + var58 + var50 - var88) / var40, var112, var93, var45, var74, 34995704477528L, var77);
                  if (var75) {
                     g(28962, var38, var81, 16056385, (var61 + var58 + var82 + ArrayListEntry.c(var70) + var49 - var88) / var40, var112, var94, var45, (byte)var22, var74);
                  }

                  GlStateManager.popMatrix();
               }

            }
         }
      }

      GlStateManager.popMatrix();
   }

   private static float B(float var0) {
      var0 = MathUtil.q(var0, 0.0F, 1.0F);
      float var1 = 1.0F - var0;
      return 1.0F - var1 * var1 * var1;
   }


   private void z(java.util.ArrayList<Float> var1, float var2, float var3, float var4) {
      var1.add(Math.max(var3, Math.min(var4, var2)));
   }

   static {
      a = 32443898390876L;
      zkm$clinit();
   }

   public ArrayList(long var1) {
      super(((a ^ (var1)) ^ 99038012877194L));
      // add code
      this.declare("ArrayList", Category.Visual, "Show a list of modules on screen");
      var1 = a ^ var1;
      this.U = new java.util.ArrayList<>();
      this.d = new java.util.ArrayList<>(6);
   }

   private ArrayListRect r(boolean var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      float var10 = var1 ? var2 - var3 - var4 + var5 : var2 + var3 - var5;
      float var11 = var1 ? var2 - var3 + var5 : var2 + var3 + var4 - var5;
      return new ArrayListRect(var10 / var9, (var6 + var8) / var9, var11 / var9, (var7 + var8) / var9, null);
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void z(float var1, float var2, long var3, float var5, float var6, int var7, float var8, ArrayListRect var9, ArrayListRect var10, float var11) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var12 = var3 ^ 34472993190935L;
      this.m(var1, var2, var5, var12, var6, var7, var8, var9, var10, var11, false);
   }

   private static float I(float var0) {
      if (Font.arraylistFont == null) {
         return 0.0F;
      }

      String var3 = Font.arraylistFont.Y();
      return !var3.equals("INTER") && !var3.equals("ROBOTO") ? 0.0F : 1.5F * var0;
   }

   public void A(long var1) {
      this.U.clear();
   }
   private static void zkm$clinit() {
      try {
         C = new Object[7];
         E = new String[7];
         a();
         h = new HashMap(13);
         long var11 = a ^ 106054434392260L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[11];
         int var18 = 0;
         String var17 = "%±Å\u0006Ä+HH+\u007f\u0086\u0019\u0010¢\u0083=\u0010UÆ\f\u0018ãg|ua\u0091\u0002¤\u00ad\u000b´9\u0010\u0019;\u009a0öþÀ?>xlv\u001b^ar\u0018YáÀ8áGSÅ\u0012ZWu¾Ið¡È@\u0017\u0081Çfd \u0010r:Ð\u0015µ:ðf\u0014ë\u009f\u009fÔc\u0083º\u0010Y÷\b;\u0001\u0018à/z¹\u000f?!M\u0099Õ\u0010W(,\u000b\u0001\u009d¶ºM½\u0007®3é\u0012ú\u0010\u0014\u0093æÝ\u0087\u009e\u000bvkªüz\u0089Ýè> >öbZ\u009c\u0011ò@ØT\u0083xY©\u0091#³\f\u0015\u009d\u008dncñ\u0089hs¨á\u0019nÑ";
         int var19 = "%±Å\u0006Ä+HH+\u007f\u0086\u0019\u0010¢\u0083=\u0010UÆ\f\u0018ãg|ua\u0091\u0002¤\u00ad\u000b´9\u0010\u0019;\u009a0öþÀ?>xlv\u001b^ar\u0018YáÀ8áGSÅ\u0012ZWu¾Ið¡È@\u0017\u0081Çfd \u0010r:Ð\u0015µ:ðf\u0014ë\u009f\u009fÔc\u0083º\u0010Y÷\b;\u0001\u0018à/z¹\u000f?!M\u0099Õ\u0010W(,\u000b\u0001\u009d¶ºM½\u0007®3é\u0012ú\u0010\u0014\u0093æÝ\u0087\u009e\u000bvkªüz\u0089Ýè> >öbZ\u009c\u0011ò@ØT\u0083xY©\u0091#³\f\u0015\u009d\u008dncñ\u0089hs¨á\u0019nÑ"
            .length();
         char var16 = 16;
         int var25 = -1;

         label58:
         while (true) {
            String var26 = var17.substring(++var25, var25 + var16);
            int var10001 = -1;

            while (true) {
               byte[] var21 = var13.doFinal(var26.getBytes("ISO-8859-1"));
               String var37 = b(var21).intern();
               switch (var10001) {
                  case 0:
                     var20[var18++] = var37;
                     if ((var25 += var16) >= var19) {
                        b = var20;
                        e = new String[11];
                        x = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[8];
                        int var3 = 0;
                        String var4 = "\u0017\u0086è¦íÓ\u0006\u0003ùH\u001c\u0016\u000e\u0096$\u0088( ý¾¥i\u0098N,\u0016É\u0010\u001a@\u0014\r²;L\u0007pÜ½ %\u0094\u0013\u0084\u0017<ÐO";
                        int var5 = "\u0017\u0086è¦íÓ\u0006\u0003ùH\u001c\u0016\u000e\u0096$\u0088( ý¾¥i\u0098N,\u0016É\u0010\u001a@\u0014\r²;L\u0007pÜ½ %\u0094\u0013\u0084\u0017<ÐO"
                           .length();
                        int var2 = 0;

                        label40:
                        while (true) {
                           var10001 = var2;
                           var2 += 8;
                           byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                           long[] var29 = var6;
                           var10001 = var3++;
                           long var41 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           int var44 = -1;

                           while (true) {
                              long var8 = var41;
                              byte[] var10 = var0.doFinal(
                                 new byte[]{
                                    (byte)(var8 >>> 56),
                                    (byte)(var8 >>> 48),
                                    (byte)(var8 >>> 40),
                                    (byte)(var8 >>> 32),
                                    (byte)(var8 >>> 24),
                                    (byte)(var8 >>> 16),
                                    (byte)(var8 >>> 8),
                                    (byte)var8
                                 }
                              );
                              long var46 = (var10[0] & 255L) << 56
                                 | (var10[1] & 255L) << 48
                                 | (var10[2] & 255L) << 40
                                 | (var10[3] & 255L) << 32
                                 | (var10[4] & 255L) << 24
                                 | (var10[5] & 255L) << 16
                                 | (var10[6] & 255L) << 8
                                 | var10[7] & 255L;
                              switch (var44) {
                                 case 0:
                                    var29[var10001] = var46;
                                    if (var2 >= var5) {
                                       m = var6;
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "\u009fQ\u0094adÉþ\u0097íÃ¹zÎjtþ";
                                    var5 = "\u009fQ\u0094adÉþ\u0097íÃ¹zÎjtþ".length();
                                    var2 = 0;
                              }

                              int var35 = var2;
                              var2 += 8;
                              var7 = var4.substring(var35, var2).getBytes("ISO-8859-1");
                              var29 = var6;
                              var10001 = var3++;
                              var41 = (var7[0] & 255L) << 56
                                 | (var7[1] & 255L) << 48
                                 | (var7[2] & 255L) << 40
                                 | (var7[3] & 255L) << 32
                                 | (var7[4] & 255L) << 24
                                 | (var7[5] & 255L) << 16
                                 | (var7[6] & 255L) << 8
                                 | var7[7] & 255L;
                              var44 = 0;
                           }
                        }
                     }

                     var16 = var17.charAt(var25);
                     break;
                  default:
                     var20[var18++] = var37;
                     if ((var25 += var16) < var19) {
                        var16 = var17.charAt(var25);
                        continue label58;
                     }

                     var17 = "\u0087¢\u009fà0Ç\u0083îh?¢\u001dkÆùdlÿSÕOx\u00988\u008el\u0081\u009dO`;L\u0010©¡Ð},Á\u00892\u0004\u0088E\u001dN\u0012m\u000b";
                     var19 = "\u0087¢\u009fà0Ç\u0083îh?¢\u001dkÆùdlÿSÕOx\u00988\u008el\u0081\u009dO`;L\u0010©¡Ð},Á\u00892\u0004\u0088E\u001dN\u0012m\u000b"
                        .length();
                     var16 = ' ';
                     var25 = -1;
               }

               var26 = var17.substring(++var25, var25 + var16);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }

   static {
      // add code
      customText = new TextSetting("Custom-text", "");
      bar = new BooleanSetting("Bar", true);
      scale = new NumberSetting("Scale", 0.8F, 0.25F, 3.0F, 0.01F);
      barColor = new ModeSetting("Bar-color", "THEME", "THEME_CUSTOM", "CUSTOM");
      offsetX = new NumberSetting("Offset-X", 4.0F, 0.0F, 100.0F, 1.0F);
      moduleNameLowercase = new BooleanSetting("Module-name-lowercase", true);
      textColor = new ModeSetting("Text-color", "THEME", "THEME_CUSTOM", "CUSTOM");
      rectangleYSpace = new NumberSetting("Rectangle-Y-space", 0.0F, 0.0F, 5.0F, 0.01F);
      customColor = new ColorSetting("Custom-color", "FFFFFF");
      rectangleYEdge = new NumberSetting("Rectangle-Y-edge", 0.25F, 0.0F, 5.0F, 0.01F);
      textCustomColor = new ColorSetting("Text-custom-color", "FFFFFF");
      suffixNameLowercase = new BooleanSetting("Suffix-name-lowercase", true);
      onlyShowSuffixModules = new BooleanSetting("Only-show-suffix-modules", false);
      icons = new BooleanSetting("Icons", false);
      backgroundOpacity = new PercentageSetting("Background-opacity", 50);
      offsetY = new NumberSetting("Offset-Y", 3.0F, 0.0F, 100.0F, 1.0F);
      splitSuffixAndName = new BooleanSetting("Split-suffix-and-name", true);
      gradientMode = new ModeSetting("Gradient-mode", false, "LEFT_RIGHT", "UP_DOWN", "LEFT_RIGHT");
      mode = new ModeSetting("Mode", false, "CURVE_RIGHT", "RIGHT", "CURVE_RIGHT", "LEFT", "CURVE_LEFT");
      barWidth = new NumberSetting("Bar-width", 2.0F, 0.0F, 5.0F, 0.01F);
      showSuffix = new BooleanSetting("Show-suffix", true);
      textShadow = new BooleanSetting("Text-shadow", true);
   }
}
