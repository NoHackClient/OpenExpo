package Expo.internal.auth;

import java.lang.reflect.Field;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionSwapper {
   private static long a;

   private static String E(String var2) {
      String var3 = "offlinePlayer:" + var2;
      return UUID.nameUUIDFromBytes(var3.getBytes()).toString().replace("-", "");
   }

   private static void t(Session var0) {
      try {
         Minecraft var3 = Minecraft.getMinecraft();
         Field var4 = Minecraft.class.getDeclaredField("session");
         var4.setAccessible(true);
         var4.set(var3, var0);
      } catch (Exception var5) {
         System.err.println("failed to set Minecraft session: " + var5.getMessage());
      }
   }

   static {
      a = 74417733219777L;
   }

   public static boolean D(String var0, long var1) {
      if (var0 != null && !var0.trim().isEmpty()) {
         AltManager.e((short)0, 76783016628697L, var0);
         String var10 = E(var0);
         Session var11 = new Session(var0, var10, "accessToken", "legacy");
         t(var11);
         SessionAccessor.k(var11);
         return true;
      } else {
         return false;
      }
   }
}
