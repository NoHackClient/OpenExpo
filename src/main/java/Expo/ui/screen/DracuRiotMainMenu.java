package Expo.ui.screen;

import Expo.util.MinecraftRef;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;











public class DracuRiotMainMenu {
   private static long a;
   private static final float v = 1.7777778F;
   private static String[] b;
   private static String[] c;
   private static Minecraft T;

   private static float q(int var0) {
      return Math.max(1.45F, Math.min(3.05F, var0 / 205.0F));
   }

   private static int R(String[] var0, float var4) {
      int var7 = 0;

      for (String var11 : var0) {
         int var12 = (int)(T.fontRendererObj.getStringWidth(var11) * var4);
         if (var12 > var7) {
            var7 = var12;
         }
      }

      return var7 + Math.max(8, (int)(4.0F * var4));
   }



   public static void h(int var0, int var1) {
      T.getTextureManager().bindTexture(MainMenuTheme.z);
      int var2 = var0;
      int var3 = (int)(var0 / 1.7777778F);
      if (var3 < var1) {
         var3 = var1;
         var2 = (int)(var1 * 1.7777778F);
      }

      int var4 = (var0 - var2) / 2;
      int var5 = (var1 - var3) / 2;
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawRect(0, 0, var0, var1, -1);
      Gui.drawModalRectWithCustomSizedTexture(var4, var5, 0.0F, 0.0F, var2, var3, var2, var3);
   }

   public static void D(int var0, int var1, long var2, List var4) {
      long var8 = var2 ^ 113096942182600L;
      int[] var10000 = new int[6];
      var10000[0] = 1;
      var10000[1] = 2;
      var10000[2] = 14;
      var10000[3] = 6;
      var10000[4] = 0;
      var10000[5] = 4;
      int[] var10 = var10000;
      String[] var21 = new String[6];
      var21[0] = I18n.format("menu.singleplayer", new Object[0]);
      var21[1] = I18n.format("menu.multiplayer", new Object[0]);
      var21[2] = I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim();
      var21[3] = I18n.format("fml.menu.mods", new Object[0]);
      var21[4] = I18n.format("menu.options", new Object[0]);
      var21[5] = I18n.format("menu.quit", new Object[0]);
      String[] var11 = var21;
      float var12 = q(var1);
      int var13 = Math.max(12, (int)(T.fontRendererObj.FONT_HEIGHT * var12));
      int var14 = Math.max(var13 + 3, (int)(12.0F * var12));
      int var15 = var1 - 24 - var13 - Math.max(6, (int)(3.0F * var12));
      int var16 = R(var11, var12);
      int var17 = var0 - Math.max(6, (int)(var0 * 0.008F)) - var16;

      for (int var18 = 0; var18 < var11.length; var18++) {
         DracuRiotMenuButton var19 = new DracuRiotMenuButton(var10[var18], var17, var15 - var14 * (var11.length - 1 - var18), var16, var13, var8, var11[var18]);
         var19.k(var12);
         var19.U(var13);
         var4.add(var19);
      }
   }

   static {
      a = 61551126875408L;
      T = MinecraftRef.c((byte)0, 0L);
   }

   public static void u(int var0, int var1, long var2, List var4) {
      var2 = a ^ var2;
      int var6 = (int)((var2 ^ 119103937162686L) << 32 >>> 48);
      float var8 = q(var1);
      int var9 = var0 - Math.max(6, (int)(var0 * 0.008F));
      int var10 = var1 - 24;
      int var11 = Math.max(12, (int)(T.fontRendererObj.FONT_HEIGHT * var8));
      int var12 = Math.max(var11 + 3, (int)(12.0F * var8));
      int var13 = var10 - var11 - Math.max(6, (int)(3.0F * var8));
      int var14 = b(var4, (char)var6, var8);
      int var15 = 0;

      for (GuiButton var17 : (Iterable<GuiButton>)(var4)) {
         if (var17 instanceof DracuRiotMenuButton) {
            ((DracuRiotMenuButton)var17).k(var8);
            var17.setWidth(var14);
            ((DracuRiotMenuButton)var17).U(var11);
            var17.xPosition = var9 - var14;
            var17.yPosition = var13 - var12 * (5 - var15);
            var15++;
         }
      }
   }
   private static int b(List var0, char var2, float var4) {
      int var7 = 0;

      for (GuiButton var9 : (Iterable<GuiButton>)(var0)) {
         if (var9 instanceof DracuRiotMenuButton) {
            int var10 = (int)(T.fontRendererObj.getStringWidth(var9.displayString) * var4);
            if (var10 > var7) {
               var7 = var10;
            }
         }
      }

      return var7 + Math.max(8, (int)(4.0F * var4));
   }


}
