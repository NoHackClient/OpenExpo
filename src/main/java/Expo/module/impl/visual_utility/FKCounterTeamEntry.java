package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.FKCounterCtorMarker;

public class FKCounterTeamEntry {
   private final int F;
   private final int x;

   private FKCounterTeamEntry(int var1, int var2) {
      this.x = var1;
      this.F = var2;
   }

   public static int f(FKCounterTeamEntry var0) {
      return var0.b();
   }

   public FKCounterTeamEntry(int var1, int var2, FKCounterCtorMarker var3) {
      this(var1, var2);
   }

   private int b() {
      return this.x;
   }

   private int A() {
      return this.F;
   }

   public static int h(FKCounterTeamEntry var0) {
      return var0.A();
   }

   public static int O(FKCounterTeamEntry var0) {
      return var0.F;
   }

   public static int p(FKCounterTeamEntry var0) {
      return var0.x;
   }
}
