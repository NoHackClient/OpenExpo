package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.AntiFireballPreMouseInputInvoker;
import Expo.event.invoker.AntiFireballPreTickInvoker;
import Expo.event.invoker.AntiFireballWorldLoadInvoker;
import Expo.module.impl.combat.AntiFireball;

public final class AntiFireballBinder {
   private static final long a = 96382948686270L;

   public static void r(EventBus var2, AntiFireball var3) {
      var2.R(var3, PreTickEvent.class, 3, new AntiFireballPreTickInvoker(var3));
      var2.R(var3, PreMouseInputEvent.class, 3, new AntiFireballPreMouseInputInvoker(var3));
      var2.R(var3, WorldLoadEvent.class, 3, new AntiFireballWorldLoadInvoker(var3));
   }

   private AntiFireballBinder() {
   }
}
