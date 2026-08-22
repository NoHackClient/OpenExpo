package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.EntityLivingBase;











public class PreRenderEvent extends Event {
   public final EntityLivingBase B;
   private static final long a = 53198000439600L;

   public PreRenderEvent(EntityLivingBase var3) {
      super();
      this.B = var3;
   }}
