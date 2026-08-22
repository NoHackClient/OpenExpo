package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.ItemTagsCtorMarker;
import net.minecraft.entity.item.EntityItem;

public class ItemTagsEntry {
   private final EntityItem f;
   private final int y;
   private final String T;

   public ItemTagsEntry(EntityItem var1, String var2, int var3, ItemTagsCtorMarker var4) {
      this(var1, var2, var3);
   }

   public static int z(ItemTagsEntry var0) {
      return var0.y;
   }

   private ItemTagsEntry(EntityItem var1, String var2, int var3) {
      this.f = var1;
      this.T = var2;
      this.y = var3;
   }

   public static String T(ItemTagsEntry var0) {
      return var0.T;
   }

   public static EntityItem H(ItemTagsEntry var0) {
      return var0.f;
   }
}
