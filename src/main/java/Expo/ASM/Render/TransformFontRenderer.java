package Expo.ASM.Render;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

public class TransformFontRenderer extends TransformerBase {
   private static long d;

   public TransformFontRenderer() {
      super("net/minecraft/client/gui/FontRenderer");
   }

   public boolean X(ClassNode var1, String var2, String... var3) {
      return BytecodeHelper.t(var1, var2, (var0, var1x) -> {
         InsnList var4x = new InsnList();
         var4x.add(new VarInsnNode(25, 1));
         BytecodeHelper.Y(var4x, x, "replaceString", "(Ljava/lang/String;)Ljava/lang/String;");
         var4x.add(new VarInsnNode(58, 1));
         var1x.instructions.insert(var4x);
         return true;
      }, var3);
   }

   static {
      d = 75314626025260L;
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= this.X(var1, "(Ljava/lang/String;FFIZ)I", "renderString", "renderString");
      return var4 | this.X(var1, "(Ljava/lang/String;)I", "getStringWidth", "getStringWidth");
   }
}
