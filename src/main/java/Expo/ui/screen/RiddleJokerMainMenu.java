package Expo.ui.screen;

import Expo.util.MinecraftRef;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;











public class RiddleJokerMainMenu {
   private static String[] c;
   private static long a;
   private static Map d;
   private static Minecraft n;
   private static String[] b;

   public static void t(int var0, int var1) {
      n.getTextureManager().bindTexture(MainMenuTheme.d);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Gui.drawRect(0, 0, var0, var1, -1);
      Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, var0, (int)(var0 * 0.5625F), var0, var0 * 0.5625F);
   }

   public static void W(int var0, long var1, int var3, List var4) {
      var1 = a ^ var1;
      int var5 = (int)((var1 ^ 4847751822584L) >>> 32);
      int var6 = (int)((var1 ^ 4847751822584L) << 32 >>> 48);
      int var7 = (int)((var1 ^ 4847751822584L) << 48 >>> 48);
      int var8 = (int)(var3 / 2.0F);
      int var9 = (int)(var3 / 15.0F);
      int var10 = (int)(var3 / 20.0F);
      int var11 = (int)(var0 / 70.0F);
      int var12 = (int)(var3 / 20.0F * 8.0F);
      var4.add(new RiddleJokerMenuButton(1, var5, (short)var6, var11, var8, var12, var10, I18n.format("menu.singleplayer", new Object[0]), (short)var7));
      var4.add(new RiddleJokerMenuButton(2, var5, (short)var6, var11, var8 + var9, var12, var10, I18n.format("menu.multiplayer", new Object[0]), (short)var7));
      RiddleJokerMenuButton var13 = new RiddleJokerMenuButton(
         14,
         var5,
         (short)var6,
         var11,
         var8 + var9 * 2,
         var12,
         var10,
         I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim(),
         (short)var7
      );
      var4.add(var13);
      var4.add(
         new RiddleJokerMenuButton(
            6,
            var5,
            (short)var6,
            var11,
            var8 + var9 * 3,
            var12,
            var10,
            I18n.format("fml.menu.mods", new Object[0]),
            (short)var7
         )
      );
      var4.add(new RiddleJokerMenuButton(0, var5, (short)var6, var11, var8 + var9 * 4, var12, var10, I18n.format("menu.options", new Object[0]), (short)var7));
      var4.add(new RiddleJokerMenuButton(4, var5, (short)var6, var11, var8 + var9 * 5, var12, var10, I18n.format("menu.quit", new Object[0]), (short)var7));
      var4.add(new GuiButtonLanguage(5, var11, var8 + var9 * 6));
   }

   static {
      a = 34212351586263L;
      n = MinecraftRef.c((byte)0, 0L);
   }

   public static void L(int var0, List<GuiButton> var1) {
      for (GuiButton var3 : var1) {
         if (var3 instanceof RiddleJokerMenuButton) {
            var3.setWidth((int)(var0 / 20.0F * 8.0F));
            ((RiddleJokerMenuButton)var3).n((int)(var0 / 20.0F));
         }
      }
   }






}
