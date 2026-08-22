package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ui.screen.MainMenuStyleScreen;
import Expo.util.MinecraftRef;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;


public class GuiMainMenuHooks {
   private static long a;



   static {
      a = 83392787864308L;
   }

   public static void onActionPerformed(GuiButton var0, CallbackInfo var1) {

      if (!"User".equals("Injection")) {
         if (var0.id == 20) {
            MinecraftRef.c((byte)0,0L).displayGuiScreen(new MainMenuStyleScreen(new GuiMainMenu()));
            var1.cancel();
         }
      }
   }

   public static void onInitGUI(List<GuiButton> var0, int var1, int var2) {
      if (!"User".equals("Injection")) {
         var0.add(
            new GuiButton(
               20,
               var1 - 44,
               var2 - 24,
               40,
               20,
               "Menu"
            )
         );
      }
   }


}
