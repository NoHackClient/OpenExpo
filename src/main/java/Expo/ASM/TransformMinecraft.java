package Expo.ASM;

import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.MethodInsnMatcher;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class TransformMinecraft extends TransformerBase {
   private static long d;

   static {
      d = 56557798079205L;
   }

   public TransformMinecraft() {
      super("net/minecraft/client/Minecraft");
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(
         var1,
         "()V",
         (var1x, var2x) -> {
            boolean var5x = false;
            var5x |= this.A(var2x);
            var5x |= BytecodeHelper.M(var2x, BytecodeHelper.r("net/minecraft/client/gui/GuiScreen", "Z", "allowUserInput", "allowUserInput"), var0 -> {
               long var1xx = d ^ 128040436482982L;
               var0.add(new InsnNode(87));
               BytecodeHelper.Y(var0, R, "Minecraft$notAllowUserInput", "()Z");
            });
            var5x |= BytecodeHelper.R(
               var2x, BytecodeHelper.s("net/minecraft/entity/player/InventoryPlayer", "(I)V", "changeCurrentItem", "changeCurrentItem"), (var0, var1xx) -> {
                  BytecodeHelper.Y(var0, R, "Minecraft$changeCurrentItem", "(" + SrgNames.X("net/minecraft/entity/player/InventoryPlayer") + "I)V");
               }
            );
            var5x |= TransformerBase.u(var2x, Type.VOID_TYPE, false, false, (var0, var1xx) -> {
               BytecodeHelper.Y(var0, v, "minecraftRunTickHead", "()V");
            });
            return var5x | TransformerBase.M(var2x, Type.VOID_TYPE, (var0, var1xx) -> {
               BytecodeHelper.Y(var0, R, "Minecraft$onPostTick", "()V");
            });
         },
         "runTick",
         "runTick"
      );
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         long var2x = d ^ 24880119880627L;
         return TransformerBase.g(var1x, "net/minecraft/client/Minecraft", SrgNames.X("net/minecraft/client/gui/GuiIngame"), var0x -> {
            long var1xx = d ^ 130876057996586L;
            BytecodeHelper.Y(var0x, R, "Minecraft$onStartGame", "()V");
         }, "ingameGUI", "ingameGUI");
      }, "startGame", "startGame");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         long var2x = d ^ 75127701365943L;
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, v, "minecraftClickMouseHead", "(" + z + ")V");
         });
         return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, R, "Minecraft$onPostClickMouse", "()V");
         });
      }, "clickMouse", "clickMouse");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         long var2x = d ^ 65030155966452L;
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, v, "minecraftRightClickMouseHead", "(" + z + ")V");
         });
         return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, R, "Minecraft$onPostRightClick", "()V");
         });
      }, "rightClickMouse", "rightClickMouse");
      var4 |= BytecodeHelper.t(var1, "(Z)V", (var0, var1x) -> {
         long var2x = d ^ 56892188335816L;
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, v, "minecraftSendClickBlockHead", "(" + z + ")V");
         });
      }, "sendClickBlockToController", "sendClickBlockToController");
      var4 |= BytecodeHelper.t(
         var1,
         "()V",
         (var0, var1x) -> {
            long var2x = d ^ 32915274582190L;
            return BytecodeHelper.R(
               var1x,
               BytecodeHelper.s(
                  "net/minecraft/util/ScreenShotHelper",
                  "(Ljava/io/File;II" + SrgNames.X("net/minecraft/client/shader/Framebuffer") + ")" + SrgNames.X("net/minecraft/util/IChatComponent"),
                  "saveScreenshot",
                  "saveScreenshot"
               ),
               (var0x, var1xx) -> {
                  BytecodeHelper.Y(
                     var0x,
                     R,
                     "Minecraft$onSaveScreenshot",
                     "(Ljava/io/File;II" + SrgNames.X("net/minecraft/client/shader/Framebuffer") + ")" + SrgNames.X("net/minecraft/util/IChatComponent")
                  );
               }
            );
         },
         "dispatchKeypresses",
         "dispatchKeypresses"
      );
      return var4 | BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/client/multiplayer/WorldClient") + "Ljava/lang/String;)V", (var0, var1x) -> {
         long var2x = d ^ 136677783342530L;
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, R, "Minecraft$onLoadWorld", "()V");
         });
         return var4x | BytecodeHelper.R(var1x, BytecodeHelper.W("java/lang/System", "gc", "()V"), (var0x, var1xx) -> {
            BytecodeHelper.Y(var0x, R, "Minecraft$onOptimizeWorldSwapping", "()V");
         });
      }, "loadWorld", "loadWorld");
   }

   private boolean A(MethodNode var1) {
      boolean var4 = false;
      MethodInsnMatcher var5 = BytecodeHelper.s(
         "net/minecraft/client/multiplayer/PlayerControllerMP",
         "(" + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + ")V",
         "onStoppedUsingItem",
         "onStoppedUsingItem"
      );
      MethodInsnMatcher var6 = BytecodeHelper.s(
         "net/minecraft/client/multiplayer/PlayerControllerMP",
         "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + ")V",
         "onStoppedUsingItem",
         "onStoppedUsingItem"
      );

      for (AbstractInsnNode var7 = var1.instructions.getFirst(); var7 != null; var7 = var7.getNext()) {
         if (var7 instanceof MethodInsnNode) {
            MethodInsnNode var8 = (MethodInsnNode)var7;
            if (var5.A(var8) || var6.A(var8)) {
               LabelNode var9 = new LabelNode();
               LabelNode var10 = new LabelNode();
               InsnList var11 = new InsnList();
               var11.add(new InsnNode(92));
               BytecodeHelper.Y(
                  var11,
                  v,
                  "minecraftShouldCancelStoppedUsingItem",
                  "(" + SrgNames.X("net/minecraft/client/multiplayer/PlayerControllerMP") + SrgNames.X("net/minecraft/entity/player/EntityPlayer") + ")Z"
               );
               var11.add(new JumpInsnNode(153, var9));
               var11.add(new InsnNode(88));
               var11.add(new JumpInsnNode(167, var10));
               var11.add(var9);
               var1.instructions.insertBefore(var8, var11);
               var1.instructions.insert(var8, var10);
               var4 = true;
            }
         }
      }

      return var4;
   }
}
