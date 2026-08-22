package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import net.minecraft.entity.Entity;











public final class EntityAccessor {
   private static TypedValueStore c;
   private static TypedValueStore v;

   public static boolean F(Entity var0, long var1) {
      return v.n(var0);
   }

   static {
      v = FieldAccessors.X(Entity.class, "isInWeb", "isInWeb");
      c = FieldAccessors.X(Entity.class, "width", "width");
   }

   public static float X(Entity var0, long var1) {
      return c.V(var0);
   }



}
