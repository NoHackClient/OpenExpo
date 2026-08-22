package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.MiningEnginePreTickInvoker;
import Expo.internal.MiningEngine;

public final class MiningEngineBinder {
   private static final long a = 135542148782955L;

   public static void t(EventBus var2, MiningEngine var4) {
      var2.R(var4, PreTickEvent.class, 3, new MiningEnginePreTickInvoker(var4));
   }

   private MiningEngineBinder() {
   }
}
