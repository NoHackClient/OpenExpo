package Expo.event.binder;

import Expo.ExpoClient;
import Expo.event.EventBus;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.invoker.ExpoClientEntityJoinWorldInvoker;
import Expo.event.invoker.ExpoClientPostTickInvoker;
import Expo.event.invoker.ExpoClientPreMouseInputInvoker;
import Expo.event.invoker.ExpoClientReceivePacketInvoker;
import Expo.event.invoker.ExpoClientSetKeyBindStateInvoker;
import Expo.event.invoker.j_2;
import Expo.event.invoker.oX;

public final class ExpoClientBinder {
   private static final long a = 111088129942612L;

   private ExpoClientBinder() {
   }

   public static void C(EventBus var2, ExpoClient var3) {
      var2.R(var3, SetKeyBindStateEvent.class, 3, new ExpoClientSetKeyBindStateInvoker(var3));
      var2.R(var3, PostTickEvent.class, 3, new ExpoClientPostTickInvoker(var3));
      var2.R(var3, PreUpdateEvent.class, 3, new oX(var3));
      var2.R(var3, PreMouseInputEvent.class, 3, new ExpoClientPreMouseInputInvoker(var3));
      var2.R(var3, PreUpdateEvent.class, 3, new j_2(var3));
      var2.R(var3, EntityJoinWorldEvent.class, 3, new ExpoClientEntityJoinWorldInvoker(var3));
      var2.R(var3, ReceivePacketEvent.class, 3, new ExpoClientReceivePacketInvoker(var3));
   }
}
