package Expo.internal.accessor;

import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.item.ItemStack;











public final class RenderEntityItemAccessorImpl {
   private static Accessor O;

   static {
      O = MethodAccessors.G(RenderEntityItem.class, "func_177078_a", "func_177078_a", new Class[]{ItemStack.class});
   }



   public static int x(RenderEntityItem var0, ItemStack var1) {
      return Accessor.D(O, new Object[]{var0, var1});
   }

}
