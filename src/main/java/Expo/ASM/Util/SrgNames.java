package Expo.ASM.Util;

public class SrgNames {
   public static String x;
   public static String f;
   public static String E;
   public static String Z;
   public static String b;
   public static String S;
   public static String T;
   public static String t;
   public static String Go;
   public static String u;
   public static String l;
   public static String Gj;
   public static String N;
   public static String W;
   public static String d;
   public static String M;
   public static String D;
   public static String s;
   public static String Gt;
   public static String Gi;
   public static String U;
   public static String F;
   public static String GF;
   public static String C;
   public static String y;
   public static String A;
   public static String G;
   public static String e;
   public static String R;
   public static String p;
   public static String GN;
   public static String L;
   public static String G5;
   public static String w;
   public static String Gc;
   public static String Gn;
   public static String Gf;
   private static long ab;
   public static String z;
   public static String g;
   public static String G0;
   public static String X;
   public static String V;
   public static String P;
   public static String Gd;
   public static String B;
   public static String GE;
   public static String m;
   public static String GC;
   public static String Gx;
   public static String v;
   public static String Y;
   public static String GM;
   public static String j;
   public static String c;
   public static String GB;
   public static String GX;
   public static String K;
   public static String r;
   public static String Gs;
   public static String k;
   public static String Gg;
   public static String GG;
   public static String J;
   public static String q;
   public static String O;
   public static String GZ;
   public static String n;
   public static String Gw;
   public static String I;
   public static String Gh;
   public static String h;
   public static String H;
   public static String Gk;
   public static String GU;
   public static String a;
   public static String Q;
   public static String Ga;
   public static String o;
   public static String GP;
   public static String i;

   public static String C(String var0) {
      return var0.length() != 1 && var0.charAt(0) != 91 && var0.charAt(0) != 76
         ? "L" + var0 + ";"
         : var0;
   }

   public static String o(String var0, String... var1) {
      StringBuilder var4 = new StringBuilder("(");

      for (String var8 : var1) {
         var4.append(C(var8));
      }

      return var4.append((char)41).append(C(var0)).toString();
   }

   public static String X(String var0) {
      return C(var0);
   }

   static {
      ab = 137773813781953L;
      GC = "V";
      m = "F";
      A = "Z";
      M = "D";
      Z = "net/minecraft/block/BlockGrass";
      L = "net/minecraft/entity/item/EntityItem";
      Q = "net/minecraft/util/ScreenShotHelper";
      n = "net/minecraft/util/DamageSource";
      GE = "net/minecraft/network/play/server/S02PacketChat";
      GM = "net/minecraft/client/gui/FontRenderer";
      GP = "net/minecraft/client/settings/GameSettings";
      U = "net/minecraft/world/World";
      Gd = "net/minecraft/client/model/ModelBiped";
      R = "net/minecraft/entity/EntityLivingBase";
      s = "net/minecraft/world/IBlockAccess";
      t = "net/minecraft/client/particle/EffectRenderer";
      r = "net/minecraft/util/IChatComponent";
      Gk = "net/minecraft/client/LoadingScreenRenderer";
      d = "net/minecraft/client/gui/GuiDisconnected";
      Gt = "net/minecraft/block/Block";
      V = "net/minecraft/server/management/ItemInWorldManager";
      G5 = "net/minecraft/block/BlockBush";
      G0 = "net/minecraft/network/NetworkManager";
      Gs = "net/minecraft/entity/player/EntityPlayer";
      GX = "net/minecraft/util/EnumWorldBlockLayer";
      v = "net/minecraft/client/renderer/BlockModelShapes";
      u = "net/minecraft/client/gui/GuiScreen";
      G = "net/minecraft/client/renderer/entity/layers/LayerCape";
      b = "net/minecraft/client/renderer/chunk/VisGraph";
      i = "net/minecraft/block/state/IBlockState";
      GF = "net/minecraft/util/EnumFacing";
      Y = "net/minecraft/util/AxisAlignedBB";
      GB = "net/minecraft/util/Vec3";
      J = "net/minecraft/client/renderer/BlockRendererDispatcher";
      k = "net/minecraft/util/BlockPos";
      F = "net/minecraft/client/entity/AbstractClientPlayer";
      w = "net/minecraft/potion/Potion";
      Gg = "net/minecraft/client/renderer/WorldRenderer";
      Gh = "net/minecraft/client/gui/GuiChat";
      T = "net/minecraft/network/Packet";
      Ga = "net/minecraft/client/model/ModelPlayer";
      l = "net/minecraft/client/entity/EntityPlayerSP";
      K = "net/minecraft/client/settings/KeyBinding";
      B = "net/minecraft/client/gui/ScaledResolution";
      g = "net/minecraft/network/play/server/S12PacketEntityVelocity";
      Gj = "net/minecraft/block/state/BlockState";
      p = "net/minecraft/client/multiplayer/PlayerControllerMP";
      h = "net/minecraft/client/gui/GuiMultiplayer";
      H = "net/minecraft/network/play/INetHandlerPlayClient";
      I = "net/minecraft/client/renderer/entity/RenderEntityItem";
      c = "net/minecraft/util/ResourceLocation";
      j = "net/minecraft/client/gui/GuiPlayerTabOverlay";
      Gx = "net/minecraft/client/renderer/entity/RendererLivingEntity";
      D = "net/minecraft/util/MovementInput";
      e = "net/minecraft/entity/player/InventoryPlayer";
      GN = "net/minecraft/block/BlockLeaves";
      GG = "net/minecraft/client/gui/GuiTextField";
      O = "net/minecraft/entity/Entity";
      Gi = "net/minecraft/client/resources/model/IBakedModel";
      Gc = "net/minecraft/client/gui/GuiButton";
      Gn = "net/minecraft/client/gui/GuiIngame";
      y = "net/minecraft/client/renderer/ItemRenderer";
      GU = "net/minecraft/client/network/NetHandlerPlayClient";
      E = "net/minecraft/scoreboard/ScoreObjective";
      P = "net/minecraft/client/gui/GuiMainMenu";
      W = "net/minecraft/client/network/NetworkPlayerInfo";
      C = "net/minecraft/util/MovementInputFromOptions";
      N = "net/minecraft/client/gui/inventory/GuiContainer";
      f = "net/minecraft/client/renderer/BlockModelRenderer";
      GZ = "net/minecraft/client/renderer/EntityRenderer";
      o = "net/minecraft/block/BlockBarrier";
      x = "net/minecraft/client/gui/GuiSelectWorld";
      q = "net/minecraft/client/shader/Framebuffer";
      Gf = "net/minecraft/client/renderer/block/statemap/BlockStateMapper";
      X = "net/minecraft/client/renderer/GlStateManager";
      z = "net/minecraft/item/ItemStack";
      Go = "net/minecraft/client/multiplayer/WorldClient";
      Gw = "net/minecraft/client/Minecraft";
      S = "net/minecraft/util/MovingObjectPosition";
   }

   public static String B(String var0) {
      return Expo.ASM.Util.AsmUtil.R(C(var0));
   }

   public static String W(String var0, String var1, String var2) {
      return Expo.ASM.Util.AsmUtil.S(var0, var1, var2);
   }

   public static String k(String var0, String var1) {
      return Expo.ASM.Util.AsmUtil.w(var0, var1);
   }

   public static String O(String var0) {
      return Expo.ASM.Util.AsmUtil.X(var0);
   }

   public static String M(String... var0) {
      return o("Z", var0);
   }

   public static String V(String... var0) {
      return o("V", var0);
   }

   public static String T(String var0) {
      return var0.replace((char)47, (char)46);
   }

   private SrgNames() {
   }
}
