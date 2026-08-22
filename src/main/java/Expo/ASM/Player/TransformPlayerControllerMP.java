package Expo.ASM.Player;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformPlayerControllerMP extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/util/BlockPos") + SrgNames.X("net/minecraft/util/EnumFacing") + ")Z",
         (var0, var1x) -> {
            boolean var4x = TransformerBase.u(
               var1x,
               Type.BOOLEAN_TYPE,
               true,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.J(var1xx, var1x);
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     R,
                     "PlayerControllerMP$onDamageBlock",
                     "("
                        + SrgNames.X("net/minecraft/util/BlockPos")
                        + SrgNames.X("net/minecraft/util/EnumFacing")
                        + SrgNames.X("net/minecraft/client/multiplayer/PlayerControllerMP")
                        + I
                        + ")V"
                  );
               }
            );
            return var4x
               | BytecodeHelper.Q(
                  var1x, BytecodeHelper.s("net/minecraft/client/multiplayer/PlayerControllerMP", "()V", "syncCurrentPlayItem", "syncCurrentPlayItem"), var1xx -> {
                     long var2xx = d ^ 44743637328558L;
                     int var4xx = BytecodeHelper.S(var1x, var1xx);
                     BytecodeHelper.I(var1xx, var4xx);
                     BytecodeHelper.Y(var1xx, R, "PlayerControllerMP$onDamageBlockAfterSync", "(" + I + ")V");
                     BytecodeHelper.E(var1xx, var4xx, Type.BOOLEAN_TYPE);
                  }
               );
         },
         "onPlayerDamageBlock",
         "onPlayerDamageBlock"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/util/BlockPos") + SrgNames.X("net/minecraft/util/EnumFacing") + ")Z",
         (var0, var1x) -> {
            return TransformerBase.M(
               var1x,
               Type.BOOLEAN_TYPE,
               (var1xx, var2xx) -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.n(var1xx, var1x, 1);
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     R,
                     "PlayerControllerMP$clickBlockReturn",
                     "("
                        + SrgNames.X("net/minecraft/util/BlockPos")
                        + SrgNames.X("net/minecraft/util/EnumFacing")
                        + SrgNames.X("net/minecraft/client/multiplayer/PlayerControllerMP")
                        + I
                        + ")V"
                  );
               }
            );
         },
         "clickBlock",
         "clickBlock"
      );
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + ")V", (var0, var1x) -> {
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 35649869710982L;
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, R, "PlayerControllerMP$onPreStoppedUsingItem", "(" + z + ")V");
         });
         return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 101209808791597L;
            BytecodeHelper.Y(var0x, R, "PlayerControllerMP$onPostStoppedUsingItem", "()V");
         });
      }, "onStoppedUsingItem", "onStoppedUsingItem");
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + SrgNames.X("net/minecraft/entity/Entity") + ")V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 1);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, R, "PlayerControllerMP$onAttackEntity", "(" + SrgNames.X("net/minecraft/entity/Entity") + z + ")V");
         });
      }, "attackEntity", "attackEntity");
      return var4
         | BytecodeHelper.t(
            var1,
            "("
               + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP")
               + SrgNames.X("net/minecraft/client/multiplayer/WorldClient")
               + SrgNames.X("net/minecraft/item/ItemStack")
               + SrgNames.X("net/minecraft/util/BlockPos")
               + SrgNames.X("net/minecraft/util/EnumFacing")
               + SrgNames.X("net/minecraft/util/Vec3")
               + ")Z",
            (var0, var1x) -> {
               return TransformerBase.u(
                  var1x,
                  Type.BOOLEAN_TYPE,
                  true,
                  true,
                  (var1xx, var2xx) -> {
                     BytecodeHelper.n(var1xx, var1x, 1);
                     BytecodeHelper.n(var1xx, var1x, 2);
                     BytecodeHelper.n(var1xx, var1x, 3);
                     BytecodeHelper.n(var1xx, var1x, 4);
                     BytecodeHelper.n(var1xx, var1x, 5);
                     BytecodeHelper.I(var1xx, var2xx);
                     BytecodeHelper.Y(
                        var1xx,
                        R,
                        "PlayerControllerMP$onPlayerRightClick",
                        "("
                           + SrgNames.X("net/minecraft/client/multiplayer/WorldClient")
                           + SrgNames.X("net/minecraft/item/ItemStack")
                           + SrgNames.X("net/minecraft/util/BlockPos")
                           + SrgNames.X("net/minecraft/util/EnumFacing")
                           + SrgNames.X("net/minecraft/util/Vec3")
                           + I
                           + ")V"
                     );
                  }
               );
            },
            "onPlayerRightClick",
            "onPlayerRightClick"
         );
   }

   static {
      d = 109293909667439L;
   }

   public TransformPlayerControllerMP() {
      super("net/minecraft/client/multiplayer/PlayerControllerMP");
   }
}
