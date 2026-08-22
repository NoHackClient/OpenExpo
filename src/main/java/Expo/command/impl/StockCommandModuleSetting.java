package Expo.command.impl;

import Expo.command.Command;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import java.util.List;
import java.util.Map;


public class StockCommandModuleSetting extends Command {
   private static String[] c;
   private static long[] e;
   private static Map g;
   private static String[] b;
   private static Map d;
   private static long a;

   public native String[] e(long var1);

   private native boolean d(long var1, NumberSetting var3, String[] var4);

   private static native String a(int var0, long var1);

   private native boolean b(TextSetting var1, short var2, String[] var3, int var4, short var5);

   private static native int b(int var0, long var1);


   public static native String O(String var0, String var1, String var2);

   public native List g(String[] var1, int var2, long var3);

   private native void d(Module var1, long var2);

   public static native boolean w(String var0, String var1);

   public native void j(String[] var1, long var2);
}
