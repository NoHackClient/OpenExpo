package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.KnockbackEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.JumpResetKnockbackInvoker;
import Expo.event.invoker.JumpResetMoveInputInvoker;
import Expo.event.invoker.JumpResetPostUpdateInvoker;
import Expo.event.invoker.JumpResetWorldLoadInvoker;
import Expo.module.impl.combat.JumpReset;

public final class JumpResetBinder {
   private static final long a = 45363415832535L;

   public static void K(EventBus var2, JumpReset var3) {
      var2.R(var3, KnockbackEvent.class, 3, new JumpResetKnockbackInvoker(var3));
      var2.R(var3, MoveInputEvent.class, 3, new JumpResetMoveInputInvoker(var3));
      var2.R(var3, PostUpdateEvent.class, 3, new JumpResetPostUpdateInvoker(var3));
      var2.R(var3, WorldLoadEvent.class, 3, new JumpResetWorldLoadInvoker(var3));
   }

   private JumpResetBinder() {
   }
}
