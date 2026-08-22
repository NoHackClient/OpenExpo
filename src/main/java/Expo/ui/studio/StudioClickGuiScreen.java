package Expo.ui.studio;

import Expo.module.Category;
import Expo.module.Modules;
import Expo.module.impl.configuration.ClickGUI;
import Expo.util.Animation;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.Sneaky;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class StudioClickGuiScreen extends GuiScreen {
   private float y;
   private String K;
   private static Map<Category, StudioFrameState> a;
   private long V;
   private static long[] f;
   private StudioModuleFrame i;
   private static String[] d;
   private float Q;
   private TextSettingComponent P;
   private boolean L;
   private final Animation A;
   private static Integer[] g;
   private static long b;
   private final Animation W;
   private static final float D = 0.94F;
   private final List<StudioNotification> k;
   private boolean m;
   private float v;
   private static Map h;
   private float Z;
   private static Map e;
   private static String[] c;

   public void X(String var1, float var2, float var3) {
      if (var1 != null && !var1.isEmpty()) {
         this.K = var1;
         this.Z = var2;
         this.v = var3;
      }
   }

   public static void n() {
      a.clear();
      Z();
   }

   public void E(StudioModuleFrame var1, short var2, short var3, int var4) {
      long var5 = ((long)var2 << 48 | (long)var3 << 48 >>> 16 | (long)var4 << 32 >>> 32) ^ b;
      long var7 = var5 ^ 138917461483055L;
      this.m = ((1 & 1) != 0);
      this.i = var1;
      if (this.P != null) {
         this.n(this.P, var7);
      }
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      b = 120365167084229L;
      e = new HashMap(13);
      long var11 = b ^ 113305454460144L;
      Cipher var13;
      byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var14 = 1; var14 < 8; var14++) {
         var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
      }

      (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var20 = new String[6];
      int var18 = 0;
      String var17 = "\u008aQXÏ\u0002I¼>8X©Ä\u0002\u0017·¢\u0010\u008bý\u0086É\u0086\b«Ñ\u0010¡txð\u001bê\u007f\u0010\u0080ÜXþjn\u0091õQýå\u001etÚ|\u001b\u0018ë\u00191\u009cgÃØz\bë\u00809¶$\b|ù\u001bÙO8ÖNæ";
      int var19 = "\u008aQXÏ\u0002I¼>8X©Ä\u0002\u0017·¢\u0010\u008bý\u0086É\u0086\b«Ñ\u0010¡txð\u001bê\u007f\u0010\u0080ÜXþjn\u0091õQýå\u001etÚ|\u001b\u0018ë\u00191\u009cgÃØz\bë\u00809¶$\b|ù\u001bÙO8ÖNæ"
         .length();
      char var16 = 16;
      int var24 = -1;

      label54:
      while (true) {
         String var25 = var17.substring(++var24, var24 + var16);
         int var10001 = -1;

         while (true) {
            byte[] var21 = var13.doFinal(var25.getBytes("ISO-8859-1"));
            String var36 = a(var21).intern();
            switch (var10001) {
               case 0:
                  var20[var18++] = var36;
                  if ((var24 += var16) >= var19) {
                     c = var20;
                     d = new String[6];
                     h = new HashMap(13);
                     Cipher var0;
                     var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                     }

                     (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[5];
                     int var3 = 0;
                     String var4 = "Te¨dn¸\u001d\u001f¹\u0006Q\u0082\u008c\u0014\u009a\u0000×r\u0018\u0089\n+`e";
                     int var5 = "Te¨dn¸\u001d\u001f¹\u0006Q\u0082\u008c\u0014\u009a\u0000×r\u0018\u0089\n+`e".length();
                     int var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var28 = var6;
                        var10001 = var3++;
                        long var40 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        int var43 = -1;

                        while (true) {
                           long var8 = var40;
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
                           long var45 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var43) {
                              case 0:
                                 var28[var10001] = var45;
                                 if (var2 >= var5) {
                                    f = var6;
                                    g = new Integer[5];
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "îYµcü\u0000Óß×À\u001f3Ö\u0011\u0090o";
                                 var5 = "îYµcü\u0000Óß×À\u001f3Ö\u0011\u0090o".length();
                                 var2 = 0;
                           }

                           int var34 = var2;
                           var2 += 8;
                           var7 = var4.substring(var34, var2).getBytes("ISO-8859-1");
                           var28 = var6;
                           var10001 = var3++;
                           var40 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var43 = 0;
                        }
                     }
                  }

                  var16 = var17.charAt(var24);
                  break;
               default:
                  var20[var18++] = var36;
                  if ((var24 += var16) < var19) {
                     var16 = var17.charAt(var24);
                     continue label54;
                  }

                  var17 = "Ua_8øiÆ÷Ð!Ñ®üX2eûõUXÏ\u0007oeV\u0089\u009cÖ\u0095Ë\u008b}\u0010¸ÞJu¬ÃÁ¹\u0092ð\u001ee\u001b\u0085\u008a/";
                  var19 = "Ua_8øiÆ÷Ð!Ñ®üX2eûõUXÏ\u0007oeV\u0089\u009cÖ\u0095Ë\u008b}\u0010¸ÞJu¬ÃÁ¹\u0092ð\u001ee\u001b\u0085\u008a/".length();
                  var16 = ' ';
                  var24 = -1;
            }

            var25 = var17.substring(++var24, var24 + var16);
            var10001 = 0;
         }
      }
   }

   public float y() {
      return this.y;
   }

   public void J(long var1, TextSettingComponent var3) {
      var1 = b ^ var1;
      long var4 = var1 ^ 95826051919469L;
      long var6 = var1 ^ 134812745124391L;
      this.L = (((b(27238, 6017169820152733544L ^ var1)) & 1) != 0);
      if (this.P != null && this.P != var3) {
         this.n(this.P, var6);
      }

      this.P = var3;
      this.P.c(var4);
   }

   public void n(TextSettingComponent var1, long var2) {
      long var4 = var2 ^ 48216273847780L;
      if (this.P != null) {
         if (var1 == null || var1 == this.P) {
            this.P.U(var4, true);
            this.P = null;
            Modules.c(0L);
         }
      }
   }

   private void l(StudioNotification var1) {
      this.k.remove(var1);
      this.k.add(var1);
   }

   public void initGui() {
      super.initGui();
      Z();
      Expo.internal.restore.ExpoClickGui.beginDisplaySort();
      try {
         this.z(9394818575647L);
      } finally {
         Expo.internal.restore.ExpoClickGui.endDisplaySort();
      }
      this.V = System.currentTimeMillis();
      this.A.U(0.0F);
      this.A.d(1.0F);
      this.W.U(this.Q);
   }

   protected void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
      float var8 = ClickGUI.scale.L() * 0.94F;
      float var9 = var1 / var8;
      float var10 = var2 / var8;

      for (StudioNotification var12 : this.k) {
         var12.k(var9, 61865618957008L, var10);
      }
   }

   public static void q(JsonObject var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      Z();
      JsonObject var3 = new JsonObject();

      for (Entry var5 : a.entrySet()) {
         JsonObject var6 = new JsonObject();
         var6.addProperty("X", ((StudioFrameState)var5.getValue()).J);
         var6.addProperty("Y", ((StudioFrameState)var5.getValue()).h);
         var6.addProperty("Opened", ((StudioFrameState)var5.getValue()).S);
         JsonObject var7 = new JsonObject();

         for (Entry var9 : ((StudioFrameState)var5.getValue()).W().entrySet()) {
            if (Boolean.TRUE.equals(var9.getValue())) {
               var7.addProperty((String)var9.getKey(), true);
            }
         }

         if (!var7.entrySet().isEmpty()) {
            var6.add("ExpandedModules", var7);
         }

         var3.add(((Category)var5.getKey()).c(), var6);
      }

      var0.add("Studio", var3);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void drawScreen(int var1, int var2, float var3) {
       try {long var4 = 65742445683630L;

      this.W();
      float var13 = ClickGUI.scale.L() * 0.94F;
      float var14 = var1 / var13;
      float var15 = var2 / var13;
      this.A.y(0.18F, this.y);
      this.W.d(this.Q);
      this.W.y(0.18F, this.y);
      this.o();
      float var16 = this.A.b(var3);
      float var17 = this.W.b(var3);

      for (StudioNotification var19 : this.k) {
         var19.t(var14, (char)0, var15, this.W.E(), 450090305);
      }

      GL11.glPushMatrix();
      GL11.glScaled(var13, var13, 1.0);
      this.l(var16);
      this.K = null;

      for (StudioNotification var21 : this.k) {
         var21.d(var14, var15, var17, var3, var16, 112770940687988L, var13);
      }

      this.C(76088342050980L);
      GL11.glPopMatrix();
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private void W() {
      long var1 = System.currentTimeMillis();
      if (this.V == 0L) {
         this.V = var1;
      }

      this.y = MathUtil.q((float)(var1 - this.V) / 16.0F, 0.5F, 4.0F);
      this.V = var1;
   }

   protected void mouseClicked(int var1, int var2, int var3) {
       try {long var4 = 21258311689151L;

      super.mouseClicked(var1, var2, var3);
      if (this.i != null && var3 > 1) {
         this.i.E().z(118276941480361L, KeyBindUtil.w((char)0, var3, 132797583844084L));
         this.i = null;
         Modules.c(0L);
      } else {
         float var17 = ClickGUI.scale.L() * 0.94F;
         float var18 = var1 / var17;
         float var19 = var2 / var17;
         float var20 = this.W.E();
         TextSettingComponent var21 = this.P;
         StudioModuleFrame var22 = this.i;
         this.L = ((0 & 1) != 0);
         this.m = ((0 & 1) != 0);

         for (int var23 = this.k.size() - 1; var23 >= 0; var23--) {
            StudioNotification var24 = this.k.get(var23);
            if (var24.m(var18, var19, 69261216583452L, var3, var20)) {
               this.l(var24);
               if (var21 != null && var21 == this.P && !this.L) {
                  this.n(var21, 89965113873812L);
               }

               if (var22 != null && var22 == this.i && !this.m) {
                  this.i = null;
               }

               return;
            }
         }

         if (this.P != null) {
            this.n(this.P, 89965113873812L);
         }

         this.i = null;
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private void o() {
      float var1 = Float.MAX_VALUE;
      float var2 = -Float.MAX_VALUE;

      for (StudioNotification var4 : this.k) {
         StudioFrameState var5 = a.get(var4.S());
         var1 = Math.min(var1, var5.h);
         var2 = Math.max(var2, var4.m(1.0F));
      }

      float var7 = 24.0F;
      float var8 = this.height - 20.0F;
      float var9 = Math.min(var7 - var1, var8 - var2);
      float var6 = var7 - var1;
      if (var2 - var1 <= this.height - 44.0F) {
         var9 = var6;
      }

      this.Q = MathUtil.q(this.Q, var9, var6);
   }

   protected void keyTyped(char var1, int var2) {
      if (this.i != null) {
         this.i.E().z(118276941480361L, var2 != 211 && var2 != 1 ? var2 : 0);
         this.i = null;
         Modules.c(0L);
      } else if (this.P != null && this.P.V()) {
         this.P.h(13426, var1, var2, (char)40995, (short)4065);
      } else if (var2 == 1) {
         this.mc.displayGuiScreen(null);
         if (this.mc.currentScreen == null) {
            this.mc.setIngameFocus();
         }

         Modules.c(0L);
      } else {
         for (StudioNotification var13 : this.k) {
            var13.s(var1, var2);
         }
      }
   }

   private void l(float var1) {
   }

   private void z(long var1) {
      this.k.clear();

      for (Category var8 : Category.values()) {
         this.k.add(new StudioNotification(this, var8, 55250609642696L, a.get(var8)));
      }
   }

   public boolean s(StudioModuleFrame var1) {
      return this.i == var1;
   }

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 10943;
      if (g[var3] == null) {
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
         long var5 = f[var3];
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
         Object[] var9 = (Object[])h.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               h.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/ui/studio/StudioClickGuiScreen", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
   }

   private static void Z() {
      if (a.isEmpty() || a.size() != Category.values().length) {
         a.clear();
         float var0 = 24.0F;
         float var1 = 60.0F;
         float var2 = 20.0F;
         Category[] var3 = Category.values();

         for (int var4 = 0; var4 < var3.length; var4++) {
            a.put(var3[var4], new StudioFrameState(var0, var1 + var4 * var2, false));
         }
      }
   }

   public static void H(JsonObject var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      Z();

      for (StudioFrameState var4 : a.values()) {
         var4.j();
      }

      if (var0.has("Studio")) {
         JsonObject var14 = var0.getAsJsonObject("Studio");

         for (Category var7 : Category.values()) {
            if (var14.has(var7.c())) {
               JsonObject var8 = var14.getAsJsonObject(var7.c());
               StudioFrameState var9 = a.get(var7);
               if (var8.has("X")) {
                  var9.J = var8.get("X").getAsFloat();
               }

               if (var8.has("Y")) {
                  var9.h = var8.get("Y").getAsFloat();
               }

               if (var8.has("Opened")) {
                  var9.S = var8.get("Opened").getAsBoolean();
               }

               if (var8.has("ExpandedModules") && var8.get("ExpandedModules").isJsonObject()) {
                  JsonObject var10 = var8.getAsJsonObject("ExpandedModules");

                  for (Entry var12 : var10.entrySet()) {
                     if (((JsonElement)var12.getValue()).isJsonPrimitive() && ((JsonElement)var12.getValue()).getAsJsonPrimitive().isBoolean()) {
                        var9.W((String)var12.getKey(), ((JsonElement)var12.getValue()).getAsBoolean());
                     }
                  }
               }
            }
         }
      }
   }

   private void C(long var1) {
      if (this.K != null) {
         Expo.util.render.RenderUtil.m(28813, this.K, (int)this.Z, 53203, (short)7294, (int)this.v);
      }
   }

   public StudioClickGuiScreen(long var1) {
      long var3 = 9394818575647L;
      this.k = new ArrayList<>();
      this.A = new Animation(0.0F);
      this.W = new Animation(0.0F);
      this.y = 1.0F;
      Z();
      this.z(var3);
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
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var1 = Mouse.getDWheel();
      if (var1 != 0) {
         this.Q += var1 > 0 ? 34.0F : -34.0F;
      }
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.P != null) {
         this.n(this.P, 89965113873812L);
      }

      this.i = null;

      for (StudioNotification var11 : this.k) {
         var11.m(855328176L, (char)14026);
      }

      Modules.c(0L);
   }

   static {
      try {
         $jnicClinit();
         a = new EnumMap<>(Category.class);
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
   }
}
