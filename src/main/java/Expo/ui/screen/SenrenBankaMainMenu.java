package Expo.ui.screen;

import Expo.util.MinecraftRef;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;











public class SenrenBankaMainMenu {
   private static long a;
   private static String[] c;
   private static Minecraft F;

   public static void W(int var0, int var1) {
      F.getTextureManager().bindTexture(MainMenuTheme.a);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawRect(0, 0, var0, var1, -1);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, var0, (int)(var0 * 0.5625F), var0, var0 * 0.5625F);
   }



   public static void l(int var0, int var1, long var2, List var4) {
      long var5 = var2 ^ 73161443938117L;
      int var7 = (int)(var1 / 2.9F);
      int var8 = (int)(var1 / 10.0F);
      int var9 = (int)(var1 / 12.0F);
      int var10 = (int)(var0 / 70.0F);
      int var11 = (int)(var0 / 6.0F);
      var4.add(new SenrenBankaMenuButton(1, var10, var5, var7, var11, var9, I18n.format("menu.singleplayer", new Object[0])));
      var4.add(new SenrenBankaMenuButton(2, var10, var5, var7 + var8, var11, var9, I18n.format("menu.multiplayer", new Object[0])));
      SenrenBankaMenuButton var12 = new SenrenBankaMenuButton(
         14,
         var10,
         var5,
         var7 + var8 * 2,
         var11,
         var9,
         I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim()
      );
      var4.add(var12);
      var4.add(new SenrenBankaMenuButton(6, var10, var5, var7 + var8 * 3, var11, var9, I18n.format("fml.menu.mods", new Object[0])));
      var4.add(new SenrenBankaMenuButton(0, var10, var5, var7 + var8 * 4, var11, var9, I18n.format("menu.options", new Object[0])));
      var4.add(new SenrenBankaMenuButton(4, var10, var5, var7 + var8 * 5, var11, var9, I18n.format("menu.quit", new Object[0])));
      var4.add(new GuiButtonLanguage(5, var10, var7 + var8 * 6));
   }

   public static void O(int var0, int var1, List<GuiButton> var2) {
      for (GuiButton var4 : var2) {
         if (var4 instanceof SenrenBankaMenuButton) {
            var4.setWidth((int)(var0 / 6.0F));
            ((SenrenBankaMenuButton)var4).N((int)(var1 / 12.0F));
         }
      }
   }
   static {
      a = 32583009526144L;
      F = MinecraftRef.c((byte)0, 0L);
   }

}
