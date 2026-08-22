package Expo.util.render;

import Expo.internal.accessor.EntityRendererAccessor;
import Expo.internal.accessor.MinecraftAccessor;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.ui.raven.RavenClickGuiScreen;
import Expo.util.ClientUtil;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class RenderUtil {
   private static Frustum Z;
   private static String[] c;
   private static RenderItem R;
   private static FloatBuffer D;
   private static Frustum l;
   private static Map h;
   private static IntBuffer A;
   private static Long[] j;
   private static Map k;
   private static int a;
   protected static float X;
   private static FloatBuffer G;
   private static Map e;
   private static String[] d;
   private static Minecraft T;
   private static long b;
   private static long[] f;
   private static long[] i;
   private static FloatBuffer Y;
   private static int n;

   public static void s(EntityLivingBase var0, double var1, int var3, float var4, int var5, int var6, char var7, char var8) {
      long var9 = ((long)var6 << 32 | (long)var7 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ b;
      long var11 = var9 ^ 17048694565743L;
      double var13 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * ClientUtil.b(var11).renderPartialTicks - T.getRenderManager().viewerPosX;
      double var15 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * ClientUtil.b(var11).renderPartialTicks - T.getRenderManager().viewerPosY;
      double var17 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * ClientUtil.b(var11).renderPartialTicks - T.getRenderManager().viewerPosZ;
      GlStateManager.pushMatrix();
      float var19 = (var5 >> 24 & 255) / 255.0F;
      float var20 = (var5 >> 16 & 255) / 255.0F;
      float var21 = (var5 >> 8 & 255) / 255.0F;
      float var22 = (var5 & 255) / 255.0F;
      T.entityRenderer.disableLightmap();
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glDepthMask(false);
      GL11.glLineWidth(var4);
      GL11.glColor4f(var20, var21, var22, var19);
      GL11.glBegin(1);

      for (int var29 = 0; var29 < var3 * 2; var29++) {
         double var30 = (Math.PI * 2) * var29 / var3 + Math.toRadians(180.0);
         GL11.glVertex3d(var13 + Math.cos(var30) * var1, var15, var17 + Math.sin(var30) * var1);
      }

      GL11.glEnd();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      T.entityRenderer.enableLightmap();
      GlStateManager.popMatrix();
   }

   public static Color x(int var0, long var1) {
      Color var3 = new Color(var0);
      return new Color(var3.getRed(), var3.getGreen(), var3.getBlue(), 255);
   }

   public static void j(AxisAlignedBB var0, int var1, long var2, boolean var4, boolean var5) {
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      float var8 = (var1 >> 24 & 255) / 255.0F;
      float var9 = (var1 >> 16 & 255) / 255.0F;
      float var10 = (var1 >> 8 & 255) / 255.0F;
      float var11 = (var1 & 255) / 255.0F;
      if (var4) {
         GL11.glColor4f(var9, var10, var11, 1.0F);
         RenderGlobal.drawSelectionBoundingBox(var0);
      }

      if (var5) {
         GL11.glColor4f(var9, var10, var11, var8);
         q(var0, 65724172677490L, var9, var10, var11);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glPopMatrix();
   }

   public static void Z(AxisAlignedBB var0, double var1, double var3, double var5, double var7, double var9, long var11, double var13, int var15) {
      var11 = b ^ var11;
      int var21 = (int)((var11 ^ 4612758227386L) >>> 32);
      int var22 = (int)((var11 ^ 4612758227386L) << 32 >>> 48);
      float var27 = MinecraftAccessor.o( T).renderPartialTicks;
      double var28 = var1 + (var7 - var1) * var27 - T.getRenderManager().viewerPosX;
      double var30 = var3 + (var9 - var3) * var27 - T.getRenderManager().viewerPosY;
      double var32 = var5 + (var13 - var5) * var27 - T.getRenderManager().viewerPosZ;
      GlStateManager.pushMatrix();
      if (var15 == 0) {
         long[] var24 = new long[]{0L};

         var15 = M(var21, (short)var22, 2L, var24);
      }

      float var34 = (var15 >> 24 & 255) / 255.0F;
      float var35 = (var15 >> 16 & 255) / 255.0F;
      float var36 = (var15 >> 8 & 255) / 255.0F;
      float var37 = (var15 & 255) / 255.0F;
      AxisAlignedBB var38 = var0;
      AxisAlignedBB var39 = new AxisAlignedBB(
         var38.minX - var7 + var28,
         var38.minY - var9 + var30,
         var38.minZ - var13 + var32,
         var38.maxX - var7 + var28,
         var38.maxY - var9 + var30,
         var38.maxZ - var13 + var32
      );
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glLineWidth(2.0F);
      GL11.glColor4f(var35, var36, var37, var34);
      D(var39, var35, var36, var37, var34);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GlStateManager.popMatrix();
   }

   public static void l(AxisAlignedBB var0, int var1, int var5, int var6, int var7) {
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var5, var6, var7).endVertex();
      var10.draw();
   }

   public static void M() {
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void N(long var0, char var2) {
      GL11.glEnable(3089);
   }

   public static void m(ItemStack var0, int var1, int var2) {
      GlStateManager.pushMatrix();
      RenderHelper.enableGUIStandardItemLighting();
      R.renderItemAndEffectIntoGUI(var0, var1, var2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   public static void H(float var0, float var1, long var2, float var4, float var5, int var6) {
      var2 = b ^ var2;
      int var7 = (int)((var2 ^ 3942401904063L) >>> 48);
      int var8 = (int)((var2 ^ 3942401904063L) << 16 >>> 48);
      float var12 = (var6 >> 24 & 255) / 255.0F;
      float var13 = (var6 >> 16 & 255) / 255.0F;
      float var14 = (var6 >> 8 & 255) / 255.0F;
      float var15 = (var6 & 255) / 255.0F;
      Color var10 = new Color(var13, var14, var15, var12);
      float var11 = var5;
      G(var0, var1, var4, (char)var7, var11, (char)var8, var10);
   }

   public static void n(double var0, double var2, double var4, long var6, int var8, int var9) {
      if (var8 >= 3) {
         float var10 = (var9 >> 24 & 255) / 255.0F;
         float var11 = (var9 >> 16 & 255) / 255.0F;
         float var12 = (var9 >> 8 & 255) / 255.0F;
         float var13 = (var9 & 255) / 255.0F;
         Tessellator var14 = Tessellator.getInstance();
         WorldRenderer var15 = var14.getWorldRenderer();
         GlStateManager.enableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GL11.glColor4f(var11, var12, var13, var10);
         var15.begin(6, DefaultVertexFormats.POSITION);

         for (int var16 = 0; var16 < var8; var16++) {
            double var17 = (Math.PI * 2) * var16 / var8 + Math.toRadians(180.0);
            var15.pos(var0 + Math.sin(var17) * var4, var2 + Math.cos(var17) * var4, 0.0).endVertex();
         }

         var14.draw();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
      }
   }

   public static void O(double var0, double var2, double var4, long var6, double var8, double var10, double var12, int var14, boolean var15, boolean var16) {
      double var19 = var0 - T.getRenderManager().viewerPosX;
      double var21 = var2 - T.getRenderManager().viewerPosY;
      double var23 = var4 - T.getRenderManager().viewerPosZ;
      AxisAlignedBB var25 = new AxisAlignedBB(var19, var21, var23, var19 + var8, var21 + var10, var23 + var12);
      j(var25, var14, 137192391982620L, var15, var16);
   }

   public static int E(int var0, double var1, long var3) {
      if (var1 < 0.0 || var1 > 1.0) {
         var1 = 0.5;
      }

      int var5 = var0 >> 16 & 255;
      int var6 = var0 >> 8 & 255;
      int var7 = var0 & 255;
      int var8 = (int)(var1 * 255.0);
      return var8 << 24 | var5 << 16 | var6 << 8 | var7;
   }

   public static void q(long var0) {
      GL11.glDisable(3089);
   }

   public static void q(ItemStack var0, int var1, int var2, String var3) {
      GlStateManager.pushMatrix();
      RenderHelper.enableGUIStandardItemLighting();
      R.renderItemAndEffectIntoGUI(var0, var1, var2);
      R.renderItemOverlayIntoGUI(T.fontRendererObj, var0, var1, var2, var3);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.popMatrix();
   }

   public static void w() {
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void C(BlockPos var0, double var1, int var3, long var4, int var6, int var7, int var8) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var4 = b ^ var4;
      int var16 = (int)((var4 ^ 139177721112338L) >>> 32);
      l(
         new AxisAlignedBB(
               var0.getX(),
               var0.getY(),
               var0.getZ(),
               var0.getX() + 1.0,
               var0.getY() + var1,
               var0.getZ() + 1.0
            )
            .offset(-RenderManagerAccessor.k(0L, T.getRenderManager()), -RenderManagerAccessor.y(var16, T.getRenderManager()), -RenderManagerAccessor.W(0L, T.getRenderManager())),
         var3

,
         var6,
         var7,
         var8
      );
   }

   public static void h(AxisAlignedBB var0, int var1, int var2, int var3, int var4, long var5, boolean var7, boolean var8) {
      var5 = b ^ var5;
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      if (var7) {
         GL11.glColor4f(var1 / 255.0F, var2 / 255.0F, var3 / 255.0F, 1.0F);
         RenderGlobal.drawSelectionBoundingBox(var0);
      }

      if (var8) {
         GL11.glColor4f(var1 / 255.0F, var2 / 255.0F, var3 / 255.0F, var4 / 255.0F);
         D(var0, var1 / 255.0F, var2 / 255.0F, var3 / 255.0F, var4 / 255.0F);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glPopMatrix();
   }

   public static void R(int var0, long var1) {
      O(var0, (var0 >> 24 & 255) / 255.0F,0L);
   }

   public static void R(Entity var0, long var1, int var3, float var4) {
      double var8 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var4 - T.getRenderManager().viewerPosX;
      double var10 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var4 - T.getRenderManager().viewerPosY;
      double var12 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var4 - T.getRenderManager().viewerPosZ;
      float var14 = (var3 >> 24 & 255) / 255.0F;
      float var15 = (var3 >> 16 & 255) / 255.0F;
      float var16 = (var3 >> 8 & 255) / 255.0F;
      float var17 = (var3 & 255) / 255.0F;
      double var18 = 0.45;
      double var20 = 0.1;
      double var22 = var0.height + 0.1 - (var0.isSneaking() ? 0.2 : 0.0);
      AxisAlignedBB var24 = new AxisAlignedBB(var8 - var18, var10 + var22, var12 - var18, var8 + var18, var10 + var22 + var20, var12 + var18);
      GlStateManager.pushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glLineWidth(2.0F);
      GL11.glColor4f(var15, var16, var17, var14);
      RenderGlobal.drawSelectionBoundingBox(var24);
      D(var24, var15, var16, var17, var14);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GlStateManager.popMatrix();
   }

   public static boolean p(AxisAlignedBB var0, double var1) {
      l.setPosition(T.getRenderViewEntity().posX, T.getRenderViewEntity().posY, T.getRenderViewEntity().posZ);
      return l.isBoundingBoxInFrustum(var0.expand(var1, var1, var1));
   }

   public static void V(BlockPos var0, long var1, int var3, int var4) {
      r(var0.getX(), var0.getY(), var0.getZ(), 1.0, 1.0, 1.0, var3, var4);
   }

   public static void k(float var0, float var1, byte var2, float var3, float var4, float var5, long var6) {
      ScaledResolution var10 = new ScaledResolution(T);
      int var11 = var10.getScaleFactor();
      int var12;
      switch (var11) {
         case 2:
            var12 = 540;
            break;
         case 3:
            var12 = 1080;
            break;
         case 4:
            var12 = 270;
            break;
         default:
            var12 = var10.getScaledHeight();
      }

      GL11.glScissor((int)(var0 * var11 * var5), (int)((var12 - var1) * var11 * var5), (int)(var3 * var11 * var5), (int)(var4 * var11 * var5));
   }

   public static void a(long var0) {
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   public static void P(double var0, double var2, double var4, double var6) {
      ScaledResolution var8 = new ScaledResolution(T);
      int var9 = var8.getScaleFactor();
      int var10 = var8.getScaledHeight();
      GL11.glScissor((int)(var0 * var9), (int)((var10 - var2 - var6) * var9), (int)(var4 * var9), (int)(var6 * var9));
   }

   public static void R(AxisAlignedBB var0, int var1, long var2) {
      int var12 = ColorUtil.l(var1,0L);
      int var13 = ColorUtil.U(0L, var1);
      int var14 = ColorUtil.d(0L, var1);
      int var15 = ColorUtil.g(0L, var1);
      Tessellator var16 = Tessellator.getInstance();
      WorldRenderer var17 = var16.getWorldRenderer();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
      var17.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var17.pos(var0.minX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.minX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.minZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.maxY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var17.pos(var0.maxX, var0.minY, var0.maxZ).color(var12, var13, var14, var15).endVertex();
      var16.draw();
   }

   public static void G(float var0, float var1, float var2, char var3, float var4, char var5, Color var6) {
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GL11.glLineWidth(2.5F);
      GL11.glColor4f(var6.getRed() / 255.0F, var6.getGreen() / 255.0F, var6.getBlue() / 255.0F, var6.getAlpha() / 255.0F);
      GL11.glBegin(2);
      GL11.glVertex2f(var0, var1);
      GL11.glVertex2f(var2, var1);
      GL11.glVertex2f(var2, var4);
      GL11.glVertex2f(var0, var4);
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static void A(Entity var0, long var1, int var3, float var4, double var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var1 = b ^ var1;
      int var22 = (int)((var1 ^ 50841124698123L) >>> 32);
      double var25 = MathUtil.h(var0.posX, var0.lastTickPosX, ClientUtil.H(0L));
      double var27 = MathUtil.h(var0.posY, var0.lastTickPosY, ClientUtil.H(0L));
      double var29 = MathUtil.h(var0.posZ, var0.lastTickPosZ, ClientUtil.H(0L));
      r(

         var0.getEntityBoundingBox()
            .expand(var5, var5, var5)
            .offset(var25 - var0.posX, var27 - var0.posY, var29 - var0.posZ)
            .offset(-RenderManagerAccessor.k(0L, T.getRenderManager()), -RenderManagerAccessor.y(var22, T.getRenderManager()), -RenderManagerAccessor.W(0L, T.getRenderManager())),
         ColorUtil.l(var3,0L),
         ColorUtil.U(0L, var3)
,
         ColorUtil.d(0L, var3),
         var4
      );
   }

   // update new version
   public static void A$fill(Entity var0, long var1, int var3, float var4, double var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      A(var0, var1, var3, var4, var5);
      int var8 = (int)((var1 ^ b ^ 50841124698123L) >>> 32);
      double var9 = MathUtil.h(var0.posX, var0.lastTickPosX, ClientUtil.H(0L));
      double var11 = MathUtil.h(var0.posY, var0.lastTickPosY, ClientUtil.H(0L));
      double var13 = MathUtil.h(var0.posZ, var0.lastTickPosZ, ClientUtil.H(0L));
      AxisAlignedBB var15 = var0.getEntityBoundingBox()
         .expand(var5, var5, var5)
         .offset(var9 - var0.posX, var11 - var0.posY, var13 - var0.posZ)
         .offset(
            -RenderManagerAccessor.k(0L, T.getRenderManager()),
            -RenderManagerAccessor.y(var8, T.getRenderManager()),
            -RenderManagerAccessor.W(0L, T.getRenderManager())
         );
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(false);
      GL11.glColor4f(ColorUtil.l(var3, 0L) / 255.0F, ColorUtil.U(0L, var3) / 255.0F, ColorUtil.d(0L, var3) / 255.0F, 0.25F);
      GL11.glBegin(7);
      double[][] var16 = {
         {var15.minX, var15.minY, var15.minZ, var15.maxX, var15.maxY, var15.minZ},
         {var15.minX, var15.minY, var15.maxZ, var15.maxX, var15.maxY, var15.maxZ}
      };

      for (double[] var20 : var16) {
         GL11.glVertex3d(var20[0], var20[1], var20[2]);
         GL11.glVertex3d(var20[3], var20[1], var20[2]);
         GL11.glVertex3d(var20[3], var20[4], var20[5]);
         GL11.glVertex3d(var20[0], var20[4], var20[5]);
      }

      GL11.glVertex3d(var15.minX, var15.minY, var15.minZ);
      GL11.glVertex3d(var15.minX, var15.minY, var15.maxZ);
      GL11.glVertex3d(var15.minX, var15.maxY, var15.maxZ);
      GL11.glVertex3d(var15.minX, var15.maxY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.minY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.minY, var15.maxZ);
      GL11.glVertex3d(var15.maxX, var15.maxY, var15.maxZ);
      GL11.glVertex3d(var15.maxX, var15.maxY, var15.minZ);
      GL11.glVertex3d(var15.minX, var15.minY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.minY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.minY, var15.maxZ);
      GL11.glVertex3d(var15.minX, var15.minY, var15.maxZ);
      GL11.glVertex3d(var15.minX, var15.maxY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.maxY, var15.minZ);
      GL11.glVertex3d(var15.maxX, var15.maxY, var15.maxZ);
      GL11.glVertex3d(var15.minX, var15.maxY, var15.maxZ);
      GL11.glEnd();
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public static void K(CustomFont var0, String var1, long var2, float var4, float var5, int var6) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var7 = var2 ^ 71677488112044L;
      String var9 = var1.replaceAll("(?i)\u00a7[\\da-f]", "");
      var0.v(var9, var4 + 1.0F, var5, 0, var7, false);
      var0.v(var9, var4 - 1.0F, var5, 0, var7, false);
      var0.v(var9, var4, var5 + 1.0F, 0, var7, false);
      var0.v(var9, var4, var5 - 1.0F, 0, var7, false);
      var0.v(var1, var4, var5, var6, var7, false);
   }

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   public static void Y(AxisAlignedBB var0, int var1, int var2, long var3) {
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      float var5 = (var1 >> 16 & 255) / 255.0F;
      float var6 = (var1 >> 8 & 255) / 255.0F;
      float var7 = (var1 & 255) / 255.0F;
      GL11.glColor4f(var5, var6, var7, var2 / 255.0F);
      RenderGlobal.drawSelectionBoundingBox(var0);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glPopMatrix();
   }

   public static void u(float var0, int var1, float var2, char var3, float var4, int var5, int var6) {
      float var9 = var4 / 2.0F;
      float var10 = var0 + var9;
      float var11 = var2 + var9;
      float var12 = (var5 >> 24 & 255) / 255.0F;
      float var13 = (var5 >> 16 & 255) / 255.0F;
      float var14 = (var5 >> 8 & 255) / 255.0F;
      float var15 = (var5 & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var13, var14, var15, var12);
      Tessellator var16 = Tessellator.getInstance();
      WorldRenderer var17 = var16.getWorldRenderer();
      var17.begin(6, DefaultVertexFormats.POSITION);
      int var18 = Math.max(24, (int)(var9 * 1.5));
      double var19 = (Math.PI * 2) / var18;

      for (int var21 = 0; var21 <= var18; var21++) {
         double var22 = var21 * var19;
         float var24 = (float)(var10 + Math.sin(var22) * var9);
         float var25 = (float)(var11 + Math.cos(var22) * var9);
         var17.pos(var24, var25, 0.0).endVertex();
      }

      var16.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void u(float var0, float var1, float var2, ResourceLocation var5) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      T.getTextureManager().bindTexture(var5);
      Gui.drawModalRectWithCustomSizedTexture((int)var0, (int)var1, 0.0F, 0.0F, (int)var2, (int)var2, (int)var2, (int)var2);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void X(AxisAlignedBB var0, int var3, int var4, int var5, int var6, float var7) {
      GL11.glLineWidth(var7);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      RenderGlobal.drawOutlinedBoundingBox(var0, var3, var4, var5, var6);
      GL11.glDisable(2848);
      GL11.glLineWidth(2.0F);
   }

   public static void j(float var0, float var1, float var2, float var3, float var4, long var5, int var7) {
      P(var0, var1, var2, var3, var4, var4, var4, var4, var7);
   }

   public static void J( float var2, float var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      var2 *= 2.0F;
      var3 *= 2.0F;
      var4 *= 2.0F;
      var5 *= 2.0F;
      GL11.glPushAttrib(1);
      GL11.glScaled(0.5, 0.5, 0.5);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBegin(9);
      P(0L, var7);

      for (int var12 = 0; var12 <= 90; var12 += 3) {
         double var13 = var12 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 + var6 + Math.sin(var13) * var6 * -1.0, var3 + var6 + Math.cos(var13) * var6 * -1.0);
      }

      for (int var20 = 90; var20 <= 180; var20 += 3) {
         double var27 = var20 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 + var6 + Math.sin(var27) * var6 * -1.0, var5 - var6 + Math.cos(var27) * var6 * -1.0);
      }

      for (int var21 = 0; var21 <= 90; var21 += 3) {
         double var28 = var21 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var4 - var6 + Math.sin(var28) * var6, var5 - var6 + Math.cos(var28) * var6);
      }

      for (int var22 = 90; var22 <= 180; var22 += 3) {
         double var29 = var22 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var4 - var6 + Math.sin(var29) * var6, var3 + var6 + Math.cos(var29) * var6);
      }

      GL11.glEnd();
      GL11.glPushMatrix();
      GL11.glShadeModel(7425);
      GL11.glLineWidth(2.0F);
      GL11.glBegin(2);
      if (var8 != 0L) {
         P(0L, var8);
      }

      for (int var23 = 0; var23 <= 90; var23 += 3) {
         double var30 = var23 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 + var6 + Math.sin(var30) * var6 * -1.0, var3 + var6 + Math.cos(var30) * var6 * -1.0);
      }

      for (int var24 = 90; var24 <= 180; var24 += 3) {
         double var31 = var24 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 + var6 + Math.sin(var31) * var6 * -1.0, var5 - var6 + Math.cos(var31) * var6 * -1.0);
      }

      if (var9 != 0) {
         P(0L, var9);
      }

      for (int var25 = 0; var25 <= 90; var25 += 3) {
         double var32 = var25 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var4 - var6 + Math.sin(var32) * var6, var5 - var6 + Math.cos(var32) * var6);
      }

      for (int var26 = 90; var26 <= 180; var26 += 3) {
         double var33 = var26 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var4 - var6 + Math.sin(var33) * var6, var3 + var6 + Math.cos(var33) * var6);
      }

      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glScaled(2.0, 2.0, 2.0);
      GL11.glPopAttrib();
      GL11.glLineWidth(1.0F);
      GL11.glShadeModel(7424);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void g(char var0, char var1, ResourceLocation var2, int var3, float var4, float var5, float var6, float var7, int var8) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      T.getTextureManager().bindTexture(var2);
      GL11.glColor4f(
         var8 >> 16 & 255,
         var8 >> 8 & 255,
         var8 & 255,
         1.0F
      );
      Gui.drawModalRectWithCustomSizedTexture((int)var4, (int)var5, 0.0F, 0.0F, (int)(var6 / var7), (int)(var6 / var7), (int)(var6 / var7), (int)(var6 / var7));
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void n(int var0, double var1, long var3) {
      float var5 = (var0 >> 16 & 255) / 255.0F;
      float var6 = (var0 >> 8 & 255) / 255.0F;
      float var7 = (var0 & 255) / 255.0F;
      GlStateManager.color(var5, var6, var7, (float)var1);
   }

   public static void c(long var0, double var2, double var4, double var6, double var8, int var10) {
      float var11 = (var10 >> 24 & 255) / 255.0F;
      float var12 = (var10 >> 16 & 255) / 255.0F;
      float var13 = (var10 >> 8 & 255) / 255.0F;
      float var14 = (var10 & 255) / 255.0F;
      GlStateManager.pushMatrix();
      Tessellator var15 = Tessellator.getInstance();
      WorldRenderer var16 = var15.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var12, var13, var14, var11);
      var16.begin(7, DefaultVertexFormats.POSITION);
      var16.pos(var2, var8, 0.0).endVertex();
      var16.pos(var6, var8, 0.0).endVertex();
      var16.pos(var6, var4, 0.0).endVertex();
      var16.pos(var2, var4, 0.0).endVertex();
      var15.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static void J(Vec3 var0, double var1, double var3, double var5, int var7, float var8, long var9) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      float var20 = (var7 >> 24 & 255) / 255.0F;
      float var21 = (var7 >> 16 & 255) / 255.0F;
      float var22 = (var7 >> 8 & 255) / 255.0F;
      float var23 = (var7 & 255) / 255.0F;
      GlStateManager.pushMatrix();
      GlStateManager.color(var21, var22, var23, var20);
      boolean var24 = T.gameSettings.viewBobbing;
      T.gameSettings.viewBobbing = false;
      EntityRendererAccessor.k(T.entityRenderer, ClientUtil.H(0L), 2);
      T.gameSettings.viewBobbing = var24;
      GL11.glLineWidth(var8);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glBegin(1);
      GL11.glVertex3d(var0.xCoord, var0.yCoord, var0.zCoord);
      GL11.glVertex3d(
         var1 - RenderManagerAccessor.k(0L, T.getRenderManager()), var3 - RenderManagerAccessor.y(19146, T.getRenderManager()), var5 - RenderManagerAccessor.W(0L, T.getRenderManager())
      );
      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glLineWidth(2.0F);
      GlStateManager.resetColor();
      GlStateManager.popMatrix();
   }

   public static void P(long var0, int var2) {
      GL11.glColor4f(
         (var2 >> 16 & 255) / 255.0F,
         (var2 >> 8 & 255) / 255.0F,
         (var2 & 255) / 255.0F,
         (var2 >> 24 & 255) / 255.0F
      );
   }

   public static void c(BlockPos var0, long var1, int var3, boolean var4, boolean var5) {
      O(var0.getX(), var0.getY(), var0.getZ(), 25559446473706L, 1.0, 1.0, 1.0, var3, var4, var5);
   }

   public static void v(EntityLivingBase var0, int var1, double var2, float var4, long var5) {
      var5 = b ^ var5;
      int var11 = (int)((var5 ^ 26074894726792L) >>> 56);
      if (l(var0)) {
         Minecraft var14 = MinecraftRef.c((byte)var11,0L);
         EntityRendererAccessor.k(var14.entityRenderer, ClientUtil.H(0L), 0);
         ScaledResolution var15 = new ScaledResolution(var14);
         double var16 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var4 - var14.getRenderManager().viewerPosX;
         double var18 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var4 - var14.getRenderManager().viewerPosY;
         double var20 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var4 - var14.getRenderManager().viewerPosZ;
         AxisAlignedBB var22 = var0.getEntityBoundingBox().expand(0.1 + var2, 0.1 + var2, 0.1 + var2);
         AxisAlignedBB var23 = new AxisAlignedBB(
            var22.minX - var0.posX + var16,
            var22.minY - var0.posY + var18,
            var22.minZ - var0.posZ + var20,
            var22.maxX - var0.posX + var16,
            var22.maxY - var0.posY + var18,
            var22.maxZ - var0.posZ + var20
         );
         Vec3[] var24 = new Vec3[8];
         var24[0] = new Vec3(var23.minX, var23.minY, var23.minZ);
         var24[1] = new Vec3(var23.minX, var23.minY, var23.maxZ);
         var24[2] = new Vec3(var23.minX, var23.maxY, var23.minZ);
         var24[3] = new Vec3(var23.minX, var23.maxY, var23.maxZ);
         var24[4] = new Vec3(var23.maxX, var23.minY, var23.minZ);
         var24[5] = new Vec3(var23.maxX, var23.minY, var23.maxZ);
         var24[6] = new Vec3(var23.maxX, var23.maxY, var23.minZ);
         var24[7] = new Vec3(var23.maxX, var23.maxY, var23.maxZ);
         double var25 = Double.MAX_VALUE;
         double var27 = Double.MAX_VALUE;
         double var29 = Double.MIN_VALUE;
         double var31 = Double.MIN_VALUE;
         boolean var33 = false;

         for (Vec3 var37 : var24) {
            double var38 = var37.xCoord;
            double var40 = var37.yCoord;
            double var42 = var37.zCoord;
            Vec3 var44 = I(var15.getScaleFactor(), var38, var40, var42);
            if (var44 != null && !(var44.zCoord >= 1.0003684) && !(var44.zCoord <= 0.0)) {
               var33 = true;
               double var45 = var44.xCoord;
               double var47 = var44.yCoord;
               if (var45 < var25) {
                  var25 = var45;
               }

               if (var47 < var27) {
                  var27 = var47;
               }

               if (var45 > var29) {
                  var29 = var45;
               }

               if (var47 > var31) {
                  var31 = var47;
               }
            }
         }

         if (var33) {
            var14.entityRenderer.setupOverlayRendering();
            ScaledResolution var54 = new ScaledResolution(var14);
            int var55 = var54.getScaledWidth();
            int var56 = var54.getScaledHeight();
            var25 = Math.max(0.0, var25);
            var27 = Math.max(0.0, var27);
            var29 = Math.min(var55, var29);
            var31 = Math.min(var56, var31);
            float var57 = (var1 >> 16 & 255) / 255.0F;
            float var58 = (var1 >> 8 & 255) / 255.0F;
            float var39 = (var1 & 255) / 255.0F;
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glLineWidth(1.0F);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
            GL11.glBegin(2);
            GL11.glVertex2d(var25, var27);
            GL11.glVertex2d(var29, var27);
            GL11.glVertex2d(var29, var31);
            GL11.glVertex2d(var25, var31);
            GL11.glEnd();
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
            GL11.glBegin(2);
            GL11.glVertex2d(var25 + 1.0, var27 + 1.0);
            GL11.glVertex2d(var29 - 1.0, var27 + 1.0);
            GL11.glVertex2d(var29 - 1.0, var31 - 1.0);
            GL11.glVertex2d(var25 + 1.0, var31 - 1.0);
            GL11.glEnd();
            GL11.glColor4f(var57, var58, var39, 1.0F);
            GL11.glBegin(2);
            GL11.glVertex2d(var25 + 0.5, var27 + 0.5);
            GL11.glVertex2d(var29 - 0.5, var27 + 0.5);
            GL11.glVertex2d(var29 - 0.5, var31 - 0.5);
            GL11.glVertex2d(var25 + 0.5, var31 - 0.5);
            GL11.glEnd();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glPopMatrix();
         }
      }
   }

   public static void d(long var0, double var2, double var4, double var6, double var8, int var10, int var11, byte var12) {
      float var15 = (var10 >> 24 & 255) / 255.0F;
      float var16 = (var10 >> 16 & 255) / 255.0F;
      float var17 = (var10 >> 8 & 255) / 255.0F;
      float var18 = (var10 & 255) / 255.0F;
      float var19 = (var11 >> 24 & 255) / 255.0F;
      float var20 = (var11 >> 16 & 255) / 255.0F;
      float var21 = (var11 >> 8 & 255) / 255.0F;
      float var22 = (var11 & 255) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      Tessellator var23 = Tessellator.getInstance();
      WorldRenderer var24 = var23.getWorldRenderer();
      var24.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var24.pos(var6, var4, X).color(var16, var17, var18, var15).endVertex();
      var24.pos(var2, var4, X).color(var16, var17, var18, var15).endVertex();
      var24.pos(var2, var8, X).color(var20, var21, var22, var19).endVertex();
      var24.pos(var6, var8, X).color(var20, var21, var22, var19).endVertex();
      var23.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }

   public static void U(long var0) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
   }

   public static void a(EntityLivingBase var0, long var1, double var3, float var5, int var6) {
      float var11 = ClientUtil.H(0L);
      double var12 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var11 - T.getRenderManager().viewerPosX;
      double var14 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var11 - T.getRenderManager().viewerPosY;
      double var16 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var11 - T.getRenderManager().viewerPosZ;
      GlStateManager.pushMatrix();
      double var18 = MathHelper.clamp_double(var0.getHealth() / var0.getMaxHealth(), 0.0, 1.0);
      double var20 = var0.getEntityBoundingBox().maxY - var0.getEntityBoundingBox().minY;
      double var22 = var20 * 40.0;
      var22 = MathHelper.clamp_double(var22, 30.0, 120.0);
      int var24 = (int)var22;
      int var25 = (int)(var22 * var18);
      int var26 = var18 < 0.3 ? Color.red.getRGB() : (var18 < 0.5 ? Color.orange.getRGB() : (var18 < 0.7 ? Color.yellow.getRGB() : Color.green.getRGB()));
      GL11.glTranslated(var12, var14 - 0.2, var16);
      GL11.glRotated(-T.getRenderManager().playerViewY, 0.0, 1.0, 0.0);
      GlStateManager.disableDepth();
      GL11.glScalef(0.03F, 0.03F, 0.03F);
      int var27 = (int)(var6 + var3 * 2.0);
      c(0L, var27, -1.0, var27 + var5, var24 + 1, Color.black.getRGB());
      c(0L, var27 + 1, var25, var27 + var5 - 1.0F, var24, Color.darkGray.getRGB());
      c(0L, var27 + 1, 0.0, var27 + var5 - 1.0F, var25, var26);
      GlStateManager.enableDepth();
      GlStateManager.popMatrix();
   }

   public static void q(AxisAlignedBB var0, long var1, float var3, float var4, float var5) {
      D(var0, var3, var4, var5, 0.25F);
   }

   public static void k(float var0, float var1, float var2, float var3, long var4, float var6, int var7) {
      float var8 = (var7 >> 24 & 255) / 255.0F;
      float var9 = (var7 >> 16 & 255) / 255.0F;
      float var10 = (var7 >> 8 & 255) / 255.0F;
      float var11 = (var7 & 255) / 255.0F;
      GlStateManager.pushMatrix();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(var6);
      GL11.glColor4f(var9, var10, var11, var8);
      GL11.glBegin(1);
      GL11.glVertex2f(var0, var1);
      GL11.glVertex2f(var2, var3);
      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.popMatrix();
   }

   public static Color t(Color var0, Color var1) {
      return s(var0, var1, 0.5);
   }

   private static void e(WorldRenderer var0, BakedQuad var1, float var2, float var5, float var6, float var7, Tessellator var8) {
      int[] var9 = var1.getVertexData();
      int var11 = var9.length / 4;
      var0.begin(7, DefaultVertexFormats.POSITION_COLOR);

      for (int var12 = 0; var12 < 4; var12++) {
         int var13 = var12 * var11;
         float var14 = Float.intBitsToFloat(var9[var13]);
         float var15 = Float.intBitsToFloat(var9[var13 + 1]);
         float var16 = Float.intBitsToFloat(var9[var13 + 2]);
         var0.pos(var14, var15, var16).color(var2, var5, var6, var7).endVertex();
      }

      var8.draw();
   }

   public static void D(AxisAlignedBB var0, float var1, float var4, float var5, float var6) {
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
      var11.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var11.pos(var0.minX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.minX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.minZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.maxY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var11.pos(var0.maxX, var0.minY, var0.maxZ).color(var1, var4, var5, var6).endVertex();
      var10.draw();
   }

   public static void l(int var0, long var1) {
      float var3 = (var0 >> 24 & 255) / 255.0F;
      float var4 = (var0 >> 16 & 255) / 255.0F;
      float var5 = (var0 >> 8 & 255) / 255.0F;
      float var6 = (var0 & 255) / 255.0F;
      GlStateManager.color(var4, var5, var6, var3);
   }

   private static long c(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 15241;
      if (j[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = i[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])k.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               k.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/util/render/RenderUtil", var14);
         }

         long var15 = (var10[0] & 255L) << 56
            | (var10[1] & 255L) << 48
            | (var10[2] & 255L) << 40
            | (var10[3] & 255L) << 32
            | (var10[4] & 255L) << 24
            | (var10[5] & 255L) << 16
            | (var10[6] & 255L) << 8
            | var10[7] & 255L;
         j[var3] = var15;
      }

      return j[var3];
   }

   public static void l(long var0, AxisAlignedBB var2, int var3) {
      var0 = b ^ var0;
      GlStateManager.pushMatrix();
      float var7 = (var3 >> 24 & 255) / 255.0F;
      float var8 = (var3 >> 16 & 255) / 255.0F;
      float var9 = (var3 >> 8 & 255) / 255.0F;
      float var10 = (var3 & 255) / 255.0F;
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glLineWidth(2.0F);
      GL11.glColor4f(var8, var9, var10, var7);
      D(var2, var8, var9, var10, var7);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GlStateManager.popMatrix();
   }

   public static boolean l(Entity var0) {
      return F(var0.getEntityBoundingBox());
   }

   public static void P(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var8, int var11) {
      if (!(var2 <= var0) && !(var3 <= var1)) {
         float var14 = var2 - var0;
         float var15 = var3 - var1;
         var4 = Math.min(var4, Math.min(var14, var15) / 2.0F);
         var5 = Math.min(var5, Math.min(var14, var15) / 2.0F);
         var8 = Math.min(var8, Math.min(var14, var15) / 2.0F);
         var6 = Math.min(var6, Math.min(var14, var15) / 2.0F);
         float var16 = (var11 >> 24 & 255) / 255.0F;
         float var17 = (var11 >> 16 & 255) / 255.0F;
         float var18 = (var11 >> 8 & 255) / 255.0F;
         float var19 = (var11 & 255) / 255.0F;
         GlStateManager.enableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color(var17, var18, var19, var16);
         Tessellator var20 = Tessellator.getInstance();
         WorldRenderer var21 = var20.getWorldRenderer();
         var21.begin(9, DefaultVertexFormats.POSITION);

         for (int var23 = 0; var23 <= 90; var23 += 6) {
            double var24 = Math.toRadians(var23);
            var21.pos(var0 + var4 - Math.sin(var24) * var4, var1 + var4 - Math.cos(var24) * var4, 0.0).endVertex();
         }

         for (int var30 = 90; var30 <= 180; var30 += 6) {
            double var33 = Math.toRadians(var30);
            var21.pos(var0 + var6 - Math.sin(var33) * var6, var3 - var6 - Math.cos(var33) * var6, 0.0).endVertex();
         }

         for (int var31 = 0; var31 <= 90; var31 += 6) {
            double var34 = Math.toRadians(var31);
            var21.pos(var2 - var8 + Math.sin(var34) * var8, var3 - var8 + Math.cos(var34) * var8, 0.0).endVertex();
         }

         for (int var32 = 90; var32 <= 180; var32 += 6) {
            double var35 = Math.toRadians(var32);
            var21.pos(var2 - var5 + Math.sin(var35) * var5, var1 + var5 + Math.cos(var35) * var5, 0.0).endVertex();
         }

         var20.draw();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public static void r(double var0, double var2, double var6, double var8, double var10, double var12, int var14, int var15) {
      double var18 = var0 - T.getRenderManager().viewerPosX;
      double var20 = var2 - T.getRenderManager().viewerPosY;
      double var22 = var6 - T.getRenderManager().viewerPosZ;
      AxisAlignedBB var24 = new AxisAlignedBB(var18, var20, var22, var18 + var8, var20 + var10, var22 + var12);
      Y(var24, var14, var15,0L);
   }

   private static boolean F(AxisAlignedBB var0) {
      Z.setPosition(T.getRenderViewEntity().posX, T.getRenderViewEntity().posY, T.getRenderViewEntity().posZ);
      return Z.isBoundingBoxInFrustum(var0);
   }

   public static Vec3 I(int var0, double var3, double var5, double var7) {
      GL11.glGetFloat(2982, D);
      GL11.glGetFloat(2983, G);
      GL11.glGetInteger(2978, A);
      boolean var9 = GLU.gluProject((float)var3, (float)var5, (float)var7, D, G, A, Y);
      return var9 ? new Vec3(Y.get(0) / var0, (Display.getHeight() - Y.get(1)) / var0, Y.get(2)) : null;
   }

   public static void W(AxisAlignedBB var0, long var1, int var3, int var4, boolean var5, boolean var6) {
      GL11.glPushMatrix();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(2848);
      GL11.glEnable(2881);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      float var10 = (var3 >> 16 & 255) / 255.0F;
      float var11 = (var3 >> 8 & 255) / 255.0F;
      float var12 = (var3 & 255) / 255.0F;
      float var13 = var4 / 255.0F;
      if (var5) {
         GL11.glColor4f(var10, var11, var12, 1.0F);
         RenderGlobal.drawSelectionBoundingBox(var0);
      }

      if (var6) {
         GL11.glColor4f(var10, var11, var12, var13);
         D(var0, var10, var11, var12, var13);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glDisable(2881);
      GL11.glPopMatrix();
   }

   public static void X() {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void N(double var0, double var2, long var4, double var6, double var8, float var10) {
      ScaledResolution var11 = new ScaledResolution(T);
      int var12 = var11.getScaleFactor();
      int var13;
      switch (var12) {
         case 2:
            var13 = 540;
            break;
         case 3:
            var13 = 1080;
            break;
         case 4:
            var13 = 270;
            break;
         default:
            var13 = var11.getScaledHeight();
      }

      GL11.glScissor((int)(var0 * var12 * var10), (int)((var13 - var2 - var8) * var12 * var10), (int)(var6 * var12 * var10), (int)(var8 * var12 * var10));
   }

   public static void N(EntityLivingBase var0, long var1, int var3) {
      float var15 = MinecraftAccessor.o( T).renderPartialTicks;
      double var16 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var15 - T.getRenderManager().viewerPosX;
      double var18 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var15 - T.getRenderManager().viewerPosY;
      double var20 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var15 - T.getRenderManager().viewerPosZ;
      GlStateManager.pushMatrix();
      if (var3 == 0) {
         long[] var12 = new long[]{0L};

         var3 = M(21658, (short)51297, 2L, var12);
      }

      float var22 = (var3 >> 24 & 255) / 255.0F;
      float var23 = (var3 >> 16 & 255) / 255.0F;
      float var24 = (var3 >> 8 & 255) / 255.0F;
      float var25 = (var3 & 255) / 255.0F;
      AxisAlignedBB var26 = var0.getEntityBoundingBox().expand(0.1, 0.1, 0.1);
      AxisAlignedBB var27 = new AxisAlignedBB(
         var26.minX - var0.posX + var16,
         var26.minY - var0.posY + var18,
         var26.minZ - var0.posZ + var20,
         var26.maxX - var0.posX + var16,
         var26.maxY - var0.posY + var18,
         var26.maxZ - var0.posZ + var20
      );
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glLineWidth(2.0F);
      GL11.glColor4f(var23, var24, var25, var22);
      D(var27, var23, var24, var25, var22);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GlStateManager.popMatrix();
   }

   public static Color s(Color var0, Color var1, double var2) {
      float var4 = (float)var2;
      float var5 = 1.0F - var4;
      float[] var6 = new float[3];
      float[] var7 = new float[3];
      var0.getColorComponents(var6);
      var1.getColorComponents(var7);
      return new Color(var6[0] * var4 + var7[0] * var5, var6[1] * var4 + var7[1] * var5, var6[2] * var4 + var7[2] * var5);
   }

   public static void N(int var0, Entity var2, int var3, float var4) {
      if (var2 instanceof EntityLivingBase) {
         double var8 = var2.lastTickPosX + (var2.posX - var2.lastTickPosX) * var4 - T.getRenderManager().viewerPosX;
         double var10 = var2.lastTickPosY + (var2.posY - var2.lastTickPosY) * var4 - T.getRenderManager().viewerPosY;
         double var12 = var2.lastTickPosZ + (var2.posZ - var2.lastTickPosZ) * var4 - T.getRenderManager().viewerPosZ;
         GlStateManager.pushMatrix();
         GL11.glTranslated(var8, var10 - 0.2, var12);
         GL11.glRotated(-T.getRenderManager().playerViewY, 0.0, 1.0, 0.0);
         GlStateManager.disableDepth();
         GL11.glScalef(0.03F, 0.03F, 0.03F);
         int var14 = Color.black.getRGB();
         Gui.drawRect(-18, -1, -21, 74, var14);
         Gui.drawRect(18, -1, 21, 74, var14);
         Gui.drawRect(-18, -1, 21, 2, var14);
         Gui.drawRect(
            -18,
            71,
            21,
            74,
            var14
         );
         Gui.drawRect(-19, 0, -20, 73, var3);
         Gui.drawRect(19, 0, 20, 73, var3);
         Gui.drawRect(-19, 0, 20, 1, var3);
         Gui.drawRect(
            -19,
            72,
            20,
            73,
            var3
         );
         GlStateManager.enableDepth();
         GlStateManager.popMatrix();
      }
   }

   public static void r(Vec3 var0, long var1, double var3, double var5, double var7, float var9, float var10, float var11, float var12, float var13) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      GlStateManager.pushMatrix();
      GlStateManager.color(var9, var10, var11, var12);
      boolean var23 = T.gameSettings.viewBobbing;
      T.gameSettings.viewBobbing = false;
      EntityRendererAccessor.k(T.entityRenderer, ClientUtil.H(0L), 2);
      T.gameSettings.viewBobbing = var23;
      GL11.glLineWidth(var13);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glBegin(1);
      GL11.glVertex3d(var0.xCoord, var0.yCoord, var0.zCoord);
      GL11.glVertex3d(
         var3 - RenderManagerAccessor.k(0L, T.getRenderManager()), var5 - RenderManagerAccessor.y(13236, T.getRenderManager()), var7 - RenderManagerAccessor.W(0L, T.getRenderManager())
      );
      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glLineWidth(2.0F);
      GlStateManager.resetColor();
      GlStateManager.popMatrix();
   }

   public static void L() {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.disableAlpha();
      GlStateManager.disableDepth();
   }

   private static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 3731;
      if (d[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])e.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               e.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/util/render/RenderUtil", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = c[var5].getBytes("ISO-8859-1");
         d[var5] = a(((Cipher)var4[0]).doFinal(var9));
      }

      return d[var5];
   }

   public static void a(IBlockState var0, double var1, long var3, double var5, double var7, int var9) {
      BlockRendererDispatcher var12 = T.getBlockRendererDispatcher();
      IBakedModel var13 = var12.getModelFromBlockState(var0, T.theWorld, new BlockPos(var1, var5, var7));
      double var14 = var1 - T.getRenderManager().viewerPosX;
      double var16 = var5 - T.getRenderManager().viewerPosY;
      double var18 = var7 - T.getRenderManager().viewerPosZ;
      float var20 = (var9 >> 24 & 255) / 255.0F;
      float var21 = (var9 >> 16 & 255) / 255.0F;
      float var22 = (var9 >> 8 & 255) / 255.0F;
      float var23 = (var9 & 255) / 255.0F;
      GlStateManager.pushMatrix();
      GlStateManager.translate(var14, var16, var18);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.color(var21, var22, var23, var20);
      p(var13, var21, var22, var23, var20);
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableTexture2D();
      GlStateManager.enableCull();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static Color R(Color var0, float var1) {
      float var2 = 0.003921569F * var0.getRed();
      float var3 = 0.003921569F * var0.getGreen();
      float var4 = 0.003921569F * var0.getBlue();
      return new Color(var2, var3, var4, var1);
   }

   public static void m(float var0, float var1, long var2, float var4, float var5, float var6, int var7) {
      float var8 = (var7 >> 24 & 255) / 255.0F;
      float var9 = (var7 >> 16 & 255) / 255.0F;
      float var10 = (var7 >> 8 & 255) / 255.0F;
      float var11 = (var7 & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glPushMatrix();
      GL11.glColor4f(var9, var10, var11, var8);
      GL11.glLineWidth(var6);
      GL11.glBegin(1);
      GL11.glVertex2d(var0, var1);
      GL11.glVertex2d(var0, var5);
      GL11.glVertex2d(var4, var5);
      GL11.glVertex2d(var4, var1);
      GL11.glVertex2d(var0, var1);
      GL11.glVertex2d(var4, var1);
      GL11.glVertex2d(var0, var5);
      GL11.glVertex2d(var4, var5);
      GL11.glEnd();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
   }

   public static void n(String var0, float var1, long var2, float var4, int var5, int var6) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var2 = b ^ var2;
      String var7 = var0.replaceAll(a(15291, 1728591932349241734L ^ var2), "");
      T.fontRendererObj.drawString(var7, var1 + 1.0F, var4, var6, false);
      T.fontRendererObj.drawString(var7, var1 - 1.0F, var4, var6, false);
      T.fontRendererObj.drawString(var7, var1, var4 + 1.0F, var6, false);
      T.fontRendererObj.drawString(var7, var1, var4 - 1.0F, var6, false);
      T.fontRendererObj.drawString(var0, var1, var4, var5, false);
   }

   public static void o(
      short var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      int var13,
      int var14,
      char var15,
      boolean var16,
      int var17,
      boolean var18
   ) {
      long var19 = ((long)var0 << 48 | (long)var15 << 48 >>> 16 | (long)var17 << 32 >>> 32) ^ b;
      long var21 = var19 ^ 130382588563012L;
      double var23 = var1 - T.getRenderManager().viewerPosX;
      double var25 = var3 - T.getRenderManager().viewerPosY;
      double var27 = var5 - T.getRenderManager().viewerPosZ;
      AxisAlignedBB var29 = new AxisAlignedBB(var23, var25, var27, var23 + var7, var25 + var9, var27 + var11);
      W(var29, var21, var13, var14, var16, var18);
   }

   private static void p(IBakedModel var0, float var1, float var2, float var3, float var4) {
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();

      for (EnumFacing var14 : EnumFacing.values()) {
         for (BakedQuad var16 : var0.getFaceQuads(var14)) {
            e(var10, var16, var1, var2, var3, var4, var9);
         }
      }

      for (BakedQuad var19 : var0.getGeneralQuads()) {
         e(var10, var19, var1, var2, var3, var4, var9);
      }
   }

   public static void m(float var0, float var1, float var2, float var3, float var4, int var5, int var6, long var7, int var9, int var10) {
      var0 *= 2.0F;
      var1 *= 2.0F;
      var2 *= 2.0F;
      var3 *= 2.0F;
      GL11.glPushAttrib(1);
      GL11.glScaled(0.5, 0.5, 0.5);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBegin(9);
      P(0L, var5);

      for (int var13 = 0; var13 <= 90; var13 += 3) {
         double var14 = var13 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var0 + var4 + Math.sin(var14) * var4 * -1.0, var1 + var4 + Math.cos(var14) * var4 * -1.0);
      }

      for (int var21 = 90; var21 <= 180; var21 += 3) {
         double var28 = var21 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var0 + var4 + Math.sin(var28) * var4 * -1.0, var3 - var4 + Math.cos(var28) * var4 * -1.0);
      }

      for (int var22 = 0; var22 <= 90; var22 += 3) {
         double var29 = var22 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 - var4 + Math.sin(var29) * var4, var3 - var4 + Math.cos(var29) * var4);
      }

      for (int var23 = 90; var23 <= 180; var23 += 3) {
         double var30 = var23 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 - var4 + Math.sin(var30) * var4, var1 + var4 + Math.cos(var30) * var4);
      }

      GL11.glEnd();
      GL11.glPushMatrix();
      GL11.glShadeModel(7425);
      GL11.glLineWidth(2.0F);
      GL11.glBegin(2);
      if (var6 != 0L) {
         P(0L, var6);
      }

      for (int var24 = 0; var24 <= 90; var24 += 3) {
         double var31 = var24 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var0 + var4 + Math.sin(var31) * var4 * -1.0, var1 + var4 + Math.cos(var31) * var4 * -1.0);
      }

      for (int var25 = 90; var25 <= 180; var25 += 3) {
         double var32 = var25 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var0 + var4 + Math.sin(var32) * var4 * -1.0, var3 - var4 + Math.cos(var32) * var4 * -1.0);
      }

      if (var9 != 0) {
         P(0L, var9);
      }

      for (int var26 = 0; var26 <= 90; var26 += 3) {
         double var33 = var26 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 - var4 + Math.sin(var33) * var4, var3 - var4 + Math.cos(var33) * var4);
      }

      if (var10 != 0) {
         P(0L, var10);
      }

      for (int var27 = 90; var27 <= 180; var27 += 3) {
         double var34 = var27 * (float) (Math.PI / 180.0);
         GL11.glVertex2d(var2 - var4 + Math.sin(var34) * var4, var1 + var4 + Math.cos(var34) * var4);
      }

      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glScaled(2.0, 2.0, 2.0);
      GL11.glPopAttrib();
      GL11.glLineWidth(1.0F);
      GL11.glShadeModel(7424);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void G() {
      GlStateManager.disableBlend();
   }

   public static void I( double var3, double var5, double var7, double var9, int var11, int var12) {
      float var15 = var12 / 255.0F;
      float var16 = (var11 >> 16 & 255) / 255.0F;
      float var17 = (var11 >> 8 & 255) / 255.0F;
      float var18 = (var11 & 255) / 255.0F;
      GlStateManager.pushMatrix();
      Tessellator var19 = Tessellator.getInstance();
      WorldRenderer var20 = var19.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var16, var17, var18, var15);
      var20.begin(7, DefaultVertexFormats.POSITION);
      var20.pos(var3, var9, 0.0).endVertex();
      var20.pos(var7, var9, 0.0).endVertex();
      var20.pos(var7, var5, 0.0).endVertex();
      var20.pos(var3, var5, 0.0).endVertex();
      var19.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
   }

   public static Color n(int var0, int var1, long var2) {
      return s(x(var0,0L), x(var1,0L), 0.5);
   }

   public static void V(float var0, float var1, float var2, float var3) {
      ScaledResolution var4 = new ScaledResolution(T);
      int var5 = var4.getScaleFactor();
      int var6 = var4.getScaledHeight();
      GL11.glScissor((int)(var0 * var5), (int)((var6 - var1) * var5), (int)(var2 * var5), (int)(var3 * var5));
   }

   public static void m(int var0, String var1, int var2, int var3, short var4, int var5) {
      long var6 = ((long)var0 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ b;
      long var8 = var6 ^ 58551363832329L;
      long var10 = var6 ^ 90920226898097L;
      long var14 = var6 ^ 82216679849612L;
      if (!var1.isEmpty()) {
         CustomFont var18 = RavenClickGuiScreen.t();
         String[] var19 = var1.split("\n");
         double var20 = var18.R(var19[0], var14);
         double var22 = var18.o(var10);

         for (String var27 : var19) {
            c(0L, var2 + 5, var5 + var22 - 2.0, var2 + 6 + var20 + 1.0, var5 + var22 * 2.0, n);
            var18.v(var27, var2 + 6, (float)(var5 + var22 - 1.0), a, var8, false);
            var5 += (int)Math.round(var22);
         }
      }
   }

   public static void r( AxisAlignedBB var2, int var3, int var4, int var6, float var7) {
      L();
      GL11.glLineWidth(var7);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      RenderGlobal.drawOutlinedBoundingBox(var2, var3, var4, var6, 255);
      GL11.glDisable(2848);
      GL11.glLineWidth(2.0F);
      GlStateManager.resetColor();
      w();
   }

   static {
      b = 23109265913955L;
      n = new Color(0, 0, 0, 100).getRGB();
      a = new Color(
      229,
      229,
      229,
      255
      )
      .getRGB();
      D = BufferUtils.createFloatBuffer(16);
      G = BufferUtils.createFloatBuffer(16);
      A = BufferUtils.createIntBuffer(16);
      Y = BufferUtils.createFloatBuffer(3);
      Z = new Frustum();
      l = new Frustum();
      R = MinecraftRef.c((byte)0, 0L).getRenderItem();
      T = MinecraftRef.c((byte)0, 0L);
      e = new HashMap(13);
      c = new String[]{"\u00cb\u0081\u00af\u00b3\u0096\u00e4\u00c1\u00e1\u0081\u00efC\u00e5\u00c3R\u008a0", "\u0012pl\u00e7\u0087O\u0006F-n\u008b\u0010\u001d\u008bX\u00f8"};
      d = new String[2];
      h = new HashMap(13);
      f = new long[]{-5088694070550099896L, 8155316329243142677L, -7422892396150652391L, 742474754744504564L, -8729608455096803099L, 2470290002738801662L, 6239973166703145587L, 8030495419858915825L, -1298946361898594236L, -1545267937410721481L, 1419340624431703604L, -5476428765416965539L, -7003657242432300719L, -2495216312109787061L, 2343146126028375485L, -4478592037699991809L, -6488717641568478588L, -1744428310593751694L, 8324249328261687948L, 7812365694904717541L, 6917391024997969355L, -3175613616820735565L, 7312472316776562945L, -4558771325751885656L, 4347900262008322153L, 6498785137216718408L, 6616957481526128662L, -9202409458979254657L, 7588322063655537140L, -779626535569165101L, -5512695679228330404L, -7092401608600228187L, 2306158580603804895L, -4596565801282309343L, 8812805725538072174L, 7497747270486841069L, 6417900034124518443L, 2374880926906000685L, 2160321411613336491L, 3103534474377545248L, 6517086263121659013L, 2019193806670065591L, 4978688397698424480L, 8029601312073824740L, 4122547359624308305L, 3963083747440586265L, 4914873387444537348L, 6356898738722732384L, -5846833725223146037L, 7317652451318471854L, 5466057205853538398L, -1884582349168091766L, 5895617322528213452L, 7367905349025222830L, -1037739916195323074L, 6188277999662890523L, 4857569544702086880L, -5519463796391533552L, -7526714701279271715L, -330132507026886760L, -9034445706494238907L, 517012506083509977L, 4476265700608652599L, 6810216486767436539L, 4850569218703218001L, -965043872831246782L, 2979301730645324363L, -4404520798714946088L, 5440983928867488244L, 331685453876583593L, 6339265489527405045L, -5381969615694432066L, 6175778928660765973L, -3297697833550516828L, -2459954745810165526L, 3062878923982220060L};
      k = new HashMap(13);
      i = new long[]{5653317873009865945L, 1291119500788730589L, 6010980468042206603L, -5828199305021914624L};
      j = new Long[4];
   }

   public static void J(long var0, CustomFont var2, String var3, float var4, float var5, int var6, int var7) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var8 = var0 ^ 123229906358270L;
      String var10 = var3.replaceAll("(?i)\u00a7[\\da-f]", "");
      var2.v(var10, var4 + 1.0F, var5, var7, var8, false);
      var2.v(var10, var4 - 1.0F, var5, var7, var8, false);
      var2.v(var10, var4, var5 + 1.0F, var7, var8, false);
      var2.v(var10, var4, var5 - 1.0F, var7, var8, false);
      var2.v(var3, var4, var5, var6, var8, false);
   }

   public static void M(BlockPos var0, long var1, int var3, int var4, boolean var5, boolean var6) {
      var1 = b ^ var1;
      int var7 = (int)((var1 ^ 133240897538828L) >>> 48);
      int var8 = (int)((var1 ^ 133240897538828L) << 16 >>> 48);
      int var9 = (int)((var1 ^ 133240897538828L) << 32 >>> 32);
      o((short)var7, var0.getX(), var0.getY(), var0.getZ(), 1.0, 1.0, 1.0, var3, var4, (char)var8, var5, var9, var6);
   }

   public static void O(int var0, float var1, long var2) {
      float var4 = (var0 >> 16 & 255) / 255.0F;
      float var5 = (var0 >> 8 & 255) / 255.0F;
      float var6 = (var0 & 255) / 255.0F;
      GlStateManager.color(var4, var5, var6, var1);
   }

   public static void m(float var0, float var1, float var2, float var3, long var4, float var6, float var7, float var8, float var9, float var10) {
      GlStateManager.pushMatrix();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(var6);
      GL11.glColor4f(var7, var8, var9, var10);
      GL11.glBegin(1);
      GL11.glVertex2f(var0, var1);
      GL11.glVertex2f(var2, var3);
      GL11.glEnd();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.popMatrix();
   }

   public static int M(int var0, short var1, long var2, long... var5) {
      long var8 = System.currentTimeMillis() + (var5.length > 0 ? var5[0] : 0L);
      return Color.getHSBColor((float)(var8 % (15000L / 2L)) / (15000.0F / (float)2L), 1.0F, 1.0F).getRGB();
   }

   public static void n(BlockPos var0, double var1, int var3, int var4, int var5, long var6, int var8, float var9) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var6 = b ^ var6;
      int var16 = (int)((var6 ^ 86564007578313L) >>> 32);
      X(
         new AxisAlignedBB(
               var0.getX(),
               var0.getY(),
               var0.getZ(),
               var0.getX() + 1.0,
               var0.getY() + var1,
               var0.getZ() + 1.0
            )
            .offset(-RenderManagerAccessor.k(0L, T.getRenderManager()), -RenderManagerAccessor.y(var16, T.getRenderManager()), -RenderManagerAccessor.W(0L, T.getRenderManager())),
         var3,
         var4,
         var5,
         var8,
         var9
      );
   }

   public static void V(EntityLivingBase var0, long var1, double var3, double var5, double var7, int var9) {
      long var10 = var1 ^ 109440825038403L;
      Z(var0.getEntityBoundingBox().expand(0.1, 0.1, 0.1), var0.lastTickPosX, var0.lastTickPosY, var0.lastTickPosZ, var3, var5, var10, var7, var9);
   }
}
