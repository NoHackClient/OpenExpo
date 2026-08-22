package Expo.internal.accessor;

import net.minecraft.entity.EntityLivingBase;

public final class EntityLivingBaseAccessor {
   private static Accessor f;

   public static float e(EntityLivingBase var0) {
      return Accessor.t(f, new Object[]{var0});
   }

   static {
      f = MethodAccessors.C(EntityLivingBase.class, new Class[0], new String[]{"getJumpUpwardsMotion", "getJumpUpwardsMotion"});
   }
}
