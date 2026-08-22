package Expo.internal.accessor;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;

public final class PlayerControllerAccessor {
   private static Accessor j;
   private static Accessor C;

   static {
      j = MethodAccessors.G(PlayerControllerMP.class, "syncCurrentPlayItem", "syncCurrentPlayItem", new Class[0]);
      C = MethodAccessors.G(PlayerControllerMP.class, "isHittingPosition", "isHittingPosition", new Class[]{BlockPos.class});
   }

   public static void Q(PlayerControllerMP var0) {
      Accessor.v(j, new Object[]{var0});
   }

   public static boolean E(PlayerControllerMP var0, BlockPos var1) {
      return Accessor.A(C, new Object[]{var0, var1});
   }
}
