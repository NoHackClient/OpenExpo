package Expo.event.events;

import net.minecraft.entity.Entity;

public class PreRenderEntityEvent extends StoppableEvent {
   public final Entity O;
   private static final long a = 122292175497410L;

   public PreRenderEntityEvent(int var1, byte var2, int var3, Entity var4) {
      super();
      this.O = var4;
   }}
