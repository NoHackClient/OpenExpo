package Expo.module;

import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.configuration.Language;
import Expo.module.impl.configuration.Notifications;
import Expo.setting.Setting;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;

public class Module {
   private boolean V;
   private static Map lb;
   private boolean w;
   private boolean Z;
   private final List<Setting> l;
   private static long[] jb;
   private String W;
   private static String[] db;
   private String Q;
   public static Minecraft f;
   private static String[] eb;
   private boolean A;
   private static Map fb;
   private boolean z;
   private static long cb;
   private boolean q;
   private static Integer[] kb;
   private boolean i;
   private Category X;
   private int j;
   private boolean P;

   public void l(String var1) {
      this.W = var1;
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      cb = 116567223632086L;
      long var20 = cb ^ 63064939872824L;
      fb = new HashMap(13);
      Cipher var11;
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[7];
      int var16 = 0;
      String var15 = "\u0012µVÇñ^ë\u0004C\u000f\u0091ïëj à¾ü³ù:@\u0088\u0088ã\bÂ\t\u000bÑ\u0007c0U\u0010È\u0000ºñ2\u00179\bÚQò\u0017\u0089Þ\u0001áÒp<]e`¸¶W8RCä\u00102f:PaíÐ¡p\u009d8Ø7\u009cmÜ\u0010ë-¢ÝâÜ\"\u009f2Æî\u009b\u0083ûö^8>\u0019\"\u001dj\u009eëf\u0092æ \fç5\r ~eÎlÜ\u0087dæ@\u0091åÕ\u0094ÒÁ\u008dQ\u009dýÌ¤G\u001fô?\u0086Ê\u0084\u0099ÿT¸;D*\u0086\u0007ý§ä\u0010ÔÝMÍ\u0092Î\u0092Ø{\u0090-\u009cÎ¶'\u009d";
      int var17 = "\u0012µVÇñ^ë\u0004C\u000f\u0091ïëj à¾ü³ù:@\u0088\u0088ã\bÂ\t\u000bÑ\u0007c0U\u0010È\u0000ºñ2\u00179\bÚQò\u0017\u0089Þ\u0001áÒp<]e`¸¶W8RCä\u00102f:PaíÐ¡p\u009d8Ø7\u009cmÜ\u0010ë-¢ÝâÜ\"\u009f2Æî\u009b\u0083ûö^8>\u0019\"\u001dj\u009eëf\u0092æ \fç5\r ~eÎlÜ\u0087dæ@\u0091åÕ\u0094ÒÁ\u008dQ\u009dýÌ¤G\u001fô?\u0086Ê\u0084\u0099ÿT¸;D*\u0086\u0007ý§ä\u0010ÔÝMÍ\u0092Î\u0092Ø{\u0090-\u009cÎ¶'\u009d"
         .length();
      char var14 = '@';
      int var27 = -1;

      label54:
      while (true) {
         String var28 = var15.substring(++var27, var27 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var28.getBytes("ISO-8859-1"));
            String var39 = a(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var39;
                  if ((var27 += var14) >= var17) {
                     db = var18;
                     eb = new String[7];
                     lb = new HashMap(13);
                     Cipher var0;
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[4];
                     int var3 = 0;
                     String var4 = "[TAËû&\u0000Â`ö÷©\u0088\u0090-)";
                     int var5 = "[TAËû&\u0000Â`ö÷©\u0088\u0090-)".length();
                     int var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var31 = var6;
                        var10001 = var3++;
                        long var43 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        int var46 = -1;

                        while (true) {
                           long var8 = var43;
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
                           long var48 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var46) {
                              case 0:
                                 var31[var10001] = var48;
                                 if (var2 >= var5) {
                                    jb = var6;
                                    kb = new Integer[4];
                                    return;
                                 }
                                 break;
                              default:
                                 var31[var10001] = var48;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "Nkj;a¨ì\u0087[\u0092÷íÁm\u009d\u008f";
                                 var5 = "Nkj;a¨ì\u0087[\u0092÷íÁm\u009d\u008f".length();
                                 var2 = 0;
                           }

                           int var37 = var2;
                           var2 += 8;
                           var7 = var4.substring(var37, var2).getBytes("ISO-8859-1");
                           var31 = var6;
                           var10001 = var3++;
                           var43 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var46 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var27);
                  break;
               default:
                  var18[var16++] = var39;
                  if ((var27 += var14) < var17) {
                     var14 = var15.charAt(var27);
                     continue label54;
                  }

                  var15 = "o\u001cÏ\fè\u0099j\u0001¹(|Dê:`\u001e(\u0096ÿªØP\u0086ÑYÈh§b¢Øæ}x®p²$ôg]Û\u001f\u000f9,î÷;RM§¹\u008f}øÁ";
                  var17 = "o\u001cÏ\fè\u0099j\u0001¹(|Dê:`\u001e(\u0096ÿªØP\u0086ÑYÈh§b¢Øæ}x®p²$ôg]Û\u001f\u000f9,î÷;RM§¹\u008f}øÁ".length();
                  var14 = 16;
                  var27 = -1;
            }

            var28 = var15.substring(++var27, var27 + var14);
            var10001 = 0;
         }
      }
   }

   public int x(Setting var1) {
      return this.l.indexOf(var1);
   }

   public void E(boolean var1) {
      this.P = var1;
   }

   public void L(PreMouseInputEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   public boolean o() {
      return this.V;
   }

   public String j(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = var1 ^ 136554896177245L;
      if (!Language.applyForDescriptions.c()) {
         return this.x(var5);
      } else {
         return Language.language.R("ENGLISH") ? this.x(var5) : Language.o(this.Q);
      }
   }

   public boolean r() {
      return this.q;
   }

   public void K(String var1) {
      this.Q = var1;
   }

   public void Z(long var1) {
   }

   public void B(Category var1) {
      this.X = var1;
   }

   public List<Setting> w() {
      return this.l;
   }

   public void A(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   public void u(short var1, long var2) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 48 | var2 << 16 >>> 16) ^ cb;
      long var8 = var4 ^ 35690757809613L;
      if (this.I()) {
         if (!this.S()) {
            if (this.o()) {
               if (!(this instanceof MacroModule)) {
                  Notifications.G(var8, "\u00a7l" + this.b() + " \u00a7r\u00a7l(\u00a7c\u00a7lOFF\u00a7r\u00a7l)", false);
               }

               this.I(0L, false);
            } else {
               if (!(this instanceof MacroModule)) {
                  Notifications.G(var8, "\u00a7l" + this.b() + " \u00a7r\u00a7l(\u00a7a\u00a7lON\u00a7r\u00a7l)", true);
               }

               this.I(0L, true);
            }

            Modules.c(0L);
         }
      }
   }

   public boolean S() {
      return this.Z;
   }

   public void P(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   public boolean D() {
      return this.w;
   }

   public String t(int var1, int var2, short var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!Language.applyForArraylist.c()) {
         return this.b();
      } else {
         return Language.language.R("ENGLISH") ? this.b() : Language.Z(0L, this.Q);
      }
   }

   public Module Q(String var1, long var2, byte var4, Boolean var5, Category var6, Boolean var7, String var8, Setting... var9) {
      long var10 = (var2 << 8 | (long)var4 << 56 >>> 56) ^ cb;
      long var12 = var10 ^ 68090126360180L;
      Expo.internal.jnic.StockClientBootstrap.W(var12, this, var1, var5, var6, var7, var8, var9);
      return this;
   }

   public String b() {
      return this.Q;
   }

   public String x(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return !this.z ? "This module is currently disabled" : this.W;
   }

   public void I(long var1, boolean var3) {
      if (this.z) {
         if (var3) {
            if (!this.V) {
               this.i = ((1 & 1) != 0);
            }
         } else if (this.V) {
            this.P = ((1 & 1) != 0);
         }

         this.V = var3;
      }
   }

   public void r(boolean var1) {
      this.Z = var1;
   }

   public void z(long var1, int var3) {
      long var4 = var1 ^ 130247413800026L;
      if (this.Z) {
         this.j = 0;
      } else {
         this.j = KeyBindUtil.m(var4, var3);
      }
   }

   public boolean I() {
      return this.z;
   }

   public void h(long var1) {
   }

   public void M(boolean var1) {
      this.z = var1;
   }

   public List<Setting> m() {
      return this.l;
   }

   public boolean P() {
      return this.A;
   }

   private static int c(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 3610;
      if (kb[var3] == null) {
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
         long var5 = jb[var3];
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
         Object[] var9 = (Object[])lb.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               lb.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/Module", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         kb[var3] = var15;
      }

      return kb[var3];
   }

   public void i(long var1) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
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

   public Module g(String var1, char var2, Boolean var3, Category var4, Boolean var5, long var6, String var8, boolean var9, boolean var10, Setting... var11) {
      long var12 = ((long)var2 << 48 | var6 << 16 >>> 16) ^ cb;
      long var14 = var12 ^ 95210393556639L;
      Expo.internal.jnic.StockClientBootstrap.Z(this, var1, var14, var3, var4, var5, var8, var9, var10, var11);
      return this;
   }

   public void A(boolean var1) {
      this.A = var1;
   }

   public void Y(long var1, boolean var3, short var4) {
      long var5 = (var1 << 16 | (long)var4 << 48 >>> 48) ^ cb;
      if (this.S()) {
         this.w = (((c(31161, 8559718078081886786L ^ var5)) & 1) != 0);
      } else if (this.f() == Category.Macro) {
         this.w = (((c(31161, 8559718078081886786L ^ var5)) & 1) != 0);
      } else {
         this.w = var3;
      }
   }

   public void d() {
   }

   public void C(boolean var1) {
      this.q = var1;
   }

   public boolean l() {
      return this.i;
   }

   public Category f() {
      return this.X;
   }

   protected final Module declare(String var1, Category var2, String var3, Setting... var4) {
      this.Q = var1;
      this.X = var2;
      this.W = var3;
      if (var4 != null) {
         for (Setting var5 : var4) {
            if (var5 != null && !this.l.contains(var5)) {
               this.l.add(var5);
            }
         }
      }

      return this;
   }

   public final String name() {
      return this.Q;
   }

   public final String description() {
      return this.W;
   }

   public final List<Setting> settings() {
      if (this.l.isEmpty()) {
         for (Class<?> var1 = this.getClass(); var1 != null && var1 != Module.class; var1 = var1.getSuperclass()) {
            for (java.lang.reflect.Field var2 : var1.getDeclaredFields()) {
               if (Setting.class.isAssignableFrom(var2.getType())) {
                  try {
                     var2.setAccessible(true);
                     Setting var3 = (Setting)var2.get(this);
                     if (var3 != null && !this.l.contains(var3)) {
                        this.l.add(var3);
                     }
                  } catch (Throwable var4) {
                  }
               }
            }
         }
      }

      return this.l;
   }

   public Module(long var1) {
      this.l = new ArrayList<>();
      this.A = false;
      this.z = true;
      this.Z = false;
      this.q = true;
      this.i = false;
      this.P = false;
   }

   public void n(boolean var1) {
      this.i = var1;
   }

   public String g(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return null;
   }

   public String Q(int var1, char var2, char var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!Language.applyForName.c()) {
         return this.b();
      } else {
         return Language.language.R("ENGLISH") ? this.b() : Language.Z(0L, this.Q);
      }
   }

   public boolean K() {
      return this.P;
   }

   public int h() {
      return this.j;
   }

   static {
      try {
         $jnicClinit();
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
      f = MinecraftRef.c((byte)0, 0L);
   }
}
