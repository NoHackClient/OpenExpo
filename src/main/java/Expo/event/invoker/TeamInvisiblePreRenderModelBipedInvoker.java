package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreRenderModelBipedEvent;
import Expo.module.impl.visual.TeamInvisible;











public final class TeamInvisiblePreRenderModelBipedInvoker implements EventInvoker {
   final TeamInvisible c;

   public TeamInvisiblePreRenderModelBipedInvoker(TeamInvisible var1) {
      this.c = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 97761694109966L;
      this.c.onPreRenderModelBiped(var4, (PreRenderModelBipedEvent)var3);
   }
}
