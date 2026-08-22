package Expo.internal.restore;

import Expo.module.Category;
import Expo.module.Module;


public final class ExpoSweepCanary extends Module {

   public static final String MARK = "EXPO_SWEEP_CANARY";

   public static final String NAME = "?" + MARK;

   public static final class Boom extends RuntimeException {
      private static final long serialVersionUID = 1L;

      Boom(String m) {
         super(m);
      }
   }

   public ExpoSweepCanary() {
      super(0L);
      this.K(NAME);
      this.B(Category.Misc);
   }

   @Override
   public void i(long var1) {
      throw new Boom(MARK + ":i(long)");
   }

   @Override
   public void A(long var1) {
      throw new Boom(MARK + ":A(long)");
   }

   @Override
   public void P(long var1) {
      throw new Boom(MARK + ":P(long)");
   }
}
