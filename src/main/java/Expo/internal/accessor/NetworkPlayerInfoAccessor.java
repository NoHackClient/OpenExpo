package Expo.internal.accessor;

import net.minecraft.client.network.NetworkPlayerInfo;

public final class NetworkPlayerInfoAccessor {
   private static Accessor p;

   public static void W(NetworkPlayerInfo var0) {
      Accessor.v(p, new Object[]{var0});
   }

   static {
      p = MethodAccessors.G(NetworkPlayerInfo.class, "loadPlayerTextures", "loadPlayerTextures", new Class[0]);
   }
}
