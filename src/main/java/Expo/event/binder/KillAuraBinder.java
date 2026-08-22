package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.events.SetAnglesEvent;
import Expo.event.invoker.KillAuraPreMouseInputInvoker;
import Expo.event.invoker.KillAuraRender3DInvoker;
import Expo.event.invoker.KillAuraSetAnglesInvoker;
import Expo.module.impl.combat.KillAura;

public final class KillAuraBinder {
   private static final long a = 49257202388509L;

   private KillAuraBinder() {
   }

   public static void e(EventBus var2, KillAura var3) {
      var2.R(var3, PreMouseInputEvent.class, 4, new KillAuraPreMouseInputInvoker(var3));
      var2.R(var3, SetAnglesEvent.class, 3, new KillAuraSetAnglesInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new KillAuraRender3DInvoker(var3));
   }
}
