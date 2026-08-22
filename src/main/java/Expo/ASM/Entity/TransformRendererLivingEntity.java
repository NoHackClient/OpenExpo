package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformRendererLivingEntity extends TransformerBase {
   private static long d;

   public TransformRendererLivingEntity() {
      super("net/minecraft/client/renderer/entity/RendererLivingEntity");
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + "DDDFF)V", (var0, var1x) -> {
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, Y, "onPreRender", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + ")V");
         });
         return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, Y, "onPostRender", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + ")V");
         });
      }, "doRender", "doRender");
      return var4 | BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + ")Z", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.BOOLEAN_TYPE, true, true, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, Y, "canRenderName", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + I + ")V");
         });
      }, "canRenderName", "canRenderName");
   }

   static {
      d = 48482666421625L;
   }
}
