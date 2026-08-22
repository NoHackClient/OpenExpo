package Expo.ui.studio;

import Expo.internal.synthetic.StudioModuleFrameCtorMarker;

public class StudioBindBadgeLayout {
   private final boolean d;
   private final float h;
   private final String p;
   private final float f;

   public static float Z(StudioBindBadgeLayout var0) {
      return var0.f;
   }

   public static float B(StudioBindBadgeLayout var0) {
      return var0.h;
   }

   private StudioBindBadgeLayout(boolean var1, float var2, float var3, String var4) {
      this.d = var1;
      this.f = var2;
      this.h = var3;
      this.p = var4;
   }

   public static boolean u(StudioBindBadgeLayout var0) {
      return var0.d;
   }

   public static String I(StudioBindBadgeLayout var0) {
      return var0.p;
   }

   public StudioBindBadgeLayout(boolean var1, float var2, float var3, String var4, StudioModuleFrameCtorMarker var5) {
      this(var1, var2, var3, var4);
   }
}
