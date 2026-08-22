package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public final class ExpoCommandIgn extends Command {
   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"ign", "name"};
   }

   @Override
   public void h(long var1) {
      this.j(new String[0],0L);
   }

   @Override
   public void j(String[] var1, long var2) {
      Minecraft var4 = Minecraft.getMinecraft();

      if (var4 == null || var4.thePlayer == null) {
         ExpoCommands.chat("§cNot in a world.");
         return;
      }

      String var5 = var4.thePlayer.getName();

      try {
         GuiScreen.setClipboardString(var5);
      } catch (Throwable var7) {
         ExpoCommands.chat("§7Your IGN is §f" + var5 + "§7 (clipboard unavailable: " + var7 + ")");
         return;
      }

      ExpoCommands.chat("§7Copied §f" + var5 + "§7 to the clipboard.");
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList();
   }
}
