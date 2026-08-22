package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TracersCtorMarker;
import net.minecraft.entity.EntityLivingBase;

public class TracersTarget {
   private final int O;
   private final EntityLivingBase K;

   public TracersTarget(EntityLivingBase var1, int var2, TracersCtorMarker var3) {
      this(var1, var2);
   }

   public static EntityLivingBase L(TracersTarget var0) {
      return var0.K;
   }

   public static int Y(TracersTarget var0) {
      return var0.O;
   }

   private TracersTarget(EntityLivingBase var1, int var2) {
      this.K = var1;
      this.O = var2;
   }
}
