package Expo.ASM.Hooks.World;

import Expo.ExpoClient;
import Expo.event.events.EntityJoinWorldEvent;
import net.minecraft.entity.Entity;











public class WorldHooks {
   private static final long a = 34519595986305L;

   public static void onEntityJoinWorld(Entity var0) {




      EntityJoinWorldEvent var8 = new EntityJoinWorldEvent(23653, var0, (byte)170, 4304924);
      ExpoClient.w.e(var8, 18670087776179L);
   }
}
