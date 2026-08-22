package Expo.ASM.Player;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformKeyBinding extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "()Z", (var1x, var2x) -> {
         return TransformerBase.M(var2x, Type.BOOLEAN_TYPE, (var1xx, var2xx) -> {
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.k(var1xx);
            BytecodeHelper.P(var1xx, var1.name, TransformerBase.j(var1, "Ljava/lang/String;", "keyDescription", "keyDescription", "d"), "Ljava/lang/String;");
            BytecodeHelper.k(var1xx);
            BytecodeHelper.P(var1xx, var1.name, TransformerBase.j(var1, "I", "keyCode", "keyCode", "g"), "I");
            BytecodeHelper.Y(var1xx, R, "Keybinding$isPressed", "(" + I + "Ljava/lang/String;I)V");
         });
      }, "isPressed", "isPressed");
      var4 |= BytecodeHelper.t(var1, "(IZ)V", (var0, var1x) -> {
         long var2x = d ^ 137907299390585L;
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.n(var1xx, var1x, 1);
            BytecodeHelper.Y(var1xx, R, "Keybinding$onSetKeyBindState", "(IZ)V");
         });
      }, "setKeyBindState", "setKeyBindState");
      return var4 | BytecodeHelper.t(var1, "(I)V", (var0, var1x) -> {
         long var2x = d ^ 1021482749711L;
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, R, "Keybinding$onTick", "(I)V");
         });
      }, "onTick", "onTick");
   }

   static {
      d = 100462606356730L;
   }

   public TransformKeyBinding() {
      super("net/minecraft/client/settings/KeyBinding");
   }
}
