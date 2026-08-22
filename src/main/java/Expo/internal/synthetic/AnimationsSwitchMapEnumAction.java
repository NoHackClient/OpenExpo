package Expo.internal.synthetic;

import net.minecraft.item.EnumAction;











public class AnimationsSwitchMapEnumAction {
   public static final int[] j = new int[EnumAction.values().length];

   static {
      try {
         j[EnumAction.BLOCK.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }
   }
}
