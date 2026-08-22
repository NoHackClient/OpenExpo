package Expo.internal.accessor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

final class ItemAccessor {
   private static Accessor B;

   private ItemAccessor() {
   }

   static {
      B = MethodAccessors.G(Item.class, "func_179544_c_", "shouldCauseReequipAnimation", new Class[]{ItemStack.class, ItemStack.class, boolean.class});
   }

   static Accessor p() {
      return B;
   }
}
