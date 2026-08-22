package Expo.command.impl;

import Expo.ExpoClient;
import Expo.command.Command;
import Expo.command.ExpoCommands;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;











// add code
public final class ExpoCommandPartySpam extends Command {

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"partyspam", "ps"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§eStart or stop party invite spamming someone");
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  .partyspam <name>");
      ExpoCommands.chat("§7Target: §f" + (ExpoClient.I == null ? "none" : ExpoClient.I));
      ExpoCommands.chat("§8The slot is set, but nothing in this build reads it: Expo/AZ.I has "
                        + "1 write (its own clinit) and 0 reads jar-wide, so the sender that "
                        + "consumed it is native and was not recovered.");
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = ExpoClient.I;

      if (var4 == null) {
         ExpoClient.I = var1[0];
         ExpoCommands.chat("§aNow party spamming §f" + ExpoClient.I);
      } else {
         ExpoClient.I = null;
         ExpoCommands.chat("§cStopped party spamming §f" + var4);
      }

      ExpoCommands.chat("§8Nothing in this build reads Expo/AZ.I, so no invite is actually "
                        + "sent -- the sender is native and was not recovered.");
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         Minecraft var6 = Minecraft.getMinecraft();

         if (var6 != null && var6.theWorld != null) {
            for (EntityPlayer var8 : var6.theWorld.playerEntities) {
               var5.add(var8.getName());
            }
         }
      }

      return var5;
   }
}
