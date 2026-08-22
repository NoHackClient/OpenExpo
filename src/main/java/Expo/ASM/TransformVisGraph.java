package Expo.ASM;

import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformVisGraph extends TransformerBase {
   private static long d;

   static {
      d = 92898150898623L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/util/BlockPos") + ")V", (var1x, var2x) -> {
         return TransformerBase.u(var2x, Type.VOID_TYPE, false, true, (var2xx, var3x) -> {
            BytecodeHelper.n(var2xx, var2x, 0);
            BytecodeHelper.k(var2xx);
            BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "Ljava/util/BitSet;", "field_178612_d", "d"), "Ljava/util/BitSet;");
            BytecodeHelper.I(var2xx, var3x);
            BytecodeHelper.Y(var2xx, N, "func_178606_a", "(" + SrgNames.X("net/minecraft/util/BlockPos") + "Ljava/util/BitSet;" + z + ")V");
         });
      }, "func_178606_a");
   }

   public TransformVisGraph() {
      super("net/minecraft/client/renderer/chunk/VisGraph");
   }
}
