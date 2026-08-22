package Expo.internal.accessor;

import net.minecraft.entity.projectile.EntityArrow;

public final class EntityArrowAccessor {
   private static TypedValueStore U;

   static {
      U = FieldAccessors.X(EntityArrow.class, "inGround", "inGround");
   }

   public static boolean E(long var0, EntityArrow var2) {
      return U.n(var2);
   }
}
