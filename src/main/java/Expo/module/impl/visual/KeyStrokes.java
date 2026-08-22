package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.KeyStrokesBinder;
import Expo.event.events.IsPressedEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;

public class KeyStrokes extends Module implements EventSubscriber {
   private static Object[] t;
   private static Map e;
   public static NumberSetting offsetY;
   private static long[] o;
   private static String[] d;
   private static String[] u;
   private static long b;
   public static NumberSetting offsetX;
   private final float L;
   private static Map s;
   private static String[] c;
   private CustomFont n;
   private static Map m;
   private final float D;
   public static PercentageSetting backgroundOpacity;
   private static Map<KeyBinding, Long> h;
   private static Minecraft I;
   private static long[] g;
   private static Integer[] k;

   private void a(KeyBinding var1, float var2, float var3, long var4, int var6, float var7, float var8, float var9, float var10, String var11) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var12 = (51346334023680L | (long)var6 << 32 >>> 32) ^ b;
      long var14 = var12 ^ 63490349436763L;
      long var16 = var12 ^ 117793772649713L;
      GlStateManager.pushMatrix();
      Expo.util.render.RenderUtil.c(var16, var2, var3, var7, var8, new Color(0, 0, 0, (int)(2.55F * backgroundOpacity.k())).getRGB());
      if (System.currentTimeMillis() - h.get(var1) < 30L) {
         Expo.util.render.RenderUtil.c(
            var16,
            var2,
            var3,
            var7,
            var8,
            new Color(
                  255,
                  255,
                  255,
                  this.f( h.get(var1))
               )
               .getRGB()
         );
      }

      Expo.util.render.RenderUtil.J(var14, this.n, var11, var9, var10, 16777215, 0);
      GlStateManager.popMatrix();
   }

   private float u(String var1, float var2, long var3, float var5) {
      return var2 + (var5 - var2) / 2.0F - this.n.R(var1, 52019766876817L) / 2.0F;
   }

   private static void a() {
      t[0] = "q17,1GS";
      t[1] = "'n8}bu\u0010y<w/Q\u0007rfk";
      t[2] = long.class;
      u[2] = "java/lang/Long";
      t[3] = "\u000e74V\u00185*";
      t[4] = void.class;
      u[4] = "java/lang/Void";
      t[5] = "kf\u0007\u0019v\u0015`i\u0016V\u0017\u001bkb\u0012\f";
      t[6] = "x\nrI\u0001$ \t3*\u0007\u0018(P`@Q~}\u000foEj\"(\u0005cQ\u0005aa\u00111*Ptq\nc\u0012\u0004#|U\t\u0010\u0018qo\u001bd\u0011Pivk";
   }

   private void D(KeyBinding var1, float var2, float var3, float var4, float var5, float var6, long var7, float var9) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.a(var1, var2, var3, 11955L, 1464655768, var4, var5, var6, var9, KeyBindUtil.p(1864665317L, (char)64411, var1.getKeyCode()));
   }

   public final void x(long var1, EventBus var3) {
      KeyStrokesBinder.e(var3, this);
   }

   public static void T() {
      h.put(I.gameSettings.keyBindForward, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindBack, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindLeft, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindRight, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindJump, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindAttack, System.currentTimeMillis());
      h.put(I.gameSettings.keyBindUseItem, System.currentTimeMillis());
   }

   private void n(KeyBinding var1, float var2, long var3, float var5, float var6, float var7) {
      GlStateManager.pushMatrix();
      Expo.util.render.RenderUtil.c(125644905353792L, var2, var5, var6, var7, new Color(0, 0, 0, (int)(2.55F * backgroundOpacity.k())).getRGB());
      if (System.currentTimeMillis() - h.get(var1) < 40L) {
         Expo.util.render.RenderUtil.c(
            125644905353792L,
            var2,
            var5,
            var6,
            var7,
            new Color(
                  255,
                  255,
                  255,
                  this.f( h.get(var1))
               )
               .getRGB()
         );
      }

      float var15 = var2 + 15.0F;
      float var16 = var5 + 4.0F;
      float var17 = var6 - 15.0F;
      float var18 = var5 + 5.0F;
      Expo.util.render.RenderUtil.m(var15, var16, 91446790430251L, var17, var18, 5.0F, Color.BLACK.getRGB());
      Expo.util.render.RenderUtil.c(125644905353792L, var15, var16, var17, var18, Color.WHITE.getRGB());
      GlStateManager.popMatrix();
   }

   public void onMouse(MouseEvent var1, long var2) {
      for (Entry var5 : h.entrySet()) {
         if (((KeyBinding)var5.getKey()).getKeyCode() + 100 == var1.getButton()) {
            h.put((KeyBinding)var5.getKey(), System.currentTimeMillis());
         }
      }
   }

   private int f( long var4) {
      if (System.currentTimeMillis() - var4 > 40L) {
         return 0;
      }

      int var8 = 100 - (int)(System.currentTimeMillis() - var4);
      return (int)(2.0F * var8);
   }

   public KeyStrokes(byte var1, int var2, int var3) {
      super(((((((long)((var1)) << 56) | (((long)((var2)) << 32) >>> 8)) | (((long)((var3)) << 40) >>> 40)) ^ b) ^ 17198579932816L));
      this.declare("KeyStrokes", Category.Visual, "Show your keys interactions");
      this.D = 4.0F;
      this.L = 2.0F;
      this.n = null;
   }

   private static int d(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 14923;
      if (k[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = g[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])m.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               m.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/visual/KeyStrokes", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         k[var3] = var15;
      }

      return k[var3];
   }

   static {
      b = 61397954654505L;
      zkm$clinit();
      h = new HashMap<>();
      I = MinecraftRef.c((byte)0, 0L);
   }

   public void onIsPressed(IsPressedEvent var1) {
      if (var1.a) {
         for (Entry var3 : h.entrySet()) {
            if (((KeyBinding)var3.getKey()).getKeyCode() == var1.o) {
               h.put((KeyBinding)var3.getKey(), System.currentTimeMillis());
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

   public void onSetKeyBindState(SetKeyBindStateEvent var1) {
      for (Entry var3 : h.entrySet()) {
         if (((KeyBinding)var3.getKey()).getKeyCode() == var1.R) {
            h.put((KeyBinding)var3.getKey(), System.currentTimeMillis());
         }
      }
   }

   private float T(long var1) {
      return 8.0F + this.n.o(60714858652844L);
   }

   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.n = Font.F(0L);
      float var17 = offsetX.L();
      float var18 = offsetY.L();
      float var19 = var17 - this.T(120224034947841L) - 2.0F;
      float var20 = var17 + this.T(120224034947841L) * 2.0F + 2.0F;
      float var21 = var18;
      float var22 = var18 + this.T(120224034947841L) + 2.0F;
      float var23 = var18 + this.T(120224034947841L) * 2.0F + 4.0F;
      float var24 = var23 + 8.0F + 2.0F;

      for (Entry var26 : h.entrySet()) {
         if (((KeyBinding)var26.getKey()).isKeyDown()) {
            h.put((KeyBinding)var26.getKey(), System.currentTimeMillis());
         }
      }

      this.E(I.gameSettings.keyBindForward, 13170027296889L, var17, var21);
      this.E(I.gameSettings.keyBindBack, 13170027296889L, var17, var22);
      this.E(I.gameSettings.keyBindLeft, 13170027296889L, var19, var22);
      this.E(I.gameSettings.keyBindRight, 13170027296889L, var19 + this.T(120224034947841L) * 2.0F + 4.0F, var22);
      float var30 = var20 - var19;
      float var31 = Math.abs(var30);
      this.n(I.gameSettings.keyBindJump, var19, 92495927115386L, var23, var20, var23 + 8.0F);
      float var27 = var19 + var31 / 2.0F - 2.0F;
      this.a(
         I.gameSettings.keyBindAttack,
         var19,
         var24,
         11955L,
         1464655768,
         var27,
         var24 + this.T(120224034947841L),
         this.u("LMB", var19, 60932596744412L, var27),
         var24 + 4.0F,
         "LMB"
      );
      float var28 = var19 + var31 / 2.0F + 2.0F;
      this.a(
         I.gameSettings.keyBindUseItem,
         var28,
         var24,
         11955L,
         1464655768,
         var20,
         var24 + this.T(120224034947841L),
         this.u("RMB", var28, 60932596744412L, var20),
         var24 + 4.0F,
         "RMB"
      );
   }

   private void E(KeyBinding var1, long var2, float var4, float var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      float var15 = this.n.o(60714858652844L);
      this.D(
         var1,
         var4,
         var5,
         var4 + 8.0F + var15,
         var5 + 8.0F + var15,
         this.u(KeyBindUtil.p(1864665317L, (char)64411, var1.getKeyCode()), var4, 60932596744412L, var4 + 8.0F + var15),
         46633935861873L,
         var5 + 4.0F
      );
   }
   private static void zkm$clinit() {
      try {
         long var31 = b ^ 90849720011812L;
         t = new Object[7];
         u = new String[7];
         a();
         e = new HashMap(13);
         Cipher var22;
         byte[] var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var23 = 1; var23 < 8; var23++) {
            var10003[var23] = (byte)(var31 << var23 * 8 >>> 56);
         }

         (var22 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var29 = new String[4];
         int var27 = 0;
         String var26 = "XG\u0016\u001f\u0088\u000e÷¬ð°,I!ø\u0010T\u0010Ì\u0010G2\u0016á1öm¸ý\u000e\u009d##\u0000";
         int var28 = "XG\u0016\u001f\u0088\u000e÷¬ð°,I!ø\u0010T\u0010Ì\u0010G2\u0016á1öm¸ý\u000e\u009d##\u0000".length();
         char var25 = 16;
         int var39 = -1;

         label77:
         while (true) {
            String var40 = var26.substring(++var39, var39 + var25);
            int var10001 = -1;

            while (true) {
               byte[] var30 = var22.doFinal(var40.getBytes("ISO-8859-1"));
               String var54 = b(var30).intern();
               switch (var10001) {
                  case 0:
                     var29[var27++] = var54;
                     if ((var39 += var25) >= var28) {
                        c = var29;
                        d = new String[4];
                        m = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var31 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var17 = new long[5];
                        int var14 = 0;
                        String var15 = "fã\rNÐ\b\u0004Y\u008f\u0014ègÞp}\u0003AYµw\b`\u0012î";
                        int var16 = "fã\rNÐ\b\u0004Y\u008f\u0014ègÞp}\u0003AYµw\b`\u0012î".length();
                        int var13 = 0;

                        label59:
                        while (true) {
                           var10001 = var13;
                           var13 += 8;
                           byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                           long[] var43 = var17;
                           var10001 = var14++;
                           long var58 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           int var62 = -1;

                           while (true) {
                              long var19 = var58;
                              byte[] var21 = var11.doFinal(
                                 new byte[]{
                                    (byte)(var19 >>> 56),
                                    (byte)(var19 >>> 48),
                                    (byte)(var19 >>> 40),
                                    (byte)(var19 >>> 32),
                                    (byte)(var19 >>> 24),
                                    (byte)(var19 >>> 16),
                                    (byte)(var19 >>> 8),
                                    (byte)var19
                                 }
                              );
                              long var66 = (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L;
                              switch (var62) {
                                 case 0:
                                    var43[var10001] = var66;
                                    if (var13 >= var16) {
                                       g = var17;
                                       k = new Integer[5];
                                       s = new HashMap(13);
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(var31 << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                       long[] var6 = new long[3];
                                       int var3 = 0;
                                       String var4 = "\u009cåõ\u008dô\u0088íZÚ®´n¹ËªU¶\u001bEw\"ºry";
                                       int var5 = "\u009cåõ\u008dô\u0088íZÚ®´n¹ËªU¶\u001bEw\"ºry".length();
                                       int var2 = 0;

                                       do {
                                          int var51 = var2;
                                          var2 += 8;
                                          byte[] var7 = var4.substring(var51, var2).getBytes("ISO-8859-1");
                                          var51 = var3++;
                                          long var8 = (var7[0] & 255L) << 56
                                             | (var7[1] & 255L) << 48
                                             | (var7[2] & 255L) << 40
                                             | (var7[3] & 255L) << 32
                                             | (var7[4] & 255L) << 24
                                             | (var7[5] & 255L) << 16
                                             | (var7[6] & 255L) << 8
                                             | var7[7] & 255L;
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
                                          var66 = (var10[0] & 255L) << 56
                                             | (var10[1] & 255L) << 48
                                             | (var10[2] & 255L) << 40
                                             | (var10[3] & 255L) << 32
                                             | (var10[4] & 255L) << 24
                                             | (var10[5] & 255L) << 16
                                             | (var10[6] & 255L) << 8
                                             | var10[7] & 255L;
                                          var6[var51] = var66;
                                       } while (var2 < var5);

                                       o = var6;
                                       return;
                                    }
                                    break;
                                 default:
                                    var43[var10001] = var66;
                                    if (var13 < var16) {
                                       continue label59;
                                    }

                                    var15 = "ý$c\u0001ó·Uà\u0018\u0095s±ÈÖ\u0095L";
                                    var16 = "ý$c\u0001ó·Uà\u0018\u0095s±ÈÖ\u0095L".length();
                                    var13 = 0;
                              }

                              int var50 = var13;
                              var13 += 8;
                              var18 = var15.substring(var50, var13).getBytes("ISO-8859-1");
                              var43 = var17;
                              var10001 = var14++;
                              var58 = (var18[0] & 255L) << 56
                                 | (var18[1] & 255L) << 48
                                 | (var18[2] & 255L) << 40
                                 | (var18[3] & 255L) << 32
                                 | (var18[4] & 255L) << 24
                                 | (var18[5] & 255L) << 16
                                 | (var18[6] & 255L) << 8
                                 | var18[7] & 255L;
                              var62 = 0;
                           }
                        }
                     }

                     var25 = var26.charAt(var39);
                     break;
                  default:
                     var29[var27++] = var54;
                     if ((var39 += var25) < var28) {
                        var25 = var26.charAt(var39);
                        continue label77;
                     }

                     var26 = "ý'\u008d\u008f\u0093sq8óØÞïÎ-Ç=\u0010E¤Xª\f+Ò\\Q\u0003\u009bÇÃ\u008a\b_";
                     var28 = "ý'\u008d\u008f\u0093sq8óØÞïÎ-Ç=\u0010E¤Xª\f+Ò\\Q\u0003\u009bÇÃ\u008a\b_".length();
                     var25 = 16;
                     var39 = -1;
               }

               var40 = var26.substring(++var39, var39 + var25);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var36) {
         throw new RuntimeException(var36);
      }
   }

   static {
      offsetX = new NumberSetting("Offset-X", 120.0F, 0.0F, 1000.0F, 1.0F);
      offsetY = new NumberSetting("Offset-Y", 30.0F, 0.0F, 1000.0F, 1.0F);
      backgroundOpacity = new PercentageSetting("Background-opacity", 50);
   }
}
