package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.EntityLivingBase;











public class MoveEntityWithHeadingEvent extends Event {
   private static final long b = 82554486399303L;
   public final EntityLivingBase a;

   public MoveEntityWithHeadingEvent(short var1, EntityLivingBase var2, short var3, int var4) {
      super();
      this.a = var2;
   }}
