package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.module.ModuleManager;
import Expo.module.impl.player.ChestStealer;
import Expo.util.BlockUtil;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;


public class GuiContainerHooks {
   private static final Minecraft A;

   public static void cancelDrawChest(CallbackInfo var0) {
      if ((A.currentScreen instanceof GuiChest || ChestStealer.y)
         && ModuleManager.q != null
         && ModuleManager.q.o()
         && ChestStealer.silent != null
         && ChestStealer.silent.c()
         && ChestStealer.chestIntegrityCheck != null
         && BlockUtil.o(ChestStealer.chestIntegrityCheck.c())) {
         var0.cancel();
      }
   }

   static {
      long var0 = 91512626691343L;
      int var2 = (int)((var0 ^ 28910806398147L) >>> 56);
      long var3 = (var0 ^ 28910806398147L) << 8 >>> 8;
      A = MinecraftRef.c((byte)var2,0L);
   }
}
