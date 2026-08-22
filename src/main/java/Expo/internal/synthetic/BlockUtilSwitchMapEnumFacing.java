package Expo.internal.synthetic;

import net.minecraft.util.EnumFacing;











public class BlockUtilSwitchMapEnumFacing {
   public static final int[] b = new int[EnumFacing.values().length];

   static {
      try {
         b[EnumFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         b[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         b[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         b[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         b[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
