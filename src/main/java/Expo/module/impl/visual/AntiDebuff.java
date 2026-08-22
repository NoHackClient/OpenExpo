package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;











public class AntiDebuff extends Module {
   private static final long a = 43175475369575L;

   public AntiDebuff(long var1) {
      super(((a ^ (var1)) ^ 8257773380165L));
      // add code
      this.declare("AntiDebuff", Category.Visual, "Remove debuff rendering");
      var1 = a ^ var1;
   }}
