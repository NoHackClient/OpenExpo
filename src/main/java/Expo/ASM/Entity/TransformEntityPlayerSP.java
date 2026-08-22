package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;











public class TransformEntityPlayerSP extends TransformerBase {
   private static long d;

   public TransformEntityPlayerSP() {
      super("net/minecraft/client/entity/EntityPlayerSP");
   }




   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, m, "EntityPlayerSP$onCloseScreen", "()V");
         });
      }, "closeScreenAndDropStack", "closeScreenAndDropStack");
      var4 |= BytecodeHelper.t(
         var1,
         "()V",
         (var0, var1x) -> {
            boolean var4x = TransformerBase.v(
               var1x, BytecodeHelper.s("net/minecraft/client/entity/AbstractClientPlayer", "()V", "onUpdate", "onUpdate"), 0, var1xx -> {
                  int var4xx = BytecodeHelper.O(var1x, var1xx);
                  BytecodeHelper.I(var1xx, var4xx);
                  BytecodeHelper.Y(var1xx, m, "EntityPlayerSP$onPreUpdate", "(" + z + ")V");
                  BytecodeHelper.E(var1xx, var4xx, Type.VOID_TYPE);
               }
            );
            return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
               BytecodeHelper.Y(var0x, m, "EntityPlayerSP$onPostUpdate", "()V");
            });
         },
         "onUpdate",
         "onUpdate"
      );
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "EntityPlayerSP$onUpdateWalkingPlayer", "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + z + ")V");
         });
      }, "onUpdateWalkingPlayer", "onUpdateWalkingPlayer");
      return var4
         | BytecodeHelper.t(
            var1,
            "()V",
            (var0, var1x) -> {
               boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
                  BytecodeHelper.k(var0x);
                  BytecodeHelper.I(var0x, var1xx);
                  BytecodeHelper.Y(var0x, m, "EntityPlayerSP$onPreLivingUpdate", "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + z + ")V");
               });
               var4x |= TransformerBase.v(
                  var1x, BytecodeHelper.s("net/minecraft/client/entity/AbstractClientPlayer", "()V", "onLivingUpdate", "onLivingUpdate"), 0, var0x -> {
                     BytecodeHelper.Y(var0x, m, "EntityPlayerSP$onPreSuperLivingUpdate", "()V");
                  }
               );
               return var4x
                  | BytecodeHelper.R(
                     var1x, BytecodeHelper.s("net/minecraft/client/entity/EntityPlayerSP", "()Z", "isUsingItem", "isUsingItem"), (var0x, var1xx) -> {
                        BytecodeHelper.Y(var0x, v, "entityPlayerSPIsUsingItem", "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + ")Z");
                     }
                  );
            },
            "onLivingUpdate",
            "onLivingUpdate"
         );
   }

   static {
      d = 75976250758998L;
   }
}
