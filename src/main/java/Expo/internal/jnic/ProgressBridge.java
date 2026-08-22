package Expo.internal.jnic;


public class ProgressBridge {
   private static long a;
   private static volatile ProgressListener d;

   public static void H(ProgressListener var0) {
      d = var0;
   }


   public static void G(ProgressListener var0) {
      if (d == var0) {
         d = null;
      }
   }

   public static StringCallback L(long var0, String var2, int var3, int var4) {
      ProgressListener var7 = d;
      if (var7 != null) {
         var7.w(var2, var3);
      }

      try {
         Class var8 = Class.forName("net.minecraftforge.fml.common.ProgressManager");
         Object var12 = var8.getMethod("push", String.class, int.class).invoke(null, var2, var3);
         int[] var10 = new int[]{0};
         return var3x -> {
            int var6 = ++var10[0];
            ProgressListener var7x = d;
            if (var7x != null) {
               var7x.q(var3x, var6, var3);
            }

            try {
               var12.getClass().getMethod("step", String.class).invoke(var12, var3x);
            } catch (Throwable var9x) {
            }
         };
      } catch (Throwable var11) {
         int[] var9 = new int[]{0};
         return var2x -> {
            ProgressListener var3x = d;
            if (var3x != null) {
               var3x.q(var2x, ++var9[0], var3);
            }
         };
      }
   }

   static {

      a = 77697956573153L;
   }

}
