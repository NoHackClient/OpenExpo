package Expo.ASM.Hooks;

import Expo.module.ModuleManager;
import java.util.BitSet;
import net.minecraft.util.BlockPos;

public class VisGraphHooks {
   private static int getIndex(BlockPos var0) {
      return getIndex(var0.getX() & 15, var0.getY() & 15, var0.getZ() & 15);
   }

   private static int getIndex(int var0, int var1, int var2) {
      return var0 << 0 | var1 << 8 | var2 << 4;
   }

   public static void func_178606_a(BlockPos var0, BitSet var1, CallbackInfo var2) {
      if (ModuleManager.m != null && ModuleManager.m.o() || ModuleManager.h != null && ModuleManager.h.o()) {
         var1.set(getIndex(var0), true);
         var2.cancel();
      }
   }
}
