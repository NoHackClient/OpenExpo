package Expo.ui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;

class MainMenuStyleList extends GuiSlot {
   private static long a;
   final MainMenuStyleScreen b;

   static {
      a = 80767251755621L;
   }

   protected int getScrollBarX() {
      return (this.width + this.getListWidth()) / 2 + 2;
   }

   protected boolean isSelected(int var1) {
      return var1 == MainMenuStyleScreen.F(this.b);
   }

   protected int getSize() {
      return MainMenuTheme.mode.S().size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      MainMenuStyleScreen.b(this.b, var1);
      this.b.updateScreen();
      if (var2) {
         this.b.actionPerformed(MainMenuStyleScreen.y(this.b));
      }
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      String var9 = MainMenuTheme.mode.S().get(var1);
      String var10 = var9;
      int var11 = -1;
      if (MainMenuTheme.mode.Y().equals(var9)) {
         var11 = 16755200;
         var10 = "> " + var10 + " <";
      }

      this.b.drawCenteredString(MainMenuStyleScreen.X(this.b), var10, this.b.width / 2, var3 + 3, var11);
   }

   public int getListWidth() {
      return 200;
   }

   public MainMenuStyleList(MainMenuStyleScreen var1, long var2, Minecraft var4) {
      super(
         var4,
         var1.width,
         var1.height,
         32,
         var1.height - 64,
         20
      );
      this.b = var1;
   }

   protected void drawBackground() {
      this.b.drawDefaultBackground();
   }

   protected int getContentHeight() {
      return MainMenuTheme.mode.S().size() * 20;
   }
}
