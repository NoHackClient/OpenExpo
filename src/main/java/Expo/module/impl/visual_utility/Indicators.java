package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.IndicatorsBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.internal.accessor.EntityArrowAccessor;
import Expo.internal.accessor.EntityRendererAccessor;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.ClientUtil;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Indicators extends Module implements EventSubscriber {
   public static BooleanSetting renderArrows;
   private static long b;
   public static NumberSetting circleRadius;
   public static BooleanSetting renderDistance;
   private final Set<Entity> m;
   public static BooleanSetting renderFireballs;
   public static BooleanSetting itemColors;
   private int Y;
   public static BooleanSetting renderSnowballs;
   public static BooleanSetting renderOnlyOffscreen;
   public static BooleanSetting renderEnderPearls;
   public static BooleanSetting onlyWhenApproaching;
   private final Map<Entity, Vec3> p;
   private static final double O = 1.0;
   public static BooleanSetting renderEggs;

   public void onPostTick(long var1, PostTickEvent var3) {
      if (ClientUtil.I()) {
         this.Y++;
         if (this.Y % 5 == 0) {
            HashSet var10 = new HashSet();
            this.m.clear();
            double var11 = f.thePlayer.posX;
            double var13 = f.thePlayer.posY;
            double var15 = f.thePlayer.posZ;

            for (Object var18 : f.theWorld.loadedEntityList) {
               if (var18 instanceof Entity) {
                  Entity var19 = (Entity)var18;
                  if (var19 != null && var19 != f.thePlayer) {
                     ItemStack var20 = this.H((short)0, var19);
                     if (var20 != null && this.d(var19, (short)0)) {
                        var10.add(var19);
                        Vec3 var21 = this.p.get(var19);
                        if (onlyWhenApproaching.c()) {
                           if (var21 == null) {
                              this.p.put(var19, new Vec3(var19.posX, var19.posY, var19.posZ));
                              continue;
                           }

                           double var22 = Math.sqrt(
                              (var11 - var21.xCoord) * (var11 - var21.xCoord)
                                 + (var13 - var21.yCoord) * (var13 - var21.yCoord)
                                 + (var15 - var21.zCoord) * (var15 - var21.zCoord)
                           );
                           double var24 = f.thePlayer.getDistanceToEntity(var19);
                           if (var22 - var24 <= 1.0) {
                              this.p.put(var19, new Vec3(var19.posX, var19.posY, var19.posZ));
                              continue;
                           }
                        }

                        this.m.add(var19);
                        this.p.put(var19, new Vec3(var19.posX, var19.posY, var19.posZ));
                     }
                  }
               }
            }

            this.p.keySet().retainAll(var10);
         }
      }
   }

   private void getRGB(int var1, Entity var2, ItemStack var3, char var4, float var5, char var6) {
      long var7 = ((long)var1 << 32 | (long)var4 << 48 >>> 32 | (long)var6 << 48 >>> 48) ^ b;
      int var11 = (int)((var7 ^ 11771545508056L) >>> 48);
      if (this.d(var2, (short)var11)) {
         if (!renderOnlyOffscreen.c() || !Expo.util.render.RenderUtil.l(var2)) {
            int var16 = itemColors.c() ? this.getItem(var3).getRGB() : -1;
            F(var2, var16, var5, circleRadius.L(), renderDistance.c());
         }
      }
   }

   public Indicators(long var1) {
      super(((b ^ (var1)) ^ 40259283249710L));
      this.declare("Indicators", Category.Visual_utility, "Show projectiles that is going to hit you on screen");
      var1 = b ^ var1;
      this.p = new HashMap<>();
      this.m = new HashSet<>();
   }

   private boolean d(Entity var1, short var2) {
      if (var1 instanceof EntityArrow && !EntityArrowAccessor.E(0L, (EntityArrow)var1) && renderArrows.c()) {
         return true;
      } else if (var1 instanceof EntityLargeFireball && renderFireballs.c()) {
         return true;
      } else if (var1 instanceof EntityEnderPearl && renderEnderPearls.c()) {
         return true;
      } else {
         return var1 instanceof EntityEgg && renderEggs.c() ? true : var1 instanceof EntitySnowball && renderSnowballs.c();
      }
   }

   public void A(long var1) {
      this.p.clear();
      this.m.clear();
   }

   static {
      b = 54273370022170L;
   }

   private Color getItem(ItemStack var3) {
      if (var3 == null) {
         return Color.WHITE;
      } else if (var3.getItem() == Items.ender_pearl) {
         return new Color(62, 127, 94);
      } else if (var3.getItem() == Items.fire_charge) {
         return new Color(255, 150, 0);
      } else if (var3.getItem() == Items.egg) {
         return new Color(255, 238, 154);
      } else {
         return var3.getItem() == Items.snowball
            ? new Color(200, 220, 255)
            : Color.WHITE;
      }
   }

   public final void x(long var1, EventBus var3) {
      IndicatorsBinder.J(var3, this);
   }

   private ItemStack H(short var1, Entity var4) {
      if (var4 == null) {
         return null;
      } else if (var4 instanceof EntityArrow) {
         return EntityArrowAccessor.E(0L, (EntityArrow)var4) ? null : new ItemStack(Items.arrow);
      } else if (var4 instanceof EntityFireball) {
         return new ItemStack(Items.fire_charge);
      } else if (var4 instanceof EntityEnderPearl) {
         return new ItemStack(Items.ender_pearl);
      } else if (var4 instanceof EntityEgg) {
         return new ItemStack(Items.egg);
      } else {
         return var4 instanceof EntitySnowball ? new ItemStack(Items.snowball) : null;
      }
   }

   public void onRender2D(long var1, Render2DEvent var3) {
      if (f.currentScreen == null && ClientUtil.I()) {
         try {
            for (Entity var11 : this.m) {
               ItemStack var12 = this.H((short)0, var11);
               if (var12 != null) {
                  this.getRGB(20855, var11, var12, (char)29931, var3.r, (char)33946);
               }
            }
         } catch (Exception var13) {
         }
      }
   }

   private static void A(int var0) {
      int var3 = var0 >> 24 & 255;
      int var4;
      int var5;
      int var6;
      if (var0 == -1) {
         var4 = 255;
         var5 = 255;
         var6 = 255;
      } else {
         var4 = var0 >> 16 & 255;
         var5 = var0 >> 8 & 255;
         var6 = var0 & 255;
      }

      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glBegin(9);
      GL11.glColor4f(var4 / 255.0F, var5 / 255.0F, var6 / 255.0F, var3 / 255.0F);
      GL11.glVertex2d(0.0, -5.0);
      GL11.glVertex2d(-5.0, 5.0);
      GL11.glVertex2d(0.0, 3.0);
      GL11.glVertex2d(5.0, 5.0);
      GL11.glEnd();
      GL11.glLineWidth(1.5F);
      GL11.glBegin(2);
      GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
      GL11.glVertex2d(0.0, -5.0);
      GL11.glVertex2d(-5.0, 5.0);
      GL11.glVertex2d(0.0, 3.0);
      GL11.glVertex2d(5.0, 5.0);
      GL11.glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
   }

   public static void F(Entity var0, int var1, float var2, double var5, boolean var7) {
      double var12 = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * var2 - f.getRenderManager().viewerPosX;
      double var14 = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * var2 - f.getRenderManager().viewerPosY + var0.height / 2.0;
      double var16 = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * var2 - f.getRenderManager().viewerPosZ;
      EntityRendererAccessor.k(f.entityRenderer, var2, 0);
      ScaledResolution var18 = new ScaledResolution(f);
      Vec3 var19 = Expo.util.render.RenderUtil.I(var18.getScaleFactor(), var12, var14, var16);
      if (var19 != null) {
         f.entityRenderer.setupOverlayRendering();
         ScaledResolution var20 = new ScaledResolution(f);
         double var21 = var19.xCoord - var20.getScaledWidth() / 2.0;
         double var23 = var19.yCoord - var20.getScaledHeight() / 2.0;
         boolean var25 = var19.zCoord < 1.0003684;
         if (!var25) {
            var21 *= -1.0;
            var23 *= -1.0;
         }

         double var26 = Math.atan2(var21, var23);
         double var28 = Math.atan2(var23, var21) * (float) (180.0 / Math.PI) + 90.0;
         double var30 = Math.hypot(var21, var23);
         if (!var25 || !(var30 < var5 + 15.0)) {
            double var32 = var20.getScaledWidth() / 2.0;
            double var34 = var20.getScaledHeight() / 2.0;
            double var36 = Math.sin(var26);
            double var38 = Math.cos(var26);
            double var40 = var32 + var5 * var36;
            double var42 = var34 + var5 * var38;
            GlStateManager.pushMatrix();
            GlStateManager.translate(var40, var42, 0.0);
            GlStateManager.rotate((float)var28, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(1.0F, 1.0F, 1.0F);
            double var44 = f.thePlayer.getDistanceToEntity(var0);
            boolean var46 = var44 <= 10.0;
            boolean var47 = var46 && System.currentTimeMillis() % 400L < 200L;
            if (!var47) {
               A(var1);
            }

            GlStateManager.popMatrix();
            var40 = var32 + (var5 - 13.0) * var36;
            var42 = var34 + (var5 - 13.0) * var38;
            GlStateManager.pushMatrix();
            GlStateManager.translate(var40, var42, 0.0);
            GlStateManager.scale(0.8, 0.8, 0.8);
            if (var7) {
               String var48 = (int)var44 + "m";
               FontRenderer var49 = f.fontRendererObj;
               int var50 = var46 ? -65536 : -1;
               var49.drawStringWithShadow(var48, -var49.getStringWidth(var48) / 2, -4.0F, var50);
            }

            GlStateManager.popMatrix();
         }
      }
   }

   static {
      renderEnderPearls = new BooleanSetting("Render-ender-pearls", true);
      renderEggs = new BooleanSetting("Render-eggs", false);
      renderFireballs = new BooleanSetting("Render-fireballs", true);
      renderSnowballs = new BooleanSetting("Render-snowballs", false);
      renderOnlyOffscreen = new BooleanSetting("Render-only-offscreen", false);
      itemColors = new BooleanSetting("Item-colors", true);
      renderArrows = new BooleanSetting("Render-arrows", true);
      onlyWhenApproaching = new BooleanSetting("Only-when-approaching", false);
      renderDistance = new BooleanSetting("Render-distance", true);
      circleRadius = new NumberSetting("Circle-radius", 50.0F, 30.0F, 200.0F, 5.0F);
   }
}
