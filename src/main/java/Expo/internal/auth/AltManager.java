package Expo.internal.auth;

import Expo.ExpoClient;
import Expo.enums.AccountType;
import Expo.ui.screen.CookieLoginScreen;
import Expo.ui.screen.ReconnectHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;











public class AltManager {
   public static ArrayList<Account> Q;
   private static File i;
   private static Gson J;
   private static long a;
   private static boolean I;
   private static String[] b;
   private static long[] e;
   private static Minecraft X;



   public static void O(long var0) {
      long var2 = var0 ^ 76724696706368L;

      try {
         JsonArray var4 = new JsonArray();

         for (Account var6 : Q) {
            var4.add(var6.F(var2));
         }

         PrintWriter var9 = new PrintWriter(new FileWriter(i));
         var9.println(J.toJson(var4));
         var9.close();
      } catch (IOException var7) {
      }
   }



   public static void Q(int var0, short var1, short var2) {
      Q.clear();

      try {
         JsonElement var7 = new JsonParser().parse(new BufferedReader(new FileReader(i)));
         if (var7 instanceof JsonArray) {
            for (JsonElement var10 : var7.getAsJsonArray()) {
               JsonObject var11 = var10.getAsJsonObject();
               Q.add(Account.k(var11,0L));
            }
         }
      } catch (FileNotFoundException var12) {
      } catch (JsonSyntaxException var13) {
         System.err.println("Error parsing accounts.json: " + var13.getMessage());
      }
   }

   public static void e(short var0, long var1, String var3) {
      long var4 = ((long)var0 << 48 | 76783016628697L) ^ a;
      long var6 = var4 ^ 83831605235907L;
      Optional var8 = Q.stream().filter(var1x -> var1x.h().equalsIgnoreCase(var3) && var1x.v() == AccountType.OFFLINE).findFirst();
      if (var8.isPresent()) {
      } else {
         Q.add(new Account("", "accessToken", var3, "", 0L, AccountType.OFFLINE));
         O(var6);
      }
   }

   public static void M(long var0) {


      if (!I) {
         TrustAllSslContext.j();
         if (!i.exists()) {
            try {
               if ((i.getParentFile().exists() || i.getParentFile().mkdirs()) && i.createNewFile()) {
               }
            } catch (IOException var5) {
            }
         }

         if (ExpoClient.w != null) {
            ExpoClient.w.s(new ReconnectHandler(), 25046058167973L);
            I = true;
         }
      }
   }

   public static void h(int var0, short var1, File var2, char var3, GuiScreen var4) {
      long var5 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 95328364081089L;
      CookieLoginScreen var9 = new CookieLoginScreen(var4, var7);
      CookieAuthService.C(var2, var9);
   }



   static {
      a = 94244023323350L;
      X = Minecraft.getMinecraft();
      i = new File(X.mcDataDir, "accounts.json");
      J = new GsonBuilder().setPrettyPrinting().create();
      Q = new ArrayList<>();
      I = false;
   }


}
