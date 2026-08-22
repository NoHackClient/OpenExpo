package Expo.ui.screen;

import Expo.internal.auth.RandomUsernamePool;
import Expo.internal.auth.SessionSwapper;
import java.util.concurrent.CompletableFuture;
import javax.swing.SwingUtilities;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;











public class OfflineLoginScreen extends GuiScreen {
   private static long a;
   private GuiTextField b;
   private GuiButton t;
   private GuiButton C = null;
   private final GuiScreen B;
   private GuiButton q;

   protected void mouseClicked(int var1, int var2, int var3) {
      this.b.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }


   static {
      a = 46768291833639L;
   }

   protected void actionPerformed(GuiButton var1) {

      if (var1 != null && var1.enabled) {
         switch (var1.id) {
            case 0:
               this.m(124399565688198L);
               break;
            case 1:
               this.U();
               break;
            case 2:
               this.mc.displayGuiScreen(this.B);
               break;
            default:
         }
      }
   }

   public OfflineLoginScreen(GuiScreen var1) {
      this.B = var1;
   }

   public void updateScreen() {
      this.b.updateCursorCounter();
   }

   protected void keyTyped(char var1, int var2) {
      this.b.textboxKeyTyped(var1, var2);
      if (var2 == 1) {
         this.mc.displayGuiScreen(this.B);
      }
   }

   public void initGui() {
      this.b = new GuiTextField(
         0,
         this.fontRendererObj,
         this.width / 2 - 100,
         this.height / 2 - 30,
         200,
         20
      );
      this.b.setMaxStringLength(16);
      this.b.setFocused(true);
      this.t = new GuiButton(
         0,
         this.width / 2 - 100,
         this.height / 2,
         200,
         20,
         "Login"
      );
      this.buttonList.add(this.t);
      this.q = new GuiButton(
         1,
         this.width / 2 - 100,
         this.height / 2 + 25,
         200,
         20,
         "Generate Random"
      );
      this.buttonList.add(this.q);
      this.C = new GuiButton(
         2,
         this.width / 2 - 100,
         this.height / 2 + 50,
         200,
         20,
         "Cancel"
      );
      this.buttonList.add(this.C);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(
         this.fontRendererObj,
         "Offline Authentication",
         this.width / 2,
         this.height / 2 - 60,
         16777215
      );
      this.b.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   private void m(long var1) {



      String var7 = this.b.getText().trim();
      if (var7.isEmpty()) {
      } else {
         boolean var8 = SessionSwapper.D(var7, 14635617689442L);
         if (var8) {
            this.mc.displayGuiScreen(new AccountManagerScreen(81800336346822L, this.B));
         } else {
         }
      }
   }

   private void U() {
      CompletableFuture.runAsync(() -> {

         String var5 = RandomUsernamePool.x(77049211506207L);
         SwingUtilities.invokeLater(() -> this.b.setText(var5));
      });
   }

}
