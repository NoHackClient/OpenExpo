package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.PlayerGetNameEvent;
import net.minecraft.client.network.NetworkPlayerInfo;











public class GuiPlayerTabOverlayHooks {
   private static final long a = 110527703412519L;

   public static void onPlayerGetName(NetworkPlayerInfo var0, CallbackInfoReturnable<String> var1) {

      PlayerGetNameEvent var8 = new PlayerGetNameEvent(var0, (String)var1.getReturnValue());
      ExpoClient.w.e(var8, 18670087776179L);
      var1.setReturnValue(var8.d());
   }
}
