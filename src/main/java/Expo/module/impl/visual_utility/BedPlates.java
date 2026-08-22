package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.ExpoClient;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BedPlatesBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.BlockUtil;
import Expo.util.LunarClientDetector;
import Expo.util.render.ColorUtil;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class BedPlates extends Module implements EventSubscriber {
   private final int d;
   private final int F;
   private static Map h;
   public static BooleanSetting outline;
   private final int J;
   private static Map p;
   private final HashMap<BlockPos, Set<Block>> a;
   private static long b;
   private static String[] e;
   public static BooleanSetting fill;
   public static PercentageSetting backgroundOpacity;
   private static Object[] r;
   public static ModeSetting color;
   private static String[] s;
   public static ColorSetting customColor;
   public static NumberSetting surroundingRange;
   private static String[] c;
   private static long[] m;

   public final void x(long var1, EventBus var3) {
      BedPlatesBinder.y(var3, this);
   }

   private BlockPos O(BlockPos var1) {
      IBlockState var2 = f.theWorld.getBlockState(var1);
      if (var2.getBlock() != Blocks.bed) {
         return null;
      }

      EnumFacing var3 = (EnumFacing)var2.getValue(BlockBed.FACING);
      return var1.offset(var3.getOpposite());
   }

   static {
      b = 130834582854816L;
      zkm$clinit();
   }

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      CustomFont var39 = Font.s(0L);
      int var40;
      switch (color.Y()) {
         case "THEME":
            var40 = Theme.S(0.0, 35338930340239L);
            break;
         case "THEME_CUSTOM":
            var40 = Theme.X(65301174328177L, 0.0);
            break;
         default:
            var40 = customColor.k(96531491288662L);
      }

      for (Entry var76 : this.a.entrySet()) {
         BlockPos var43 = (BlockPos)var76.getKey();
         Set var44 = (Set)var76.getValue();
         BlockPos var45 = this.O(var43);
         double var46;
         double var48;
         double var50;
         if (var45 != null) {
            var46 = (var43.getX() + var45.getX()) / 2.0 + 0.5;
            var48 = (var43.getY() + var45.getY()) / 2.0 + 1.2;
            var50 = (var43.getZ() + var45.getZ()) / 2.0 + 0.5;
         } else {
            var46 = var43.getX() + 0.5;
            var48 = var43.getY() + 1.2;
            var50 = var43.getZ() + 0.5;
         }

         double var52 = var46 - RenderManagerAccessor.k(0L, f.getRenderManager());
         double var54 = var48 - RenderManagerAccessor.y(13236, f.getRenderManager());
         double var56 = var50 - RenderManagerAccessor.W(0L, f.getRenderManager());
         GlStateManager.pushMatrix();
         GlStateManager.translate(var52, var54, var56);
         GlStateManager.rotate(-f.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(f.getRenderManager().playerViewX, LunarClientDetector.q(0L) ? 1.0F : this.H(), 0.0F, 0.0F);
         double var58 = f.getRenderViewEntity().getDistance(var43.getX(), var43.getY(), var43.getZ());
         double var60 = Math.pow(Math.min(Math.max(var58, 6.0), 128.0), 0.75) * 0.005;
         GlStateManager.scale(-var60, -var60, var60);
         GlStateManager.disableDepth();
         String var64 = "EMPTY";
         float var62;
         float var63;
         if (var44.isEmpty()) {
            var62 = var39.R(var64, 52019766876817L) + 10.0F;
            var63 = var39.o(60714858652844L) + 10.0F;
         } else {
            var62 = var44.size() * 16 + (var44.size() - 1) * 2 + 10;
            var63 = 26.0F;
         }

         float var65 = -(var62 / 2.0F);
         float var66 = -(var63 / 2.0F);
         float var67 = var62 / 2.0F;
         float var68 = var63 / 2.0F;
         int var69;
         if (fill.c()) {
            var69 = new Color(ColorUtil.l(var40,0L), ColorUtil.U(0L, var40), ColorUtil.d(0L, var40), (int)(2.55 * backgroundOpacity.k())).getRGB();
         } else {
            var69 = new Color(0, 0, 0, (int)(2.55 * backgroundOpacity.k())).getRGB();
         }

         if (outline.c()) {
            Expo.util.render.RenderUtil.m(var65, var66, var67, var68, 3.0F, var69, var40, 45584246178720L, var40, var40);
         } else {
            Expo.util.render.RenderUtil.j(var65, var66, var67, var68, 3.0F, 4113131265056L, var69);
         }

         if (var44.isEmpty()) {
            var39.X(var64, var65 + 5.0F, 90289579616747L, var66 + 5.0F, -1);
         } else {
            float var70 = var65 + 5.0F;
            float var71 = var66 + 5.0F;

            for (Block var73 : (Iterable<Block>)(var44)) {
               this.U(new ItemStack(Item.getItemFromBlock(var73)), (int)var70, (int)var71);
               var70 += 18.0F;
            }
         }

         GlStateManager.enableDepth();
         GlStateManager.popMatrix();
      }
   }

   public BedPlates(long var1) {
      super(((b ^ (var1)) ^ 84481071993970L));
      this.declare("BedPlates", Category.Visual_utility, "Show surrounding blocks of beds");
      var1 = b ^ var1;
      this.a = new HashMap<>();
      this.F = 5;
      this.J = 2;
      this.d = 16;
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private static void a() {
      r[0] = "7=1s80\u0011";
      r[1] = "C\u0005z.D\u000bt\u0012~$\t/c\u0019$8";
      r[2] = short.class;
      s[2] = "java/lang/Short";
      r[3] = char.class;
      s[3] = "java/lang/Character";
      r[4] = "q^n\u001717G";
      r[5] = int.class;
      s[5] = "java/lang/Integer";
      r[6] = void.class;
      s[6] = "java/lang/Void";
      r[7] = "}b#\u000es\u000fvm2A\u0012\u0001}f6\u001b";
      r[8] = " &\u0011)y.'3(i\u001bmv7Kwa00?C\u0018\"5/!\u0011c*+%=(\"pm#+M#p;pM\u0013)\u007fm:*Z%&hJvOcd3w,V\"pTvsDd`(w0Q}\u001b";
   }

   private Set<Block> Q(BlockPos var1, int var2) {
      LinkedHashSet<Block> var3 = new LinkedHashSet<>();
      HashSet var4 = new HashSet();
      LinkedList var5 = new LinkedList();
      var5.add(var1);
      var4.add(var1);

      while (!var5.isEmpty()) {
         BlockPos var6 = (BlockPos)var5.poll();

         for (EnumFacing var10 : EnumFacing.values()) {
            BlockPos var11 = var6.offset(var10);
            if (!var4.contains(var11) && var11.getY() >= var1.getY() && !(var11.distanceSq(var1) > var2 * var2)) {
               Block var12 = f.theWorld.getBlockState(var11).getBlock();
               if (!BlockUtil.f(var12)) {
                  var4.add(var11);
                  var5.add(var11);
                  if (var12 != Blocks.bed) {
                     var3.add(this.E(var12));
                  }
               }
            }
         }
      }

      var3.removeIf(var1x -> !this.h(var1x));
      return var3;
   }

   private float H() {
      return f.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
   }

   public void onPreUpdate(PreUpdateEvent var1) {
      for (BlockPos var3 : ExpoClient.G) {
         IBlockState var4 = f.theWorld.getBlockState(var3);
         if (var4.getBlock() instanceof BlockBed && var4.getValue(BlockBed.PART) == EnumPartType.HEAD) {
            LinkedHashSet var5 = new LinkedHashSet<>(this.Q(var3, (int)surroundingRange.L()));
            BlockPos var6 = this.O(var3);
            if (var6 != null) {
               var5.addAll(this.Q(var6, (int)surroundingRange.L()));
            }

            this.a.put(var3, var5);
         }
      }

      this.a.keySet().removeIf(BlockUtil::a$r1);
   }

   private Block E(Block var1) {
      if (var1 == Blocks.wool) {
         return Blocks.wool;
      } else {
         return var1 != Blocks.stained_glass && var1 != Blocks.stained_glass_pane ? var1 : Blocks.glass;
      }
   }

   private boolean h(Block var1) {
      return var1 == Blocks.end_stone
         || var1 == Blocks.wool
         || var1 == Blocks.glass
         || var1 == Blocks.planks
         || var1 == Blocks.log
         || var1 == Blocks.log2
         || var1 == Blocks.obsidian
         || var1 == Blocks.clay
         || var1 == Blocks.hardened_clay
         || var1 == Blocks.stained_hardened_clay
         || var1 == Blocks.ice
         || var1 == Blocks.packed_ice;
   }

   public void A(long var1) {
      this.a.clear();
   }

   private void U(ItemStack var1, int var2, int var3) {
      GlStateManager.pushMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.clear(256);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glDisable(2896);
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F, 1.0F, -0.01F);
      f.getRenderItem().zLevel = -150.0F;
      f.getRenderItem().renderItemAndEffectIntoGUI(var1, var2, var3);
      f.getRenderItem().renderItemOverlays(f.fontRendererObj, var1, var2, var3);
      f.getRenderItem().zLevel = 0.0F;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      GlStateManager.disableDepth();
      GlStateManager.enableDepth();
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      GlStateManager.popMatrix();
   }
   private static void zkm$clinit() {
      try {
         r = new Object[9];
         s = new String[9];
         a();
         h = new HashMap(13);
         long var11 = b ^ 13899860389197L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[3];
         int var18 = 0;
         String var17 = "Î\u0000=\u0082\u0098Y\u0096/²\\Ñ§ï\u0014ç< 2ç^¶\u0015\u0098X\nì\u0098¢Äû\u0002¥\u0010\t\rÓ\\\u008a;ç\u0084«\u0094&\u001bgþ§T\u0010º\u008a\u0010iHÅÇ2e¼!·\u0003\u000e/3";
         int var19 = "Î\u0000=\u0082\u0098Y\u0096/²\\Ñ§ï\u0014ç< 2ç^¶\u0015\u0098X\nì\u0098¢Äû\u0002¥\u0010\t\rÓ\\\u008a;ç\u0084«\u0094&\u001bgþ§T\u0010º\u008a\u0010iHÅÇ2e¼!·\u0003\u000e/3"
            .length();
         char var16 = ' ';
         int var15 = -1;

         while (true) {
            byte[] var21 = var13.doFinal(var17.substring(++var15, var15 + var16).getBytes("ISO-8859-1"));
            String var31 = b(var21).intern();
            int var10001 = -1;
            var20[var18++] = var31;
            if ((var15 += var16) >= var19) {
               c = var20;
               e = new String[3];
               p = new HashMap(13);
               Cipher var0;
               var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

               for (int var1 = 1; var1 < 8; var1++) {
                  var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
               }

               (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
               long[] var6 = new long[7];
               int var3 = 0;
               String var4 = "¸³X~F Ç\rU\u0092¨gí\u0012\u001aø×Ëïaa\u0011F\u009eY\u000e\u0080\u008f4Þ>\u009az)\u001anLW·\u007f";
               int var5 = "¸³X~F Ç\rU\u0092¨gí\u0012\u001aø×Ëïaa\u0011F\u009eY\u000e\u0080\u008f4Þ>\u009az)\u001anLW·\u007f".length();
               int var2 = 0;

               label36:
               while (true) {
                  var10001 = var2;
                  var2 += 8;
                  byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                  long[] var26 = var6;
                  var10001 = var3++;
                  long var34 = (var7[0] & 255L) << 56
                     | (var7[1] & 255L) << 48
                     | (var7[2] & 255L) << 40
                     | (var7[3] & 255L) << 32
                     | (var7[4] & 255L) << 24
                     | (var7[5] & 255L) << 16
                     | (var7[6] & 255L) << 8
                     | var7[7] & 255L;
                  int var37 = -1;

                  while (true) {
                     long var8 = var34;
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
                     long var39 = (var10[0] & 255L) << 56
                        | (var10[1] & 255L) << 48
                        | (var10[2] & 255L) << 40
                        | (var10[3] & 255L) << 32
                        | (var10[4] & 255L) << 24
                        | (var10[5] & 255L) << 16
                        | (var10[6] & 255L) << 8
                        | var10[7] & 255L;
                     switch (var37) {
                        case 0:
                           var26[var10001] = var39;
                           if (var2 >= var5) {
                              m = var6;
                              return;
                           }
                           break;
                        default:
                           var26[var10001] = var39;
                           if (var2 < var5) {
                              continue label36;
                           }

                           var4 = "ì\u0012h¢ºï\u008a°\u0010!üc\u0005Q\u007fû";
                           var5 = "ì\u0012h¢ºï\u008a°\u0010!üc\u0005Q\u007fû".length();
                           var2 = 0;
                     }

                     int var30 = var2;
                     var2 += 8;
                     var7 = var4.substring(var30, var2).getBytes("ISO-8859-1");
                     var26 = var6;
                     var10001 = var3++;
                     var34 = (var7[0] & 255L) << 56
                        | (var7[1] & 255L) << 48
                        | (var7[2] & 255L) << 40
                        | (var7[3] & 255L) << 32
                        | (var7[4] & 255L) << 24
                        | (var7[5] & 255L) << 16
                        | (var7[6] & 255L) << 8
                        | var7[7] & 255L;
                     var37 = 0;
                  }
               }
            }

            var16 = var17.charAt(var15);
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }
   static {
      backgroundOpacity = new PercentageSetting("Background-opacity", 40);
      customColor = new ColorSetting("Custom-color", "000000");
   }
   static {
      outline = new BooleanSetting("Outline", true);
      fill = new BooleanSetting("Fill", false);
   }
   static {
      surroundingRange = new NumberSetting("Surrounding-range", 5.0F, 0.0F, 10.0F, 1.0F);
   }
   static {
      color = new ModeSetting("Color", false, "CUSTOM", "THEME", "THEME_CUSTOM", "CUSTOM");
   }
}
