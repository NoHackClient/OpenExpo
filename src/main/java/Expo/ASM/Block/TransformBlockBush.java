package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.tree.ClassNode;

public class TransformBlockBush extends TransformerBase {
   private static long d;

   static {
      d = 63269248874563L;
   }

   public boolean s(ClassNode var1) {
      return TransformerBase.G(
         var1,
         "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"),
         a,
         "getBlockLayer",
         "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"),
         "getBlockLayer",
         "getBlockLayer"
      );
   }

   public TransformBlockBush() {
      super("net/minecraft/block/BlockBush");
   }
}
