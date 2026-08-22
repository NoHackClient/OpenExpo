package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NoInteractBinder;
import Expo.event.events.PlayerRightClickEvent;
import Expo.module.Module;
import Expo.util.BlockUtil;











public class NoInteract extends Module implements EventSubscriber {
   private static final long a = 77666870766207L;

   private static void a() {
   }

   public NoInteract(long var1) {
      super(((a ^ (var1)) ^ 132296521410115L));
      // add code
      this.declare("NoInteract", Category.Player, "Prevent you from interacting with container blocks");
      var1 = a ^ var1;
   }

   public void onPlayerRightClick(PlayerRightClickEvent var1) {


      if (BlockUtil.S(var1.a$r2())) {
         var1.I(21307, 3074332907L);
      }
   }

   public final void x(long var1, EventBus var3) {
      NoInteractBinder.y(var3, this);
   }

   static {
      a();
   }}
