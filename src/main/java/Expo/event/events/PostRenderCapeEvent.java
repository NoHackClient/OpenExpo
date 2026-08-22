package Expo.event.events;

import net.minecraft.client.entity.AbstractClientPlayer;











public class PostRenderCapeEvent extends StoppableEvent {
   private static final long a = 120196592877995L;
   public final AbstractClientPlayer U;

   public PostRenderCapeEvent(AbstractClientPlayer var3) {
      super();
      this.U = var3;
   }}
