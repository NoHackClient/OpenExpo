package Expo.ui.screen;

import Expo.module.Modules;
import Expo.util.Sneaky;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;


public class MainMenuStyleScreen extends GuiScreen {
   private final GuiScreen H;
   private static long b;
   private MainMenuStyleList z;
   private int h;
   private GuiButton m;
   private GuiButton N;
   private GuiButton a;

   static FontRenderer X(MainMenuStyleScreen var0) {
      return var0.fontRendererObj;
   }

   static int F(MainMenuStyleScreen var0) {
      return var0.h;
   }

   static GuiButton y(MainMenuStyleScreen var0) {
      return var0.N;
   }

   public void initGui() {

      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.buttonList
         .add(
            this.N = new GuiButton(
               0,
               this.width / 2 - 150 - 4,
               this.height - 52,
               150,
               20,
               "Select"
            )
         );
      this.buttonList
         .add(
            this.m = new GuiButton(
               1,
               this.width / 2 + 4,
               this.height - 52,
               150,
               20,
               "Cancel"
            )
         );
      this.buttonList
         .add(
            this.a = new GuiButton(
               2,
               4,
               this.height - 24,
               110,
               20,
               this.O()
            )
         );
      this.z = new MainMenuStyleList(this, 120694520305086L, this.mc);
      this.z.registerScrollButtons(4, 5);
      this.h = this.D();
      this.updateScreen();
   }

   protected void actionPerformed(GuiButton var1) {
       try {long var2 = 94303189473383L;

      if (var1 != null) {
         switch (var1.id) {
            case 0:
               if (this.h >= 0 && this.h < MainMenuTheme.mode.S().size()) {
                  String var8 = MainMenuTheme.mode.S().get(this.h);
                  MainMenuTheme.mode.i(var8);
                  Modules.c(0L);
               }
               break;
            case 1:
               this.mc.displayGuiScreen(this.H);
               break;
            case 2:
               MainMenuTheme.music.W(112370683098682L);
               Modules.c(0L);
               this.updateScreen();
               break;
            default:
               if (this.z != null) {
                  this.z.actionPerformed(var1);
               }
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void drawScreen(int var1, int var2, float var3) {
      if (this.z != null) {
         this.z.drawScreen(var1, var2, var3);
      }

      super.drawScreen(var1, var2, var3);
      this.drawCenteredString(this.fontRendererObj, "Main Menu style", this.width / 2, 20, -1);
      if (this.h >= 0 && this.h < MainMenuTheme.mode.S().size()) {
         String var6 = MainMenuTheme.mode.S().get(this.h);
         this.drawCenteredString(this.fontRendererObj, "Selected: " + var6, this.width / 2, this.height - 30, -1);
      }
   }

   private int D() {
      return MainMenuTheme.mode.G();
   }

   static {

      b = 88517798541407L;
   }

   public void handleMouseInput() {
      if (this.z != null) {
         this.z.handleMouseInput();
      }

      super.handleMouseInput();
   }

   public MainMenuStyleScreen(GuiScreen var1) {
      this.z = null;
      this.N = null;
      this.m = null;
      this.a = null;
      this.h = -1;
      this.H = var1;
   }


   private String O() {
      return "Music: " + (MainMenuTheme.music.c() ? "ON" : "OFF");
   }

   public void updateScreen() {
      if (this.N != null) {
         this.N.enabled = this.h >= 0;
      }

      if (this.a != null) {
         this.a.displayString = this.O();
      }
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   static int b(MainMenuStyleScreen var0, int var1) {
      return var0.h = var1;
   }

   protected void keyTyped(char var1, int var2) {
      switch (var2) {
         case 1:
            this.actionPerformed(this.m);
            break;
         case 28:
            this.actionPerformed(this.N);
            break;
         case 200:
            if (this.h > 0) {
               this.h--;
            }
            break;
         case 208:
            if (this.h < MainMenuTheme.mode.S().size() - 1) {
               this.h++;
            }
      }
   }


}
