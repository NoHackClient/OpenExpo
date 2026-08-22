package Expo.event.events;

import net.minecraft.entity.Entity;

public class PreRenderModelBipedEvent extends StoppableEvent {
   private static final long a = 139085730622877L;
   public final Entity O;

   public PreRenderModelBipedEvent(Entity var3) {
      super();
      this.O = var3;
   }}
