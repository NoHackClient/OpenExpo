package Expo.internal.accessor;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;











public final class EnchantmentHelperAccessorImpl {
   private static Accessor N;

   static {
      N = MethodAccessors.C(EnchantmentHelper.class, new Class[]{ItemStack.class, EnumCreatureAttribute.class}, new String[]{"getModifierForCreature", "getModifierForCreature"});
   }

   public static float P(ItemStack var0, EnumCreatureAttribute var1) {
      return Accessor.t(N, new Object[]{var0, var1});
   }



}
