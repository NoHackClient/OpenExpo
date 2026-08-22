package Expo.internal.accessor;

import net.minecraft.client.renderer.WorldRenderer;











public final class WorldRendererAccessor {
   private static Accessor s;



   public static int t(WorldRenderer var0, int var1) {
      return Accessor.D(s, new Object[]{var0, var1});
   }

   static {
      s = MethodAccessors.G(WorldRenderer.class, "getColorIndex", "getColorIndex", new Class[]{int.class});
   }

}
