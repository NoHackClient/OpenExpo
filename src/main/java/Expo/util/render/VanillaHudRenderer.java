package Expo.util.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;











public class VanillaHudRenderer extends Gui {
   private static final ResourceLocation e = new ResourceLocation("textures/gui/widgets.png");

   private void A(EntityPlayer var1, int var2, int var3) {
      if (var1.isInsideOfMaterial(Material.water)) {
         int var4 = var1.getAir();
         int var5 = MathHelper.ceiling_double_int((var4 - 2) * 10.0 / 300.0);
         int var6 = MathHelper.ceiling_double_int(var4 * 10.0 / 300.0) - var5;
         int var7 = var3 - 10;

         for (int var8 = 0; var8 < var5 + var6; var8++) {
            int var9 = var2 - var8 * 8 - 9;
            this.drawTexturedModalRect(var9, var7, var8 < var5 ? 16 : 25, 18, 9, 9);
         }
      }
   }

   private void j(Minecraft var1, ScaledResolution var2) {
      Entity var3 = var1.getRenderViewEntity();
      if (var3 instanceof EntityPlayer && var1.playerController != null && var1.playerController.gameIsSurvivalOrAdventure()) {
         EntityPlayer var4 = (EntityPlayer)var3;
         int var5 = var2.getScaledWidth() / 2 - 91;
         int var6 = var2.getScaledWidth() / 2 + 91;
         int var7 = var2.getScaledHeight() - 39;
         var1.getTextureManager().bindTexture(Gui.icons);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         this.Y(var4, var5, var7);
         this.I(var4, var5, var7);
         this.h(var4, var6, var7);
         this.A(var4, var6, var7);
         GlStateManager.disableBlend();
      }
   }

   private void C(Minecraft var1, ScaledResolution var2) {
      Scoreboard var3 = var1.theWorld.getScoreboard();
      if (var3 != null) {
         ScoreObjective var4 = this.o(var1, var3);
         if (var4 != null) {
            FontRenderer var5 = var1.fontRendererObj;
            if (var5 != null) {
               ArrayList var6 = new ArrayList();

               for (Score var8 : var3.getSortedScores(var4)) {
                  String var9 = var8.getPlayerName();
                  if (var9 != null && !var9.startsWith("#")) {
                     var6.add(var8);
                  }
               }

               if (!var6.isEmpty()) {
                  int var25 = Math.max(0, var6.size() - 15);
                  List var26 = var6.subList(var25, var6.size());
                  int var27 = var5.getStringWidth(var4.getDisplayName());

                  for (Score var11 : (Iterable<Score>)(var26)) {
                     ScorePlayerTeam var12 = var3.getPlayersTeam(var11.getPlayerName());
                     String var13 = ScorePlayerTeam.formatPlayerName(var12, var11.getPlayerName());
                     String var14 = EnumChatFormatting.RED + String.valueOf(var11.getScorePoints());
                     var27 = Math.max(var27, var5.getStringWidth(var13 + ":" + var14));
                  }

                  int var28 = var5.FONT_HEIGHT;
                  int var29 = var26.size() * var28;
                  byte var30 = 3;
                  int var31 = var2.getScaledWidth() - var30 + 2;
                  int var32 = var2.getScaledWidth() - var27 - var30;
                  int var15 = var2.getScaledHeight() / 2 + var29 / 3;
                  int var16 = 0;

                  for (Score var18 : (Iterable<Score>)(var26)) {
                     ScorePlayerTeam var19 = var3.getPlayersTeam(var18.getPlayerName());
                     String var20 = ScorePlayerTeam.formatPlayerName(var19, var18.getPlayerName());
                     String var21 = EnumChatFormatting.RED + "" + var18.getScorePoints();
                     var16++;
                     int var22 = var15 - var16 * var28;
                     drawRect(var32 - 2, var22, var31, var22 + var28, 1342177280);
                     var5.drawString(var20, var32, var22, 553648127);
                     var5.drawString(var21, var31 - var5.getStringWidth(var21), var22, 553648127);
                     if (var16 == var26.size()) {
                        String var23 = var4.getDisplayName();
                        int var24 = var22 - var28;
                        drawRect(var32 - 2, var24 - 1, var31, var24 + var28 - 1, 1610612736);
                        drawRect(var32 - 2, var24, var31, var24 + var28, 1342177280);
                        var5.drawString(var23, var32 + var27 / 2 - var5.getStringWidth(var23) / 2, var24, 553648127);
                     }
                  }
               }
            }
         }
      }
   }

   private void Y(EntityPlayer var1, int var2, int var3) {
      int var4 = var1.getTotalArmorValue();
      if (var4 > 0) {
         for (int var5 = 0; var5 < 10; var5++) {
            int var6 = var2 + var5 * 8;
            if (var5 * 2 + 1 < var4) {
               this.drawTexturedModalRect(var6, var3 - 10, 34, 9, 9, 9);
            } else if (var5 * 2 + 1 == var4) {
               this.drawTexturedModalRect(var6, var3 - 10, 25, 9, 9, 9);
            } else {
               this.drawTexturedModalRect(var6, var3 - 10, 16, 9, 9, 9);
            }
         }
      }
   }

   private void i(Minecraft var1, ScaledResolution var2) {
      if (var1.gameSettings != null && var1.gameSettings.thirdPersonView == 0) {
         var1.getTextureManager().bindTexture(Gui.icons);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
         int var3 = var2.getScaledWidth() / 2 - 7;
         int var4 = var2.getScaledHeight() / 2 - 7;
         this.drawTexturedModalRect(var3, var4, 0, 0, 16, 16);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }
   }

   private void h(EntityPlayer var1, int var2, int var3) {
      int var4 = var1.getFoodStats().getFoodLevel();
      int var5 = var1.isPotionActive(Potion.hunger) ? 52 : 16;

      for (int var6 = 0; var6 < 10; var6++) {
         int var7 = var2 - var6 * 8 - 9;
         int var8 = var3;
         this.drawTexturedModalRect(var7, var8, 16, 27, 9, 9);
         int var9 = var6 * 2 + 2;
         if (var9 <= var4) {
            this.drawTexturedModalRect(var7, var8, var5 + 36, 27, 9, 9);
         } else if (var9 - 1 == var4) {
            this.drawTexturedModalRect(var7, var8, var5 + 45, 27, 9, 9);
         }
      }
   }

   public void e(Minecraft var1, float var2) {
      if (var1 != null && var1.thePlayer != null && var1.theWorld != null) {
         ScaledResolution var3 = new ScaledResolution(var1);
         var1.entityRenderer.setupOverlayRendering();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         this.i(var1, var3);
         this.o(var1, var3);
         this.j(var1, var3);
         this.e(var1, var3);
         this.C(var1, var3);
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   private void I(EntityPlayer var1, int var2, int var3) {
      int var4 = MathHelper.ceiling_float_int(var1.getHealth());
      int var5 = MathHelper.ceiling_float_int(var1.getMaxHealth());
      int var6 = MathHelper.ceiling_float_int(var1.getAbsorptionAmount());
      int var7 = MathHelper.ceiling_float_int((var5 + var6) / 20.0F);
      int var8 = Math.max(10 - (var7 - 2), 3);
      byte var9 = 16;
      if (var1.isPotionActive(Potion.poison)) {
         var9 += 36;
      } else if (var1.isPotionActive(Potion.wither)) {
         var9 += 72;
      }

      int var10 = MathHelper.ceiling_float_int((var5 + var6) / 2.0F);

      for (int var11 = var10 - 1; var11 >= 0; var11--) {
         int var12 = MathHelper.ceiling_float_int((var11 + 1) / 10.0F) - 1;
         int var13 = var11 % 10;
         int var14 = var2 + var13 * 8;
         int var15 = var3 - var12 * var8;
         this.drawTexturedModalRect(var14, var15, 16, 0, 9, 9);
         int var16 = var11 * 2 + 2;
         if (var11 * 2 + 1 < var4) {
            this.drawTexturedModalRect(var14, var15, var9 + 36, 0, 9, 9);
         } else if (var11 * 2 + 1 == var4) {
            this.drawTexturedModalRect(var14, var15, var9 + 45, 0, 9, 9);
         } else if (var16 <= var4 + var6) {
            this.drawTexturedModalRect(var14, var15, 160, 0, 9, 9);
         } else if (var16 - 1 == var4 + var6) {
            this.drawTexturedModalRect(var14, var15, 169, 0, 9, 9);
         }
      }
   }

   private void e(Minecraft var1, ScaledResolution var2) {
      if (var1.playerController != null && var1.playerController.gameIsSurvivalOrAdventure() && var1.thePlayer != null && !var1.thePlayer.isRidingHorse()) {
         EntityPlayerSP var3 = var1.thePlayer;
         int var4 = var3.xpBarCap();
         if (var4 > 0) {
            int var5 = var2.getScaledWidth() / 2 - 91;
            int var6 = var2.getScaledHeight() - 29;
            var1.getTextureManager().bindTexture(Gui.icons);
            this.drawTexturedModalRect(var5, var6, 0, 64, 182, 5);
            int var7 = (int)(var3.experience * 183.0F);
            if (var7 > 0) {
               this.drawTexturedModalRect(var5, var6, 0, 69, var7, 5);
            }

            if (var3.experienceLevel > 0 && var1.fontRendererObj != null) {
               String var8 = String.valueOf(var3.experienceLevel);
               int var9 = var2.getScaledWidth() / 2 - var1.fontRendererObj.getStringWidth(var8) / 2;
               int var10 = var2.getScaledHeight() - 31 - 4;
               var1.fontRendererObj.drawString(var8, var9 + 1, var10, 0);
               var1.fontRendererObj.drawString(var8, var9 - 1, var10, 0);
               var1.fontRendererObj.drawString(var8, var9, var10 + 1, 0);
               var1.fontRendererObj.drawString(var8, var9, var10 - 1, 0);
               var1.fontRendererObj.drawString(var8, var9, var10, 8453920);
            }
         }
      }
   }

   private void o(Minecraft var1, ScaledResolution var2) {
      Entity var3 = var1.getRenderViewEntity();
      if (var3 instanceof EntityPlayer) {
         EntityPlayer var4 = (EntityPlayer)var3;
         int var5 = var2.getScaledWidth();
         int var6 = var2.getScaledHeight();
         int var7 = var5 / 2;
         var1.getTextureManager().bindTexture(e);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.zLevel = -90.0F;
         this.drawTexturedModalRect(var7 - 91, var6 - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(var7 - 91 - 1 + var4.inventory.currentItem * 20, var6 - 23, 0, 22, 24, 22);
         RenderItem var8 = var1.getRenderItem();
         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

         for (int var9 = 0; var9 < 9; var9++) {
            int var10 = var7 - 90 + var9 * 20 + 2;
            int var11 = var6 - 19;
            ItemStack var12 = var4.inventory.mainInventory[var9];
            if (var12 != null) {
               var8.renderItemAndEffectIntoGUI(var12, var10, var11);
               var8.renderItemOverlays(var1.fontRendererObj, var12, var10, var11);
            }
         }

         GlStateManager.disableRescaleNormal();
         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableLighting();
      }
   }

   private ScoreObjective o(Minecraft var1, Scoreboard var2) {
      ScoreObjective var3 = null;
      ScorePlayerTeam var4 = var2.getPlayersTeam(var1.thePlayer.getName());
      if (var4 != null && var4.getChatFormat() != null) {
         int var5 = var4.getChatFormat().getColorIndex();
         if (var5 >= 0) {
            var3 = var2.getObjectiveInDisplaySlot(3 + var5);
         }
      }

      return var3 != null ? var3 : var2.getObjectiveInDisplaySlot(1);
   }
}
