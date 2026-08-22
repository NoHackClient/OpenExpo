package Expo.ASM.World;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformWorld extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/Entity") + ")Z", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.BOOLEAN_TYPE, false, false, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, R, "World$onEntityJoinWorld", "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V");
         });
      }, "spawnEntityInWorld", "spawnEntityInWorld");
      return var4 | BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, R, "World$onEntityJoinWorld", "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V");
         });
      }, "onEntityAdded", "onEntityAdded");
   }

   public TransformWorld() {
      super("net/minecraft/world/World");
   }


   static {
      d = 103346272902059L;
   }


}
