package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.Entity;











public class AttackTargetEntityEvent extends Event {
   public final Entity w;
   private static final long a = 1199300337788L;

   public AttackTargetEntityEvent(Entity var1) {
      super();
      this.w = var1;
   }}
