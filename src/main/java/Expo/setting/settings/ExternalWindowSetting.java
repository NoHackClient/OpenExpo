package Expo.setting.settings;

import Expo.util.render.VisualSpoofRenderer;

public class ExternalWindowSetting extends BooleanSetting {
   public void v(boolean var1, long var2) throws Throwable {
      long var4 = var2 ^ 0L;
      VisualSpoofRenderer.j(var1);
      super.v(var1, var4);
   }

   public ExternalWindowSetting(String var1, boolean var2) {
      super(var1, var2);
   }

   static {
      a();
   }

   private static void a() {
   }
}
