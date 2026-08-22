package Expo.ASM.Hooks.Entity;

import Expo.ExpoClient;
import Expo.event.events.PickUpItemEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;











public class EntityItemHooks {
   private static final long a = 17550383036311L;

   public static void onPickUpItem(EntityItem var0, EntityPlayer var1) {

      PickUpItemEvent var8 = new PickUpItemEvent(var0, var1);
      ExpoClient.w.e(var8, 18670087776179L);
   }
}
