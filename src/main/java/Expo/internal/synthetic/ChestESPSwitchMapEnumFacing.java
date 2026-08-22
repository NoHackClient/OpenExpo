package Expo.internal.synthetic;

import net.minecraft.util.EnumFacing;

public class ChestESPSwitchMapEnumFacing {
   public static final int[] V = new int[EnumFacing.values().length];

   static {
      try {
         V[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         V[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         V[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         V[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
