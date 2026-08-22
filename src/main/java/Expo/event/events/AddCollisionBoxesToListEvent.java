package Expo.event.events;

import Expo.event.Event;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class AddCollisionBoxesToListEvent extends Event {
   private static final long a = 129494685460453L;
   public final BlockPos E;
   public final Block B;

   public AddCollisionBoxesToListEvent(Block var1, BlockPos var2) {
      super();
      this.B = var1;
      this.E = var2;
   }}
