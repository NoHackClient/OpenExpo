package Expo.internal.jnic;

import Expo.internal.restore.ExpoBootstrap;
import Expo.internal.restore.ExpoRavenGui;
import Expo.module.Category;
import Expo.module.Module;
import Expo.setting.Setting;
import java.util.Map;
import org.apache.logging.log4j.Logger;


public class StockClientBootstrap {
   private static long[] e;
   private static Map d;
   private static Map g;
   private static volatile boolean F;
   private static long a;
   private static String[] c;
   private static String[] b;
   private static Logger r;
   public static boolean $skidonion$891820656;


   public static native void q(long var0);

   public static native void Z(
      Module var0, String var1, long var2, Boolean var4, Category var5, Boolean var6, String var7, boolean var8, boolean var9, Setting... var10
   );

   private static native String a(int var0, long var1);

   public static void F() {
      ExpoBootstrap.initClient();
   }

   public static void P(int var0) {
      ExpoRavenGui.installPanels();
   }

   public static native void W(long var0, Module var2, String var3, Boolean var4, Category var5, Boolean var6, String var7, Setting... var8);
}
