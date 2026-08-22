package Expo.internal.accessor;

import net.minecraft.client.renderer.EntityRenderer;

public final class EntityRendererAccessor {
   private static Accessor k;

   public static void k(EntityRenderer var0, float var1, int var2) {
      Accessor.v(k, new Object[]{var0, var1, var2});
   }

   static {
      k = MethodAccessors.G(EntityRenderer.class, "setupCameraTransform", "setupCameraTransform", new Class[]{float.class, int.class});
   }
}
