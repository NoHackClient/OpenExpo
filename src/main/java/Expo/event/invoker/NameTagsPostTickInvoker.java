package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.NameTags;

public final class NameTagsPostTickInvoker implements EventInvoker {
   final NameTags p;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 102083023309324L;
      this.p.onPostTick((PostTickEvent)var3, var4);
   }

   public NameTagsPostTickInvoker(NameTags var1) {
      this.p = var1;
   }
}
