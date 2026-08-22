package Expo.internal.accessor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;











final class ItemStackAccessor {
   private static Accessor M;
   private static Accessor S;



   static {
      M = MethodAccessors.O(Item.class, new Class[]{ItemStack.class}, new String[]{"getColorFromDamage", "getMetadata"});
      S = MethodAccessors.O(Item.class, new Class[]{int.class}, new String[]{"getMetadata", "getMetadata"});
   }

   static Accessor q() {
      return S;
   }

   private ItemStackAccessor() {
   }

   static Accessor j() {
      return M;
   }

}
