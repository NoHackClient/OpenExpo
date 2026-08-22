package Expo.ui.screen;

import Expo.ASM.Hooks.Gui.GuiEventHooks;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public final class GuiScreenButtonList {
   public static List<GuiButton> J(GuiScreen var0) {
      return GuiEventHooks.buttonList(var0);
   }

   private GuiScreenButtonList() {
   }
}
