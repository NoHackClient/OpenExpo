package Expo.internal.accessor;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public final class BlockAccessor {
   private static TypedValueStore c;

   public static Block o(int var0, ItemBucket var1, short var2) {
      return (Block)c.v(var1);
   }

   static {
      c = FieldAccessors.X(ItemBucket.class, "isFull", "isFull");
   }
}
