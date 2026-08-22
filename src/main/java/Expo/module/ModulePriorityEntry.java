package Expo.module;

import Expo.internal.synthetic.ModulePriorityCtorMarker;

public class ModulePriorityEntry {
   public final Class<? extends PriorityModule> P;
   private static long b;
   public final int L;
   private boolean j;
   private static long a;

   public void N(boolean var1) {
      this.j = var1;
   }

   public boolean d() {
      return this.j;
   }

   static boolean z(ModulePriorityEntry var0, boolean var1) {
      return var0.j = var1;
   }

   ModulePriorityEntry(Class var1, int var2, ModulePriorityCtorMarker var3, long var4) {
      this((var1), (var2), ((a ^ (var4)) ^ 135283708948622L));
      var4 = a ^ var4;
   }

   private ModulePriorityEntry(Class var1, int var2, long var3) {
      this.j = (b) != 0;
      this.P = var1;
      this.L = var2;
   }

   static boolean r(ModulePriorityEntry var0) {
      return var0.j;
   }

   static {
      a = 45265588781549L;
   }
}
