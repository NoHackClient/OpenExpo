package Expo.module;

import Expo.module.impl.combat.AntiFireball;
import Expo.module.impl.combat.KillAura;
import Expo.module.impl.player.ChestAura;
import Expo.module.impl.player.InvManager;
import Expo.module.impl.world.AutoDigPlace;
import Expo.module.impl.world.AutoTool;
import Expo.module.impl.world.BedNuker;
import Expo.module.impl.world.BlockIn;
import Expo.module.impl.world.Nuker;
import Expo.module.impl.world.Scaffold;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
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











public class ModulePriority {
   public static List<ModulePriorityEntry> l;
   public static Map<Class<? extends PriorityModule>, ModulePriorityEntry> C;

   public static boolean c(Class<? extends PriorityModule> var0) {
      ModulePriorityEntry var1 = C.get(var0);
      if (var1 == null) {
         return true;
      }

      for (int var2 = var1.L + 1; var2 < l.size() - 1; var2++) {
         ModulePriorityEntry var3 = l.get(var2);
         if (ModulePriorityEntry.r(var3)) {
            return false;
         }
      }

      return true;
   }

   public static void U(Class<? extends PriorityModule> var0, boolean var1) {
      ModulePriorityEntry var2 = C.get(var0);
      if (var2 != null) {
         ModulePriorityEntry.z(var2, var1);
      }
   }

   static {
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {


         Cipher var1;
         byte[] var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(132284884015632L << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var0 = new long[9];
         int var4 = 0;
         String var5 = "HÜ\f/\u001fYh\u009eÚ\u008eÐ~®L\u009a\u0080{0A~;\u0087Åm\u0004\"Ý |¦Ì[\u001e½ÿÉç¢\bÉë\u0011Ð\u0093jC÷Í\u0002l\u008a´\u00adÖ+»";
         int var6 = "HÜ\f/\u001fYh\u009eÚ\u008eÐ~®L\u009a\u0080{0A~;\u0087Åm\u0004\"Ý |¦Ì[\u001e½ÿÉç¢\bÉë\u0011Ð\u0093jC÷Í\u0002l\u008a´\u00adÖ+»".length();
         int var3 = 0;

         label36:
         while (true) {
            int var10001 = var3;
            var3 += 8;
            byte[] var7 = var5.substring(var10001, var3).getBytes("ISO-8859-1");
            long[] var19 = var0;
            var10001 = var4++;
            long var23 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            int var25 = -1;

            while (true) {
               long var8 = var23;
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
               long var27 = (var10[0] & 255L) << 56
                  | (var10[1] & 255L) << 48
                  | (var10[2] & 255L) << 40
                  | (var10[3] & 255L) << 32
                  | (var10[4] & 255L) << 24
                  | (var10[5] & 255L) << 16
                  | (var10[6] & 255L) << 8
                  | var10[7] & 255L;
               switch (var25) {
                  case 0:
                     var19[var10001] = var27;
                     if (var3 >= var6) {
                        C = new HashMap<>();
                        ModulePriorityEntry[] var20 = new ModulePriorityEntry[(int)var0[5]];
                        var20[0] = new ModulePriorityEntry(AutoTool.class, 0, null, 55039588965837L);
                        var20[1] = new ModulePriorityEntry(Nuker.class, 1, null, 55039588965837L);
                        var20[2] = new ModulePriorityEntry(InvManager.class, 2, null, 55039588965837L);
                        var20[3] = new ModulePriorityEntry(ChestAura.class, 3, null, 55039588965837L);
                        var20[4] = new ModulePriorityEntry(AntiFireball.class, 4, null, 55039588965837L);
                        var20[5] = new ModulePriorityEntry(BedNuker.class, 5, null, 55039588965837L);
                        var20[(int)var0[4]] = new ModulePriorityEntry(BlockIn.class, (int)var0[6], null, 55039588965837L);
                        var20[(int)var0[1]] = new ModulePriorityEntry(KillAura.class, (int)var0[3], null, 55039588965837L);
                        var20[(int)var0[8]] = new ModulePriorityEntry(Scaffold.class, (int)var0[0], null, 55039588965837L);
                        var20[(int)var0[7]] = new ModulePriorityEntry(AutoDigPlace.class, (int)var0[2], null, 55039588965837L);
                        l = Arrays.asList(var20);

                        for (ModulePriorityEntry var16 : l) {
                           C.put(var16.P, var16);
                        }

                        return;
                     }
                     break;
                  default:
                     var19[var10001] = var27;
                     if (var3 < var6) {
                        continue label36;
                     }

                     var5 = "ù¾»6N\u0097×\u0002\u001cå\u0096\u0017Ã\u0090ÓÆ";
                     var6 = "ù¾»6N\u0097×\u0002\u001cå\u0096\u0017Ã\u0090ÓÆ".length();
                     var3 = 0;
               }

               int var22 = var3;
               var3 += 8;
               var7 = var5.substring(var22, var3).getBytes("ISO-8859-1");
               var19 = var0;
               var10001 = var4++;
               var23 = (var7[0] & 255L) << 56
                  | (var7[1] & 255L) << 48
                  | (var7[2] & 255L) << 40
                  | (var7[3] & 255L) << 32
                  | (var7[4] & 255L) << 24
                  | (var7[5] & 255L) << 16
                  | (var7[6] & 255L) << 8
                  | var7[7] & 255L;
               var25 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var17) {
         throw new RuntimeException(var17);
      }
   }
}
