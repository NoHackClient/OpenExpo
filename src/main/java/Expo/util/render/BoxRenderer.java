package Expo.util.render;

import Expo.util.MinecraftRef;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

public class BoxRenderer {
   private static long a;
   private static Minecraft M;

   public static void D(AxisAlignedBB var0, Color var2) {
      C();
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      RenderManager var11 = M.getRenderManager();
      AxisAlignedBB var12 = var0.offset(-var11.viewerPosX, -var11.viewerPosY, -var11.viewerPosZ);
      double var13 = var12.minX;
      double var15 = var12.minY;
      double var17 = var12.minZ;
      double var19 = var12.maxX;
      double var21 = var12.maxY;
      double var23 = var12.maxZ;
      float var25 = var2.getAlpha() / 255.0F;
      float var26 = var2.getRed() / 255.0F;
      float var27 = var2.getGreen() / 255.0F;
      float var28 = var2.getBlue() / 255.0F;
      GlStateManager.color(var26, var27, var28, var25);
      var10.begin(7, DefaultVertexFormats.POSITION);
      var10.pos(var13, var15, var17).endVertex();
      var10.pos(var19, var15, var17).endVertex();
      var10.pos(var19, var15, var23).endVertex();
      var10.pos(var13, var15, var23).endVertex();
      var10.pos(var13, var21, var23).endVertex();
      var10.pos(var19, var21, var23).endVertex();
      var10.pos(var19, var21, var17).endVertex();
      var10.pos(var13, var21, var17).endVertex();
      var10.pos(var13, var15, var23).endVertex();
      var10.pos(var13, var21, var23).endVertex();
      var10.pos(var13, var21, var17).endVertex();
      var10.pos(var13, var15, var17).endVertex();
      var10.pos(var19, var15, var17).endVertex();
      var10.pos(var19, var21, var17).endVertex();
      var10.pos(var19, var21, var23).endVertex();
      var10.pos(var19, var15, var23).endVertex();
      var10.pos(var13, var21, var17).endVertex();
      var10.pos(var19, var21, var17).endVertex();
      var10.pos(var19, var15, var17).endVertex();
      var10.pos(var13, var15, var17).endVertex();
      var10.pos(var13, var15, var23).endVertex();
      var10.pos(var19, var15, var23).endVertex();
      var10.pos(var19, var21, var23).endVertex();
      var10.pos(var13, var21, var23).endVertex();
      var9.draw();
      F(var13, var15, var17, var19, var21, var23, var2);
      o();
   }

   static {
      a = 76566711834522L;
      M = MinecraftRef.c((byte)0, 0L);
   }

   private static void F(double var0, double var2, double var4, double var6, double var8, double var10, Color var12) {
      GL11.glLineWidth(2.0F);
      GL11.glColor4f(var12.getRed() / 255.0F, var12.getGreen() / 255.0F, var12.getBlue() / 255.0F, var12.getAlpha() / 255.0F);
      GL11.glBegin(1);
      GL11.glVertex3d(var0, var2, var4);
      GL11.glVertex3d(var6, var2, var4);
      GL11.glVertex3d(var6, var2, var4);
      GL11.glVertex3d(var6, var2, var10);
      GL11.glVertex3d(var6, var2, var10);
      GL11.glVertex3d(var0, var2, var10);
      GL11.glVertex3d(var0, var2, var10);
      GL11.glVertex3d(var0, var2, var4);
      GL11.glVertex3d(var0, var8, var4);
      GL11.glVertex3d(var6, var8, var4);
      GL11.glVertex3d(var6, var8, var4);
      GL11.glVertex3d(var6, var8, var10);
      GL11.glVertex3d(var6, var8, var10);
      GL11.glVertex3d(var0, var8, var10);
      GL11.glVertex3d(var0, var8, var10);
      GL11.glVertex3d(var0, var8, var4);
      GL11.glVertex3d(var0, var2, var4);
      GL11.glVertex3d(var0, var8, var4);
      GL11.glVertex3d(var6, var2, var4);
      GL11.glVertex3d(var6, var8, var4);
      GL11.glVertex3d(var6, var2, var10);
      GL11.glVertex3d(var6, var8, var10);
      GL11.glVertex3d(var0, var2, var10);
      GL11.glVertex3d(var0, var8, var10);
      GL11.glEnd();
      GL11.glLineWidth(1.0F);
   }

   private static void o() {
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GlStateManager.resetColor();
      GlStateManager.popMatrix();
   }

   private static void C() {
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.disableDepth();
      GlStateManager.disableLighting();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableTexture2D();
   }

   public static void I( BlockPos var2, Color var3) {
      R(
         new AxisAlignedBB(
            var2.getX(), var2.getY(), var2.getZ(), var2.getX() + 1, var2.getY() + 1, var2.getZ() + 1
         ),
         var3
      );
   }

   private BoxRenderer() {
   }

   public static void p(BlockPos var0, long var1, Color var3) {
      var1 = a ^ var1;
      D(
         new AxisAlignedBB(
            var0.getX(), var0.getY(), var0.getZ(), var0.getX() + 1, var0.getY() + 1, var0.getZ() + 1
         )
,
         var3

);
   }

   public static void R( AxisAlignedBB var2, Color var3) {
      RenderManager var6 = M.getRenderManager();
      AxisAlignedBB var7 = var2.offset(-var6.viewerPosX, -var6.viewerPosY, -var6.viewerPosZ);
      C();
      F(var7.minX, var7.minY, var7.minZ, var7.maxX, var7.maxY, var7.maxZ, var3);
      o();
   }
}
