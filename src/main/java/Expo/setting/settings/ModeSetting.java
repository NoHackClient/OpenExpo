package Expo.setting.settings;

import Expo.module.MacroModule;
import Expo.module.Module;
import Expo.module.impl.configuration.Language;
import Expo.setting.Setting;
import Expo.util.MathUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;


public class ModeSetting extends Setting {
   private static long a;
   protected String Y;
   private static String[] g;
   private static Map h;
   private static long i;
   private static String[] f;
   private int p;
   private final List<String> L;

   public String a(char var1, short var2, Module var3, int var4) {
      if (!Language.applyForSettings.c()) {
         return this.Y();
      }

      if (Language.language.R("ENGLISH")) {
         return this.Y();
      }

      try {
         int var9 = MathUtil.k(var3.x(this) + 1, 1, var3.w().size());
         String var10 = var3 instanceof MacroModule ? Language.z("setting.Macro." + var9 + ".modes",0L) : Language.z("setting." + var3.b() + "." + var9 + ".modes",0L);
         String[] var11 = var10.split(", ");
         return var11[this.p];
      } catch (Exception var12) {
         return "LANGUAGE_ERROR";
      }
   }

   public ModeSetting(String var1, boolean var2, String var3, String... var4) {
      this.q = var1;
      this.L = Arrays.asList(var4);
      if (this.L.contains(var3.toUpperCase())) {
         this.Y = var3;
      } else {
         this.Y = var4[0];
      }

      this.p = this.L.indexOf(this.Y);
   }

   public ModeSetting(String var1, String... var2) {
      this.q = var1;
      this.L = Arrays.asList(var2);
      this.Y = var2[0];
      this.p = this.L.indexOf(this.Y);
   }

   public int G() {
      return this.p;
   }




   public List o(char var1, int var2, Module var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!Language.applyForSettings.c()) {
         return this.S();
      }

      if (Language.language.R("ENGLISH")) {
         return this.S();
      }

      try {
         int var9 = MathUtil.k(var3.x(this) + 1, 1, var3.w().size());
         String var13 = var3 instanceof MacroModule
            ? Language.z("setting.Macro." + var9 + ".modes",0L)
            : Language.z("setting." + var3.b() + "." + var9 + ".modes",0L);
         String[] var14 = var13.split(", ");
         return Arrays.asList(var14);
      } catch (Exception var12) {
         ArrayList var10 = new ArrayList();

         for (int var11 = 0; var11 < this.L.size(); var11++) {
            var10.add("LANGUAGE_ERROR");
         }

         return var10;
      }
   }

   public List<String> S() {
      return this.L;
   }

   public void i(String var1) {
      if (this.L.contains(var1)) {
         this.Y = var1.toUpperCase();
         this.p = this.L.indexOf(this.Y);
      } else {
         this.Y = this.L.get(0);
         this.p = this.L.indexOf(this.Y);
      }
   }

   public String Y() {
      return this.Y;
   }

   static {
      a = 62652152538766L;
      h = new HashMap(13);
      f = new String[]{"\u00bfe@{\u00ba\u00aaxS", "/\u009c\u0080\u0003'#H\u00c1", "\u00f4\u00cc\u00b2\u00c5\u001a\u009eEa\u0091s\u00b0)\u00b4\u0094\u0082\u00be", "\u00d7\u00e1\u00cc\u0006\u00e7\u0099\u00ad\u0093", "\u00e6}\u00ca\u00ad+2J\u001e\u00c9\u00ee\u0012\u00cb\u0086\u0004^J", "\u0081\u00ac\u0006\u00cb\u00a2\u00f0\u00cc\u00ec", "\u008d\u00bc\u0017\u00c25\u008c\u00c5\u00d0", "-\u0088\u0016\u0087K\u00c8a\u00bd0_\u0012\u0016(\u00d0&\u00a7", "\u000b\u008bU{X\u00d3\u00f3\u0089\u00af\u0013\u00f2n\u0094\u00f4\u009f\u00b0", "\u0099UZ=@F\u00d7\u0087\u0092\u00a3=\u00f7\u00e0\u00a4fC", "8|\u00baB\u007f\u00c0\u000e\u00a8\u00efX|\u00c1\u00c0\u009b\u0098\u001c", "\u00d8\u00bd\u00f2\u00c0\u00c8\u00b8\u00b3\u0094"};
      g = new String[12];
      i = -733332439924998144L;
   }

   public void w(long var1) {
      this.p++;
      if (this.p > this.L.size() - 1) {
         this.p = (int)i;
      }

      this.Y = this.L.get(this.p);
   }

   public void X() {
      this.p--;
      if (this.p < 0) {
         this.p = this.L.size() - 1;
      }

      this.Y = this.L.get(this.p);
   }

   public void M(int var1) {
      this.p = var1;
   }

   public boolean R(String var1) {
      return this.Y.equalsIgnoreCase(var1);
   }

}
