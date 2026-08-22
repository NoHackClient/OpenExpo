package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreRenderEntityEvent;
import Expo.module.impl.visual.TeamInvisible;











public final class TeamInvisiblePreRenderEntityInvoker implements EventInvoker {
   final TeamInvisible Q;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 114058086611297L;
      this.Q.onPreRenderEntity((PreRenderEntityEvent)var3, var4);
   }

   public TeamInvisiblePreRenderEntityInvoker(TeamInvisible var1) {
      this.Q = var1;
   }
}
