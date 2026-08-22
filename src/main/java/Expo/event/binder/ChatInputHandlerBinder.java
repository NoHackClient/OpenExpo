package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.GuiChatKeyTypedEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.ChatInputHandlerGuiChatKeyTypedInvoker;
import Expo.event.invoker.ChatInputHandlerPostTickInvoker;
import Expo.internal.ChatInputHandler;

public final class ChatInputHandlerBinder {
   private static final long a = 136951807088222L;

   public static void A(EventBus var0, ChatInputHandler var3) {
      var0.R(var3, GuiChatKeyTypedEvent.class, 3, new ChatInputHandlerGuiChatKeyTypedInvoker(var3));
      var0.R(var3, PostTickEvent.class, 3, new ChatInputHandlerPostTickInvoker(var3));
   }

   private ChatInputHandlerBinder() {
   }
}
