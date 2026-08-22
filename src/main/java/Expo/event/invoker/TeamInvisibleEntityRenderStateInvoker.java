package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityRenderStateEvent;
import Expo.module.impl.visual.TeamInvisible;

public final class TeamInvisibleEntityRenderStateInvoker implements EventInvoker {
   final TeamInvisible o;

   public TeamInvisibleEntityRenderStateInvoker(TeamInvisible var1) {
      this.o = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 115658039802805L) >>> 48);
      int var5 = (int)((var1 ^ 115658039802805L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 115658039802805L) << 48 >>> 48);
      this.o.onEntityRenderState((char)var4, var5, (EntityRenderStateEvent)var3, var6);
   }
}
