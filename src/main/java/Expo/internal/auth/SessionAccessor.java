package Expo.internal.auth;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionAccessor {
   private static Field t = null;
   private static final Minecraft u = Minecraft.getMinecraft();

   public static Session d() {
      return u.getSession();
   }

   private static Field C() {
      if (t == null) {
         try {
            for (Field var3 : Minecraft.class.getDeclaredFields()) {
               if (var3.getType().isAssignableFrom(Session.class)) {
                  t = var3;
                  t.setAccessible(true);
                  break;
               }
            }
         } catch (Exception var4) {
            t = null;
         }
      }

      return t;
   }

   public static void k(Session var0) {
      try {
         C().set(u, var0);
      } catch (Exception var2) {
      }
   }
}
