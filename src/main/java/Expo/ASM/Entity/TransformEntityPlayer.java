package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformEntityPlayer extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 52988061637045L;
            BytecodeHelper.Y(var0x, m, "EntityPlayer$onPostItemUseFinish", "()V");
         });
      }, "onItemUseFinish", "onItemUseFinish");
      var4 |= BytecodeHelper.t(var1, "()" + SrgNames.X("net/minecraft/util/IChatComponent"), (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.getReturnType(var1x.desc), (var0x, var1xx) -> {
            long var2xx = d ^ 134741924567679L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "EntityPlayer$onGetDisplayName", "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + I + ")V");
         });
      }, "func_145748_c", "getDisplayName");
      return var4
         | BytecodeHelper.t(
            var1,
            "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V",
            (var0, var1x) -> {
               return TransformerBase.u(
                  var1x,
                  Type.VOID_TYPE,
                  false,
                  true,
                  (var1xx, var2xx) -> {
                     BytecodeHelper.k(var1xx);
                     BytecodeHelper.n(var1xx, var1x, 0);
                     BytecodeHelper.I(var1xx, var2xx);
                     BytecodeHelper.Y(
                        var1xx,
                        m,
                        "EntityPlayer$onAttackTargetEntity",
                        "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + SrgNames.X("net/minecraft/entity/Entity") + z + ")V"
                     );
                  }
               );
            },
            "attackTargetEntityWithCurrentItem",
            "attackTargetEntityWithCurrentItem"
         );
   }

   public TransformEntityPlayer() {
      super("net/minecraft/entity/player/EntityPlayer");
   }

   static {
      d = 36532662932892L;
   }
}
