package Expo.internal.auth;

import Expo.enums.AccountType;
import Expo.ui.screen.AccountManagerScreen;
import Expo.ui.screen.CookieLoginScreen;
import Expo.util.ChatFormatting;
import Expo.util.Sneaky;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.net.ssl.SSLSocketFactory;
import net.minecraft.util.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CookieAuthService {
   private static long[] h;
   private static String[] b;
   private static long a;
   private static List<String> N;
   private static Map g;
   private static Map k;
   private static String[] c;
   private static Map d;
   private static String u;
   private static RequestConfig K;
   private static long[] e;
   private static ExecutorService j;
   private static Gson P;
   private static List<String> X;
   private static Integer[] f;


   private static CompletableFuture<Boolean> R(Map<String, String> var0, CookieLoginScreen var1) {
      return CompletableFuture.supplyAsync(() -> {
          try {long var2 = 94968564611454L;









         try {
            var1.x("&fRequesting Microsoft access token...&r");
            String var17 = y(var0);
            if (var17 == null) {
               var1.x("&cFailed to get access token (cookies may be expired)&r");
               return false;
            }

            var1.x("&fAuthenticating with Xbox Live...&r");
            Map var18 = p((short)0, (char)7945, var17, 1176163491);
            var1.x("&fGetting XSTS token...&r");
            String var19 = e((String)var18.get("Token"), 35749052507919L);
            String var20 = "XBL3.0 x=" + (String)var18.get("uhs") + ";" + var19;
            var1.x("&fAuthenticating with Minecraft...&r");
            MinecraftLoginResponse var21 = W(76987989326542L, var20);
            if (var21 != null && var21.m != null) {
               var1.x("&fRetrieving Minecraft profile...&r");
               MinecraftProfileResponse var22 = l(42909564680031L, var21.m);
               if (var22 != null && var22.g != null) {
                  Session var23 = new Session(var22.g, var22.z, var21.m, "mojang");
                  AltManager.Q.add(new Account("", var21.m, var22.g, var22.z, 0L, AccountType.MINECRAFT));
                  AltManager.O(11006179144378L);
                  SessionAccessor.k(var23);
                  var1.x("&aSuccessfully logged in as " + var23.getUsername() + "&r");
                  return true;
               } else {
                  var1.x("&cFailed to get Minecraft profile&r");
                  return false;
               }
            } else {
               var1.x("&cFailed to get Minecraft access token&r");
               return false;
            }
         } catch (Exception var24) {
            System.err.println("[CookieAuth] Authentication failed: " + var24.getMessage());
            var1.x("&cAuthentication failed: " + var24.getMessage() + "&r");
            return false;
         }
      } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } });
   }


   private static boolean o( String var2) {
      return var2.startsWith("__Host-")
         || var2.startsWith("MSP")
         || var2.equals("JSH")
         || var2.equals("JSHP")
         || var2.equals("NAP")
         || var2.equals("OParams")
         || var2.equals("PPLState")
         || var2.equals("WLSSC")
         || var2.equals("uaid")
         || var2.equals("AMCSecAuth")
         || var2.equals("ESTSAUTH")
         || var2.equals("ESTSAUTHPERSISTENT")
         || var2.equals("MSPOK")
         || var2.equals("MSPShared")
         || var2.equals("MSPPre")
         || var2.equals("MSPCID")
         || var2.equals("ANON")
         || var2.equals("pres")
         || var2.equals("LOpt")
         || var2.equals("MSPOAuthVis");
   }

   public static void K() {
      j.shutdown();
   }

   private static Map L(String var0) {
      LinkedHashMap var7 = new LinkedHashMap();
      String var8 = var0.replace("\n", "").replace("\r", "");

      for (String var12 : var8.split(";")) {
         var12 = var12.trim();
         if (var12.contains("=")) {
            int var13 = var12.indexOf(61);
            String var14 = var12.substring(0, var13).trim();
            String var15 = var12.substring(var13 + 1).trim();
            if (o( var14) && !var15.isEmpty()) {
               i(var7, var14, var15);
            }
         }
      }

      if (var7.isEmpty()) {
         for (String var21 : var0.split("\\r?\\n")) {
            var21 = var21.trim();
            if (var21.contains("=")) {
               int var23 = var21.indexOf(61);
               String var24 = var21.substring(0, var23).trim();
               String var25 = var21.substring(var23 + 1).trim();
               if (o( var24) && !var25.isEmpty()) {
                  i(var7, var24, var25);
               }
            }
         }
      }

      return var7;
   }

   private static String z(String var0, String var1) {
      URI var2 = URI.create(var0);
      URI var3 = var2.resolve(var1);
      return var3.toString();
   }

   public static MinecraftLoginResponse W(long var0, String var2) throws IOException, Exception, Throwable {




      AccountManagerScreen.q = new TimedStatusMessage(ChatFormatting.y("&7Logging into Minecraft services..."), 5000L);
      String var9 = "{\"identityToken\":\"" + var2 + "\",\"ensureLegacyEnabled\":true}";
      CloseableHttpClient var10 = Y(18702133247L, (byte)17);
      Throwable var11 = null;

      try {
         HttpPost var12 = new HttpPost(URI.create("https://api.minecraftservices.com/authentication/login_with_xbox"));
         var12.setConfig(K);
         var12.setHeader("Content-Type", "application/json");
         var12.setHeader("Accept", "application/json");
         var12.setEntity(new StringEntity(var9, StandardCharsets.UTF_8));
         CloseableHttpResponse var13 = var10.execute(var12);
         int var14 = var13.getStatusLine().getStatusCode();
         String var15 = EntityUtils.toString(var13.getEntity(), StandardCharsets.UTF_8);
         var13.close();
         if (var14 != 200) {
            throw new IOException("Minecraft login failed (" + var14 + "): " + var15);
         } else {
            return (MinecraftLoginResponse)P.fromJson(var15, MinecraftLoginResponse.class);
         }
      } catch (Throwable var25) {
         var11 = var25;
         throw var25;
      } finally {
         if (var10 != null) {
            if (var11 != null) {
               try {
                  var10.close();
               } catch (Throwable var24) {
                  var11.addSuppressed(var24);
               }
            } else {
               var10.close();
            }
         }
      }
   }

   private static void i(Map var0, String var1, String var2) {
      if (!"Disabled".equalsIgnoreCase(var2)) {
         var0.put(var1, var2);
      }
   }

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private static Map p(short var0, char var1, String var2, int var3) throws Exception, Throwable {
      long var4 = ((long)var0 << 48 | (long)var1 << 48 >>> 16 | (long)var3 << 32 >>> 32) ^ a;
      long var6 = (var4 ^ 102018160879679L) >>> 8;
      int var8 = (int)((var4 ^ 102018160879679L) << 56 >>> 56);
      Exception var9 = null;

      for (String var13 : new String[]{"t=", "d="}) {
         try {
            JsonObject var14 = new JsonObject();
            JsonObject var15 = new JsonObject();
            var15.addProperty("AuthMethod", "RPS");
            var15.addProperty("SiteName", "user.auth.xboxlive.com");
            var15.addProperty("RpsTicket", var13 + var2);
            var14.add("Properties", var15);
            var14.addProperty("RelyingParty", "http://auth.xboxlive.com");
            var14.addProperty("TokenType", "JWT");
            CloseableHttpClient var16 = Y(var6, (byte)var8);
            Throwable var17 = null;

            try {
               HttpPost var18 = new HttpPost(URI.create("https://user.auth.xboxlive.com/user/authenticate"));
               var18.setConfig(K);
               var18.setHeader("Content-Type", "application/json");
               var18.setHeader("User-Agent", "Go-http-client/1.1");
               var18.setHeader("X-Xbl-Contract-Version", "0");
               var18.setEntity(new StringEntity(var14.toString(), StandardCharsets.UTF_8));
               CloseableHttpResponse var19 = var16.execute(var18);
               int var20 = var19.getStatusLine().getStatusCode();
               String var21 = EntityUtils.toString(var19.getEntity(), StandardCharsets.UTF_8);
               var19.close();
               if (var20 != 200) {
                  throw new IOException("Xbox Live authentication failed (" + var20 + "): " + var21);
               }

               JsonObject var22 = new JsonParser().parse(var21).getAsJsonObject();
               LinkedHashMap var23 = new LinkedHashMap();
               var23.put("Token", var22.get("Token").getAsString());
               var23.put("uhs", var22.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject().get("uhs").getAsString());
               return var23;
            } catch (Throwable var34) {
               var17 = var34;
               throw var34;
            } finally {
               if (var16 != null) {
                  if (var17 != null) {
                     try {
                        var16.close();
                     } catch (Throwable var33) {
                        var17.addSuppressed(var33);
                     }
                  } else {
                     var16.close();
                  }
               }
            }
         } catch (Exception var36) {
            var9 = var36;
         }
      }

      throw var9 != null ? var9 : new IOException("Xbox Live authentication failed");
   }

   private static String H( String var2) {
      String var3 = var2;
      if (var2.contains("#")) {
         var3 = var2.split("#", 2)[1];
      } else if (var2.contains("?")) {
         var3 = var2.split("\\?", 2)[1];
      }

      String var4 = null;
      String var5 = null;

      for (String var9 : var3.split("&")) {
         if (var9.startsWith("error=")) {
            var4 = var9.substring("error=".length());
         } else if (var9.startsWith("error_description=")) {
            var5 = var9.substring("error_description=".length());
         }
      }

      if (var4 == null) {
         return null;
      }

      try {
         var4 = URLDecoder.decode(var4, "UTF-8");
         if (var5 != null) {
            var5 = URLDecoder.decode(var5, "UTF-8");
         }
      } catch (Exception var10) {
      }

      return var5 != null ? var4 + ": " + var5 : var4;
   }

   private static CloseableHttpClient Y(long var0, byte var2) {
      long var3 = (var0 << 8 | (long)var2 << 56 >>> 56) ^ a;

      try {
         SSLSocketFactory var5 = TrustAllSslContext.j().getSocketFactory();
         SSLConnectionSocketFactory var6 = new SSLConnectionSocketFactory(var5, new String[]{"TLSv1.2"}, null, new BrowserCompatHostnameVerifier());
         return HttpClientBuilder.create().setSSLSocketFactory(var6).disableRedirectHandling().build();
      } catch (Exception var7) {
         return HttpClients.custom().disableRedirectHandling().build();
      }
   }

   public static CompletableFuture<Boolean> C(File var0, CookieLoginScreen var1) {
      CompletableFuture var2 = new CompletableFuture();
      j.execute(() -> {
          try {long var3 = 61773449943444L;


         try {
            var1.x("&fReading cookie file...&r");
            Map<String, String> var9 = Z(var0);
            if (var9.isEmpty()) {
               var1.x("&cNo valid Microsoft cookies found in file&r");
               var2.complete(false);
               return;
            }

            if (!d(var9)) {
               var1.x("&cMissing auth cookies (need __Host-MSAAUTH, JSH, or JSHP)&r");
               var2.complete(false);
               return;
            }

            var1.x("&fAuthenticating with Microsoft...&r");
            R(var9, var1).whenComplete((var2xx, var3x) -> {
               if (var3x != null) {
                  System.err.println("[CookieAuth] Authentication failed: " + var3x.getMessage());
                  var1.x("&cAuthentication failed: " + var3x.getMessage() + "&r");
                  var2.complete(false);
               } else {
                  var2.complete(var2xx);
               }
            });
         } catch (Exception var10) {
            var1.x("&cError processing cookie file: " + var10.getMessage() + "&r");
            var2.complete(false);
         }
      } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } });
      return var2;
   }

   private static Map Z(File var0) throws FileNotFoundException, IOException, Throwable {



      String var11 = j(var0);
      if (var11.trim().isEmpty()) {
         return Collections.emptyMap();
      }

      if (var11.trim().startsWith("[")) {
         return n(var11);
      }

      Map var12 = A(var11);
      return !var12.isEmpty() ? var12 : L(var11);
   }

   private static String J( String var2) throws UnsupportedEncodingException, Exception {
      if (var2.contains("#")) {
         String var3 = var2.split("#", 2)[1];

         for (String var7 : var3.split("&")) {
            if (var7.startsWith("access_token=")) {
               return URLDecoder.decode(var7.substring("access_token=".length()), "UTF-8");
            }
         }
      }

      if (var2.contains("access_token=")) {
         int var9 = var2.indexOf("access_token=") + "access_token=".length();
         int var10 = var2.indexOf(38, var9);
         String var11 = var10 == -1 ? var2.substring(var9) : var2.substring(var9, var10);
         return URLDecoder.decode(var11, "UTF-8");
      } else {
         return null;
      }
   }

   private static Map n(String var0) {



      LinkedHashMap var10 = new LinkedHashMap();

      try {
         JsonElement var11 = new JsonParser().parse(var0);
         JsonArray var12;
         if (var11.isJsonArray()) {
            var12 = var11.getAsJsonArray();
         } else {
            if (!var11.isJsonObject() || !var11.getAsJsonObject().has("cookies")) {
               return var10;
            }

            var12 = var11.getAsJsonObject().getAsJsonArray("cookies");
         }

         for (JsonElement var14 : var12) {
            if (var14.isJsonObject()) {
               JsonObject var15 = var14.getAsJsonObject();
               if (var15.has("name") && var15.has("value")) {
                  if (var15.has("expirationDate")) {
                     double var16 = var15.get("expirationDate").getAsDouble();
                     if (var16 > 0.0 && var16 < System.currentTimeMillis() / 1000.0) {
                        continue;
                     }
                  }

                  String var21 = "";
                  if (var15.has("domain")) {
                     var21 = var15.get("domain").getAsString();
                  } else if (var15.has("host")) {
                     var21 = var15.get("host").getAsString();
                  }

                  String var17 = var15.get("name").getAsString().trim();
                  String var18 = var15.get("value").getAsString().trim();
                  if (N(var21, -1817252485) && o( var17) && !var18.isEmpty()) {
                     i(var10, var17, var18);
                  }
               }
            }
         }
      } catch (Exception var19) {
         System.err.println("[CookieAuth] Failed to parse JSON cookies: " + var19.getMessage());
      }

      return var10;
   }

   private static String j(File var0) throws FileNotFoundException, IOException, Throwable {
      StringBuilder var3 = new StringBuilder();

      String var6;
      try (BufferedReader var4 = new BufferedReader(new InputStreamReader(new FileInputStream(var0), StandardCharsets.UTF_8))) {
         while ((var6 = var4.readLine()) != null) {
            var3.append(var6).append((char)10);
         }
      }

      return var3.toString();
   }

   private static boolean N(String var0, int var3) {
      long var4 = (133264245260288L | (long)var3 << 32 >>> 32) ^ a;
      if (var0 != null && !var0.isEmpty()) {
         var0 = var0.toLowerCase();
         return var0.contains("live.com") || var0.contains("microsoftonline.com") || var0.contains("microsoft.com") || var0.contains("xboxlive.com");
      } else {
         return true;
      }
   }

   private static String e(String var0, long var1) throws IOException, Exception, Throwable {



      JsonObject var6 = new JsonObject();
      JsonObject var7 = new JsonObject();
      JsonArray var8 = new JsonArray();
      var8.add(new JsonPrimitive(var0));
      var7.addProperty("SandboxId", "RETAIL");
      var7.add("UserTokens", var8);
      var6.add("Properties", var7);
      var6.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
      var6.addProperty("TokenType", "JWT");
      CloseableHttpClient var9 = Y(18702133247L, (byte)17);
      Throwable var10 = null;

      try {
         HttpPost var11 = new HttpPost(URI.create("https://xsts.auth.xboxlive.com/xsts/authorize"));
         var11.setConfig(K);
         var11.setHeader("Content-Type", "application/json");
         var11.setHeader("User-Agent", "Go-http-client/1.1");
         var11.setHeader("X-Xbl-Contract-Version", "0");
         var11.setEntity(new StringEntity(var6.toString(), StandardCharsets.UTF_8));
         CloseableHttpResponse var12 = var9.execute(var11);
         int var13 = var12.getStatusLine().getStatusCode();
         String var14 = EntityUtils.toString(var12.getEntity(), StandardCharsets.UTF_8);
         var12.close();
         if (var13 != 200) {
            throw new IOException("XSTS authentication failed (" + var13 + "): " + var14);
         } else {
            JsonObject var15 = new JsonParser().parse(var14).getAsJsonObject();
            if (var15.has("XErr")) {
               throw new IOException("XSTS error: " + var15.get("XErr").getAsString());
            } else {
               return var15.get("Token").getAsString();
            }
         }
      } catch (Throwable var25) {
         var10 = var25;
         throw var25;
      } finally {
         if (var9 != null) {
            if (var10 != null) {
               try {
                  var9.close();
               } catch (Throwable var24) {
                  var10.addSuppressed(var24);
               }
            } else {
               var9.close();
            }
         }
      }
   }

   public static MinecraftProfileResponse l(long var0, String var2) throws IOException, Exception, Throwable {




      AccountManagerScreen.q = new TimedStatusMessage(ChatFormatting.y("&7Fetching Minecraft profile..."), 5000L);
      CloseableHttpClient var9 = Y(18702133247L, (byte)17);
      Throwable var10 = null;

      try {
         HttpGet var11 = new HttpGet(URI.create("https://api.minecraftservices.com/minecraft/profile"));
         var11.setConfig(K);
         var11.setHeader("Authorization", "Bearer " + var2);
         var11.setHeader("Accept", "application/json");
         CloseableHttpResponse var12 = var9.execute(var11);
         int var13 = var12.getStatusLine().getStatusCode();
         String var14 = EntityUtils.toString(var12.getEntity(), StandardCharsets.UTF_8);
         var12.close();
         if (var13 != 200) {
            throw new IOException("Minecraft profile request failed (" + var13 + "): " + var14);
         } else {
            return (MinecraftProfileResponse)P.fromJson(var14, MinecraftProfileResponse.class);
         }
      } catch (Throwable var24) {
         var10 = var24;
         throw var24;
      } finally {
         if (var9 != null) {
            if (var10 != null) {
               try {
                  var9.close();
               } catch (Throwable var23) {
                  var10.addSuppressed(var23);
               }
            } else {
               var9.close();
            }
         }
      }
   }

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 12353;
      if (f[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = e[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])g.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               g.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/internal/auth/CookieAuthService", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         f[var3] = var15;
      }

      return f[var3];
   }

   private static String y(Map var2) throws Exception, Throwable {






      Exception var8 = null;
      ArrayList var9 = new ArrayList();
      var9.add(N);
      var9.add(X);

      for (List var11 : (Iterable<List>)(var9)) {
         String var12 = K(17874, (char)6104, (char)29767, var2, var11);
         if (!StringUtils.isBlank(var12)) {
            try {
               String var13 = b(97204582492950L, var12);
               if (var13 != null) {
                  return var13;
               }
            } catch (Exception var14) {
               var8 = var14;
            }
         }
      }

      if (var8 != null) {
         throw var8;
      } else {
         return null;
      }
   }

   private static String K(int var0, char var1, char var2, Map var3, List var4) {
      long var5 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      ArrayList var7 = new ArrayList(var4);

      for (String var9 : (Iterable<String>)(var3.keySet())) {
         if (!var7.contains(var9)) {
            var7.add(var9);
         }
      }

      StringBuilder var11 = new StringBuilder();

      for (String var10 : (Iterable<String>)(var7)) {
         if (var3.containsKey(var10)) {
            if (var11.length() > 0) {
               var11.append("; ");
            }

            var11.append(var10).append((char)61).append((String)var3.get(var10));
         }
      }

      return var11.toString();
   }

   private static boolean d(Map var0) {
      return var0.containsKey("__Host-MSAAUTH") || var0.containsKey("__Host-MSAAUTHP") || var0.containsKey("JSH") || var0.containsKey("JSHP");
   }

   private static String b(long var0, String var2) throws IOException, Exception, Throwable {



      CloseableHttpClient var10 = Y(18702133247L, (byte)17);
      Throwable var11 = null;

      try {
         String var12 = "https://login.live.com/oauth20_authorize.srf?redirect_uri=https://sisu.xboxlive.com/connect/oauth/XboxLive&response_type=token&client_id=000000004420578E&scope=XboxLive.Signin%20XboxLive.offline_access&prompt=none";
         int var13 = 0;

         while (var13 < 12) {
            HttpGet var14 = new HttpGet(URI.create(var12));
            var14.setConfig(K);
            var14.setHeader("Host", URI.create(var12).getHost());
            var14.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");
            var14.setHeader("Cookie", var2);
            var14.setHeader("Accept", "*/*");
            var14.setHeader("Accept-Language", "en-US,en;q=0.9");
            var14.setHeader("Connection", "keep-alive");
            CloseableHttpResponse var15 = var10.execute(var14);
            int var16 = var15.getStatusLine().getStatusCode();
            String var17 = var15.getFirstHeader("Location") != null ? var15.getFirstHeader("Location").getValue() : null;
            EntityUtils.consume(var15.getEntity());
            var15.close();
            if (var17 != null) {
               String var18 = H( var17);
               if (var18 != null) {
                  throw new IOException(var18);
               }

               String var19 = J( var17);
               if (var19 != null) {
                  return var19;
               }

               if (var16 == 302
                  || var16 == 303
                  || var16 == 301
                  || var16 == 307) {
                  var12 = z(var12, var17);
                  var13++;
                  continue;
               }
            }

            if (var16 == 200) {
               return null;
            }
            break;
         }
      } catch (Throwable var31) {
         var11 = var31;
         throw var31;
      } finally {
         if (var10 != null) {
            if (var11 != null) {
               try {
                  var10.close();
               } catch (Throwable var30) {
                  var11.addSuppressed(var30);
               }
            } else {
               var10.close();
            }
         }
      }

      return null;
   }

   static {
      a = 78731209098125L;
      zkm$clinit();
   }

   private static Map A(String var0) {



      LinkedHashMap var10 = new LinkedHashMap();

      for (String var14 : var0.split("\\r?\\n")) {
         var14 = var14.trim();
         if (!var14.isEmpty() && !var14.startsWith("#")) {
            String[] var15 = var14.split("\t", 7);
            if (var15.length >= 7) {
               String var16 = var15[0].trim().toLowerCase();
               String var17 = var15[5].trim();
               String var18 = var15[6].trim();
               if (N(var16, -1817252485) && o( var17) && !var18.isEmpty()) {
                  i(var10, var17, var18);
               }
            }
         }
      }

      return var10;
   }

   private static void zkm$clinit() {
      try {
         long var31 = a ^ 80069987178142L;
         d = new HashMap(13);
         Cipher var22;
         byte[] var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var23 = 1; var23 < 8; var23++) {
            var10003[var23] = (byte)(var31 << var23 * 8 >>> 56);
         }

         (var22 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var29 = new String[174];
         int var27 = 0;
         String var26 = "6¿=\u0003\u008dq©l\u009cÎæéüõö2»¯%\u0089ÐµkÖvÔaÈi\u0001Ü?\u0010\u0005é|Àí¶\u0085,\u0080à]#µ÷L÷(ÍÓ[l\u0080þmÏ\u0014ÂlÔÁ\u0000÷sh&>Z\u0094M;,\u0016[}]UV\u0017dD.\u0002§M\u0091¿CP3.\u0089Ï\u0089À&\u0003ü4tNPIx\f6¦ÖÂröµÞ:S@üy\u009d\u0002´-\bõëÏmQûí²Ï\u008eÍ6ËbP'+z\u0091ìõ¤ËóPëãÆèKN¦ \u009f*¶q\u0092H\fwMû\u000bX#\u0010\u00961(\u008c9L ÁeüÊ¸®\bg·@cÄ£\u0006b(qËIi½\u0003×\u0089IôÙ\u0090\u0003nZ±°\\\u0002çÆ¿e\u0014L\u0014ÄË\u0018¿´¨à¶6µÝ\u001c  Èâ¬ë\u0004,\u001bb@\u0019\u0085éá\u0012À-hV\u0010ý\u0085rµ\u0013V\u0099ý\u0091\u0000û\n\u007f\f&\u0006\u0010N\u0083\u000e$\u009eúg\u0095Â\u0092O+ý_ÏÞ(\rùo¹r\u0011ÿ\f,Q¿Î> Áí!g\\´\u0093º°E¡e\u008a\u008b\u008eeG=Ê\u0084Aw_\u00047~\u0010\u008a¿\u001eU\u0002îÕXÿOÂóõ\u0093Z\u008b@\u0086ÝàÅÛ\u0012U\"\u009b\u000eü \u0084©\u0002êç\u000e`\u009aHlá\u0002\u0018åÜq$eE\u00101í{jYâlh¶\u0090ç\u000et¬?òÊIÉ¯oñ\u0081w\u001ennK°Å\u001db0f\u0014\u000e\u0094©iøÄ5\u0010\u001bþÿ\u0081x&t\u0086ú\u001c¼o§Uiô¯E\u000bB)TÐðw\u000bô\u009c\u009e\u0080\nC7Î°\u0010Oå\u0010÷ÒÌ1\u00856\u0003\u0088¾\u0000-lHSÃq(?\u0087\u009bÀ\u0093\u0006\u0011ÐÝð\u00848rö\f!ë.~½Sû6ðnóLc\u0003+úÑ}°\u0003\b\u0094\u0085õ)(¾Þ\u008aªa\u0080ù7ÅBmR«\u0081\u0084ð\u008fb.E\u0018ÅE\u0013çÝ\u0005\u0011\u0095\u008f\u009d(Ç¹È^.\\ã\u0087\u0018ò\u009c\u0003ð¦2)i3oÝ¸\u0096\u00136y2Æø®\u0006AÚ@\u0010?%¨\u0007k\"\u008a\u008dÝ\u000eÅ\\&\n\u008e·\u0010ËU\u0019Xª<,Á\f·Ç\u009e\u0084²ËV\u0018\u001ar[»\r\u009c#$/SÉõ\u008f\f±<\u0003}²\u0084·m·\u000f\u0010ïÐB\u007féS\u0013²³c¯\u0096x~\u0098\bH\u0093êV«ô\u0003ÎÙËS¤®_·À\u0016å\u008fo R$\u0005Y\u001f\b\u0099v¹²^ÔÂ®\u0092ÔH\u001b6ôD»\u008e'\u0005³¡\u001aÅ\u001dûý\u001f&\u007f\u008eæ\u0006é\u008f£_ p¡ä\u001cbÈH±È\u0010\u0005õÃ\u0005AT£ù\u0098A\u0084¶ÔÔH\u001f\u0010\u0019.X·uÌInÕpÉg\u008e¿\u009eß\u0010¨ºË\u008dø\u000e\u0013ÀÆCÿ6]ñ\rö\u0010g\fg\u0084À°½?½\u008cö_ý\u0013Æï\u0010À{^\u009cã¢\u009e|Ù8®º\u0093m\u009f\u0018\u0010\u0016,C\u009fÑëå\u0001ÃØWËX\u0014|´ l»<>Eh\u008c:ü]\f\t_\u0011«\t<ÏXò\riêø\u009c+\u0084[ç-\u00ad\u0001 DP\u0087ÃdE\u001eUà=\u008d\u00ad>\u0015I¾\u0087J\u0011µê×Ä\u000fâ\u0002ô\t\tÔm\u0019\u0010¸.\u0099ÇÃ¤é_^ú¨å5o\u0087a\u0010|ª\u0005Ó-G'ÏeÃù\u0012\u0003²\u0098l\u0018}k$ëz\u008eIð\u00886\u000f(K§¼Ü\tÓn \u0017Òç\u00958\u0006;1ä\bL#y#ÛÐD\u0091sîÀ20¡\u00988\u001aÛºCâ&øó°°öm\u0090\u0089¦\u0006Ëë0¨]f($!\u0014;å¬Ë/\u007fNö]\u0018\u0004'Ø> WÙW|\u0093L÷çcY÷XÊ\u0019;ª:\u00ad¾\u0010j¿ÁGy\u0004_ñf\u008bh83b7.\u0018á`ï\u0013Ì½\u008c`\u0004â\u0000\roÒ\u000eVG½þLs78\u0001XsÈ\u0092c×\u0016zV=`¥zÊ\u009c\u000f¶ó ñ\u0086æ$Þ\f×Ç]]wX³Í=\u0013\u008e\u0000G3¨D¬6oÁÊ\u008eè^ñv:ÎÉxI_ø¦7\u0015\u0093!kW·ãíqàÛÏ¶¯\u0096íÿ\u0087èA¼\u008e8{\u001eÖXëþ@K$TÁ)\b\u0007\u0083\u009aÍ\u0013,\u0013\u0010\u001eC\u008fÔ¼ØR\u0096U\u008fHu\u009cÙÌ0\u0096\u0080+\u0096%RÈ¾T\u0007J\u0086Ê\u008a¼¿Íözçå\u0006\u0014ùllg*ð«\u0080øãå ©b& °àÅP¼=0Ä½\u0016\u0014ï$Þä\u00022K[ÏAÙiyyÅv\u0091(È\u0089ÖÔf\u0098Èo};!ñýX\u009b«Ú=[D×\u0019Ë>³ßÜ`C\u0000xåöfÐ\u0019)MÚ»8Ër¾Á ;\u0085X\u0093:ÂÙOMÄïB:´ùO\u008eU=;\u0099¬k¡\u0096C\u001cQâ\u008d»MãÛ\u0093\u009d\u0085\u001e\u0088,\fRæOY÷\u001aW*Ø\u0016 ÀtJÁõOl\u0017ÓáXpá\b¹z×'Z\f¤º\u008cj_°\u000e'Qa¦g r\\ ±û©-V\u001añí\u0094¦\t¸PÙþsûx\u0099p\u0012GÁÝ\u0018º\u0086\u008dñ\u0018\u0090\u0013GËzN\u00856\u0000·IßXåM\u000f\u0097ý\u0087\u001bÀ·Y¿\u0010Â\u001a\u0080®É\u0005¿gà\u0096»\u0095ð\u001f;h\u0010l\u0015(IM_¨È_|]öè®E\n\u0010 \t\u009eR\u001fXôÖå\u0004°-ñ\u001bÓB\u0018 ü5\u0012\u0092]V\u001cïD«?Êa\u00adv&J\u0088©B6ÜÊ\u0010Òg¢-q\r\u0004Þùv}ìê'®\u0098\u0010@þ^\u0013´:<ÃU2\\dÔãº\u0084 \u0000Ñ\u001cR#ZÄ§\u0082ÿOGYØà\u0019²A½Ò¡ÙN:Î#¾Y±\u0001U\u009cŐ1§ñ(°m\u0016\b\u0081&Û3_°ÚÒå\u0083Å¤B\u0091±\u00ad\u0080áeÍ×.\u001cäÂQ´\\L¯\u0095>¤®qÞ\u008f\u008bf+îer£\u000ez6\u0096\u0014\u009eÉ\u0099êd\u0087ªH\u0007\u001a\u0097\u0016yl\u000f%\u009d\u0011XtqYVv;<ÈZE.\u000b!\u0091.¢H\u001f%´»\u0014g>=y\u0084¡ø\u0086\u0085Ç\u001cÔåâÅÁ£'6èÛ;Cª¡\u001cþí+\u0001\u0099Nã\u0006Þ¿\u0002xî½\u0014#!/bw\nÅ\n\u0000V\r°FJ\u009dJE³7Ki\u0001AÀxòáïØ\u000bi|CíM\u0098ßQª\u009bÃÃ»\u009d[\u0086¨ùf×ñ\u0095\u001cr\u0084Ëû\u0092Ïìô{ÄúÎùÁ/E\u0092Ý\u0085â÷\u0007\u0090-ÀMN\u00ad·´ÿ\u009cb´.¾\u001eÜyºßßk\u0013Ãc9>Á*\\W«àÇ\u009c\u000fhH×å\u0001$~\u0098¨wIâ\u0014çgU¤`ð\u0092\u000344}O¾VÙh\u009bLG\u009f*?sq\u000f\\,K»\u000b~°ñ\u0006ÂÄÉ±ì¡ñ\u00adO_:\u0095=8Î\u000fà0²\u008bÓhOì\u0085\u00adéàøþK\u0096#U\u0005\u000f\u009aû4ý\u0018\u0013ã\fBpíÊbJ\u0000în\u009e²\u0099\u008c>:Q^\u0094ê9Ä\u0010ée±(_®6\u0086]»\u0086\u0002%Þ°®0'¢ÈRðª]\u0092`;yhëOÓ\u008fÔº~½}&\u000f°WÒ\u000fè¸¸ã\u0093\u001b\u000e±æº\u0080õ\u0013Õ\rdX;\u0089Ç\u0082\u0018O\u0002%½6K\u000f\u0092}Hu´æ\u00ad\u008d\u0091Ã¾ñõí\nQ·\u0010+\u009a¸\u000eõÛy\u009c,°î½\u008a¾\u008bi8½\u0091C2ßmï\u0018=iç)Þ\u0088g/\u008dN\u0002µ£D¢d#pû\u008eà[Í\u0011¦f\u009cbÆ9Ãµ\u0082GÔnC4Y%\u009c\u009cÆ\u0082Ãû<U\u0010»áïØá´\u0018ýñJD\u0097Ã\u001e/*0[ÿüjÑ\u0000ª\u0092\u0019¬\u000b®\u0090\u008aë®g%¤Ë\u0096\u0094\u0098ã\u0083\u001cÎÇa\u00023ÇYÉY\u0007µã¯\u0096°Ã\u009e-\u001f³\u0014É\u0010ð>ÄûÍ ßu\u0018\u0015 º¡\u0088£¥8<í\u001eÍVÃ¨QUFÝýô\u008f§\u001f\u0012Ó\u001e×3H\u0001\u009f\f,\u0087\u0013_T\u001a\u0088àd«fÂ\u0098\u0081\u0094`ÓI\b_8$VrÐÀ\u0085,¬\u0099¼ tª\u000b2\u009egî\u0013õ°Í#Û\u0093Øq\u001a×²\u009e\u0093óxò\u0090õÒ+|d+oPéþ\u0081ÙÍà\u001a(Cãq\u0016¢\u001d\u008c¯Â°X³tzK\u008d0\u0099\u009cl¹úGó\u0084öuº[D;hØr\u0004\bâ5\u0003\bs\u0093Mñç\u0005ó\bëtû .\u001a{§òei\u0092Ný\"\u007f3îÙ´»ú¡\u009d\u0010?ÜÑ\u0018¶\u0000Ì\u001cJ]Ù\u008få}-Ã8!ö³}\u0014RN©N$/§\u000b\u0092ãUõJwH\fb\u0096\"L\u0004\u0084]NJ¾\bTò8¼¬à\u0012b\u0010\u0097¬\u0086¢õ+,H§\u001e¥\r\u001dóÆŐ\u0001\u0098p?ù\u0010½\u0087\u0000è\u009dÒ\u0097Ûýzö\u009bsö¹\u001f¹e!\u0089´¢\u0092~w\u007fegôkRJâgK8nNÒ\n\u009cF¦Å3áº\u001d\u009e¼»¼=ãºÖi\u0011Ó\u0090¢¤,Â\u008c\u008a\u0080?6F\u0086Nê6Û\u0013 \u0096\u0005\u001a¢\tbWA¡¬\u000f\u0013\u00078>àÒ\u001b\fBâ)ý\u0087\u0019²A#ú¬\u0016\u0091ËgÉ\\·;»GT\u0000¨ÓªW\u0091\u0087\nÄê\u008cõ=4Õ\u001aâ)\u008a\u00018ª§Q_å1\u0004\u009cxÖ\u008b_(\u0005®Ã\u0097\u0086À[;\u009c\u001d\u0092æb\n\u0012Ø\b7md\u0014Foæ\t'\u0007\u0006j(¨Çi\tòoÖ{\u0095Ä\u008ct¬>c\u0005øA\u0088õÐÿ×e\u008e\u0094ÍÈÜt{È[8ãª»\u0011OD¹Dc^§Î¬äÙ\u001aÞâû\u009fÌu\u0087Â\u009bøÚ3\u001dcf,Ø«\u001d¢+öåô\u008cÖ\u0095¶\u0010\u0084 \u001b.\u0093fÆxõþ\u0015\u0087dpÃß\u007fÝ\u0096huyd¡Z\u001b¿nhW¥à®P\u0089\bxDEÓ\u0012\u0000Ñ§¹ÏÙ×î%É(á¤ý0ÌF\u0090;^\u0005Ò7äj<Z\b\u0010Bãb^ZaÍ\u008c\u0096v\u0000b£ZË)\u0010?¯<\u0004\u008a\u0085\u0091ÅA@j\"ß\u0097\u009eÑH¿ïH\u0096Ä\u001dõ6\u0018ê \u00ad\u0091Ø \u0082\u008a¶CXÔ \u008eá\u009aµ\\ëEy\u0007y»9â\f\u000b÷V¡SVcz\u0016ñËx©\u0084\u0091Æ2\u001eª\u0090´S%\u0094Óijª\u0093¼Ê\u001b\u0011)Zë@W¬\u0094[Ì\u0010\u0085St\u0012 \u0091ü)L¥\u0099\u0017\u0005o\"Ä\u0017\u000e;Ô\u0016\u000bñ5\u009cé¤»i[è1\u0015 `\u0006%ÊÆò®G\u0082ó¼G\u0084à·òí·X\u0089\u0089Þ\u009e]\u0010p\u0010a¿U\u0010m©\u0000fD\u0018\u009dícV \nIk9òy¿\u008eb\u0001\u000bÈ\u0095q¤#CfÉ\u0089Ð\u001e¢\u000f!Ø\u009c\u0097¯Í\"¥ Ò|$Ù`\u0007z <êÝ}øÀÏ¬½>öª¥AQ\u0005É\n¨Á\u0019dÔ<HàJåu\u0013zéÌW\u001dQßbòþ*6\u000fFÁ\u0099àõüÔºa.££\u0099äI½\u009d\u009drzü»\u008a\u0001\u0006K{áÕ\u0087\u0005êÌeú\u0089Òäj\u0095³÷\u0010\u0018·Q\u0091 \u0013!Ñ\u001e~Ê\u0010²â\u001b\u00817C\r\u0089=Í°C×T\u008fY\u0010;\u0093Ê3cÚ:£*ïòÇ\u0088¶ÇS\u0010Ô½\u0013þáäÝ\u0089µ5#;Äe,' ½Vúf(\u0085\u001e_LÇ\u008fÉÖÇ>\u0004\u0096\u00950pø±\u0081ñ§C\u008d2ÒÇÉ#\u0010\\\u0094\u0006\u0002ÉK²\u0094ZÀ;\u0098µ¥Óî kþ}uâ£\u0018Ü\u0013\u0007Lð)õ\u0004e\u0006Óùå\u008d¥\u0086\u0080Õ\tÁ\u001f\u0006út®\u0018\u008dþÐx%dK\u0085\u0096\u0002îºØ¯KÜ`\u001eJ\\\u0004\u0086ÐÆ\u0010æ\nP§w)£\u009aþ´£þ~ÁB&(\b4\u0090\u0007¦i\u0084\u009e'¨\u000f\u0094\u0095\u00802ç\\©\u009e{gpðïb\u000b÷ {Òco1£V\u0094\u0011PaE \u0015\u000f¡\u0013©uë.rCóyÁBr¶Ï¨\u0005C¼]\u001fö\u0094\u0080óý½\u0004ØÜ J³©à¶P\u0088\u008e?\u0010\u0019â>\u008dÈ\u0088\u0096\u0091\u0007y\u0097ª\u009ao\u007f×'²\u0096Ï¤\u0088\u0018ðÆ\u007fy·êp\u000bè\u0013¥äEi\u008cá@ª\"H½éø\u0091\u0010\u0016n\u0090+ny2[^÷\u0086c\u0004Ez\t\u0010¥ëJâiÀVsCõq§P?\u009dÏ\u0010Mâò\u0091\u000bçæÐÆ;Ï\u009d§E\u0083b\u0010Ä\u0000^\u0017\u0005\b\u000f=\u0016Ë\u009bØ\u009b\u007f#J \u0018\u00adÚ*d\u0081{ïÐ¾\\F\u0013ñ\u009e\u000b\u009d±C|\u00ad.\u001dôn'\u008bö«\u00ad¹JHä´\u0084\u0015G\\\u0089\u0097\u0090^\u0010-{°YE¡Ç\u008eõ*>\u0084n7/ª\u008eg\u0091^[\u009b\fJ÷ÞsRj\f¿ò\u0083¾Ñ\u009djq÷V{Ñ\u0084!Ð\bµ\u0084o´å\u00075<gQg?J].\u0010(dÂ\u0096\u0085²5º#[¢Rîxsd(åuÞ\u001ao\u0004'\u0080y±\u0098Î\u0006\u0013·¬IÇ1e\u0097\u0002Ãáóò\u0017\u0094¶xG.{ìWÐÍ$ö¹\u0010`\u000e\u0096&(\u001fÍà\u001f{×£\u001du\u001dPX\u008f\u0012ÿ4|Þ\u007fã-EAL\\\u0094¢Ó\u0092I³é\u009e\u0017_\t\u0091E\u009ckuDÁï(\f\u0082ãó9Èj'ÆB\u0003\u0001x¹ÊóZÔM\u0083%3ýzh\u0002[¾G\u008bô\u0019Ý\u00137Õ\u0096?9\\\u009b|,*¨iÑUyÃ¬\u0090\u000fLØ8É\u007fÖÒµÆÿÆøþ?\u009dA?ë\u0010ÈÄ\u0013\f\u0003\u0090¿<õx\u0005ì#ç©×HDDn\u0084Ä°wÍÏMÍú\u0012\u0089ÔÐ¤_\u0098½åãê@e\u008e\b³Çt\r;Cþ-+9d\u0082¹\u009c!ú£ú©\u0084\u0096L¬ \fV\u0013»SPÇ\u0084ûNaXh©\u0001u\u0011\u009bU!+H Wa:óôÒ0\u0014\u00830s§f\u001fXbw\u0006øcQ\u008c\u008dcX\u009biÄåÑÑB\t\u000e\u001fi~¥§µb\u0092\u0004hh\u008b´ú~}ãÖ\u0087\u0018)¥<*öhú\u0082\u00adgò7óî¯{>}\u008aP£Ï÷è¬\u0088\u009e\u0000R6¯\u0004z²®\u0012\u0089Å@bEüúÌ÷óø\u0011Ü8\u0010\t1´j1\u0014ú\t\u009e\u0006 Øá\u0005×:Ê:\u001fV¿vt\u008b\u0001W\u0013e«Èi\u0085¥D£µ³\u0002¥e²\u001d&£ït%ÃT\u0081s\u0006i*\u008c Pöæ[÷\r\u00972\u0098Ò\u009b1²A\u009cOèÎLd Ûiñ\u00adwÛ|3N«\u008b\u0010ð´¿ðÕ*9tø/Ëé\u008e2ÕÊ\u0010ÎØ¥p×¸í\u007f\u008c}(\u0006ñî\u00158 ºãûÏ£\u0006½ÅhÃõ&<NrÆI\"\u0006v·\u008d\u0004j÷b\u0003\u009coD\u009ak\u0010\u0012\u0081é\u001f\b_s#w\u000f`\u0090¿¹'cH$=ÓP\u00805äé*PK|ôN\u001dT÷½\u0014rµÜ¬6¯ìÚ¹H¼0\u008fêÁ\u001bgP¹ÌÆ&(4èÌÌWÙv]$\u00ado\u0011\u0016&ë.w}T¢~q*TW©¢_LÍ\u0010A.±\u009c\u0092FÍ/àò\u0014Cº@WC\u0010â®C¨âkÎ¬`ýX\u0082\u0091 \u008f*\u0010\u00adÃH3àp\fQ¤\u000f \f\u001f\f\u000f²\u0010Ð\u0088\u001f\u0007a\u001dÏ§\u001db\u009fÕÔY®L\u0010× \u008b\u0087ó}\u000bÿ7¤T\u001aµ\u0090Ùò@k§ÍRâXRú\u001eÄw\u0002\u000f\txuòKC°Â¬¿À\u0087´r^`\u0017±vvÔZ¤¹ñ\u008cIF\u0086\u009b\rðkCD\u009cwáÈçká?Ü\u0086R;4\b³`\u0010@à\u009aTïy^\u0090æòR\u0092!ÿã\u0017\u0010\u0005|\u00133|\r\u0090z\u008e9Ä/\t\u0093Â\u0099\u0010ªÇ\u008eµ¿Ýø¼óÕ\u009b6!\\6\u0003°þÀWâÚ¡}-+\u00191O\t\u0095³ \u009d·6ÜííB\u001b \n\u00adGS\u0085\u0010É\u001e@J\u008f^ÔFX©ãdÓUä5ë\u001eeí±Ó<©%ö¢.\t*ë\u001dµ\u0019UôÇP({9\u0098\u0013\u0003DzÏ/¢uÙÞàTõ´èû\f\u0094ûbû*IþÌ\u0016ã°ÅOõwá}ØK.:3N\u0087À\u0097\u0098£ûW\"\u0089£7}\u0082ôA\\Q¾(-íG,e\u0004fF·ªù:\u0086Ü\bî\u000eò\u0013gCZî\u0099\u0093\b\u001aAß\u0095\u001f\u0094à\u0017qe8u(ßt\u009c\u0081\f\u0010\r5Þw6-\u0085·\u001fÚ>±×\u008c!+ &ç¶H½\u0095ì Þ\u0001úd\u00871u4$NìN/\u0099ù\u009eö&Ê\u0003\u008fü#\u009b\u0018EF\u0012\u0099þ·.sAJé\n\u0011\u001c}£í\u0011ü1\u008e|(¿ +Ù\u0080\u001b\u0096ÀPp<f\u009dx¶/\u0004»Óåm¿\u008a\u00ad\u0082sÞ\u008a7bw¤p²\u0010è¢\u0016ÕÝ`\u0088Ò/\u0000º,ôøy\u0091(÷6#ÃñI?5;\u0081¤då\f\u000fbÆ°\u008cWeã|êÖÚ½\u00822øoº\rà°\u0090ª\u000b÷hX®È£\u0085TJÜ¦µ\u0014+~YÝ÷R\u00929.ÃÅ\u0000?\u0006Û\u0092plÐÛ¥ñ§=ª·<±Uµ\u000fÙP\u001c\u0093À\u0002þ\u009aóyù Ñ\u001bÞÚ·\r¡]\u009cæÅ\u0089õ6\u0095\u0004\u0085\u0081¼q\u0083Û\u0015C\t\u009fzº\u001dô³\u0092Ùå@8ýqIJ\u001bÚ\u0099|\u0085òJ¯ªèÐõ8\u0097k2Ø\u008fØm\u0019«Y\u009a\u0089\u0016\u009e\rà'Í½\u0013\u0016¦\u009a{¤J&R¨4\u000e\u0004\u0014_@<\tqS\u0010nó\u0001?\u009a\u0017\u0018DÍ\u0001\u0003V\bÌÿ\u009a\u0018Cñ¸ÔÆ3!\u000b4\u0092u6ðº\u001f2\u0014çº\u0085S\"%ð \u009a\u0090Õáûô\t^\u0018«.H\u007f¬ñi6\u007fzà\u008b±rFt«ÞXÉ\\Q» 9üÏ{\u0003m4:\u0088\u008d@znuR\u0092JH\u009d½Å\u00120â¨\u009c¤Ì\u0000æ=\r\u0010+ö`\u008cG.\u0014\u0018Í\u001bx\u009b\u0090lÏ¤\u0010:\u0019·{ÂåÑ\u008fà\nÃ\u008e»Ü\f¤\u0010yÚP\u00ad\u0080¢=\u0017ys\u0099yÛ\u0092Ä¯\u0010\u008cx\u0084ÑÃ\u001am\u009c\u0082I};/³\u001b\u009b\u0010îYe±\u0081\npH\u009e$\u00869\u0006ä\u0000¼pûMp\u0083Ôo\u000eQí%5P¹\u0093=c\u008d\u008a§:²rcd`~\u0007\u00151\n\u0095\u001b)±Âï \u009büæTT\u0003\u009d+ëf8+5\u0091`çê½\fÊ\u0001¼\u0088\u0005.\u008a\u000bÔ\u001eUï\u0084»²\u0002L¢\u0087¯;Y°FF±úâ\u0095£\u000fã\u0090[=Í§æ·\u0002'\u007fþ\u000f\nS\"\u0085PÜ_ Ô\u009cgà\u0010g=§ÈÑ\u0088h1Þ°®û=ò\u0011«\u0018ï4¤¯Û\u0087å4\bJX¨C\u0094l\u0091\u0094T?\u009c{ÕÄà\u0018'\u009dj_tBc\u0010¢\"®§ú¬óÑ,\u009cÒA=\u008d×a(·G\u001cdáN\u008b\u001aò'\u009fïæK¶ªªF.\u001bÑòSN;ºr\u0003=:Ï>\u0098\u0093ÿ±ß\u0096(z\u0010\u0086p+M\u0095'\u0090ÝTè\u0012\u008b%\tá\u0080\u0010\u0097\u0011¦B¨ú¿l¶IY\u0010'I£Û v\u000e\u00046É\u001a\u0004\b\u0012NÍÓ\u0098\u009b²\u008eÈHÖ<©{§V^\u0085êÛ¦DKm\u0010\u0085È\u0088Î\u0015\n °\u0014m\u009d\u0093å\u0090Ù>\u0018\u0001æ\u0087C\u000e¬ÑUÏ\n\u0085·gÔ®¬S\u0001\u001fà²£\u0014ê <¬\u008b%âQdT\u0000{rA©½`z\u0010sÉÜ$\u008eNÏÀäÒ×e\f?DPñHz>±Fn\u000b`\u0081_\u000f9m\u0096Éë¢\u0081L «&N1|\u0099\u008eúl\u0014=>k7»\t~\u007fâ<ÕP\u001d^í«ìVT²\u0080ß½b\"¬\u008dÊ\u0000óNJ5~oe{\u0086\u0006À¸QC\n,fLË¯\u0010\"ÛMfùÐÛ©x\u008a©+\u0005ý\u00ad\u001f@\u0015)MîZÔÖ~¡¹ñnU\u0090÷ÿ¿E½zo¹x\u0016A¦¥Îÿuô\u008f\u0013@M¸®ÏàxåTd\n\u0018îññ\u000eÒ=×g}höôÿâmE³\u0093\u0083\u0010F\f\\:\u000e ú%¤{\u0087ø8k\u0095¬\u0018²4\u0018Áä \u0010bN#\u0086rj\t\u0014\r³Þ¡s¸ÛØ|\u0010°\u0097P¤L\u0014í\u0014ÄxÊ¶,®8² e\u008b V)\u0099\u0099U$E\rø&+ \u008b\u008b\u0098õÒ^±Øð\u001dµ £íú\u0083ö\u0010µ±RL\u0007#Gé^Î¾ib\u009e?²\u0010\u007f¤\u008e\u0004¤pöè\u008fä\u0004x\u0001\u0088¯L\u0010;©\u009aó&\u0086¿\u0000\u0019TÂÔuÕA\u001d\u0010J\u0094q£7\u0005\u009aãí\u008aî\",)\u0099ý\u0010ú\u0088\u0001\nZµÐÂ\bSx\u000b-Ï\u0098O0\u0018T\u001c)1]\u008cÃ£\u0083ÄøóE\u0083Cò_\u0000ÃXR\u009f«\u0083\u0081Î0\u000bE \u0017Äa!=\u0013\n\u0081\u00190*É,\u008aÞÐ*\u0018\u0085èê\u000fh(³¨s\u0006\u00042ö´ï£u%\u0099{V+\"® \u009c6@Øö\"\u0017>Çh%ôQ·³$\u0007xZ}í\u0093?6.Ø¿={^\u0017s\u0010¢\tÀ\u008c!§OJ\u0086]?4+v[ú Ç<\u0099]\"õC*\u0091aa£\nÅ\u001c\u008fæ\u0087À=-^Èr\u0005#c6\u0091û¬\u0098\u0010÷,:a'ÕË¨@sÊ]/¼ù÷ ùèÄ¨ë\u0095vå\u0095Ç\u000b#òÙÅXi[\"ê7q¾\u0013ß\u009bh»ÂH\u0082\u0004 \u001f\u0097ã\u0001\u0007`|\u0014F\u0081\u0018B\u009fWÂ\u0018ÈøÚV\u0015\u0004\u0088\u0082÷/jûðÌ\u0015+\u0010\u0014\u0085\u0084¤hÒúx\u008dìtq\u001bµ\u0006Y\u0010¼\u0005Ä\u0093\u0097\u0096\u0095Ò\u0018B\u008f~5u}W@,¡êÞ\u001eVâ3f¶÷ñÐ\u0090Zóã\bH3=\u001fôÞ¹Såñ\u0097}\u0084õ\u0095\u0012\u0091\u009d6²\u0017ðº\u0000®+0QÑºW\u0005Ê\u000e¬ù +0o¤øÿìE½\u0010~\u0003ÇªeÃ\u009eä/\u00ad\u000f\u00133õR\b ô\u001eÚ^*[P'ùwn\u001c+²FÔmòh\u0092\u0097\u0097©$jßøZ\u000f^<à -à}:\u001dêÇ·o0ÎV\u0085@Æf\u008cáus\u001e\fÊç\u0005\u0093¬\u0007d>\u0093X\u0018\u0093õÛ3E¨ºÅ/ÇOå\u008e@rÿ>Àg°ß9g;";
         int var28 = "6¿=\u0003\u008dq©l\u009cÎæéüõö2»¯%\u0089ÐµkÖvÔaÈi\u0001Ü?\u0010\u0005é|Àí¶\u0085,\u0080à]#µ÷L÷(ÍÓ[l\u0080þmÏ\u0014ÂlÔÁ\u0000÷sh&>Z\u0094M;,\u0016[}]UV\u0017dD.\u0002§M\u0091¿CP3.\u0089Ï\u0089À&\u0003ü4tNPIx\f6¦ÖÂröµÞ:S@üy\u009d\u0002´-\bõëÏmQûí²Ï\u008eÍ6ËbP'+z\u0091ìõ¤ËóPëãÆèKN¦ \u009f*¶q\u0092H\fwMû\u000bX#\u0010\u00961(\u008c9L ÁeüÊ¸®\bg·@cÄ£\u0006b(qËIi½\u0003×\u0089IôÙ\u0090\u0003nZ±°\\\u0002çÆ¿e\u0014L\u0014ÄË\u0018¿´¨à¶6µÝ\u001c  Èâ¬ë\u0004,\u001bb@\u0019\u0085éá\u0012À-hV\u0010ý\u0085rµ\u0013V\u0099ý\u0091\u0000û\n\u007f\f&\u0006\u0010N\u0083\u000e$\u009eúg\u0095Â\u0092O+ý_ÏÞ(\rùo¹r\u0011ÿ\f,Q¿Î> Áí!g\\´\u0093º°E¡e\u008a\u008b\u008eeG=Ê\u0084Aw_\u00047~\u0010\u008a¿\u001eU\u0002îÕXÿOÂóõ\u0093Z\u008b@\u0086ÝàÅÛ\u0012U\"\u009b\u000eü \u0084©\u0002êç\u000e`\u009aHlá\u0002\u0018åÜq$eE\u00101í{jYâlh¶\u0090ç\u000et¬?òÊIÉ¯oñ\u0081w\u001ennK°Å\u001db0f\u0014\u000e\u0094©iøÄ5\u0010\u001bþÿ\u0081x&t\u0086ú\u001c¼o§Uiô¯E\u000bB)TÐðw\u000bô\u009c\u009e\u0080\nC7Î°\u0010Oå\u0010÷ÒÌ1\u00856\u0003\u0088¾\u0000-lHSÃq(?\u0087\u009bÀ\u0093\u0006\u0011ÐÝð\u00848rö\f!ë.~½Sû6ðnóLc\u0003+úÑ}°\u0003\b\u0094\u0085õ)(¾Þ\u008aªa\u0080ù7ÅBmR«\u0081\u0084ð\u008fb.E\u0018ÅE\u0013çÝ\u0005\u0011\u0095\u008f\u009d(Ç¹È^.\\ã\u0087\u0018ò\u009c\u0003ð¦2)i3oÝ¸\u0096\u00136y2Æø®\u0006AÚ@\u0010?%¨\u0007k\"\u008a\u008dÝ\u000eÅ\\&\n\u008e·\u0010ËU\u0019Xª<,Á\f·Ç\u009e\u0084²ËV\u0018\u001ar[»\r\u009c#$/SÉõ\u008f\f±<\u0003}²\u0084·m·\u000f\u0010ïÐB\u007féS\u0013²³c¯\u0096x~\u0098\bH\u0093êV«ô\u0003ÎÙËS¤®_·À\u0016å\u008fo R$\u0005Y\u001f\b\u0099v¹²^ÔÂ®\u0092ÔH\u001b6ôD»\u008e'\u0005³¡\u001aÅ\u001dûý\u001f&\u007f\u008eæ\u0006é\u008f£_ p¡ä\u001cbÈH±È\u0010\u0005õÃ\u0005AT£ù\u0098A\u0084¶ÔÔH\u001f\u0010\u0019.X·uÌInÕpÉg\u008e¿\u009eß\u0010¨ºË\u008dø\u000e\u0013ÀÆCÿ6]ñ\rö\u0010g\fg\u0084À°½?½\u008cö_ý\u0013Æï\u0010À{^\u009cã¢\u009e|Ù8®º\u0093m\u009f\u0018\u0010\u0016,C\u009fÑëå\u0001ÃØWËX\u0014|´ l»<>Eh\u008c:ü]\f\t_\u0011«\t<ÏXò\riêø\u009c+\u0084[ç-\u00ad\u0001 DP\u0087ÃdE\u001eUà=\u008d\u00ad>\u0015I¾\u0087J\u0011µê×Ä\u000fâ\u0002ô\t\tÔm\u0019\u0010¸.\u0099ÇÃ¤é_^ú¨å5o\u0087a\u0010|ª\u0005Ó-G'ÏeÃù\u0012\u0003²\u0098l\u0018}k$ëz\u008eIð\u00886\u000f(K§¼Ü\tÓn \u0017Òç\u00958\u0006;1ä\bL#y#ÛÐD\u0091sîÀ20¡\u00988\u001aÛºCâ&øó°°öm\u0090\u0089¦\u0006Ëë0¨]f($!\u0014;å¬Ë/\u007fNö]\u0018\u0004'Ø> WÙW|\u0093L÷çcY÷XÊ\u0019;ª:\u00ad¾\u0010j¿ÁGy\u0004_ñf\u008bh83b7.\u0018á`ï\u0013Ì½\u008c`\u0004â\u0000\roÒ\u000eVG½þLs78\u0001XsÈ\u0092c×\u0016zV=`¥zÊ\u009c\u000f¶ó ñ\u0086æ$Þ\f×Ç]]wX³Í=\u0013\u008e\u0000G3¨D¬6oÁÊ\u008eè^ñv:ÎÉxI_ø¦7\u0015\u0093!kW·ãíqàÛÏ¶¯\u0096íÿ\u0087èA¼\u008e8{\u001eÖXëþ@K$TÁ)\b\u0007\u0083\u009aÍ\u0013,\u0013\u0010\u001eC\u008fÔ¼ØR\u0096U\u008fHu\u009cÙÌ0\u0096\u0080+\u0096%RÈ¾T\u0007J\u0086Ê\u008a¼¿Íözçå\u0006\u0014ùllg*ð«\u0080øãå ©b& °àÅP¼=0Ä½\u0016\u0014ï$Þä\u00022K[ÏAÙiyyÅv\u0091(È\u0089ÖÔf\u0098Èo};!ñýX\u009b«Ú=[D×\u0019Ë>³ßÜ`C\u0000xåöfÐ\u0019)MÚ»8Ër¾Á ;\u0085X\u0093:ÂÙOMÄïB:´ùO\u008eU=;\u0099¬k¡\u0096C\u001cQâ\u008d»MãÛ\u0093\u009d\u0085\u001e\u0088,\fRæOY÷\u001aW*Ø\u0016 ÀtJÁõOl\u0017ÓáXpá\b¹z×'Z\f¤º\u008cj_°\u000e'Qa¦g r\\ ±û©-V\u001añí\u0094¦\t¸PÙþsûx\u0099p\u0012GÁÝ\u0018º\u0086\u008dñ\u0018\u0090\u0013GËzN\u00856\u0000·IßXåM\u000f\u0097ý\u0087\u001bÀ·Y¿\u0010Â\u001a\u0080®É\u0005¿gà\u0096»\u0095ð\u001f;h\u0010l\u0015(IM_¨È_|]öè®E\n\u0010 \t\u009eR\u001fXôÖå\u0004°-ñ\u001bÓB\u0018 ü5\u0012\u0092]V\u001cïD«?Êa\u00adv&J\u0088©B6ÜÊ\u0010Òg¢-q\r\u0004Þùv}ìê'®\u0098\u0010@þ^\u0013´:<ÃU2\\dÔãº\u0084 \u0000Ñ\u001cR#ZÄ§\u0082ÿOGYØà\u0019²A½Ò¡ÙN:Î#¾Y±\u0001U\u009cŐ1§ñ(°m\u0016\b\u0081&Û3_°ÚÒå\u0083Å¤B\u0091±\u00ad\u0080áeÍ×.\u001cäÂQ´\\L¯\u0095>¤®qÞ\u008f\u008bf+îer£\u000ez6\u0096\u0014\u009eÉ\u0099êd\u0087ªH\u0007\u001a\u0097\u0016yl\u000f%\u009d\u0011XtqYVv;<ÈZE.\u000b!\u0091.¢H\u001f%´»\u0014g>=y\u0084¡ø\u0086\u0085Ç\u001cÔåâÅÁ£'6èÛ;Cª¡\u001cþí+\u0001\u0099Nã\u0006Þ¿\u0002xî½\u0014#!/bw\nÅ\n\u0000V\r°FJ\u009dJE³7Ki\u0001AÀxòáïØ\u000bi|CíM\u0098ßQª\u009bÃÃ»\u009d[\u0086¨ùf×ñ\u0095\u001cr\u0084Ëû\u0092Ïìô{ÄúÎùÁ/E\u0092Ý\u0085â÷\u0007\u0090-ÀMN\u00ad·´ÿ\u009cb´.¾\u001eÜyºßßk\u0013Ãc9>Á*\\W«àÇ\u009c\u000fhH×å\u0001$~\u0098¨wIâ\u0014çgU¤`ð\u0092\u000344}O¾VÙh\u009bLG\u009f*?sq\u000f\\,K»\u000b~°ñ\u0006ÂÄÉ±ì¡ñ\u00adO_:\u0095=8Î\u000fà0²\u008bÓhOì\u0085\u00adéàøþK\u0096#U\u0005\u000f\u009aû4ý\u0018\u0013ã\fBpíÊbJ\u0000în\u009e²\u0099\u008c>:Q^\u0094ê9Ä\u0010ée±(_®6\u0086]»\u0086\u0002%Þ°®0'¢ÈRðª]\u0092`;yhëOÓ\u008fÔº~½}&\u000f°WÒ\u000fè¸¸ã\u0093\u001b\u000e±æº\u0080õ\u0013Õ\rdX;\u0089Ç\u0082\u0018O\u0002%½6K\u000f\u0092}Hu´æ\u00ad\u008d\u0091Ã¾ñõí\nQ·\u0010+\u009a¸\u000eõÛy\u009c,°î½\u008a¾\u008bi8½\u0091C2ßmï\u0018=iç)Þ\u0088g/\u008dN\u0002µ£D¢d#pû\u008eà[Í\u0011¦f\u009cbÆ9Ãµ\u0082GÔnC4Y%\u009c\u009cÆ\u0082Ãû<U\u0010»áïØá´\u0018ýñJD\u0097Ã\u001e/*0[ÿüjÑ\u0000ª\u0092\u0019¬\u000b®\u0090\u008aë®g%¤Ë\u0096\u0094\u0098ã\u0083\u001cÎÇa\u00023ÇYÉY\u0007µã¯\u0096°Ã\u009e-\u001f³\u0014É\u0010ð>ÄûÍ ßu\u0018\u0015 º¡\u0088£¥8<í\u001eÍVÃ¨QUFÝýô\u008f§\u001f\u0012Ó\u001e×3H\u0001\u009f\f,\u0087\u0013_T\u001a\u0088àd«fÂ\u0098\u0081\u0094`ÓI\b_8$VrÐÀ\u0085,¬\u0099¼ tª\u000b2\u009egî\u0013õ°Í#Û\u0093Øq\u001a×²\u009e\u0093óxò\u0090õÒ+|d+oPéþ\u0081ÙÍà\u001a(Cãq\u0016¢\u001d\u008c¯Â°X³tzK\u008d0\u0099\u009cl¹úGó\u0084öuº[D;hØr\u0004\bâ5\u0003\bs\u0093Mñç\u0005ó\bëtû .\u001a{§òei\u0092Ný\"\u007f3îÙ´»ú¡\u009d\u0010?ÜÑ\u0018¶\u0000Ì\u001cJ]Ù\u008få}-Ã8!ö³}\u0014RN©N$/§\u000b\u0092ãUõJwH\fb\u0096\"L\u0004\u0084]NJ¾\bTò8¼¬à\u0012b\u0010\u0097¬\u0086¢õ+,H§\u001e¥\r\u001dóÆŐ\u0001\u0098p?ù\u0010½\u0087\u0000è\u009dÒ\u0097Ûýzö\u009bsö¹\u001f¹e!\u0089´¢\u0092~w\u007fegôkRJâgK8nNÒ\n\u009cF¦Å3áº\u001d\u009e¼»¼=ãºÖi\u0011Ó\u0090¢¤,Â\u008c\u008a\u0080?6F\u0086Nê6Û\u0013 \u0096\u0005\u001a¢\tbWA¡¬\u000f\u0013\u00078>àÒ\u001b\fBâ)ý\u0087\u0019²A#ú¬\u0016\u0091ËgÉ\\·;»GT\u0000¨ÓªW\u0091\u0087\nÄê\u008cõ=4Õ\u001aâ)\u008a\u00018ª§Q_å1\u0004\u009cxÖ\u008b_(\u0005®Ã\u0097\u0086À[;\u009c\u001d\u0092æb\n\u0012Ø\b7md\u0014Foæ\t'\u0007\u0006j(¨Çi\tòoÖ{\u0095Ä\u008ct¬>c\u0005øA\u0088õÐÿ×e\u008e\u0094ÍÈÜt{È[8ãª»\u0011OD¹Dc^§Î¬äÙ\u001aÞâû\u009fÌu\u0087Â\u009bøÚ3\u001dcf,Ø«\u001d¢+öåô\u008cÖ\u0095¶\u0010\u0084 \u001b.\u0093fÆxõþ\u0015\u0087dpÃß\u007fÝ\u0096huyd¡Z\u001b¿nhW¥à®P\u0089\bxDEÓ\u0012\u0000Ñ§¹ÏÙ×î%É(á¤ý0ÌF\u0090;^\u0005Ò7äj<Z\b\u0010Bãb^ZaÍ\u008c\u0096v\u0000b£ZË)\u0010?¯<\u0004\u008a\u0085\u0091ÅA@j\"ß\u0097\u009eÑH¿ïH\u0096Ä\u001dõ6\u0018ê \u00ad\u0091Ø \u0082\u008a¶CXÔ \u008eá\u009aµ\\ëEy\u0007y»9â\f\u000b÷V¡SVcz\u0016ñËx©\u0084\u0091Æ2\u001eª\u0090´S%\u0094Óijª\u0093¼Ê\u001b\u0011)Zë@W¬\u0094[Ì\u0010\u0085St\u0012 \u0091ü)L¥\u0099\u0017\u0005o\"Ä\u0017\u000e;Ô\u0016\u000bñ5\u009cé¤»i[è1\u0015 `\u0006%ÊÆò®G\u0082ó¼G\u0084à·òí·X\u0089\u0089Þ\u009e]\u0010p\u0010a¿U\u0010m©\u0000fD\u0018\u009dícV \nIk9òy¿\u008eb\u0001\u000bÈ\u0095q¤#CfÉ\u0089Ð\u001e¢\u000f!Ø\u009c\u0097¯Í\"¥ Ò|$Ù`\u0007z <êÝ}øÀÏ¬½>öª¥AQ\u0005É\n¨Á\u0019dÔ<HàJåu\u0013zéÌW\u001dQßbòþ*6\u000fFÁ\u0099àõüÔºa.££\u0099äI½\u009d\u009drzü»\u008a\u0001\u0006K{áÕ\u0087\u0005êÌeú\u0089Òäj\u0095³÷\u0010\u0018·Q\u0091 \u0013!Ñ\u001e~Ê\u0010²â\u001b\u00817C\r\u0089=Í°C×T\u008fY\u0010;\u0093Ê3cÚ:£*ïòÇ\u0088¶ÇS\u0010Ô½\u0013þáäÝ\u0089µ5#;Äe,' ½Vúf(\u0085\u001e_LÇ\u008fÉÖÇ>\u0004\u0096\u00950pø±\u0081ñ§C\u008d2ÒÇÉ#\u0010\\\u0094\u0006\u0002ÉK²\u0094ZÀ;\u0098µ¥Óî kþ}uâ£\u0018Ü\u0013\u0007Lð)õ\u0004e\u0006Óùå\u008d¥\u0086\u0080Õ\tÁ\u001f\u0006út®\u0018\u008dþÐx%dK\u0085\u0096\u0002îºØ¯KÜ`\u001eJ\\\u0004\u0086ÐÆ\u0010æ\nP§w)£\u009aþ´£þ~ÁB&(\b4\u0090\u0007¦i\u0084\u009e'¨\u000f\u0094\u0095\u00802ç\\©\u009e{gpðïb\u000b÷ {Òco1£V\u0094\u0011PaE \u0015\u000f¡\u0013©uë.rCóyÁBr¶Ï¨\u0005C¼]\u001fö\u0094\u0080óý½\u0004ØÜ J³©à¶P\u0088\u008e?\u0010\u0019â>\u008dÈ\u0088\u0096\u0091\u0007y\u0097ª\u009ao\u007f×'²\u0096Ï¤\u0088\u0018ðÆ\u007fy·êp\u000bè\u0013¥äEi\u008cá@ª\"H½éø\u0091\u0010\u0016n\u0090+ny2[^÷\u0086c\u0004Ez\t\u0010¥ëJâiÀVsCõq§P?\u009dÏ\u0010Mâò\u0091\u000bçæÐÆ;Ï\u009d§E\u0083b\u0010Ä\u0000^\u0017\u0005\b\u000f=\u0016Ë\u009bØ\u009b\u007f#J \u0018\u00adÚ*d\u0081{ïÐ¾\\F\u0013ñ\u009e\u000b\u009d±C|\u00ad.\u001dôn'\u008bö«\u00ad¹JHä´\u0084\u0015G\\\u0089\u0097\u0090^\u0010-{°YE¡Ç\u008eõ*>\u0084n7/ª\u008eg\u0091^[\u009b\fJ÷ÞsRj\f¿ò\u0083¾Ñ\u009djq÷V{Ñ\u0084!Ð\bµ\u0084o´å\u00075<gQg?J].\u0010(dÂ\u0096\u0085²5º#[¢Rîxsd(åuÞ\u001ao\u0004'\u0080y±\u0098Î\u0006\u0013·¬IÇ1e\u0097\u0002Ãáóò\u0017\u0094¶xG.{ìWÐÍ$ö¹\u0010`\u000e\u0096&(\u001fÍà\u001f{×£\u001du\u001dPX\u008f\u0012ÿ4|Þ\u007fã-EAL\\\u0094¢Ó\u0092I³é\u009e\u0017_\t\u0091E\u009ckuDÁï(\f\u0082ãó9Èj'ÆB\u0003\u0001x¹ÊóZÔM\u0083%3ýzh\u0002[¾G\u008bô\u0019Ý\u00137Õ\u0096?9\\\u009b|,*¨iÑUyÃ¬\u0090\u000fLØ8É\u007fÖÒµÆÿÆøþ?\u009dA?ë\u0010ÈÄ\u0013\f\u0003\u0090¿<õx\u0005ì#ç©×HDDn\u0084Ä°wÍÏMÍú\u0012\u0089ÔÐ¤_\u0098½åãê@e\u008e\b³Çt\r;Cþ-+9d\u0082¹\u009c!ú£ú©\u0084\u0096L¬ \fV\u0013»SPÇ\u0084ûNaXh©\u0001u\u0011\u009bU!+H Wa:óôÒ0\u0014\u00830s§f\u001fXbw\u0006øcQ\u008c\u008dcX\u009biÄåÑÑB\t\u000e\u001fi~¥§µb\u0092\u0004hh\u008b´ú~}ãÖ\u0087\u0018)¥<*öhú\u0082\u00adgò7óî¯{>}\u008aP£Ï÷è¬\u0088\u009e\u0000R6¯\u0004z²®\u0012\u0089Å@bEüúÌ÷óø\u0011Ü8\u0010\t1´j1\u0014ú\t\u009e\u0006 Øá\u0005×:Ê:\u001fV¿vt\u008b\u0001W\u0013e«Èi\u0085¥D£µ³\u0002¥e²\u001d&£ït%ÃT\u0081s\u0006i*\u008c Pöæ[÷\r\u00972\u0098Ò\u009b1²A\u009cOèÎLd Ûiñ\u00adwÛ|3N«\u008b\u0010ð´¿ðÕ*9tø/Ëé\u008e2ÕÊ\u0010ÎØ¥p×¸í\u007f\u008c}(\u0006ñî\u00158 ºãûÏ£\u0006½ÅhÃõ&<NrÆI\"\u0006v·\u008d\u0004j÷b\u0003\u009coD\u009ak\u0010\u0012\u0081é\u001f\b_s#w\u000f`\u0090¿¹'cH$=ÓP\u00805äé*PK|ôN\u001dT÷½\u0014rµÜ¬6¯ìÚ¹H¼0\u008fêÁ\u001bgP¹ÌÆ&(4èÌÌWÙv]$\u00ado\u0011\u0016&ë.w}T¢~q*TW©¢_LÍ\u0010A.±\u009c\u0092FÍ/àò\u0014Cº@WC\u0010â®C¨âkÎ¬`ýX\u0082\u0091 \u008f*\u0010\u00adÃH3àp\fQ¤\u000f \f\u001f\f\u000f²\u0010Ð\u0088\u001f\u0007a\u001dÏ§\u001db\u009fÕÔY®L\u0010× \u008b\u0087ó}\u000bÿ7¤T\u001aµ\u0090Ùò@k§ÍRâXRú\u001eÄw\u0002\u000f\txuòKC°Â¬¿À\u0087´r^`\u0017±vvÔZ¤¹ñ\u008cIF\u0086\u009b\rðkCD\u009cwáÈçká?Ü\u0086R;4\b³`\u0010@à\u009aTïy^\u0090æòR\u0092!ÿã\u0017\u0010\u0005|\u00133|\r\u0090z\u008e9Ä/\t\u0093Â\u0099\u0010ªÇ\u008eµ¿Ýø¼óÕ\u009b6!\\6\u0003°þÀWâÚ¡}-+\u00191O\t\u0095³ \u009d·6ÜííB\u001b \n\u00adGS\u0085\u0010É\u001e@J\u008f^ÔFX©ãdÓUä5ë\u001eeí±Ó<©%ö¢.\t*ë\u001dµ\u0019UôÇP({9\u0098\u0013\u0003DzÏ/¢uÙÞàTõ´èû\f\u0094ûbû*IþÌ\u0016ã°ÅOõwá}ØK.:3N\u0087À\u0097\u0098£ûW\"\u0089£7}\u0082ôA\\Q¾(-íG,e\u0004fF·ªù:\u0086Ü\bî\u000eò\u0013gCZî\u0099\u0093\b\u001aAß\u0095\u001f\u0094à\u0017qe8u(ßt\u009c\u0081\f\u0010\r5Þw6-\u0085·\u001fÚ>±×\u008c!+ &ç¶H½\u0095ì Þ\u0001úd\u00871u4$NìN/\u0099ù\u009eö&Ê\u0003\u008fü#\u009b\u0018EF\u0012\u0099þ·.sAJé\n\u0011\u001c}£í\u0011ü1\u008e|(¿ +Ù\u0080\u001b\u0096ÀPp<f\u009dx¶/\u0004»Óåm¿\u008a\u00ad\u0082sÞ\u008a7bw¤p²\u0010è¢\u0016ÕÝ`\u0088Ò/\u0000º,ôøy\u0091(÷6#ÃñI?5;\u0081¤då\f\u000fbÆ°\u008cWeã|êÖÚ½\u00822øoº\rà°\u0090ª\u000b÷hX®È£\u0085TJÜ¦µ\u0014+~YÝ÷R\u00929.ÃÅ\u0000?\u0006Û\u0092plÐÛ¥ñ§=ª·<±Uµ\u000fÙP\u001c\u0093À\u0002þ\u009aóyù Ñ\u001bÞÚ·\r¡]\u009cæÅ\u0089õ6\u0095\u0004\u0085\u0081¼q\u0083Û\u0015C\t\u009fzº\u001dô³\u0092Ùå@8ýqIJ\u001bÚ\u0099|\u0085òJ¯ªèÐõ8\u0097k2Ø\u008fØm\u0019«Y\u009a\u0089\u0016\u009e\rà'Í½\u0013\u0016¦\u009a{¤J&R¨4\u000e\u0004\u0014_@<\tqS\u0010nó\u0001?\u009a\u0017\u0018DÍ\u0001\u0003V\bÌÿ\u009a\u0018Cñ¸ÔÆ3!\u000b4\u0092u6ðº\u001f2\u0014çº\u0085S\"%ð \u009a\u0090Õáûô\t^\u0018«.H\u007f¬ñi6\u007fzà\u008b±rFt«ÞXÉ\\Q» 9üÏ{\u0003m4:\u0088\u008d@znuR\u0092JH\u009d½Å\u00120â¨\u009c¤Ì\u0000æ=\r\u0010+ö`\u008cG.\u0014\u0018Í\u001bx\u009b\u0090lÏ¤\u0010:\u0019·{ÂåÑ\u008fà\nÃ\u008e»Ü\f¤\u0010yÚP\u00ad\u0080¢=\u0017ys\u0099yÛ\u0092Ä¯\u0010\u008cx\u0084ÑÃ\u001am\u009c\u0082I};/³\u001b\u009b\u0010îYe±\u0081\npH\u009e$\u00869\u0006ä\u0000¼pûMp\u0083Ôo\u000eQí%5P¹\u0093=c\u008d\u008a§:²rcd`~\u0007\u00151\n\u0095\u001b)±Âï \u009büæTT\u0003\u009d+ëf8+5\u0091`çê½\fÊ\u0001¼\u0088\u0005.\u008a\u000bÔ\u001eUï\u0084»²\u0002L¢\u0087¯;Y°FF±úâ\u0095£\u000fã\u0090[=Í§æ·\u0002'\u007fþ\u000f\nS\"\u0085PÜ_ Ô\u009cgà\u0010g=§ÈÑ\u0088h1Þ°®û=ò\u0011«\u0018ï4¤¯Û\u0087å4\bJX¨C\u0094l\u0091\u0094T?\u009c{ÕÄà\u0018'\u009dj_tBc\u0010¢\"®§ú¬óÑ,\u009cÒA=\u008d×a(·G\u001cdáN\u008b\u001aò'\u009fïæK¶ªªF.\u001bÑòSN;ºr\u0003=:Ï>\u0098\u0093ÿ±ß\u0096(z\u0010\u0086p+M\u0095'\u0090ÝTè\u0012\u008b%\tá\u0080\u0010\u0097\u0011¦B¨ú¿l¶IY\u0010'I£Û v\u000e\u00046É\u001a\u0004\b\u0012NÍÓ\u0098\u009b²\u008eÈHÖ<©{§V^\u0085êÛ¦DKm\u0010\u0085È\u0088Î\u0015\n °\u0014m\u009d\u0093å\u0090Ù>\u0018\u0001æ\u0087C\u000e¬ÑUÏ\n\u0085·gÔ®¬S\u0001\u001fà²£\u0014ê <¬\u008b%âQdT\u0000{rA©½`z\u0010sÉÜ$\u008eNÏÀäÒ×e\f?DPñHz>±Fn\u000b`\u0081_\u000f9m\u0096Éë¢\u0081L «&N1|\u0099\u008eúl\u0014=>k7»\t~\u007fâ<ÕP\u001d^í«ìVT²\u0080ß½b\"¬\u008dÊ\u0000óNJ5~oe{\u0086\u0006À¸QC\n,fLË¯\u0010\"ÛMfùÐÛ©x\u008a©+\u0005ý\u00ad\u001f@\u0015)MîZÔÖ~¡¹ñnU\u0090÷ÿ¿E½zo¹x\u0016A¦¥Îÿuô\u008f\u0013@M¸®ÏàxåTd\n\u0018îññ\u000eÒ=×g}höôÿâmE³\u0093\u0083\u0010F\f\\:\u000e ú%¤{\u0087ø8k\u0095¬\u0018²4\u0018Áä \u0010bN#\u0086rj\t\u0014\r³Þ¡s¸ÛØ|\u0010°\u0097P¤L\u0014í\u0014ÄxÊ¶,®8² e\u008b V)\u0099\u0099U$E\rø&+ \u008b\u008b\u0098õÒ^±Øð\u001dµ £íú\u0083ö\u0010µ±RL\u0007#Gé^Î¾ib\u009e?²\u0010\u007f¤\u008e\u0004¤pöè\u008fä\u0004x\u0001\u0088¯L\u0010;©\u009aó&\u0086¿\u0000\u0019TÂÔuÕA\u001d\u0010J\u0094q£7\u0005\u009aãí\u008aî\",)\u0099ý\u0010ú\u0088\u0001\nZµÐÂ\bSx\u000b-Ï\u0098O0\u0018T\u001c)1]\u008cÃ£\u0083ÄøóE\u0083Cò_\u0000ÃXR\u009f«\u0083\u0081Î0\u000bE \u0017Äa!=\u0013\n\u0081\u00190*É,\u008aÞÐ*\u0018\u0085èê\u000fh(³¨s\u0006\u00042ö´ï£u%\u0099{V+\"® \u009c6@Øö\"\u0017>Çh%ôQ·³$\u0007xZ}í\u0093?6.Ø¿={^\u0017s\u0010¢\tÀ\u008c!§OJ\u0086]?4+v[ú Ç<\u0099]\"õC*\u0091aa£\nÅ\u001c\u008fæ\u0087À=-^Èr\u0005#c6\u0091û¬\u0098\u0010÷,:a'ÕË¨@sÊ]/¼ù÷ ùèÄ¨ë\u0095vå\u0095Ç\u000b#òÙÅXi[\"ê7q¾\u0013ß\u009bh»ÂH\u0082\u0004 \u001f\u0097ã\u0001\u0007`|\u0014F\u0081\u0018B\u009fWÂ\u0018ÈøÚV\u0015\u0004\u0088\u0082÷/jûðÌ\u0015+\u0010\u0014\u0085\u0084¤hÒúx\u008dìtq\u001bµ\u0006Y\u0010¼\u0005Ä\u0093\u0097\u0096\u0095Ò\u0018B\u008f~5u}W@,¡êÞ\u001eVâ3f¶÷ñÐ\u0090Zóã\bH3=\u001fôÞ¹Såñ\u0097}\u0084õ\u0095\u0012\u0091\u009d6²\u0017ðº\u0000®+0QÑºW\u0005Ê\u000e¬ù +0o¤øÿìE½\u0010~\u0003ÇªeÃ\u009eä/\u00ad\u000f\u00133õR\b ô\u001eÚ^*[P'ùwn\u001c+²FÔmòh\u0092\u0097\u0097©$jßøZ\u000f^<à -à}:\u001dêÇ·o0ÎV\u0085@Æf\u008cáus\u001e\fÊç\u0005\u0093¬\u0007d>\u0093X\u0018\u0093õÛ3E¨ºÅ/ÇOå\u008e@rÿ>Àg°ß9g;"
            .length();
         char var25 = ' ';
         int var36 = -1;

         label77:
         while (true) {
            String var37 = var26.substring(++var36, var36 + var25);
            int var10001 = -1;

            while (true) {
               byte[] var30 = var22.doFinal(var37.getBytes("ISO-8859-1"));
               String var53 = a(var30).intern();
               switch (var10001) {
                  case 0:
                     var29[var27++] = var53;
                     if ((var36 += var25) >= var28) {
                        b = var29;
                        c = new String[174];
                        u = "https://login.live.com/oauth20_authorize.srf?redirect_uri=https://sisu.xboxlive.com/connect/oauth/XboxLive&response_type=token&client_id=000000004420578E&scope=XboxLive.Signin%20XboxLive.offline_access&prompt=none";
                        g = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var31 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var17 = new long[47];
                        int var14 = 0;
                        String var15 = "\u0082\u0085E\t\n\f\u008b\u000fS=.ã\u000bÖ\u001bø\u0006²[Ö°CY\u0084ï\u008a\u0000¢\u001eÚÌkÜ@Qi\u0093~\u0081\u008dø\u0091»°=æ\u0005Çt\u007fø×q-\u0099\u0019.'kº±3ØÛpËÛ.ØºP¼x:\u0014\"FrQ¤\u0003¤8\u00810¬\u009b-y\u0010ö\u0004 \u0014}àôÝdL¿\u007fÎq>¥æ]Á\u0082ãä3Ù8Ev\u008aµù%Î\u0019\u0098:V£þ\u0096CÈlík\u001bT\u0007jÊ\u0010Ql(\u0081|Ð\u0096DõAåèXy\u0015Èm\u0010\u008dùn×~ÐÂ\u0091·BwW6\u0092®!mßI\u009f\u0097×\u0000%HM\u0010ìLÜp{\u0002^J\u0087Èã8ê\u0089QÀ\fì9?óW\u0016P'\\lot3\u0098pAêÁM\u0011\u001eÙ\\÷°V\b75Éù¬Y\u001aÍ¯±,X\u0095µ\u0012£8\u008c\u0082½\u0006é\f\u0000õ\u0005\u001d-\u009bÃ\u008dF³\u0082\u0087áF\u0012\u008aæèÁØ8gNÎü÷e\u0016>þED:\u008b²´ø\u0087\u009a\u0081\u0002\u009e\u0081ÚßÛ\u0019g^â\u0016_ÎÚ\u0099Z§CKëä}§\u001f\u0007{Æ\u0082ø Òa¶5\u0098\u0010\\¥úá\u0081¨¯i\rd>ÐÍ7\r\u0018¿ÿÒö\u0019\u000b\u000bÛOÜÖ\u009bmþ\u000bm";
                        int var16 = "\u0082\u0085E\t\n\f\u008b\u000fS=.ã\u000bÖ\u001bø\u0006²[Ö°CY\u0084ï\u008a\u0000¢\u001eÚÌkÜ@Qi\u0093~\u0081\u008dø\u0091»°=æ\u0005Çt\u007fø×q-\u0099\u0019.'kº±3ØÛpËÛ.ØºP¼x:\u0014\"FrQ¤\u0003¤8\u00810¬\u009b-y\u0010ö\u0004 \u0014}àôÝdL¿\u007fÎq>¥æ]Á\u0082ãä3Ù8Ev\u008aµù%Î\u0019\u0098:V£þ\u0096CÈlík\u001bT\u0007jÊ\u0010Ql(\u0081|Ð\u0096DõAåèXy\u0015Èm\u0010\u008dùn×~ÐÂ\u0091·BwW6\u0092®!mßI\u009f\u0097×\u0000%HM\u0010ìLÜp{\u0002^J\u0087Èã8ê\u0089QÀ\fì9?óW\u0016P'\\lot3\u0098pAêÁM\u0011\u001eÙ\\÷°V\b75Éù¬Y\u001aÍ¯±,X\u0095µ\u0012£8\u008c\u0082½\u0006é\f\u0000õ\u0005\u001d-\u009bÃ\u008dF³\u0082\u0087áF\u0012\u008aæèÁØ8gNÎü÷e\u0016>þED:\u008b²´ø\u0087\u009a\u0081\u0002\u009e\u0081ÚßÛ\u0019g^â\u0016_ÎÚ\u0099Z§CKëä}§\u001f\u0007{Æ\u0082ø Òa¶5\u0098\u0010\\¥úá\u0081¨¯i\rd>ÐÍ7\r\u0018¿ÿÒö\u0019\u000b\u000bÛOÜÖ\u009bmþ\u000bm"
                           .length();
                        int var13 = 0;

                        label59:
                        while (true) {
                           var10001 = var13;
                           var13 += 8;
                           byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                           long[] var40 = var17;
                           var10001 = var14++;
                           long var57 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           int var61 = -1;

                           while (true) {
                              long var19 = var57;
                              byte[] var21 = var11.doFinal(
                                 new byte[]{
                                    (byte)(var19 >>> 56),
                                    (byte)(var19 >>> 48),
                                    (byte)(var19 >>> 40),
                                    (byte)(var19 >>> 32),
                                    (byte)(var19 >>> 24),
                                    (byte)(var19 >>> 16),
                                    (byte)(var19 >>> 8),
                                    (byte)var19
                                 }
                              );
                              long var65 = (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L;
                              switch (var61) {
                                 case 0:
                                    var40[var10001] = var65;
                                    if (var13 >= var16) {
                                       e = var17;
                                       f = new Integer[47];
                                       k = new HashMap(13);
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(var31 << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                       long[] var6 = new long[2];
                                       int var3 = 0;
                                       String var4 = "ê\u007f\u0013\u009e¯$q\u0011\u0005,\u0001\u001fGò\u0016\u0011";
                                       int var5 = "ê\u007f\u0013\u009e¯$q\u0011\u0005,\u0001\u001fGò\u0016\u0011".length();
                                       int var2 = 0;

                                       do {
                                          int var50 = var2;
                                          var2 += 8;
                                          byte[] var7 = var4.substring(var50, var2).getBytes("ISO-8859-1");
                                          var50 = var3++;
                                          long var8 = (var7[0] & 255L) << 56
                                             | (var7[1] & 255L) << 48
                                             | (var7[2] & 255L) << 40
                                             | (var7[3] & 255L) << 32
                                             | (var7[4] & 255L) << 24
                                             | (var7[5] & 255L) << 16
                                             | (var7[6] & 255L) << 8
                                             | var7[7] & 255L;
                                          byte[] var10 = var0.doFinal(
                                             new byte[]{
                                                (byte)(var8 >>> 56),
                                                (byte)(var8 >>> 48),
                                                (byte)(var8 >>> 40),
                                                (byte)(var8 >>> 32),
                                                (byte)(var8 >>> 24),
                                                (byte)(var8 >>> 16),
                                                (byte)(var8 >>> 8),
                                                (byte)var8
                                             }
                                          );
                                          var65 = (var10[0] & 255L) << 56
                                             | (var10[1] & 255L) << 48
                                             | (var10[2] & 255L) << 40
                                             | (var10[3] & 255L) << 32
                                             | (var10[4] & 255L) << 24
                                             | (var10[5] & 255L) << 16
                                             | (var10[6] & 255L) << 8
                                             | var10[7] & 255L;
                                          var6[var50] = var65;
                                       } while (var2 < var5);

                                       h = var6;
                                       j = Executors.newFixedThreadPool(4);
                                       P = new Gson();
                                       K = RequestConfig.custom()
                                          .setConnectionRequestTimeout(30000)
                                          .setConnectTimeout(30000)
                                          .setSocketTimeout(30000)
                                          .build();
                                       String[] var42 = new String[23];
                                       var42[0] = "__Host-MSAAUTH";
                                       var42[1] = "__Host-MSAAUTHP";
                                       var42[2] = "JSHP";
                                       var42[3] = "JSH";
                                       var42[4] = "MSPAuth";
                                       var42[5] = "MSPBack";
                                       var42[6] = "MSPProf";
                                       var42[7] = "MSPRequ";
                                       var42[8] = "MSPSoftVis";
                                       var42[9] = "MSPOK";
                                       var42[10] = "MSPShared";
                                       var42[11] = "MSPPre";
                                       var42[12] = "MSPCID";
                                       var42[13] = "MSPOAuthVis";
                                       var42[14] = "AMCSecAuth";
                                       var42[15] = "NAP";
                                       var42[16] = "ANON";
                                       var42[17] = "OParams";
                                       var42[18] = "PPLState";
                                       var42[19] = "WLSSC";
                                       var42[20] = "uaid";
                                       var42[21] = "pres";
                                       var42[22] = "LOpt";
                                       N = Arrays.asList(var42);
                                       String[] var43 = new String[23];
                                       var43[0] = "__Host-MSAAUTH";
                                       var43[1] = "__Host-MSAAUTHP";
                                       var43[2] = "JSH";
                                       var43[3] = "JSHP";
                                       var43[4] = "MSPAuth";
                                       var43[5] = "MSPBack";
                                       var43[6] = "MSPProf";
                                       var43[7] = "MSPRequ";
                                       var43[8] = "MSPSoftVis";
                                       var43[9] = "MSPOK";
                                       var43[10] = "MSPShared";
                                       var43[11] = "MSPPre";
                                       var43[12] = "MSPCID";
                                       var43[13] = "MSPOAuthVis";
                                       var43[14] = "AMCSecAuth";
                                       var43[15] = "NAP";
                                       var43[16] = "ANON";
                                       var43[17] = "OParams";
                                       var43[18] = "PPLState";
                                       var43[19] = "WLSSC";
                                       var43[20] = "uaid";
                                       var43[21] = "pres";
                                       var43[22] = "LOpt";
                                       X = Arrays.asList(var43);
                                       return;
                                    }
                                    break;
                                 default:
                                    var40[var10001] = var65;
                                    if (var13 < var16) {
                                       continue label59;
                                    }

                                    var15 = "Y \u0096^Ôß\u0083\u0083}¡GÊNBÌ\u0016";
                                    var16 = "Y \u0096^Ôß\u0083\u0083}¡GÊNBÌ\u0016".length();
                                    var13 = 0;
                              }

                              int var49 = var13;
                              var13 += 8;
                              var18 = var15.substring(var49, var13).getBytes("ISO-8859-1");
                              var40 = var17;
                              var10001 = var14++;
                              var57 = (var18[0] & 255L) << 56
                                 | (var18[1] & 255L) << 48
                                 | (var18[2] & 255L) << 40
                                 | (var18[3] & 255L) << 32
                                 | (var18[4] & 255L) << 24
                                 | (var18[5] & 255L) << 16
                                 | (var18[6] & 255L) << 8
                                 | var18[7] & 255L;
                              var61 = 0;
                           }
                        }
                     }

                     var25 = var26.charAt(var36);
                     break;
                  default:
                     var29[var27++] = var53;
                     if ((var36 += var25) < var28) {
                        var25 = var26.charAt(var36);
                        continue label77;
                     }

                     var26 = "J^÷\u0006éÓ\u007f½\u0080\u0007\u0006£ßÏ\u001e\u001f´K\u009fÚ\u008dZøKÀÀB8êô'`¹A\u0085^z\u009a\u0014ý(\u0096uâ\u0096ÄÕR\u001b\u009a\u009bA,[*\u008d*\u0004`\u001b\u001f\u0085,Ñ\u001e\u009a\u0088\u0004·\u0081uØ\u008cL\nÒ\u0085Ïèê\u0013";
                     var28 = "J^÷\u0006éÓ\u007f½\u0080\u0007\u0006£ßÏ\u001e\u001f´K\u009fÚ\u008dZøKÀÀB8êô'`¹A\u0085^z\u009a\u0014ý(\u0096uâ\u0096ÄÕR\u001b\u009a\u009bA,[*\u008d*\u0004`\u001b\u001f\u0085,Ñ\u001e\u009a\u0088\u0004·\u0081uØ\u008cL\nÒ\u0085Ïèê\u0013"
                        .length();
                     var25 = '(';
                     var36 = -1;
               }

               var37 = var26.substring(++var36, var36 + var25);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var33) {
         throw new RuntimeException(var33);
      }
   }

}
