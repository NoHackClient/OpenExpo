package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TracersCtorMarker;

public class TracersFilterFlags {
   private final boolean M;
   private final boolean A;
   private final boolean T;
   private final boolean Y;
   private final boolean p;
   private final boolean y;
   private final boolean Q;
   private final boolean b;

   public static boolean c(TracersFilterFlags var0) {
      return var0.T;
   }

   public static boolean g(TracersFilterFlags var0) {
      return var0.A;
   }

   public static boolean l(TracersFilterFlags var0) {
      return var0.b;
   }

   public static boolean M(TracersFilterFlags var0) {
      return var0.p;
   }

   private TracersFilterFlags(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      this.A = var1;
      this.Q = var2;
      this.T = var3;
      this.M = var4;
      this.p = var5;
      this.y = var6;
      this.b = var7;
      this.Y = var8;
   }

   public static boolean h(TracersFilterFlags var0) {
      return var0.y;
   }

   public static boolean I(TracersFilterFlags var0) {
      return var0.Q;
   }

   public TracersFilterFlags(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, TracersCtorMarker var9) {
      this(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static boolean E(TracersFilterFlags var0) {
      return var0.M;
   }

   public static boolean d(TracersFilterFlags var0) {
      return var0.Y;
   }
}
