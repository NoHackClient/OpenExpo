package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostRenderModelBipedEvent;
import Expo.module.impl.visual.TeamInvisible;

public final class TeamInvisiblePostRenderModelBipedInvoker implements EventInvoker {
   final TeamInvisible R;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 47655161708655L;
      this.R.onPostRenderModelBiped((PostRenderModelBipedEvent)var3, var4);
   }

   public TeamInvisiblePostRenderModelBipedInvoker(TeamInvisible var1) {
      this.R = var1;
   }
}
