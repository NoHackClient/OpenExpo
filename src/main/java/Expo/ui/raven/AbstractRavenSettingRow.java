package Expo.ui.raven;

import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.Sneaky;
import java.awt.Color;

public abstract class AbstractRavenSettingRow implements RavenElement {
   protected int P;
   protected int N;
   protected int g;
   protected RavenModuleRow O;
   public static int a;
   public static int C;
   public static int r;
   public static int M;
   protected int h;
   private static long b;
   protected int y;

   public final void c(int var1, long var2, int var4) {
       try {long var5 = var2 ^ 116072975790327L;
      boolean var9 = this.D(0L, var1, var4);
      this.P = var9 ? a : C;
      this.N = var9 ? r : M;
      this.V(var5, var1, var4);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void t(RavenModuleRow var1) {
      this.O = var1;
   }

   public AbstractRavenSettingRow(RavenModuleRow var1) {
      this.P = C;
      this.N = M;
      this.t(var1);
   }

   public boolean D(long var1, int var3, int var4) {
      return var3 > this.h && var3 < this.h + this.C().O.t() && var4 > this.g && var4 < this.g + 8;
   }

   public static AbstractRavenSettingRow e(Setting var0, RavenModuleRow var1, long var2, int var4) {
      if (var0 instanceof HeaderSetting) {
         return new RavenTextRow((HeaderSetting)var0, var1, var4);
      } else if (var0 instanceof ModeSetting) {
         return new RavenModeRow((ModeSetting)var0, var1, var4);
      } else if (var0 instanceof NumberSetting) {
         return new RavenSliderRow((NumberSetting)var0, 27408400409158L, var1, var4);
      } else if (var0 instanceof PercentageSetting) {
         return new RavenSliderRow((short)0, (PercentageSetting)var0, var1, (char)30473, var4, 338882696);
      } else if (var0 instanceof BooleanSetting) {
         return new RavenCheckBoxRow(var1.R, (BooleanSetting)var0, var1, var4);
      } else if (var0 instanceof ColorSetting) {
         return new RavenColorRow((ColorSetting)var0, var1, var4, 95728611103432L);
      } else {
         return var0 instanceof TextSetting ? new RavenTextRow((TextSetting)var0, var1, var4) : null;
      }
   }

   static {
      b = 22004650097170L;
      C = new Color(255, 255, 255)
         .getRGB();
      a = new Color(162, 162, 162)
         .getRGB();
      M = new Color(20, 255, 0).getRGB();
      r = new Color(20, 162, 0).getRGB();
   }

   public RavenModuleRow C() {
      return this.O;
   }
}
