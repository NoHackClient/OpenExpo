package Expo.ASM.Gui;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformGuiContainer extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "(IIF)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, o, "cancelDrawChest", "(" + z + ")V");
         });
      }, "drawScreen", "drawScreen");
   }

   static {
      d = 139396823925430L;
   }

   public TransformGuiContainer() {
      super("net/minecraft/client/gui/inventory/GuiContainer");
   }
}
