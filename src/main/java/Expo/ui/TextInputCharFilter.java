package Expo.ui;












public class TextInputCharFilter {
   private static long a;

   private TextInputCharFilter() {
   }

   static {
      a = 32085462174855L;
   }

   public static boolean R(char var0) {
      return var0 >= 32 && var0 != 127;
   }

}
