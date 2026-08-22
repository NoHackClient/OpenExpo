package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;
import Expo.ui.raven.RavenClickGuiScreen;
import Expo.ui.studio.StudioClickGuiScreen;
import Expo.ui.vestige.VestigeClickGuiScreen;
import Expo.util.KeyBindUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.lwjgl.input.Keyboard;











public class ClickGUI extends Module {
   public static NumberSetting scale;
   public static VestigeClickGuiScreen B;
   private static long a;
   public static StudioClickGuiScreen Y;
   private static String[] c;
   public static ModeSetting mode;
   private static String[] b;
   private static long e;
   public static TextSetting keybind;
   private static Map d;
   public static RavenClickGuiScreen F;

   public static void O(int var0, int var1, char var2) {
      switch (mode.Y()) {
         case "RAVEN":
            f.displayGuiScreen(F);
            F.P();
            break;
         case "VESTIGE":
            f.displayGuiScreen(B);
            break;
         default:
            f.displayGuiScreen(Y);
      }
   }

   static {
      a = 104656739453137L;
      d = new HashMap(13);
      b = new String[]{"\"`\u00df\u001f\"\u0018^\n", "\u00bb\u00d6h\u00a9\u00e6\u00eb&\u00d9", "9\u008fUy\u0004\u0010\u00ac\u0097"};
      c = new String[3];
      e = -1470002277744902144L;
   }

   private static String b(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ 11714 ^ 1828;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])d.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/module/impl/configuration/ClickGUI", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)73L;

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(5262084930954866114L << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         c[var5] = b(((Cipher)var4[0]).doFinal(var9));
      }

      return c[var5];
   }

   public static boolean a(long var0) {


      return KeyBindUtil.a(81924588974218L, keybind.X()) != (int)e;
   }

   public static String r(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      return a(28525798576885L) ? Keyboard.getKeyName(KeyBindUtil.a(133307858789426L, keybind.X())) : b(15966, 5262084930954866114L);
   }

   private static void a() {
   }

   public ClickGUI(long var1) {
      super(((a ^ (var1)) ^ 117393621110351L));
      // add code
      this.declare("ClickGUI", Category.Configuration, "Manager ClickGUI settings");
      var1 = a ^ var1;
   }

   public static boolean x(int var0, short var1, char var2) {
      long var3 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      long var5 = var3 ^ 54891142830207L;
      long var7 = var3 ^ 89214500155064L;
      long var9 = var3 ^ 37360305032848L;
      return a(var5) && KeyBindUtil.V(KeyBindUtil.a(var7, keybind.X()), var9);
   }

   private static String b(byte[] var0) {
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
   static {
      // add code
      scale = new NumberSetting("Scale", 1.0F, 0.1F, 5.0F, 0.01F);
      keybind = new TextSetting("Keybind", "RSHIFT");
      mode = new ModeSetting("Mode", true, "STUDIO", "STUDIO", "RAVEN", "VESTIGE");
   }
}
