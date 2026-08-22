package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;


public class TransformEntity extends TransformerBase {
   private static long d;


   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(FFF)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.J(var1xx, var1x);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, m, "Entity$onMoveFlying", "(" + SrgNames.X("net/minecraft/entity/Entity") + "FFF" + z + ")V");
         });
      }, "moveFlying", "moveFlying");
      var4 |= BytecodeHelper.t(var1, "(FF)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.J(var1xx, var1x);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, m, "Entity$setAngles", "(" + SrgNames.X("net/minecraft/entity/Entity") + "FF" + z + ")V");
         });
      }, "setAngles", "setAngles");
      var4 |= BytecodeHelper.t(
         var1,
         "(DF)" + SrgNames.X("net/minecraft/util/MovingObjectPosition"),
         (var0, var1x) -> {
            return BytecodeHelper.R(
               var1x, BytecodeHelper.s("net/minecraft/entity/Entity", "(F)" + SrgNames.X("net/minecraft/util/Vec3"), "getLook", "getLook"), (var0x, var1xx) -> {
                  long var2xx = d ^ 115462417225750L;
                  BytecodeHelper.Y(var0x, v, "entityRayTraceGetLook", "(" + SrgNames.X("net/minecraft/entity/Entity") + "F)" + SrgNames.X("net/minecraft/util/Vec3"));
               }
            );
         },
         "rayTrace",
         "rayTrace"
      );
      var4 |= BytecodeHelper.t(var1, "()F", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.FLOAT_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 83335895026306L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "Entity$getCollisionBorderSize", "(" + SrgNames.X("net/minecraft/entity/Entity") + I + ")V");
         });
      }, "getCollisionBorderSize", "getCollisionBorderSize");
      var4 |= BytecodeHelper.t(var1, "(DDD)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 64158182290468L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "Entity$moveEntity", "(" + SrgNames.X("net/minecraft/entity/Entity") + z + ")V");
         });
      }, "moveEntity", "moveEntity");
      return var4 | BytecodeHelper.t(var1, "(DDD)V", (var0, var1x) -> {

         for (AbstractInsnNode var4x = var1x.instructions.getFirst(); var4x != null; var4x = var4x.getNext()) {
            if (var4x.getOpcode() == 54) {
               InsnList var5x = new InsnList();
               BytecodeHelper.k(var5x);
               var5x.add(new InsnNode(95));
               BytecodeHelper.Y(var5x, m, "Entity$onSafeWalk", "(" + SrgNames.X("net/minecraft/entity/Entity") + "Z)Z");
               var1x.instructions.insertBefore(var4x, var5x);
               return true;
            }
         }

         return false;
      }, "moveEntity", "moveEntity");
   }


   public TransformEntity() {
      super("net/minecraft/entity/Entity");
   }



   static {
      d = 10424132520708L;
   }
}
