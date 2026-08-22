package Expo.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;











public class SoundEngine {
   private static long a;
   private SoundCallback e;
   private static CopyOnWriteArrayList<OggStreamPlayer> V;

   public static void d(InputStream var0, long var1, char var3, float var4) {
      long var5 = (var1 << 16 | (long)var3 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 96736540735253L;
      if (var0 != null) {
         D(var0, var4, var7);
      }
   }

   public void o(SoundCallback var1) {
      this.e = var1;
   }

   public static void G(int var0) {

      for (OggStreamPlayer var8 : V) {
         var8.L();
      }
   }

   private static void D(InputStream var0, float var1, long var2) {
      var2 = a ^ var2;
      long var10001 = var2 ^ 43034187926941L;
      int var4 = (int)((var2 ^ 43034187926941L) >>> 32);
      int var5 = (int)((var2 ^ 43034187926941L) << 32 >>> 48);
      int var6 = (int)(var10001 << 48 >>> 48);
      var10001 = var2 ^ 59569126331397L;
      int var7 = (int)((var2 ^ 59569126331397L) >>> 48);
      int var8 = (int)((var2 ^ 59569126331397L) << 16 >>> 48);
      OggStreamPlayer var10 = new OggStreamPlayer(var4, (short)var5, var0, (char)var6, var1, null);
      V.add(var10);
      var10.t((char)var7, (char)var8);
   }

   public static void y(long var0, String var2) {
      long var3 = var0 ^ 88759620491555L;
      B(var2, var3, 0.0F);
   }

   public static void e(int var0, long var1) {
      long var3 = ((long)var0 << 32 | 3609307507L) ^ a;
      long var5 = var3 ^ 119972016601853L;

      for (OggStreamPlayer var8 : V) {
         var8.F(var5);
      }
   }

   public static void c(long var0) {



      for (OggStreamPlayer var5 : V) {
         var5.b(129455148608165L);
      }

      V.clear();
   }

   static CopyOnWriteArrayList l() {
      return V;
   }

   static {
      // add code

      a = 19188549812115L;

      V = new CopyOnWriteArrayList<>();
   }


   public static void B(String var0, long var1, float var3) {
      long var4 = var1 ^ 6527503770779L;

      try {
         if (var0 == null || var0.isEmpty() || var0.charAt(0) != 47) {
            return;
         }

         URL var6 = SoundEngine.class.getResource(var0);
         if (var6 == null) {
            return;
         }

         InputStream var7 = var6.openStream();
         D(var7, var3, var4);
      } catch (IOException var8) {
         Expo.internal.restore.ExpoDiag.attribute(var8, "SoundEngine.B/3#0");
      }
   }

   public static void E(long var0, InputStream var2) {
      var0 = a ^ var0;
      long var3 = (var0 ^ 120840991663794L) >>> 16;
      int var5 = (int)((var0 ^ 120840991663794L) << 48 >>> 48);
      d(var2, var3, (char)var5, 0.0F);
   }

}
