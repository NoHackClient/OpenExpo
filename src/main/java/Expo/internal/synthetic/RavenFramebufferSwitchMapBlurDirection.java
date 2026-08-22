package Expo.internal.synthetic;

import Expo.enums.BlurDirection;








public class RavenFramebufferSwitchMapBlurDirection {
   public static final int[] O = new int[BlurDirection.values().length];

   static {
      try {
         O[BlurDirection.LR.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         O[BlurDirection.TB.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
