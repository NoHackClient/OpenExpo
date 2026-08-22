package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HandleChatEvent;
import Expo.module.impl.visual.KillEffect;











public final class KillEffectHandleChatInvoker implements EventInvoker {
   final KillEffect v;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 103267610312446L;
      this.v.onHandleChat(var4, (HandleChatEvent)var3);
   }

   public KillEffectHandleChatInvoker(KillEffect var1) {
      this.v = var1;
   }
}
