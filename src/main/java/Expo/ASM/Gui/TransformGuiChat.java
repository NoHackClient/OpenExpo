package Expo.ASM.Gui;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;


public class TransformGuiChat extends TransformerBase {
   private static long d;
   private static long w;

   public TransformGuiChat() {
      super("net/minecraft/client/gui/GuiChat");
   }


   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(Ljava/lang/String;Ljava/lang/String;)V", (var0, var1x) -> {
         InsnList var4x = new InsnList();
         BytecodeHelper.n(var4x, var1x, 0);
         BytecodeHelper.Y(var4x, k, "modifyAutoCompleteMessage", "(Ljava/lang/String;)Ljava/lang/String;");
         var4x.add(new VarInsnNode((int)w, 1));
         var1x.instructions.insert(var4x);
         return true;
      }, "sendAutocompleteRequest", "sendAutocompleteRequest");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = 95917825590422L;
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, k, "onAutocompletePlayerNames", "(" + z + ")V");
         });
      }, "autocompletePlayerNames", "autocompletePlayerNames");
      return var4 | BytecodeHelper.t(var1, "([Ljava/lang/String;)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, k, "onAutocompleteResponse", "([Ljava/lang/String;" + z + ")V");
         });
      }, "onAutocompleteResponse", "onAutocompleteResponse");
   }

   static {
      d = 21638065235124L;
      w = -2762240308694482886L;
   }
}
