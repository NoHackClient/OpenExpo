package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AntiBotBinder;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.util.MinecraftRef;
import com.mojang.authlib.GameProfile;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class AntiBot extends Module implements EventSubscriber {
   public static BooleanSetting tablistCheck;
   private static long a;
   private static Minecraft F;
   private static String[] c;
   private static CopyOnWriteArrayList<String> b;

   static {
      a = 67041402973696L;
      b = new CopyOnWriteArrayList<>();
      F = MinecraftRef.c((byte)0, 0L);
   }

   public void onPreLivingUpdate(PreLivingUpdateEvent var1) {
      CopyOnWriteArrayList var2 = new CopyOnWriteArrayList();

      for (NetworkPlayerInfo var4 : F.getNetHandler().getPlayerInfoMap()) {
         GameProfile var5 = var4.getGameProfile();
         if (var5.getId() != F.thePlayer.getUniqueID()) {
            String var6 = var5.getName();
            var2.add(var6);
         }
      }

      b = var2;
   }

   public static boolean T(short var0, EntityPlayer var3) {
      if (F.isSingleplayer()) {
         return false;
      } else if (tablistCheck.c() && !b.contains(var3.getName())) {
         return true;
      } else if (var3 instanceof EntityPlayerSP) {
         return false;
      } else {
         NetworkPlayerInfo var6 = F.getNetHandler().getPlayerInfo(var3.getName());
         if (var6 == null) {
            return true;
         } else if (var3.getName().startsWith("§k")) {
            return var3.isInvisible();
         } else if (var6.getResponseTime() < 1) {
            return true;
         } else {
            ScorePlayerTeam var7 = var6.getPlayerTeam();
            if (var7 == null) {
               return false;
            } else {
               return !var7.getTeamName().isEmpty() ? false : var7.getColorPrefix().equals("§c");
            }
         }
      }
   }

   public AntiBot(int var1, int var2, short var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 125956485188596L));
      this.declare("AntiBot", Category.Misc, "Detect bots");
   }

   public final void x(long var1, EventBus var3) {
      AntiBotBinder.t(var3, this);
   }

   static {
      tablistCheck = new BooleanSetting("Tablist-check", false);
   }
}
