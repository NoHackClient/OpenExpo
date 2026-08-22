package Expo.command.impl;

import Expo.command.Command;
import Expo.enums.DetectedCheat;
import Expo.internal.CheaterDetector;
import Expo.util.CheaterRegistry;
import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class StockCommandCheaters extends Command {
   private static Minecraft p;
   private static String[] a;
   private static Map c;
   private static String[] b;

   private static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 22492;
      if (b[var3] == null) {
         Object[] var5;
         try {
            Long var4 = Thread.currentThread().getId();
            var5 = (Object[])c.get(var4);
            if (var5 == null) {
               var5 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               c.put(var4, var5);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/command/impl/StockCommandCheaters", var10);
         }

         byte[] var7 = new byte[8];
         var7[0] = (byte)(var1 >>> 56);

         for (int var8 = 1; var8 < 8; var8++) {
            var7[var8] = (byte)(var1 << var8 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var7);
         SecretKey var9 = ((SecretKeyFactory)var5[1]).generateSecret(var11);
         ((Cipher)var5[0]).init(2, var9, (IvParameterSpec)var5[2]);
         byte[] var6 = a[var3].getBytes("ISO-8859-1");
         b[var3] = a(((Cipher)var5[0]).doFinal(var6));
      }

      return b[var3];
   }

   static {
      p = MinecraftRef.c((byte)0, 0L);
      c = new HashMap(13);
      a = new String[]{"\u00a9F\u00c5\\+\u00a8M\u0095", "C\u00b9c\u00874\u001fD\u0000]U\u00f5]3OKs", "K\u00e7-\u00e1\u00d7\u00f3\u00d3\u001a\u00ba\u00f36\u00eb3#\u00f5\u00a0", "<\u00f3xk6Y\u0091\u00d2", "N\u0005AN:7\u008c\u00cf\u0012\u00d6\u00d8$$\u00b7_\u0091", "\u00ba\u00ce\u009dl\u00beS.O\u00ee[\t\u0096h\u000c\u00a3\u0088", "0\u001f\u00be2?9\"*\u00c784N\u00d5A\u00deV\u00f5d\u00e5R\u0017\u0003\u009c\u0096\u00eb\u00cf1\u00f9\u00f7(\u0083H\u00aa\u00c6\u0017\u00c9l\u00d8\u001c\u00ff\u00f0;r6\u0006\u008b\u0014\u0096", "s1\u0011\u00bf,\u00913G_\u00b3\u00c0\u00b5\u00f8\u009c\u0096&", "3b\u0089F\u00c8w\u00c5T\u00c7\u00e2\u00c33\u00a8e\u0016\u001e\u0082\u0083\u00e6'\u00a5\u00f1d\u00af", "\u0094\u00df(^\u0099H\u0008X", "\u0010\u00e4\u00fdU\u00ce\u00cf\u00f5\u001a\u00c5)/\u00de[\u001a\u00bdR\u00c8!\u00be\u00cc\u001f\u00e0\u0017\u00b1"};
      b = new String[11];
   }

   public void j(String[] var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var2 ^ 64071467756989L;
      LinkedHashMap var6 = new LinkedHashMap();

      for (Entry var8 : CheaterDetector.c.entrySet()) {
         if (p.theWorld.getPlayerEntityByUUID((UUID)var8.getKey()) != null) {
            CheaterRegistry var9 = CheaterDetector.R.get(var8.getKey());
            if (var9 != null && var9.M()) {
               var6.put(var8.getKey(), var9);
            }
         }
      }

      if (var6.isEmpty()) {
         Expo.util.ClientUtil.t(var4, a(30577, 263412545876638464L ^ var2));
      } else {
         Expo.util.ClientUtil.t(var4, a(14757, 374938952792798678L ^ var2));

         for (Entry var15 : (Iterable<Entry>)(var6.entrySet())) {
            EntityPlayer var16 = p.theWorld.getPlayerEntityByUUID((UUID)var15.getKey());
            ArrayList var10 = new ArrayList();

            for (Entry var12 : ((CheaterRegistry)var15.getValue()).e.entrySet()) {
               if ((Boolean)var12.getValue()) {
                  DetectedCheat var13 = (DetectedCheat)var12.getKey();
                  var10.add(var13.colorFormatCode + var13.name());
               }
            }

            Expo.util.ClientUtil.t(
               var4,
               a(1313, 6512990764992088412L ^ var2)
                  + var16.getDisplayName().getFormattedText()
                  + a(397, 1170653753028759027L ^ var2)
                  + String.join(a(10075, 5448382726235319083L ^ var2), var10)
            );
         }
      }
   }

   public void h(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var3 = var1 ^ 73519816701158L;
      Expo.util.ClientUtil.t(var3, a(30904, 6524925473862299036L ^ var1));
      Expo.util.ClientUtil.t(var3, a(17697, 7720076776179994624L ^ var1));
      Expo.util.ClientUtil.b("");
      Expo.util.ClientUtil.b(a(31247, 2754259379236482863L ^ var1));
   }

   public String[] e(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return new String[]{a(3587, 2446196992674237243L ^ var1), a(15674, 6295245790310119431L ^ var1), a(11990, 3851171648294562794L ^ var1)};
   }

   public boolean J() {
      return false;
   }

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }
}
