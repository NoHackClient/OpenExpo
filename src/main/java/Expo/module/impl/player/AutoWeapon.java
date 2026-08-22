package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoWeaponBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;











public class AutoWeapon extends Module implements EventSubscriber {
   private static final long a = 28055302659721L;
   private static final String[] c = new String[10];
   public static BooleanSetting fishingrodIsWeapon;
   private static final Minecraft E;
   private static final Object[] b = new Object[10];
   public static BooleanSetting axeIsWeapon;
   public static BooleanSetting stickIsWeapon;

   public final void x(long var1, EventBus var3) {
      AutoWeaponBinder.N(var3, this);
   }

   public static int M(long var0) {
      var0 = a ^ var0;
      int var2 = (int)((var0 ^ 95819851952059L) >>> 48);
      int var3 = (int)((var0 ^ 95819851952059L) << 16 >>> 48);
      int var5 = 0;
      double var6 = 0.0;

      for (int var8 = 0; var8 < InventoryPlayer.getHotbarSize(); var8++) {
         ItemStack var9 = E.thePlayer.inventory.getStackInSlot(var8);
         if (var9 != null) {
            double var10 = 0.0;
            double var12 = 0.0;
            if (var9.getItem() instanceof ItemSword) {
               var10 = ItemUtil.p((short)var2, var9, (char)var3);
               var12 = 0.4;
            } else if (axeIsWeapon.c() && var9.getItem() instanceof ItemAxe) {
               var10 = ItemUtil.p((short)var2, var9, (char)var3);
               var12 = 0.3;
            } else if (stickIsWeapon.c() && var9.getItem() == Items.stick) {
               var10 = ItemUtil.p((short)var2, var9, (char)var3);
               var12 = 0.2;
            } else if (fishingrodIsWeapon.c() && var9.getItem() == Items.fishing_rod) {
               var10 = ItemUtil.p((short)var2, var9, (char)var3);
               var12 = 0.1;
            }

            var10 += var12;
            if (var10 > var6) {
               var5 = var8;
            }
         }
      }

      return var5;
   }

   private static void a() {
      b[0] = "W\u00115f|3K";
      b[1] = long.class;
      c[1] = "java/lang/Long";
      b[2] = int.class;
      c[2] = "java/lang/Integer";
      b[3] = void.class;
      c[3] = "java/lang/Void";
      b[4] = "\u0005\u0002[u9\u0002\u0017";
      b[5] = "e\b'Y9\u0005R\u001f#St!E\u0014yO";
      b[6] = ".vB\u0010@K/";
      b[7] = ":\u007fZ}\u001e\u001a1pK2\u007f\u0014:{Oh";
      b[8] = "Tsd\b7\u0002\b=n\tX(n8a\u0011'^V::\u000e%nRhk\u0011(\u0014\u0016`3\u0003X\u0014\n`<\u0018eVS|\u0002Hi\t\u001fg}\u00037_\u001f\u0003";
      b[9] = "\u000bP=/UJR\u000b\u0001\u00054V_\u0002?7\t\u0014\u0006\u001e\u0001dF\u001dCP~&ISVa;lS]_\u001ep2\u0005];";
   }

   static {
      int var2 = 0;
      a();
      E = MinecraftRef.c((byte)var2,0L);
   }

   public void onPreUpdate(PreUpdateEvent var3) {


      if (E.objectMouseOver.entityHit != null && KeyBindUtil.V(E.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
         ItemUtil.P( M(93384294372710L));
      }
   }

   public AutoWeapon(long var1) {
      super(((a ^ (var1)) ^ 109000085513128L));
      // add code
      this.declare("AutoWeapon", Category.Player, "Switch to the best weapon in hotbar during combat");
      var1 = a ^ var1;
   }
   static {
      // add code
      axeIsWeapon = new BooleanSetting("Axe-is-weapon", false);
      stickIsWeapon = new BooleanSetting("Stick-is-weapon", false);
      fishingrodIsWeapon = new BooleanSetting("FishingRod-is-weapon", false);
   }
}
