package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;











public class FullBright extends Module {
   private static final long b = 113539360861368L;
   private float a;

   public void A(long var1) {
      f.gameSettings.gammaSetting = this.a;
   }

   public FullBright(long var1) {
      super(((b ^ (var1)) ^ 34610441079344L));
      // add code
      this.declare("FullBright", Category.Visual, "Let the game always be bright");
      var1 = b ^ var1;
      this.a = f.gameSettings.gammaSetting;
   }

   public void i(long var1) {
      this.a = f.gameSettings.gammaSetting;
      f.gameSettings.gammaSetting = 15.0F;
   }}
