package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.GuiChatKeyTypedEvent;
import Expo.internal.ChatInputHandler;











public final class ChatInputHandlerGuiChatKeyTypedInvoker implements EventInvoker {
   final ChatInputHandler E;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 11573287068515L) >>> 32);
      long var5 = (var1 ^ 11573287068515L) << 32 >>> 32;
      this.E.onGuiChatKeyTyped((GuiChatKeyTypedEvent)var3, var4, var5);
   }

   public ChatInputHandlerGuiChatKeyTypedInvoker(ChatInputHandler var1) {
      this.E = var1;
   }
}
