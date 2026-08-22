package Expo.ui.screen;

import Expo.util.MinecraftRef;
import Expo.util.SoundEngine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;


public class SenrenBankaMenuButton extends GuiButton {
   private boolean h;
   private final float a;
   public int k;
   private static long b;

   private void U(String var1, float var2, float var3, long var4) {


      Minecraft var9 = MinecraftRef.c((byte)0,0L);
      String var10 = var1.replaceAll("(?i)§[\\da-f]", "");
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2, var3 + 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2, var3 - 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3 + 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3 + 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3 - 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3 - 1.0F, -2894893, false);
      var9.fontRendererObj.drawString(var1, var2, var3, -45824, false);
   }

   private void B(String var1, float var2, float var3, long var4) {


      Minecraft var9 = MinecraftRef.c((byte)0,0L);
      String var10 = var1.replaceAll("(?i)§[\\da-f]", "");
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3, -96, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3, -96, false);
      var9.fontRendererObj.drawString(var10, var2, var3 + 1.0F, -96, false);
      var9.fontRendererObj.drawString(var10, var2, var3 - 1.0F, -96, false);
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3 + 1.0F, -96, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3 + 1.0F, -96, false);
      var9.fontRendererObj.drawString(var10, var2 + 1.0F, var3 - 1.0F, -96, false);
      var9.fontRendererObj.drawString(var10, var2 - 1.0F, var3 - 1.0F, -96, false);
      var9.fontRendererObj.drawString(var1, var2, var3, -29696, false);
   }


   public SenrenBankaMenuButton(int var1, int var2, long var3, int var5, int var6, int var7, String var8) {
      super(
         (var1),
         (var2),
         (var5),
         (var6),
         (var7),
         (var8)
      );
      this.a = 1.0F;
      this.k = 20;
      this.h = 0 != 0;
   }

   static {

      b = 96817688816412L;
   }


   public void drawButton(Minecraft var1, int var2, int var3) {



      if (this.visible) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.xPosition
            && var3 >= this.yPosition
            && var2 < this.xPosition + this.width
            && var3 < this.yPosition + this.k;
         if (this.h && !this.hovered) {
            this.h = false;
         }

         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         var1.getTextureManager().bindTexture(MainMenuTheme.n);
         float var12 = this.k / 3.0F * 0.8F;
         float var13 = this.k / 3.0F;
         drawModalRectWithCustomSizedTexture(this.xPosition + 1, (int)(this.yPosition + this.k / 2.0F - var13 / 2.0F), 0.0F, 0.0F, (int)var12, (int)var13, var12, var13);
         this.mouseDragged(var1, var2, var3);
         float var14 = 1.4F;
         if (this.hovered) {
            if (!this.h) {
               this.h = true;
               SoundEngine.y(59424967409495L, "/assets/minecraft/mainmenu/option.ogg");
            }

            GlStateManager.pushMatrix();
            GlStateManager.scale(var14, var14, var14);
            this.B(this.displayString, (this.xPosition + this.k / 2.0F + 5.0F) / var14, (this.yPosition + (this.k - 8.0F) / 2.0F) / var14, 8173588228278L);
            GlStateManager.popMatrix();
         } else {
            GlStateManager.pushMatrix();
            GlStateManager.scale(var14, var14, var14);
            this.U(this.displayString, (this.xPosition + this.k / 2.0F + 5.0F) / var14, (this.yPosition + (this.k - 8.0F) / 2.0F) / var14, 137972018576089L);
            GlStateManager.popMatrix();
         }
      }
   }

   public void N(int var1) {
      this.k = var1;
   }

}
