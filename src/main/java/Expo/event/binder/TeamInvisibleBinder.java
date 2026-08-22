package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.EntityRenderStateEvent;
import Expo.event.events.PostRenderCapeEvent;
import Expo.event.events.PostRenderModelBipedEvent;
import Expo.event.events.PreRenderCapeEvent;
import Expo.event.events.PreRenderModelBipedEvent;
import Expo.event.events.PreRenderEntityEvent;
import Expo.event.invoker.TeamInvisiblePreRenderEntityInvoker;
import Expo.event.invoker.TeamInvisibleEntityRenderStateInvoker;
import Expo.event.invoker.TeamInvisiblePostRenderCapeInvoker;
import Expo.event.invoker.TeamInvisiblePostRenderModelBipedInvoker;
import Expo.event.invoker.TeamInvisiblePreRenderCapeInvoker;
import Expo.event.invoker.TeamInvisiblePreRenderModelBipedInvoker;
import Expo.module.impl.visual.TeamInvisible;











public final class TeamInvisibleBinder {
   private static final long a = 107658549533327L;

   private TeamInvisibleBinder() {
   }

   public static void y(EventBus var2, TeamInvisible var3) {
      var2.R(var3, PreRenderEntityEvent.class, 3, new TeamInvisiblePreRenderEntityInvoker(var3));
      var2.R(var3, EntityRenderStateEvent.class, 3, new TeamInvisibleEntityRenderStateInvoker(var3));
      var2.R(var3, PreRenderModelBipedEvent.class, 3, new TeamInvisiblePreRenderModelBipedInvoker(var3));
      var2.R(var3, PostRenderModelBipedEvent.class, 3, new TeamInvisiblePostRenderModelBipedInvoker(var3));
      var2.R(var3, PreRenderCapeEvent.class, 3, new TeamInvisiblePreRenderCapeInvoker(var3));
      var2.R(var3, PostRenderCapeEvent.class, 3, new TeamInvisiblePostRenderCapeInvoker(var3));
   }
}
