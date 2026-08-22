package Expo.internal.accessor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;













public final class ItemOnEntitySwingAccessor {
   private static Accessor w;

   private ItemOnEntitySwingAccessor() {
   }

   public static Accessor S() {
      return w;
   }

   static {
      w = MethodAccessors.G(Item.class, "hitEntity", "onEntitySwing", new Class[]{EntityLivingBase.class, ItemStack.class});
   }



}
