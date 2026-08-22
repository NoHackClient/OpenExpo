package Expo.util;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.HypixelGameStateBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.WorldLoadEvent;
import java.io.UnsupportedEncodingException;
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

public class HypixelGameState implements EventSubscriber {
   private static boolean E;
   private static boolean e;
   private static HypixelScoreboardParser h;
   private static boolean A;
   private static boolean j;
   private static long a;
   private static boolean r;
   private static boolean G;
   private static boolean N;
   private static boolean H;

   private static void u(HypixelScoreboardParser var0) {
      A = var0.V();
      H = var0.F();
      G = var0.k();
      r = var0.P();
      E = var0.f();
      e = var0.l();
      j = var0.W();
      N = var0.i();
   }

   public static boolean r() {
      return N;
   }

   public static boolean P() {
      return G;
   }

   static {
      try {
         a = 84332417681851L;
         long var11 = a ^ 21143209762804L;
         long var13 = var11 ^ 100374323679001L;
         a();
         Cipher var1;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(var11 << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var0 = new long[2];
         int var4 = 0;
         String var5 = "\u0007\bæ¦[\u0013â¯z!7\u007fWÏª?";
         int var6 = "\u0007\bæ¦[\u0013â¯z!7\u007fWÏª?".length();
         int var3 = 0;

         do {
            int var10001 = var3;
            var3 += 8;
            byte[] var7 = var5.substring(var10001, var3).getBytes("ISO-8859-1");
            long var8 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            byte[] var10 = var1.doFinal(
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
            long var10004 = (var10[0] & 255L) << 56
               | (var10[1] & 255L) << 48
               | (var10[2] & 255L) << 40
               | (var10[3] & 255L) << 32
               | (var10[4] & 255L) << 24
               | (var10[5] & 255L) << 16
               | (var10[6] & 255L) << 8
               | var10[7] & 255L;
            int var17 = -1;
            var0[(var4++)] = var10004;
         } while (var3 < var6);

         A = (var0[0]) != 0;
         G = (var0[1]) != 0;
         r = (var0[1]) != 0;
         H = (var0[1]) != 0;
         E = (var0[1]) != 0;
         e = (var0[1]) != 0;
         j = (var0[1]) != 0;
         N = (var0[1]) != 0;
         h = new HypixelScoreboardParser(var13);
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var15) {
         throw new RuntimeException(var15);
      }
   }

   public static boolean F() {
      return r;
   }

   public static boolean A() {
      return H;
   }

   public void onPostTick(long var1, PostTickEvent var3) {
      k(113061010428450L);
   }

   public static HypixelScoreboardParser L() {
      return h;
   }

   private static void a() {
   }

   public final void x(long var1, EventBus var3) {
      HypixelGameStateBinder.F(var3, this);
   }

   public static boolean p() {
      return A;
   }

   public static boolean C() {
      return e;
   }

   public static boolean d() {
      return j;
   }

   public void onWorldLoad(WorldLoadEvent var1, long var2) {
      HypixelScoreboardParser.M((short)0, 730080858);
      h = new HypixelScoreboardParser(5239149500758L);
      u(h);
   }

   public static boolean G() {
      return E;
   }

   public static void k(long var0) {
      h = new HypixelScoreboardParser(5239149500758L);
      u(h);
   }
}
