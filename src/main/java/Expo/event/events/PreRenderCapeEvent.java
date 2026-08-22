package Expo.event.events;

import net.minecraft.client.entity.AbstractClientPlayer;











public class PreRenderCapeEvent extends StoppableEvent {
   public final AbstractClientPlayer F;
   private static final long a = 35671248390426L;

   public PreRenderCapeEvent(short var1, int var2, short var3, AbstractClientPlayer var4) {
      super();
      this.F = var4;
   }}
