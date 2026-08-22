package Expo.module.impl.player;

import Expo.module.Category;

import Expo.module.Module;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.misc.AntiBot;
import Expo.setting.settings.BooleanSetting;
import Expo.util.MinecraftRef;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class GhostHand extends Module {
   public static BooleanSetting blacklistEnemy;
   public static BooleanSetting playersOnly;
   private static final long a = 65639399934920L;
   public static BooleanSetting disableWhileHoldingSword;
   private static final Minecraft N;
   public static BooleanSetting teammatesOnly;
   public static BooleanSetting toolsOnly;

   public static void T(List<Entity> var0) {
      var0.removeIf(GhostHand::w);
   }

   public GhostHand(long var1) {
      super(((a ^ (var1)) ^ 68687997558019L));
      this.declare("GhostHand", Category.Player, "Allows you to interact through entity");
      var1 = a ^ var1;
   }

   private static boolean w(Entity var0) {
      return !(var0 instanceof EntityLivingBase)
            || !blacklistEnemy.c()
            || (!(var0 instanceof EntityPlayer) || AntiBot.T((short)0, (EntityPlayer)var0) || Teams.g(0L, var0)) && !Teams.Y(var0)
         ? (!disableWhileHoldingSword.c() || N.thePlayer.getHeldItem() == null || !(N.thePlayer.getHeldItem().getItem() instanceof ItemSword))
            && (!toolsOnly.c() || N.thePlayer.getHeldItem() != null && N.thePlayer.getHeldItem().getItem() instanceof ItemTool)
            && (!playersOnly.c() || var0 instanceof EntityPlayer)
            && (!teammatesOnly.c() || var0 instanceof EntityLivingBase && Teams.g(0L, var0))
         : false;
   }

   static {
      int var2 = 0;
      N = MinecraftRef.c((byte)var2,0L);
   }
   static {
      teammatesOnly = new BooleanSetting("Teammates-only", true);
      disableWhileHoldingSword = new BooleanSetting("Disable-while-holding-sword", true);
      toolsOnly = new BooleanSetting("Tools-only", false);
      playersOnly = new BooleanSetting("Players-only", true);
      blacklistEnemy = new BooleanSetting("Blacklist-enemy", true);
   }
}
