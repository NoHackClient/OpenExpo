package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;

public class PickUpItemEvent extends Event {
   private static final long a = 128454492582759L;
   public final EntityPlayer F;
   public final EntityItem P;

   public PickUpItemEvent(EntityItem var1, EntityPlayer var2) {
      super();
      this.P = var1;
      this.F = var2;
   }}
