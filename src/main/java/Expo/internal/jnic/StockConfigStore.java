package Expo.internal.jnic;

import Expo.internal.restore.ExpoConfig;
import Expo.module.Category;
import Expo.module.Module;
import Expo.setting.Setting;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class StockConfigStore {
   private static Map d;
   private static String[] c;
   private static String w;
   private static String[] b;
   private static String t;
   private static long a;
   public static String Q;

   public static native boolean g(String var0, long var1, JsonObject var3);

   public static native boolean K(String var0, long var1);

   public static native boolean p(long var0, JsonObject var2, String var3);

   public static native boolean R(char var0, short var1, int var2);

   public static native JsonObject w(long var0, JsonObject var2);

   public static native boolean u(JsonObject var0, long var1);

   public static native void U(JsonObject var0, long var1);

   public static native void n(JsonObject var0, long var1);

   public static native boolean H(JsonObject var0, long var1);

   public static native boolean J(long var0);

   public static native JsonObject E(String var0, long var1) throws IOException;

   public static native JsonObject v(JsonObject var0, long var1, Category var3);

   public static native boolean l(long var0, byte var2);

   public static native boolean L(long var0, String var2);

   public static native JsonObject J(int var0, int var1, JsonObject var2);

   public static native void v(long var0, JsonObject var2);

   public static native JsonObject x(JsonObject var0, String var1, long var2);

   public static native File g(long var0);

   public static native void S(long var0);

   public static native void l(long var0, JsonObject var2);

   public static native JsonObject v$JsonObject(long var0, JsonObject var2);

   public static native void K(long var0, JsonObject var2, Category var3);

   public static native Path u(long var0, String var2);

   public static native JsonObject i(long var0, JsonObject var2);

   public static native JsonObject Z(JsonObject var0, long var1);

   public static native JsonObject A(long var0, JsonObject var2);

   public static boolean o(String var2) {
      return ExpoConfig.save(var2).ok;
   }

   public static native boolean E(String var0, short var1, short var2, int var3, boolean var4);
}
