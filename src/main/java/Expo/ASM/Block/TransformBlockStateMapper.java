package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.tree.ClassNode;

public class TransformBlockStateMapper extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "()Ljava/util/Map;", (var0, var1x) -> {
         return BytecodeHelper.R(var1x, BytecodeHelper.W("java/util/Set", "contains", "(Ljava/lang/Object;)Z"), (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, L, "getRenderType", "(Ljava/util/Set;Ljava/lang/Object;)Z");
         });
      }, "putAllStateModelLocations", "putAllStateModelLocations");
   }

   public TransformBlockStateMapper() {
      super("net/minecraft/client/renderer/block/statemap/BlockStateMapper");
   }

   static {
      d = 73488395764592L;
   }
}
