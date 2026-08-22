package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.enums.DetectedCheat;
import Expo.internal.CheaterDetector;
import Expo.util.CheaterRegistry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public final class ExpoCommandCheaters extends Command {
   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"cheaters", "ac", "nocheaters"};
   }

   @Override
   public void h(long var1) {
      this.j(new String[0],0L);
   }

   @Override
   public void j(String[] var1, long var2) {
      Minecraft var4 = Minecraft.getMinecraft();

      if (var4 == null || var4.theWorld == null) {
         ExpoCommands.chat("§cNot in a world.");
         return;
      }

      Map<UUID, EntityPlayer> var5 = CheaterDetector.c;
      Map<UUID, CheaterRegistry> var6 = CheaterDetector.R;

      if (var5 == null || var6 == null) {
         ExpoCommands.chat("§cThe detector has not started yet.");
         return;
      }

      LinkedHashMap<UUID, CheaterRegistry> var7 = new LinkedHashMap<UUID, CheaterRegistry>();

      for (Map.Entry<UUID, EntityPlayer> var9 : var5.entrySet()) {
         if (var4.theWorld.getPlayerEntityByUUID(var9.getKey()) != null) {
            CheaterRegistry var10 = var6.get(var9.getKey());

            if (var10 != null && var10.M()) {
               var7.put(var9.getKey(), var10);
            }
         }
      }

      if (var7.isEmpty()) {
         ExpoCommands.chat("No cheaters found");
         return;
      }

      ExpoCommands.chat("§7Flagged players:");

      for (Map.Entry<UUID, CheaterRegistry> var12 : var7.entrySet()) {
         EntityPlayer var13 = var4.theWorld.getPlayerEntityByUUID(var12.getKey());

         if (var13 == null) {
            continue;
         }

         List<String> var14 = new ArrayList<String>();

         for (Map.Entry<DetectedCheat, Boolean> var16 : var12.getValue().e.entrySet()) {
            if (Boolean.TRUE.equals(var16.getValue())) {
               DetectedCheat var17 = var16.getKey();
               var14.add(var17.colorFormatCode + var17.name());
            }
         }

         ExpoCommands.chat("§7" + var13.getDisplayName().getFormattedText()
                           + "§8: " + String.join("§8, ", var14));
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList();
   }
}
