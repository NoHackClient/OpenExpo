package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformBlock extends TransformerBase {
   private static long d;


   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "()" + SrgNames.X("net/minecraft/util/EnumWorldBlockLayer"), (var0, var1x) -> {
         long var2x = d ^ 53709245379394L;
         return TransformerBase.u(var1x, Type.getReturnType(var1x.desc), true, true, (var0x, var1xx) -> {
            long var2xx = 58949497469418L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, H, "getBlockLayer", "(" + SrgNames.X("net/minecraft/block/Block") + I + ")V");
         });
      }, "getBlockLayer", "getBlockLayer");
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/world/IBlockAccess") + SrgNames.X("net/minecraft/util/BlockPos") + ")I",
         (var0, var1x) -> {
            long var2x = d ^ 20793278481626L;
            return TransformerBase.u(
               var1x,
               Type.INT_TYPE,
               true,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.J(var1xx, var1x);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx, H, "getMixedBrightnessForBlock", "(" + SrgNames.X("net/minecraft/world/IBlockAccess") + SrgNames.X("net/minecraft/util/BlockPos") + I + ")V"
                  );
               }
            );
         },
         "getMixedBrightnessForBlock",
         "getMixedBrightnessForBlock"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/world/IBlockAccess") + SrgNames.X("net/minecraft/util/BlockPos") + SrgNames.X("net/minecraft/util/EnumFacing") + ")Z",
         (var0, var1x) -> {
            long var2x = d ^ 126019547128468L;
            return TransformerBase.u(
               var1x,
               Type.BOOLEAN_TYPE,
               true,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.n(var1xx, var1x, 1);
                  BytecodeHelper.n(var1xx, var1x, 2);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     H,
                     "shouldSideBeRendered",
                     "("
                        + SrgNames.X("net/minecraft/block/Block")
                        + SrgNames.X("net/minecraft/world/IBlockAccess")
                        + SrgNames.X("net/minecraft/util/BlockPos")
                        + SrgNames.X("net/minecraft/util/EnumFacing")
                        + I
                        + ")V"
                  );
               }
            );
         },
         "shouldSideBeRendered",
         "shouldSideBeRendered"
      );
      return var4
         | BytecodeHelper.t(
            var1,
            "("
               + SrgNames.X("net/minecraft/world/World")
               + SrgNames.X("net/minecraft/util/BlockPos")
               + SrgNames.X("net/minecraft/block/state/IBlockState")
               + SrgNames.X("net/minecraft/util/AxisAlignedBB")
               + "Ljava/util/List;"
               + SrgNames.X("net/minecraft/entity/Entity")
               + ")V",
            (var1x, var2x) -> {
               return TransformerBase.u(
                  var2x,
                  Type.VOID_TYPE,
                  false,
                  true,
                  (var2xx, var3x) -> {
                     BytecodeHelper.J(var2xx, var2x);
                     BytecodeHelper.k(var2xx);
                     BytecodeHelper.P(
                        var2xx,
                        var1.name,
                        TransformerBase.j(var1, SrgNames.X("net/minecraft/block/state/BlockState"), "blockState", "blockState", "M"),
                        SrgNames.X("net/minecraft/block/state/BlockState")
                     );
                     BytecodeHelper.k(var2xx);
                     BytecodeHelper.I(var2xx, var3x);
                     BytecodeHelper.Y(
                        var2xx,
                        H,
                        "addCollisionBoxesToList",
                        "("
                           + SrgNames.X("net/minecraft/world/World")
                           + SrgNames.X("net/minecraft/util/BlockPos")
                           + SrgNames.X("net/minecraft/block/state/IBlockState")
                           + SrgNames.X("net/minecraft/util/AxisAlignedBB")
                           + "Ljava/util/List;"
                           + SrgNames.X("net/minecraft/entity/Entity")
                           + SrgNames.X("net/minecraft/block/state/BlockState")
                           + SrgNames.X("net/minecraft/block/Block")
                           + z
                           + ")V"
                     );
                  }
               );
            },
            "addCollisionBoxesToList",
            "addCollisionBoxesToList"
         );
   }

   public TransformBlock() {
      super("net/minecraft/block/Block");
   }


   static {
      d = 23934094264149L;
   }

}
