package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.impl.misc.AntiBot;

public final class AntiBotPreLivingUpdateInvoker implements EventInvoker {
   final AntiBot v;

   public void c(long var1, Object var3) {
      this.v.onPreLivingUpdate((PreLivingUpdateEvent)var3);
   }

   public AntiBotPreLivingUpdateInvoker(AntiBot var1) {
      this.v = var1;
   }
}
