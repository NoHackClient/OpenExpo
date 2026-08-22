package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;











public class LivingDeathEvent extends Event {
   public final EntityLivingBase p;
   public final DamageSource M;
   private static final long a = 57793467811553L;

   public LivingDeathEvent(int var1, DamageSource var2, char var3, short var4, EntityLivingBase var5) {
      super();
      this.M = var2;
      this.p = var5;
   }}
