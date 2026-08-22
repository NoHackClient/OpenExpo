package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BlocksESPBinder;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render3DEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.RaytraceUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class BlocksESP extends Module implements EventSubscriber {
   public static BooleanSetting tracers;
   public static HeaderSetting oresSettings;
   private static Set<Vec3i> Y;
   private static Set<Vec3i> a;
   private static String[] u;
   private static Object[] o;
   public static BooleanSetting redstone;
   private static Minecraft s;
   private static long[] e;
   public static BooleanSetting lapis;
   public static BooleanSetting coal;
   public static NumberSetting range;
   public static PercentageSetting opacity;
   private static long d;
   public static BooleanSetting gold;
   public static BooleanSetting diamond;
   public static NumberSetting cavesRadius;
   public static BooleanSetting emerald;
   private static Map n;
   public static Set<BlockPos> L;
   public static BooleanSetting spawner;
   public static BooleanSetting shade;
   public static Set<BlockPos> x;
   public static BooleanSetting iron;
   public static BooleanSetting obsidian;
   public static BooleanSetting outline;
   public static BooleanSetting cavesOnly;

   static {
      d = 610396979265L;
      zkm$clinit();
      s = MinecraftRef.c((byte)0, 0L);
   }

   public final void x(long var1, EventBus var3) {
      BlocksESPBinder.b(var3, this);
   }

   public BlocksESP(long var1) {
      super(((d ^ (var1)) ^ 89300791270915L));
      this.declare("BlocksESP", Category.Visual_utility, "Highlight some blocks");
      var1 = d ^ var1;
   }

   public static boolean y(BlockPos var0) {
      if (!cavesOnly.c()) {
         return true;
      }

      if (cavesRadius.L() >= 2.0F) {
         for (Vec3i var2 : a) {
            if (U(var0.add(var2))) {
               return true;
            }
         }
      } else {
         for (Vec3i var4 : Y) {
            if (U(var0.add(var4))) {
               return true;
            }
         }
      }

      return false;
   }

   private Color x(char var1, Block var2, int var3) {
      if (var2 == Blocks.gold_ore) {
         return new Color(16777093);
      } else if (var2 == Blocks.iron_ore) {
         return new Color(16777215);
      } else if (var2 == Blocks.coal_ore) {
         return new Color(0);
      } else if (var2 == Blocks.lapis_ore) {
         return new Color(5592575);
      } else if (var2 == Blocks.redstone_ore || var2 == Blocks.lit_redstone_ore) {
         return new Color(16729157);
      } else if (var2 == Blocks.diamond_ore) {
         return new Color(5636095);
      } else if (var2 == Blocks.emerald_ore) {
         return new Color(5635925);
      } else {
         return var2 == Blocks.obsidian ? new Color(11141290) : new Color(-1);
      }
   }

   private static void a() {
      o[0] = "b \u0011\u001e\"PK";
      o[1] = long.class;
      u[1] = "java/lang/Long";
      o[2] = "\u0012\u0018^=~^%\u000fZ73z2\u0004\u0000+";
      o[3] = "8I\r=\u0019P\u001a";
      o[4] = void.class;
      u[4] = "java/lang/Void";
      o[5] = "S\u00008qqpX\u000f)>\u0010~S\u0004-d";
      o[6] = "\u0004 \u001c#!A\u001dg\u0013\u001dq0\u001df\u001ay'\\\u0012hG\u001d\"I[!\u0005o+WZcz'v\nZ \u0019\u007f\u007f\f\u001e\u0019@`\u007fL\u0006$\u0004s#\tb";
   }

   private static boolean U(BlockPos var0) {
      if (!s.theWorld.isBlockLoaded(var0, false)) {
         return false;
      }

      Block var1 = s.theWorld.getBlockState(var0).getBlock();
      return var1 instanceof BlockMobSpawner || !var1.isFullBlock() || !var1.getMaterial().isOpaque() || var1.canProvidePower();
   }

   public void A(long var1) {
      L.clear();
      x.clear();
   }

   public void onReceivePacket(ReceivePacketEvent var1) {
      if (var1.d instanceof S22PacketMultiBlockChange) {
         for (BlockUpdateData var5 : ((S22PacketMultiBlockChange)var1.d).getChangedBlocks()) {
            if (L(var5.getBlockState().getBlock())) {
               x.add(new BlockPos(var5.getPos()));
            }
         }
      } else if (var1.d instanceof S23PacketBlockChange) {
         S23PacketBlockChange var6 = (S23PacketBlockChange)var1.d;
         if (L(var6.getBlockState().getBlock())) {
            x.add(new BlockPos(var6.getBlockPosition()));
         }
      }
   }

   public void onRender3D(long var1, Render3DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      Vec3 var10;
      if (s.gameSettings.thirdPersonView == 0) {
         var10 = new Vec3(0.0, 0.0, 1.0)
            .rotatePitch((float)(-Math.toRadians(MathUtil.k(s.getRenderViewEntity().rotationPitch, s.getRenderViewEntity().prevRotationPitch, Expo.util.ClientUtil.H(112506723048534L)))))
            .rotateYaw((float)(-Math.toRadians(MathUtil.k(s.getRenderViewEntity().rotationYaw, s.getRenderViewEntity().prevRotationYaw, Expo.util.ClientUtil.H(112506723048534L)))));
      } else {
         var10 = new Vec3(0.0, 0.0, 0.0)
            .rotatePitch((float)(-Math.toRadians(MathUtil.k(s.thePlayer.cameraPitch, s.thePlayer.prevCameraPitch, Expo.util.ClientUtil.H(112506723048534L)))))
            .rotateYaw((float)(-Math.toRadians(MathUtil.k(s.thePlayer.cameraYaw, s.thePlayer.prevCameraYaw, Expo.util.ClientUtil.H(112506723048534L)))));
      }

      var10 = new Vec3(var10.xCoord, var10.yCoord + s.getRenderViewEntity().getEyeHeight(), var10.zCoord);
      Expo.util.render.RenderUtil.L();

      for (BlockPos var12 : L) {
         if (x.contains(var12)) {
            L.remove(var12);
         } else if (L(s.theWorld.getBlockState(var12).getBlock()) && y(var12)) {
            this.u(var12, 114872601337382L, s.theWorld.getBlockState(var12).getBlock(), var10);
         } else {
            L.remove(var12);
         }
      }

      for (BlockPos var16 : x) {
         if (L(s.theWorld.getBlockState(var16).getBlock()) && y(var16)) {
            this.u(var16, 114872601337382L, s.theWorld.getBlockState(var16).getBlock(), var10);
         } else {
            x.remove(var16);
         }
      }

      Expo.util.render.RenderUtil.w();
   }

   public static boolean L(Block var0) {
      return iron.c() && var0 == Blocks.iron_ore
         || gold.c() && var0 == Blocks.gold_ore
         || diamond.c() && var0 == Blocks.diamond_ore
         || emerald.c() && var0 == Blocks.emerald_ore
         || lapis.c() && var0 == Blocks.lapis_ore
         || redstone.c() && var0 == Blocks.redstone_ore
         || coal.c() && var0 == Blocks.coal_ore
         || spawner.c() && var0 == Blocks.mob_spawner
         || obsidian.c() && var0 == Blocks.obsidian;
   }

   public void i(long var1) {
      s.renderGlobal.loadRenderers();
   }

   public void onEntityJoinWorld(EntityJoinWorldEvent var1) {
      if (var1.H instanceof EntityPlayerSP) {
         L.clear();
         x.clear();
      }
   }

   private void u(BlockPos var1, long var2, Block var4, Vec3 var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (RaytraceUtil.Y(var1, range.L(), 119767551018300L)) {
         Color var17 = this.x((char)0, var4, 1134423564);
         if (outline.c()) {
            Expo.util.render.RenderUtil.n(var1, 1.0, var17.getRed(), var17.getGreen(), var17.getBlue(), 114394550953247L, 255, 1.5F);
         }

         if (shade.c()) {
            Expo.util.render.RenderUtil.C(var1, 1.0, var17.getRed(), 96914206771396L, var17.getGreen(), var17.getBlue(), (int)(2.55 * opacity.k()));
         }

         if (tracers.c()) {
            Expo.util.render.RenderUtil.r(
               var5,
               92754948049078L,
               var1.getX() + 0.5,
               var1.getY() + 0.5,
               var1.getZ() + 0.5,
               var17.getRed() / 255.0F,
               var17.getGreen() / 255.0F,
               var17.getBlue() / 255.0F,
               1.0F,
               1.5F
            );
         }
      }
   }
   private static void zkm$clinit() {
      try {
         long var11 = d ^ 24998517968569L;
         o = new Object[7];
         u = new String[7];
         a();
         n = new HashMap(13);
         Cipher var0;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var1 = 1; var1 < 8; var1++) {
            var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
         }

         (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var6 = new long[30];
         int var3 = 0;
         String var4 = "Mäwÿfp½Ò|²Êu\u001b©\u0084M\u0095° ´Þµ\u001d°Ï²\u008eY\tÉ\u0085\u0015`&\u0007\u0085\u008f_8è\u0087Ø§V©3Ü\u0017{óF1Ö\u0018ÑbªùñZÂhxÉ`8\u008cê7ìUû\u000bíAµä-Û]+GI\n\u009b¿ïNÚ\u0015(Õc?ÂMª#ÒI¬YL\u0001q\u001aY\u001f±\u0010\u0005\u00adç!6eØ_\u001a\\=ùnQÙ¾Èâ~¶:o\u0097VQ\u0099Ò(º&Ñ\u0019ÅÉ\u007f©\u0001#±®\u0010þö\u008fE\u0012ãI£N¨Gì¡L\u007f÷\u0089Ç²sOu\u0095y\u009fúÈÅ*ñæ\u0003\u0012ýYK\u008f#C(Î\u0096ÝëÝ\u001b¦]õÔk\u0099!ÊçÔ¸\u0006\u0094\u0081v;±\u0089y\u00ad7I\b×â¿@";
         int var5 = "Mäwÿfp½Ò|²Êu\u001b©\u0084M\u0095° ´Þµ\u001d°Ï²\u008eY\tÉ\u0085\u0015`&\u0007\u0085\u008f_8è\u0087Ø§V©3Ü\u0017{óF1Ö\u0018ÑbªùñZÂhxÉ`8\u008cê7ìUû\u000bíAµä-Û]+GI\n\u009b¿ïNÚ\u0015(Õc?ÂMª#ÒI¬YL\u0001q\u001aY\u001f±\u0010\u0005\u00adç!6eØ_\u001a\\=ùnQÙ¾Èâ~¶:o\u0097VQ\u0099Ò(º&Ñ\u0019ÅÉ\u007f©\u0001#±®\u0010þö\u008fE\u0012ãI£N¨Gì¡L\u007f÷\u0089Ç²sOu\u0095y\u009fúÈÅ*ñæ\u0003\u0012ýYK\u008f#C(Î\u0096ÝëÝ\u001b¦]õÔk\u0099!ÊçÔ¸\u0006\u0094\u0081v;±\u0089y\u00ad7I\b×â¿@"
            .length();
         int var2 = 0;

         label27:
         while (true) {
            int var10001 = var2;
            var2 += 8;
            byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
            long[] var18 = var6;
            var10001 = var3++;
            long var21 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            int var25 = -1;

            while (true) {
               long var8 = var21;
               byte[] var10 = var0.doFinal(
                  new byte[]{
                     (byte)(var8 >>> 56),
                     (byte)(var8 >>> 48),
                     (byte)(var8 >>> 40),
                     (byte)(var8 >>> 32),
                     (byte)(var8 >>> 24),
                     (byte)(var8 >>> 16),
                     (byte)(var8 >>> 8),
                     (byte)var8
                  }
               );
               long var27 = (var10[0] & 255L) << 56
                  | (var10[1] & 255L) << 48
                  | (var10[2] & 255L) << 40
                  | (var10[3] & 255L) << 32
                  | (var10[4] & 255L) << 24
                  | (var10[5] & 255L) << 16
                  | (var10[6] & 255L) << 8
                  | var10[7] & 255L;
               switch (var25) {
                  case 0:
                     var18[var10001] = var27;
                     if (var2 >= var5) {
                        e = var6;
                        L = ConcurrentHashMap.newKeySet();
                        x = ConcurrentHashMap.newKeySet();
                        Vec3i[] var23 = new Vec3i[6];
                        var23[0] = new Vec3i(0, -1, 0);
                        var23[1] = new Vec3i(1, 0, 0);
                        var23[2] = new Vec3i(0, 0, -1);
                        var23[3] = new Vec3i(0, 0, 1);
                        var23[4] = new Vec3i(-1, 0, 0);
                        var23[5] = new Vec3i(0, 1, 0);
                        Y = new LinkedHashSet<>(Arrays.asList(var23));
                        Vec3i[] var24 = new Vec3i[24];
                        var24[0] = new Vec3i(0, -2, 0);
                        var24[1] = new Vec3i(1, -1, 0);
                        var24[2] = new Vec3i(0, -1, -1);
                        var24[3] = new Vec3i(0, -1, 0);
                        var24[4] = new Vec3i(0, -1, 1);
                        var24[5] = new Vec3i(-1, -1, 0);
                        var24[6] = new Vec3i(2, 0, 0);
                        var24[7] = new Vec3i(0, 0, 2);
                        var24[8] = new Vec3i(0, 0, -2);
                        var24[9] = new Vec3i(-2, 0, 0);
                        var24[10] = new Vec3i(1, 0, -1);
                        var24[11] = new Vec3i(1, 0, 0);
                        var24[12] = new Vec3i(1, 0, 1);
                        var24[13] = new Vec3i(0, 0, -1);
                        var24[14] = new Vec3i(0, 0, 1);
                        var24[15] = new Vec3i(-1, 0, -1);
                        var24[16] = new Vec3i(-1, 0, 0);
                        var24[17] = new Vec3i(-1, 0, 1);
                        var24[18] = new Vec3i(1, 1, 0);
                        var24[19] = new Vec3i(0, 1, -1);
                        var24[20] = new Vec3i(0, 1, 0);
                        var24[21] = new Vec3i(0, 1, 1);
                        var24[22] = new Vec3i(-1, 1, 0);
                        var24[23] = new Vec3i(0, 2, 0);
                        a = new LinkedHashSet<>(Arrays.asList(var24));
                        return;
                     }
                     break;
                  default:
                     var18[var10001] = var27;
                     if (var2 < var5) {
                        continue label27;
                     }

                     var4 = "\u0017\u0001c·þ.E\u0081\u0098©\u0005\u008e]8å:";
                     var5 = "\u0017\u0001c·þ.E\u0081\u0098©\u0005\u008e]8å:".length();
                     var2 = 0;
               }

               int var20 = var2;
               var2 += 8;
               var7 = var4.substring(var20, var2).getBytes("ISO-8859-1");
               var18 = var6;
               var10001 = var3++;
               var21 = (var7[0] & 255L) << 56
                  | (var7[1] & 255L) << 48
                  | (var7[2] & 255L) << 40
                  | (var7[3] & 255L) << 32
                  | (var7[4] & 255L) << 24
                  | (var7[5] & 255L) << 16
                  | (var7[6] & 255L) << 8
                  | var7[7] & 255L;
               var25 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var16) {
         throw new RuntimeException(var16);
      }
   }

   static {
      coal = new BooleanSetting("Coal", false);
      lapis = new BooleanSetting("Lapis", false);
      tracers = new BooleanSetting("Tracers", false);
      shade = new BooleanSetting("Shade", true);
      cavesRadius = new NumberSetting("Caves-radius", 2.0F, 1.0F, 2.0F, 1.0F);
      range = new NumberSetting("Range", 128.0F, 0.0F, 512.0F, 1.0F);
      spawner = new BooleanSetting("Spawner", false);
      diamond = new BooleanSetting("Diamond", true);
      cavesOnly = new BooleanSetting("Caves-only", true);
      iron = new BooleanSetting("Iron", false);
      opacity = new PercentageSetting("Opacity", 40);
      gold = new BooleanSetting("Gold", false);
      obsidian = new BooleanSetting("Obsidian", false);
      redstone = new BooleanSetting("Redstone", false);
      emerald = new BooleanSetting("Emerald", false);
      outline = new BooleanSetting("Outline", true);
   }
   static {
      oresSettings = new HeaderSetting("Ores settings");
   }
}
