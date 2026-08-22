package Expo.event.events;

import net.minecraft.entity.Entity;











public class EntityRenderStateEvent extends StoppableEvent {
   public final Entity k;
   private static final long a = 8090091801291L;

   public EntityRenderStateEvent(short var1, char var2, int var3, Entity var4) {
      super();
      this.k = var4;
   }}
