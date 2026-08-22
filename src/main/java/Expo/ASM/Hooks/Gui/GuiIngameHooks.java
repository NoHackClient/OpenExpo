package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.module.Modules;
import Expo.module.impl.configuration.ScoreBoard;
import Expo.module.impl.world.Scaffold;
import Expo.util.render.VisualSpoofRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;

public class GuiIngameHooks {
   private static long a;
   private static long b;

   public static ItemStack updateTickGetCurrentItem(InventoryPlayer var0) {
      Scaffold var3 = Modules.J(Scaffold.class);
      if (var3 != null && var3.o() && Scaffold.fakeItem != null && Scaffold.fakeItem.c()) {
         int var4 = var3.q();
         if (var4 >= 0 && var4 < (int)b) {
            return var0.getStackInSlot(var4);
         }
      }

      return var0.getCurrentItem();
   }

   static {
      a = 56464679945669L;
      b = 1704908060083879945L;
   }

   public static void renderScoreboard(ScoreObjective var0, ScaledResolution var1, CallbackInfo var2) {
      if (!VisualSpoofRenderer.H()) {
         ScoreBoard.n(var0, var1);
         var2.cancel();
      }
   }
}
