package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformEntityItem extends TransformerBase {
   private static long d;

   static {
      d = 101780087572982L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + ")V",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.VOID_TYPE,
               false,
               false,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(
                     var1xx,
                     m,
                     "EntityItem$onPickUpItem",
                     "(" + SrgNames.X("net/minecraft/entity/item/EntityItem") + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + ")V"
                  );
               }
            );
         },
         "onCollideWithPlayer",
         "onCollideWithPlayer"
      );
   }


   public TransformEntityItem() {
      super("net/minecraft/entity/item/EntityItem");
   }


}
