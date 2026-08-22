package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.ItemESPCtorMarker;
import net.minecraft.entity.item.EntityItem;

public class ItemESPEntry {
   private final int a;
   private final EntityItem d;

   public ItemESPEntry(EntityItem var1, int var2, ItemESPCtorMarker var3) {
      this(var1, var2);
   }

   public static EntityItem w(ItemESPEntry var0) {
      return var0.d;
   }

   private ItemESPEntry(EntityItem var1, int var2) {
      this.d = var1;
      this.a = var2;
   }

   public static int r(ItemESPEntry var0) {
      return var0.a;
   }
}
