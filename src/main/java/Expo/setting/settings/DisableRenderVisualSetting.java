package Expo.setting.settings;

import Expo.util.render.VisualSpoofRenderer;

public class DisableRenderVisualSetting extends BooleanSetting {
   public void v(boolean var1, long var2) throws Throwable {
      long var4 = 64895789836511L;
      VisualSpoofRenderer.q(var1);
      super.v(var1, var4);
   }

   public DisableRenderVisualSetting(String var1, boolean var2) {
      super(var1, var2);
   }
}
