package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.tree.ClassNode;

public class TransformBlockGrass extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return TransformerBase.G(
         var1,
         "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"),
         F,
         "getBlockLayer",
         "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"),
         "getBlockLayer",
         "getBlockLayer"
      );
   }

   public TransformBlockGrass() {
      super("net/minecraft/block/BlockGrass");
   }

   static {
      d = 96898560477241L;
   }
}
