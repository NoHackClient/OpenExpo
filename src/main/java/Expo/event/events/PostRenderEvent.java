package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.EntityLivingBase;











public class PostRenderEvent extends Event {
   public final EntityLivingBase z;
   private static final long a = 12438281193360L;

   public PostRenderEvent(EntityLivingBase var3) {
      super();
      this.z = var3;
   }}
