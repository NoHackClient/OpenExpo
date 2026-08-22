package Expo.ASM.Gui;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformGuiTextField extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(Ljava/lang/String;)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, V, "onGuiChatKeyTyped", "()V");
         });
      }, "writeText", "writeText");
      return var4 | BytecodeHelper.t(var1, "(I)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, V, "onGuiChatKeyTyped", "()V");
         });
      }, "deleteWords", "deleteWords", "deleteFromCursor", "deleteFromCursor");
   }

   static {
      d = 14047968795735L;
   }

   public TransformGuiTextField() {
      super("net/minecraft/client/gui/GuiTextField");
   }
}
