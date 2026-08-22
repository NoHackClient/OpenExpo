package Expo.ASM;

import Expo.ASM.Hooks.Block.BlockBarrierHooks;
import Expo.ASM.Hooks.Block.BlockBushHooks;
import Expo.ASM.Hooks.Block.BlockGrassHooks;
import Expo.ASM.Hooks.Block.BlockHooks;
import Expo.ASM.Hooks.Block.BlockLeavesHooks;
import Expo.ASM.Hooks.Block.BlockModelRendererHooks;
import Expo.ASM.Hooks.Block.BlockModelShapesHooks;
import Expo.ASM.Hooks.Block.BlockRendererDispatcherHooks;
import Expo.ASM.Hooks.Block.BlockStateMapperHooks;
import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ASM.Hooks.Entity.EntityHookDispatch;
import Expo.ASM.Hooks.Entity.EntityRenderStateHooks;
import Expo.ASM.Hooks.Entity.EntityRendererHooks;
import Expo.ASM.Hooks.Entity.RenderEntityItemHooks;
import Expo.ASM.Hooks.Entity.RendererLivingEntityHooks;
import Expo.ASM.Hooks.Gui.GuiChatHooks;
import Expo.ASM.Hooks.Gui.GuiContainerHooks;
import Expo.ASM.Hooks.Gui.GuiIngameHooks;
import Expo.ASM.Hooks.Gui.GuiPlayerTabOverlayHooks;
import Expo.ASM.Hooks.Gui.GuiScreenHooks;
import Expo.ASM.Hooks.Gui.GuiTextFieldHooks;
import Expo.ASM.Hooks.HookDispatch;
import Expo.ASM.Hooks.LayerCapeHooks;
import Expo.ASM.Hooks.MiscHooks;
import Expo.ASM.Hooks.Network.NetHandlerPlayClientHooks;
import Expo.ASM.Hooks.Network.NetworkManagerHooks;
import Expo.ASM.Hooks.Network.NetworkPlayerInfoHooks;
import Expo.ASM.Hooks.Render.EffectRendererHooks;
import Expo.ASM.Hooks.Render.ItemRendererHooks;
import Expo.ASM.Hooks.Render.ModelBipedHooks;
import Expo.ASM.Hooks.Render.WorldRendererHooks;
import Expo.ASM.Hooks.VisGraphHooks;
import Expo.ASM.Hooks.World.WorldClientHooks;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.InsnEditor;
import Expo.ASM.Util.MethodInsnMatcher;
import Expo.ASM.Util.ReturnSiteEmitter;
import Expo.util.ClientUtil;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;


public abstract class TransformerBase implements ClassTransform, Opcodes {
   public static String D;
   public static String a;
   public static String b;
   public static String o;
   public static String k;
   public static String h;
   public static String Y;
   public static String r;
   public static String s;
   public static String T;
   public static String j;
   public static String u;
   public static String q;
   public static String A;
   public static String z;
   public static String v;
   public static String S;
   public static String x;
   public static String L;
   public static String e;
   public static String H;
   public static String K;
   private static long c;
   public static String m;
   public static String B;
   public static String f;
   public static String I;
   public static String Q;
   private final String X;
   public static String p;
   public static String R;
   protected static String n;
   public static String C;
   public static String F;
   protected static String N;
   public static String g;
   public static String V;

   public static String e(Class<?> var0) {
      return Type.getInternalName(var0);
   }

   public static boolean u(MethodNode var0, Type var1, boolean var2, boolean var3, ReturnSiteEmitter var4) {
      InsnList var7 = new InsnList();
      int var8 = var2 ? BytecodeHelper.S(var0, var7) : BytecodeHelper.O(var0, var7);
      var4.c(var7, var8);
      if (var3) {
         BytecodeHelper.E(var7, var8, var1);
      }

      var0.instructions.insert(var7);
      return true;
   }

   public static boolean v(MethodNode var0, MethodInsnMatcher var1, int var2, InsnEditor var3) {
      int var6 = 0;

      for (AbstractInsnNode var7 = var0.instructions.getFirst(); var7 != null; var7 = var7.getNext()) {
         if (var7 instanceof MethodInsnNode && var1.A((MethodInsnNode)var7) && var6++ == var2) {
            InsnList var8 = new InsnList();
            var3.D(var8);
            var0.instructions.insertBefore(var7, var8);
            return true;
         }
      }

      return false;
   }

   public static void Y(MethodNode var0) {
      AbstractInsnNode var3 = var0.instructions.getFirst();

      while (var3 != null) {
         AbstractInsnNode var4 = var3.getNext();
         var0.instructions.remove(var3);
         var3 = var4; // add code
      }

      var0.tryCatchBlocks.clear();
      var0.localVariables.clear();
   }


   public static boolean Q(MethodNode var0, MethodInsnMatcher var1, int var2, InsnEditor var3) {
      int var6 = 0;

      for (AbstractInsnNode var7 = var0.instructions.getFirst(); var7 != null; var7 = var7.getNext()) {
         if (var7 instanceof MethodInsnNode && var1.A((MethodInsnNode)var7) && var6++ == var2) {
            InsnList var8 = new InsnList();
            var3.D(var8);
            var0.instructions.insert(var7, var8);
            return true;
         }
      }

      return false;
   }

   public static boolean M(MethodNode var0, Type var1, ReturnSiteEmitter var2) {
      boolean var5 = false;

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6.getOpcode() == BytecodeHelper.N(var1)) {
            InsnList var7 = new InsnList();
            int var8 = -1;
            int var9;
            if (var1.getSort() == 0) {
               var9 = BytecodeHelper.O(var0, var7);
            } else {
               var8 = BytecodeHelper.t(var0, var1);
               var7.add(new VarInsnNode(BytecodeHelper.D(var1), var8));
               var9 = BytecodeHelper.r(var0, var7, var1, var8);
            }

            var2.c(var7, var9);
            if (var1.getSort() != 0) {
               BytecodeHelper.E(var7, var9, var1);
               var7.add(new VarInsnNode(BytecodeHelper.R(var1), var8));
            }

            var0.instructions.insertBefore(var6, var7);
            var5 = true;
         }
      }

      return var5;
   }

   public abstract boolean s(ClassNode var1);

   public static boolean g(MethodNode var0, String var1, String var2, InsnEditor var3, String... var4) {

      for (AbstractInsnNode var7 = var0.instructions.getFirst(); var7 != null; var7 = var7.getNext()) {
         if (var7 instanceof FieldInsnNode && var7.getOpcode() == 181) {
            FieldInsnNode var8 = (FieldInsnNode)var7;
            if (Expo.ASM.Util.AsmUtil.v(var8, var1, var2, var4)) {
               InsnList var9 = new InsnList();
               var3.D(var9);
               var0.instructions.insert(var7, var9);
               return true;
            }
         }
      }

      return false;
   }

   protected TransformerBase(String var1) {
      this.X = var1;
   }

   public static String j(ClassNode var0, String var1, String... var2) {

      for (String var8 : var2) {
         for (Object var10 : var0.fields) {
            FieldNode var11 = (FieldNode)var10;
            if (Expo.ASM.Util.AsmUtil.v(new FieldInsnNode(180, var0.name, var11.name, var11.desc), var0.name, var1, var8)) {
               return var11.name;
            }
         }
      }

      return var2[0];
   }

   public String E() {
      return this.X;
   }

   public static boolean K(MethodNode var0, String var1, String var2, String var3, int var4, InsnEditor var5) {
      int var8 = 0;

      for (AbstractInsnNode var9 = var0.instructions.getFirst(); var9 != null; var9 = var9.getNext()) {
         if (var9 instanceof FieldInsnNode) {
            FieldInsnNode var10 = (FieldInsnNode)var9;
            if (var10.getOpcode() == 180 && Expo.ASM.Util.AsmUtil.v(var10, var1, var3, var2) && var8++ == var4) {
               InsnList var11 = new InsnList();
               var5.D(var11);
               var0.instructions.insertBefore(var9, var11);
               return true;
            }
         }
      }

      return false;
   }

   private static void a() {
   }

   static {

      c = 7055454155035L;

      z = Type.getDescriptor(CallbackInfo.class);

      I = Type.getDescriptor(CallbackInfoReturnable.class);

      v = e(MiscHooks.class);

      R = e(HookDispatch.class);

      m = e(EntityHookDispatch.class);

      H = e(BlockHooks.class);

      B = e(BlockBarrierHooks.class);

      a = e(BlockBushHooks.class);

      F = e(BlockGrassHooks.class);

      S = e(BlockLeavesHooks.class);

      K = e(BlockModelShapesHooks.class);

      L = e(BlockStateMapperHooks.class);

      k = e(GuiChatHooks.class);

      o = e(GuiContainerHooks.class);

      C = e(GuiIngameHooks.class);

      p = e(GuiPlayerTabOverlayHooks.class);

      f = e(GuiScreenHooks.class);

      V = e(GuiTextFieldHooks.class);

      A = e(NetHandlerPlayClientHooks.class);

      q = e(NetworkManagerHooks.class);

      Q = e(NetworkPlayerInfoHooks.class);

      g = e(BlockModelRendererHooks.class);

      r = e(BlockRendererDispatcherHooks.class);

      b = e(EffectRendererHooks.class);

      s = e(EntityRendererHooks.class);

      D = e(ItemRendererHooks.class);

      n = e(LayerCapeHooks.class);

      j = e(ModelBipedHooks.class);

      h = e(EntityRenderStateHooks.class);

      u = e(RenderEntityItemHooks.class);

      Y = e(RendererLivingEntityHooks.class);

      N = e(VisGraphHooks.class);

      T = e(WorldClientHooks.class);

      e = e(WorldRendererHooks.class);

      x = e(ClientUtil.class);
   }

   public byte[] S(byte[] var1) throws Throwable {
      return BytecodeHelper.G(var1, this::s);
   }

   public static boolean G(ClassNode var0, String var1, String var2, String var3, String var4, String... var5) {
      return BytecodeHelper.t(var0, var1, (var4x, var5x) -> {
         InsnList var8 = new InsnList();
         BytecodeHelper.Y(var8, var2, var3, var4);
         var8.add(new InsnNode(BytecodeHelper.N(Type.getReturnType(var1))));
         Y(var5x);
         var5x.instructions.add(var8);
         return true;
      }, var5);
   }

}
