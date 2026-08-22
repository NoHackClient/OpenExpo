package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.DisableRenderVisualSetting;
import Expo.setting.settings.ExternalWindowSetting;
import Expo.setting.settings.ScreenshotBypassSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.KeyBindUtil;











public class VisualSpoof extends Module {
   public static ScreenshotBypassSetting o;
   public static TextSetting keybindToggleRenderVisual;
   public static ExternalWindowSetting v;
   private static long a;
   private static long b;
   public static DisableRenderVisualSetting t;

   public VisualSpoof(short var1, short var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 48) >>> 16)) | (((long)((var3)) << 32) >>> 32)) ^ a) ^ 5201466166722L));
      // add code
      this.declare("VisualSpoof", Category.Configuration, "Turn on or off visual spoofing");
   }

   private static void a() {
   }

   public static boolean n(long var0) {







      return A((short)0, 130018228, 5179) && KeyBindUtil.V(KeyBindUtil.a(81924588974218L, keybindToggleRenderVisual.X()), 64165991731362L);
   }

   public static boolean A(short var0, int var1, int var2) {
      long var3 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var2 << 48 >>> 48) ^ a;
      long var5 = var3 ^ 61908097401099L;
      return KeyBindUtil.a(var5, keybindToggleRenderVisual.X()) != (int)b;
   }

   static {
      a = 129138922202554L;
      // add code
      b = 6804156122400817152L;
   }

   static {
      // add code
      t = new DisableRenderVisualSetting("Disable-render-visual", false);
      keybindToggleRenderVisual = new TextSetting("Keybind-toggle-render-visual", "NONE");
   }
   static {
      // add code
      v = new ExternalWindowSetting("Enable-external-window", false);
      o = new ScreenshotBypassSetting("Enable-screenshot-bypass", false);
   }
}
