package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.ItemTags;

public final class ItemTagsRender3DInvoker implements EventInvoker {
   final ItemTags Y;

   public ItemTagsRender3DInvoker(ItemTags var1) {
      this.Y = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 23438487098872L;
      this.Y.onRender3D(var4, (Render3DEvent)var3);
   }
}
