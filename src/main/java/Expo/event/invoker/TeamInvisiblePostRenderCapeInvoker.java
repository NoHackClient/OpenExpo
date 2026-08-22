package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostRenderCapeEvent;
import Expo.module.impl.visual.TeamInvisible;

public final class TeamInvisiblePostRenderCapeInvoker implements EventInvoker {
   final TeamInvisible Z;

   public TeamInvisiblePostRenderCapeInvoker(TeamInvisible var1) {
      this.Z = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = (var1 ^ 24560177989730L) >>> 8;
      int var6 = (int)((var1 ^ 24560177989730L) << 56 >>> 56);
      this.Z.onPostRenderCape(var4, (byte)var6, (PostRenderCapeEvent)var3);
   }
}
