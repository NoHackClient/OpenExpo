package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;











public class Freelook extends Module {
   public static boolean G;
   private int T;
   private float p;
   private static long a;
   private float s;
   public static float N;
   public static ModeSetting mode;
   public static float v;

   public static float M() {
      return v;
   }

   static {
      a = 47083344118779L;
      // add code
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {
         long var7 = a ^ 109798899218549L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var7 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var7 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

         byte[] var6 = var2.doFinal(
            new byte[]{
               (byte)19L,
               (byte)5090L,
               (byte)1303196L,
               (byte)333618213L,
               (byte)85406262747L,
               (byte)21864003263266L,
               (byte)5597184835396295L,
               (byte)1432879317861451648L
            }
         );
         long var10 = (var6[0] & 255L) << 56
            | (var6[1] & 255L) << 48
            | (var6[2] & 255L) << 40
            | (var6[3] & 255L) << 32
            | (var6[4] & 255L) << 24
            | (var6[5] & 255L) << 16
            | (var6[6] & 255L) << 8
            | var6[7] & 255L;
         long var0 = var10;
         G = (var0 & 1L) != 0L;
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var9) {
         throw new RuntimeException(var9);
      }
   }

   public static void L(boolean var0) {
      G = var0;
   }

   public static void B(float var0) {
      N = var0;
   }

   public String g(long var1) {
      return mode.Y();
   }

   public static float v() {
      return N;
   }

   public Freelook(long var1) {
      super(((a ^ (var1)) ^ 23928364651878L));
      // add code
      this.declare("Freelook", Category.Visual, "Allows you to move your camera without moving your head");
      var1 = a ^ var1;
   }

   public static boolean c() {
      return G;
   }

   public static void v(float var0) {
      v = var0;
   }

   public void i(long var1) {
      this.p = f.thePlayer.rotationYaw;
      this.s = f.thePlayer.rotationPitch;
      this.T = f.gameSettings.thirdPersonView;
      f.gameSettings.thirdPersonView = 1;
      B(this.p);
      v(this.s);
      L(true);
   }

   public void A(long var1) {
      L(false);
      f.gameSettings.thirdPersonView = this.T;
      f.thePlayer.rotationYaw = this.p;
      f.thePlayer.rotationPitch = this.s;
   }

   static {
      // add code
      mode = new ModeSetting("Mode", true, "HOLD", "HOLD", "TOGGLE");
   }
}
