package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;

public class Gadgets extends Module {
   public static BooleanSetting noScreenBackground;
   private static final long a = 31284398215526L;
   public static BooleanSetting noMiningParticles;
   public static BooleanSetting betterWorldSwapping;

   static {
      noMiningParticles = new BooleanSetting("No-mining-particles", false);
      betterWorldSwapping = new BooleanSetting("Better-world-swapping", true);
      noScreenBackground = new BooleanSetting("No-screen-background", true);
   }

   public Gadgets(long var1) {
      super(((a ^ (var1)) ^ 46172785993502L));
      this.declare("Gadgets", Category.Configuration, "Some useful items");
      var1 = a ^ var1;
   }}
