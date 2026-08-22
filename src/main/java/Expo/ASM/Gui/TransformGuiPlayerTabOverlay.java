package Expo.ASM.Gui;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformGuiPlayerTabOverlay extends TransformerBase {
   private static long d;

   static {
      d = 76852039433438L;
   }


   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/client/network/NetworkPlayerInfo") + ")Ljava/lang/String;", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.getReturnType(var1x.desc), (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, p, "onPlayerGetName", "(" + SrgNames.X("net/minecraft/client/network/NetworkPlayerInfo") + I + ")V");
         });
      }, "getPlayerName", "getPlayerName");
   }

   public TransformGuiPlayerTabOverlay() {
      super("net/minecraft/client/gui/GuiPlayerTabOverlay");
   }


}
