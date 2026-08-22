package Expo.internal.synthetic;

import net.minecraft.util.EnumFacing.Axis;

public class TrajectoriesSwitchMapAxis {
   public static final int[] t = new int[Axis.values().length];

   static {
      try {
         t[Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         t[Axis.Y.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         t[Axis.Z.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
