package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.util.BuildInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ExpoCommandInfo extends Command {
   private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
   private static final String SEP = "§8-----------------------------";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"info"};
   }

   @Override
   public void h(long var1) {
      this.j(new String[0],0L);
   }

   @Override
   public void j(String[] var1, long var2) {
      ExpoCommands.chat(SEP);
      ExpoCommands.chat("§fAbout " + field("K", "Expo"));
      ExpoCommands.chat("§7Version: §f" + field("L", "?"));
      ExpoCommands.chat("§7Author: §f" + field("g", "?"));
      ExpoCommands.chat("§7Release: §f" + field("Z", "?"));
      ExpoCommands.chat("§7Build: §f" + field("q", "?"));

      String var4 = field("W", "");

      if (!var4.isEmpty()) {
         ExpoCommands.chat("§7Current user: §f" + var4);
      }

      ExpoCommands.chat("§7Current system time: §f"
                        + new SimpleDateFormat(PATTERN).format(new Date()));
      ExpoCommands.chat("§8" + field("B", ""));
      ExpoCommands.chat(SEP);
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList();
   }

   private static String field(String var0, String var1) {
      try {
         java.lang.reflect.Field var2 = BuildInfo.class.getDeclaredField(var0);
         var2.setAccessible(true);
         Object var3 = var2.get(null);
         return var3 == null ? var1 : String.valueOf(var3);
      } catch (Throwable var4) {
         return var1;
      }
   }
}
