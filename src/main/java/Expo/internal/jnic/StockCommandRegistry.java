package Expo.internal.jnic;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.command.impl.StockCommandModuleSetting;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Logger;

public class StockCommandRegistry {
   private static Map g;
   private static long a;
   private static String[] c;
   private static String[] b;
   private static Map d;
   private static long[] e;
   private static Logger u;
   public static Set<Command> L;
   public static StockCommandModuleSetting J;
   public static boolean $skidonion$1876635328;

   public static native Command X(long var0, String var2);

   public static boolean E(int var0, char var1, char var2, String var3) {
      return ExpoCommands.dispatch(var3);
   }

   static {
      L = new LinkedHashSet<Command>();
   }
}
