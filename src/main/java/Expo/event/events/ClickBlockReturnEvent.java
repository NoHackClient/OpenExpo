package Expo.event.events;

import Expo.event.Event;
import net.minecraft.util.BlockPos;











public class ClickBlockReturnEvent extends Event {
   public final BlockPos b;
   private static final long a = 32610292643119L;

   public ClickBlockReturnEvent(BlockPos var3) {
      super();
      this.b = var3;
   }}
