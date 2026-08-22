package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FallIndicatorBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.RaytraceUtil;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class FallIndicator extends Module implements EventSubscriber {
   private String M;
   private static int d;
   private float F;
   private double u;
   public static PercentageSetting minDamagePercentage;
   private static String[] v;
   private static Object[] o;
   private boolean s;
   private double e;
   public static BooleanSetting showFallDistance;
   private static float[] t;
   private String O;
   public static BooleanSetting onlyWhileSneaking;
   private static long b;
   private final ItemStack[] Y;
   private static Map n;
   private final ItemStack[] K;
   private static String[] g;
   private boolean L;
   private int U;
   private static String[] c;
   private static Map h;
   private static long[] k;
   private int r;

   public final void x(long var1, EventBus var3) {
      FallIndicatorBinder.r(var3, this);
   }

   public void A(long var1) {
      this.a(0L);
   }

   private static int R(float var0) {
      float var3 = 2.5F;
      float var4 = 20.0F;
      float var5 = MathHelper.clamp_float((var0 - var3) / (var4 - var3), 0.0F, 1.0F);
      int var6 = (int)(255.0F * (1.0F - var5));
      return -65536 | var6 << 8;
   }

   private static String z(float var0) {
      return var0 == (float)((long)var0) ? Long.toString((long)var0) : Float.toString(var0);
   }

   public void onPostTick(PostTickEvent var1, long var2) {
      if (!this.isSneaking()) {
         this.G((char)0);
      }
   }

   public void onRender2D(Render2DEvent var1, long var2) {
      if (!this.isSneaking()) {
         if (this.L && this.M != null) {
            CustomFont var12 = Font.F(0L);
            ScaledResolution var13 = var1.C;
            int var14 = var13.getScaledWidth() / 2;
            int var15 = var13.getScaledHeight() / 2 + var13.getScaledHeight() / 45;
            var12.v(this.M, var14 - var12.R(this.M, 52019766876817L) / 2.0F, var15, -1, 88827598794260L, true);
            if (showFallDistance.c() && this.O != null) {
               var12.v(this.O, var14 - var12.R(this.O, 52019766876817L) / 2.0F, var15 + var12.o(60714858652844L), this.r, 88827598794260L, true);
            }
         }
      }
   }

   private static float o(float var0, int var1) {
      if (var1 < 0) {
         return var0;
      }

      float var2 = var1 < t.length ? t[var1] : (float)Math.pow(10.0, var1);
      return Math.round(var0 * var2) / var2;
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

   public FallIndicator(long var1) {
      super(((b ^ (var1)) ^ 27654362203666L));
      this.declare("FallIndicator", Category.Visual_utility, "Display the damage amount you might receive when looking at the ground");
      var1 = b ^ var1;
      this.Y = new ItemStack[4];
      this.K = new ItemStack[4];
      this.e = -1.0;
      this.u = -1.0;
      this.F = 0.0F;
      this.U = -1;
      this.s = ((0 & 1) != 0);
      this.L = ((0 & 1) != 0);
      this.M = null;
      this.O = null;
      this.r = -1;
   }

   private void a(long var1) {
      this.e = -1.0;
      this.u = -1.0;
      this.F = 0.0F;
      this.U = -1;

      for (int var3 = 0; var3 < 4; var3++) {
         this.Y[var3] = null;
         this.K[var3] = null;
      }

      this.s = ((0 & 1) != 0);
      this.L = ((0 & 1) != 0);
      this.M = null;
      this.O = null;
      this.r = -1;
   }

   private void G(char var1) {
      this.L = ((0 & 1) != 0);
      this.M = null;
      this.O = null;
      this.r = -1;
      MovingObjectPosition var8 = RaytraceUtil.J(1000.0);
      BlockPos var9 = var8.getBlockPos();
      if (var9 != null) {
         if (!(RaytraceUtil.d(f.thePlayer.posX, f.thePlayer.posZ, var9.getX(), var9.getZ()) >= 5.0)) {
            double var10 = var8.getBlockPos().getY();
            boolean var12 = f.thePlayer.onGround;
            float var13;
            if (var12) {
               this.e = -1.0;
               this.u = -1.0;
               this.F = 0.0F;
               if (var10 == -1.0) {
                  return;
               }

               var13 = (float)Math.max(0.0, f.thePlayer.posY - var10);
            } else {
               if (this.e == -1.0) {
                  this.e = f.thePlayer.posY;
                  this.u = var10;
                  this.F = 0.0F;
               } else if (var10 != this.u) {
                  this.u = var10;
                  this.F = 0.0F;
               }

               if (this.e == -1.0 || this.u == -1.0) {
                  return;
               }

               if (this.F == 0.0F) {
                  this.F = (float)Math.max(0.0, this.e - this.u);
               }

               var13 = this.F;
            }

            if (!(var13 <= 4.0F)) {
               var13--;
               PotionEffect var14 = f.thePlayer.getActivePotionEffect(Potion.jump);
               float var15 = var14 != null ? var14.getAmplifier() + 1 : 0.0F;
               PotionEffect var16 = f.thePlayer.getActivePotionEffect(Potion.resistance);
               boolean var17 = var16 != null;
               int var18 = var17 ? var16.getAmplifier() + 1 : 0;
               boolean var19 = false;

               for (int var20 = 0; var20 < 4; var20++) {
                  ItemStack var21 = f.thePlayer.inventory.armorItemInSlot(var20);
                  this.K[var20] = var21;
                  if (this.Y[var20] != var21) {
                     var19 = true;
                  }
               }

               int var32 = this.U;
               if (var19 || !this.s) {
                  long var33 = 0L;

                  for (int var23 = 0; var23 < 100; var23++) {
                     int var24 = EnchantmentHelper.getEnchantmentModifierDamage(this.K, DamageSource.fall);
                     if (var24 > 20) {
                        var24 = 20;
                     }

                     var33 += var24;
                  }

                  var32 = (int)Math.round(var33 / 100.0);
                  this.U = var32;
                  System.arraycopy(this.K, 0, this.Y, 0, 4);
                  this.s = ((1 & 1) != 0);
               }

               float var34 = var13 - 3.0F - var15;
               double var22 = Math.round(Math.max(0.0F, var34));
               if (var17 && var22 > 0.0) {
                  int var35 = var18 * 5;
                  int var25 = 25 - var35;
                  var22 = var25 * var22 / 25.0;
               }

               if (var22 > 0.0 && var32 > 0) {
                  var22 = (25 - var32) * var22 / 25.0;
               }

               double var36 = f.thePlayer.getHealth();
               double var26 = var22 / var36 * 100.0;
               if (!(var26 < minDamagePercentage.k())) {
                  if (var26 != 0.0) {
                     double var28 = var22 / var36;
                     String var30;
                     if (var22 >= var36) {
                        var30 = "§4";
                     } else if (var28 >= 0.7) {
                        var30 = "§c";
                     } else if (var28 >= 0.5) {
                        var30 = "§6";
                     } else if (var28 >= 0.3) {
                        var30 = "§e";
                     } else {
                        var30 = "§a";
                     }

                     this.M = var30 + "-" + z(o((float)var22, 1)) + " " + var30 + "HP";
                     this.L = ((1 & 1) != 0);
                     if (showFallDistance.c()) {
                        this.r = R(var13);
                        this.O = z(o(var13, 1)) + "m";
                     }
                  }
               }
            }
         }
      }
   }

   static {
      b = 69942045818855L;
      zkm$clinit();
   }

   private static void a() {
      o[0] = "Q\u0015J+`'\\";
      o[1] = long.class;
      v[1] = "java/lang/Long";
      o[2] = "\"qY)z]\u0015f]#7y\u0002m\u0007?";
      o[3] = "d?\u0010mN1U";
      o[4] = void.class;
      v[4] = "java/lang/Void";
      o[5] = "\u00127\u00005U?\u00198\u0011z41\u00123\u0015 ";
      o[6] = "@e>\u0003o[]n'mq>\u001b?0\u0010m\u0007O}/\u001d\u000b\u0007Fie\f{\u000eL|&m1T\u0013bf\u0017`@Af_V4\\Xt`\u001csYI\u0004";
   }

   private boolean isSneaking() {
      if (f.currentScreen != null) {
         return true;
      } else if (f.gameSettings.thirdPersonView != 0) {
         return true;
      } else if (f.gameSettings.showDebugInfo) {
         return true;
      } else if (f.thePlayer.capabilities.isCreativeMode) {
         return true;
      } else {
         return f.thePlayer.capabilities.allowFlying ? true : onlyWhileSneaking.c() && !f.thePlayer.isSneaking();
      }
   }
   private static void zkm$clinit() {
      try {
         o = new Object[7];
         v = new String[7];
         a();
         h = new HashMap(13);
         long var11 = b ^ 97273488151114L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[6];
         int var18 = 0;
         String var17 = "¢\u0086º/4gæôÉµ\u0080í\u0084\u007f·E\u0010ï§g\u0014²ç>ü\u000f\u0006\u00ad\\¦·\r.\u0010í¹\u009b6ÏN³©\u0084mâyN86r\u0010ÿ\u0099§\u0087\u0000\u009biÝ;öóÒIÍGî";
         int var19 = "¢\u0086º/4gæôÉµ\u0080í\u0084\u007f·E\u0010ï§g\u0014²ç>ü\u000f\u0006\u00ad\\¦·\r.\u0010í¹\u009b6ÏN³©\u0084mâyN86r\u0010ÿ\u0099§\u0087\u0000\u009biÝ;öóÒIÍGî"
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
                        c = var20;
                        g = new String[6];
                        n = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[15];
                        int var3 = 0;
                        String var4 = "5®\bÖA¶\"Ö0ÃR\u009dâº§ÙÅ%\u0007\u0096\u0005\u0006b\tïëq \u009a¯\u0083o¹N°\u00adô\u0082Á\u008bM\u000b©ÎC)E3Ï\u0006Ço\u0087g÷¿Ð\u001bs¥Lv\u0002kuuG³¸b§q-\r6\u0001ÓÖ\u0088Ç\u0015X/\u0096\u0093;² ¢\u009e¨öZJâÛ{úå8>?V4";
                        int var5 = "5®\bÖA¶\"Ö0ÃR\u009dâº§ÙÅ%\u0007\u0096\u0005\u0006b\tïëq \u009a¯\u0083o¹N°\u00adô\u0082Á\u008bM\u000b©ÎC)E3Ï\u0006Ço\u0087g÷¿Ð\u001bs¥Lv\u0002kuuG³¸b§q-\r6\u0001ÓÖ\u0088Ç\u0015X/\u0096\u0093;² ¢\u009e¨öZJâÛ{úå8>?V4"
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
                                       k = var6;
                                       d = -1;
                                       t = new float[]{1.0F, 10.0F, 100.0F, 1000.0F};
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "Ø\u0006\u0090.ê¦Ì\u0091\u0007Ê®qHyïÍ";
                                    var5 = "Ø\u0006\u0090.ê¦Ì\u0091\u0007Ê®qHyïÍ".length();
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

                     var17 = "ñpgáÞ\u00174`Í\u000e\u0003- =\u0000v\u0010BrªcÛT¾\u0080ª=\u009aPò½:Y";
                     var19 = "ñpgáÞ\u00174`Í\u000e\u0003- =\u0000v\u0010BrªcÛT¾\u0080ª=\u009aPò½:Y".length();
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
      minDamagePercentage = new PercentageSetting("Min-damage-percentage", 0);
      showFallDistance = new BooleanSetting("Show-fall-distance", true);
      onlyWhileSneaking = new BooleanSetting("Only-while-sneaking", false);
   }
}
