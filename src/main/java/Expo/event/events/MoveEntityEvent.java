package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.Entity;

public class MoveEntityEvent extends Event {
   public final Entity D;
   private static final long a = 29704273218627L;

   public MoveEntityEvent(short var1, int var2, short var3, Entity var4) {
      super();
      this.D = var4;
   }}
