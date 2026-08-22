package Expo.internal.accessor;

import net.minecraft.entity.Entity;

public final class EntityCanRiderInteractAccessor {
   private static Accessor H;

   static {
      H = MethodAccessors.G(Entity.class, "func_184228_n", "canRiderInteract", new Class[0]);
   }

   private EntityCanRiderInteractAccessor() {
   }

   public static Accessor C() {
      return H;
   }
}
