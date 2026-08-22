package Expo.setting.settings;

import Expo.util.render.VisualSpoofRenderer;

public class ScreenshotBypassSetting extends BooleanSetting {
   public void v(boolean var1, long var2) throws Throwable {
      long var4 = var2 ^ 0L;
      VisualSpoofRenderer.T(var1);
      super.v(var1, var4);
   }

   public ScreenshotBypassSetting(String var1, boolean var2) {
      super(var1, var2);
   }
}
