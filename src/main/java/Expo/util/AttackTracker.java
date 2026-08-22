package Expo.util;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AttackTrackerBinder;
import Expo.event.events.AttackEntityEvent;
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











public class AttackTracker implements EventSubscriber {
   public static boolean s;
   private static long a;

   public static boolean J() {
      return s;
   }

   static {
      a = 61906676875913L;
      // add code
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {
         long var7 = a ^ 62761001884487L;
         a();
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var7 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var7 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

         byte[] var6 = var2.doFinal(
            new byte[]{
               (byte)23L,
               (byte)5915L,
               (byte)1514303L,
               (byte)387661751L,
               (byte)99241408291L,
               (byte)25405800522515L,
               (byte)6503884933764078L,
               (byte)1664994543043603969L
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
         s = (var0) != 0;
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var9) {
         throw new RuntimeException(var9);
      }
   }

   public void onAttackEntity(AttackEntityEvent var1, long var2) {



      if (!s) {
         var1.I(21307, 3074332907L);
      }
   }

   public static void Z(boolean var0) {
      s = var0;
   }

   private static void a() {
   }

   public final void x(long var1, EventBus var3) {
      AttackTrackerBinder.D(var3, this);
   }

}
