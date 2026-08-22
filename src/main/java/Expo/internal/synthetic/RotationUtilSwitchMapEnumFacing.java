package Expo.internal.synthetic;

import net.minecraft.util.EnumFacing;

public class RotationUtilSwitchMapEnumFacing {
   public static final int[] m = new int[EnumFacing.values().length];

   static {
      try {
         m[EnumFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         m[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         m[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         m[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         m[EnumFacing.EAST.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
