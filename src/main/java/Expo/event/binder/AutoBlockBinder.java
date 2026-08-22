package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ModuleTagEvent;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.event.events.TickEvent;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.event.invoker.AutoBlockModuleTagInvoker;
import Expo.event.invoker.AutoBlockPostUpdateInvoker;
import Expo.event.invoker.AutoBlockPreMouseInputInvoker;
import Expo.event.invoker.AutoBlockReceivePacketInvoker;
import Expo.event.invoker.AutoBlockRedirectIsUsingItemInvoker;
import Expo.event.invoker.AutoBlockTickInvoker;
import Expo.event.invoker.AutoBlockUpdateCameraAndRenderInvoker;
import Expo.module.impl.combat.AutoBlock;

public final class AutoBlockBinder {
   private static final long a = 95045012906063L;

   public static void Z(int var0, EventBus var1, byte var2, AutoBlock var3) {
      var1.R(var3, RedirectIsUsingItemEvent.class, 3, new AutoBlockRedirectIsUsingItemInvoker(var3));
      var1.R(var3, ModuleTagEvent.class, 3, new AutoBlockModuleTagInvoker(var3));
      var1.R(var3, PreMouseInputEvent.class, 3, new AutoBlockPreMouseInputInvoker(var3));
      var1.R(var3, PostUpdateEvent.class, 3, new AutoBlockPostUpdateInvoker(var3));
      var1.R(var3, ReceivePacketEvent.class, 3, new AutoBlockReceivePacketInvoker(var3));
      var1.R(var3, UpdateCameraAndRenderEvent.class, 3, new AutoBlockUpdateCameraAndRenderInvoker(var3));
      var1.R(var3, TickEvent.class, 3, new AutoBlockTickInvoker(var3));
   }

   private AutoBlockBinder() {
   }
}
