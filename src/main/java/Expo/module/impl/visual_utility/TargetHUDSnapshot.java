package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TargetHUDCtorMarker;
import java.awt.Color;











public class TargetHUDSnapshot {
   private final float o;
   private final int c;
   private final String g;
   private final String G;
   private final Color k;
   private final float a;
   private final Color R;

   public static String k(TargetHUDSnapshot var0) {
      return var0.G;
   }

   public static float C(TargetHUDSnapshot var0) {
      return var0.o;
   }

   public static Color j(TargetHUDSnapshot var0) {
      return var0.k;
   }

   public static float m(TargetHUDSnapshot var0) {
      return var0.a;
   }

   public TargetHUDSnapshot(String var1, float var2, float var3, Color var4, Color var5, int var6, String var7, TargetHUDCtorMarker var8) {
      this(var1, var2, var3, var4, var5, var6, var7);
   }

   private TargetHUDSnapshot(String var1, float var2, float var3, Color var4, Color var5, int var6, String var7) {
      this.G = var1;
      this.a = var2;
      this.o = var3;
      this.k = var4;
      this.R = var5;
      this.c = var6;
      this.g = var7;
   }

   public static int H(TargetHUDSnapshot var0) {
      return var0.c;
   }

   public static Color B(TargetHUDSnapshot var0) {
      return var0.R;
   }
}
