package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.PercentageSetting;

public class NoHurtCam extends Module {
   public static PercentageSetting effect;
   private static final long a = 43035660892934L;

   static {
      effect = new PercentageSetting("Effect", 0);
   }

   public NoHurtCam(long var1) {
      super(((a ^ (var1)) ^ 90698830292366L));
      this.declare("NoHurtCam", Category.Visual, "Change the hurt camera effect");
      var1 = a ^ var1;
   }}
