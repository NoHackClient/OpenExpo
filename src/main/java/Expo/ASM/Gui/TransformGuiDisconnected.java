package Expo.ASM.Gui;

import Expo.ASM.Hooks.Gui.GuiEventHooks;
import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import java.util.Map;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;











public class TransformGuiDisconnected extends TransformerBase {
   private static long d;
   private static String P;
   private static Map t;



   static {
      d = 55710805314204L;
      P = TransformerBase.e(GuiEventHooks.class);
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            BytecodeHelper.k(var0x);
            BytecodeHelper.Y(var0x, P, "onDisconnectedInit", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + ")V");
         });
      }, "initGui", "initGui");
   }

   public TransformGuiDisconnected() {
      super("net/minecraft/client/gui/GuiDisconnected");
   }

   private static void b() {
   }



}
