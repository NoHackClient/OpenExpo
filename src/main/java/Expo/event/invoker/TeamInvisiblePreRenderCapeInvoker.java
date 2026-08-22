package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreRenderCapeEvent;
import Expo.module.impl.visual.TeamInvisible;











public final class TeamInvisiblePreRenderCapeInvoker implements EventInvoker {
   final TeamInvisible b;

   public TeamInvisiblePreRenderCapeInvoker(TeamInvisible var1) {
      this.b = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 102530270150799L;
      this.b.onPreRenderCape((PreRenderCapeEvent)var3, var4);
   }
}
