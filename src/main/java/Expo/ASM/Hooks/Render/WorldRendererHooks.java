package Expo.ASM.Hooks.Render;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.internal.accessor.WorldRendererAccessor;
import Expo.module.ModuleManager;
import Expo.module.impl.visual.CaveXray;
import Expo.util.render.ColorUtil;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.WorldRenderer;











public class WorldRendererHooks {
   private static final long a = 91233847165584L;

   public static void putColorMultiplier(WorldRenderer var0, float var1, float var2, float var3, int var4, boolean var5, IntBuffer var6, CallbackInfo var7) {
      int var14 = WorldRendererAccessor.t(var0, var4);
      int var15 = -1;
      if (!var5) {
         var15 = var6.get(var14);
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int var16 = (int)((var15 & 0xFF) * var1);
            int var17 = (int)((var15 >> 8 & 0xFF) * var2);
            int var18 = (int)((var15 >> 16 & 0xFF) * var3);
            var15 = var15 & 0xFF000000 | var18 << 16 | var17 << 8 | var16;
            if (ModuleManager.m != null && ModuleManager.m.o()) {
               var15 = ColorUtil.O(var16, var17, var18, CaveXray.L(0L));
            }
         } else {
            int var20 = (int)((var15 >> 24 & 0xFF) * var1);
            int var21 = (int)((var15 >> 16 & 0xFF) * var2);
            int var22 = (int)((var15 >> 8 & 0xFF) * var3);
            var15 = var15 & 0xFF | var20 << 24 | var21 << 16 | var22 << 8;
            if (ModuleManager.m != null && ModuleManager.m.o()) {
               var15 = ColorUtil.O(var20, var21, var22, CaveXray.L(0L));
            }
         }
      }

      var6.put(var14, var15);
      var7.cancel();
   }
}
