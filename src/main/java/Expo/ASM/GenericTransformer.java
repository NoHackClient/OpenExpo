package Expo.ASM;

import Expo.ASM.Hooks.Gui.GuiEventHooks;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import java.util.Map;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;











public class GenericTransformer extends TransformerBase {
   private static String y;
   private static Map t;
   private static long d;

   private static void b() {
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 50622047037306L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.Y(var0x, y, "onInitGui", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + ")V");
         });
      }, "initGui", "initGui");
      var4 |= BytecodeHelper.t(var1, "(IIF)V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 134993214120361L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.Y(var0x, y, "onDrawScreen", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + ")V");
         });
      }, "drawScreen", "drawScreen");
      return var4
         | BytecodeHelper.t(
            var1,
            "(" + SrgNames.X("net/minecraft/client/gui/GuiButton") + ")V",
            (var0, var1x) -> {
               return TransformerBase.u(
                  var1x,
                  Type.VOID_TYPE,
                  false,
                  true,
                  (var1xx, var2xx) -> {
                     BytecodeHelper.k(var1xx);
                     BytecodeHelper.n(var1xx, var1x, 0);
                     BytecodeHelper.I(var1xx, var2xx);
                     BytecodeHelper.Y(
                        var1xx,
                        y,
                        "onActionPerformed",
                        "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + SrgNames.X("net/minecraft/client/gui/GuiButton") + z + ")V"
                     );
                  }
               );
            },
            "actionPerformed",
            "actionPerformed"
         );
   }

   public GenericTransformer(String var1) {
      super(var1);
   }

   static {
      d = 108897702989157L;
      y = TransformerBase.e(GuiEventHooks.class);
   }






}
