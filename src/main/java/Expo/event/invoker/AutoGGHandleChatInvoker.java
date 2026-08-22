package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HandleChatEvent;
import Expo.module.impl.misc.AutoGG;

public final class AutoGGHandleChatInvoker implements EventInvoker {
   final AutoGG K;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 38136312673351L;
      this.K.onHandleChat(var4, (HandleChatEvent)var3);
   }

   public AutoGGHandleChatInvoker(AutoGG var1) {
      this.K = var1;
   }
}
