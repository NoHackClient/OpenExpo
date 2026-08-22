package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NotificationsBinder;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.ui.NotificationToast;
import Expo.util.ClipPlayer;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;


public class Notifications extends Module implements EventSubscriber {
   private static List<NotificationToast> s;
   private static int F;
   private static Minecraft Y;
   public static ModeSetting stripColor;
   private static final float N = 2.0F;
   public static NumberSetting leaveTime;
   private static final float b = 6.0F;
   private static final float S = 0.7F;
   public static NumberSetting offsetX;
   public static ColorSetting customBackgroundColor;
   private static Map o;
   private static int B;
   private static int J;
   private static String[] x;
   public static ModeSetting graphic;
   public static ColorSetting customstripColor;
   public static ModeSetting sound;
   private static String[] h;
   private static long a;
   private static Map u;
   private static Object[] v;
   private static long[] r;
   public static NumberSetting stayTime;
   public static NumberSetting offsetY;
   public static BooleanSetting textShadow;
   private static final float R = 1.6F;

   public static float u(float var0) {
      return b(var0);
   }


   private static NotificationToast a(String var0, long var1, boolean var3, CustomFont var4, float var5) {
      var1 = a ^ var1;
      long var6 = var1 ^ 101930431433604L;
      long var8 = var1 ^ 12962250064558L;
      ScaledResolution var10 = new ScaledResolution(Y);
      float var11 = F(var6);
      float var12 = 0.8F;
      float var13 = 7.3F + var4.R(var0, var8) * var12 + 4.5F;
      float var14 = Math.max(46.0F, var13 + 4.0F);
      float var15 = var3 ? var10.getScaledWidth() - var14 - offsetX.L() : offsetX.L();
      float var16 = var3 ? var10.getScaledWidth() + var14 + 8.0F : -var14 - 8.0F;
      return new NotificationToast(var0, System.currentTimeMillis(), var15, var16, var14, var11, var5);
   }

   private static float b(float var0) {
      var0 = MathUtil.q(var0, 0.0F, 1.0F);
      float var1 = 1.0F - var0;
      return 1.0F - var1 * var1 * var1;
   }

   public static void Z(long var0, String var2, char var3, boolean var4, float var5) {
      long var6 = (var0 << 16 | (long)var3 << 48 >>> 48) ^ a;
      long var8 = var6 ^ 53217379349332L;
      int var10 = (int)((var6 ^ 65525955500111L) >>> 48);
      int var11 = (int)((var6 ^ 65525955500111L) << 16 >>> 32);
      long var13 = var6 ^ 87745717111639L;
      CustomFont var15 = Font.O((short)var10, var11);
      switch (graphic.Y()) {
         case "CHAT":
            Expo.util.ClientUtil.t(var13, var2);
            break;
         case "LEFT":
            s.add(a(var2, var8, false, var15, var5));
            break;
         case "RIGHT":
            s.add(a(var2, var8, true, var15, var5));
      }

      switch (sound.Y()) {
         case "BUTTON":
            Expo.util.ClientUtil.B("gui.button.press");
            break;
         case "PLATE":
            if (var4) {
               Y.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.click"), 0.6F));
            } else {
               Y.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.click"), 0.5F));
            }
            break;
         case "SIGMA":
            if (var4) {
               ClipPlayer.x("/assets/minecraft/sounds/sigma/enable.wav");
            } else {
               ClipPlayer.x("/assets/minecraft/sounds/sigma/disable.wav");
            }
            break;
         case "RISE":
            if (var4) {
               ClipPlayer.x("/assets/minecraft/sounds/rise/enable.wav");
            } else {
               ClipPlayer.x("/assets/minecraft/sounds/rise/disable.wav");
            }
            break;
         case "QUICKMACRO":
            if (var4) {
               ClipPlayer.x("/assets/minecraft/sounds/quickmacro/enable.wav");
            } else {
               ClipPlayer.x("/assets/minecraft/sounds/quickmacro/disable.wav");
            }
            break;
         case "SLEEP":
            if (var4) {
               ClipPlayer.x("/assets/minecraft/sounds/sleep/enable.wav");
            } else {
               ClipPlayer.x("/assets/minecraft/sounds/sleep/disable.wav");
            }
      }
   }

   static {
      a = 123782199563812L;
      F = 3;
      J = 1;
      B = 3;
      s = new ArrayList<>();
      Y = MinecraftRef.c((byte)0, 0L);
      v = new Object[9];
      x = new String[9];
      o = new HashMap(13);
      h = new String[24];
      u = new HashMap(13);
      r = new long[]{2966946584198201996L, -5404648118911198893L, -8470739123547187686L, 1677113841768690543L, 4283053398555877610L, -7662428232398007713L, 4506934344420597124L, -3325439409045599548L, -6260022984373518689L, 2630741561568766778L, -302759479584201442L, -908384258933398582L, 644954613818694405L, -8163367185621506862L};
   }

   static {
      // add code
      graphic = new ModeSetting("Graphic", false, "RIGHT", "CHAT", "LEFT", "RIGHT", "DISABLE");
   }

   public final void x(long var1, EventBus var3) {
      NotificationsBinder.r(var3, this);
   }

   private static void n(CustomFont var0, NotificationToast var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8, long var9, int var11, int var12) {
      var9 = a ^ var9;
      long var13 = var9 ^ 5514759140731L;
      long var15 = var9 ^ 108806817169347L;
      long var17 = var9 ^ 43465937848623L;
      long var19 = var9 ^ 95040672155471L;
      float var21 = var2 + var4;
      float var22 = var3 + var5;
      Expo.util.render.RenderUtil.c(var17, var2, var3, var21, var22, var6);
      float var23 = var3 + var5 * 0.5F - 0.8F - 0.1F;
      Expo.util.render.RenderUtil.j(var2 + 3.6F, var23, var2 + 3.6F + 1.6F, var23 + 1.6F, 1.6F, var19, var12);
      float var24 = var2 + 2.6F;
      float var25 = var21 - 2.6F;
      float var26 = var3 + var5 - 1.6F;
      float var27 = var26 + 0.7F;
      Expo.util.render.RenderUtil.j(var24, var26, var25, var27, 1.0F, var19, var8);
      float var28 = (var25 - var24) * var1.T();
      if (var28 > 0.0F) {
         Expo.util.render.RenderUtil.j(var24, var26, var24 + var28, var27, 1.0F, var19, var7);
      }

      float var29 = 0.7F;
      float var30 = var0.o(var15) * var29;
      float var31 = var3 + (var5 - var30) * 0.5F;
      GlStateManager.pushMatrix();
      GlStateManager.translate(var2 + 7.3F, var31, 0.0F);
      GlStateManager.scale(var29, var29, 1.0F);
      var0.v(NotificationToast.m(var1), 0.0F, 0.0F, var11, var13, textShadow.c());
      GlStateManager.popMatrix();
   }

   public static float R(float var0) {
      return D(var0);
   }

   private static int u(int var0, float var1) {
      var1 = MathUtil.q(var1, 0.0F, 1.0F);
      int var4 = Math.round((var0 >> 24 & 255) * var1);
      return var0 & 16777215 | var4 << 24;
   }

   private static int F(long var0) {




      CustomFont var7 = Font.O((short)0, 1609519694);
      return (int)(var7.o(60714858652844L) + 2.0F + 1.0F);
   }

   public Notifications(long var1) {
      super(((a ^ (var1)) ^ 1063327075694L));
      // add code
      this.declare("Notifications", Category.Configuration, "Module toggle notifications settings");
      var1 = a ^ var1;
   }

   private static float y() {
      return Math.max(180.0F, Math.min(320.0F, leaveTime.L() * 0.75F));
   }

   public static void G(long var0, String var2, boolean var3) {
      var0 = a ^ var0;
      long var4 = (var0 ^ 88767308938623L) >>> 16;
      int var6 = (int)((var0 ^ 88767308938623L) << 48 >>> 48);
      Z(var4, var2, (char)var6, var3, stayTime.L());
   }


   private static float D(float var0) {
      var0 = MathUtil.q(var0, 0.0F, 1.0F);
      return var0 * var0 * var0;
   }

   private static int C(int var0, int var1, float var3) {
      var3 = MathUtil.q(var3, 0.0F, 1.0F);
      int var8 = var0 >> 24 & 255;
      int var9 = var0 >> 16 & 255;
      int var10 = var0 >> 8 & 255;
      int var11 = var0 & 255;
      int var12 = var1 >> 24 & 255;
      int var13 = var1 >> 16 & 255;
      int var14 = var1 >> 8 & 255;
      int var15 = var1 & 255;
      int var16 = (int)(var8 + (var12 - var8) * var3);
      int var17 = (int)(var9 + (var13 - var9) * var3);
      int var18 = (int)(var10 + (var14 - var10) * var3);
      int var19 = (int)(var11 + (var15 - var11) * var3);
      return var16 << 24
         | var17 << 16
         | var18 << 8
         | var19;
   }

   public static float Z() {
      return y();
   }

   public void onRender2D(int var1, Render2DEvent var2, int var3, char var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var9 = var5 ^ 24451832784568L;
      long var11 = var5 ^ 85966193915020L;
      long var13 = var5 ^ 21197217540732L;
      int var15 = (int)((var5 ^ 116486171722295L) >>> 48);
      int var16 = (int)((var5 ^ 116486171722295L) << 16 >>> 32);
      long var18 = var5 ^ 14820923538502L;
      long var20 = var5 ^ 107570200187233L;
      if (graphic.R("LEFT") || graphic.R("RIGHT")) {
         int var25;
         switch (stripColor.Y()) {
            case "THEME":
               var25 = Theme.S(0.0, var9);
               break;
            case "THEME_CUSTOM":
               var25 = Theme.X(var18, 0.0);
               break;
            default:
               var25 = customstripColor.k(var20);
         }

         CustomFont var50 = Font.O((short)var15, var16);
         ScaledResolution var51 = var2.C;
         long var28 = System.currentTimeMillis();
         float var30 = y();
         float var31 = stayTime.L();
         float var32 = leaveTime.L();
         float var33 = F(var11);
         float var35 = var51.getScaledHeight() - offsetY.L() - var33;
         s.removeIf(var5x -> var5x.c(var28, var30, var31, var32));
         ArrayList var36 = new ArrayList();

         for (int var37 = 0; var37 < s.size(); var37++) {
            NotificationToast var38 = s.get(var37);
            if (var38.i(var28, var30, var31, var32) > 0.0F) {
               var36.add(var38);
            }
         }

         if (!var36.isEmpty()) {
            float var52 = var35;
            float var53 = 0.075F + var2.r * 0.025F;

            for (int var39 = 0; var39 < var36.size(); var39++) {
               NotificationToast var40 = (NotificationToast)var36.get(var39);
               float var41 = var40.i(var28, var30, var31, var32);
               if (!(var41 <= 0.0F)) {
                  if (Float.isNaN(NotificationToast.R(var40))) {
                     NotificationToast.K(var40, var52 - 6.0F);
                  }

                  NotificationToast.K(var40, MathUtil.k(var52, NotificationToast.R(var40), var53));
                  if (Math.abs(var52 - NotificationToast.R(var40)) <= 0.03F) {
                     NotificationToast.K(var40, var52);
                  }

                  float var42 = NotificationToast.R(var40);
                  float var43 = MathUtil.k(NotificationToast.e(var40), NotificationToast.O(var40), var41);
                  int var44 = customBackgroundColor.k(var20);
                  int var45 = u(C(var44, -16777216, 0.18F), var41);
                  int var46 = u(C(var25, -1, 0.1F), var41);
                  int var47 = u(822083583, var41);
                  int var48 = u(-657931, var41);
                  int var49 = u(var25, var41);
                  n(var50, var40, var43, var42, NotificationToast.f(var40), NotificationToast.c(var40), var45, var46, var47, var13, var48, var49);
               }
            }
         }
      }
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

   private static void a() {
      v[0] = "/\u0014)q9#2";
      v[1] = "u[?\u0007tyBL;\r9]UGa\u0011";
      v[2] = short.class;
      x[2] = "java/lang/Short";
      v[3] = int.class;
      x[3] = "java/lang/Integer";
      v[4] = "W=u0BwQ";
      v[5] = char.class;
      x[5] = "java/lang/Character";
      v[6] = void.class;
      x[6] = "java/lang/Void";
      v[7] = "zX&}\t qW72h.z\\3h";
      v[8] = "If5*V?\u000erh@\\F\u0000d2/\u0016:\u000b-1@\u001f%\u0016|c;Lx\u0012c\nzDz\u001cmv!]8\u0016\u001c1+Y{\u001fzm%A~p'n>_:@wa/XFLa`q_yN}r:&";
   }

   private static float A(float var0) {
      var0 = MathUtil.q(var0, 0.0F, 1.0F);
      return var0 < 0.5F ? 4.0F * var0 * var0 * var0 : 1.0F - (float)Math.pow(-2.0F * var0 + 2.0F, 3.0) / 2.0F;
   }
   static {
      // add code
      customBackgroundColor = new ColorSetting("Custom-background-color", "000000");
      stripColor = new ModeSetting("Strip-color", "THEME", "THEME_CUSTOM", "CUSTOM");
      customstripColor = new ColorSetting("Customstrip-color", "FFFFFF");
      sound = new ModeSetting("Sound", "BUTTON", "PLATE", "SIGMA", "RISE", "QUICKMACRO", "SLEEP", "DISABLE");
      offsetY = new NumberSetting("Offset-Y", 50.0F, 0.0F, 200.0F, 1.0F);
      stayTime = new NumberSetting("Stay-time", 300.0F, 100.0F, 2000.0F, 1.0F);
      leaveTime = new NumberSetting("Leave-time", 500.0F, 100.0F, 2000.0F, 1.0F);
      textShadow = new BooleanSetting("Text-shadow", true);
      offsetX = new NumberSetting("Offset-X", 0.0F, 0.0F, 100.0F, 1.0F);
   }
}
