package Expo.internal.accessor;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;











public final class GuiContainerAccessor {
   private static TypedValueStore E;

   static {
      E = FieldAccessors.X(GuiContainer.class, "draggedStack", "draggedStack");
   }



   public static void S(GuiContainer var0, ItemStack var1) {
      E.d(var0, var1);
   }

}
