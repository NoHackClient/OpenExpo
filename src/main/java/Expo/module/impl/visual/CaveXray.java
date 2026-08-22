package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.BlockUtil;
import net.minecraft.util.BlockPos;











public class CaveXray extends Module {
   public static BooleanSetting reloadRenderer;
   private static long[] b;
   private static long a;
   public static PercentageSetting opacity;

   private void markBlockRangeForRenderUpdate(int var1) {
      BlockPos var2 = BlockUtil.Z();
      f.renderGlobal
         .markBlockRangeForRenderUpdate(
            var2.getX() - var1,
            var2.getY() - var1,
            var2.getZ() - var1,
            var2.getX() + var1,
            var2.getY() + var1,
            var2.getZ() + var1
         );
   }



   public static int L(long var0) {
      return 255 * opacity.k() / 100;
   }

   public void A(long var1) {
      if (reloadRenderer.c()) {
         f.renderGlobal.loadRenderers();
      } else {
         this.markBlockRangeForRenderUpdate(900);
      }
   }

   public CaveXray(long var1) {
      super(((a ^ (var1)) ^ 86093155091655L));
      // add code
      this.declare("CaveXray", Category.Visual, "Allows you to see structures underground (Only works with optifine)");
      var1 = a ^ var1;
   }

   public void i(long var1) {
      if (reloadRenderer.c()) {
         f.renderGlobal.loadRenderers();
      } else {
         this.markBlockRangeForRenderUpdate(900);
      }
   }

   static {
      a = 106916738106937L;
   }

   public String g(long var1) {
      return opacity.k() + "%";
   }


   static {
      // add code
      reloadRenderer = new BooleanSetting("Reload-renderer", false);
      opacity = new PercentageSetting("Opacity", 60);
   }
}
