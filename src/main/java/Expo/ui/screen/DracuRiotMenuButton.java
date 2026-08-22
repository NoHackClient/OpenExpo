package Expo.ui.screen;

import Expo.util.SoundEngine;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;


public class DracuRiotMenuButton extends GuiButton {
   private static String b;
   public int v;
   private boolean o;
   private static long a;
   private static long[] c;
   private float B;
   private static Map e;

   private void l(FontRenderer var1, String var2, float var3, float var4, int var5) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(this.B, this.B, 1.0F);
      var1.drawString(var2, var3 / this.B, var4 / this.B, var5, false);
      GlStateManager.popMatrix();
   }

   public void drawButton(Minecraft var1, int var2, int var3) {

      if (this.visible) {
         FontRenderer var10 = var1.fontRendererObj;
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.hovered = var2 >= this.xPosition
            && var3 >= this.yPosition
            && var2 < this.xPosition + this.width
            && var3 < this.yPosition + this.v;
         if (this.o && !this.hovered) {
            this.o = false;
         }

         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         this.mouseDragged(var1, var2, var3);
         if (this.hovered) {
            if (!this.o) {
               this.o = true;
               SoundEngine.y(59424967409495L, b);
            }

            this.C(var10,true);
         } else {
            this.C(var10,false);
         }
      }
   }


   public DracuRiotMenuButton(int var1, int var2, int var3, int var4, int var5, long var6, String var8) {
      super(
         (var1),
         (var2),
         (var3),
         (var4),
         (var5),
         (var8)
      );
      this.v = 20;
      this.B = 1.0F;
      this.o = 0 != 0;
   }



   public void U(int var1) {
      this.v = var1;
      super.height = var1;
   }

   private void C(FontRenderer var1,boolean var4) {
      int var5 = (int)(var1.getStringWidth(this.displayString) * this.B);
      float var6 = this.xPosition + this.width - var5;
      float var7 = this.yPosition;
      int var8 = var4 ? -2003125 : -6402497;

      int var10 = var4 ? -3898 : -9790;
      this.l(var1, this.displayString, var6 + this.B, var7 + this.B, -10014421);
      this.l(var1, this.displayString, var6 - this.B, var7, var8);
      this.l(var1, this.displayString, var6 + this.B, var7, var8);
      this.l(var1, this.displayString, var6, var7 - this.B, var8);
      this.l(var1, this.displayString, var6, var7 + this.B, var8);
      this.l(var1, this.displayString, var6, var7, var10);
   }

   public void k(float var1) {
      this.B = var1;
   }

   static {
      a = 115380999990494L;
      b = "/assets/minecraft/mainmenu/option.ogg";
      e = new HashMap(13);
      c = new long[]{-1264485471761501984L, 6215361741798071984L, 5107200575086882811L, -7531211150311414439L, 5782579429849861360L, 189833895540201679L, 6008160011562758701L, 6417825392000962573L, 8675782347306971290L, -6815582354269240955L, 753185595500222673L};
   }
}
