package Expo.ASM.Network;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformNetworkPlayerInfo extends TransformerBase {
   private static long d;

   static {
      d = 69769816458693L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "()" + SrgNames.X("net/minecraft/util/ResourceLocation"),
         (var1x, var2x) -> {
            return TransformerBase.M(
               var2x,
               Type.getReturnType(var2x.desc),
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.P(
                     var1xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/util/ResourceLocation"), "locationCape", "locationCape", "f"),
                     SrgNames.X("net/minecraft/util/ResourceLocation")
                  );
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.P(
                     var1xx,
                     var1.name,
                     TransformerBase.j(var1, "Lcom/mojang/authlib/GameProfile;", "gameProfile", "gameProfile", "a"),
                     "Lcom/mojang/authlib/GameProfile;"
                  );
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     Q,
                     "getLocationCape",
                     "("
                        + SrgNames.X("net/minecraft/util/ResourceLocation")
                        + "Lcom/mojang/authlib/GameProfile;"
                        + SrgNames.X("net/minecraft/client/network/NetworkPlayerInfo")
                        + I
                        + ")V"
                  );
               }
            );
         },
         "getLocationCape",
         "getLocationCape"
      );
   }

   public TransformNetworkPlayerInfo() {
      super("net/minecraft/client/network/NetworkPlayerInfo");
   }
}
