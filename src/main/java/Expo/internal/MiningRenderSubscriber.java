package Expo.internal;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.MiningRenderSubscriberBinder;
import Expo.event.events.Render3DEvent;











public class MiningRenderSubscriber implements EventSubscriber {
   private static final long a = 112537864652265L;

   static {
      a();
   }

   public final void x(long var1, EventBus var3) {
      MiningRenderSubscriberBinder.n(var3, this);
   }

   public void onRender3D(long var1, Render3DEvent var3) {
      var1 = a ^ var1;
      long var4 = var1 ^ 64953820480700L;
      MiningEngine.uq.v(var4);
   }

   private static void a() {
   }
}
