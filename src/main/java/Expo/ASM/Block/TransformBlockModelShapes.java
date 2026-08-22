package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

public class TransformBlockModelShapes extends TransformerBase {
   private static long d;

   public TransformBlockModelShapes() {
      super("net/minecraft/client/renderer/BlockModelShapes");
   }

   static {
      d = 6178367048455L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/block/state/IBlockState") + ")" + SrgNames.X("net/minecraft/client/resources/model/IBakedModel"),
         (var0, var1x) -> {
            for (AbstractInsnNode var4 = var1x.instructions.getFirst(); var4 != null; var4 = var4.getNext()) {
               if (var4.getOpcode() == 176) {
                  InsnList var5 = new InsnList();
                  int var6 = BytecodeHelper.t(var1x, Type.getObjectType("net/minecraft/client/resources/model/IBakedModel"));
                  var5.add(new VarInsnNode(58, var6));
                  BytecodeHelper.n(var5, var1x, 0);
                  var5.add(new VarInsnNode(25, var6));
                  BytecodeHelper.Y(
                     var5,
                     K,
                     "getModelForState",
                     "("
                        + SrgNames.X("net/minecraft/block/state/IBlockState")
                        + SrgNames.X("net/minecraft/client/resources/model/IBakedModel")
                        + ")"
                        + SrgNames.X("net/minecraft/client/resources/model/IBakedModel")
                  );
                  var1x.instructions.insertBefore(var4, var5);
               }
            }

            return true;
         },
         "getModelForState",
         "getModelForState"
      );
   }
}
