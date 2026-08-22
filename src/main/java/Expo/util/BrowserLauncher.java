package Expo.util;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.net.URI;

public class BrowserLauncher {
   private static long a;

   static {
      a = 50444756987960L;
   }

   public static void Y(String var0) {
      try {
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var0), null);
      } catch (Exception var2) {
      }
   }

   public static void F(URI var0) {
      try {
         Class var3 = Class.forName("java.awt.Desktop");
         Object var4 = var3.getMethod("getDesktop").invoke(null);
         var3.getMethod("browse", URI.class).invoke(var4, var0);
      } catch (Exception var5) {
      }
   }
}
