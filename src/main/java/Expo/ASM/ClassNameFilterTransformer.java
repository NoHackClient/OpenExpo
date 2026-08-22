package Expo.ASM;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClassNameFilterTransformer implements IClassTransformer {
   private static Set<String> c;
   private static Logger i;
   private static Set<String> U;
   private static long a;
   private static Class<?> W;


   public ClassNameFilterTransformer() {
      i.info("ASM Transformer initialized");
   }

   public static boolean M(String var0) {
      if (var0 == null) {
         return true;
      }

      String var3 = var0.replace((char)47, (char)46);
      return var3.startsWith("java.")
         || var3.startsWith("javax.")
         || var3.startsWith("sun.")
         || var3.startsWith("com.sun.")
         || var3.startsWith("org.")
         || var3.startsWith("com.google.")
         || var3.startsWith("net.minecraftforge.")
         || var3.startsWith("net.minecraft.launchwrapper.")
         || var3.startsWith("optifine.")
         || var3.startsWith("com.spiderfrog.")
         || var3.startsWith("Expo.");
   }

   public byte[] transform(String var1, String var2, byte[] var3) {
      if (var3 == null) {
         return null;
      }

      if (!M(var1) && !M(var2)) {
         String var6 = z(var1, var2);
         if (var6 != null && !c.contains(var6) && !U.contains(var6)) {
            ClassTransform var7 = TransformerRegistry.k(var2);
            if (var7 == null) {
               var7 = TransformerRegistry.k(var1);
            }

            if (var7 == null) {
               var7 = TransformerRegistry.k(var6);
            }

            if (var7 == null) {
               return var3;
            }


            if (!c.add(var6)) {
               i.info("ASM recursive transform skipped: {}", new Object[]{var6});
               return var3;
            }

            try {
               byte[] var8 = var7.S(var3);
               if (var8 != null && var8 != var3) {
                  U.add(var6);
                  i.info("ASM transformed: {}", new Object[]{var7.E()});
                  return var8;
               }

               i.info("ASM target matched but unchanged: {} (name={}, transformedName={})", new Object[]{var7.E(), var1, var2});
            } catch (Throwable var13) {
               i.error("ASM transform failed for {}: {}: {}", new Object[]{var7.E(), var13.getClass().getName(), var13.getMessage(), var13});
            } finally {
               c.remove(var6);
            }

            return var3;
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }




   public static String z(String var0, String var1) {
      String var4 = var1 != null && !var1.isEmpty() ? var1 : var0;
      return var4 == null ? null : var4.replace((char)47, (char)46);
   }

   static {
      a = 82465177970730L;
      i = LogManager.getLogger("Expo ASM Transformer");
      c = Collections.newSetFromMap(new ConcurrentHashMap<>());
      U = Collections.newSetFromMap(new ConcurrentHashMap<>());
   }

}
