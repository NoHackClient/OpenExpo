package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.Entity;

public class EntityJoinWorldEvent extends Event {
   private static final long a = 65611262055503L;
   public final Entity H;

   public EntityJoinWorldEvent(int var1, Entity var2, byte var3, int var4) {
      super();
      this.H = var2;
   }}
