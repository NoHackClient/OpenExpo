package Expo.module;

public class PriorityModule extends Module {
   private static final long ab = 76245502448272L;

   public PriorityModule(long var1, char var3) {
      super((((((var1) << 16) | (((long)((var3)) << 48) >>> 48)) ^ ab) ^ 124237609442652L));
   }

   public void T(boolean var1) {
      ModulePriority.U((Class<? extends PriorityModule>)this.getClass(), var1);
   }

   public boolean Y() {
      return ModulePriority.c((Class<? extends PriorityModule>)this.getClass());
   }}
