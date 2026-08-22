package Expo.internal.auth;

import Expo.util.Sneaky;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.net.ssl.SSLSocketFactory;
import net.minecraft.util.Session.Type;
import net.minecraft.util.Session;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;











public final class AuthService {
   public static String E;
   private static Map d;
   public static int P;
   private static long a;
   private static String[] b;
   public static RequestConfig e;

   public static CompletableFuture<String> P(String var0, String var1, Executor var2) {
      return CompletableFuture.supplyAsync(
         () -> {

            try {
               CloseableHttpClient var6 = d();
               Throwable var7 = null;

               try {
                  HttpPost var8 = new HttpPost(URI.create("https://api.minecraftservices.com/authentication/login_with_xbox"));
                  var8.setConfig(e);
                  var8.setHeader("Content-Type", "application/json");
                  var8.setEntity(new StringEntity(String.format("{\"identityToken\": \"XBL3.0 x=%s;%s\"}", var1, var0)));
                  CloseableHttpResponse var9 = var6.execute(var8);
                  JsonObject var10 = new JsonParser().parse(EntityUtils.toString(var9.getEntity())).getAsJsonObject();
                  return Optional.ofNullable(var10.get("access_token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           return new Exception(
                              var10.has("error")
                                 ? String.format("%s: %s", var10.get("error").getAsString(), var10.get("errorMessage").getAsString())
                                 : "There was no access token or error description present."
                           );
                        }
                     );
               } catch (Throwable var23) {
                  var7 = var23;
                  throw var23;
               } finally {
                  if (var6 != null) {
                     if (var7 != null) {
                        try {
                           var6.close();
                        } catch (Throwable var22) {
                           var7.addSuppressed(var22);
                        }
                     } else {
                        var6.close();
                     }
                  }
               }
            } catch (InterruptedException var25) {
               throw new CancellationException("Minecraft access token acquisition was cancelled!");
            } catch (Exception var26) {
               throw new CompletionException("Unable to acquire Minecraft access token!", var26);
            }
         },
         var2
      );
   }

   public static CompletableFuture<String> M(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(
         () -> {

            try {
               CloseableHttpClient var5 = d();
               Throwable var6 = null;

               try {
                  HttpPost var7 = new HttpPost(URI.create("https://user.auth.xboxlive.com/user/authenticate"));
                  JsonObject var8 = new JsonObject();
                  JsonObject var9 = new JsonObject();
                  var9.addProperty("AuthMethod", "RPS");
                  var9.addProperty("SiteName", "user.auth.xboxlive.com");
                  var9.addProperty("RpsTicket", String.format("d=%s", var0));
                  var8.add("Properties", var9);
                  var8.addProperty("RelyingParty", "http://auth.xboxlive.com");
                  var8.addProperty("TokenType", "JWT");
                  var7.setConfig(e);
                  var7.setHeader("Content-Type", "application/json");
                  var7.setEntity(new StringEntity(var8.toString()));
                  CloseableHttpResponse var10 = var5.execute(var7);
                  JsonObject var11 = var10.getStatusLine().getStatusCode() == 200
                     ? new JsonParser().parse(EntityUtils.toString(var10.getEntity())).getAsJsonObject()
                     : new JsonObject();
                  return Optional.ofNullable(var11.get("Token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           return new Exception(
                              var11.has("XErr")
                                 ? String.format("%s: %s", var11.get("XErr").getAsString(), var11.get("Message").getAsString())
                                 : "There was no access token or error description present."
                           );
                        }
                     );
               } catch (Throwable var24) {
                  var6 = var24;
                  throw var24;
               } finally {
                  if (var5 != null) {
                     if (var6 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var23) {
                           var6.addSuppressed(var23);
                        }
                     } else {
                        var5.close();
                     }
                  }
               }
            } catch (InterruptedException var26) {
               throw new CancellationException("Xbox Live access token acquisition was cancelled!");
            } catch (Exception var27) {
               throw new CompletionException("Unable to acquire Xbox Live access token!", var27);
            }
         },
         var1
      );
   }

   public static URI P( String var2) {

      try {
         URIBuilder var3 = new URIBuilder("https://login.live.com/oauth20_authorize.srf")
            .addParameter("client_id", "42a60a84-599d-44b2-a7c6-b00cdef1d6a2")
            .addParameter("response_type", "code")
            .addParameter("redirect_uri", String.format("http://localhost:%d/callback", 25575))
            .addParameter("scope", "XboxLive.signin XboxLive.offline_access")
            .addParameter("state", var2)
            .addParameter("prompt", "select_account");
         return var3.build();
      } catch (Exception var4) {
         return null;
      }
   }

   public static CompletableFuture<Map<String, String>> x(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(
         () -> {

            try {
               CloseableHttpClient var5 = d();
               Throwable var6 = null;

               try {
                  HttpPost var7 = new HttpPost(URI.create("https://login.live.com/oauth20_token.srf"));
                  var7.setConfig(e);
                  var7.setHeader("Content-Type", "application/x-www-form-urlencoded");
                  var7.setEntity(
                     new UrlEncodedFormEntity(
                        Arrays.asList(
                           new BasicNameValuePair("client_id", "42a60a84-599d-44b2-a7c6-b00cdef1d6a2"),
                           new BasicNameValuePair("grant_type", "authorization_code"),
                           new BasicNameValuePair("code", var0),
                           new BasicNameValuePair("redirect_uri", String.format("http://localhost:%d/callback", 25575))
                        ),
                        "UTF-8"
                     )
                  );
                  CloseableHttpResponse var8 = var5.execute(var7);
                  JsonObject var9 = new JsonParser().parse(EntityUtils.toString(var8.getEntity())).getAsJsonObject();
                  String var10 = Optional.ofNullable(var9.get("access_token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           long var1xx = a ^ 61670468512220L;
                           return new Exception(
                              var9.has("error")
                                 ? String.format("%s: %s", var9.get("error").getAsString(), var9.get("error_description").getAsString())
                                 : "There was no Microsoft access token or error description present."
                           );
                        }
                     );
                  String var11 = Optional.ofNullable(var9.get("refresh_token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           long var1xx = a ^ 57607353597228L;
                           return new Exception(
                              var9.has("error")
                                 ? String.format("%s: %s", var9.get("error").getAsString(), var9.get("error_description").getAsString())
                                 : "There was no Microsoft refresh token or error description present."
                           );
                        }
                     );
                  HashMap var12 = new HashMap();
                  var12.put("access_token", var10);
                  var12.put("refresh_token", var11);
                  return var12;
               } catch (Throwable var25) {
                  var6 = var25;
                  throw var25;
               } finally {
                  if (var5 != null) {
                     if (var6 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var24) {
                           var6.addSuppressed(var24);
                        }
                     } else {
                        var5.close();
                     }
                  }
               }
            } catch (InterruptedException var27) {
               throw new CancellationException("Microsoft access tokens acquisition was cancelled!");
            } catch (Exception var28) {
               throw new CompletionException("Unable to acquire Microsoft access tokens!", var28);
            }
         },
         var1
      );
   }



   public static CompletableFuture<Map<String, String>> L(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(
         () -> {

            try {
               CloseableHttpClient var5 = d();
               Throwable var6 = null;

               try {
                  HttpPost var7 = new HttpPost("https://xsts.auth.xboxlive.com/xsts/authorize");
                  JsonObject var8 = new JsonObject();
                  JsonObject var9 = new JsonObject();
                  JsonArray var10 = new JsonArray();
                  var10.add(new JsonPrimitive(var0));
                  var9.addProperty("SandboxId", "RETAIL");
                  var9.add("UserTokens", var10);
                  var8.add("Properties", var9);
                  var8.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
                  var8.addProperty("TokenType", "JWT");
                  var7.setConfig(e);
                  var7.setHeader("Content-Type", "application/json");
                  var7.setEntity(new StringEntity(var8.toString()));
                  CloseableHttpResponse var11 = var5.execute(var7);
                  JsonObject var12 = var11.getStatusLine().getStatusCode() == 200
                     ? new JsonParser().parse(EntityUtils.toString(var11.getEntity())).getAsJsonObject()
                     : new JsonObject();
                  return Optional.ofNullable(var12.get("Token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .map(var1xx -> {
                        String var4 = var12.get("DisplayClaims")
                           .getAsJsonObject()
                           .get("xui")
                           .getAsJsonArray()
                           .get(0)
                           .getAsJsonObject()
                           .get("uhs")
                           .getAsString();
                        HashMap var5x = new HashMap();
                        var5x.put("Token", var1xx);
                        var5x.put("uhs", var4);
                        return var5x;
                     })
                     .orElseThrow(
                        () -> {
                           return new Exception(
                              var12.has("XErr")
                                 ? String.format("%s: %s", var12.get("XErr").getAsString(), var12.get("Message").getAsString())
                                 : "There was no access token or error description present."
                           );
                        }
                     );
               } catch (Throwable var25) {
                  var6 = var25;
                  throw var25;
               } finally {
                  if (var5 != null) {
                     if (var6 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var24) {
                           var6.addSuppressed(var24);
                        }
                     } else {
                        var5.close();
                     }
                  }
               }
            } catch (InterruptedException var27) {
               throw new CancellationException("Xbox Live XSTS token acquisition was cancelled!");
            } catch (Exception var28) {
               throw new CompletionException("Unable to acquire Xbox Live XSTS token!", var28);
            }
         },
         var1
      );
   }

   static {
      a = 56313239387342L;
      P = 25575;
      e = RequestConfig.custom()
         .setConnectionRequestTimeout(30000)
         .setConnectTimeout(30000)
         .setSocketTimeout(30000)
         .build();
   }

   public static CompletableFuture<Session> y(String var0, String var1, String var2, Executor var3) {
      return CompletableFuture.supplyAsync(() -> {
         if (!StringUtils.isBlank(var0) && !StringUtils.isBlank(var1) && !StringUtils.isBlank(var2)) {
            return new Session(var1, var2, var0, Type.MOJANG.toString());
         } else {
            throw new IllegalArgumentException("Access Token, Username, and UUID cannot be empty for direct login.");
         }
      }, var3);
   }

   public static CompletableFuture<String> S(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(
         () -> {
             try {long var1x = 49014433104943L;

            HttpServer var3;
            try {
               var3 = HttpServer.create(new InetSocketAddress(25575), 0);
            } catch (IOException var12) {
               throw new CompletionException("Unable to start local auth server!", var12);
            }

            CountDownLatch var4 = new CountDownLatch(1);
            AtomicReference var5 = new AtomicReference(null);
            AtomicReference var6 = new AtomicReference(null);
            var3.createContext(
               "/callback",
               var4x -> {
                  Map var7x = URLEncodedUtils.parse(var4x.getRequestURI().toString().replaceAll("/callback\\?", ""), StandardCharsets.UTF_8)
                     .stream()
                     .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
                  if (!var0.equals(var7x.get("state"))) {
                     var6.set(String.format("State mismatch! Expected '%s' but got '%s'.", var0, var7x.get("state")));
                  } else if (var7x.containsKey("code")) {
                     var5.set(var7x.get("code"));
                  } else if (var7x.containsKey("error")) {
                     var6.set(String.format("%s: %s", var7x.get("error"), var7x.get("error_description")));
                  }

                  InputStream var8 = AuthService.class.getResourceAsStream("/callback.html");
                  byte[] var9x = var8 != null ? IOUtils.toByteArray(var8) : new byte[0];
                  var4x.getResponseHeaders().add("Content-Type", "text/html");
                  var4x.sendResponseHeaders(200, var9x.length);
                  var4x.getResponseBody().write(var9x);
                  var4x.getResponseBody().close();
                  var4.countDown();
               }
            );

            try {
               var3.start();
               var4.await();
               String var13 = (String)Optional.ofNullable(var5.get()).filter(var0xx -> !StringUtils.isBlank((CharSequence)var0xx)).orElseThrow(() -> {
                  return new Exception((String)Optional.ofNullable(var6.get()).orElse("There was no auth code or error description present."));
               });
               var3.stop(2);
               return var13;
            } catch (Throwable var11) {
               Throwable var7 = var11;

               try {
                  var3.stop(2);
                  throw var7;
               } catch (InterruptedException var9) {
                  throw new CancellationException("Microsoft auth code acquisition was cancelled!");
               } catch (Exception var10) {
                  throw new CompletionException("Unable to acquire Microsoft auth code!", var10);
               }
            }
         } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } },
         var1
      );
   }

   public static CompletableFuture<Session> i(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(() -> {

         try {
            CloseableHttpClient var5 = d();
            Throwable var6 = null;

            try {
               HttpGet var7 = new HttpGet(URI.create("https://api.minecraftservices.com/minecraft/profile"));
               var7.setConfig(e);
               var7.setHeader("Authorization", "Bearer " + var0);
               CloseableHttpResponse var8 = var5.execute(var7);
               JsonObject var9 = new JsonParser().parse(EntityUtils.toString(var8.getEntity())).getAsJsonObject();
               if (var9.has("error")) {
                  throw new Exception(String.format("%s: %s", var9.get("error").getAsString(), var9.get("errorMessage").getAsString()));
               } else {
                  return Optional.ofNullable(var9.get("id")).<String>map(JsonElement::getAsString).filter(var0xx -> !StringUtils.isBlank(var0xx)).map(var2 -> {
                     return new Session(var9.get("name").getAsString(), var2, var0, Type.MOJANG.toString());
                  }).orElseThrow(() -> {
                     return new Exception("Minecraft profile ID (UUID) was missing from the response.");
                  });
               }
            } catch (Throwable var22) {
               var6 = var22;
               throw var22;
            } finally {
               if (var5 != null) {
                  if (var6 != null) {
                     try {
                        var5.close();
                     } catch (Throwable var21) {
                        var6.addSuppressed(var21);
                     }
                  } else {
                     var5.close();
                  }
               }
            }
         } catch (InterruptedException var24) {
            throw new CancellationException("Minecraft profile fetching was cancelled!");
         } catch (Exception var25) {
            throw new CompletionException("Unable to fetch Minecraft profile!", var25);
         }
      }, var1);
   }

   public static CompletableFuture<Map<String, String>> A(String var0, Executor var1) {
      return CompletableFuture.supplyAsync(
         () -> {

            try {
               CloseableHttpClient var5 = d();
               Throwable var6 = null;

               try {
                  HttpPost var7 = new HttpPost(URI.create("https://login.live.com/oauth20_token.srf"));
                  var7.setConfig(e);
                  var7.setHeader("Content-Type", "application/x-www-form-urlencoded");
                  var7.setEntity(
                     new UrlEncodedFormEntity(
                        Arrays.asList(
                           new BasicNameValuePair("client_id", "42a60a84-599d-44b2-a7c6-b00cdef1d6a2"),
                           new BasicNameValuePair("grant_type", "refresh_token"),
                           new BasicNameValuePair("refresh_token", var0),
                           new BasicNameValuePair("redirect_uri", String.format("http://localhost:%d/callback", 25575))
                        ),
                        "UTF-8"
                     )
                  );
                  CloseableHttpResponse var8 = var5.execute(var7);
                  JsonObject var9 = new JsonParser().parse(EntityUtils.toString(var8.getEntity())).getAsJsonObject();
                  String var10 = Optional.ofNullable(var9.get("access_token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           long var1xx = a ^ 75322153337202L;
                           return new Exception(
                              var9.has("error")
                                 ? String.format("%s: %s", var9.get("error").getAsString(), var9.get("error_description").getAsString())
                                 : "There was no Microsoft access token or error description present."
                           );
                        }
                     );
                  String var11 = Optional.ofNullable(var9.get("refresh_token"))
                     .<String>map(JsonElement::getAsString)
                     .filter(var0xx -> !StringUtils.isBlank(var0xx))
                     .orElseThrow(
                        () -> {
                           long var1xx = a ^ 90169655270364L;
                           return new Exception(
                              var9.has("error")
                                 ? String.format("%s: %s", var9.get("error").getAsString(), var9.get("error_description").getAsString())
                                 : "There was no Microsoft refresh token or error description present."
                           );
                        }
                     );
                  HashMap var12 = new HashMap();
                  var12.put("access_token", var10);
                  var12.put("refresh_token", var11);
                  return var12;
               } catch (Throwable var25) {
                  var6 = var25;
                  throw var25;
               } finally {
                  if (var5 != null) {
                     if (var6 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var24) {
                           var6.addSuppressed(var24);
                        }
                     } else {
                        var5.close();
                     }
                  }
               }
            } catch (InterruptedException var27) {
               throw new CancellationException("Microsoft access tokens acquisition was cancelled!");
            } catch (Exception var28) {
               throw new CompletionException("Unable to acquire Microsoft access tokens!", var28);
            }
         },
         var1
      );
   }





   private static CloseableHttpClient d() {

      try {
         SSLSocketFactory var2 = TrustAllSslContext.j().getSocketFactory();
         SSLConnectionSocketFactory var3 = new SSLConnectionSocketFactory(var2, new String[]{"TLSv1.2"}, null, new BrowserCompatHostnameVerifier());
         return HttpClientBuilder.create().setSSLSocketFactory(var3).build();
      } catch (Exception var4) {
         return HttpClients.createDefault();
      }
   }



}
