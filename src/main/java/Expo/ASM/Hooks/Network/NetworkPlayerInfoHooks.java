package Expo.ASM.Hooks.Network;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.internal.accessor.NetworkPlayerInfoAccessor;
import Expo.module.impl.configuration.CustomCape;
import Expo.util.MinecraftRef;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;

public class NetworkPlayerInfoHooks {
   private static String b;
   private static long a;

   static {
      a = 137574316748705L;
      b = "NONE";
   }

   public static void getLocationCape(ResourceLocation var0, GameProfile var1, NetworkPlayerInfo var2, CallbackInfoReturnable<ResourceLocation> var3) {
      if (var1.getId().equals(MinecraftRef.c((byte)0,0L).thePlayer.getGameProfile().getId()) && !CustomCape.cape.R(b)) {
         var3.setReturnValue(CustomCape.d(0L));
         var3.cancel();
      } else {
         if (var0 == null) {
            NetworkPlayerInfoAccessor.W(var2);
         }
      }
   }
}
