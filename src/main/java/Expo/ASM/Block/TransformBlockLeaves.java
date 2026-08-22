package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;


public class TransformBlockLeaves extends TransformerBase {
   private static long w;
   private static long d;


   static {
      d = 39735817187027L;
      w = -5190937494450863952L;
   }


   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"), (var1x, var2x) -> {
         InsnList var5 = new InsnList();
         BytecodeHelper.k(var5);
         BytecodeHelper.P(var5, var1.name, TransformerBase.j(var1, "Z", "isTransparent", "isTransparent", "P"), "Z");
         BytecodeHelper.Y(var5, S, "getBlockLayer", "(Z)" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"));
         var5.add(new InsnNode((int)w));
         TransformerBase.Y(var2x);
         var2x.instructions.add(var5);
         return true;
      }, "getBlockLayer", "getBlockLayer");
   }


   public TransformBlockLeaves() {
      super("net/minecraft/block/BlockLeaves");
   }
}
