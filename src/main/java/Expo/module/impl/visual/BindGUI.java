package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BindGUIBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.NumberSetting;
import Expo.util.KeyBindUtil;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.renderer.GlStateManager;

public class BindGUI extends Module implements EventSubscriber {
   private static Object[] n;
   private static String[] b;
   private static Map d;
   private static Map k;
   public static NumberSetting scale;
   private static String[] p;
   private int o;
   private static long a;
   public static NumberSetting offsetY;
   private static long[] g;
   private final List<Module> m;
   public static NumberSetting offsetX;
   private static String[] c;

   private static void a() {
      n[0] = "[h/2`As";
      n[1] = long.class;
      p[1] = "java/lang/Long";
      n[2] = "\\\u00185p%6k\u000f1zh\u0012|\u0004kf";
      n[3] = "59T}\u007f`\u0002";
      n[4] = void.class;
      p[4] = "java/lang/Void";
      n[5] = "A^u\u0004T\"JQdK5,AZ`\u0011";
      n[6] = "\u0018C\u0012d78NP\u0013[\u0019H\u000f\u0005\u0015$85\u0010\u0004\u001b[b'\u001cSS<cx\u0011Bia`vIPQ1+(\u0015?Rf>x\u0010P\t*#yu";
   }

   public BindGUI(long var1) {
      super(((a ^ (var1)) ^ 65928473173042L));
      this.declare("BindGUI", Category.Visual, "Show binds of modules and their enabled status");
      var1 = a ^ var1;
      this.m = new ArrayList<>();
   }

   public final void x(long var1, EventBus var3) {
      BindGUIBinder.J(var3, this);
   }

   private static String b(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 11185;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])d.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/module/impl/visual/BindGUI", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         c[var5] = b(((Cipher)var4[0]).doFinal(var9));
      }

      return c[var5];
   }

   public void A(long var1) {
      this.m.clear();
      this.o = 0;
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.O(108535276639352L);
   }

   private void O(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      CustomFont var10 = Font.s(0L);
      ArrayList var11 = new ArrayList();
      int var12 = 0;

      for (int var13 = 0; var13 < ModuleManager.S.size(); var13++) {
         Module var14 = ModuleManager.S.get(var13);
         if (var14.h() != 0) {
            var11.add(var14);
            int var15 = (int)var10.R(var14.b() + " [" + KeyBindUtil.p(1864665317L, (char)64411, var14.h()) + "]", 52019766876817L);
            if (var15 > var12) {
               var12 = var15;
            }
         }
      }

      this.m.clear();
      this.m.addAll(var11);
      this.o = var12;
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

   public void onRender2D(long var1, Render2DEvent var3) {
      float var23 = scale.L();
      GlStateManager.pushMatrix();
      GlStateManager.scale(var23, var23, var23);
      CustomFont var24 = Font.F(0L);
      List var25 = this.m;
      float var26 = (float)(offsetX.L() + 1.0 * var23);
      float var27 = (float)(offsetY.L() + 1.0 * var23);
      float var28 = 1.0F;
      var28 *= var23;
      float var29 = var24.R("BindGUI      (Empty)", 52019766876817L);
      float var30 = !var25.isEmpty() ? Math.max(this.o, var29) : var29;
      float var31 = var24.o(60714858652844L) + var28 * 2.0F;
      float var32 = var30 + var28 * 2.0F;
      var31 *= var23;
      var32 *= var23;
      float var33 = var27;
      Expo.util.render.RenderUtil.c(
         125644905353792L,
         var26 / var23,
         var33 / var23,
         (var26 + var32) / var23,
         (var33 + var31) / var23,
         new Color(0, 0, 0, 170).getRGB()
      );
      if (!var25.isEmpty()) {
         Expo.util.render.RenderUtil.k(var26 / var23, (var33 + var31) / var23, (var26 + var32) / var23, (var33 + var31) / var23, 121972467785353L, 1.0F, -1);
      }

      int var19 = 16777215;
      float var20 = ((var33 + var28 * 2.0F) / var23);
      float var21 = ((var26 + var28) / var23);
      String var22 = (var25.isEmpty() ? "BindGUI      (Empty)" : "BindGUI");
      var24.T(37697014677608L, var22, var21, var20, var19);

      for (int var34 = 0; var34 < var25.size(); var34++) {
         Module var35 = (Module)var25.get(var34);
         var33 += var31;
         Expo.util.render.RenderUtil.c(
            125644905353792L,
            var26 / var23,
            var33 / var23,
            (var26 + var32) / var23,
            (var33 + var31) / var23,
            new Color(0, 0, 0, 100).getRGB()
         );
         var19 = var35.o() ? 4456292 : 11714229;
         var20 = ((var33 + var28 * 2.0F) / var23);
         var21 = ((var26 + var28) / var23);
         var22 = (var35.b() + " [" + KeyBindUtil.p(1864665317L, (char)64411, var35.h()) + "]");
         var24.T(37697014677608L, var22, var21, var20, var19);
      }

      GlStateManager.popMatrix();
   }

   static {
      a = 89407818855642L;
      zkm$clinit();
   }
   private static void zkm$clinit() {
      try {
         n = new Object[7];
         p = new String[7];
         a();
         d = new HashMap(13);
         long var11 = a ^ 106843107854997L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[5];
         int var18 = 0;
         String var17 = "}5V¯÷¯3,ØA\u008c:Å©OÞ(¥wçIf\u0001¢j\u0094òå*òÚ©2èaøy\u0001GÂäü\u001eTÙ3XlÔ@j9è3S\u0080\u0090\u0010´ß\u0016}\u008fÀ\u001eNl%ê\u0089ÝÂ\u0090ê";
         int var19 = "}5V¯÷¯3,ØA\u008c:Å©OÞ(¥wçIf\u0001¢j\u0094òå*òÚ©2èaøy\u0001GÂäü\u001eTÙ3XlÔ@j9è3S\u0080\u0090\u0010´ß\u0016}\u008fÀ\u001eNl%ê\u0089ÝÂ\u0090ê"
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
                        c = new String[5];
                        k = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[6];
                        int var3 = 0;
                        String var4 = "8§GVZU\u0093ÜÇð\u0080vÐ\u0018¾8\u0006õ\u009e«\u007f_BÕ\u0013JÜUû_\u008aû";
                        int var5 = "8§GVZU\u0093ÜÇð\u0080vÐ\u0018¾8\u0006õ\u009e«\u007f_BÕ\u0013JÜUû_\u008aû".length();
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
                                       g = var6;
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "ñ\u007fHUG³L+ÂôÆ}ÂI\u0097|";
                                    var5 = "ñ\u007fHUG³L+ÂôÆ}ÂI\u0097|".length();
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

                     var17 = "/\u0003\u0082È¯\u0090»£\n¶2'h\u0012ß\u009a(\u0095jÊp\u001caÙþÓT\u0005\u0098\u001eÓT}LùH|ç#Ú=PèÂAÌ\r\u0094Äp2)å\u001d\u0097»\u009b";
                     var19 = "/\u0003\u0082È¯\u0090»£\n¶2'h\u0012ß\u009a(\u0095jÊp\u001caÙþÓT\u0005\u0098\u001eÓT}LùH|ç#Ú=PèÂAÌ\r\u0094Äp2)å\u001d\u0097»\u009b"
                        .length();
                     var16 = 16;
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
      scale = new NumberSetting("Scale", 0.8F, 0.25F, 3.0F, 0.01F);
      offsetY = new NumberSetting("Offset-Y", 100.0F, 0.0F, 1000.0F, 1.0F);
      offsetX = new NumberSetting("Offset-X", 3.0F, 0.0F, 1000.0F, 1.0F);
   }
}
